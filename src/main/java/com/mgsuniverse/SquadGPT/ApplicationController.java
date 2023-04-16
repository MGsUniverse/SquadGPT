package com.mgsuniverse.SquadGPT;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    @FXML
    private ChoiceBox<String> position;
    @FXML
    private ChoiceBox<String> ability;
    @FXML
    private Label goalsText;
    @FXML
    private Slider goalsSlider;
    @FXML
    private Label assistText;
    @FXML
    private Slider assistSlider;
    @FXML
    private Slider ageSlider;
    @FXML
    private Label ageText;
    @FXML
    private TextField teamText;
    @FXML
    private TextField countryText;
    @FXML
    private TextField othersText;
    @FXML
    private CheckBox playingCheckbox;
    @FXML
    private TextArea resultArea;

    private String[] positions = {"Any", "GK", "RB", "RWB", "CB", "LB", "LWB", "CDM", "CM", "CAM", "RM", "RW", "LM", "LW", "RF", "LF", "CF", "ST"};
    private String[] abilities = {"Any", "Passing", "Attacking", "Shooting", "Crossing", "Dribbling", "Free Kicks", "Penalties", "Skills", "Defending", "Speed", "Physicality", "Weak Foot", "Corners"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        position.getItems().addAll(positions);
        ability.getItems().addAll(abilities);

        goalsSlider.valueProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                goalsText.setText(String.valueOf((int)goalsSlider.getValue()));
            }
        });

        assistSlider.valueProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                assistText.setText(String.valueOf((int)assistSlider.getValue()));
            }
        });

        ageSlider.valueProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                ageText.setText(String.valueOf((int)ageSlider.getValue()));
            }
        });
    }

    public void search(ActionEvent event) throws Exception {
        String query = "Can you find me some soccer players with a rank (ranked best to worst) that fits the following criteria: ";
        if (position.getValue() != null) {
            query = query + position.getValue() + ", ";
        }
        if (ability.getValue() != null) {
            query = query + "good " + ability.getValue() + ", ";
        }
        query = query + "at least " + (int)goalsSlider.getValue() + " goals, ";
        query = query + "at least " + (int)assistSlider.getValue() + " assists, ";
        query = query + "at most " + (int)ageSlider.getValue() + " years old, ";
        if (!teamText.getText().isEmpty()) {
            query = query + "currently plays for " + teamText.getText() + ", ";
        }
        if (!countryText.getText().isEmpty()) {
            query = query + "is from " + countryText.getText() + ", ";
        }
        if (!othersText.getText().isEmpty()){
            query = query + othersText.getText();
        }
        if (playingCheckbox.isSelected()) {
            query = query + "still plays soccer";
        }

        resultArea.setText(chatgpt.chatGPT(query));
    }

    public void help(ActionEvent event){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(" All data (including age) only\n goes up until September 2021"));
        Scene dialogScene = new Scene(dialogVbox, 200, 50);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}