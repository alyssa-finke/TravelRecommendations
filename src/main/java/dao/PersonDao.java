package dao;

import model.Person;

import java.util.List;

public interface PersonDao {
    Person getPerson(long personId);

    List<Person> listAllPeople();

    Person createPerson(Person person, String personFirstName, String personLastName);

    void updatePerson(Person updatedPerson);//should this be long personId?

    void deletePerson(long personId);
}
