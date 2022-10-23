package com.example.project.util;

import com.example.project.Resource;
import com.example.project.exception.InstantiationNotAllowedException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ControllerGetter {
    private ControllerGetter() {
        throw new InstantiationNotAllowedException();
    }

    public static Parent getByResource(Resource resource){
        try {
            return new FXMLLoader(ControllerGetter.class.getResource(resource.getResourceFileName())).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
