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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserRatedController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void my_rated(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/UserRated.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("My Rated Movies");
            stage.show();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void movie_list(javafx.event.ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/MovieList.fxml"));
            root = loader.load();
            MovieListController c1 = loader.getController();
            c1.showList(e);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void back(javafx.event.ActionEvent e){
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
    ChoiceBox<String> userMenu;
    @FXML
    Label my_Label;
    @FXML
    ListView<String> ratedList;
    @FXML
    Label title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        my_Label.setText("Hello " + Controller.loggedInUser.getUsername() + "!");

        ArrayList<String> rated = new ArrayList<>();
        if(Controller.loggedInUser.getRated().size() == 0){
            title.setText("Nothing Is Here");
        }
        for(String str : Controller.loggedInUser.getRated().keySet()){
            rated.add(String.format("%10s \t----\t %20s \t----\t Rated: %2s", str,
                    Main.movieList.get(str).getTitle(), Controller.loggedInUser.getRated().get(str)));
        }

        Collator collator = Collator.getInstance(Locale.US);
        rated.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return collator.compare(o1, o2);
            }
        });
        ratedList.getItems().addAll(rated);

        userMenu.getItems().add("Log Out");

        userMenu.setOnAction(Controller::after_logout);
    }
}
