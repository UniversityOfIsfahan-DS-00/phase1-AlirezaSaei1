package IMDB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main extends Application {
    static HashMap<String, String> userPass = new HashMap<>();
    static HashMap<String, User> userInfo = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //Load Users
            File file =  new File("users.txt");
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                userPass = new HashMap<>();
                String[] data;
                for(String line; (line = br.readLine()) != null; ) {
                    data = line.split(",");
                    userPass.put(data[1], data[2]);
                    userInfo.put(data[1], new User(data[0], data[1], data[2],new HashSet<>(Arrays.asList(data[3].split("-"))), new HashSet<>(Arrays.asList(data[4].split("-")))));
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }


            Parent root = FXMLLoader.load(getClass().getResource("JFXs/Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome!");
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image("IMDB/Pictures/Movie_Logo.png"));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
