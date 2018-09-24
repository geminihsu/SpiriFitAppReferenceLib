package spirit.fitness.scanner.until;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import jxl.*;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import spirit.fitness.scanner.common.Constrant;
import spirit.fitness.scanner.model.DailyShippingReportbean;
import spirit.fitness.scanner.model.SalesJournal;

import javax.swing.JTable;

public class ExcelHelper {

	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String COMMA_DELIMITER = ",";

	public void fillData(JTable table, File file) {

		try {
			int[] total = new int[9];
		
			int totalCntIdx = 0;
			
			WritableWorkbook workbook1 = Workbook.createWorkbook(file);
			WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			
			WritableCellFormat cellheaderFormat = new WritableCellFormat();
			workbook1.setColourRGB(Colour.OCEAN_BLUE,42, 82, 190);
			cellheaderFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont TableFormat = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
			cellheaderFormat.setFont(TableFormat); // set the font
			cellheaderFormat.setBackground(Colour.OCEAN_BLUE); // Table background
			cellheaderFormat.setAlignment(Alignment.CENTRE);
			
			
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont rowFormat = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			cellFormat.setFont(rowFormat); // set the font
			cellFormat.setBackground(Colour.WHITE); // Table background
			cellFormat.setAlignment(Alignment.CENTRE);
			
			WritableCellFormat oddRowFormat = new WritableCellFormat();
			oddRowFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont oddRowFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			oddRowFormat.setFont(oddRowFont); // set the font
			
			workbook1.setColourRGB(Colour.ICE_BLUE,235, 235, 255);
			oddRowFormat.setBackground(Colour.ICE_BLUE); // Table background
			oddRowFormat.setAlignment(Alignment.CENTRE);
				
			TableModel model = table.getModel();

			for (int i = 0; i < model.getColumnCount(); i++) {
				Label column = new Label(i, 0, model.getColumnName(i));
				
				
				column.setCellFormat(cellheaderFormat);
				
				sheet1.addCell(column);
			}
			int j = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				for (j = 0; j < model.getColumnCount(); j++) {
					
					if(model.getValueAt(i, j) == null)
						continue;
					
					Label row = new Label(j, i + 1, model.getValueAt(i, j).toString());
					
					if(i == 1)
						sheet1.setColumnView(i, 50);
					else
						if(i == 2 || i == 3)
							sheet1.setColumnView(i, 12);
					else if(i>3 && j >=4) 
					{
					total[j-2] += Integer.valueOf(model.getValueAt(i, j).toString());
					    
					}else if(i == 8 || i == 9)
						sheet1.setColumnView(i, 12);
					if(i % 2 == 0)
						row.setCellFormat(oddRowFormat);
					else
						row.setCellFormat(cellFormat);
					sheet1.addCell(row);
				}
			}
			totalCntIdx =0;
			for (j = 0; j < model.getColumnCount(); j++) {
				Label row = null;
			if(j == 0)
				row = new Label(j, model.getRowCount() + 1, "Total");
			else if(j == 1)
				row = new Label(j, model.getRowCount() + 1, "");
			else
				row = new Label(j, model.getRowCount() + 1, String.valueOf(total[totalCntIdx++]));
			
			if(model.getRowCount() == 1)
				sheet1.setColumnView(model.getRowCount()-1, 50);
			else if(model.getRowCount() == 8 || model.getRowCount() == 9)
				sheet1.setColumnView(model.getRowCount()-1, 12);
			
				row.setCellFormat(cellheaderFormat);
			sheet1.addCell(row);
			}
			workbook1.write();
			workbook1.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void fillDailyShippingData(JTable table, File file) {

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
					if (model.getValueAt(i, j) != null) {
						Label row = new Label(j, i + 1, model.getValueAt(i, j).toString());
						row.setCellFormat(cellFormat);
						sheet1.addCell(row);
					}
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
		int soIndex = 1;
		HashMap<String, Integer> soCntmap = new HashMap<String, Integer>();
		HashSet<String> snMap = new HashSet<String>();
		List<SalesJournal> result = new ArrayList<SalesJournal>();
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(cvsSplitBy);

				if (country[Constrant.ITEMID].startsWith("T0") || soMap.get(country[Constrant.SO]) == null)
					continue;

				if (soCntmap.get(country[Constrant.SO]) == null) {

					soCntmap.put(country[Constrant.SO], soIndex);
					soIndex++;
				}
				SalesJournal salesJournal = new SalesJournal();
				salesJournal.CustomerID = country[Constrant.CUSTOMERID];
				salesJournal.SO = country[Constrant.SO];
				salesJournal.Date = country[Constrant.DATE];
				salesJournal.ShipBy = country[Constrant.SHIPBY];
				salesJournal.ShipByfalse = country[Constrant.SHIPBYFALSE];
				salesJournal.DropShip = country[Constrant.DROPSHIP];
				salesJournal.ShipToName = country[Constrant.SHIPTONAME];
				salesJournal.ShipToAddress1 = country[Constrant.SHIPTOADDRESS1];
				salesJournal.ShipToAddress2 = country[Constrant.SHIPTOADDRESS2];
				salesJournal.ShipToCity = country[Constrant.SHIPTOCITY];
				salesJournal.ShipToState = country[Constrant.SHIPTOSTATE];
				salesJournal.ShipToPhone = country[Constrant.SHIPTOPHONE];
				salesJournal.ShipToZipCode = country[Constrant.SHIPTOZIPCODE];
				salesJournal.ShipToCountryCode = country[Constrant.SHIPTOZCODECOUNTRY];
				salesJournal.ShipToCountry = country[Constrant.SHIPTOZIPCOUNTRY];
				salesJournal.CustPo = country[Constrant.CUSTPO];
				salesJournal.AcountReceivableId = country[Constrant.ACCOUNT_RECE_ID];
				salesJournal.ShipVia = country[Constrant.SHIPVIA];
				salesJournal.DiscountAmount = country[Constrant.DISCOUNT_AMOUNT];
				salesJournal.DisplayTerms = country[Constrant.DISPLAY_TERMS];
				salesJournal.DisplayType = country[Constrant.DISPLAY_TYPE];
				salesJournal.SalesRepID = country[Constrant.SALES_REPID];
				salesJournal.SalesTax = country[Constrant.SALES_TAX];
				salesJournal.AcountReceivable = country[Constrant.ACCOUNT_RECEIVABLE];
				salesJournal.NotePrint = country[Constrant.NOTE_PRINT];
				salesJournal.numberOfDistribution = country[Constrant.NUMBEROFDISTRIBUTION];
				if (!salesJournal.SalesTax.equals(""))
					salesJournal.SODistribution = String
							.valueOf(Integer.valueOf(country[Constrant.SODISTRIBUTION]) + 1);
				else
					salesJournal.SODistribution = String.valueOf(country[Constrant.SODISTRIBUTION]);

				salesJournal.Quantity = country[Constrant.QTY];

				if (country[Constrant.ITEMID].equals(""))
					salesJournal.ItemID = "";
				else
					salesJournal.ItemID = country[Constrant.ITEMID];

				salesJournal.Description = country[Constrant.DESCRIPTION];
				salesJournal.SOCntIdx = "" + soCntmap.get(country[Constrant.SO]);
				int shifIndex = 0;
				if (salesJournal.ItemID.startsWith("PLT")) {
					salesJournal.Description = salesJournal.Description + country[Constrant.DESCRIPTION + 1];
					shifIndex++;
				}
				salesJournal.GL_Account = country[Constrant.GL_ACCOUNT + shifIndex];
				salesJournal.UnitPrice = country[Constrant.UNIT_PRICE + shifIndex];
				salesJournal.TaxType = country[Constrant.TAX_TYPE + shifIndex];
				salesJournal.UPC_SKU = country[Constrant.UPC_SKU + shifIndex];
				salesJournal.Weight = country[Constrant.WEIGHT + shifIndex];
				salesJournal.U_M = country[Constrant.U_M + shifIndex];
				salesJournal.StockingQty = country[Constrant.STOCKING_QTY + shifIndex];
				salesJournal.StockingUnitPrice = country[Constrant.STOCKING_UNIT_PRICE + shifIndex];
				salesJournal.Amount = country[Constrant.AMOUNT + shifIndex];
				salesJournal.ProposalAccepted = country[Constrant.PROPOSAL_ACCEPTED + shifIndex];

				int qty = 0;

				if (salesJournal.Quantity.indexOf(".") != -1)
					qty = Integer.valueOf(salesJournal.Quantity.substring(0, salesJournal.Quantity.indexOf(".")));
				// while (qty > 0) {

				List<DailyShippingReportbean> history = soMap.get(country[Constrant.SO]);

				for (DailyShippingReportbean i : history) {

					if (i.sn != null && snMap.contains(i.sn))
						continue;

					if(salesJournal.ItemID.equals("450887"))
						i.itemID = "450887";
					
					if(salesJournal.ItemID.equals("115816"))
						i.itemID = "115816";
					
					if (i.itemID.equals(salesJournal.ItemID) && !i.itemID.equals("")) {

						SalesJournal salesJournal2 = copyData(salesJournal);
						salesJournal2.SN = i.sn;
						salesJournal2.TrackingNO = i.trackingNo;
						salesJournal2.ShippingDate = i.shippingDate;
						salesJournal2.Quantity = salesJournal.Quantity;
						snMap.add(i.sn);

						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						SimpleDateFormat shipFormat = new SimpleDateFormat("yyyy-MM-dd");
						try {
							Date shippedDate = shipFormat.parse(salesJournal2.ShippingDate.substring(0, 10));
							Calendar ship = Calendar.getInstance();
							ship.setTime(shippedDate);
							String shipDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());
							salesJournal2.ShippingDate = shipDate;

							Date date = formatter.parse(salesJournal2.Date);

							Calendar c = Calendar.getInstance();
							c.setTime(date);

							if (salesJournal2.DisplayTerms.indexOf("30") != -1)
								ship.add(Calendar.DATE, 30);
							else if (salesJournal2.DisplayTerms.indexOf("45") != -1)
								ship.add(Calendar.DATE, 45);
							else if (salesJournal2.DisplayTerms.indexOf("60") != -1)
								ship.add(Calendar.DATE, 60);
							else if (salesJournal2.DisplayTerms.indexOf("90") != -1)
								ship.add(Calendar.DATE, 90);
							else if (salesJournal2.DisplayTerms.equals("Prepaid"))
								ship.add(Calendar.DATE, 0);

							String dueDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());

							if (dueDate.indexOf("0018") != -1)
								dueDate = dueDate.replace("0018", "2018");

							salesJournal2.DueDate = dueDate;

							result.add(salesJournal2);
							System.out.println("Sales [order = " + salesJournal2.SO + "] , [itemID="
									+ salesJournal2.ItemID + "]" + " , [SN=" + salesJournal2.SN + "]" + " , [UnitPrice="
									+ salesJournal2.UnitPrice + "]");
							// break;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

				if (salesJournal.Description.startsWith("Freight")) {
					SalesJournal salesJournal2 = copyData(salesJournal);
					DailyShippingReportbean i = history.get(0);
					salesJournal2.TrackingNO = i.trackingNo;
					salesJournal2.ShippingDate = i.shippingDate;
					salesJournal2.SOCntIdx = "" + soCntmap.get(country[Constrant.SO]);
					salesJournal2.ItemID = "";
					salesJournal2.SN = "";

					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					SimpleDateFormat shipFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date shippedDate = shipFormat.parse(salesJournal2.ShippingDate.substring(0, 10));
						Calendar ship = Calendar.getInstance();
						ship.setTime(shippedDate);
						String shipDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());
						salesJournal2.ShippingDate = shipDate;

						Date date = formatter.parse(salesJournal2.Date);

						Calendar c = Calendar.getInstance();
						c.setTime(date);

						if (salesJournal2.DisplayTerms.indexOf("30") != -1)
							ship.add(Calendar.DATE, 30);
						else if (salesJournal2.DisplayTerms.indexOf("45") != -1)
							ship.add(Calendar.DATE, 45);
						else if (salesJournal2.DisplayTerms.indexOf("60") != -1)
							ship.add(Calendar.DATE, 60);
						else if (salesJournal2.DisplayTerms.indexOf("90") != -1)
							ship.add(Calendar.DATE, 90);
						else if (salesJournal2.DisplayTerms.equals("Prepaid"))
							ship.add(Calendar.DATE, 0);

						String dueDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());

						if (dueDate.indexOf("0018") != -1)
							dueDate = dueDate.replace("0018", "2018");

						salesJournal2.DueDate = dueDate;

						result.add(salesJournal2);
						System.out.println("Sales [order = " + salesJournal2.SO + "] , [itemID=" + salesJournal2.ItemID
								+ "]" + " , [Description=" + salesJournal2.Description + "]" + " , [UnitPrice="
								+ salesJournal2.UnitPrice + "]");

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (salesJournal.Description.startsWith("SHIPPER'S")
						|| salesJournal.Description.startsWith("CHERISE CRISMAN ")) {
					SalesJournal salesJournal2 = copyData(salesJournal);
					DailyShippingReportbean i = history.get(0);
					salesJournal2.TrackingNO = i.trackingNo;
					salesJournal2.ShippingDate = i.shippingDate;

					salesJournal2.ItemID = "";
					salesJournal2.SN = "";

					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					SimpleDateFormat shipFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date shippedDate = shipFormat.parse(salesJournal2.ShippingDate.substring(0, 10));
						Calendar ship = Calendar.getInstance();
						ship.setTime(shippedDate);
						String shipDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());
						salesJournal2.ShippingDate = shipDate;

						Date date = formatter.parse(salesJournal.Date);

						Calendar c = Calendar.getInstance();
						c.setTime(date);

						if (salesJournal2.DisplayTerms.indexOf("30") != -1)
							ship.add(Calendar.DATE, 30);
						else if (salesJournal2.DisplayTerms.indexOf("45") != -1)
							ship.add(Calendar.DATE, 45);
						else if (salesJournal2.DisplayTerms.indexOf("60") != -1)
							ship.add(Calendar.DATE, 60);
						else if (salesJournal2.DisplayTerms.indexOf("90") != -1)
							ship.add(Calendar.DATE, 90);
						else if (salesJournal2.DisplayTerms.equals("Prepaid"))
							ship.add(Calendar.DATE, 0);

						String dueDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());

						if (dueDate.indexOf("0018") != -1)
							dueDate = dueDate.replace("0018", "2018");

						salesJournal2.DueDate = dueDate;

						result.add(salesJournal2);
						System.out.println("Sales [order = " + salesJournal2.SO + "] , [itemID=" + salesJournal2.ItemID
								+ "]" + " , [Description=" + salesJournal2.Description + "]" + " , [UnitPrice="
								+ salesJournal2.UnitPrice + "]");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (salesJournal.Description.equals("") && !salesJournal.GL_Account.equals("")) {
					SalesJournal salesJournal2 = copyData(salesJournal);
					DailyShippingReportbean i = history.get(0);
					salesJournal2.TrackingNO = i.trackingNo;
					salesJournal2.ShippingDate = i.shippingDate;

					salesJournal2.ItemID = "";
					salesJournal2.SN = "";

					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					SimpleDateFormat shipFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date shippedDate = shipFormat.parse(salesJournal2.ShippingDate.substring(0, 10));
						Calendar ship = Calendar.getInstance();
						ship.setTime(shippedDate);
						String shipDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());
						salesJournal2.ShippingDate = shipDate;

						Date date = formatter.parse(salesJournal.Date);

						Calendar c = Calendar.getInstance();
						c.setTime(date);

						if (salesJournal2.DisplayTerms.indexOf("30") != -1)
							ship.add(Calendar.DATE, 30);
						else if (salesJournal2.DisplayTerms.indexOf("45") != -1)
							ship.add(Calendar.DATE, 45);
						else if (salesJournal2.DisplayTerms.indexOf("60") != -1)
							ship.add(Calendar.DATE, 60);
						else if (salesJournal2.DisplayTerms.indexOf("90") != -1)
							ship.add(Calendar.DATE, 90);
						else if (salesJournal2.DisplayTerms.equals("Prepaid"))
							ship.add(Calendar.DATE, 0);

						String dueDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());

