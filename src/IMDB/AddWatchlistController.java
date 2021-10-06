package IMDB;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddWatchlistController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void addToWatchlist(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/AddWatchlist.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("WatchList");
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
    ListView<String> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome_Label.setText("Hello " + Controller.loggedInUser.getUsername() + "!");

        ArrayList<String> movies = new ArrayList<>();
        for (String key : Main.movieList.keySet()){
            if(!Controller.loggedInUser.getWatchlist().contains(key)){
                movies.add(Main.movieList.get(key).getTitle());
            }
        }
        list.getItems().addAll(movies);

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Adding New Movie To Watchlist");
                    alert.setContentText("Do You Want To Add This Movie To Your Watchlist?");

                    if(alert.showAndWait().get() == ButtonType.OK){
                        String value = list.getSelectionModel().getSelectedItem();
                        for(String key : Main.movieList.keySet()){
                            if(Main.movieList.get(key).getTitle().equals(value)){
                                Controller.loggedInUser.add_watchlist(key);

                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setTitle("Alert");
                                a.setHeaderText("Movie Successfully Added To Watchlist");
                                a.setContentText("Movie: " + Main.movieList.get(key).getTitle());
                                a.showAndWait();

                                list.getItems().remove(Main.movieList.get(key).getTitle());
                            }
                        }
                    }
                }
            }
        });

        userMenu.getItems().add("Log Out");

        userMenu.setOnAction(Controller::after_logout);
    }
}
