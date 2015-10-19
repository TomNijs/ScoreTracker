/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.thomasmore.controller;

import be.thomasmore.model.Klas;
import be.thomasmore.model.Student;
import be.thomasmore.model.Test;
import be.thomasmore.service.DefaultService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;


/**
 *
 * @author Tom
 */
@ManagedBean(name = "defaultController")
@ViewScoped
public class DefaultController implements Serializable{
    @EJB
    private DefaultService service;
    
    //Dit is een test om te zien of database werkt
    public List<Student> getStudenten(){
        return service.getStudenten();
    }
    
    public List<Test> getTesten(){
        return service.getTesten();
    }
    
    public Klas getKlas(ValueChangeEvent e){
        return service.getKlas(Integer.parseInt(e.getNewValue().toString()));
    }
    
    public List<Klas> getKlassen(){
        return service.getKlassen();
    }
    
    
}
