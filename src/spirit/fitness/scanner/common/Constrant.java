package spirit.fitness.scanner.common;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import spirit.fitness.scanner.model.Locationbean;
import spirit.fitness.scanner.model.ModelDailyReportbean;
import spirit.fitness.scanner.model.ModelZone2bean;
import spirit.fitness.scanner.model.Modelbean;


public class Constrant {
	public static String webUrl = "http://172.16.2.6";
	//public static String webUrl = "http://172.16.2.6";
	//public static String weburlprefix = "FGService/api/";
	
	public static HashMap<String,Modelbean> models;
	public static HashMap<String,Locationbean> locations;
	public static HashMap<String,ModelDailyReportbean> dailyReport;
	public static List<ModelZone2bean> modelZone2List;
	public static HashMap<String,ModelZone2bean> modelZone2;
	  
	//Zone mapping Table key : zone number, value : location number
    public static int ZONE_CODE_LEN_LIMIT = 3;
    public static int ZONE_CODE_1 = 1;
    public static int ZONE_CODE_2 = 2;
    public static int ZONE_CODE_3 = 3;
    public static int ZONE_CODE_4 = 4;
    public static int ZONE_CODE_5 = 5;
    public static int ZONE_CODE_6 = 6;
    public static int ZONE_CODE_7 = 7;
    
    //Zone 1 range
    public static int ZONE_CODE_1_MIN = 0;
    public static int ZONE_CODE_1_MAX = 69;
    //Zone 2 range
    public static int ZONE_CODE_2_MIN = 701;
    public static int ZONE_CODE_2_MAX = 992;

    //Zone 3 range
    public static int ZONE_CODE_3_A = 881;
    public static int ZONE_CODE_3_B = 891;
    public static int ZONE_CODE_3_C = 901;
    public static int ZONE_CODE_3_D = 911;
    //Zone 4 range
    public static int ZONE_CODE_4_ONE = 888;
    
    //Rework range
    public static int ZONE_CODE_5_ONE = 555;
    
    //QC range
    public static int ZONE_CODE_6_ONE = 666;
    
    //Scrapped range
    public static int ZONE_CODE_7_ONE = 777;
    
    //Shipping
    public static int ZONE_CODE_SHIPPING = 999;
    
    
    //Java form color define
    public static Color BACKGROUN_COLOR = new java.awt.Color(235, 240, 255);
    public static Color FRAME_BORDER_BACKGROUN_COLOR = new java.awt.Color(94, 134, 193);
    public static Color TABLE_COLOR = new java.awt.Color(255, 255, 255);
    public static Color DISPALY_ITEMS_TABLE_COLOR = new java.awt.Color(255, 255, 240);
    public static Color BUTTON_PANEL_BACKGROUND_COLOR = new java.awt.Color(230, 230, 250);
    public static Color BUTTON_BACKGROUN_COLOR = new java.awt.Color(255, 255, 240);
    
     
  //SalesJournal attributes index
    public static final int CUSTOMERID = 0;
    public static final int SO = 1;
    public static final int DATE = 2;
    public static final int SHIPBY = 3; 
    public static final int SHIPBYFALSE = 4; 
    public static final int DROPSHIP = 5;
    public static final int SHIPTONAME = 6;
    public static final int SHIPTOADDRESS1 = 7;
    public static final int SHIPTOADDRESS2 = 8;
    public static final int SHIPTOCITY = 9;
    public static final int SHIPTOSTATE = 10;
    public static final int SHIPTOPHONE = 11;
    public static final int SHIPTOZIPCODE = 12;
    public static final int SHIPTOZCODECOUNTRY = 13;
    public static final int SHIPTOZIPCOUNTRY = 14;
    public static final int CUSTPO = 15;
    public static final int ACCOUNT_RECE_ID = 16;
    public static final int SHIPVIA = 17;
    public static final int DISCOUNT_AMOUNT = 18;
    public static final int DISPLAY_TERMS = 19;
    public static final int DISPLAY_TYPE = 20;
    public static final int SALES_REPID = 21;
    public static final int ACCOUNT_RECEIVABLE = 26;
    public static final int NOTE_PRINT = 27;
    public static final int NUMBEROFDISTRIBUTION = 28;
    public static final int SODISTRIBUTION = 29;
    public static final int QTY = 30;
    public static final int ITEMID = 31;
    public static final int DESCRIPTION = 32;
    public static final int GL_ACCOUNT = 33;
    public static final int UNIT_PRICE = 34;
    public static final int TAX_TYPE = 35;
    public static final int UPC_SKU = 36;
    public static final int WEIGHT = 37;
    public static final int U_M = 38;
    public static final int STOCKING_QTY = 39;
    public static final int STOCKING_UNIT_PRICE = 40;
    public static final int AMOUNT = 41;
    public static final int PROPOSAL_ACCEPTED = 42;
    
    
    public static String serial_list = "";
  
}
