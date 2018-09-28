package spirit.fitness.scanner.model;

import com.google.gson.annotations.SerializedName;

public class SerialNoRecord {
  @SerializedName("SN")	
  public String SN;
  @SerializedName("Status")
  public String Status;
  @SerializedName("Date")
  public String Date;
  @SerializedName("Location")
  public String Location;
  @SerializedName("ContainNo")
  public String ContainNo;
  @SerializedName("ZoneCode")
  public int ZoneCode;
  @SerializedName("SalesOrder")
  public String SalesOrder;
}
