package org.example.models;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

@Entity
@Table(name = "calculator_history")
@Data
public class Record {


    @Id
    @GeneratedValue
    private Long id;

    private String expression;

    private double result;

    public Record() {

    }

    public Record(String expression, double result) {
        this.expression = expression;
        this.result = result;
    }



}
