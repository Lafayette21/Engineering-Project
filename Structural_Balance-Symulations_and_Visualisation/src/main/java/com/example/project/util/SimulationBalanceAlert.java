package com.example.project.util;

import javafx.scene.control.Alert;

public class SimulationBalanceAlert extends Alert {
    private static final String MESSAGE = "Siec osiągnęła balans";
    private static final String TITLE = "Sukces";

    public SimulationBalanceAlert() {
        super(AlertType.INFORMATION);
        this.setContentText(MESSAGE);
        this.setTitle(TITLE);
    }
}
