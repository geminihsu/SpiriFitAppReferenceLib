package spirit.fitness.scanner.util;



import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import spirit.fitness.scanner.common.Constrant;
import spirit.fitness.scanner.model.Containerbean;

import java.util.Properties;




public class EmailHelper {
	private final static String CONTAINER_NO = "Container# ";
	private final static String RECEIVED_DATE = "Received Date : ";
	private final static String SCANNED_DATE = "Scan Date : ";
	private final static String MODEL_NO = "Model No. : ";
	private final static String MODEL_DESC = "Model Description : ";
	private final static String SERIAL_START = "Serial No. Begin : ";
	private final static String SERIAL_END = "Serial No. End : ";
	private final static String QTY = "Quantity : ";
	

	public static void sendMail(String scanDate,Containerbean containInfo,String modelNo, String items) 
	{

	   final String username = "geminih@spiritfitness.com";
       final String password = "$pirit3Ma1l";

       Properties props = new Properties();
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.port", "587");

       Session session = Session.getInstance(props,
               new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(username, password);
                   }
               });

       try {

    	   //String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress("geminih@spiritfitness.com"));
           message.setRecipients(Message.RecipientType.TO,
                   InternetAddress.parse("spiritfitness.mail.service@gmail.com"));
           message.setSubject(scanDate +" Container #" + containInfo.ContainerNo );
           String containInfoTxt = CONTAINER_NO + containInfo.ContainerNo +"\n" + 
        		   SCANNED_DATE + scanDate + "\n" +
        		   MODEL_NO + modelNo + "\n" +
        		   MODEL_DESC + Constrant.models.get(modelNo).Desc + "\n" +
        		   SERIAL_START + containInfo.SNBegin + "\n" +
        		   SERIAL_END + containInfo.SNEnd + "\n" +
        		   QTY + String.valueOf( Integer.valueOf(containInfo.SNEnd.substring(10, 16))
       					- Integer.valueOf(containInfo.SNBegin.substring(10, 16)) + 1) + "\n";


           message.setText(containInfoTxt + "--------------------------------------------\n" + "Scanned SN : \n" + items);		   

           Transport.send(message);

           System.out.println("Mail sent succesfully!");

       } catch (MessagingException e) {
           throw new RuntimeException(e);
       }
    }
	
}
