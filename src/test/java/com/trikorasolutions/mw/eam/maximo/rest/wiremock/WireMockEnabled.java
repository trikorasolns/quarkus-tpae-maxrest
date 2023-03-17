package com.trikorasolutions.mw.eam.maximo.rest.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.trikorasolutions.mw.eam.maximo.rest.HelperFunctions;
import com.trikorasolutions.mw.eam.maximo.rest.TpaeMaxRestConstants;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.notContaining;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

public class WireMockEnabled implements QuarkusTestResourceLifecycleManager {

    /**
     * <p> Logger </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(WireMockEnabled.class);

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        LOG.warn("WireMockExtensions#start()...");
        wireMockServer = new WireMockServer();
        wireMockServer.start();


        wireMockServer.stubFor(get(urlEqualTo(
                String.format("/mbo/invbalances/?%s=%s&%s=%s&%s=%s&%s=%s", TpaeMaxRestConstants.REST_PARAM_USER, "empty",
                        TpaeMaxRestConstants.REST_PARAM_PASSWORD, "empty", "itemset", "empty", "siteid", "empty")))
                .atPriority(20)
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .withHeader("Accept", MediaType.APPLICATION_JSON)
                        .withHeader("Accept-Encoding", "UTF-8")
                        .withStatus(Response.Status.OK.getStatusCode())
                        .withBody(HelperFunctions.getStreamContents(
                                HelperFunctions.getResourceAsStream("samples/mbo.invbalances.empty.json")))
                ));
        wireMockServer.stubFor(get(urlMatching("/mbo/invbalances/.*")).atPriority(99)
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .withHeader("Accept", MediaType.APPLICATION_JSON)
                        .withHeader("Accept-Encoding", "UTF-8")
                        .withStatus(Response.Status.OK.getStatusCode())
                        .withBody(HelperFunctions.getStreamContents(
                                HelperFunctions.getResourceAsStream("samples/mbo.invbalances.json")))
                ));

        wireMockServer.stubFor(get(urlEqualTo(
                String.format("/mbo/item/?%s=%s&%s=%s&%s=%s&%s=%s", TpaeMaxRestConstants.REST_PARAM_USER, "empty",
                        TpaeMaxRestConstants.REST_PARAM_PASSWORD, "empty", "itemset", "SET1", "itemnum", "~eq~XXX")))
                .atPriority(20)
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .withHeader("Accept", MediaType.APPLICATION_JSON)
                        .withHeader("Accept-Encoding", "UTF-8")
                        .withStatus(Response.Status.OK.getStatusCode())
                        .withBody(HelperFunctions.getStreamContents(
                                HelperFunctions.getResourceAsStream("samples/mbo.item.empty.json")))
                ));
        wireMockServer.stubFor(get(urlMatching("/mbo/item/.*")).atPriority(99)
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .withHeader("Accept", MediaType.APPLICATION_JSON)
                        .withHeader("Accept-Encoding", "UTF-8")
                        .withStatus(Response.Status.OK.getStatusCode())
                        .withBody(HelperFunctions.getStreamContents(
                                HelperFunctions.getResourceAsStream("samples/mbo.item.json")))
                ));

//        wireMockServer.stubFor(get(urlMatching(".*"))).atPriority(0)
//                .willReturn(aResponse()
//                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
//                        .withStatus(Response.Status.UNAUTHORIZED.getStatusCode()))
//                .withBody("Error 401: BMXAA0021E - User name and password combination are not valid. Try again. "
//                ));

//        wireMockServer.stubFor(get(urlEqualTo("/mbo/invbalances"))
//                .willReturn(aResponse()
//                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
//                        .withHeader("Accept", MediaType.APPLICATION_JSON)
//                        .withHeader("Accept-Encoding", "UTF-8")
//                        .withStatus(Response.Status.OK.getStatusCode())
//                        .withBody(HelperFunctions.getStreamContents(
//                                HelperFunctions.getResourceAsStream("samples/mbo.invbalances.json"))
//                        )));
//        wireMockServer.stubFor(
//                get(urlMatching(".*")).atPriority(10).willReturn(aResponse()
//                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
//                        .withHeader("Accept", MediaType.APPLICATION_JSON)
//                        .withHeader("Accept-Encoding", "UTF-8")
//                        .withStatus(Response.Status.OK.getStatusCode())
//                        .proxiedFrom("https://stage.code.quarkus.io/api")));

        LOG.warn("quarkus.rest-client.tpae-api.url: {}", wireMockServer.baseUrl());
//        final Map retMap = Collections.singletonMap("quarkus.rest-client.tpae-api.url",
//                wireMockServer.baseUrl());
        final Map retMap = Map.of("quarkus.rest-client.tpae-api.url",
                wireMockServer.baseUrl(),
                "wiremock.port", "" + wireMockServer.port());
        LOG.warn("retMap: {}", retMap);
        return retMap;
//
    }

    @Override
    public void stop() {
        LOG.warn("WireMockExtensions#stop()...");
        if (null != wireMockServer) {
            wireMockServer.stop();
            wireMockServer = null;
        }
    }

    @Override
    public void inject(TestInjector testInjector) {
        testInjector.injectIntoFields(wireMockServer,
                new TestInjector.AnnotatedAndMatchesType(InjectWireMock.class, WireMockServer.class));
    }
}
