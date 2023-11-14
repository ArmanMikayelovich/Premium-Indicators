package com.mikayelovich.premium_indicators.core.binance;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.function.Consumer;


public class BinanceWebSocketClient extends WebSocketClient {
    private static final Logger log = LoggerFactory.getLogger(BinanceWebSocketClient.class);

    private final Consumer<String> procedure;
    private int counter = 0;

    public BinanceWebSocketClient(URI serverUri, Consumer<String> procedure) {
        super(serverUri);
        this.procedure = procedure;
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {
        log.debug("Opened connection");
    }

    @Override
    public void onMessage(String message) {
        // Process message
        counter++;
        if (counter > 1000) {
            log.debug("Received: another thousand of actions");
            counter = 0;
        }
        procedure.accept(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.debug("Closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        log.error("An error occurred:" + ex.getMessage());
    }
}
