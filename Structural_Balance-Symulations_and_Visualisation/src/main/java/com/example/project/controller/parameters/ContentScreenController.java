package com.example.project.controller.parameters;

import com.example.project.Resource;

public class ContentScreenController implements ParameterControlledScreen{

    ParametersScreenController screenParent;

    public void setContent(Resource resource){
        screenParent.setContentScreen(resource);
    }

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
