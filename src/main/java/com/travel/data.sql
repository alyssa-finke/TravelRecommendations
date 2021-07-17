DROP TABLE IF EXISTS person, place CASCADE;

CREATE TABLE person (
    person_id serial,
    person_first varchar(30) NOT NULL,
    person_last varchar(30) NOT NULL,
    CONSTRAINT PK_person PRIMARY KEY(person_id)
);

CREATE TABLE place (
    city_id serial,
    city_name varchar(40) NOT NULL,
    person_id varchar(50) NOT NULL,
    must_see varchar(200) NOT NULL,
    must_do varchar(200) NULL,
    meal varchar(200)NULL,
    dont_forget varchar(200) NULL,
    notes varchar(500) NULL,
    CONSTRAINT PK_place PRIMARY KEY(city_id)
    CONSTRAINT FK_place_person FOREIGN KEY(person_id) REFERENCES(person_id)
);
