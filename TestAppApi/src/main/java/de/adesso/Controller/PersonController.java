package de.adesso.Controller;

import de.adesso.Service.PersonService;
import de.adesso.model.Geschlecht;
import de.adesso.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/createPerson")
    public String createPerson(Person person) {
        return personService.createPerson(person);
    }

    /**
     * GET /delete  --> Delete the Person having the passed id.
     */
    @DeleteMapping("/deletePerson/{id}")
    @ResponseBody
    public String delete(@PathVariable String id) {
        return personService.delete(id);
    }

    /**
     * GET /get-by-email  --> Return the id for the Person having the passed
     * email.
     */
    @GetMapping("/get-Persons-by-email")
    @ResponseBody
    public Iterable<Person> getPersonsByEmail(String email) {
        return personService.findPersonsByEmail(email);
    }

    /**
     * GET /update  --> Update the email and the name for the Person in the
     * database having the passed id.
     */
    @PutMapping("/updatePerson")
    @ResponseBody
    public String updatePerson(Person person) {
        return personService.updatePerson(person);
    }


    /**
     * GET /all  --> Gets all Person Information
     */
    @GetMapping("/getall")
    @ResponseBody
    public Iterable<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Person getPersonById(@PathVariable("id") String id) {
        Person person = personService.getPersonByID(id);
        return person;
    }
}
