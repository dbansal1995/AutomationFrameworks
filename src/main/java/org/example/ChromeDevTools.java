package org.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.Event;
import org.openqa.selenium.devtools.v141.emulation.Emulation;
import org.openqa.selenium.devtools.v141.network.Network;
import org.openqa.selenium.devtools.v141.network.model.Response;

import java.util.Optional;

public class ChromeDevTools {


    public static DevTools chromDevTools(){
        ChromeDriver chromeDriver=new ChromeDriver();
        DevTools tools=chromeDriver.getDevTools();
        tools.createSession();
        return tools;
    }

    public void setLocation(int lat, int longitude){

        DevTools tools=chromDevTools();

        tools.send(Emulation.setGeolocationOverride(Optional.of(lat), Optional.of(longitude),Optional.empty(), Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()));

    }

    public void CapturNetworkLogs(){

        DevTools tools=chromDevTools();

        tools.addListener(Network.responseReceived(), response->{

            Response res=response.getResponse();
            res.getStatus();

        });





    }
}
