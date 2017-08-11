package de.adesso.Repository;

import de.adesso.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

 
   @Query("select u from Person u where u.email = ?1")
   public Iterable<Person> findByEmail(String email);

    @Query("select u from Person u where u.name = ?1")
    public Iterable<Person> findPersonsByName(String name);

    /**
   * This method will find an Person instance in the database by its name and email.
   * Note that this method is not implemented and its working code will be
   * automatically generated from its signature by Spring Data JPA.
   */
   public Person findByEmailAndName(String email, String name);

   public Person findPersonByEmail(String email);

   public Person findPersonById(String uuid);

   public Person findByTelmobile(String Phonenumber);



}