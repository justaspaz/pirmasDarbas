package lt.vu.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@Getter @Setter
public class Repair implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String description;

    @Column
    int cost;

    @ManyToOne
    @JoinColumn(name="pc_id")
    Pc pc;

    @ManyToMany
    @JoinTable(
            name = "pc_repair_module",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "pcmodule_id"))
    Set<Pcmodules> pcmodules;
}