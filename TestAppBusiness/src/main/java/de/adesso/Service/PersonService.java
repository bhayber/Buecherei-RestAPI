package de.adesso.Service;

import de.adesso.Repository.PersonRepository;
import de.adesso.model.Geschlecht;
import de.adesso.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String createPerson(String name, String email, String phoneNumber, Geschlecht geschlecht, String adresse) {
        Person newPerson = new Person();
        try {
            newPerson.setEmail(email);
            newPerson.setName(name);
            newPerson.setTelmobile(phoneNumber);
            newPerson.setGeschlecht(geschlecht);
            newPerson.setAdresse(adresse);
            personRepository.save(newPerson);
        } catch (Exception ex) {
            return "Error creating the Person: " + ex.toString() + newPerson.getId();
        }
        return "Person succesfully created with id = " + newPerson.getId();
    }

    public String createPerson(Person person) {
        try {
            Person newPerson = new Person(person);
            personRepository.save(person);
        } catch (Exception ex) {
            return "Error creating the Person: " + ex.toString() + person.getId();
        }
        return "Person succesfully created with id = " + person.getId();
    }

    public String delete(String uuid) {
        try {
            Person FoundPerson = personRepository.findPersonById(uuid);
            if (FoundPerson != null)
                personRepository.delete(FoundPerson);
            else
                return ("Person NOT FOUND. Please Enter valid Person ID");
        } catch (Exception ex) {
            return "Error deleting the Person:" + ex.toString();
        }
        return "Person succesfully deleted!";
    }


    public String deleteByEmail(String email) {
        try {
            Person FoundPerson = personRepository.findPersonByEmail(email);
            if (FoundPerson != null)
                personRepository.delete(FoundPerson);
            else
                return ("Person NOT FOUND. Please Enter valid Person Email");
        } catch (Exception ex) {
            return "Error deleting the Person:" + ex.toString();
        }
        return "Person succesfully deleted!";
    }


    public Person findPersonByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public String updatePerson(Person person) {
        try {
            Person FoundPerson = personRepository.findPersonById(person.getId());
            FoundPerson.setEmail(person.getEmail());
            FoundPerson.setAdresse(person.getAdresse());
            FoundPerson.setName(person.getName());
            FoundPerson.setTelmobile(person.getTelmobile());
            FoundPerson.setGeschlecht(person.getGeschlecht());
            personRepository.save(FoundPerson);
        } catch (Exception ex) {
            return "Error updating the Person: " + ex.toString();
        }
        return "Person succesfully updated!";
    }

    public Person getPersonByPhone(String phone) {
        return personRepository.findByTelmobile(phone);
    }

    public Person getPersonByID(String id) {
        return personRepository.findPersonById(id);
    }

    public Person getPersonByNameAndEmail(String email, String name) {
        return personRepository.findByEmailAndName(email, name);
    }

    public Iterable<Person> getPersonsByName(String PersonName) {
        return personRepository.findPersonsByName(PersonName);
    }

    public Iterable<Person> getAllPerson() {
        return personRepository.findAll();
    }
}
