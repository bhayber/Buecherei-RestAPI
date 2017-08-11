package de.adesso.Controller;

import de.adesso.Service.KundenService;
import de.adesso.Service.PersonService;
import de.adesso.model.Kunde;
import de.adesso.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kunde")
public class KundenController {

    @Autowired
    private KundenService kundenService;

    @PostMapping("/createKunde")
    public String createKunde(Kunde kunde) {
        return kundenService.createKunde(kunde);
    }

    /**
     * GET /delete  --> Delete the Person having the passed id.
     */
    @DeleteMapping("/deleteKunde/{id}")
    @ResponseBody
    public String delete(@PathVariable String id) {
        return kundenService.delete(id);
    }

    /**
     * GET /get-by-email  --> Return the id for the Person having the passed
     * email.
     */
    @GetMapping("/getKundebyNameAndEmail")
    @ResponseBody
    public Kunde getKundebyNameAndEmail(Kunde kunde) {
        return kundenService.getKundeByNameAndEmail(kunde.getEmail(),kunde.getName());
    }

    /**
     * GET /update  --> Update the email and the name for the Person in the
     * database having the passed id.
     */
    @PutMapping("/updateKunde")
    @ResponseBody
    public String updateKunde(Kunde kunde) {return kundenService.updateKunde(kunde);
    }

    /**
     * GET /all  --> Gets all Person Information
     */
    @GetMapping("/getall")
    @ResponseBody
    public Iterable<Kunde> getAllKunden() {
        return kundenService.getAllKunden();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Kunde getKundeById(@PathVariable("id") String id) {
        Kunde kunde = kundenService.getKundeByID(id);
        return kunde;
    }




}
