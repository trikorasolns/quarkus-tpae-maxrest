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
  public void query() {
    final String tpaeTestUsername = ConfigProvider.getConfig().getValue("tpae.username", String.class);
    final String tpaeTestPassword = ConfigProvider.getConfig().getValue("tpae.password", String.class);
    final String tpaeTestSite = ConfigProvider.getConfig().getValue("tpae.siteid", String.class);
    Uni<Object> s = woRestImpl.query(tpaeTestUsername, tpaeTestPassword, new HashMap<String, String>() {{
      put("_INCLUDECOLS", "WORKORDERID,WONUM,SITEID");
      put("SITEID", tpaeTestSite);
    }}).collectItems().asList().onItem().apply(t -> {
      {
        t.stream().forEach(u -> {
          {
            printWo(u);
          }
        });
        return t;
      }
    });
    LOGGER.warn("asynchronus print {}", s);
    LOGGER.warn("...#query.");
  }

  private void printWo(JsonObject thisResult) {
    LOGGER.warn("#printWo(JsonObject)", thisResult);
    LOGGER.warn("{}", thisResult);
  }
}
