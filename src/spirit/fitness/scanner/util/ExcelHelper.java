package spirit.fitness.scanner.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import jxl.*;
import jxl.write.*;
import spirit.fitness.scanner.common.Constrant;
import spirit.fitness.scanner.model.DailyShippingReportbean;
import spirit.fitness.scanner.model.SalesJournal;

//Read more: http://niravjavadeveloper.blogspot.com/2011/05/java-swing-export-jtable-to-excel-file.html#ixzz54awwPWb4
import javax.swing.JTable;

public class ExcelHelper {

	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String COMMA_DELIMITER = ",";

	public void fillData(JTable table, File file) {

		try {

			WritableWorkbook workbook1 = Workbook.createWorkbook(file);
			WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			TableModel model = table.getModel();

			for (int i = 0; i < model.getColumnCount(); i++) {
				Label column = new Label(i, 0, model.getColumnName(i));
				column.setCellFormat(cellFormat);
				sheet1.addCell(column);
			}
			int j = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				for (j = 0; j < model.getColumnCount(); j++) {
					Label row = new Label(j, i + 1, model.getValueAt(i, j).toString());
					row.setCellFormat(cellFormat);
					sheet1.addCell(row);
				}
			}
			workbook1.write();
			workbook1.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void fillDailyShippingData(JTable table, File file) {

		try {

			WritableWorkbook workbook1 = Workbook.createWorkbook(file);
			WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			TableModel model = table.getModel();

			for (int i = 0; i < model.getColumnCount(); i++) {
				Label column = new Label(i, 0, model.getColumnName(i));
				column.setCellFormat(cellFormat);
				sheet1.addCell(column);
			}
			int j = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				for (j = 0; j < model.getColumnCount(); j++) {
					Label row = new Label(j, i + 1, model.getValueAt(i, j).toString());
					row.setCellFormat(cellFormat);
					sheet1.addCell(row);
				}
			}
			workbook1.write();
			workbook1.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static List<SalesJournal> readCSVFile(String path,
			LinkedHashMap<String, List<DailyShippingReportbean>> soMap) {
		// String csvFile = System.getProperty("user.dir")+"\\SO_20180523.csv";
		String csvFile = path + "\\SO.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<SalesJournal> result = new ArrayList<SalesJournal>();
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(cvsSplitBy);

				if (country[Constrant.ITEMID].startsWith("T0") || soMap.get(country[Constrant.SO]) == null)
					continue;

				SalesJournal salesJournal = new SalesJournal();
				salesJournal.CustomerID = country[Constrant.CUSTOMERID];
				salesJournal.SO = country[Constrant.SO];
				salesJournal.Date = country[Constrant.DATE];
				salesJournal.ShipBy = country[Constrant.SHIPBY];
				salesJournal.DropShip = country[Constrant.DROPSHIP];
				salesJournal.ShipToName = country[Constrant.SHIPTONAME];
				salesJournal.ShipToAddress1 = country[Constrant.SHIPTOADDRESS1];
				salesJournal.ShipToAddress2 = country[Constrant.SHIPTOADDRESS2];
				salesJournal.ShipToCity = country[Constrant.SHIPTOCITY];
				salesJournal.ShipToState = country[Constrant.SHIPTOSTATE];
				salesJournal.ShipToZipCode = country[Constrant.SHIPTOZIPCODE];
				salesJournal.ShipToCountry = country[Constrant.SHIPTOZIPCOUNTRY];
				salesJournal.CustPo = country[Constrant.CUSTPO];
				salesJournal.ShipVia = country[Constrant.SHIPVIA];
				salesJournal.DiscountAmount = country[Constrant.DISCOUNT_AMOUNT];
				salesJournal.DisplayTerms = country[Constrant.DISPLAY_TERMS];
				salesJournal.SalesRepID = country[Constrant.SALES_REPID];
				salesJournal.AcountReceivable = country[Constrant.ACCOUNT_RECEIVABLE];
				salesJournal.NotePrint = country[Constrant.NOTE_PRINT];
				salesJournal.numberOfDistribution = country[Constrant.NUMBEROFDISTRIBUTION];
				salesJournal.SODistribution = country[Constrant.SODISTRIBUTION];
				salesJournal.Quantity = country[Constrant.QTY];
				salesJournal.ItemID = country[Constrant.ITEMID];
				salesJournal.Description = country[Constrant.DESCRIPTION];
				
			    salesJournal.GL_Account = country[Constrant.GL_ACCOUNT];
				salesJournal.UnitPrice = country[Constrant.UNIT_PRICE];
				salesJournal.TaxType = country[Constrant.TAX_TYPE];
				salesJournal.UPC_SKU = country[Constrant.UPC_SKU]; 
				salesJournal.Weight = country[Constrant.WEIGHT];
				salesJournal.U_M = country[Constrant.U_M]; 
				salesJournal.StockingQty = country[Constrant.STOCKING_QTY]; 
				salesJournal.StockingUnitPrice = country[Constrant.STOCKING_UNIT_PRICE]; 
				salesJournal.Amount = country[Constrant.AMOUNT]; 
				salesJournal.ProposalAccepted = country[Constrant.PROPOSAL_ACCEPTED];
			
				int qty = 0;

				if (salesJournal.Quantity.indexOf(".") != -1)
					qty = Integer.valueOf(salesJournal.Quantity.substring(0, salesJournal.Quantity.indexOf(".")));
				//while (qty > 0) {

					List<DailyShippingReportbean> history = soMap.get(country[Constrant.SO]);

					for (DailyShippingReportbean i : history) {
						
						if(i.itemID.equals(salesJournal.ItemID)) {
						salesJournal.SN = i.sn;
						salesJournal.TrackingNO = i.trackingNo;
						result.add(salesJournal);
						System.out.println("Sales [order = " + salesJournal.SO + "] , [itemID=" + salesJournal.ItemID
								+ "]" + " , [SN=" + salesJournal.SN + "]" + " , [Qty=" + salesJournal.Quantity
								+ "]");
						}
					}

					//qty--;
				//}

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

	public static void writeToCVS(String path,List<SalesJournal> salesOrder) {
		FileWriter fileWriter = null;
		String csvFile = path + "\\SALES.csv";

		try {
			fileWriter = new FileWriter(csvFile);

			// Write a new student object list to the CSV file
			for (SalesJournal sales : salesOrder) {
				fileWriter.append(String.valueOf(sales.CustomerID));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.SO));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("FALSE");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("FALSE");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.Date));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipBy));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.DropShip));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("FALSE");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToName));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToAddress1));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToAddress2));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToCity));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToState));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToZipCode));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToCountry));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.CustPo));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipVia));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.DiscountAmount));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.DisplayTerms));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.SalesRepID));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.AcountReceivable));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.NotePrint));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.numberOfDistribution));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.SODistribution));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.Quantity));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ItemID));
				fileWriter.append(COMMA_DELIMITER);
				if(sales.SN == null)
					fileWriter.append("");
				else
					fileWriter.append(String.valueOf(sales.SN));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.Description));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.GL_Account));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("PRO# "+String.valueOf(sales.TrackingNO));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.UnitPrice));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.TaxType));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.UPC_SKU));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.Weight));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.U_M));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.StockingQty));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.StockingUnitPrice));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.Amount));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ProposalAccepted));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}

	
	// public static void main(String[] args) {
	// ExcelHelper excelhelper = new ExcelHelper();
	// readCSVFile();
	// }

	// Read more:
	// http://niravjavadeveloper.blogspot.com/2011/05/java-swing-export-jtable-to-excel-file.html#ixzz54awovRLM
}
