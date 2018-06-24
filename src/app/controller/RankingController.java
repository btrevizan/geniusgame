package app.controller;

import app.model.Ranking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import app.model.Game;
import app.model.Player;


public class RankingController{

    @FXML
    private TableView<Player> rankingTable;

    @FXML
    private TableColumn<Player, String> playerNameColumn;

    @FXML
    private TableColumn<Player, Integer> playerPointsColumn;

    @FXML
    private Button backButton;

    private Ranking ranking;

    @FXML
    public void initialize(){
        this.playerNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        this.playerPointsColumn.setCellValueFactory(cellData -> cellData.getValue().getPointsProperty().asObject());
    }

    public void setModel(Ranking model) {
        this.ranking = model;
    }

    public void setRankingTable(){
        ObservableList<Player> rank = FXCollections.observableArrayList(this.ranking.getRank());
        this.rankingTable.setItems(rank);
    }

    public Button getBackButton(){
        return this.backButton;
    }
}
