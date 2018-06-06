package spirit.fitness.scanner.model;

import com.google.gson.annotations.SerializedName;

public class Palletbean {
	 @SerializedName("seq")
	 public Integer seq;
	 @SerializedName("ItemID")
	 public String itemID;
	 @SerializedName("SalesOrder")
	 public String salesOrder;
	 @SerializedName("TrackingNo")
	 public String trackingNo;
	 @SerializedName("ShipCity")
	 public String shipCity;
	 @SerializedName("ShipState")
	 public String shipState;
	 @SerializedName("BillTo")
	 public String billTo;
	 @SerializedName("ShipVia")
	 public String shipVia;
	 @SerializedName("ShippedDate")
	 public String shippedDate;
	 @SerializedName("CreatedDate")
	 public String createdDate;

}