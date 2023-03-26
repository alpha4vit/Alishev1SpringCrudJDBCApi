package by.gurinovich.webApp1.dao;

import by.gurinovich.webApp1.models.Book;
import by.gurinovich.webApp1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BooksDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks(){
        List<Book> temp = jdbcTemplate.query("select * from books", new BeanPropertyRowMapper<>(Book.class));
        return temp;
    }

    public Book getBook(int id){
        Book book = jdbcTemplate.query("select * from books where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
        return book;
    }

    public void save(Book book){
        jdbcTemplate.update("insert into books (name, author, publishment) values (?,?,?)", book.getName(), book.getAuthor(), book.getPublishment());
    }

    public void delete(int id){
        jdbcTemplate.update("delete from books where id = ?", id);
    }

    public void edit(Book book,int id){
        jdbcTemplate.update("update books set name=?, author=?, publishment=? where id = ?", book.getName(), book.getAuthor(), book.getPublishment(), id);
    }

    public void release(int id){
        jdbcTemplate.update("update books set person_id = null where id = ?", id);
    }

    public void setOwner(Person person, int id){
        jdbcTemplate.update("update books set person_id = ? where id = ?", person.getId(), id);
    }

    public Person getBookOwner(int id){
        return jdbcTemplate.query("select people.* from books join people on people.id = books.person_id where books.id=?",new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

}
