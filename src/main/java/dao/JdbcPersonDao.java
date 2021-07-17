package dao;

import model.Person;
import model.Place;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPersonDao implements PersonDao{
    private JdbcTemplate jdbcTemplate;
    private PersonDao personDao;
    private PlaceDao placeDao;


    public JdbcPersonDao(JdbcTemplate jdbcTemplate, PersonDao personDao, PlaceDao placeDao) {
        this.jdbcTemplate = jdbcTemplate;
       this.personDao = personDao;
       this.placeDao = placeDao;
    }

    @Override //Should be able to see a list of who has submitted
    public List<Person> listAllPeople() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT person_id, person_first, person_last FROM person;";
        //   String nameSearch = "%" + personName + "%";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Person person = mapRowToPerson(results);
            people.add(person);
        }
        return people;
    }


    //I want to be able to select by personId
    @Override
    public Person getPerson(long personId) {
        Person person = null;
        String sql = "SELECT person_id, person_first, person_last FROM person WHERE person_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, personId);
        if(results.next()){
            person = mapRowToPerson(results);
        }

        return person;
    }

    //I want to be able to find by name
    public Person findByName(String personFirstName, String personLastName) throws NameNotFoundException {
        String sql = "SELECT person_id, person_first, person_last " +
                "FROM person " +
                "WHERE person_first ILIKE ? OR person_last ILIKE ? ;";
        SqlRowSet rowSet= jdbcTemplate.queryForRowSet(sql, personFirstName, personLastName);
        if (rowSet.next()){
            return mapRowToPerson(rowSet);
        }
        throw new NameNotFoundException(personFirstName + " " + personLastName + "was not found.");
    }

    //Do I want to find Id by name?


    //I want to create a person
    @Override
    public Person createPerson(Person person, String personFirstName, String personLastName) {
        String sql = "INSERT INTO person(person_first, person_last) " +
                "VALUES(?, ?) " +
                "RETURNING person_id);";
        Long personId = jdbcTemplate.queryForObject(sql, Long.class, person.getPersonFirstName(), person.getPersonLastName());
        return getPerson(personId);
    }

    @Override
    public void updatePerson(Person updatedPerson) {
        String sql = "UPDATE person " +
                "SET person_first = ?, person_last = ? " +
                "WHERE person_id = ?;";
                jdbcTemplate.update(sql, updatedPerson.getPersonFirstName(), updatedPerson.getPersonLastName());
    }

    @Override
    public void deletePerson(long personId) {
        //do i also need to delete from place?
        String sql = "DELETE FROM person WHERE person_id = ?; DELETE FROM place WHERE person_id = ?";
        jdbcTemplate.update(sql, personId, personId);
    }

    private Person mapRowToPerson(SqlRowSet rowSet) {
        Person person = new Person();
        person.setPersonId(rowSet.getLong("person_id"));
        person.setPersonFirstName(rowSet.getString("person_first"));
        person.setPersonLastName(rowSet.getString("person_last"));
        return person;
    }

}
