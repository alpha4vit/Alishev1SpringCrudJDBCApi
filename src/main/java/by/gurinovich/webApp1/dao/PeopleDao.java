package by.gurinovich.webApp1.dao;

import java.util.ArrayList;
import java.util.List;

import by.gurinovich.webApp1.RowMappers.PersonRowMapper;
import by.gurinovich.webApp1.models.Book;
import by.gurinovich.webApp1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.unbescape.json.JsonEscapeType;

@Component
public class PeopleDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeopleList(){
        List<Person> people = new ArrayList<>();
        people = jdbcTemplate.query("select * from people", new BeanPropertyRowMapper<>(Person.class));
        return people;
    }

    public void savePerson(Person person){
        jdbcTemplate.update("insert into people(name, age) values (?, ?)", person.getName(), person.getAge());
    }

    public Person showPersonalInfo(int id){
        Person temp = jdbcTemplate.query("select * from people where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        return temp;
    }

    public void delete(int id){
        jdbcTemplate.update("delete from people where id = ?", id);
    }

    public void edit(int id, Person person){
        jdbcTemplate.update("update people set name=?, age=? where id = ?", person.getName(), person.getAge(), id);
    }

    public List<Book> getBooks(int id){
        List<Book> books =  jdbcTemplate.query("select * from books where person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }



}
