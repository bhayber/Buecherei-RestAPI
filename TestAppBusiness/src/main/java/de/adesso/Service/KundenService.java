package de.adesso.Service;

import de.adesso.Repository.KundenRepository;
import de.adesso.Repository.PersonRepository;
import de.adesso.model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import java.util.Calendar;

@Component
public class KundenService {


    @Autowired
    private KundenRepository kundenRepository;

    @Autowired
    private PersonRepository personRepository;

    public KundenService(KundenRepository kundenRepository) {
        this.kundenRepository = kundenRepository;
    }

    public String createKunde(Kunde kunde) throws EntityExistsException {
        Kunde newKunde = new Kunde();
        Calendar today = Calendar.getInstance();
        Kunde foundKunde = kundenRepository.findByEmail(kunde.getEmail());
        PasswordMaker passwordMaker = new PasswordMaker();
        if (foundKunde != null) throw new EntityExistsException("Kunde mit der Email existiert bereits");
        try {
            newKunde.setEmail(kunde.getEmail());
            newKunde.setName(kunde.getName());
            newKunde.setTelmobile(kunde.getTelmobile());
            newKunde.setGeschlecht(kunde.getGeschlecht());
            newKunde.setAdresse(kunde.getAdresse());
            newKunde.setAusweisnr(kunde.getAusweisnr());
            newKunde.setMitgliedSeit(today.getTime());
            newKunde.setPassword(passwordMaker.hashPassword(kunde.getPassword()));
            kundenRepository.save(newKunde);
        } catch (Exception ex) {
            return "Error creating the Person: " + ex.toString();
        }
        return "Kunde succesfully created with id = [" + newKunde.getId() + "]";
    }

    public String delete(String uuid) {
        try {
            Kunde FoundKunde = kundenRepository.findById(uuid);
            if (FoundKunde != null)
                kundenRepository.delete(FoundKunde);
            else
                return ("Kunde NOT FOUND. Please Enter valid Kunden ID");
        } catch (Exception ex) {
            return "Error deleting the Kunde:" + ex.toString();
        }
        return "Kunde succesfully deleted!";
    }

    public String deleteByAusweisNr(String ausweisnr) {
        try {
            Kunde FoundKunde = kundenRepository.findByAusweisnr(ausweisnr);
            if (FoundKunde != null)
                kundenRepository.delete(FoundKunde);
            else
                return ("Kunde NOT FOUND. Please Enter valid AusweisNr");
        } catch (Exception ex) {
            return "Error deleting the Kunde:" + ex.toString();
        }
        return "Kunde succesfully deleted!";
    }

    public String updateKunde(Kunde kunde) {
        try {
            Kunde foundKunde = kundenRepository.findById(kunde.getId());
            foundKunde.setEmail(kunde.getEmail());
            foundKunde.setName(kunde.getName());
            foundKunde.setTelmobile(kunde.getTelmobile());
            foundKunde.setGeschlecht(kunde.getGeschlecht());
            foundKunde.setAustritt(kunde.getAustritt());
            kundenRepository.save(foundKunde);
        } catch (Exception ex) {
            return "Error updating the Kunde: " + ex.toString();
        }
        return "Kunde succesfully updated!";
    }

    public Kunde getKundeByID(String id) {
        return kundenRepository.findById(id);
    }

    public Kunde getKundeByAusweisnr(String ausweisnr) {
        return kundenRepository.findByAusweisnr(ausweisnr);
    }

    public Kunde getKundeByNameAndEmail(String email, String name) {
        return kundenRepository.findByEmailAndName(email, name);
    }

    public Kunde getKundeByEmail(String email) {
        return kundenRepository.findByEmail(email);
    }

    public Iterable<Kunde> getKundenByName(String kundenName) {
        return kundenRepository.findKundenByName(kundenName);
    }

    public Iterable<Kunde> getAllKunden() {
        return kundenRepository.findAll();
    }
}
