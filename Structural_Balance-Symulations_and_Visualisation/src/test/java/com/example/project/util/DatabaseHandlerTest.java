package com.example.project.util;

import com.example.project.database.util.DatabaseHandler;
import org.junit.Test;

public class DatabaseHandlerTest {

    @Test
    public void shouldEstablishConnection(){
        new DatabaseHandler();
    }

}