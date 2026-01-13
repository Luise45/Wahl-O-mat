package htw.gruppe.backend.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@Entity
@Table(name = "gremium")
public class Gremium {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gremium_id")
    private Long id;


    @Column(nullable = false, unique = true)
    private String name;


   @OneToMany(mappedBy = "gremium")
   @Fetch(FetchMode.SUBSELECT)
    private List<Wahlliste> wahllisten;


    public Gremium() {
    }


    public Gremium(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public List<Wahlliste> getWahllisten() {
        return wahllisten;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setWahllisten(List<Wahlliste> wahllisten) {
        this.wahllisten = wahllisten;
    }
}

