package IMDB;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.text.Collator;
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
            try(ObjectInputStream br = new ObjectInputStream(new FileInputStream(file1))) {
                try {
                    while (true) {
                        Object u = br.readObject();
                        userPass.put(((User) u).getEmail(), ((User) u).getPassword());
                        userInfo.put(((User) u).getEmail(), ((User) u));
                    }
                }catch (NullPointerException e){
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

            primaryStage.setOnCloseRequest(event -> {
                event.consume();
                exit(primaryStage);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Your Are About To Exit Program!");
        alert.setContentText("Are You Sure You Want To Exit?");

        if(alert.showAndWait().get() == ButtonType.OK){
            save();
            Platform.exit();
        }
    }

    public void save(){
       //Save User Information
        File file1 =  new File("users.txt");
        try(ObjectOutputStream br = new ObjectOutputStream(new FileOutputStream(file1))) {
            for(User u : userInfo.values()){
                br.writeObject(u);
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        //Update Movie Information
            Collator collator = Collator.getInstance(Locale.US);
            Movie[] movies = Main.movieList.values().toArray(new Movie[0]);
            List<Movie> list = new ArrayList<>(Arrays.asList(movies));
            list.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return collator.compare(o1.getTitleId(), o2.getTitleId());
                }
            });


        /*File file2 = new File("title.akas.txt");
        try(PrintWriter pw = new PrintWriter(file2)){
            pw.println("titleId\tordering\ttitle\tregion\tlanguage\ttypes\tattributes\tisOriginalTitle");
            for(Movie m : list){
                String str = String.join(" ", m.getAttributes());
                int flag;
                if(m.getIsOriginalTitle().equals("No")){
                    flag = 0;
                }else{
                    flag = 1;
                }
                pw.println(m.getTitleId() + "\t" + m.getOrdering()+ "\t" +
                        m.getTitle()+ "\t" + m.getRegion()+ "\t" + m.getLanguage()+ "\t" +
                        m.getTypes()+ "\t" + str + "\t" + flag);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }*/

        File file3 = new File("title.ratings.txt");
        try(PrintWriter pw = new PrintWriter(file3)){
            pw.println("tconst\taverageRating\tnumVotes");
            for(Movie m : list){
                String avgRate = String.valueOf(Math.round(m.getAvgRating() * 10) / 10f);
                pw.println(m.getTitleId() + "\t" + avgRate + "\t" + m.getVotes());
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
