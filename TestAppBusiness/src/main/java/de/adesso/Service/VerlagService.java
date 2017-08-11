package de.adesso.Service;

import de.adesso.Repository.VerlagRepository;
import de.adesso.model.Verlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerlagService {

    @Autowired
    private VerlagRepository verlagRepository;

    public VerlagService(VerlagRepository verlagRepository) {
        this.verlagRepository = verlagRepository;
    }

    public Verlag findVerlagByName(String verlagName) {
        return verlagRepository.findVerlagByName(verlagName);
    }

    public String createVerlag(Verlag verlag) {
        Verlag newVerlag = new Verlag();
        try {
            newVerlag.setName(verlag.getName());
            newVerlag.setAdresse(verlag.getAdresse());
            verlagRepository.save(newVerlag);
        } catch (Exception ex) {
            return "Error creating the Verlag: " + ex.toString() + newVerlag.getId();
        }
        return "Verlag succesfully created with id = " + newVerlag.getId();
    }
}
