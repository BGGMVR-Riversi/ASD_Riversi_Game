package edu.miu.cs.cs525.reversi.view;

import javafx.event.Event;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;

public class NetworkUI {

    private Stage primaryStage, networkStage;
    private BorderPane networkLayout;
    private String eventString;

    private void showNetworkUI(Event event) throws Exception{
        eventString = event.getEventType().toString();
    }
}
