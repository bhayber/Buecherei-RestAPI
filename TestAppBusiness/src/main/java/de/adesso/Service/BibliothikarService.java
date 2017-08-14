package de.adesso.Service;

import de.adesso.Repository.BibliothikarRepository;
import de.adesso.Repository.PersonRepository;
import de.adesso.model.Bereich;
import de.adesso.model.Bibliothikar;
import de.adesso.model.Geschlecht;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BibliothikarService {

    @Autowired
    private BibliothikarRepository bibliothikarRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BibliothikarService(BibliothikarRepository bibliothikarRepository) {
        this.bibliothikarRepository = bibliothikarRepository;
    }

    public String createKunde(String name, String email, String PhoneNumber, Geschlecht geschlecht, String adresse, String objektAdresse, Bereich bereich) {
        Bibliothikar newBib = new Bibliothikar();
        try {
            newBib.setEmail(email);
            newBib.setName(name);
            newBib.setTelmobile(PhoneNumber);
            newBib.setGeschlecht(geschlecht);
            newBib.setAdresse(adresse);
            newBib.setBereich(bereich);
            bibliothikarRepository.save(newBib);
        } catch (Exception ex) {
            return "Error creating the Bibliothikar: " + ex.toString() + newBib.getId();
        }
        return "Bibliothikar succesfully created with id = " + newBib.getId();
    }

    public String delete(String uuid) {
        try {
            Bibliothikar FoundBibl = bibliothikarRepository.findById(uuid);
            if (FoundBibl != null)
                bibliothikarRepository.delete(FoundBibl);
            else
                return ("Bibliothikar NOT FOUND. Please Enter valid Bibliothikar ID");
        } catch (Exception ex) {
            return "Error deleting the Bibliothikar:" + ex.toString();
        }
        return "Bibliothikar succesfully deleted!";
    }

    public String updateBibliothikar(String uuid, String objektAdresse, Bereich bereich) {
        try {
            Bibliothikar foundBib = bibliothikarRepository.findById(uuid);
            foundBib.setBereich(bereich);
            foundBib.setObjektAdresse(objektAdresse);
            bibliothikarRepository.save(foundBib);
        } catch (Exception ex) {
            return "Error updating the Kunde: " + ex.toString();
        }
        return "Kunde succesfully updated!";
    }

    public Bibliothikar getBibliothikarByID(String id) {
        return bibliothikarRepository.findById(id);
    }

    public Iterable<Bibliothikar> getBibliothikarByBereich(Bereich bereich) {
        return bibliothikarRepository.findByBereich(bereich);
    }

    public Iterable<Bibliothikar> getAllBibliothikare() {
        return bibliothikarRepository.findAll();
    }
}
