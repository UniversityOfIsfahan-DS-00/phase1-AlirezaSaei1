package IMDB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;

public class Controller {
    static User loggedInUser;
    private static Parent root1;
    private static Stage stage1;
    private static Scene scene1;

    public Label myLabel;
    public Label eLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent e) {
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/Login.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void signUp(ActionEvent e) {
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/SignUp.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void privacy(ActionEvent e) {
        try {
            Desktop.getDesktop().browse(new URI("https://www.imdb.com/privacy"));
        } catch (Exception exception) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("ERROR");
            a.setHeaderText("ERROR");
            a.setContentText("Something went wrong!");
            a.showAndWait();
        }
    }


    @FXML
    Button m1;

    public void mouse1_entered(MouseEvent e) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0.4);
        colorAdjust.setBrightness(0.1);
        colorAdjust.setSaturation(0.8);
        m1.setEffect(colorAdjust);
    }

    public void mouse1_exited(MouseEvent e) {
        m1.setEffect(null);
    }

    @FXML
    Button m2;

    public void mouse2_entered(MouseEvent e) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0.4);
        colorAdjust.setBrightness(0.1);
        colorAdjust.setSaturation(0.8);
        m2.setEffect(colorAdjust);
    }

    public void mouse2_exited(MouseEvent e) {
        m2.setEffect(null);
    }

    @FXML
    Button m3;

    public void mouse3_entered(MouseEvent e) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0.4);
        colorAdjust.setBrightness(0.1);
        colorAdjust.setSaturation(0.8);
        m3.setEffect(colorAdjust);
    }

    public void mouse3_exited(MouseEvent e) {
        m3.setEffect(null);
    }

    @FXML
    Button m4;

    public void mouse4_entered(MouseEvent e) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0.4);
        colorAdjust.setBrightness(0.1);
        colorAdjust.setSaturation(0.8);
        m4.setEffect(colorAdjust);
    }

    public void mouse4_exited(MouseEvent e) {
        m4.setEffect(null);
    }

    @FXML
    TextField name;
    @FXML
    TextField email;
    @FXML
    TextField password;


    public void create(ActionEvent e) {
        try {
            String input1 = name.getText();
            String input2 = email.getText();
            String input3 = password.getText();

            if(Main.userPass.containsKey(input2)){
                throw new RuntimeException("Email Already Used!");
            }else {
                Main.userPass.put(input2, input3);
                Main.userInfo.put(input2, new User(input1, input2, input3));

                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Welcome " + input1);
                a.setHeaderText("Sign Up Successful");
                a.setContentText("Account Created Successfully!");
                a.showAndWait();

                root = FXMLLoader.load(getClass().getResource("JFXs/Main.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Welcome!");
                stage.show();
            }
        } catch (Exception exception) {
            eLabel.setText(exception.getMessage());
        }
    }

    @FXML
    TextField l_email;
    @FXML
    TextField l_password;

    public void enter(ActionEvent e) {
        boolean flag = false;
        try {
            String input1 = l_email.getText();
            String input2 = l_password.getText();

            if(Main.userPass.containsKey(input1)){
                if(input2.equals(Main.userPass.get(input1))){
                    if(Main.userInfo.get(input1)!=null){
                        loggedInUser = Main.userInfo.get(input1);
                        flag = true;
                    }else{
                        throw new RuntimeException("Error! Please Try Again");
                    }
                }else{
                    throw new RuntimeException("Password Is Incorrect!");
                }
            }else{
                throw new RuntimeException("Email Does Not Exist!");
            }
        } catch (Exception exception) {
            myLabel.setText(exception.getMessage());
        }
        if (flag && loggedInUser!=null) {
            successful_login(e);
        }
    }

    public void successful_login(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/MainMenu.fxml"));
            root = loader.load();
            MenuController c1 = loader.getController();
            c1.MainMenu(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void back(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("JFXs/Main.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void after_logout(ActionEvent e){
        try {
            root1 = FXMLLoader.load(Controller.class.getResource("JFXs/Main.fxml"));
            stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene1 = new Scene(root1);
            stage1.setScene(scene1);
            stage1.setTitle("Welcome!");
            stage1.show();

            Controller.loggedInUser = null;

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
