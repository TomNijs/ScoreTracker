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
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
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
    
    public String sendEmail() throws EmailException{
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("studentId");
        int studentId = Integer.parseInt(id);
        Student student = service.getStudent(studentId);
        HtmlEmail email = new HtmlEmail();
 
   
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("pointernulltest@gmail.com", "r0449914"));
        email.setSSLOnConnect(true);
        email.addTo(student.getEmail(), student.getNaam() + " " + student.getVoornaam());
        email.setFrom("me@apache.org", "Me");
        email.setSubject("Rapport");
        email.setTextMsg("Test");
           
        email.send();
        return null;
    }
    
    
}
