package com.jed.jeep.controller.support;

import org.springframework.boot.web.server.LocalServerPort;

public class BaseTest {
    @LocalServerPort
    private int serverPort;

    protected String getBaseUri(){
        return String.format("http://localhost:%d/jeep_db", serverPort);
    }


}
