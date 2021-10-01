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
    static HashMap<String, Movie> movieList = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        try {
            //Load Users
            File file1 =  new File("users.txt");
            try(BufferedReader br = new BufferedReader(new FileReader(file1))) {
                String[] data;
                for(String line; (line = br.readLine()) != null; ) {
                    data = line.split(",");
                    userPass.put(data[1], data[2]);
                    userInfo.put(data[1], new User(data[0], data[1], data[2],
                            new HashSet<>(Arrays.asList(data[3].split("-"))), new HashSet<>(Arrays.asList(data[4].split("-")))));
                }
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }

            //Load Movie Info
            File file2 = new File("title.akas.txt");
            boolean skipFirstLine = false;
            try(BufferedReader br = new BufferedReader(new FileReader(file2))){
                String[] data;
                for(String line; (line = br.readLine()) != null; ) {
                    boolean original = false;
                    if(!skipFirstLine){
                        skipFirstLine = true;
                        continue;
                    }
                    data = line.split("\t");

                    if(Integer.parseInt(data[7]) == 1){
                        original = true;
                    }

                    movieList.put(data[0], new Movie(data[2], data[0], Integer.parseInt(data[1]), data[3], data[4], data[5],
                            new ArrayList<>(Arrays.asList(data[6].split(" "))), original));

                }

            }catch (Exception exception){
                exception.printStackTrace();
            }

            //Load Movie Ratings
            skipFirstLine = false;
            File file3 = new File("title.ratings.txt");
            try(BufferedReader br = new BufferedReader(new FileReader(file3))){
                String[] data;
                for(String line; (line = br.readLine()) != null; ) {
                    if(!skipFirstLine){
                        skipFirstLine = true;
                        continue;
                    }
                    data = line.split("\t");
                    movieList.get(data[0]).setAvgRating(Double.parseDouble(data[1]));
                    movieList.get(data[0]).setVotes(Integer.parseInt(data[2]));

                }

            }catch (Exception exception){
                exception.printStackTrace();
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
