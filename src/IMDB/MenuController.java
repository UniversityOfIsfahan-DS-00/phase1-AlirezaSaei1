package IMDB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class MenuController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label welcome_Label;
    @FXML
    ChoiceBox<String> userMenu;

    static String getRandomSetElement(Set<String> set) {
        return set.stream().skip(new Random().nextInt(set.size())).findFirst().orElse(null);
    }

    public void MainMenu(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/MainMenu.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.show();
        }catch (Exception exception){
            exception.printStackTrace();
        }

        if(Controller.loggedInUser.getWatchlist().size()!=0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Hello " + Controller.loggedInUser.getUsername());
            a.setHeaderText("watchlist Reminder");
            String rand = getRandomSetElement(Controller.loggedInUser.getWatchlist());
            a.setContentText("Let's Watch : " + Main.movieList.get(rand).getTitle());
            a.showAndWait();
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

    public void top_ten(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TopTen.fxml"));
            root = loader.load();
            TopTenController c1 = loader.getController();
            c1.topTenList(e);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void vote(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Rating.fxml"));
            root = loader.load();
            RatingController c1 = loader.getController();
            c1.votingPage(e);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void user_rated(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/UserRated.fxml"));
            root = loader.load();
            UserRatedController c1 = loader.getController();
            c1.my_rated(e);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void myWatchlist(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/MyWatchlist.fxml"));
            root = loader.load();
            MyWatchlistController c1 = loader.getController();
            c1.showWatchlist(e);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void addToWatchlist(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/AddWatchlist.fxml"));
            root = loader.load();
            AddWatchlistController c1 = loader.getController();
            c1.addToWatchlist(e);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome_Label.setText("Hello " + Controller.loggedInUser.getUsername() + "!");

        userMenu.getItems().add("Log Out");

        userMenu.setOnAction(Controller::after_logout);
    }
}
