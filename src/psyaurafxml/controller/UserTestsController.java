package psyaurafxml.controller;

import psyaurafxml.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserTestsController implements Initializable {

    @FXML
    private Button pessimisme;

    @FXML
    private Button optimisme;

    @FXML
    private Button depression;

    @FXML
    private Button confiance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pessimisme.setOnAction(event -> navigateToQuestions("pessimismeQuestions.fxml"));
        optimisme.setOnAction(event -> navigateToQuestions("optimismeQuestions.fxml"));
        depression.setOnAction(event -> navigateToQuestions("depressionQuestions.fxml"));
        confiance.setOnAction(event -> navigateToQuestions("confianceQuestions.fxml"));
    }

    public void navigateToQuestions(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/psyaurafxml/view/" + fxmlFileName));
            Scene questionScene = new Scene(loader.load());

            Stage stage = (Stage) pessimisme.getScene().getWindow();
            stage.setScene(questionScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
