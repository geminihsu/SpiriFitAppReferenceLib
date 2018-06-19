package spirit.fitness.scanner.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
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

	public static void sendMail(String scanDate, List<Containerbean> containInfo, String items,String email) {

		final String username = "donotreply@spiritfitness.com";
		final String password = "$pirit3Ma1l";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			// String timeStamp = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss").format(Calendar.getInstance().getTime());

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("donotreply@spiritfitness.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject(scanDate + " Container #" + containInfo.get(0).ContainerNo);

			String[] scanitems = items.split("\n");
			String mailContent = "------------------------------------------------------------<br>" ;

			int startIndex = 0;
			for (Containerbean container : containInfo) {
				String itemsArray = "";
				String containInfoTxt =  CONTAINER_NO + container.ContainerNo + "<br>" + RECEIVED_DATE + containInfo.get(0).date + "<br>" + SCANNED_DATE + scanDate + "<br>"
						+ MODEL_NO + container.SNEnd.substring(0, 6) + "<br>" + MODEL_DESC + Constrant.models.get(container.SNEnd.substring(0, 6)).Desc + "<br>"
						+ SERIAL_START + container.SNBegin + "<br>" + SERIAL_END + container.SNEnd + "<br>" + QTY
						+ String.valueOf(Integer.valueOf(container.SNEnd.substring(10, 16))
								- Integer.valueOf(container.SNBegin.substring(10, 16)) + 1)
						+ "<br>";

				int startIdx = Integer.valueOf(container.SNBegin.substring(10, 16)) ;
				int endIdx = Integer.valueOf(container.SNEnd.substring(10, 16)) ;
				
				int qty = endIdx - startIdx+ 1;
				String[] scanSN = new String[qty];
				System.arraycopy( scanitems, startIndex, scanSN, 0, qty );
				startIndex= startIndex + qty;
				
				for(String s : scanSN) 
				{
					if(Integer.valueOf(s.substring(10,16)) - startIdx < 0 || endIdx - Integer.valueOf(s.substring(10,16)) <0)
						itemsArray += "<font color=\"red\">"+s + "</font>"+"<br>";
					else
						itemsArray += s+"<br>";
				}
				containInfoTxt += "------------------------------------------------------------<br>" + "Scanned SN : <br>"+"------------------------------------------------------------<br>" + itemsArray;

				mailContent += containInfoTxt +"--------------------------------------------------------<br>";
			}
			
			
			//message.setText(mailContent);
			message.setContent(mailContent,"text/html");
			Transport.send(message);

			System.out.println("Mail sent succesfully!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
