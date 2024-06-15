package dev.naimsulejmani.grupi1javafxregistration.models;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    //PK
    private int id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private boolean passive;
}
