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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

/**
 * Deze controller zal het uploaden en inlezen van excelbestanden afhandelen
 * @author Tom
 */
@ManagedBean(name = "fileController")
@SessionScoped
public class FileController {
    
    //variabelen voor gegevens die uitgelezen gaan worden instantiëren
    List<Student> studenten = new ArrayList();
    List<Score> scores = new ArrayList();
    Test test = new Test();
    Vak vak = new Vak();
    Klas klas = new Klas();
    Klastest klasTest = new Klastest();
    
    //Part variabele zal het upgeloade bestand bevatten
    private Part part;
    
    public Part getPart() {
        return part;
    }
    public void setPart(Part part) {
        this.part = part;
    }
    
    //EJB instantie aanmaken om de DB methodes aan te spreken
    @EJB
    private DefaultService defaultService;
    
    //Methode om de bestandsnaam uit het bestand te halen
    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("***** partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    public String uploadFile() throws IOException {
        
        //De bestandsnaam uit het bestand (part) halen
        String fileName = getFileName(part);
        System.out.println("***** fileName: " + fileName);
        
        String basePath = "C:" + File.separator + "data" + File.separator;
        File outputFilePath = new File(basePath + fileName);
        
        //Streams aanmaken om het upgeloade bestand naar de destination te kopiëren
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = part.getInputStream();
            outputStream = new FileOutputStream(outputFilePath);
            
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            
        } finally {
          if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }  
        }
        //leesExcel(basePath + fileName);
        
        return null;
    }

    
}
