package spirit.fitness.scanner.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFileChooser;
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

	public static void displayError(LoadingFrameHelper loadingframe) {
		if (isNetworkIssue) {
			loadingframe.setVisible(false);
			loadingframe.dispose();
			isNetworkIssue = false;
			netWorkHandler = null;
			JOptionPane.showMessageDialog(null, "Please check network configuration.");
			NetWorkHandler.backUpSerialNo(loadingframe);
		}
	}

	public static void backUpSerialNo(LoadingFrameHelper loadingframe) 
    {
    	if(Constrant.serial_list != null && !Constrant.serial_list.equals("")) 
		{
    		
    		JFileChooser chooser = new JFileChooser();
    		chooser.setDialogTitle("Please save scanned serial number list to directory : ");
    		int returnVal = chooser.showSaveDialog(loadingframe);
    		if (returnVal == JFileChooser.APPROVE_OPTION) {
    		    FileOutputStream stream = null;
    		    PrintStream out = null;
    		    try {
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    		        File file = new File(chooser.getSelectedFile().getAbsolutePath()+timeStamp+"_"+Calendar.getInstance().getTimeInMillis()+".txt");
    		        stream = new FileOutputStream(file); 
    		        String text = Constrant.serial_list;
    		        String[] tmp = text.split("\n");
    		        out = new PrintStream(stream);
    		        for(String s : tmp) 
    		        {
    		        	out.println(s);        
    		        }
    		        Constrant.serial_list = null;
    		    } catch (Exception ex) {
    		        //do something
    		    } finally {
    		        try {
    		            if(stream!=null) stream.close();
    		            if(out!=null) out.close();
    		        } catch (Exception ex) {
    		            //do something
    		        }
    		    }
    		}
			
			
		}
	}
}