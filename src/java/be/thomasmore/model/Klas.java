/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.thomasmore.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tom
 */
@Entity
@Table(name = "klas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klas.findAll", query = "SELECT k FROM Klas k"),
    @NamedQuery(name = "Klas.findById", query = "SELECT k FROM Klas k WHERE k.id = :id"),
    @NamedQuery(name = "Klas.findByNummer", query = "SELECT k FROM Klas k WHERE k.nummer = :nummer")})
public class Klas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "Nummer")
    private String nummer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klasId")
    private List<Student> studentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klasId")
    private List<Klastest> klastestList;

    public Klas() {
    }

    public Klas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @XmlTransient
    public List<Klastest> getKlastestList() {
        return klastestList;
    }

    public void setKlastestList(List<Klastest> klastestList) {
        this.klastestList = klastestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klas)) {
            return false;
        }
        Klas other = (Klas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.thomasmore.model.Klas[ id=" + id + " ]";
    }
    
}
