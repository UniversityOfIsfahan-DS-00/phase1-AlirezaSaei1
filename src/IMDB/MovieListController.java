package IMDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieListController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label w_Label;
    @FXML
    ChoiceBox<String> uMenu;

    public void showList(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/MovieList.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Movies");
            stage.show();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void back(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/MainMenu.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.show();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    TableView<Movie> tableView;

    @FXML
    TableColumn<Movie, String> idCol;
    @FXML
    TableColumn<Movie, String> tCol;
    @FXML
    TableColumn<Movie, String> regionCol;
    @FXML
    TableColumn<Movie, String> langCol;
    @FXML
    TableColumn<Movie, String> typeCol;
    @FXML
    TableColumn<Movie, ArrayList<String>> attCol;
    @FXML
    TableColumn<Movie, Boolean> originalCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setSortType(TableColumn.SortType.ASCENDING);

        w_Label.setText("Hello " + Controller.loggedInUser.getUsername() + "!");

        uMenu.getItems().add("Log Out");

        uMenu.setOnAction(Controller::after_logout);

        //Initializing TableView
        idCol.setCellValueFactory(new PropertyValueFactory<>("titleId"));
        tCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        langCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("types"));
        attCol.setCellValueFactory(new PropertyValueFactory<>("attributes"));
        originalCol.setCellValueFactory(new PropertyValueFactory<>("isOriginalTitle"));

        ObservableList<Movie> list = FXCollections.observableArrayList(Main.movieList.values());
        tableView.setItems(list);

        //Sorting By Movie ID
        tableView.getSortOrder().add(idCol);

    }
}
