package com.trikorasolutions.mw.eam.maximo.rest.service.mbo.asset;

import com.trikorasolutions.mw.eam.maximo.rest.TpaeMaxRestConstants;
import com.trikorasolutions.mw.eam.maximo.rest.service.common.MaxRestCommonGetService;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Map;

@Path("/mbo/asset")
@RegisterRestClient(configKey = "tpae-api")
public interface TpaeMboAssetService extends MaxRestCommonGetService {


    @GET
    @Path("/")
    Uni<JsonObject> getByAssetnum(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd,
                                  @QueryParam(TpaeMaxRestConstants.SITE_ID) String siteid,
                                  @QueryParam("assetnum") String assetnum,
                                  Map<String, String> multiValueMap);

}
