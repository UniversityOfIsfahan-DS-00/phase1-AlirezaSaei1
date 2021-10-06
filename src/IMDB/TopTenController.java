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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TopTenController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label welcome_Label;
    @FXML
    ChoiceBox<String> userMenu;

    public void topTenList(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/TopTen.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Top 10");
            stage.show();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void movie_list(ActionEvent e){
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

    @FXML Label n1; @FXML Label n2; @FXML Label n3; @FXML Label n4;
    @FXML Label n5; @FXML Label n6; @FXML Label n7; @FXML Label n8;
    @FXML Label n9; @FXML Label n10;

    @FXML Label r1; @FXML Label r2; @FXML Label r3; @FXML Label r4;
    @FXML Label r5; @FXML Label r6; @FXML Label r7; @FXML Label r8;
    @FXML Label r9; @FXML Label r10;

    @FXML Label v1; @FXML Label v2; @FXML Label v3; @FXML Label v4;
    @FXML Label v5; @FXML Label v6; @FXML Label v7; @FXML Label v8;
    @FXML Label v9; @FXML Label v10;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome_Label.setText("Hello " + Controller.loggedInUser.getUsername() + "!");

        Movie[] movies = Main.movieList.values().toArray(new Movie[0]);
        List<Movie> list = new ArrayList<>(Arrays.asList(movies));
        list.sort(Movie::compareTo);

        for(int i=0; i<10; i++){
            if(i == 0){
                n1.setText(list.get(i).getTitle()); r1.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v1.setText(String.valueOf(list.get(i).getVotes()));
            }else if(i == 1){
                n2.setText(list.get(i).getTitle()); r2.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v2.setText(String.valueOf(list.get(i).getVotes()));
            }else if(i == 2){
                n3.setText(list.get(i).getTitle()); r3.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v3.setText(String.valueOf(list.get(i).getVotes()));
            }else if(i == 3){
                n4.setText(list.get(i).getTitle()); r4.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v4.setText(String.valueOf(list.get(i).getVotes()));
            }else if(i == 4){
                n5.setText(list.get(i).getTitle()); r5.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v5.setText(String.valueOf(list.get(i).getVotes()));
            }else if(i == 5){
                n6.setText(list.get(i).getTitle()); r6.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v6.setText(String.valueOf(list.get(i).getVotes()));
            }else if(i == 6){
                n7.setText(list.get(i).getTitle()); r7.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v7.setText(String.valueOf(list.get(i).getVotes()));
            }else if(i == 7){
                n8.setText(list.get(i).getTitle()); r8.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v8.setText(String.valueOf(list.get(i).getVotes()));
            }else if(i == 8){
                n9.setText(list.get(i).getTitle()); r9.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v9.setText(String.valueOf(list.get(i).getVotes()));
            }else{
                n10.setText(list.get(i).getTitle()); r10.setText(String.valueOf(Math.round(list.get(i).getAvgRating() * 10) / 10f));
                v10.setText(String.valueOf(list.get(i).getVotes()));
            }
        }

        userMenu.getItems().add("Log Out");

        userMenu.setOnAction(Controller::after_logout);
    }
}
