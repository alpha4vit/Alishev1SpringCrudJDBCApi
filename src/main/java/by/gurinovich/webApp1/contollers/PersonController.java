package by.gurinovich.webApp1.contollers;

import by.gurinovich.webApp1.dao.PeopleDao;
import by.gurinovich.webApp1.models.Book;
import by.gurinovich.webApp1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PeopleDao peopleDao;

    @Autowired
    public PersonController(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @GetMapping("")
    public String showAllPeople(Model model){
        model.addAttribute("people", peopleDao.getPeopleList());
        return "people/all";
    }

    @GetMapping("/new")
    public String newPeoplePage(@ModelAttribute("person")Person person){
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        peopleDao.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleDao.showPersonalInfo(id));
        model.addAttribute("books",peopleDao.getBooks(id));
        return "people/showInfo";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        peopleDao.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleDao.showPersonalInfo(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id")int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/edit";
        }
        peopleDao.edit(id, person);
        return "redirect:/people";
    }


}
