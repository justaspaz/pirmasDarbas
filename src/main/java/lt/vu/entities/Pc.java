package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "PC.findAll", query = "select t from Pc as t")
})
@Getter @Setter
public class Pc implements Serializable {
    private Integer version;
    private Integer number;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String brand;

    @Column
    String pcName;

    @OneToMany(mappedBy = "pc")
    Set<Repair> repair;

    @Column(name = "VERSION")
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "NUMBER")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}