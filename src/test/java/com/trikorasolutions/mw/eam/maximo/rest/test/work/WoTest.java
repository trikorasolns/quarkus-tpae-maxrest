package com.trikorasolutions.mw.eam.maximo.rest.test.work;

import com.trikorasolutions.mw.eam.maximo.rest.impl.workorder.MaximoRestWorkorderServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.HashMap;

@QuarkusTest
public class WoTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(WoTest.class);

  @Inject
  MaximoRestWorkorderServiceImpl woRestImpl;

  @Test
  public void add() {
    final String tpaeTestUsername = ConfigProvider.getConfig().getValue("tpae.username", String.class);
    final String tpaeTestPassword = ConfigProvider.getConfig().getValue("tpae.password", String.class);
    final String tpaeTestSite = ConfigProvider.getConfig().getValue("tpae.siteid", String.class);
    JsonObject wo = woRestImpl.add(tpaeTestUsername, tpaeTestPassword, new HashMap<String, String>() {{
      put("SITEID", tpaeTestSite);
      put("DESCRIPTION", "test " + tpaeTestSite);
    }}).await().indefinitely();
    LOGGER.warn("item: {}", wo);

  }

  @Test
  public void query() {
    final String tpaeTestUsername = ConfigProvider.getConfig().getValue("tpae.username", String.class);
    final String tpaeTestPassword = ConfigProvider.getConfig().getValue("tpae.password", String.class);
    final String tpaeTestSite = ConfigProvider.getConfig().getValue("tpae.siteid", String.class);
    woRestImpl.querySync(tpaeTestUsername, tpaeTestPassword, new HashMap<String, String>() {{
      put("_INCLUDECOLS", "WORKORDERID,WONUM,SITEID");
      put("SITEID", tpaeTestSite);
      put("WONUM", "1000");
    }}).stream().peek(peeked -> {
          {
        LOGGER.warn("item: {}", peeked);
          }
    }).forEach(result -> {
      {
        LOGGER.warn("result: {}", result);
      }
    });
    LOGGER.warn("...#query.");
  }

  @Test
  public void queryAsync() {
    final String tpaeTestUsername = ConfigProvider.getConfig().getValue("tpae.username", String.class);
    final String tpaeTestPassword = ConfigProvider.getConfig().getValue("tpae.password", String.class);
    final String tpaeTestSite = ConfigProvider.getConfig().getValue("tpae.siteid", String.class);
    JsonObject tpaeWo = woRestImpl.query(tpaeTestUsername, tpaeTestPassword, new HashMap<String, String>() {{
      put("_INCLUDECOLS", "WORKORDERID,WONUM,SITEID,CHANGEDATE");
      put("SITEID", tpaeTestSite);
      put("WONUM", "1000");
    }}).await().indefinitely();
    LOGGER.warn("queryAsync result: {}", tpaeWo);
    tpaeWo.stream().peek(peeked -> {
      {
        LOGGER.warn("item: {}", peeked);
      }
    }).forEach(result -> {
      {
        LOGGER.warn("result: {}", result);
      }
    });
    LOGGER.warn("...#queryAsync.");
  }

  private void printWo(JsonObject thisResult) {
    LOGGER.warn("#printWo(JsonObject)", thisResult);
    LOGGER.warn("{}", thisResult);
  }
}
