package IMDB;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

public class RatingController implements Initializable {

    String votedID;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void votingPage(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/Rating.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Movie Rating");
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

    public void submitVote(ActionEvent e){
        Movie movie = Main.movieList.get(votedID);
        if(movie != null){
            if(!Controller.loggedInUser.getRated().containsKey(votedID)) {
                double rated;
                if (stars.getText().equals("10.")) {
                    rated = 10D;
                } else {
                    rated = Double.parseDouble(stars.getText());
                }
                movie.voted(rated, Controller.loggedInUser);


                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Alert");
                a.setHeaderText(rated + " Stars");
                a.setContentText("You Successfully Rated " + movie.getTitle());
                a.showAndWait();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/MainMenu.fxml"));
                    root = loader.load();
                    MenuController c1 = loader.getController();
                    c1.MainMenu(e);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Warning");
                a.setHeaderText("Something Went Wrong");
                a.setContentText("You Have Already Voted: " + movie.getTitle());
                a.showAndWait();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Warning");
            a.setHeaderText("Something Went Wrong");
            a.setContentText("Please Check You've Selected A Valid Movie");
            a.showAndWait();
        }
    }

    @FXML
    Label welcome_Label;
    @FXML
    ChoiceBox<String> userMenu;
    @FXML
    Slider slider;
    @FXML
    Label stars;
    @FXML
    Label movieName;
    @FXML
    ListView<String> listView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome_Label.setText("Hello " + Controller.loggedInUser.getUsername() + "!");

        ArrayList<String> list = new ArrayList<>();
        for(Movie m : Main.movieList.values()){
            list.add(m.getTitleId() + "  ->  " + m.getTitle());
        }
        slider.setMin(0);
        slider.setMax(10);
        slider.setShowTickLabels(true);
        slider.setValue(5);
        slider.setMajorTickUnit(1);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                                Number number, Number t1) {
                String value = String.valueOf(t1.doubleValue());
                stars.textProperty().setValue(value.substring(0, 3));
            }
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String[] v = listView.getSelectionModel().getSelectedItem().split("->");
                movieName.setText(v[1]);
                votedID = v[0].replace(" ", "");
            }
        });

        Collator collator = Collator.getInstance(Locale.US);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return collator.compare(o1, o2);
            }
        });

        listView.getItems().addAll(list);

        userMenu.getItems().add("Log Out");

        userMenu.setOnAction(Controller::after_logout);
    }
}
