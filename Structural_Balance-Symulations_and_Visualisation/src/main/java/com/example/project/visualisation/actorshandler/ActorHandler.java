package com.example.project.visualisation.actorshandler;

import javafx.scene.layout.AnchorPane;

public abstract class ActorHandler {
    protected int actorsNumber;

    public ActorHandler(int actorsNumber) {
        this.actorsNumber = actorsNumber;
    }

    public void setActorsScreen(int actorsNumber){
        this.actorsNumber = actorsNumber;
    }

    public abstract void organizeActors(AnchorPane panel);
}
