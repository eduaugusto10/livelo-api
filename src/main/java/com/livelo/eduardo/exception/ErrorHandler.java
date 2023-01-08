package com.livelo.eduardo.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ErrorHandler implements ResponseErrorHandler{
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
         return (response.getStatusCode().series() ==  HttpStatus.CONFLICT.series() 
                    || response.getStatusCode().series() == HttpStatus.UNAUTHORIZED.series()
                        || response.getStatusCode().series() == HttpStatus.NOT_FOUND.series());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // TODO Auto-generated method stub
        
    }
}
