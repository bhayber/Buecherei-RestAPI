package de.adesso.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.adesso.model.Kunde;

@Repository
@Transactional
public interface KundenRepository extends JpaRepository<Kunde, Integer> {

	Kunde findByAusweisnr(String ausweisnr);

	Kunde findById(String id);

	Kunde findByEmail(String email);

	@Query("select u from Kunde u where u.ausweisnr IS NOT NULL")
	Iterable<Kunde> findAllKunden();

	Iterable<Kunde> findKundenByName(String kundeName);

	Kunde findByEmailAndName(String email, String name);

}
