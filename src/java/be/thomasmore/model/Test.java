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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t"),
    @NamedQuery(name = "Test.findById", query = "SELECT t FROM Test t WHERE t.id = :id"),
    @NamedQuery(name = "Test.findByBeschrijving", query = "SELECT t FROM Test t WHERE t.beschrijving = :beschrijving"),
    @NamedQuery(name = "Test.findByTotaalScore", query = "SELECT t FROM Test t WHERE t.totaalScore = :totaalScore"),})
public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "Beschrijving")
    private String beschrijving;
    @Column(name = "TotaalScore")
    private Integer totaalScore;
    @JoinColumn(name = "VakId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    public Vak vakId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testId")
    private List<Score> scoreList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testId")
    private List<Klastest> klastestList;

    public Test() {
    }

    public Test(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Integer getTotaalScore() {
        return totaalScore;
    }

    public void setTotaalScore(Integer totaalScore) {
        this.totaalScore = totaalScore;
    }

    public Vak getVakId() {
        return vakId;
    }

    public void setVakId(Vak vakId) {
        this.vakId = vakId;
    }

    @XmlTransient
    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
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
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.thomasmore.model.Test[ id=" + id + " ]";
    }
    
}
