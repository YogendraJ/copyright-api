package com.deloitte.copyright.lambda.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.deloitte.copyright.CopyRightApplication;

/**
 * This class is the entry point from AWS Lambda Function ('copyright-api-handler').
 * @author Yogendra Joshi
 *
 */
public class CopyRightLambdaHandler implements RequestStreamHandler {

    public static final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
 
    //Static block to preload the Spring Boot Application (CopyRightApplication.class)
    static { 
       try { 
           handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(CopyRightApplication.class);
       } catch (ContainerInitializationException e) { 
           throw new RuntimeException("Could not initialize Spring Boot application", e); 
       } 
    }

    /**
     * This is the overriden version of the implementation of RequestStreamHandler
     * provided by AWS. This 'handleRequest' method will delegate the call to 
     * the actual REST Controller within the CopyRight API
     */
    @Override 
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}