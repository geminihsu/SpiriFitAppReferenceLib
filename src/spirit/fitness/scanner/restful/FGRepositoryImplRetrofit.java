package spirit.fitness.scanner.restful;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import spirit.fitness.scanner.common.Constrant;
import spirit.fitness.scanner.common.HttpRequestCode;
import spirit.fitness.scanner.model.Itembean;
import spirit.fitness.scanner.restful.callback.InventoryCallback;
import spirit.fitness.scanner.restful.listener.InventoryCallBackFunction;
import spirit.fitness.scanner.util.LoadingFrameHelper;
import spirit.fitness.scanner.util.NetWorkHandler;

public class FGRepositoryImplRetrofit {

	/*
	 * public Itembean updateItem(Itembean item) throws Exception { Retrofit
	 * retrofit = new
	 * Retrofit.Builder().baseUrl(Constrant.webUrl).addConverterFactory(
	 * GsonConverterFactory.create()) .build(); SpiritfitResource service =
	 * retrofit.create(SpiritfitResource.class);
	 * 
	 * return service.updateItem(item.seq, item).execute().body(); }
	 */

	// retrieve return code number
	private InventoryCallBackFunction inventoryServiceCallBackFunction;

	public void setinventoryServiceCallBackFunction(InventoryCallBackFunction _inventoryCallBackFunction) {
		inventoryServiceCallBackFunction = _inventoryCallBackFunction;

	}

	public List<Itembean> updateItem(List<Itembean> item) throws Exception {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);

		Response<List<Itembean>> request = service.updateItem(item).execute();
		int code = request.code();
		List<Itembean> result = retriveCode(code, request);

