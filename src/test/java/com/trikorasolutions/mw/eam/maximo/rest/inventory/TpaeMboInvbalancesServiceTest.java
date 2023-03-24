package com.trikorasolutions.mw.eam.maximo.rest.inventory;

import com.trikorasolutions.mw.eam.maximo.rest.WireMockExtensions;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
public class TpaeMboInvbalancesServiceTest {

    @Test
    public void testExtensionsIdEndpoint() {
        given()
                .when().get("/mbo/invbalances/")
                .then()
                .statusCode(200)
                .body("$.size()", is(1),
                        "[0].id", is("io.quarkus:quarkus-rest-client"),
                        "[0].name", is("REST Client Classic"),
                        "[0].keywords.size()", greaterThan(1),
                        "[0].keywords", hasItem("rest-client"));
    }
}
