package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Player;


public class RankingController{

    @FXML
    public TableView<Player> rankingTable;

    @FXML
    public TableColumn<Player, String> playerNameColumn;

    @FXML
    public TableColumn<Player, Integer> playerPointsColumn;

    private Game model;

    public RankingController(){ }

    @FXML
    public void initialize(){
        this.playerNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        this.playerPointsColumn.setCellValueFactory(cellData -> cellData.getValue().getPointsProperty().asObject());
    }

    public void setModel(Game model) {
        this.model = model;
    }

    public void setRankingTable(){
        ObservableList<Player> rank = FXCollections.observableArrayList(this.model.getRanking().getRank());
        this.rankingTable.setItems(rank);
    }
}
