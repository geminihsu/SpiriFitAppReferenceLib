package spirit.fitness.scanner.restful.listener;

import java.util.List;

import spirit.fitness.scanner.model.Containerbean;
import spirit.fitness.scanner.model.Palletbean;

public interface PalletCallBackFunction {
	public void resultCode(int code);
	public void addPallet(List<Palletbean> items);
	public void getPalletItems(List<Palletbean> items);
}
