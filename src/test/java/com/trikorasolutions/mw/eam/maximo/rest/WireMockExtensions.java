package com.trikorasolutions.mw.eam.maximo.rest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.trikorasolutions.mw.eam.maximo.rest.impl.inventory.InventoryTpaeMaxRestServiceImpl;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

public class WireMockExtensions implements QuarkusTestResourceLifecycleManager {

    /**
     * <p> Logger </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(WireMockExtensions.class);
    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        LOG.warn("WireMockExtensions#start()...");
        wireMockServer = new WireMockServer();
        wireMockServer.start();

        wireMockServer.stubFor(get(urlMatching("/mbo/invbalances/.*"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(HelperFunctions.getStreamContents(
                                HelperFunctions.getResourceAsStream("samples/mbo.invbalances.json"))
                        )));

        wireMockServer.stubFor(get(urlEqualTo("/mbo/invbalances"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(HelperFunctions.getStreamContents(
                                HelperFunctions.getResourceAsStream("samples/mbo.invbalances.json"))
                        )));
        wireMockServer.stubFor(
                get(urlMatching(".*")).atPriority(10).willReturn(aResponse().proxiedFrom("https://stage.code.quarkus.io/api")));

        LOG.warn("quarkus.rest-client.tpae-api.url: {}", wireMockServer.baseUrl());
        return Collections.singletonMap("quarkus.rest-client.tpae-api.url",
                wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}
