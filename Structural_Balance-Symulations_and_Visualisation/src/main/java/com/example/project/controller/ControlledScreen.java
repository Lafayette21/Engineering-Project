package com.example.project.controller;

import com.example.project.database.repository.RepositoryManager;

public interface ControlledScreen {
    void setScreenParent(MainApplicationScreenController screenParent);

    void setRepositoryManager(RepositoryManager repositoryManager);
}
