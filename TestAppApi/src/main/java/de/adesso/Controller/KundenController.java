package de.adesso.Controller;

import de.adesso.Service.KundenService;
import de.adesso.model.Kunde;
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


    @DeleteMapping("/deleteKundeByAusweisNr/{ausweisnr}")
    @ResponseBody
    public String deleteByAusweisNr(@PathVariable String ausweisnr) {
        return kundenService.deleteByAusweisNr(ausweisnr);
    }


    @GetMapping("/getKundebyNameAndEmail")
    @ResponseBody
    public Kunde getKundebyNameAndEmail(Kunde kunde) {
        return kundenService.getKundeByNameAndEmail(kunde.getEmail(), kunde.getName());
    }

    @GetMapping("/getKundebyAusweisNr")
    @ResponseBody
    public Kunde getKundebyAusweisNr(String ausweisnr) {
        return kundenService.getKundeByAusweisnr(ausweisnr);
    }

    @GetMapping("/getKundebyEmail")
    @ResponseBody
    public Kunde getKundebyEmail(String email) {
        return kundenService.getKundeByEmail(email);
    }


    /**
     * GET /update  --> Update the email and the name for the Person in the
     * database having the passed id.
     */
    @PutMapping("/updateKunde")
    @ResponseBody
    public String updateKunde(Kunde kunde) {
        return kundenService.updateKunde(kunde);
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