						if (dueDate.indexOf("0018") != -1)
							dueDate = dueDate.replace("0018", "2018");

						salesJournal2.DueDate = dueDate;

						result.add(salesJournal2);
						System.out.println("Sales [order = " + salesJournal2.SO + "] , [itemID=" + salesJournal2.ItemID
								+ "]" + " , [Description=" + salesJournal2.Description + "]" + " , [UnitPrice="
								+ salesJournal2.UnitPrice + "]");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (salesJournal.ItemID.equals("") && !salesJournal.Description.equals("")
						&& salesJournal.UnitPrice.equals("0.00")) {
					SalesJournal salesJournal2 = copyData(salesJournal);
					DailyShippingReportbean i = history.get(0);
					salesJournal2.TrackingNO = i.trackingNo;
					salesJournal2.ShippingDate = i.shippingDate;

					salesJournal2.ItemID = "";
					salesJournal2.SN = "";

					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					SimpleDateFormat shipFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date shippedDate = shipFormat.parse(salesJournal2.ShippingDate.substring(0, 10));
						Calendar ship = Calendar.getInstance();
						ship.setTime(shippedDate);
						String shipDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());
						salesJournal2.ShippingDate = shipDate;

						Date date = formatter.parse(salesJournal.Date);

						Calendar c = Calendar.getInstance();
						c.setTime(date);

