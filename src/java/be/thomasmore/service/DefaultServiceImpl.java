/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.thomasmore.service;

import be.thomasmore.model.Klas;
import be.thomasmore.model.Klastest;
import be.thomasmore.model.Score;
import be.thomasmore.model.Student;
import be.thomasmore.model.Test;
import be.thomasmore.model.Vak;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tom
 */
@Stateless
public class DefaultServiceImpl implements DefaultService{

    //EntityManager aanmaken om te communiceren met de DB
    //PersistenceContext is een annotation die hiermee gepaart gaat
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Klas getKlas(int id) {
        Query q = em.createNamedQuery("Klas.findById", Klas.class);
        q.setParameter("id", id);
        return (Klas)q.getSingleResult();
    }

    @Override
    public Klastest getKlastest(int id) {
        Query q = em.createNamedQuery("Klastest.findById", Klastest.class);
        q.setParameter("id", id);
        return (Klastest)q.getSingleResult();
    }

    @Override
    public Score getScore(int id) {
        Query q = em.createNamedQuery("Score.findById", Score.class);
        q.setParameter("id", id);
        return (Score)q.getSingleResult();
    }

    @Override
    public Student getStudent(int id) {
        Query q = em.createNamedQuery("Student.findById", Student.class);
        q.setParameter("id", id);
        return (Student)q.getSingleResult();
    }

    @Override
    public Test getTest(int id) {
        Query q = em.createNamedQuery("Test.findById", Test.class);
        q.setParameter("id", id);
        return (Test)q.getSingleResult();
    }

    @Override
    public Vak getVak(int id) {
        Query q = em.createNamedQuery("Vak.findById", Vak.class);
        q.setParameter("id", id);
        return (Vak)q.getSingleResult();
    }

    @Override
    public List<Klas> getKlassen() {
        Query q = em.createNamedQuery("Klas.findAll", Klas.class);
        return (List<Klas>)q.getResultList();
    }

    @Override
    public List<Klastest> getKlastesten() {
        Query q = em.createNamedQuery("Klastest.findAll", Klastest.class);
        return (List<Klastest>)q.getResultList();
    }

    @Override
    public List<Score> getScores() {
        Query q = em.createNamedQuery("Score.findAll", Score.class);
        return (List<Score>)q.getResultList();
    }

    @Override
    public List<Student> getStudenten() {
        Query q = em.createNamedQuery("Student.findAll", Student.class);
        return (List<Student>)q.getResultList();
    }

    @Override
    public List<Test> getTesten() {
        Query q = em.createNamedQuery("Test.findAll", Test.class);
        return (List<Test>)q.getResultList();
    }

    @Override
    public List<Vak> getVakken() {
        Query q = em.createNamedQuery("Vak.findAll", Vak.class);
        return (List<Vak>)q.getResultList();
    }
    
    @Override
    public List<Klastest> getKlastestenByKlasId(int id) {
        Query q = em.createNamedQuery("Klastest.findByKlasId", Klastest.class);
        q.setParameter("id", id);
        return (List<Klastest>)q.getResultList();
    }
    
    @Override
    public List<Score> getScoresByTestId(int id) {
        Query q = em.createNamedQuery("Score.findScoreByScoreId", Score.class);
        q.setParameter("id", id);
        return (List<Score>)q.getResultList();
    }
    
    @Override
    public List<Test> getTestenByVakId(int id) {
        Query q = em.createNamedQuery("Test.findByVak", Test.class);
        q.setParameter("id", id);
        return (List<Test>)q.getResultList();
    }

    @Override
    public void removeKlas(Klas klas) {
        em.remove(klas);
    }

    @Override
    public void removeKlastest(Klastest klastest) {
        em.remove(klastest);
    }

    @Override
    public void removeScore(Score score) {
        em.remove(score);
    }

    @Override
    public void removeStudent(Student student) {
        em.remove(student);
    }

    @Override
    public void removeTest(Test test) {
        em.remove(test);
    }

    @Override
    public void removeVak(Vak vak) {
        em.remove(vak);
    }

    @Override
    public void addKlas(Klas klas) {
        em.persist(klas);
    }

    @Override
    public void addKlastest(Klastest klastest) {
        em.persist(klastest);
    }

    @Override
    public void addScore(Score score) {
        em.persist(score);
    }

    @Override
    public void addStudent(Student student) {
        em.persist(student);
    }

    @Override
    public void addTest(Test test) {
        em.persist(test);
    }

    @Override
    public void addVak(Vak vak) {
        em.persist(vak);
    }
}
