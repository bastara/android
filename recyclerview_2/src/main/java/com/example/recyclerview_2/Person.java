package com.example.recyclerview_2;

import java.util.ArrayList;
import java.util.List;

class Person {
    String name;
    String age;

    Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    private List<Person> persons;

    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old"));
        persons.add(new Person("Lavery Maiss", "25 years old"));
        persons.add(new Person("Lillie Watts", "35 years old"));
    }
}