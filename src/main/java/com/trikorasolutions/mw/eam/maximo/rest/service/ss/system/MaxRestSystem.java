package com.trikorasolutions.mw.eam.maximo.rest.service.ss.system;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Base64;
import java.util.Optional;

/**
 *
 * <h2>IBM Reference documentation</h2>
 * Source: https://www.ibm.com/support/knowledgecenter/SSLKT6_7.6.0/com.ibm.mif
 * .doc/gp_intfrmwk/rest_api/c_rest_service_querydata.html
 * <p>Services for the <i>/ss/system</i></p>
 * <p>To obtain a list of all services you can use the following GET method:<br/>
 * <pre>GET /maxrest/rest/ss</pre
 * </p>
 * <p>You can then drill down to each of the services in the list to get a list of operations that can be called. For
 * example, the following method returns the operations for the service that is called system:<br/>
 * <pre>GET /maxrest/rest/ss/system</pre
 * </p>
 */
@Path("/ss/system")
@RegisterRestClient(configKey = "tpae-api")
@Produces(MediaType.APPLICATION_JSON)
@ClientHeaderParam(name = "Authorization", value = "{generateAuthHeader}")
public interface MaxRestSystem {

  default String generateAuthHeader() {
    final Optional<String> tpaeUser = ConfigProvider.getConfig().getOptionalValue("tpae.user", String.class);
    final Optional<String> tpaePassword = ConfigProvider.getConfig().getOptionalValue("tpae.password", String.class);
    if (tpaeUser.isPresent() && tpaePassword.isPresent()) {
      return "Basic " +  Base64.getEncoder().encodeToString((tpaeUser.get() + ":" + tpaePassword.get()).getBytes());
    } else {
      return null;
    }
  }

  @GET
  @Path("getDate")
  Uni<JsonObject> getDate(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd);

  @GET
  @Path("login")
  Uni<JsonObject> login(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd);

  @GET
  @Path("logout")
  Uni<JsonObject> logout(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd);
}
