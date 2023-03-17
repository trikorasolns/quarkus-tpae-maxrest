package com.trikorasolutions.mw.eam.maximo.rest.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.trikorasolutions.mw.eam.maximo.rest.impl.inventory.InventoryTpaeMaxRestServiceImpl;
import com.trikorasolutions.mw.eam.maximo.rest.wiremock.InjectWireMock;
import com.trikorasolutions.mw.eam.maximo.rest.wiremock.WireMockEnabled;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@QuarkusTestResource(WireMockEnabled.class)
@RunOnVertxContext
public class WireMockTest {

    @InjectWireMock
    WireMockServer wireMockServer;

    @Test
    public void checkWiremock() {
        assertTrue(wireMockServer.isRunning(), "Wiremock should be running");
    }
}
