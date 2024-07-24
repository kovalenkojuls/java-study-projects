package ru.julia.neo4jdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Phone {
    private String id;
    private String model;
    private String color;
    private String serialNumber;
}
