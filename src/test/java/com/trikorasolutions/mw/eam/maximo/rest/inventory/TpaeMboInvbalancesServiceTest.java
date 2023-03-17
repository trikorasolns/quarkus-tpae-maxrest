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
public class TpaeMboInvbalancesServiceTest {

    @Inject
    InventoryTpaeMaxRestServiceImpl blInventory;

    @Test
    public void testBalancesGet(UniAsserter asserter) {
        asserter
                .assertThat(
                        () -> blInventory.getBalances("a", "a", "a", "a", Map.of()),
                        jsonObject -> {
                            Assertions.assertThat(jsonObject.getJsonObject("INVBALANCESMboSet")).isNotNull();
                            Assertions.assertThat(jsonObject.getJsonObject("INVBALANCESMboSet").getString("rsCount"))
                                    .isEqualTo("3");
                            Assertions.assertThat(jsonObject.getJsonObject("INVBALANCESMboSet").getString("rsStart"))
                                    .isEqualTo("0");
                            Assertions.assertThat(
                                            jsonObject.getJsonObject("INVBALANCESMboSet").getJsonArray("INVBALANCES").size())
                                    .isEqualTo(3);
                        });
    }

    @Test
    public void testBalancesEmpty(UniAsserter asserter) {
        asserter
                .assertThat(
                        () -> blInventory.getBalances("empty", "empty", "empty", "empty", Map.of()),
                        jsonObject -> {
                            Assertions.assertThat(jsonObject.getJsonObject("INVBALANCESMboSet")).isNotNull();
                            Assertions.assertThat(jsonObject.getJsonObject("INVBALANCESMboSet").getString("rsCount"))
                                    .isEqualTo("0");
                            Assertions.assertThat(jsonObject.getJsonObject("INVBALANCESMboSet").getString("rsStart"))
                                    .isEqualTo("0");
                            Assertions.assertThat(
                                            jsonObject.getJsonObject("INVBALANCESMboSet").getJsonArray("INVBALANCES").size())
                                    .isEqualTo(0);
                        });
    }
}
