package spirit.fitness.scanner.restful.listener;

import java.util.List;

import spirit.fitness.scanner.model.CustOrderbean;
import spirit.fitness.scanner.model.DailyShippingReportbean;
import spirit.fitness.scanner.model.Historybean;
import spirit.fitness.scanner.model.Itembean;
import spirit.fitness.scanner.model.SerialNoRecord;


public interface HistoryCallBackFunction {
	public void resultCode(int code);
	public void checkHistoryItemsBySalesOrder(List<Historybean> items);
	public void getHistoryItems(List<Historybean> items);
	public void getDailyShippingItems(List<DailyShippingReportbean> items);
	public void getSerialNoRecord(List<SerialNoRecord> items);
	public void exception(String error);
}