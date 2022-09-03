package com.example.project.visualisation.screen;

public abstract class ActorHandler {
    protected int actorsNumber;

    public void setActorsScreen(int actorsNumber){
        this.actorsNumber = actorsNumber;
    }

    public abstract void organizeActors();
}
