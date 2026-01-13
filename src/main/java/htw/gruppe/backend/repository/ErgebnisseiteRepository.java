package htw.gruppe.backend.repository;

import htw.gruppe.backend.entity.Gremium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//brauche die Entitaet

/**
 * Repository fuer die Daten der Ergebnisseite
 * @author Tabatt
 * @version 1.0
 */
@Repository
public interface ErgebnisseiteRepository extends JpaRepository<Gremium, Long> {

    @Query("""
    SELECT DISTINCT g
    FROM Gremium g
    LEFT JOIN FETCH g.wahllisten w
""")

    List<Gremium> findAllWithRelations();
}


//eigene Query schreiben? Join

