package com.deloitte.copyright;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.deloitte.copyright.lambda.handler.CopyRightLambdaHandler;
import com.deloitte.copyright.model.CopyRightRequest;

@SpringBootTest
public class CopyRightAppApplicationTests {

	private SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	private static Context lambdaContext;

	public CopyRightAppApplicationTests() {
		handler = CopyRightLambdaHandler.handler;
		lambdaContext = new MockLambdaContext();
	}

	/**
	 * testFindAndReplaceSuccess tests CopyRight API with an Valid Input and expects
	 * a 200 - OK response with Copyright symbol added to the given input
	 */
	@Test
	public void testFindAndReplaceSuccess() {

		CopyRightRequest request = new CopyRightRequest();
		request.setRequest("Google Cloud");

		AwsProxyRequest lambdaProxyRequest = new AwsProxyRequestBuilder("/copyright", HttpMethod.POST)
												.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
												.body(request)
												.build();

		AwsProxyResponse lambdaProxyResponse = handler.proxy(lambdaProxyRequest, lambdaContext);

		assertNotNull(lambdaProxyResponse);
		assertTrue(lambdaProxyResponse.getBody().contains("Google© Cloud"));
		assertEquals(Response.Status.OK.getStatusCode(), lambdaProxyResponse.getStatusCode());
	}

	/**
	 * testFindAndReplaceBadRequest tests CopyRight API with an Invalid Input and
	 * expects a failure as the input passed to the API is Invalid / Blank
	 */
	@Test
	public void testFindAndReplaceBadRequest() {
		CopyRightRequest request = new CopyRightRequest();
		// Passing an Invalid Request and expecting a failure
		request.setRequest("");

		AwsProxyRequest lambdaProxyRequest = new AwsProxyRequestBuilder("/copyright", HttpMethod.POST)
												.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
												.body(request)
												.build();

		AwsProxyResponse lambdaProxyResponse = handler.proxy(lambdaProxyRequest, lambdaContext);

		assertNotNull(lambdaProxyResponse);
		assertTrue(lambdaProxyResponse.getBody().contains("Mandatory Element 'data' is missing in the API Request."));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), lambdaProxyResponse.getStatusCode());
	}
}