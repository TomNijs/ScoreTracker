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
import javax.ejb.EJB;

/**
 *
 * @author Rune
 */
public class PdfController {
  
    @EJB
    DefaultService service;
    public void createPdfKlas(int id) {
 
    Document document = new Document();
    Klas klas = service.getKlas(id);
    List<Klastest> klastesten = service.getKlastestenByKlasId(id);
    List<Test> testen = null;
    
    try {
        String docNaam = klas.getNummer() + "-resultaten.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(docNaam));

        document.open();
        
        Font font = FontFactory.getFont("Calibri");
        Font fontbold = FontFactory.getFont("Calibri", Font.BOLD);

        PdfPTable table = new PdfPTable(3); // 3 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph("Vak", fontbold));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Student", fontbold));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Behaald", fontbold));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
            for (Klastest klastest : klastesten) {
                Test test = klastest.getTestId();
                testen.add(service.getTest(test.getId()));
            }
            
            for (Test test : testen) {
                
            }

            document.add(new Phrase("Klas: ", fontbold));
            document.add(new Phrase(klas.getNummer(), font));
            document.add(table);

            document.close();
        } catch(Exception e){

        }
 
 }
    public void createPdfTest(int id, int klasId) {
 
    Document document = new Document();
    Test test = service.getTest(id);
    List<Score> scores = service.getScoresByTestId(id);
    Klas klas = service.getKlas(klasId);
    Vak vak = test.getVakId();
    
    try {
        String docNaam = test.getBeschrijving() + "-resultaten.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(docNaam));

        document.open();
        
        Font font = FontFactory.getFont("Calibri");
        Font fontbold = FontFactory.getFont("Calibri", Font.BOLD);

        PdfPTable table = new PdfPTable(3); // 3 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph("Test", fontbold));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Student", fontbold));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Score", fontbold));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
            for (Score score : scores) {
                Student student = service.getStudent(score.getId());
                PdfPCell cellTest = new PdfPCell(new Paragraph(test.getBeschrijving(), font));
                PdfPCell cellStudent = new PdfPCell(new Paragraph(student.getVoornaam(), font));
                PdfPCell cellScore = new PdfPCell(new Paragraph(score.getScore()));
            }

            document.add(new Phrase("Klas: ", fontbold));
            document.add(new Phrase(klas.getNummer(), font));
            document.add(new Phrase("  Vak: ", fontbold));
            document.add(new Phrase(vak.getNaam(), font));
            document.add(table);

            document.close();
        } catch(Exception e){

        }
 
 }
}

