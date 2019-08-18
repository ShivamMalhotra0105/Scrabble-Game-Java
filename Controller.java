package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import javafx.geometry.Pos;

public class Controller implements Initializable, ChangeListener<String> {

    private HashMap<Character, Integer> bag = new HashMap<Character, Integer>();
    private HashMap<Character, Integer> scores = new HashMap<Character, Integer>();
    private List<String> usedWords = new ArrayList<String>();
    private int score;

    @FXML
    private Label totalScoreLabel;

    @FXML
    private Button errorButton;

    @FXML
    private Button submitButton;

    @FXML
    private HBox top1;

    @FXML
    private HBox top2;

    @FXML
    private HBox top3;

    @FXML
    private VBox top4;

    @FXML
    private HBox top5;

    @FXML
    private TextField word;

    @FXML
    private Label numberA;

    @FXML
    private Label numberB;

    @FXML
    private Label numberC;

    @FXML
    private Label numberD;

    @FXML
    private Label numberE;

    @FXML
    private Label numberF;

    @FXML
    private Label numberG;

    @FXML
    private Label numberH;

    @FXML
    private Label numberI;

    @FXML
    private Label numberJ;

    @FXML
    private Label numberK;

    @FXML
    private Label numberL;

    @FXML
    private Label numberM;

    @FXML
    private Label numberN;

    @FXML
    private Label numberO;

    @FXML
    private Label numberP;

    @FXML
    private Label numberQ;

    @FXML
    private Label numberR;

    @FXML
    private Label numberS;

    @FXML
    private Label numberT;

    @FXML
    private Label numberU;

    @FXML
    private Label numberV;

    @FXML
    private Label numberW;

    @FXML
    private Label numberX;

    @FXML
    private Label numberY;

    @FXML
    private Label numberZ;

    @FXML
    private Label usedWordsLabel;

    @FXML
    private Label errorLabel;

    // Reset game state
    private void reset() {
        usedWords.clear();

        // Generates score per each letter
        scores.put('A', 1);
        scores.put('B', 3);
        scores.put('C', 3);
        scores.put('D', 2);
        scores.put('E', 1);
        scores.put('F', 4);
        scores.put('G', 2);
        scores.put('H', 4);
        scores.put('I', 1);
        scores.put('J', 8);
        scores.put('K', 5);
        scores.put('L', 1);
        scores.put('M', 3);
        scores.put('N', 1);
        scores.put('O', 1);
        scores.put('P', 3);
        scores.put('Q', 10);
        scores.put('R', 1);
        scores.put('S', 1);
        scores.put('T', 1);
        scores.put('U', 1);
        scores.put('V', 4);
        scores.put('W', 4);
        scores.put('X', 8);
        scores.put('Y', 4);
        scores.put('Z', 10);

        // Generates how many letters in bag
        bag.put('E', 12);
        bag.put('A', 9);
        bag.put('R', 6);
        bag.put('O', 8);
        bag.put('I', 8);
        bag.put('T', 6);
        bag.put('S', 4);
        bag.put('N', 6);
        bag.put('L', 4);
        bag.put('D', 4);
        bag.put('U', 4);
        bag.put('G', 3);
        bag.put('P', 2);
        bag.put('M', 2);
        bag.put('B', 2);
        bag.put('H', 2);
        bag.put('C', 2);
        bag.put('W', 2);
        bag.put('Y', 2);
        bag.put('F', 2);
        bag.put('V', 2);
        bag.put('K', 1);
        bag.put('X', 1);
        bag.put('Z', 1);
        bag.put('J', 1);
        bag.put('Q', 1);

        // reset score to 0
        score = 0;

    }

    @FXML
    private void returnToGameAction(ActionEvent event) {
        errorButton.setVisible(false);
        errorLabel.setVisible(false);
        top2.setVisible(true);
        top3.setVisible(true);
        top4.setVisible(true);
        submitButton.setVisible(true);
    }


    private void errorMessage(String msg) {
        top2.setVisible(false);
        top3.setVisible(false);
        submitButton.setVisible(false);
        errorLabel.setAlignment(Pos.TOP_LEFT);
        errorLabel.setText(msg);
        errorButton.setVisible(true);
        errorLabel.setVisible(true);
    }

    private void gameOverMessage() {
        top2.setVisible(false);
        top3.setVisible(false);
        top4.setVisible(false);
        errorLabel.setText("GAME OVER");
        errorLabel.setAlignment(Pos.TOP_CENTER);
        errorButton.setVisible(false);
        errorLabel.setVisible(true);
    }

