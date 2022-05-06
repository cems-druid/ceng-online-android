package com.example.cengonline_v1.model;

public class Person {

    private String name;
    private String surname;
    private String Id;

    public Person(String name, String surname, String id){
     this.name = name;
     this.surname = surname;
     this.Id = id;
    }

    public Person(){

    }

    //Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return Id;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setId(String id) {
        Id = id;
    }
}
