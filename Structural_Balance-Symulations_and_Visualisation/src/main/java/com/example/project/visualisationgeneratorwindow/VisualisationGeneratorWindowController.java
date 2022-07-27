package com.example.project.visualisationgeneratorwindow;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationGeneratorWindowController implements Initializable {
    @FXML
    private Slider actorsSlider;

    @FXML
    private TextField actorsTextField;

    int actorsAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actorsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldNumber, Number newNumber) {
                actorsAmount = (int) newNumber;
                actorsTextField.setText(Integer.toString(actorsAmount));
            }
        });
    }

}
