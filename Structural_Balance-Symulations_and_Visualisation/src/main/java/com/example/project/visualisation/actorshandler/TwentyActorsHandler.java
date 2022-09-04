package com.example.project.visualisation.actorshandler;

import com.example.project.visualisation.screen.RowDrawer;
import javafx.scene.layout.AnchorPane;

public class TwentyActorsHandler extends ActorHandler{
    private static final int X_DISTANCE = 90;
    private static final int Y_DISTANCE = 90;
    private static final int COLUMN_NUMBER = 5;
    private static final int ROW_NUMBER = 4;
    private static final int BEGIN_X_POSITION = 60;
    private static final int BEGIN_Y_POSITION = 10;

    public TwentyActorsHandler(int actorsNumber) {
        super(actorsNumber);
    }

    @Override
    public void organizeActors(AnchorPane panel) {
        for (int row = 0; row < ROW_NUMBER; row++) {
            RowDrawer rowDrawer = new RowDrawer(X_DISTANCE, COLUMN_NUMBER,
                    BEGIN_X_POSITION, BEGIN_Y_POSITION + row * Y_DISTANCE);

            rowDrawer.draw(panel);
        }
    }
}
