package spirit.fitness.scanner.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import jxl.*;
import jxl.write.*;
import spirit.fitness.scanner.model.SalesJournal;

//Read more: http://niravjavadeveloper.blogspot.com/2011/05/java-swing-export-jtable-to-excel-file.html#ixzz54awwPWb4
import javax.swing.JTable;

public class ExcelHelper {
	public void fillData(JTable table, File file) {

        try {

            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0); 
            TableModel model = table.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                Label column = new Label(i, 0, model.getColumnName(i));
                sheet1.addCell(column);
            }
            int j = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                for (j = 0; j < model.getColumnCount(); j++) {
                    Label row = new Label(j, i + 1, model.getValueAt(i, j).toString());
                    sheet1.addCell(row);
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	public static List<SalesJournal> readCSVFile() 
	{
		 String csvFile = System.getProperty("user.dir")+"\\SO_20180523.csv";

	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        List<SalesJournal> result = new ArrayList<SalesJournal>();
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] country = line.split(cvsSplitBy);

	                SalesJournal salesJournal = new SalesJournal();
	                salesJournal.CustomerID = country[0];
	                salesJournal.SO = country[1];
	                salesJournal.Date = country[2];
	                salesJournal.ShipBy = country[3];
	                salesJournal.DropShip = country[5];
	                salesJournal.ShipToName = country[6];
	                salesJournal.ShipToAddress1 = country[7];
	                salesJournal.ShipToAddress2 = country[8];
	                salesJournal.ShipToCity = country[9];
	                salesJournal.ShipToState = country[10];
	                salesJournal.ShipToZipCode = country[12];
	                salesJournal.ShipToCountry = country[14];
	                salesJournal.CustPo = country[15];
	                salesJournal.ShipVia = country[16];
	                salesJournal.DiscountAmount = country[17];
	                salesJournal.DistplayTerms = country[18];
	                salesJournal.SalesRepID = country[25];
	                salesJournal.AcountReceivable = country[26];
	                salesJournal.NotePrint = country[27];
	                salesJournal.numberOfDistribution = country[28];
	                salesJournal.SODistribution = country[29];
	                salesJournal.Quantity = country[30];
	                salesJournal.ItemID = country[31];
	                salesJournal.Description = country[32];
	                salesJournal.GL_Account = country[33];
	                salesJournal.UnitPrice = country[34];
	                salesJournal.TaxType = country[35];
	                salesJournal.UPC_SKU = country[36];
	                salesJournal.Weight = country[37];
	                salesJournal.U_M = country[38];
	                salesJournal.StockingQty = country[39];
	                salesJournal.StockingUnitPrice = country[40];
	                salesJournal.Amount = country[41];
	                salesJournal.ProposalAccepted = country[42];
	              
	                int qty = 0;
	                
	                if(salesJournal.Quantity.indexOf(".") != -1)
	                	qty =	Integer.valueOf(salesJournal.Quantity.substring(0,salesJournal.Quantity.indexOf(".")));
	                while(qty > 0) {
	                result.add(salesJournal);
	                System.out.println("Sales [order = " + salesJournal.SO + " , itemID=" + salesJournal.ItemID + "]"+ " , Qty=" + salesJournal.Quantity + "]");

	                qty --;
	                }
	             
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
         return result;
	    }
	
  // public static void main(String[] args) {
	   //ExcelHelper excelhelper = new ExcelHelper();
	//   readCSVFile();
  //  }

//Read more: http://niravjavadeveloper.blogspot.com/2011/05/java-swing-export-jtable-to-excel-file.html#ixzz54awovRLM
}
