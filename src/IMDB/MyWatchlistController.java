package IMDB;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyWatchlistController implements Initializable {

    String selectedId;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showWatchlist(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/MyWatchlist.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("My WatchList");
            stage.show();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void all_movies(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/MovieList.fxml"));
            root = loader.load();
            MovieListController c1 = loader.getController();
            c1.showList(e);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void watched(ActionEvent e){
        if(selected.getText().equals("No Movies Selected")){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Warning");
            a.setHeaderText("Something Went Wrong");
            a.setContentText("Please Check You've Selected A Valid Movie");
            a.showAndWait();
        }else{
            for(String key : Main.movieList.keySet()){
                if(Main.movieList.get(key).getTitle().equals(selected.getText())){
                    Controller.loggedInUser.delete_watchlist(key);
                    watchlist.getItems().remove(Main.movieList.get(key).getTitle());
                    selected.setText("No Movies Selected");

                    if(!Controller.loggedInUser.getRated().containsKey(key)){
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.setTitle("Watchlist Updated");
                        a.setHeaderText("Please Rate This Movie At The Rating Section");
                        a.setContentText("You Watched: " + Main.movieList.get(key).getTitle());
                        a.showAndWait();
                    }else{
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.setTitle("Watchlist Updated");
                        a.setHeaderText("You Can Add New Movies To Your Watchlist From Menu");
                        a.setContentText("You Watched: " + Main.movieList.get(key).getTitle());
                        a.showAndWait();
                    }
                    if(watchlist.getItems().size()==0){
                        back(e);
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.setTitle("Congratulations!");
                        a.setHeaderText("Good Job!");
                        a.setContentText("You Have Watched All Of Movies from Your Watchlist!");
                        a.showAndWait();
                    }
                }
            }
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
    Label welcome_Label;
    @FXML
    ChoiceBox<String> userMenu;
    @FXML
    Label w_label;
    @FXML
    Label selected;
    @FXML
    ListView<String> watchlist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome_Label.setText("Hello " + Controller.loggedInUser.getUsername() + "!");

        if(Controller.loggedInUser.getWatchlist().size()==0){
            w_label.setText("Nothing Is Here!");
        }else{
            ArrayList<String> added = new ArrayList<>();
            for(String str : Controller.loggedInUser.getWatchlist()){
                    added.add(Main.movieList.get(str).getTitle());
            }
            watchlist.getItems().addAll(added);
        }

        watchlist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selected.setText(watchlist.getSelectionModel().getSelectedItem());
                selectedId = watchlist.getSelectionModel().getSelectedItem();
            }
        });

        userMenu.getItems().add("Log Out");

        userMenu.setOnAction(Controller::after_logout);
    }
}
