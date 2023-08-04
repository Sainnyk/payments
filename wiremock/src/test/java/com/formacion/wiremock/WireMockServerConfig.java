package com.formacion.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class WireMockServerConfig {
    private static final int PORT = 8089;

    private WireMockServer wireMockServer;

    public void startWireMock() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(PORT));
        wireMockServer.start();
    }

    public void stopWireMockServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    public static void main(String[] args) {
        WireMockServerConfig wireMockServerConfig = new WireMockServerConfig();
        wireMockServerConfig.startWireMock();
    }

}
