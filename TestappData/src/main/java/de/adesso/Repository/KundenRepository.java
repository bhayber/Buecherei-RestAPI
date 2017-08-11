package de.adesso.Repository;

import de.adesso.model.Kunde;
import de.adesso.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface KundenRepository extends JpaRepository<Kunde,Integer>{

    public Kunde findByAusweisnr(String ausweisnr);

    public Kunde findById(String id);

    @Query("select u from Kunde u where u.email = ?1")
    public Iterable<Kunde> findByEmail(String email);

    @Query("select u from Kunde u where u.ausweisnr != null")
    public Iterable<Kunde> findAllKunden();

    @Query("select u from Kunde u where u.name = ?1")
    public Iterable<Kunde> findKundenByName(String kundeName);

   public Kunde findByEmailAndName(String email, String name);



}
