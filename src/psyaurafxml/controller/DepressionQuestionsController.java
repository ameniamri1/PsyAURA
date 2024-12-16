/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package psyaurafxml.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import psyaurafxml.Question;

/**
 * FXML Controller class
 *
 * @author amria
 */
public class DepressionQuestionsController  {

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @FXML
    private Label questionLabel;

    @FXML
    private RadioButton option1;

    @FXML
    private RadioButton option2;

    @FXML
    private RadioButton option3;

    @FXML
    private Label scoreLabel;
    @FXML

    private Button nextButton;
    @FXML
    private Button retournerButton;
    
    @FXML

    private ToggleGroup optionsGroup;

    public DepressionQuestionsController (){
        // Initialisation des questions
        questions = new ArrayList<>();
        questions.add(new Question("Vous vous sentez souvent fatigué(e), même après une bonne nuit de sommeil.",
                List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez perdu de l'intérêt pour les activités que vous aimiez auparavant.",
                List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez des difficultés à vous lever le matin.",
                List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous vous sentez souvent inutile ou coupable.",
                List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez du mal à prendre des décisions, même les plus simples.",
                List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous vous sentez souvent triste sans raison apparente.",
                List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
    }

    @FXML
    public void initialize() {
        optionsGroup = new ToggleGroup();
        option1.setToggleGroup(optionsGroup);
        option2.setToggleGroup(optionsGroup);
        option3.setToggleGroup(optionsGroup);

        afficherQuestion();
    }

    public void afficherQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            questionLabel.setText(question.getTexte());
            option1.setText(question.getOptions().get(0));
            option2.setText(question.getOptions().get(1));
            option3.setText(question.getOptions().get(2));
        } else {
            questionLabel.setText("Quiz terminé !");
            option1.setDisable(true);
            option2.setDisable(true);
            option3.setDisable(true);
        }
    }

    @FXML
    public void handleNext() {
        if (optionsGroup.getSelectedToggle() == null) {
            scoreLabel.setText("Veuillez sélectionner une option !");
            return;
        }

        RadioButton selectedOption = (RadioButton) optionsGroup.getSelectedToggle();
        int selectedIndex = List.of(option1, option2, option3).indexOf(selectedOption);

        Question question = questions.get(currentQuestionIndex);
        score += question.getPoints().get(selectedIndex);

        scoreLabel.setText("Score : " + score);

        currentQuestionIndex++;
        optionsGroup.selectToggle(null);
        afficherQuestion();
    }
    @FXML
public void handleFinishTest() {
    // Charger la vue de description du test
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/psyaurafxml/DescriptionView.fxml")); // Remplacez par le chemin de votre fichier FXML
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) nextButton.getScene().getWindow(); // Obtient la scène actuelle
        stage.setScene(scene); // Changer la scène
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
@FXML
    public void handleRetour() {
        try {
            // Remplacez par le chemin de votre scène précédente
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/psyaurafxml/view/FXMLDocumentController.java"))); 
            Stage stage = (Stage) retournerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
