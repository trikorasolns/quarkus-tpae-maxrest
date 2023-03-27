package com.trikorasolutions.mw.eam.maximo.rest.service.os.asset;

import com.trikorasolutions.mw.eam.maximo.rest.service.common.MaxRestCommonGetService;
import io.smallrye.mutiny.Multi;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.Map;

@Path("/os/tkr_mw_asset")
@RegisterRestClient(configKey = "tpae-api")
public interface TpaeOsAssetService extends MaxRestCommonGetService {

//    getLinearAssetMeterId
//    getDocuments
//    addDocument

    @POST
    @Path("/{id}")
    Multi<JsonObject> enterMeterReading(@PathParam("id") String assetuid, @QueryParam("_lid") String uid,
                                        @QueryParam("_lpwd") String pwd,
                                        @QueryParam("assetmeter.id1.metername") String metername,
                                        @QueryParam("assetmeter.id1.linearassetmeterid") String linearassetmeterid,
                                        @QueryParam("assetmeter.id1.newreading") String newreading,
                                        @QueryParam("assetmeter.id1.isdelta") String isdelta,
                                        @QueryParam("assetmeter.id1.newreadingdate") String newreadingdate,
                                        @QueryParam("assetmeter.id1.inspector") String inspector,
                                        Map<String, String> multiValueMap);

    @POST
    @Path("/{id}")
    Multi<JsonObject> enterMeasurement(@PathParam("id") String assetuid, @QueryParam("_lid") String uid,
                                       @QueryParam("_lpwd") String pwd,
                                       @QueryParam("assetmeter.id1.metername") String metername,
                                       @QueryParam("assetmeter.id1.measurement.id2.linearassetmeterid")
                                       String linearassetmeterid,
                                       @QueryParam("assetmeter.id1.measurement.id2.measurementvalue") String measurementvalue,
                                       @QueryParam("assetmeter.id1.measurement.id2.measuredate") String measuredate,
                                       @QueryParam("assetmeter.id1.measurement.id2.inspector") String inspector,
                                       @QueryParam("assetmeter.id1.measurement.id2.observation") String inspector,
                                       Map<String, String> multiValueMap);

}