						if (salesJournal2.DisplayTerms.indexOf("30") != -1)
							ship.add(Calendar.DATE, 30);
						else if (salesJournal2.DisplayTerms.indexOf("45") != -1)
							ship.add(Calendar.DATE, 45);
						else if (salesJournal2.DisplayTerms.indexOf("60") != -1)
							ship.add(Calendar.DATE, 60);
						else if (salesJournal2.DisplayTerms.indexOf("90") != -1)
							ship.add(Calendar.DATE, 90);
						else if (salesJournal2.DisplayTerms.equals("Prepaid"))
							ship.add(Calendar.DATE, 0);

						String dueDate = new SimpleDateFormat("MM/dd/yyyy").format(ship.getTime());

						if (dueDate.indexOf("0018") != -1)
							dueDate = dueDate.replace("0018", "2018");

						salesJournal2.DueDate = dueDate;

						result.add(salesJournal2);
						System.out.println("Sales [order = " + salesJournal2.SO + "] , [itemID=" + salesJournal2.ItemID
								+ "]" + " , [Description=" + salesJournal2.Description + "]" + " , [UnitPrice="
								+ salesJournal2.UnitPrice + "]");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// qty--;
				// }

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

	public static boolean writeToCVS(String path, List<SalesJournal> salesOrder) {
		FileWriter fileWriter = null;
		String csvFile = path + "\\SALES.CSV";

		try {
			fileWriter = new FileWriter(csvFile);

			// Write a new student object list to the CSV file
			for (SalesJournal sales : salesOrder) {

				fileWriter.append(String.valueOf(sales.CustomerID));
				fileWriter.append(COMMA_DELIMITER);

				String salesOrderhead = String.valueOf(sales.SO);
				if (salesOrderhead.indexOf("EDI") == -1)
					salesOrderhead = "P" + salesOrderhead;
				fileWriter.append(salesOrderhead);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(sales.ShipByfalse);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(sales.ShipByfalse);
				fileWriter.append(COMMA_DELIMITER);
				// fileWriter.append("FALSE");
				// fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShippingDate));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipBy));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.DropShip));
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
				fileWriter.append(String.valueOf(sales.ShipToPhone));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToZipCode));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToCountryCode));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipToCountry));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.CustPo));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.AcountReceivableId));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShipVia));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ShippingDate));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.DueDate));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.DiscountAmount));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.Date));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.DisplayTerms));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.DisplayType));
				fileWriter.append(COMMA_DELIMITER);

				fileWriter.append(String.valueOf(sales.SalesRepID));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.SalesTax));
				fileWriter.append(COMMA_DELIMITER);
				if (String.valueOf(sales.TrackingNO).equals(""))
					fileWriter.append(String.valueOf(sales.TrackingNO));
				else {
					String pro = String.valueOf(sales.TrackingNO);
					if (pro.length() >= 34)
						pro = pro.substring(pro.length() - 12, pro.length());

					fileWriter.append("PRO# " + pro);
				}
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("TRUE");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.AcountReceivable));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.NotePrint));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("FALSE");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.numberOfDistribution));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.SODistribution));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf("0"));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf("TRUE"));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf("FALSE"));
				fileWriter.append(COMMA_DELIMITER);

				fileWriter.append(String.valueOf(sales.Quantity));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.SO));
				fileWriter.append(COMMA_DELIMITER);

				if (sales.ItemID == null || sales.ItemID.equals(""))
					fileWriter.append("");
				else
					fileWriter.append(String.valueOf(sales.ItemID));
				fileWriter.append(COMMA_DELIMITER);
				if (sales.SN == null)
					fileWriter.append("");
				else
					fileWriter.append(String.valueOf(sales.SN));
				fileWriter.append(COMMA_DELIMITER);
				int distribution = 0;
				if (!sales.SalesTax.equals(""))
					distribution = Integer.valueOf(sales.SODistribution) - 1;
				else
					distribution = Integer.valueOf(sales.SODistribution);
				fileWriter.append(String.valueOf(distribution));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.Description));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.GL_Account));
				fileWriter.append(COMMA_DELIMITER);

				fileWriter.append(String.valueOf(sales.UnitPrice));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.TaxType));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.UPC_SKU));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.Weight));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.ProposalAccepted));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.U_M));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.StockingQty));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.StockingUnitPrice));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sales.Amount));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				if (sales.ItemID.equals(""))
					fileWriter.append(String.valueOf(sales.SalesTax));
				else
					fileWriter.append(String.valueOf(""));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf("30"));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(sales.SOCntIdx);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf("0"));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf("0"));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf("0"));
				fileWriter.append(NEW_LINE_SEPARATOR);

			}

			System.out.println("CSV file was created successfully !!!");
			return true;
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			return false;
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
				return false;
			}

		}
	}

	private static SalesJournal copyData(SalesJournal s) {
		SalesJournal salesJournal = new SalesJournal();
		salesJournal.CustomerID = s.CustomerID;
		salesJournal.SO = s.SO;
		salesJournal.Date = s.Date;
		salesJournal.ShipBy = s.ShipBy;
		salesJournal.ShipByfalse = s.ShipByfalse;
		salesJournal.DropShip = s.DropShip;
		salesJournal.ShipToName = s.ShipToName;
		salesJournal.ShipToAddress1 = s.ShipToAddress1;
		salesJournal.ShipToAddress2 = s.ShipToAddress2;
		salesJournal.ShipToCity = s.ShipToCity;
		salesJournal.ShipToState = s.ShipToState;
		salesJournal.ShipToPhone = s.ShipToPhone;
		salesJournal.ShipToZipCode = s.ShipToZipCode;
		salesJournal.ShipToCountryCode = s.ShipToCountryCode;
		salesJournal.ShipToCountry = s.ShipToCountry;
		salesJournal.CustPo = s.CustPo;
		salesJournal.AcountReceivableId = s.AcountReceivableId;
		salesJournal.ShipVia = s.ShipVia;
		salesJournal.DiscountAmount = s.DiscountAmount;
		salesJournal.DisplayTerms = s.DisplayTerms;
		salesJournal.DisplayType = s.DisplayType;
		salesJournal.SalesRepID = s.SalesRepID;
		salesJournal.SalesTax = s.SalesTax;
		salesJournal.AcountReceivable = s.AcountReceivable;
		salesJournal.NotePrint = s.NotePrint;
		salesJournal.numberOfDistribution = s.numberOfDistribution;
		salesJournal.SODistribution = s.SODistribution;
		salesJournal.Quantity = s.Quantity;
		salesJournal.ItemID = s.ItemID;
		salesJournal.Description = s.Description;
		salesJournal.SOCntIdx = "" + s.SOCntIdx;
		salesJournal.Description = s.Description;

		salesJournal.GL_Account = s.GL_Account;
		salesJournal.UnitPrice = s.UnitPrice;
		salesJournal.TaxType = s.TaxType;
		salesJournal.UPC_SKU = s.UPC_SKU;
		salesJournal.Weight = s.Weight;
		salesJournal.U_M = s.U_M;
		salesJournal.StockingQty = s.StockingQty;
		salesJournal.StockingUnitPrice = s.StockingUnitPrice;
		salesJournal.Amount = s.Amount;
		salesJournal.ProposalAccepted = s.ProposalAccepted;

		return salesJournal;
	}
	// public static void main(String[] args) {
	// ExcelHelper excelhelper = new ExcelHelper();
	// readCSVFile();
	// }

}