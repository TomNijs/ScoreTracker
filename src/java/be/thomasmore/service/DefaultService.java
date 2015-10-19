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

/**
 *
 * @author Tom
 */
public interface DefaultService {
    
    //Methodes om van een entity een enkele record op te vragen
    public Klas getKlas(int id);
    public Klastest getKlastest(int id);
    public Score getScore(int id);
    public Student getStudent(int id);
    public Test getTest(int id);
    public Vak getVak(int id);
    
    //Methodes om van een entity alle records op te vragen
    public List<Klas> getKlassen();
    public List<Klastest> getKlastesten();
    public List<Score> getScores();
    public List<Student> getStudenten();
    public List<Test> getTesten();
    public List<Vak> getVakken();
    public List<Test> getTestenByVakId(int vakId);
    
    //Methodes om een entity te verwijderen
    public void removeKlas(Klas klas);
    public void removeKlastest(Klastest klastest);
    public void removeScore(Score score);
    public void removeStudent(Student student);
    public void removeTest(Test test);
    public void removeVak(Vak vak);
    
    //Methodes om een entity toe te voegen
    public void addKlas(Klas klas);
    public void addKlastest(Klastest klastest);
    public void addScore(Score score);
    public void addStudent(Student student);
    public void addTest(Test test);
    public void addVak(Vak vak);
}
