import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Launch extends Application {

    private Stage primaryStage;
    private BorderPane mainLayout;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception, FileNotFoundException {
        this.primaryStage = primaryStage;
        showMainView();
    }

    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
//        File file = new File("src/edu/miu/cs/cs525/reversi/view/");
        loader.setLocation(Launch.class.getResource("Launch.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
//        scene.getStylesheets().add(this.getClass().getResource("main.css").toExternalForm());
        primaryStage.setScene(scene);
        this.primaryStage.setTitle("Othello/Reversi Game");
        this.primaryStage.setResizable(false);
        this.primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
