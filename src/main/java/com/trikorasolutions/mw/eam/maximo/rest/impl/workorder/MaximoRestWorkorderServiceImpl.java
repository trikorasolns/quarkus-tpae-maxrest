/**
 *
 */
package com.trikorasolutions.mw.eam.maximo.rest.impl.workorder;

import com.trikorasolutions.mw.eam.maximo.rest.service.mbo.workorder.MaxMboWoService;
import com.trikorasolutions.mw.eam.maximo.rest.service.mbo.workorder.MaxOsWoService;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * <p> Implementation of Work Order service. </p> ${properties:maximoBaseFolder}/mbo/workorder?_keys=true&amp;SITEID~eq~${headers:SITEID}&amp;CHANGEDATE=~gteq~2015-01-
 * 01
 *
 * @author Trikora Solutions
 */
@ApplicationScoped
public class MaximoRestWorkorderServiceImpl {

  /**
   * <p> Logger </p>
   */
  private static final transient Logger COMMON_LOG = LoggerFactory.getLogger(MaximoRestWorkorderServiceImpl.class);

  /**
   * <p> Output date format. </p>
   */
  protected final SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

  @Inject
  @RestClient
  MaxMboWoService woMboService;

  @Inject
  @RestClient
  MaxOsWoService woOsService;

  /**
   * @
   */
  public MaximoRestWorkorderServiceImpl() {
  }

