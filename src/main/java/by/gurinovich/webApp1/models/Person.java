package by.gurinovich.webApp1.models;

import by.gurinovich.webApp1.dao.PeopleDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

public class Person {

    private int id;


    @Size(min = 2, max = 30, message= "Размер ФИО должнен содержать от 2 до 30 символов")
    private String name;

    @Min(value = 1865, message = "Год рождения должен быть больше чем 1865")
    private int age;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
