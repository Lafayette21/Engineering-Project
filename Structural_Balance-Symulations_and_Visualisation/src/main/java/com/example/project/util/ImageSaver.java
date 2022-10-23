package com.example.project.util;

import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;

public class ImageSaver {
    public static void save(AnchorPane visualisationPane, Integer stepNumber) {
        String fileName = "SimulationStep" + stepNumber;
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

        //Prompt user to select a file
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                //Pad the capture area
                WritableImage writableImage = new WritableImage((int) visualisationPane.getWidth(),
                        (int) visualisationPane.getHeight());
                visualisationPane.snapshot(null, writableImage);
                //Write the snapshot to the chosen file
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                //Write the snapshot to the chosen file
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
