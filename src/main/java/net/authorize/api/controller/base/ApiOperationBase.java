package net.authorize.api.controller.base;

import java.lang.reflect.ParameterizedType;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import net.authorize.Environment;
import net.authorize.api.contract.v1.ANetApiRequest;
import net.authorize.api.contract.v1.ANetApiResponse;
import net.authorize.api.contract.v1.ImpersonationAuthenticationType;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.MessagesType;
import net.authorize.api.contract.v1.MessagesType.Message;
import net.authorize.util.HttpUtility;
import net.authorize.util.LogHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ramittal
 *
 */
public abstract class ApiOperationBase<Q extends ANetApiRequest, S extends ANetApiResponse> implements IApiOperation<Q, S> {

	protected static Log logger = LogFactory.getLog(ApiOperationBase.class);

	private static Environment environment = null;
	private static MerchantAuthenticationType merchantAuthentication = null;
	
	private Q apiRequest = null;
	private S apiResponse = null;

	Class<Q> requestClass = null;
	Class<S> responseClass = null;

	private ANetApiResponse errorResponse = null;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected ApiOperationBase(Q apiRequest) {
		if ( null == apiRequest)
		{
			logger.error(apiRequest);
			throw new NullPointerException( "Input request cannot be null");
		}
		if ( null != this.getApiResponse())
		{
			logger.error(this.getApiResponse());
			throw new IllegalStateException( "Response should be null");
		}
		
		this.requestClass = (Class<Q>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.responseClass = this.getResponseType();
		this.setApiRequest(apiRequest);
		
		logger.info(String.format("Creating instance for request:'%s' and response:'%s'", requestClass, responseClass));
		logger.info(String.format("Request:'%s'", apiRequest));
		validate();
	}
	
	protected Q getApiRequest() {
		return apiRequest;
	}

	protected void setApiRequest(Q apiRequest) {
		this.apiRequest = apiRequest;
	}

	public S getApiResponse() {
		return apiResponse;
	}

	private void setApiResponse(S apiResponse) {
		this.apiResponse = apiResponse;
	}

	public ANetApiResponse getErrorResponse() {
		return errorResponse;
	}

	private void setErrorResponse(ANetApiResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public static Environment getEnvironment() {
		return environment;
	}

	public static void setEnvironment(Environment environment) {
		ApiOperationBase.environment = environment;
	}

	public static MerchantAuthenticationType getMerchantAuthentication() {
		return merchantAuthentication;
	}

	public static void setMerchantAuthentication(
			MerchantAuthenticationType merchantAuthentication) {
		ApiOperationBase.merchantAuthentication = merchantAuthentication;
	}

	public S executeWithApiResponse() {
		return this.executeWithApiResponse(ApiOperationBase.getEnvironment());
	}

	public S executeWithApiResponse(Environment environment) {
		this.execute(environment);
		return this.getApiResponse();
	}

	final String nullEnvironmentErrorMessage = "Environment not set. Set environment using setter or use overloaded method to pass appropriate environment";

	public void execute() {
		if ( null == ApiOperationBase.getEnvironment())
		{
			throw new InvalidParameterException(nullEnvironmentErrorMessage);
		} 
		else
		{
			this.execute( ApiOperationBase.getEnvironment());
		}
	}
	
	public void execute(Environment environment) {
		logger.info(String.format("Executing Request:'%s'", this.getApiRequest()));
		
		if ( null == environment) throw new InvalidParameterException(nullEnvironmentErrorMessage);
		
		beforeExecute();

		ANetApiResponse httpApiResponse = HttpUtility.postData(environment, this.getApiRequest(), this.responseClass);
		if ( null != httpApiResponse)
		{
			logger.info(String.format("Received Response:'%s' for request:'%s'", httpApiResponse, this.getApiRequest()));
			if ( httpApiResponse.getClass() == responseClass)
			{
				@SuppressWarnings("unchecked")
				S response = (S) httpApiResponse;
				this.setApiResponse( response);
				logger.info(String.format("Setting response: '%s'", response));				
			} else if (httpApiResponse.getClass() == net.authorize.api.contract.v1.ErrorResponse.class) {
				this.setErrorResponse(httpApiResponse);
				logger.info(String.format("Received ErrorResponse:'%s'", httpApiResponse));
			} else {
				this.setErrorResponse(httpApiResponse);
				logger.error(String.format("Invalid response:'%s'", httpApiResponse));
			}
			this.setResultStatus();
			
		} else {
			logger.info(String.format("Got a 'null' Response for request:'%s'%s", this.getApiRequest(), LogHelper.LineSeparator));
		}
		afterExecute();
	}

	public MessageTypeEnum getResultCode() {
		return this.resultCode;
	}
	
	private void setResultStatus() {
		this.results = new ArrayList<String>();
		MessagesType messageTypes = getResultMessage();
		
		if (null != messageTypes) {
			this.resultCode = messageTypes.getResultCode();
		}
		
		if (null != messageTypes) {
			for ( Message amessage : messageTypes.getMessage()) {
				this.results.add(String.format( "%s:%s", amessage.getCode(), amessage.getText()));
			}
		}
	}

	public List<String> getResults() {
		return this.results;
	}

	private MessagesType getResultMessage() {
		MessagesType messageTypes = null;
		
		if ( null != this.getErrorResponse())
		{
			messageTypes = this.getErrorResponse().getMessages();
		} else if ( null != this.getApiResponse()) {
			messageTypes = this.getApiResponse().getMessages();
		}

		return messageTypes;
	}
	
	protected List<String> results = null;
	protected MessageTypeEnum resultCode = null;
	
	protected void beforeExecute() {}
	protected void afterExecute() {}
 
	protected abstract void validateRequest();
	protected abstract Class<S> getResponseType();
	
	private void validate() {

		ANetApiRequest request = this.getApiRequest();

		//validate not nulls
		validateAndSetMerchantAuthentication();

		//validate nulls
		MerchantAuthenticationType merchantAuthenticationType = request.getMerchantAuthentication();
		//if ( null != ) throw new IllegalArgumentException(" needs to be null");
		
		if ( null != merchantAuthenticationType.getSessionToken()) throw new IllegalArgumentException("SessionToken needs to be null");
		if ( null != merchantAuthenticationType.getPassword()) throw new IllegalArgumentException("Password needs to be null");
		if ( null != merchantAuthenticationType.getMobileDeviceId()) throw new IllegalArgumentException("MobileDeviceId needs to be null");
	    
	    ImpersonationAuthenticationType impersonationAuthenticationType = merchantAuthenticationType.getImpersonationAuthentication();
		if ( null != impersonationAuthenticationType) throw new IllegalArgumentException("ImpersonationAuthenticationType needs to be null");
//	    impersonationAuthenticationType.setPartnerLoginId(CnpApiLoginIdKey);
//	    impersonationAuthenticationType.setPartnerTransactionKey(CnpTransactionKey);
//	    merchantAuthenticationType.setImpersonationAuthentication(impersonationAuthenticationType);

		validateRequest();
	}
	
	private void validateAndSetMerchantAuthentication() {

		ANetApiRequest request = this.getApiRequest();
		if ( null == request.getMerchantAuthentication())
		{
			if ( null != ApiOperationBase.getMerchantAuthentication())
			{
				request.setMerchantAuthentication(ApiOperationBase.getMerchantAuthentication());
			}
			else
			{
				throw new NullPointerException("MerchantAuthentication cannot be null");
			}
		}
	}
}
