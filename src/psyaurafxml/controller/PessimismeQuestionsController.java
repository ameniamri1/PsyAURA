/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package psyaurafxml.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.List;
import psyaurafxml.Question;

/**
 * FXML Controller class
 *
 * @author amria
 */
public class PessimismeQuestionsController   {

  

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

    private ToggleGroup optionsGroup; // Création du groupe pour les RadioButtons

    public PessimismeQuestionsController() {
        questions = new ArrayList<>();
        questions.add(new Question("Lorsque vous rencontrez un problème, vous êtes confiant(e) de pouvoir le résoudre.",
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous croyez que les choses finiront par s'améliorer, même après un échec.",
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous trouvez des opportunités même dans les situations difficiles.",
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous avez tendance à voir le verre à moitié plein.",
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous avez une attitude positive même lorsqu'il y a des obstacles.",
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous croyez qu'il y a toujours une lumière au bout du tunnel.",
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
    }

    @FXML
    public void initialize() {
        optionsGroup = new ToggleGroup(); // Initialisation du ToggleGroup
        option1.setToggleGroup(optionsGroup); // Assignation au groupe
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

        // Calculer les points pour la question actuelle
        RadioButton selectedOption = (RadioButton) optionsGroup.getSelectedToggle();
        int selectedIndex = List.of(option1, option2, option3).indexOf(selectedOption);

        Question question = questions.get(currentQuestionIndex);
        score += question.getPoints().get(selectedIndex);

        // Mettre à jour le score
        scoreLabel.setText("Score : " + score);

        // Passer à la question suivante
        currentQuestionIndex++;
        optionsGroup.selectToggle(null); // Décocher les options
        afficherQuestion();
    }}




    
    
