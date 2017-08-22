package de.adesso.Repository;

import de.adesso.model.Verlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface VerlagRepository extends JpaRepository<Verlag, Integer> {

    Verlag findById(String id);

    Set<Verlag> findVerlaegeByNameOrderByNameDesc(String name);

    Verlag findVerlagByName(String name);

}