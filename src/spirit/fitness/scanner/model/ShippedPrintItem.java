package spirit.fitness.scanner.model;

public class ShippedPrintItem {
	public int getQty() {
		return Qty;
	}
	public void setQty(int qty) {
		Qty = qty;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String item) {
		this.itemID = item;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	private int Qty = 0;
	private String itemID;
	private String model;
	private String trackingNo;
	
	
}