    private boolean validate(String word) {
        if (word.length() == 0) {
            errorMessage("Word cannot be blank");
            return false;
        }
        if (word.length()<2) {
            errorMessage("Word is too short");
            return false;
        }
        if (word.length()>8) {
            errorMessage("Word is too long");
            return false;
        }
        // word must have at least one vowel
        if (!word.contains("A") && !word.contains("E") && !word.contains("I") && !word.contains("O") && !word.contains("U") && !word.contains("Y")) {
            errorMessage("Word does not contain vowels");
            return false;
        }
        // check word is used already
        if (usedWords.contains(word)) {
            errorMessage("Word is already used");
            return false;
        }
        // Check word contains not letter
        for(Character c : word.toCharArray()) {
            if (!Character.isLetter(c)) {
                errorMessage("Word contains not letter \""+c+"\"");
                return false;
            }
        }

        // Calculate how many letters are needed for given word
        HashMap<Character, Integer> neededForWord = new HashMap<Character, Integer>();
        for(Character c : word.toCharArray()) {
            if (neededForWord.containsKey(c))
                neededForWord.put(c, neededForWord.get(c)+1);
            else
                neededForWord.put(c, 1);
        }
        // Make sure we have enough letters in the bag
        for(Character c : word.toCharArray()) {
            if (bag.get(c)< neededForWord.get(c)) {
                errorMessage("Insufficient letter \""+c+"\"");
                return false;
            }
        }

        return true;
    }


    // Ch3eck for game over condition
    private boolean gameOverCondition() {
        // Check there is vowels in the bag
        if (bag.get('A')==0 && bag.get('E')==0 && bag.get('I')==0 && bag.get('O')==0 && bag.get('U')==0 && bag.get('Y')==0)
            return true;

        // check total counts of letters
        int totalCount = 0;
        // Keys are from A to Z
        for(char c='A'; c<='Z'; c++)
            totalCount += bag.get(c);

        if (totalCount<2)
            return true;

        return false;
    }

    private void submitWord(String word) {
        if (validate(word)) {
            // Add word to used word list
            usedWords.add(word);

            // generate text for used word
            String uwt = "";
            for(String usedWord: usedWords) {
                // Add comma before word
                // if length = 0 it means it's first word
                // not needed to add comma before first word
                if (uwt.length()>0)
                    uwt = uwt + ", ";
                uwt = uwt + usedWord;
            }

            // Calculate score and recrease letter counts in bag
            for(Character c : word.toCharArray()) {
                score = score + scores.get(c);
                bag.put(c, bag.get(c) - 1);
            }
            updateBag();

            // Update text on the screen
            usedWordsLabel.setText(uwt);

            // Update text on the screen
            totalScoreLabel.setText(String.format("%d", score));
            // clean text in edit box
            this.word.clear();
        }
        if (gameOverCondition()) {
            // Show game over message
            gameOverMessage();
            //t.setInputType(InputType.TYPE_NULL);
            //submit.setVisibility(View.INVISIBLE);
        }
    }

    @FXML
    private void submitWordAction(ActionEvent event) {
        submitWord(word.getText());
    }

    private void updateBag() {
        numberA.setText(String.format("%d", bag.get('A')));
        numberB.setText(String.format("%d", bag.get('B')));
        numberC.setText(String.format("%d", bag.get('C')));
        numberD.setText(String.format("%d", bag.get('D')));
        numberE.setText(String.format("%d", bag.get('E')));
        numberF.setText(String.format("%d", bag.get('F')));
        numberG.setText(String.format("%d", bag.get('G')));
        numberH.setText(String.format("%d", bag.get('H')));
        numberI.setText(String.format("%d", bag.get('I')));
        numberJ.setText(String.format("%d", bag.get('J')));
        numberK.setText(String.format("%d", bag.get('K')));
        numberL.setText(String.format("%d", bag.get('L')));
        numberM.setText(String.format("%d", bag.get('M')));
        numberN.setText(String.format("%d", bag.get('N')));
        numberO.setText(String.format("%d", bag.get('O')));
        numberP.setText(String.format("%d", bag.get('P')));
        numberQ.setText(String.format("%d", bag.get('Q')));
        numberR.setText(String.format("%d", bag.get('R')));
        numberS.setText(String.format("%d", bag.get('S')));
        numberT.setText(String.format("%d", bag.get('T')));
        numberU.setText(String.format("%d", bag.get('U')));
        numberV.setText(String.format("%d", bag.get('V')));
        numberW.setText(String.format("%d", bag.get('W')));
        numberX.setText(String.format("%d", bag.get('X')));
        numberY.setText(String.format("%d", bag.get('Y')));
        numberZ.setText(String.format("%d", bag.get('Z')));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reset();
        errorButton.setVisible(false);
        word.textProperty().addListener(this);
        updateBag();
    }

    @Override
    public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        word.setText(newValue.toUpperCase());
    }
}
