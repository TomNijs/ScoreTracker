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
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rune
 */
@ManagedBean(name = "PdfController")
@ViewScoped
public class PdfController {
  
    @EJB
    DefaultService service;
    public void createPdfKlas() {
    
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String KlasId = params.get("klasId");
    int id = Integer.parseInt(KlasId);
    Document document = new Document();
    Klas klas = service.getKlas(id);
    List<Klastest> klastesten = klas.getKlastestList();
    List<Test> testen = null;
    
    HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();    
    res.setHeader("Content-Disposition", "attachement; filename=" + klas.getNummer() + "-resultaten.pdf");
    res.setContentType("application/pdf");
    
    try {
        PdfWriter.getInstance(document, res.getOutputStream());

        document.open();
        
        Font font = FontFactory.getFont("Calibri");
        Font fontbold = FontFactory.getFont("Calibri", Font.BOLD);

        PdfPTable table = new PdfPTable(3); // 3 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph("Vak", font));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Student", font));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Behaald", font));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
            for (Klastest klastest : klastesten) {
                Test test = klastest.getTestId();
                testen.add(service.getTest(test.getId()));
            }
            
            for (Test test : testen) {
                List<Score> scores = test.getScoreList();
                Vak vak = test.getVakId();
                for (Score score : scores) {
                    Student student = score.getStudentId();
                    PdfPCell cellVak = new PdfPCell(new Paragraph(vak.getNaam(), font));
                    PdfPCell cellStudent = new PdfPCell(new Paragraph(student.getVoornaam(), font));
                    PdfPCell cellScore = new PdfPCell(new Paragraph(score.getScore().toString(), font));
                
                    table.addCell(cellVak);
                    table.addCell(cellStudent);
                    table.addCell(cellScore);
                }
            }

            document.add(new Phrase("Klas: ", font));
            document.add(new Phrase(klas.getNummer(), font));
            document.add(table);

            document.close();
        } catch(Exception e){

        }
 
 }
    public void createPdfTest() {
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String KlasId = params.get("klasId3");
    String TestId = params.get("testId");
    int klasId = Integer.parseInt(KlasId);
    int id = Integer.parseInt(TestId);
    Document document = new Document();
    Test test = service.getTest(id);
    List<Score> scores = test.getScoreList();
    Klas klas = service.getKlas(klasId);
    Vak vak = test.getVakId();
    
    HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();    
    res.setHeader("Content-Disposition", "attachement; filename=" + test.getBeschrijving() + "-resultaten.pdf");
    res.setContentType("application/pdf");
    
    try {
        PdfWriter.getInstance(document, res.getOutputStream());

        document.open();
        
        Font font = FontFactory.getFont("Calibri");
        Font fontbold = FontFactory.getFont("Calibri", Font.BOLD);

        PdfPTable table = new PdfPTable(3); // 3 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph("Test", font));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Student", font));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Score", font));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
            for (Score score : scores) {
                Student student = service.getStudent(score.getId());
                PdfPCell cellTest = new PdfPCell(new Paragraph(test.getBeschrijving(), font));
                PdfPCell cellStudent = new PdfPCell(new Paragraph(student.getVoornaam(), font));
                PdfPCell cellScore = new PdfPCell(new Paragraph(score.getScore().toString()));
                
                table.addCell(cellTest);
                table.addCell(cellStudent);
                table.addCell(cellScore);
            }

            document.add(new Phrase("Klas: ", font));
            document.add(new Phrase(klas.getNummer(), font));
            document.add(new Phrase("  Vak: ", font));
            document.add(new Phrase(vak.getNaam(), font));
            document.add(table);

            document.close();
        } catch(Exception e){

        }
 
 }
    
    public void createPdfVak() {
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String KlasId = params.get("klasId2");
    String VakId = params.get("vakId");
    int klasId = Integer.parseInt(KlasId);
    int id = Integer.parseInt(VakId);
    Document document = new Document();
    Vak vak = service.getVak(id);
    List<Test> testen = vak.getTestList();
    Klas klas = service.getKlas(klasId);
    
    HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();    
    res.setHeader("Content-Disposition", "attachement; filename=" + vak.getNaam() + "-resultaten.pdf");
    res.setContentType("application/pdf");
    
    try {
        PdfWriter.getInstance(document, res.getOutputStream());

        document.open();
        
        Font font = FontFactory.getFont("Calibri");
        Font fontbold = FontFactory.getFont("Calibri", Font.BOLD);

        PdfPTable table = new PdfPTable(3); // 3 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph("Test", font));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Student", font));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Score", font));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
            for (Test test : testen) {
                List<Score> scores = test.getScoreList();
                for (Score score : scores) {
                    Student student = score.getStudentId();
                    PdfPCell cellTest = new PdfPCell(new Paragraph(test.getBeschrijving(), font));
                    PdfPCell cellStudent = new PdfPCell(new Paragraph(student.getVoornaam(), font));
                    PdfPCell cellScore = new PdfPCell(new Paragraph(score.getScore().toString()));
                    
                    table.addCell(cellTest);
                    table.addCell(cellStudent);
                    table.addCell(cellScore);
                }
            }

            document.add(new Phrase("Klas: ", font));
            document.add(new Phrase(klas.getNummer(), font));
            document.add(new Phrase("  Vak: ", font));
            document.add(new Phrase(vak.getNaam(), font));
            document.add(table);

            document.close();
        } catch(Exception e){

        }    
    }
    public void createPdfStudent() {
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String StudentId = params.get("studentId");
    int id = Integer.parseInt(StudentId);
    Document document = new Document();
    Student student = service.getStudent(id);
    List<Score> scores = student.getScoreList();
    Klas klas = student.getKlasId();
    
    HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();    
    res.setHeader("Content-Disposition", "attachement; filename=" + student.getNaam() + student.getVoornaam() + "-resultaten.pdf");
    res.setContentType("application/pdf");
    
    try {
        PdfWriter.getInstance(document, res.getOutputStream());

        document.open();
        
        Font font = FontFactory.getFont("Calibri");

        PdfPTable table = new PdfPTable(3); // 3 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph("Vak", font));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Test", font));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Score", font));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
            for (Score score : scores) {
                Test test = score.getTestId();
                PdfPCell cellVak = new PdfPCell(new Paragraph(test.getVakId().getNaam(), font));
                PdfPCell cellTest = new PdfPCell(new Paragraph(test.getBeschrijving(), font));
                PdfPCell cellScore = new PdfPCell(new Paragraph(score.getScore().toString()));
                
                table.addCell(cellVak);
                table.addCell(cellTest);
                table.addCell(cellScore);
            }

            document.add(new Phrase("Student: ", font));
            document.add(new Phrase((student.getNaam() + " " + student.getVoornaam()), font));
            document.add(new Phrase(  "Klas: ", font));
            document.add(new Phrase(klas.getNummer(), font));
            document.add(table);

            document.close();
        } catch(Exception e){

        }
 
 }
}

