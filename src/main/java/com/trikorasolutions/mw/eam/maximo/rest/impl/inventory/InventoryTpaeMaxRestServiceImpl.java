/**
 *
 */

package com.trikorasolutions.mw.eam.maximo.rest.impl.inventory;

import com.trikorasolutions.mw.eam.maximo.rest.service.mbo.inventory.TpaeMboInvbalancesService;
import com.trikorasolutions.mw.eam.maximo.rest.service.mbo.inventory.TpaeMboItemService;
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
 * <p> Implementation of Inventory service. </p>
 * ${properties:maximoBaseFolder}/mbo/workorder?_keys=true&amp;SITEID~eq~${headers:SITEID}&amp;CHANGEDATE=~gteq~2015-01-
 * 01
 *
 * @author Trikora Solutions
 */
@ApplicationScoped
public class InventoryTpaeMaxRestServiceImpl {

    /**
     * <p> Logger </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(InventoryTpaeMaxRestServiceImpl.class);

    /**
     * <p> Output date format. </p>
     */
    protected final SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Inject
    @RestClient
    TpaeMboInvbalancesService invbalancesService;

    @Inject
    @RestClient
    TpaeMboItemService itemService;

    /**
     * @
     */
    public InventoryTpaeMaxRestServiceImpl() {
    }

    public Uni<JsonObject> getBalances(String userName, String userPwd, String itemsetId, String siteId,
                                       Map<String, String> hmUrlParameters) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("#getBalances(String,String,String,String,Map)...");
        }
        return invbalancesService.queryItemsetSite(userName, userPwd, itemsetId, siteId, hmUrlParameters);
    }

    /**
     * @param userName
     * @param userPwd
     * @param itemset
     * @param itemNum
     * @param hmUrlParameters
     * @return
     */
    public Uni<JsonObject> getItem(final String userName, final String userPwd, final String itemset, final String itemNum,
                                   Map<String, String> hmUrlParameters) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("#getByPKey(String,String,String)...");
            LOG.debug("itemset: {}", itemset);
            LOG.debug("itemNum: {}", itemNum);
            LOG.debug("hmUrlParameters: {}", hmUrlParameters);
        }
        return itemService.queryByItemNum(userName, userPwd, itemset, itemNum, hmUrlParameters);
    }

}
