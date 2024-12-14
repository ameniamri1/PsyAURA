/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package psyaurafxml.controller;

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
public class ConfianceQuestionsController  {




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

    private ToggleGroup optionsGroup;

    public ConfianceQuestionsController() {
        // Initialisation des questions
        questions = new ArrayList<>();
        questions.add(new Question("Vous êtes à l'aise de prendre la parole en public.", 
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous faites confiance à vos compétences pour réussir.", 
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous aimez relever des défis, même si vous ne savez pas si vous réussirez.", 
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Lorsque vous faites face à une situation inconnue, vous êtes calme et sûr(e) de vous.", 
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Les autres vous disent souvent que vous inspirez confiance.", 
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous êtes capable de prendre des décisions importantes sans hésiter.", 
                List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
    }

    @FXML
    private void initialize() {
        optionsGroup = new ToggleGroup();
        option1.setToggleGroup(optionsGroup);
        option2.setToggleGroup(optionsGroup);
        option3.setToggleGroup(optionsGroup);

        afficherQuestion();
    }

    private void afficherQuestion() {
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
    private void handleNext() {
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
}

    
    
