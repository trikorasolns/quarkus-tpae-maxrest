package com.trikorasolutions.mw.eam.maximo.rest.test.work;

import com.trikorasolutions.mw.eam.maximo.rest.impl.workorder.MaximoRestWorkorderServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

@QuarkusTest
public class WoTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(WoTest.class);

  @Inject
  MaximoRestWorkorderServiceImpl woRestImpl;

  @Test
  public void fullWoTest() {
    final String tpaeTestUsername = ConfigProvider.getConfig().getValue("tpae.username", String.class);
    final String tpaeTestPassword = ConfigProvider.getConfig().getValue("tpae.password", String.class);
    final String tpaeTestSite = ConfigProvider.getConfig().getValue("tpae.siteid", String.class);
    JsonObject woJson = woRestImpl.add(tpaeTestUsername, tpaeTestPassword, new HashMap<String, String>() {{
      put("SITEID", tpaeTestSite);
      put("DESCRIPTION", "quarkus test " + tpaeTestSite);
    }}).await().indefinitely();
    printWo(woJson);
    JsonObject woAttrs = woJson.getJsonObject("WORKORDER").getJsonObject("Attributes");
    printWo(woAttrs);
    String woNum = woAttrs.getJsonObject("WONUM").getString("content");
    Long workorderId = woAttrs.getJsonObject("WORKORDERID").getLong("content");
    LOGGER.warn("wonum, workorderid: {}, {}", woNum, workorderId);
    woJson = woRestImpl.query(tpaeTestUsername, tpaeTestPassword, new HashMap<String, String>() {{
      put("_INCLUDECOLS", "WORKORDERID,WONUM,SITEID,CHANGEDATE");
      put("SITEID", tpaeTestSite);
      put("WONUM", woNum);
    }}).await().indefinitely();
    printWo(woJson);
    LOGGER.warn(" # JPNUM");
    Optional<String> jpNum = ConfigProvider.getConfig().getOptionalValue("tpae.test.jpnum", String.class);
    if (jpNum.isPresent()) {
      LOGGER.warn("set jpnum: {}", jpNum.get());
      woJson = woRestImpl.modify(tpaeTestUsername, tpaeTestPassword, String.valueOf(workorderId),
          new HashMap<String, String>() {{
            put("JPNUM", jpNum.get());
          }}).await().indefinitely();
    }
    printWo(woJson);
    LOGGER.info(" # STATUS: APPR");
    try {
      woJson = woRestImpl.changeStatus(tpaeTestUsername, tpaeTestPassword, String.valueOf(workorderId), "APPR", null,
          "Quarkus Test").await().indefinitely();
    } catch (Exception e) {
      LOGGER.error(" # ERROR: APPR", e);
    }
    printWoChangeStatus(woJson);
    LOGGER.info(" # STATUS: INPRG");
    try {
      woJson = woRestImpl.changeStatus(tpaeTestUsername, tpaeTestPassword, String.valueOf(workorderId), "INPRG", null,
          "Quarkus Test").await().indefinitely();
    } catch (Exception e) {
      LOGGER.error(" # ERROR: INPRG", e);
    }
    printWoChangeStatus(woJson);
    LOGGER.warn(" # MATUSETRANS");
    Optional<String> itemNum = ConfigProvider.getConfig().getOptionalValue("tpae.test.itemnum", String.class);
    Optional<String> storeRoom = ConfigProvider.getConfig().getOptionalValue("tpae.test.storeroom", String.class);
    Optional<String> itemSet = ConfigProvider.getConfig().getOptionalValue("tpae.test.itemset", String.class);
    if (itemNum.isPresent() && storeRoom.isPresent() && itemSet.isPresent()) {
      LOGGER.warn("set itemNum: {}", itemNum.get());
      woJson = woRestImpl.reportMaterials(tpaeTestUsername, tpaeTestPassword, String.valueOf(workorderId),
          itemNum.get(), null, "ISSUE", new BigDecimal(-1), null, woNum, storeRoom.get(),
          (ConfigProvider.getConfig().getOptionalValue("tpae.test.bin",
              String.class).isPresent() ? ConfigProvider.getConfig().getOptionalValue("tpae.test.bin",
              String.class).get() : null), null, itemSet.get(), null, null).await().indefinitely();
      woJson = woRestImpl.reportMaterials(tpaeTestUsername, tpaeTestPassword, String.valueOf(workorderId),
          itemNum.get(), null, "RETURN", new BigDecimal(1), null, woNum, storeRoom.get(),
          (ConfigProvider.getConfig().getOptionalValue("tpae.test.bin",
              String.class).isPresent() ? ConfigProvider.getConfig().getOptionalValue("tpae.test.bin",
              String.class).get() : null), null, itemSet.get(), null, null).await().indefinitely();
    }
    LOGGER.info(" MATUSETRANS: {}",
        woJson.getJsonObject("SyncTKR_MW_WOResponse").getJsonObject("TKR_MW_WOSet").getJsonObject(
            "WORKORDER").getJsonObject("RelatedMbos").getJsonArray("MATUSETRANS").toString());
    LOGGER.warn(" # WORKLOG");
    woJson = woRestImpl.addWorkLog(tpaeTestUsername, tpaeTestPassword, String.valueOf(workorderId), "WORK", null,
        "work log title",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.").await().indefinitely();
    LOGGER.info(" WORKLOG: {}",
        woJson.getJsonObject("SyncTKR_MW_WOResponse").getJsonObject("TKR_MW_WOSet").getJsonObject(
            "WORKORDER").getJsonObject("RelatedMbos").getJsonArray("WORKLOG").toString());
    LOGGER.warn(" # STATUS: COMP");
    try {
      woJson = woRestImpl.changeStatus(tpaeTestUsername, tpaeTestPassword, String.valueOf(workorderId), "COMP", null,
          "Quarkus Test").await().indefinitely();
    } catch (Exception e) {
      LOGGER.error(" # ERROR: COMP", e);
    }
    printWoChangeStatus(woJson);
    LOGGER.warn(" # STATUS: CLOSE");
    try {
      woJson = woRestImpl.changeStatus(tpaeTestUsername, tpaeTestPassword, String.valueOf(workorderId), "CLOSE", null,
          "Quarkus Test").await().indefinitely();
      printWoChangeStatus(woJson);
    } catch (Exception e) {
      LOGGER.error(" # ERROR: CLOSE", e);
    }
    Assertions.assertEquals("CLOSE",
        woJson.getJsonObject("WORKORDER").getJsonObject("Attributes").getJsonObject("STATUS").getString("content"));
    LOGGER.warn("fullWoTest().");
  }

  private void printWoChangeStatus(JsonObject woJson) {
    JsonObject woAttrs = woJson.getJsonObject("WORKORDER").getJsonObject("Attributes");
    LOGGER.info("WORKORDER, STATUS, STATUSDATE: {}, {}, {}", woAttrs.getJsonObject("WONUM").getString("content"),
        woAttrs.getJsonObject("STATUS").getString("content"), woAttrs.getJsonObject("STATUSDATE").getString("content"));
  }

  private void printWo(JsonObject objJson) {
    LOGGER.warn("jsonObj: {}", objJson);
  }
}
