package com.example.project.database.repository;

import com.example.project.Resource;

import java.util.HashMap;
import java.util.Map;
public final class RepositoryManager {
    private static RepositoryManager repositoryManagerInstance;
    private final Map<Resource, ParameterRepository> parameterRepositoryMap = new HashMap<>();

    private RepositoryManager() {}

    public static RepositoryManager getInstance(){
        if (repositoryManagerInstance == null){
            repositoryManagerInstance = new RepositoryManager();
        }
        return repositoryManagerInstance;
    }

    public void registerParameterRepository(Resource resource, ParameterRepository parameterRepository){
        parameterRepositoryMap.put(resource,parameterRepository);
    }

    public ParameterRepository getParameterRepositoryByResource(Resource resource){
        return parameterRepositoryMap.get(resource);
    }
}
