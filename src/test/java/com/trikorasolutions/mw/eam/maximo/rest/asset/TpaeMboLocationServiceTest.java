package com.trikorasolutions.mw.eam.maximo.rest.asset;

import com.trikorasolutions.mw.eam.maximo.rest.impl.asset.AssetTpaeMaxRestServiceImpl;
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
public class TpaeMboLocationServiceTest {

    @Inject
    AssetTpaeMaxRestServiceImpl blAsset;

//    @Test
    public void testLocationGet(UniAsserter asserter) {
        asserter
                .assertThat(
                        () -> blAsset.getLocation("a", "a", "a", "a", Map.of()),
                        jsonObject -> {
                            Assertions.assertThat(jsonObject.getJsonObject("LOCATIONSMboSet")).isNotNull();
                            Assertions.assertThat(jsonObject.getJsonObject("LOCATIONSMboSet").getString("rsCount"))
                                    .isEqualTo("3");
                            Assertions.assertThat(jsonObject.getJsonObject("LOCATIONSMboSet").getString("rsStart"))
                                    .isEqualTo("0");
                            Assertions.assertThat(
                                            jsonObject.getJsonObject("LOCATIONSMboSet").getJsonArray("LOCATIONS").size())
                                    .isEqualTo(3);
                        });
    }

//    @Test
    public void testLocationEmpty(UniAsserter asserter) {
        asserter
                .assertThat(
                        () -> blAsset.getLocation("empty", "empty", "empty", "empty", Map.of()),
                        jsonObject -> {
                            Assertions.assertThat(jsonObject.getJsonObject("LOCATIONSMboSet")).isNotNull();
                            Assertions.assertThat(jsonObject.getJsonObject("LOCATIONSMboSet").getString("rsCount"))
                                    .isEqualTo("0");
                            Assertions.assertThat(jsonObject.getJsonObject("LOCATIONSMboSet").getString("rsStart"))
                                    .isEqualTo("0");
                            Assertions.assertThat(
                                            jsonObject.getJsonObject("LOCATIONSMboSet").getJsonArray("LOCATIONS").size())
                                    .isEqualTo(0);
                        });
    }
}
