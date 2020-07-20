package com.trikorasolutions.mw.eam.maximo.rest;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.Provider;

@Provider
public class PostMessageBodyFilter implements ClientRequestFilter {
  @Override
  public void filter(ClientRequestContext requestContext) throws IOException {
    if (requestContext.getEntity() instanceof Map) {
      UriBuilder uriBuilder = UriBuilder.fromUri(requestContext.getUri());
      Map allParam = (Map) requestContext.getEntity();
      for (Object key : allParam.keySet()) {
        uriBuilder.queryParam(key.toString(), allParam.get(key));
      }
      requestContext.setUri(uriBuilder.build());
      requestContext.setEntity(null);
    }
  }
}
