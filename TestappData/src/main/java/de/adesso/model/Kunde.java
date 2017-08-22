package de.adesso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "demo_Kunde")
public class Kunde extends Person {

    //BÜCHEREI AUSWEIS NR
    @Column(name = "ausweisnr", nullable = false, unique = true)
    private String ausweisnr;

    @Column(name = "mitgliedSeit")
    private Date mitgliedSeit;

    @Column(name = "austritt")
    private Date austritt;

    public String getAusweisnr() {
        return ausweisnr;
    }

    public void setAusweisnr(String ausweisnr) {
        this.ausweisnr = ausweisnr;
    }

    public Date getMitgliedSeit() {
        return mitgliedSeit;
    }

    public void setMitgliedSeit(Date mitgliedSeit) {
        this.mitgliedSeit = mitgliedSeit;
    }

    public Date getAustritt() {
        return austritt;
    }

    public void setAustritt(Date austritt) {
        this.austritt = austritt;
    }

}
