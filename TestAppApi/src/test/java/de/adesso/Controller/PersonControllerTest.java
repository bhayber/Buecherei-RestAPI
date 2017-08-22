package de.adesso.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.adesso.model.Geschlecht;
import de.adesso.model.Person;
import de.adesso.start.App;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes = App.class)
@ActiveProfiles("prod")
public class PersonControllerTest {

    private String createdID;

    @Autowired
    WebApplicationContext context;

    Authentication authentication;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//        authentication = new TestingAuthenticationToken("admin", "secret",
//                AuthorityUtils.createAuthorityList("ROLE_USER"));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    //Delete Test
    @Test
    public void test4() throws Exception {

        String sol = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE
                , "http://localhost:9000/person/deletePersonByMail").param("email", "neueEmail@hansseite.de")
        ).andReturn().getResponse().getContentAsString();

        assert (sol.contains("Person succesfully deleted!"));
    }


    @After
    public void cleanup() throws Exception {
        SecurityContextHolder.clearContext();
    }

    //Create Person
    @Test
    public void test1() throws Exception {

        String sol = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST
                , "http://localhost:9000/person/createPerson").param("name", "Hans")
                .param("email", "hans@hansseite.de")
        ).andReturn().getResponse().getContentAsString();
        assert (sol.contains("Person succesfully created with id"));
    }

    // @Test
//    public void getAllPersonTest() throws Exception {
//
////        ObjectMapper mapper = new ObjectMapper();
////
////        String personList =  mvc.perform(get("http://localhost:9000/person/getall"))
////                .andExpect(
////                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
////                .andReturn().getResponse().getContentAsString();
////        List<Person> list = mapper.readValue(personList, new TypeReference<List<Person>>(){});
////
////        System.out.println("Die Liste aller Personen: ");
////        list.forEach( person -> System.out.println(person.getEmail()));
////
////      assert(list.size() >0);
//    }

    @Test
    public void test3() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String personJson = mvc.perform(get("http://localhost:9000/person/getPersonByEmail").param("email", "neueEmail@hansseite.de"))
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse().getContentAsString();
        Person person = mapper.readValue(personJson, Person.class);

        this.createdID = person.getId();

        Assert.assertThat(person.getEmail(), new Contains("neueEmail@hansseite.de"));
    }

    //Test for Update the new Created Person
    @Test
    public void test2() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String personJson = mvc.perform(get("http://localhost:9000/person/getPersonByEmail").param("email", "hans@hansseite.de"))
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse().getContentAsString();
        Person person = mapper.readValue(personJson, Person.class);

        this.createdID = person.getId();


        Person p = new Person();
        p.setId(this.createdID);
        p.setEmail("neueEmail@hansseite.de");
        p.setName("Hans Meiser");
        p.setGeschlecht(Geschlecht.MAENLICH);

        String test = (mvc.perform(put("http://localhost:9000/person/updatePerson").
                param("id", p.getId()).param("email", p.getEmail()
        ).param("name", p.getName()).param("geschlecht", "MAENLICH"))
                .andReturn().getResponse().getContentAsString());

        Assert.assertThat(test, new Contains("Person succesfully updated!"));
    }


}


