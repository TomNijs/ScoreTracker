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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Deze controller zal het uploaden en inlezen van excelbestanden afhandelen
 *
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

    private void leesExcel(String path) {
        try {
            //Excelbestand in RAM steken voor Apache POI
            FileInputStream fileInputStream = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheet("Blad1");

            //Iterator om door de worksheets te gaan (enkel nodig om het eerste worksheet door te gaan)
            Iterator<Row> rowIterator = worksheet.iterator();

            //Klas zoeken en persisten
            //Door de rijen itereren
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //Over de kolommen van deze rij itereren
                Iterator<Cell> cellIterator = row.cellIterator();
                if (row.getRowNum() == 0) {
                    while (cellIterator.hasNext()) {
                        //Cell vastnemen
                        Cell cell = cellIterator.next();
                        //Kijken of er in de eerste cell 'klas' staat
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                if (cell.getStringCellValue().equalsIgnoreCase("klas")) {
                                    //breaken zodat hij doorgaat naar de volgende cell
                                    break;
                                    //Checken of de cell niet leeg is
                                } else if (!cell.getStringCellValue().equalsIgnoreCase("")) {
                                    if (klas.getNummer() == null) {
                                        klas.setNummer(cell.getStringCellValue());
                                        defaultService.addKlas(klas);
                                    }
                                }
                                break;
                        }
                    }//Einde van celliterator
                } else if (row.getRowNum() == 1) {
                    while (cellIterator.hasNext()) {
                        //Cell vastnemen
                        Cell cell = cellIterator.next();
                        //Kijken of in de allereerste cel 'vak' staat
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                if (cell.getStringCellValue().equalsIgnoreCase("vak")) {
                                    //breaken zodat hij doorgaat naar de volgende cell
                                    break;
                                } else if (!cell.getStringCellValue().equalsIgnoreCase("")){
                                    if (vak.getNaam() == null) {
                                        vak.setNaam(cell.getStringCellValue());
                                        defaultService.addVak(vak);
                                    }
                                }
                        }
                    }//Einde van celliterator
                } else if (row.getRowNum() == 2) {
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.getColumnIndex() == 1) {
                            test.setBeschrijving(cell.getStringCellValue());
                        }
                    }
                } else if (row.getRowNum() == 3) {
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equalsIgnoreCase("totaal")) {

                        }
                        if (cell.getColumnIndex() == 1) {
                            test.setTotaalScore((int) cell.getNumericCellValue());
                            test.setVakId(vak.getId());
                            defaultService.addTest(test);
                            klasTest.setKlasId(klas.getId());
                            klasTest.setTestId(test.getId());
                            defaultService.addKlastest(klasTest);
                        }
                    }
                } else if (row.getRowNum() > 5) {
                    Student student = new Student();
                    Score score = new Score();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equalsIgnoreCase("zit al in de DB")) {
                            break;
                        }
                        if (cell.getColumnIndex() == 0) {
                            if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                                student.setStudentenNr((int) cell.getNumericCellValue());
                            }
                        }
                        if (cell.getColumnIndex() == 1) {
                            String[] voorenachternaam = cell.getStringCellValue().split("\\s+");
                            student.setVoornaam(voorenachternaam[0]);
                            student.setNaam(voorenachternaam[1]);
                            student.setEmail(voorenachternaam[0] + "." + voorenachternaam[1] + "@thomasmore.be");
                            student.setKlasId(klas.getId());
                        }
                        if (cell.getColumnIndex() == 2) {
                            score.setScore((int) cell.getNumericCellValue());
                            score.setTestId(test.getId());
                            score.setStudentId(student.getId());
                            break;
                        }
                    }
                    studenten.add(student);
                    scores.add(score);
                }
            }//einde van rowiterator
        for (Student student : studenten) {
            defaultService.addStudent(student);
        }
        for (Score score : scores) {
            defaultService.addScore(score);
        }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
