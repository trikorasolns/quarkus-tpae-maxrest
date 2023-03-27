/**
 *
 */

package com.trikorasolutions.mw.eam.maximo.rest.impl.asset;

import com.trikorasolutions.mw.eam.maximo.rest.TpaeMaxRestConstants;
import com.trikorasolutions.mw.eam.maximo.rest.service.mbo.asset.TpaeMboAssetService;
import com.trikorasolutions.mw.eam.maximo.rest.service.mbo.asset.TpaeMboLocationService;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Map;


/**
 * <p> Implementation of Asset service. </p>
 * ${properties:maximoBaseFolder}/mbo/workorder?_keys=true&amp;SITEID~eq~${headers:SITEID}&amp;CHANGEDATE=~gteq~2015-01-
 * 01
 *
 * @author Trikora Solutions
 */
@ApplicationScoped
public class AssetTpaeMaxRestServiceImpl {

    /**
     * <p> Logger </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(AssetTpaeMaxRestServiceImpl.class);

    /**
     * <p> Output date format. </p>
     */
    protected final SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Inject
    @RestClient
    TpaeMboAssetService assetService;

    @Inject
    @RestClient
    TpaeMboLocationService locationService;

    public Uni<JsonObject> getAsset(String userName, String userPwd, String siteId, String assetNum,
                                    Map<String, String> hmUrlParameters) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("#getAsset(...)...");
        }
        return assetService.getByAssetnum(userName, userPwd, TpaeMaxRestConstants.QBE_EQUALS + siteId,
                TpaeMaxRestConstants.QBE_EQUALS + assetNum, hmUrlParameters);
    }

    public Uni<JsonObject> queryAsset(String userName, String userPwd, String siteId, String assetNum,
                                      Map<String, String> hmUrlParameters) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("#queryAsset(...)...");
        }
        return assetService.getByAssetnum(userName, userPwd, siteId, assetNum, hmUrlParameters);
    }

    public Uni<JsonObject> getLocation(String userName, String userPwd, String siteId, String location,
                                       Map<String, String> hmUrlParameters) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("#getLocation(...)...");
        }
        return locationService.getByLocation(userName, userPwd, TpaeMaxRestConstants.QBE_EQUALS + siteId,
                TpaeMaxRestConstants.QBE_EQUALS + location, hmUrlParameters);
    }

    public Uni<JsonObject> queryLocation(String userName, String userPwd, String siteId, String location,
                                         Map<String, String> hmUrlParameters) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("#queryLocation(...)...");
        }
        return locationService.getByLocation(userName, userPwd, siteId, location, hmUrlParameters);
    }
}
