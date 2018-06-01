package spirit.fitness.scanner.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import spirit.fitness.scanner.common.Constrant;

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
            netWorkHandler = null;
            NetWorkHandler.backUpSerialNo();
            JOptionPane.showMessageDialog(null, "Please check network configuration.");
           
        }
    }
    
    public static void backUpSerialNo() 
    {
    	if(!Constrant.serial_list.equals("")) 
		{
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				List<String> lines = Arrays.asList(Constrant.serial_list);
				Path file = Paths.get(timeStamp+"_"+Calendar.getInstance().getTimeInMillis()+".txt");
				try {
					Files.write(file, lines, Charset.forName("UTF-8"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
		}
    }
}