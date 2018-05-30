package spirit.fitness.scanner.util;

import javax.swing.JOptionPane;

public class NetWorkHandler {

	private static NetWorkHandler netWorkHandler;
	
	private static boolean isNetworkIssue;
	
	public static NetWorkHandler getInstance() {
		if (netWorkHandler == null) {
			netWorkHandler = new NetWorkHandler();
			isNetworkIssue = true;
		}
		return netWorkHandler;
	}
	
	public static void displayError(LoadingFrameHelper loadingframe) 
	{
		if(isNetworkIssue) 
		{
			loadingframe.setVisible(false);
			loadingframe.dispose();
			isNetworkIssue = false;
			JOptionPane.showMessageDialog(null, "Please check network configuration.");
		}
	}
}
