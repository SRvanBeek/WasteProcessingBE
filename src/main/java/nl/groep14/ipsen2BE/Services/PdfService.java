package nl.groep14.ipsen2BE.Services;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.opencsv.CSVReader;
import nl.groep14.ipsen2BE.Models.Label;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;



public class PdfService {
    private Label label;

    public PdfService(Label label) {
        this.label = label;
    }

    public List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    public void createAfvalLabel() throws Exception {
        Path csvPath = Path.of("src/main/resources/Lables/AfvalLabel.csv");
        List<String[]> labelcsv = readAllLines(csvPath);

        ArrayList<String> categorielist = new ArrayList<>();
        categorielist.add("yeet");

        String dest = "src/main/resources/";
        File file = new File(dest, "Afvallabel" + label.getOrdernummer() + ".pdf");
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.addNewPage();
        Document document = new Document(pdfDoc);

        pdfDoc.setDefaultPageSize(PageSize.A8);
        document.setMargins(100,100,100,100);
        Table table = new Table(2);

        ImageData imageData = ImageDataFactory.create("src/main/resources/Logo/logo_dark_big.png");
        Image img = new Image(imageData);

        for (int i = 0; i < labelcsv.size(); i++) {
            String tableitem = Arrays.toString(labelcsv.get(i));
            table.addCell(tableitem.substring(tableitem.indexOf("[") + 1, tableitem.indexOf("]")));

            switch (i) {
                case 0 -> table.addCell(label.getNaam());
                case 1 -> table.addCell(String.valueOf(label.getOrdernummer()));
                case 2 -> table.addCell(String.valueOf(label.getCategorie()).substring(String.valueOf(label.getCategorie()).indexOf("["), String.valueOf(label.getCategorie()).indexOf("]")));
                case 3 -> table.addCell(String.valueOf(label.getMetrage()));
                case 4 -> table.addCell(label.getSamenstellingen());
            }
        }

        document.add(img);
        document.add(table);

        document.close();
    }

    public void createOrderLabel() throws Exception {
        Path csvPath = Path.of("src/main/resources/Labels/OrderLabel.csv");
        List<String[]> labelcsv = readAllLines(csvPath);

        ArrayList<String> categorielist = new ArrayList<>();
        categorielist.add("yeet");

        String dest = "src/main/resources/";
        File file = new File(dest, "Afvallabel" + label.getOrdernummer() + ".pdf");
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.addNewPage();
        Document document = new Document(pdfDoc);

        pdfDoc.setDefaultPageSize(PageSize.A8);
        document.setMargins(100,100,100,100);
        Table table = new Table(2);

        ImageData imageData = ImageDataFactory.create("src/main/resources/logo/logo_dark_big.png");
        Image img = new Image(imageData);

        for (int i = 0; i < labelcsv.size(); i++) {
            String tableitem = Arrays.toString(labelcsv.get(i));
            table.addCell(tableitem.substring(tableitem.indexOf("[") + 1, tableitem.indexOf("]")));

            switch (i) {
                case 0 -> table.addCell(label.getNaam());
                case 1 -> table.addCell(String.valueOf(label.getOrdernummer()));
                case 2 -> table.addCell(String.valueOf(label.getMetrage()));
                case 3 -> table.addCell(label.getProductgroep());
                case 4 -> table.addCell(label.getKleur());
                case 5 -> table.addCell(String.valueOf(label.getArtikelnummer()));
                case 6 -> table.addCell(String.valueOf(label.getCategorie()).substring(String.valueOf(label.getCategorie()).indexOf("["), String.valueOf(label.getAfleverlocatie()).indexOf("]")));
            }
        }

        document.add(img);
        document.add(table);

        document.close();
    }
}
