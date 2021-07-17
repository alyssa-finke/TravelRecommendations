package model;

public class Person {
    private Long personId;
    private String personFirstName;
    private String personLastName;

    public Person(long personId, String personFirstName, String personLastName) {
        this.personId = personId;
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
    }

    public Person() {
    }

    public Long getPersonId() {
        return personId;
    }


    public String getPersonFirstName() {
        return personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }
}