  public Uni<JsonObject> getByPKey(String userName, String userPwd, String psrecordId,
      Map<String, String> phmUrlParameters) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#getByPKey(String,String,String)...");
      COMMON_LOG.debug("psrecordId: " + psrecordId);
    }
    return woMboService.getById(userName, userPwd, psrecordId, phmUrlParameters);
  }

  public Uni<JsonObject> getByKeys(String userName, String userPwd, String psSiteId, String psRecurdNum,
      Map<String, String> phmUrlParameters) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#getByKeys(String,String,String)...");
      COMMON_LOG.debug("wonum: " + psRecurdNum);
      COMMON_LOG.debug("siteid: " + psSiteId);
    }
    return woMboService.getByNum(userName, userPwd, psRecurdNum, psSiteId, phmUrlParameters);
  }

  public JsonObject querySync(String userName, String userPwd, HashMap<String, String> phmUrlParameters) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#querySync(String,String,HashMap)...");
      COMMON_LOG.debug("  -> userName: {}", userName);
      COMMON_LOG.debug("  -> phmUrlParameters: {}", phmUrlParameters);
      COMMON_LOG.debug("  -> woMboService: {}", woMboService);
    }
    return woMboService.querySync(userName, userPwd, phmUrlParameters);
  }

  public Uni<JsonObject> query(String userName, String userPwd, HashMap<String, String> phmUrlParameters) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#query(String,String,HashMap)...");
      COMMON_LOG.debug("  -> userName: {}", userName);
      COMMON_LOG.debug("  -> phmUrlParameters: {}", phmUrlParameters);
      COMMON_LOG.debug("  -> woMboService: {}", woMboService);
    }
    return woMboService.query(userName, userPwd, phmUrlParameters);
  }

  public Uni<JsonObject> changeStatus(String userName, String userPwd, String pSrecordId, String pSnewStatus,
      Date pDstatusDate, String psmemo) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#changeStatus(...)...");
      COMMON_LOG.debug("  -> pSrecordId: " + pSrecordId);
      COMMON_LOG.debug("  -> pSnewStatus: " + pSnewStatus);
      COMMON_LOG.debug("  -> pDstatusDate: " + pDstatusDate);
      COMMON_LOG.debug("  -> psmemo: " + psmemo);
    }
    return woMboService.changeStatus(userName, userPwd,  "changeStatus", pSrecordId, "this", pSnewStatus,
        (pDstatusDate != null ? sdFormat.format(pDstatusDate) : null), psmemo);
  }

  public Uni<JsonObject> add(String userName, String userPwd, HashMap<String, String> pHmUrlParameters) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#add(String,String,HashMap)...");
      COMMON_LOG.debug("  -> pHmUrlParameters: " + pHmUrlParameters);
    }
    COMMON_LOG.warn(" woMboService: " + woMboService);
    Uni<JsonObject> retVal = woMboService.add(userName, userPwd, pHmUrlParameters);
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("  -> psaAuthToken: {}", retVal);
      COMMON_LOG.debug("#add(String,String,HashMap).");
    }
    return retVal;
  }

  public Uni<JsonObject> modify(String userName, String userPwd, String pSrecordId,
      Map<String, String> pHmUrlParameters) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#modify(String,String,String,HashMap)...");
      COMMON_LOG.debug("  -> pSrecordId: " + pSrecordId);
      COMMON_LOG.debug("  -> pHmUrlParameters: " + pHmUrlParameters);
    }
    return woMboService.modify(userName, userPwd, pSrecordId, pHmUrlParameters);
  }

  public Uni<JsonObject> getFull(String userName, String userPwd, String recordId,
      Map<String, String> hmUrlParameters) {
    return woMboService.getById(userName, userPwd, recordId, hmUrlParameters);
  }

  public Uni<JsonObject> addDocument(String userName, String userPwd, String pSrecordId, String pSdocType,
      String pSurlType, String psdescription, String psfileName, String... pSdocumentData) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#addDocument(...)...");
      COMMON_LOG.debug("  -> pSrecordId: " + pSrecordId);
      COMMON_LOG.debug("  -> pSdocType: " + pSdocType);
      COMMON_LOG.debug("  -> pSdescription: " + psdescription);
      COMMON_LOG.debug("  -> pSfileName: " + psfileName);
    }
    StringBuffer sbDocumentData = new StringBuffer();
    if (pSdocumentData != null && pSdocumentData.length > 0) {
      for (int i = 0; i < pSdocumentData.length; i++) {
        sbDocumentData.append(pSdocumentData[i]);
      }
    }
    return woOsService.addDocument(userName, userPwd, pSrecordId, "0", "0", "0", "1", pSurlType, pSdocType,
        psdescription, psfileName, psfileName, psfileName, psfileName, sbDocumentData.toString());
  }

  public Uni<JsonObject> reportMaterials(String userName, String userPwd, String pSworkorderId, String pSitemCode,
      String smaterialDesc, String pSissueType, BigDecimal pBdQuantity, Date pdActualDate, String psworkorderKey,
      String sStoreLocation, String sbinNumber, String psrotAssetNum, String psitemSetId, String psactualTaskId,
      Map<String, String> pPhmUrlParameters) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#reportMaterials(...)...");
      COMMON_LOG.debug("  -> pSworkorderId: " + pSworkorderId);
      COMMON_LOG.debug("  -> pSitemCode: " + pSitemCode);
      COMMON_LOG.debug("  -> psrotAssetNum: " + psrotAssetNum);
      COMMON_LOG.debug("  -> smaterialDesc: " + smaterialDesc);
      COMMON_LOG.debug("  -> pSissueType: " + pSissueType);
      COMMON_LOG.debug("  -> pBdQuantity: " + pBdQuantity);
      COMMON_LOG.debug("  -> pdActualDate: " + pdActualDate);
      COMMON_LOG.debug("  -> psworkorderKey: " + psworkorderKey);
      COMMON_LOG.debug("  -> sStoreLocation: " + sStoreLocation);
      COMMON_LOG.debug("  -> sbinNumber: " + sbinNumber);
      COMMON_LOG.debug("  -> pPhmUrlParameters: " + pPhmUrlParameters);
    }
    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
    return woOsService.addMaterials(userName, userPwd, pSworkorderId, pSitemCode, smaterialDesc, psrotAssetNum,
        formatter.format(pBdQuantity.doubleValue()), pSissueType, (pdActualDate!=null?sdFormat.format(pdActualDate):null), psworkorderKey,
        sStoreLocation, sbinNumber, psitemSetId, psactualTaskId);
  }

  public Uni<JsonObject> reportServices(String userName, String userPwd, String pSsite, String pSworkorderId,
      String pSissueType, BigDecimal pBdQuantity, String psworkorderKey, Map<String, String> pPhmUrlParameters) {
    // TODO Auto-generated method stub
    return null;
  }

  public Uni<JsonObject> reportTools(String userName, String userPwd, String pSworkorderId, String psItemCode,
      Date pdEnterDate, BigDecimal pBdQuantity, BigDecimal pBdtimeAmount, String psworkorderKey,
      final String pstrRotAssetNum, final String pstrRotAssetSiteId, Map<String, String> pPhmUrlParameters) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#reportTools(...)...");
      COMMON_LOG.debug("  -> pSworkorderId: " + pSworkorderId);
      COMMON_LOG.debug("  -> psItemCode: " + psItemCode);
      COMMON_LOG.debug("  -> pdEnterDate: " + pdEnterDate);
      COMMON_LOG.debug("  -> pBdQuantity: " + pBdQuantity);
      COMMON_LOG.debug("  -> pBdtimeAmount: " + pBdtimeAmount);
      COMMON_LOG.debug("  -> pPhmUrlParameters: " + pPhmUrlParameters);
    }
    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(new Locale("ES", "es"));
    return woOsService.addTools(userName, userPwd, pSworkorderId, psItemCode, pstrRotAssetNum, pstrRotAssetSiteId,
        formatter.format(pBdQuantity.doubleValue()), formatter.format(pBdtimeAmount.doubleValue()), psworkorderKey,
        sdFormat.format(pdEnterDate));
  }

  public Uni<JsonObject> addWorkLog(String userName, String userPwd, String workorderId, String logType, Date logDate,
      String description, String text) {
    if (COMMON_LOG.isDebugEnabled()) {
      COMMON_LOG.debug("#addWorkLog(...)...");
      COMMON_LOG.debug("  -> workorderId: " + workorderId);
      COMMON_LOG.debug("  -> pslogType: " + logType);
      COMMON_LOG.debug("  -> pdlogDate: " + logDate);
      COMMON_LOG.debug("  -> psdescription: " + description);
      COMMON_LOG.debug("  -> text: " + text);
    }
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    return woOsService.addWorklog(userName, userPwd, workorderId, logType, sdFormat.format(logDate), description, text);
  }


}
