package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.parametervalues.ParameterValue;

import java.util.HashMap;
import java.util.Map;

public class ParametersValueHandler {
    private final Map<Resource, ParameterValue> parameterValueMap = new HashMap<>();

    public void updateValues(Resource resource, ParameterValue parameterValue) {
        if (parameterValueMap.containsKey(resource)) {
            parameterValueMap.replace(resource, parameterValue);
        } else {
            parameterValueMap.put(resource, parameterValue);
        }
    }

    public ParameterValue getParameterValueByResource(Resource resource){
        return parameterValueMap.get(resource);
    }
}
