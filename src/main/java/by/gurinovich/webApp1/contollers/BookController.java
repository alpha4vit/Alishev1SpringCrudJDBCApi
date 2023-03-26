package by.gurinovich.webApp1.contollers;

import by.gurinovich.webApp1.dao.BooksDao;
import by.gurinovich.webApp1.dao.PeopleDao;
import by.gurinovich.webApp1.models.Book;
import by.gurinovich.webApp1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.unbescape.json.JsonEscapeType;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BooksDao booksDao;
    private final PeopleDao peopleDao;

    @Autowired
    public BookController(BooksDao booksDao, PeopleDao peopleDao) {
        this.booksDao = booksDao;
        this.peopleDao = peopleDao;
    }

    @GetMapping("")
    public String allBooks(Model model){
        model.addAttribute("books", booksDao.getBooks());
        return "books/all";
    }

    @GetMapping("/{id}")
    public String bookInfo(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", booksDao.getBook(id));
        Person BookOwner = booksDao.getBookOwner(id);
        if (BookOwner != null){
            model.addAttribute("owner", BookOwner);
        }
        else{
            model.addAttribute("people", peopleDao.getPeopleList());
        }
        return "books/showInfo";
    }

    @PatchMapping("/{id}/person")
    public String selectPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        booksDao.setOwner(person, id);
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        booksDao.release(id);
        return "redirect:/books/"+id;
    }


    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id")int id){
        booksDao.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String addPage(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
          return "books/new";
        }
        booksDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id")int id, Model model){
        model.addAttribute("book", booksDao.getBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        booksDao.edit(book, id);
        return "redirect:/books/"+id;
    }
}
