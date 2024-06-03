package org.adk.shopping.order.rest.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
public class InventoryClientStub {
    public static void stupIventoryCall(String skuCode, Integer quantity) {
        stubFor(get(urlEqualTo(String.format("/api/inventories/%s/%s/available",skuCode, quantity)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }
}
