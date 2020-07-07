package edu.miu.cs.cs525.reversi.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReversiSingleton {

    private static ReversiSingleton reversiSingletonObj;
    public static Stage stage;
    private BorderPane mainLayout;

    private ReversiSingleton(){}

    public static ReversiSingleton getInstance(){
        if(reversiSingletonObj == null){
            synchronized (ReversiSingleton.class){
                if(reversiSingletonObj==null)  reversiSingletonObj = new ReversiSingleton();
                if(stage == null) stage = new Stage();
            }
        }
        return reversiSingletonObj;
    }

    public void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Launch.class.getResource("Launch.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        this.stage.setTitle("Othello/Reversi Game");
        this.stage.setResizable(false);
        this.stage.centerOnScreen();
        stage.show();
    }
}
