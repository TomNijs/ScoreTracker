/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.thomasmore.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tom
 */
@Entity
@Table(name = "klastest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klastest.findAll", query = "SELECT k FROM Klastest k"),
    @NamedQuery(name = "Klastest.findByKlasId", query = "SELECT k FROM Klastest k WHERE k.klasId = :id"), 
    @NamedQuery(name = "Klastest.findById", query = "SELECT k FROM Klastest k WHERE k.id = :id")})
    
public class Klastest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "Test_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Test testId;
    @JoinColumn(name = "Klas_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Klas klasId;

    public Klastest() {
    }

    public Klastest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test testId) {
        this.testId = testId;
    }

    public Klas getKlasId() {
        return klasId;
    }

    public void setKlasId(Klas klasId) {
        this.klasId = klasId;
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
        if (!(object instanceof Klastest)) {
            return false;
        }
        Klastest other = (Klastest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.thomasmore.model.Klastest[ id=" + id + " ]";
    }
    
}
