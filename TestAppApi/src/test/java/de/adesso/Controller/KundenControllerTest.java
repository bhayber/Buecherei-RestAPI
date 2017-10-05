package de.adesso.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.adesso.model.Geschlecht;
import de.adesso.model.Kunde;
import de.adesso.start.App;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


//Die Integrationstests laufen in der eingebeteten Derby Datenbank
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes = App.class)
public class KundenControllerTest {

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //create Kunde Test
    @Test
    public void test1() throws Exception {
        Kunde newKunde = new Kunde();
        newKunde.setAusweisnr("AB-CX-22-33-22-23");
        newKunde.setName("Dörte");
        newKunde.setEmail("Dörte@email.de");
        newKunde.setAdresse("Siemensstrasse 12 60489 Frankfurt");
        newKunde.setTelmobile("069 29 29 11");
        newKunde.setGeschlecht(Geschlecht.WEIBLICH);
        newKunde.setPassword("Test");
        String sol = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST
                , "http://localhost:9000/kunde/createKunde").param("name", newKunde.getName()).param("adresse", newKunde.getAdresse())
                .param("email", newKunde.getEmail())
                .param("ausweisnr", newKunde.getAusweisnr())
                .param("geschlecht", newKunde.getGeschlecht().name())
                .param("gebDatum", "30/12/1984")
        ).
                andReturn().getResponse().getContentAsString();
        assertThat(sol, new Contains("Kunde succesfully created with id"));
    }

    //get all Kunden Test
    @Test
    public void test2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String kundenlist = mvc.perform(get("http://localhost:9000/kunde/getall"))
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse().getContentAsString();

        List<Kunde> list = mapper.readValue(kundenlist, new TypeReference<List<Kunde>>() {
        });

        System.out.println("Die Liste aller Kunden (name + ausweisnr): ");
        list.forEach(kunde -> System.out.println(kunde.getName() + " : " + kunde.getAusweisnr()));
        assert (list.size() > 0);
    }

    //Delete Kunde By AusweisNr
    @Test
    public void test4() throws Exception {

        String sol = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE
                , "http://localhost:9000/kunde/deleteKundeByAusweisNr/{ausweisnr}", "AB-CX-22-33-22-23")
        ).andReturn().getResponse().getContentAsString();
        assertThat(sol, new Contains("Kunde succesfully deleted!"));
    }

    //Get Kunde By his email Test
    @Test
    public void test3() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String sol = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET
                , "http://localhost:9000/kunde/getKundebyEmail/").param("email", "Dörte@email.de")
        ).andReturn().getResponse().getContentAsString();
        Kunde kunde = mapper.readValue(sol, Kunde.class);
        assertThat(kunde.getEmail(), new Contains("Dörte@email.de"));

    }

}
