/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import gestionassurancefx.Entities.Reclamation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public class pdf {
        public static void ExporterPDF(ObservableList<Reclamation> list) {
            PdfWriter writer = null;
            try {
                String dest = "src/gestionassurancefx/reclamation.pdf";
                writer = new PdfWriter(dest);
                // Creating a PdfDocument object
                PdfDocument pdf = new PdfDocument(writer);
                // Creating a Document object
                Document doc = new Document(pdf);
                // Creating a table
                float [] pointColumnWidths = {50F, 175F, 130F,130F,50F,50F,200F};
                Table table = new Table(pointColumnWidths);
                // Adding cells to the table
                table.addCell("ID");
                table.addCell("Type");
                table.addCell("Date Saisi");
                table.addCell("Type Assure");
                table.addCell("id Assure");
                table.addCell("Etat");
                table.addCell("Description");
                for (Reclamation Rec : list) {
                    table.addCell(""+Rec.getIdR());
                    table.addCell(Rec.getTypeRec());
                    table.addCell(Rec.getDateSaisiRec().toString());
                    table.addCell(Rec.getTypeAssure());
                    table.addCell(""+Rec.getIdCli());
                    table.addCell(""+Rec.getTraitementRec());
                    table.addCell(""+Rec.getDescRec());
                    
                }
                // Adding Table to document
                doc.add(table);
                // Closing the document
                doc.close();
                System.out.println("Table created successfully..");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {  
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

}
