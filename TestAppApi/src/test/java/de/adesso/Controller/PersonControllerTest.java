package de.adesso.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.adesso.Service.PersonService;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes = App.class)
public class PersonControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    PersonService personService;

    Authentication authentication;

    private MockMvc mvc;


    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//        authentication = new TestingAuthenticationToken("admin", "secret",
//                AuthorityUtils.createAuthorityList("ROLE_USER"));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @After
    public void cleanup() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void createPersonTest() throws Exception {

        String sol = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST
                , "http://localhost:9000/person/createPerson").param("name", "Hans")
                .param("email", "hans@hansseite.de")
        ).andReturn().getResponse().getContentAsString();

        assert (sol.contains("Person succesfully created with id"));
    }

    @Test
    public void getAllPersonTest() throws Exception {

//        ObjectMapper mapper = new ObjectMapper();
//
//        String personList =  mvc.perform(get("http://localhost:9000/person/getall"))
//                .andExpect(
//                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andReturn().getResponse().getContentAsString();
//        List<Person> list = mapper.readValue(personList, new TypeReference<List<Person>>(){});
//
//        System.out.println("Die Liste aller Personen: ");
//        list.forEach( person -> System.out.println(person.getEmail()));
//
//      assert(list.size() >0);
    }

    @Test
    public void getPersonTest() throws Exception {

        mvc.perform(get("http://localhost:9000/person/{id}", "18bf8fba-ab68-4673-b33b-0b5c4f8eb989"))
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.email").value("neueEmail@hansseite.de"));
    }

    @Test
    public void updatePersonTest() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
        Person p = new Person();
        p.setId("18bf8fba-ab68-4673-b33b-0b5c4f8eb989");
        p.setEmail("neueEmail@hansseite.de");
        p.setName("Hans Meiser");
        p.setGeschlecht(Geschlecht.MAENLICH);
//        String jsonInString = mapper.writeValueAsString(p);

     String test = (mvc.perform(put("http://localhost:9000/person/updatePerson").
             param("id",p.getId()).param("email",p.getEmail()
                    ).param("name",p.getName()).param("geschlecht","MAENLICH"))
                .andReturn().getResponse().getContentAsString());

        Assert.assertThat (test, new Contains("Person succesfully updated!"));

    }


}


