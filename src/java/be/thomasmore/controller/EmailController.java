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
@ManagedBean(name = "EmailController")
@ViewScoped
public class EmailController implements Serializable{
    @EJB
    private DefaultService service;
    
    //Dit is een test om te zien of database werkt
    
    
    
}
