package de.adesso.Repository;

import de.adesso.model.Verlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface VerlagRepository extends JpaRepository<Verlag,Integer>{

 public Verlag findById(String id);

 public Set<Verlag> findVerlaegeByNameOrderByNameDesc(String name);

 public Verlag findVerlagByName(String name);

}