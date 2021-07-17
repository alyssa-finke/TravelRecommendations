package controller;
import dao.PersonDao;
import dao.PlaceDao;
import model.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    private PersonDao personDao;
    private PlaceDao placeDao;

    public PersonController(PersonDao personDao, PlaceDao placeDao){
        this.personDao = personDao;
        this.placeDao = placeDao;
    }
    //this is my next step

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<Person> listAllPeople(){
        return personDao.listAllPeople();
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public void createPerson(@RequestBody Person person) {
        personDao.createPerson(person, person.getPersonLastName(), person.getPersonLastName());
        //are these getters correct?
    }

}
