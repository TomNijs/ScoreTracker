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
 * @author Sam
 */
@ManagedBean(name = "defaultController")
@ViewScoped
public class DefaultController implements Serializable{
    @EJB
    private DefaultService service;
    public Vak selectedVak;  
    public Klas selectedKlas;
    public Klastest selectedKlasTest;
    public Student selectedStudent;
    public double totaalVakScore;
    public double totaalScore;
    
    public void removeScore(Score score){
        this.service.removeScore(score);
    }
    
    public DefaultService getService() {
        return service;
    }

    public void setService(DefaultService service) {
        this.service = service;
    }
    
    public List<Student> getStudenten(){
        if(this.selectedKlas != null){
            return this.selectedKlas.getStudentList();
        }else{
            return service.getStudenten();
        }
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
        return service.getVakken();
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
    
    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
    
    public Klastest getSelectedKlasTest() {
        return selectedKlasTest;
    }

    public void setSelectedKlasTest(Klastest selectedKlasTest) {
        this.selectedKlasTest = selectedKlasTest;
    }
    
    public List<Score> getScores() {
        if(this.selectedKlasTest != null ){
            return selectedKlasTest.getTestId().getScoreList();
        }else{
            return service.getScores();
        }
    }
    
    public double getTotaalVakScore() {
        return totaalVakScore;
    }

    public void setTotaalVakScore(double totaalVakScore) {
        this.totaalVakScore = totaalVakScore;
        this.totaalScore += totaalVakScore;
    }
    
    public double getTotaalScore() {
        return totaalScore/getVakkenByStudent().size();
    }

    public void setTotaalScore(double totaalScore) {
        this.totaalScore = totaalScore;
    }

    public double getTestGemiddelde() {
        double gemiddelde = 0;
        double sub = 0;
        if (this.selectedKlasTest != null){
            Klastest klastest = this.selectedKlasTest;
            for(Score score1 : klastest.getTestId().getScoreList()){
                gemiddelde += score1.getScore();
                sub += klastest.getTestId().getTotaalScore();
            }
            gemiddelde = (gemiddelde/sub)*100;
        }
        return gemiddelde;
    }

    public List<Vak> getVakkenByKlasTest() {
        List<Vak> vakken = new ArrayList<>(service.getVakken());
        List<Vak> vakkensend = new ArrayList<>(service.getVakken());

        List<Klastest> klastesten = getTestenByVak();
        
        //selected klas
        for(Vak vak: vakken){
            for(Klastest klastest: klastesten){
                //nog toevoegen als er geen testen zijn voor klas
                if (!vak.getTestList().contains(klastest.getTestId())) {
                    vakkensend.remove(vak);
                }
            }
            
        }
        
        return vakkensend;
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
    
    public int getTotaalBehaaldeScore(){
        int totaalBehaaldeScore = 0;
        if(this.selectedStudent != null){
            Student selectedStudent = this.selectedStudent;
            for(Score score1 : selectedStudent.getScoreList()){
                totaalBehaaldeScore += score1.getScore();
            }
        }
        
        return totaalBehaaldeScore;     
    }
    
    public List<Vak> getVakkenByStudent(){
        this.setTotaalScore(0);
        List<Vak> vakken = new ArrayList<Vak>();
        if(this.selectedStudent!=null){
            Student selectedStudent = this.selectedStudent;
            for(Score score1 : selectedStudent.getScoreList()){
                if (!vakken.contains(score1.getTestId().getVakId())) {
                    vakken.add(score1.getTestId().getVakId());
                }
            }
            
        }
        return vakken;
    }
    
    public List<Score> getScoresByVak(Vak vak){
        List<Score> scores = new ArrayList<Score>();
        double subtotaal = 0;
        double subtotaal2 = 0;
        double teller = 0;
        if(this.selectedStudent!=null){
            Student selectedStudent = this.selectedStudent;    
            for(Score score1 : selectedStudent.getScoreList()){
                if(score1.getTestId().getVakId().getId() == vak.getId()){
                    scores.add(score1);
                    subtotaal += score1.getScore();
                    subtotaal2 += score1.getTestId().getTotaalScore();
                    teller++;
                } 
                
            }
            this.setTotaalVakScore((subtotaal/subtotaal2)*100);
        }
        return scores;
    }
    
    
    
}
