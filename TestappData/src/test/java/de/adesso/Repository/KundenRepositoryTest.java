package de.adesso.Repository;

import de.adesso.model.Geschlecht;
import de.adesso.model.Kunde;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDatabaseConfig.class, DevDatabaseConfig.class})
@SpringBootConfiguration
public class KundenRepositoryTest {

    //Konstanten
    private static final String name = "Bernadot Schuhmacher";
    private static final String email = "HanseRockstar@Hannover.de";
    private static final String ausweisnr = "XX-XX-BB";
    private static final String adresse = "Bernadotstrasse 12; 60486 Frankfurt am Main";

    private static String id;

    @Autowired(required = false)
    private KundenRepository kundenRepository;

    @Before
    public void create() throws Exception {
        Kunde kunde = new Kunde();
        kunde.setName(name);
        kunde.setEmail(email);
        kunde.setAusweisnr(ausweisnr);
        kunde.setGeschlecht(Geschlecht.WEIBLICH);
        kunde.setAdresse(adresse);
        kundenRepository.save(kunde);
        id = kunde.getId();
        Assertions.assertThat(id).isNotNull(); // AssertJ Benutzt
    }

    @After
    public void tearDown() throws Exception {
        kundenRepository.deleteAll(); // sauber machen nach dem Testen
    }

    @Test
    public void GetKundeTest() {
        Kunde foundKunde = kundenRepository.findById(id);
        Assert.assertNotNull("Der Kunde mit der ID : " + id + " wurde nicht gefunden!!", foundKunde);
        Assert.assertEquals(name, foundKunde.getName());
        Assert.assertEquals(email, foundKunde.getEmail());
        Assert.assertEquals(adresse, foundKunde.getAdresse());
        Assert.assertEquals(ausweisnr, foundKunde.getAusweisnr());
    }

    @Test
    public void UpdateKundeTest() {
        Kunde foundKunde = kundenRepository.findById(id);
        foundKunde.setAdresse("Düsseldorferstr. 12");
        foundKunde.setTelmobile("0800 8800 8800");
        kundenRepository.save(foundKunde);
        foundKunde = kundenRepository.findById(id);
        Assert.assertNotNull("Der Kunde mit der ID : " + id + " wurde nicht gefunden!!", foundKunde);
        Assert.assertEquals("Düsseldorferstr. 12", foundKunde.getAdresse());
        Assert.assertEquals(email, foundKunde.getEmail());
        Assert.assertEquals("0800 8800 8800", foundKunde.getTelmobile());
    }

}