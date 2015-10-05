/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.thomasmore.controller;

import be.thomasmore.model.Student;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tom
 */
@ManagedBean(name = "defaultController")
@ViewScoped
public class DefaultController implements Serializable{
    EntityManagerFactory emf = null;
    
    //Dit is een test om te zien of database werkt
    public List<Student> getAllStudents(){
        emf = Persistence.createEntityManagerFactory("ScoreTrackerPU");
        EntityManager em = emf.createEntityManager();     
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
        List<Student> studenten = query.getResultList();
        return studenten;
    }
}
