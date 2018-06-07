package spirit.fitness.scanner.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DailyShippingReportbean{
	
	@SerializedName("CreatedDate")
	public String createdDate;
	@SerializedName("SO")
	public String salesOrder;
	@SerializedName("ItemID")
	public String itemID;
	@SerializedName("FG")
	public String fg;
	@SerializedName("Qty")
	public String qty;
	@SerializedName("TrackingNo")
	public String trackingNo;
	@SerializedName("ShipVia")
	public String shipVia;
	@SerializedName("ShipState")
	public String shipState;
	
	@SerializedName("ShipCity")
	public String shipCity;
	@SerializedName("ShippingDate")
	public String shippingDate;
	
	@SerializedName("SN")
	public String sn;
	
}
