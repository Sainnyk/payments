package com.formacion.ejercicio1;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class WireMockServerConfig {


    private WireMockServer wireMockServer;

    public void startWireMock() {

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
