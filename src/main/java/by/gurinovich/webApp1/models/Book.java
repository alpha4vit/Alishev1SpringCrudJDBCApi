package by.gurinovich.webApp1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @Size(min = 1, message = "Некорректное название книги")
    private String name;

    @Size(min = 1, message = "Некорректное имя автора")
    private String author;

    @Min(value = 1865, message = "Год рождения должен быть больше чем 1865")
    private int publishment;

    private String namePerson;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishment() {
        return publishment;
    }

    public void setPublishment(int publishment) {
        this.publishment = publishment;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }
}