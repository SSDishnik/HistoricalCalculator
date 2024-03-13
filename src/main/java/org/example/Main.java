package org.example;

import org.example.application.CalculatorApplication;
import org.example.daoImpl.RecordDAOImpl;
import org.example.models.Record;
import org.example.service.RecordService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException{
        CalculatorApplication.launch(CalculatorApplication.class);
    }
}