package IMDB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label welcome_Label;
    @FXML
    ChoiceBox<String> userMenu;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome_Label.setText("Hello " + Controller.loggedInUser.getUsername() + " !");

        userMenu.getItems().add("Log Out");

        userMenu.setOnAction(Controller::after_logout);
    }
}