		return result;
	}

	/*
	 * public Itembean createItem(Itembean item) throws Exception { Retrofit
	 * retrofit = new
	 * Retrofit.Builder().baseUrl(Constrant.webUrl).addConverterFactory(
	 * GsonConverterFactory.create()) .build(); SpiritfitResource service =
	 * retrofit.create(SpiritfitResource.class); return
	 * service.createItem(item).execute().body();
	 * 
	 * }
	 */

	public List<Itembean> createItem(List<Itembean> items) {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);
		List<Itembean> result = null;
		try {
			Response<List<Itembean>> request = service.createItem(items).execute();
			int code = request.code();
			result = retriveCode(code, request);
		} catch (Exception e) {
			NetWorkHandler.getInstance();
			// JOptionPane.showMessageDialog(null, "Please check network configuration.");
			if (inventoryServiceCallBackFunction != null)
				inventoryServiceCallBackFunction.exception(e.toString());
		}

		return result;

	}

	public List<Itembean> getAllItems() throws Exception {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);

		Response<List<Itembean>> request = service.getAllItems().execute();
		int code = request.code();
		List<Itembean> result = retriveCode(code, request);

		return result;
	}

	public List<Itembean> getReceiveItemBySNList(List<Itembean> items) {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);
		List<Itembean> result = null;
		try {
			Response<List<Itembean>> request = service.getReceiveItemExits(items).execute();
			int code = request.code();

			if (code == HttpRequestCode.HTTP_REQUEST_OK)
				result = request.body();

			if (inventoryServiceCallBackFunction != null)
				inventoryServiceCallBackFunction.checkReceiveItem(result);
		} catch (Exception e) {
			NetWorkHandler.getInstance();
			// JOptionPane.showMessageDialog(null, "Please check network configuration.");
			if (inventoryServiceCallBackFunction != null)
				inventoryServiceCallBackFunction.exception(e.toString());
		}
		return result;
	}

	public List<Itembean> getMoveItemBySNList(List<Itembean> items) {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);
		List<Itembean> result = null;
		try {
			Response<List<Itembean>> request = service.getMoveItemsBySNxits(items).execute();
			int code = request.code();

			if (code == HttpRequestCode.HTTP_REQUEST_OK)
				result = request.body();

			if (inventoryServiceCallBackFunction != null)
				inventoryServiceCallBackFunction.checkMoveItems(result);
		} catch (Exception e) {
			NetWorkHandler.getInstance();
			// JOptionPane.showMessageDialog(null, "Please check network configuration.");
			if (inventoryServiceCallBackFunction != null)
				inventoryServiceCallBackFunction.exception(e.toString());
		}
		return result;
	}

	public List<Itembean> getItemsZone2BySNList(String salesOrder, List<Itembean> items) {

		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);
		List<Itembean> result = null;
		try {
			Response<List<Itembean>> request = service.getItemsZone2BySNExits(salesOrder, items).execute();
			int code = request.code();

			if (code == HttpRequestCode.HTTP_REQUEST_OK || code == HttpRequestCode.HTTP_REQUEST_ACCEPTED)
				result = request.body();
			else if (code == HttpRequestCode.HTTP_REQUEST_INSERT_DATABASE_ERROR) {
				result = request.body();
				System.out.println("The item does not exists on peach tree.");
			}

			if (inventoryServiceCallBackFunction != null)
				inventoryServiceCallBackFunction.checkInventoryZone2Items(code, result);
		} catch (Exception e) {
			NetWorkHandler.getInstance();
			// JOptionPane.showMessageDialog(null, "Please check network configuration.");
			if (inventoryServiceCallBackFunction != null)
				inventoryServiceCallBackFunction.exception(e.toString());
		}
		return result;
	}

	public List<Itembean> getItemsByLocation(Integer location) throws Exception {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);

		Response<List<Itembean>> request = service.getItemsByLocation(location).execute();
		int code = request.code();
		List<Itembean> result = retriveCode(code, request);
		return result;
	}

	public List<Itembean> getItemsByModel(String modelNo) throws Exception {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);

		Response<List<Itembean>> request = service.getItemsByModelNo(modelNo).execute();
		int code = request.code();
		List<Itembean> result = retriveCode(code, request);
		return result;
	}

	public List<Itembean> getItemsByModelAndLocation(String modelNo, Integer location) throws Exception {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);

		Response<List<Itembean>> request = service.getItemsByModelNoAndLocation(modelNo, location).execute();
		int code = request.code();
		List<Itembean> result = retriveCode(code, request);
		return result;
	}

	public List<Itembean> getItemsByModelAndCount(String modelNo, Integer count) throws Exception {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);

		Response<List<Itembean>> request = service.getItemsByModelNoAndCount(modelNo, count).execute();
		int code = request.code();
		List<Itembean> result = retriveCode(code, request);
		return result;
	}

	public List<Itembean> getItemsByModelAndDate(String modelNo, String date) throws Exception {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);

		Response<List<Itembean>> request = service.getItemsByModelNoAndDate(modelNo, date).execute();
		int code = request.code();
		List<Itembean> result = retriveCode(code, request);
		return result;
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * FGRepositoryImplRetrofit fgRepository = new FGRepositoryImplRetrofit();
	 * 
	 * List<Itembean> items = new ArrayList<>();
	 * 
	 * Itembean item1 = new Itembean();
	 * 
	 * item1.SN = "1580121711003849"; item1.date = "2018-01-08 14:56:58.000";
	 * item1.Location = "721"; item1.ModelNo = "158012"; items.add(item1);
	 * 
	 * Itembean item2 = new Itembean();
	 * 
	 * item2.SN = "1580121711003848"; item2.date = "2018-01-08 14:56:58.000";
	 * item2.Location = "721"; item2.ModelNo = "158012"; items.add(item2);
	 * 
	 * /* Itembean item = new Itembean();
	 * 
	 * 
	 * item.seq = 1; item.SN = "158012130800080"+String.valueOf(2); item.date =
	 * "2017-12-13 16:14:02.343"; item.Location = "111"; item.ModelNo = "158012";
	 * items.add(item);
	 */
	// Itembean fg = fgRepository.getAllItems().get(0);
	// Itembean fg = fgRepository.getItemsByModel(Integer.valueOf("158012")).get(0);

	// Itembean fg =
	// fgRepository.getItemsByModelAndDate(Integer.valueOf("166812"),"2017-01-01").get(0);

	// Itembean fg = fgRepository.getItemsByLocation(Integer.valueOf("025")).get(0);
	// String fg = fgRepository.createItem(items).get(0).SN;
	// Itembean fg = fgRepository.updateItem(item);

	// fgRepository.deleteItem(items);
	// System.out.println(fg);

	// bookRepository.deleteBook(book.getId());
	// }

	public List<Itembean> deleteItem(List<Itembean> items) throws Exception {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
				.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl).client(okHttpClient)

				.addConverterFactory(GsonConverterFactory.create()).build();
		InventoryCallback service = retrofit.create(InventoryCallback.class);
		Call<List<Itembean>> data = service.deleteItems(items);
		return data.execute().body();
	}

	/*
	 * public List<Itembean> deleteItem(List<Itembean> items) throws Exception {
	 * Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl)
	 * .addConverterFactory(GsonConverterFactory.create()).build();
	 * InventoryCallback service = retrofit.create(InventoryCallback.class);
	 * Call<List<Itembean>> data = service.deleteItem(items); return
	 * data.execute().body(); }
	 */

	public Itembean findItemBySeq(Integer seq) {
		return null;
	}

	private List<Itembean> retriveCode(int code, Response<List<Itembean>> request) {
		List<Itembean> resultData = null;

		if (inventoryServiceCallBackFunction != null) {
			inventoryServiceCallBackFunction.resultCode(code);
			if (code == HttpRequestCode.HTTP_REQUEST_OK) {
				resultData = request.body();
				inventoryServiceCallBackFunction.getInventoryItems(resultData);
			}

		}
		return resultData;
	}

}
