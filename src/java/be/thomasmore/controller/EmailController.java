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
//import org.apache.commons.mail.*;



/**
 *
 * 
 */
@ManagedBean(name = "EmailController")
@ViewScoped
public class EmailController implements Serializable{
    @EJB
    private DefaultService service;
    
    
    
    
    
}
