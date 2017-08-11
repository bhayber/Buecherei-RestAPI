package de.adesso.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

    @Test
    public void createKunde() throws Exception {
        Kunde newKunde = new Kunde();
        newKunde.setAusweisnr("AB-CX-22-33-22-23");
        newKunde.setName("Dörte");
        newKunde.setEmail("Dörte@email.de");
        newKunde.setAdresse("Siemensstrasse 12 60489 Frankfurt");
        newKunde.setTelmobile("069 29 29 11");

        String sol = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST
                , "http://localhost:9000/kunde/createKunde").param("name", newKunde.getName()).param("adresse", newKunde.getAdresse())
                .param("email", newKunde.getEmail())
                .param("ausweisnr", newKunde.getAusweisnr())
        ).
                andReturn().getResponse().getContentAsString();
        assertThat(sol, new Contains("Kunde succesfully created with id"));
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getKundebyNameAndEmail() throws Exception {
    }

    @Test
    public void updateKunde() throws Exception {
    }

    @Test
    public void getAllKunden() throws Exception {
                ObjectMapper mapper = new ObjectMapper();

        String kundenlist =   mvc.perform(get("http://localhost:9000/kunde/getall"))
                .andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse().getContentAsString();

        List<Kunde> list = mapper.readValue(kundenlist, new TypeReference<List<Kunde>>(){});

        System.out.println("Die Liste aller Kunden (name + ausweisnr): ");
        list.forEach(kunde -> System.out.println(kunde.getName() + " : " + kunde.getAusweisnr()));
        assert(list.size() > 0);
    }

    @Test
    public void getKundeById() throws Exception {
    }
}
