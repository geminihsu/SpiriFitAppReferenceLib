package spirit.fitness.scanner.model;

import com.google.gson.annotations.SerializedName;

public class DailyShippingReportbean{
	
	@SerializedName("CreatedDate")
	public String createdDate;
	@SerializedName("SO")
	public String salesOrder;
	@SerializedName("ItemID")
	public String itemID;
	@SerializedName("QTY")
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
	
}
