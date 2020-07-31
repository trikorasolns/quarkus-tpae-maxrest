package com.trikorasolutions.mw.eam.maximo.rest.test.work;

import com.trikorasolutions.mw.eam.maximo.rest.impl.workorder.MaximoRestWorkorderServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;

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
    woJson = woRestImpl.changeStatus(tpaeTestUsername, tpaeTestPassword, String.valueOf(workorderId), "APPR",
        new Date(System.currentTimeMillis()), "Quarkus Test").await().indefinitely();
    printWo(woJson);
    LOGGER.warn("fullWoTest().");
  }

  private void printWoMain(JsonObject thisResult) {
    LOGGER.warn("#printWo(JsonObject)", thisResult);
    LOGGER.warn("woTest: {}", thisResult);
  }

  private void printWo(JsonObject objJson) {
    LOGGER.warn("jsonObj: {}", objJson);
  }
}
