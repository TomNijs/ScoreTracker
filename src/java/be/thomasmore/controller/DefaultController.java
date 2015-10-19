/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.thomasmore.controller;

import be.thomasmore.model.Klas;
import be.thomasmore.model.Student;
import be.thomasmore.model.Test;
import be.thomasmore.model.Vak;
import be.thomasmore.service.DefaultService;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



/**
 *
 * @author Tom
 */
@ManagedBean(name = "defaultController")
@ViewScoped
public class DefaultController implements Serializable{
    @EJB
    private DefaultService service;
    public Vak selectedvak;  
    public List<Test> testen;
    
    @PostConstruct
    public void init() {
        
        setTesten();
    }
    public DefaultService getService() {
        return service;
    }

    public void setService(DefaultService service) {
        this.service = service;
    }
    
    //Dit is een test om te zien of database werkt
    public List<Student> getStudenten(){
        return service.getStudenten();
    }
    
    public void setTesten(){
        this.testen = service.getTesten();
    }
    
    public List<Test> getTesten(){
        return this.testen;
    }
    
    public Vak getSelectedvak() {
        return selectedvak;
    }

    public void setSelectedvak(Vak selectedvak) {
        this.selectedvak = selectedvak;
    }
    
    public Klas getKlas(int id){
        return service.getKlas(id);
    }
    
    public List<Klas> getKlassen(){
        return service.getKlassen();
    }
    
    public List<Vak> getVakken() {
        return service.getVakken();
    }
    
    public Vak getVak(int id){
        return service.getVak(id);
    }
    
    public void onVakChange(){
        for(Test test : testen){            
            if(!Objects.equals(test.vakId.getId(), selectedvak.getId()) ){
                testen.remove(test);
            }
        }
    }
    
    
}
