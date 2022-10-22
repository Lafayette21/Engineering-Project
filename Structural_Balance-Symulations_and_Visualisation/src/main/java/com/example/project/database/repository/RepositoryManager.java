package com.example.project.database.repository;

import com.example.project.Resource;

import java.util.HashMap;
import java.util.Map;

public class RepositoryManager {
    private final Map<Resource, ParameterRepository> parameterRepositoryMap = new HashMap<>();

    public void registerParameterRepository(Resource resource, ParameterRepository parameterRepository){
        parameterRepositoryMap.put(resource,parameterRepository);
    }

    public ParameterRepository getParameterRepositoryByResource(Resource resource){
        return parameterRepositoryMap.get(resource);
    }
}
