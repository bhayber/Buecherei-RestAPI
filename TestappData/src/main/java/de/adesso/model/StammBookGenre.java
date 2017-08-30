package de.adesso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "demo_StammBookGenre")
public class StammBookGenre extends EntityBase {

    @Column(name = "bezeichnung", nullable = false)
    private String bezeichnung;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }


}
