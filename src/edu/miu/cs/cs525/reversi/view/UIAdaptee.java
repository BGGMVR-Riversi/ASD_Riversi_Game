package edu.miu.cs.cs525.reversi.view;

import edu.miu.cs.cs525.reversi.model.Piece;
import edu.miu.cs.cs525.reversi.model.PieceType;
import edu.miu.cs.cs525.reversi.model.Tile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UIAdaptee {

    private BorderPane commonBorderPaneUI;

    public void showBorderView() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UIAdaptee.class.getResource("BoardUI.fxml"));
        commonBorderPaneUI = loader.load();
        Scene scene = new Scene(commonBorderPaneUI);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        ReversiSingleton.stage.setScene(scene);
        ReversiSingleton.stage.show();
    }

    public void showNtkUIView() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UIAdaptee.class.getResource("NetworkUI.fxml"));
        commonBorderPaneUI = loader.load();
        Scene scene = new Scene(commonBorderPaneUI);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        ReversiSingleton.stage.setScene(scene);
        ReversiSingleton.stage.show();
    }

    public void showPlayersUIView() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UIAdaptee.class.getResource("PlayersUI.fxml"));
        commonBorderPaneUI = loader.load();
        Scene scene = new Scene(commonBorderPaneUI);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        ReversiSingleton.stage.setScene(scene);
        ReversiSingleton.stage.show();
    }
}
