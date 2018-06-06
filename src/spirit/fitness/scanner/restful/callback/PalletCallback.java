package spirit.fitness.scanner.restful.callback;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import spirit.fitness.scanner.model.Containerbean;
import spirit.fitness.scanner.model.Palletbean;

public interface PalletCallback {
	@Headers("Content-Type: application/json")
	
	@GET("FGService/api/Pallets")
	Call<List<Palletbean>> getAllItems();
	
	@GET("FGService/api/Pallets/date/{salesOrder}")
	Call<List<Palletbean>> getItemsBySalesOrder(@Path("salesOrder") String salesOrder);
	
	
	@GET("FGService/api/Pallets/date/{date}")
	Call<List<Palletbean>> getItemsByDate(@Path("date") String date);
	
	
	//Post ArrayList items
	@POST("FGService/api/Pallets")
	Call<List<Palletbean>> createItem(@Body List<Palletbean> itembean);

	
	//PUT more than one item
    @PUT("FGService/api/Pallets")
	Call<List<Palletbean>> updateItem(@Body List<Palletbean> itembean);

	//Delete no work
	@DELETE("FGService/api/Pallets/{Seq}")
	Call<Palletbean> deleteItem(@Path("Seq") Integer Seq);
}
