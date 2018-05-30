package spirit.fitness.scanner.restful;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import spirit.fitness.scanner.common.Constrant;
import spirit.fitness.scanner.common.HttpRequestCode;
import spirit.fitness.scanner.model.Containerbean;
import spirit.fitness.scanner.model.Palletbean;
import spirit.fitness.scanner.restful.callback.ContainerCallback;
import spirit.fitness.scanner.restful.callback.PalletCallback;
import spirit.fitness.scanner.restful.listener.ContainerCallBackFunction;
import spirit.fitness.scanner.restful.listener.PalletCallBackFunction;

public class PalletRepositoryImplRetrofit {
	// retrieve return code number
		private PalletCallBackFunction palletServiceCallBackFunction;

		public void setPalletServiceCallBackFunction(PalletCallBackFunction _palletServiceCallBackFunction) {
			palletServiceCallBackFunction = _palletServiceCallBackFunction;

		}

		public List<Palletbean> createItem(List<Palletbean> items) throws Exception {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl)
					.addConverterFactory(GsonConverterFactory.create()).build();
			PalletCallback service = retrofit.create(PalletCallback.class);
			Response<List<Palletbean>> request = service.createItem(items).execute();
			int code = request.code();

			List<Palletbean> resultData = null;

			if (code == HttpRequestCode.HTTP_REQUEST_OK)
				resultData = request.body();

			if (palletServiceCallBackFunction != null)
				palletServiceCallBackFunction.addPallet(resultData);

			return resultData;

		}

		public List<Palletbean> getAllItems() throws Exception {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl)
					.addConverterFactory(GsonConverterFactory.create()).build();
			PalletCallback service = retrofit.create(PalletCallback.class);
			Response<List<Palletbean>> request = service.getAllItems().execute();
			int code = request.code();

			List<Palletbean> result = retriveCode(code, request);

			return result;
		}

		public List<Palletbean> getItemsByDate(String date) throws Exception {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl)
					.addConverterFactory(GsonConverterFactory.create()).build();
			PalletCallback service = retrofit.create(PalletCallback.class);
			Call<List<Palletbean>> items = service.getItemsByDate(date);
			// System.out.println(items.execute().toString());
			return items.execute().body();
		}

		public List<Palletbean> getItemsBySalesOrder(String salesOrder) throws Exception {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl)
					.addConverterFactory(GsonConverterFactory.create()).build();
			PalletCallback service = retrofit.create(PalletCallback.class);

			Call<List<Palletbean>> items = service.getItemsBySalesOrder(salesOrder);
			// System.out.println(items.execute().toString());
			return items.execute().body();
		}

		public List<Palletbean> updateItem(List<Palletbean> item) throws Exception {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constrant.webUrl)
					.addConverterFactory(GsonConverterFactory.create()).build();
			PalletCallback service = retrofit.create(PalletCallback.class);

			Response<List<Palletbean>> request = service.updateItem(item).execute();
			int code = request.code();

			List<Palletbean> result = retriveCode(code, request);
			return result;
		}

		

		private List<Palletbean> retriveCode(int code, Response<List<Palletbean>> request) {
			List<Palletbean> resultData = null;

			if (palletServiceCallBackFunction != null) {
				palletServiceCallBackFunction.resultCode(code);
				if (code == HttpRequestCode.HTTP_REQUEST_OK) {
					resultData = request.body();
					palletServiceCallBackFunction.getPalletItems(resultData);
				}
			}
			return resultData;
		}
}
