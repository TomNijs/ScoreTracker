/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.thomasmore.controller;

import be.thomasmore.model.Klas;
import be.thomasmore.model.Klastest;
import be.thomasmore.model.Score;
import be.thomasmore.model.Student;
import be.thomasmore.model.Test;
import be.thomasmore.model.Vak;
import be.thomasmore.service.DefaultService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.selectitems.SelectItemsBuilder;


/**
 *
 * @author Tom
 */
@ManagedBean(name = "defaultController")
@ViewScoped
public class DefaultController implements Serializable{
    @EJB
    private DefaultService service;
    public Vak selectedVak;  
    public Klas selectedKlas;
    public Klastest selectedKlasTest;

    public DefaultService getService() {
        return service;
    }

    public void setService(DefaultService service) {
        this.service = service;
    }
    
    public List<Student> getStudenten(){
        return service.getStudenten();
    }
    
    public List<Test> getTesten(){
        return service.getTesten();
    }
   
    public Klas getKlas(int id){
        return service.getKlas(id);
    }
    
    public List<Klas> getKlassen(){
        return service.getKlassen();
    }
    
    public List<Vak> getVakken() {
        List<Vak> vakken = new ArrayList<>(service.getVakken());
        List<Vak> vakkensend = new ArrayList<>(service.getVakken());
        
        //checken welke klas en zien hoeveel hoeveel klastesten er zijn voor vak
        //
        List<Klastest> klastesten = getTestenByVak();
        
        //selected klas
        for(Vak vak: vakken){
            for(Klastest klastest: klastesten){
                if (!vak.getTestList().contains(klastest.getTestId())) {
                    vakkensend.remove(vak);
                }
            }
            
        }
        
        return vakkensend;
    }
    
    public Vak getSelectedVak() {
        return selectedVak;
    }

    public void setSelectedVak(Vak selectedVak) {
        this.selectedKlasTest = null;
        this.selectedVak = selectedVak;
    }
    
    public Klas getSelectedKlas() {
        return selectedKlas;
    }

    public void setSelectedKlas(Klas selectedKlas) {
        this.selectedVak = null;
        this.selectedKlasTest = null;
        this.selectedKlas = selectedKlas;
    }
    
    public Klastest getSelectedKlasTest() {
        return selectedKlasTest;
    }

    public void setSelectedKlasTest(Klastest selectedKlasTest) {
        this.selectedKlasTest = selectedKlasTest;
    }
    
    public List<Score> getScores() {
        return service.getScores();
    }

    
    public List<Klastest> getTestenByVak(){
        
        List<Klastest> klasTestenSend = new ArrayList<>(service.getKlastesten());
        List<Klastest> alleKlasTesten = new ArrayList<>(service.getKlastesten());
        //selectedklas filteren
        if (this.selectedKlas!=null) {
            Klas klas = this.getSelectedKlas();
            for (Klastest klastest : alleKlasTesten) {
                if (klastest.getKlasId().getId() != klas.getId()) {
                    klasTestenSend.remove(klastest);
                }
            }
        }
        //selectedvak fileren
        if (this.selectedVak!=null) {
            Vak vak = this.getSelectedVak();
            for (Klastest klastest : alleKlasTesten) {
                if (klastest.getTestId().getVakId().getId() != vak.getId()) {
                    klasTestenSend.remove(klastest);
                }
            }
        }
        return klasTestenSend;   
    }
    
    
    
}
