package by.gurinovich.webApp1.RowMappers;

import by.gurinovich.webApp1.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt(1));
        person.setName(resultSet.getString(2));
        person.setAge(resultSet.getInt(3));
        return person;
    }
}
