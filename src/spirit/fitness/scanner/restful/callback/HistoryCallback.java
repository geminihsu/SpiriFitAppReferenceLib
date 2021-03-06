package spirit.fitness.scanner.restful.callback;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import spirit.fitness.scanner.model.DailyShippingReportbean;
import spirit.fitness.scanner.model.Historybean;
import spirit.fitness.scanner.model.Itembean;
import spirit.fitness.scanner.model.SerialNoRecord;
/**
 * Created by User on 5/1/2017.
 */

public interface HistoryCallback {
    
	@Headers("Content-Type: application/json")
	@GET("FGService/api/Histories")
	Call<List<Historybean>> getAllItems();

	@GET("FGService/api/Histories/date/{date}")
	Call<List<Historybean>> getItemsByDate(@Path("date") String location);
	
	@GET("FGService/api/Histories/model/{modelNo}")
	Call<List<Historybean>> getItemsByModelNo(@Path("modelNo") Integer modelNo);
	@GET("FGService/api/Histories/salesOrder/{salesOrder}")
	Call<List<Historybean>> getItemsBySalesOrder(@Path("salesOrder") String modelNo);
	//Post one item
	/*@POST("/api/FGInventory")
	Call<Itembean> createItem(@Body Itembean itembean);*/
	@GET("FGService/api/Histories/dailyship/{date}")
	Call<List<DailyShippingReportbean>> getDailyReportItems(@Path("date") String location);
	
	@GET("FGService/api/Histories/serialNo/{serialNo}")
	Call<List<SerialNoRecord>> getItemsBySerialNo(@Path("serialNo") String serialNo);
	
	//Post ArrayList items
	@POST("FGService/api/Histories")
	Call<List<Historybean>> createItem(@Body List<Historybean> itembean);

	/*//PUT one item
	@PUT("/api/FGInventory/{seq}")
	Call<Itembean> updateItem(@Path("seq") Integer seq, @Body Itembean item);*/

	
	//PUT more than one item
	@PUT("FGService/api/Histories")
	Call<List<Historybean>> updateItem(@Body List<Historybean> itembean);

	//Delete no work
	@DELETE("FGService/api/Histories/{seq}")
	Call<Historybean> deleteItem(@Path("seq") Integer seq);

}
