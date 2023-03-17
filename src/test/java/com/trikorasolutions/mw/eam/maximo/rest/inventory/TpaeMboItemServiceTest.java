package com.trikorasolutions.mw.eam.maximo.rest.inventory;

import com.trikorasolutions.mw.eam.maximo.rest.impl.inventory.InventoryTpaeMaxRestServiceImpl;
import com.trikorasolutions.mw.eam.maximo.rest.wiremock.WireMockEnabled;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import java.util.Map;

@QuarkusTest
@QuarkusTestResource(WireMockEnabled.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunOnVertxContext
public class TpaeMboItemServiceTest {

    @Inject
    InventoryTpaeMaxRestServiceImpl blInventory;

    @Test
    public void testItemList(UniAsserter asserter) {
        asserter
                .assertThat(
                        () -> blInventory.getItem("wilson", "wilson", "SET1", "pump", Map.of()),
                        jsonObject -> {
                            Assertions.assertThat(jsonObject.getJsonObject("ITEMMboSet")).isNotNull();
                            Assertions.assertThat(jsonObject.getJsonObject("ITEMMboSet").getString("rsCount"))
                                    .isEqualTo("3");
                            Assertions.assertThat(jsonObject.getJsonObject("ITEMMboSet").getString("rsStart"))
                                    .isEqualTo("0");
                            Assertions.assertThat(
                                            jsonObject.getJsonObject("ITEMMboSet").getJsonArray("ITEM").size())
                                    .isEqualTo(3);
                        });
    }
    @Test
    public void testItemGetEmpty(UniAsserter asserter) {
        asserter
                .assertThat(
                        () -> blInventory.getItem("empty", "empty", "SET1", "~eq~XXX", Map.of()),
                        jsonObject -> {
                            Assertions.assertThat(jsonObject.getJsonObject("ITEMMboSet")).isNotNull();
                            Assertions.assertThat(jsonObject.getJsonObject("ITEMMboSet").getString("rsCount"))
                                    .isEqualTo("0");
                            Assertions.assertThat(jsonObject.getJsonObject("ITEMMboSet").getString("rsStart"))
                                    .isEqualTo("0");
                            Assertions.assertThat(
                                            jsonObject.getJsonObject("ITEMMboSet").getJsonArray("ITEM").size())
                                    .isEqualTo(0);
                        });
    }

}
