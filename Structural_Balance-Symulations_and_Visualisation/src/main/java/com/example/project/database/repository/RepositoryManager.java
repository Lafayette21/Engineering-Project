package com.example.project.database.repository;

import com.example.project.RepositoryName;

import java.util.HashMap;
import java.util.Map;

public final class RepositoryManager {
    private static RepositoryManager repositoryManagerInstance;
    private final Map<RepositoryName, ParameterRepository> parameterRepositoryMap = new HashMap<>();

    private RepositoryManager() {
    }

    public static RepositoryManager getInstance() {
        if (repositoryManagerInstance == null) {
            repositoryManagerInstance = new RepositoryManager();
        }
        return repositoryManagerInstance;
    }

    public void registerParameterRepository(RepositoryName repositoryName, ParameterRepository parameterRepository) {
        parameterRepositoryMap.put(repositoryName, parameterRepository);
    }

    public ParameterRepository getParameterRepositoryByName(RepositoryName repositoryName) {
        return parameterRepositoryMap.get(repositoryName);
    }
}
