package edu.miu.cs.cs525.reversi.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class  Launch extends Application {

    private ReversiSingleton reversiSingletonObj;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        reversiSingletonObj = ReversiSingleton.getInstance();
        reversiSingletonObj.showMainView();
    }
}
