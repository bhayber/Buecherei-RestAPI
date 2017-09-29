package de.adesso.Repository;

import de.adesso.model.Bereich;
import de.adesso.model.Bibliothikar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface BibliothikarRepository extends JpaRepository<Bibliothikar, Integer> {

    Bibliothikar findById(String id);

    Set<Bibliothikar> findByBereich(Bereich bereich);

    Set<Bibliothikar> findByobjektAdresse(Bereich bereich);

}