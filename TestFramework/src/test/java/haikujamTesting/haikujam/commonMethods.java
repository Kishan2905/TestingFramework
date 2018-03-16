package haikujamTesting.haikujam;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class commonMethods extends AppiumDriverBase {
	static SessionId password=((AndroidDriver)driver).getSessionId();	
	public static WebElement locateElement(String elementWithProp) {
		String a[]=elementWithProp.split(":");
		String locator=a[0];
		String ele=a[1];
		System.out.println(a[0]);
		System.out.println(a[1]);
		WebElement element = null;
		switch (locator) {
		case "id":
			element=driver.findElement(By.id(ele));
			break;
		case "xpath":
			element=driver.findElement(By.xpath(ele));
		default:
			break;
		}
		return element;
	}
	
	public static void mailReport() {
		
        // Create all the needed properties
        Properties connectionProperties = new Properties();
        // SMTP host
        connectionProperties.put("mail.smtp.host", "smtp.itcuties.com");
        // Is authentication enabled
        connectionProperties.put("mail.smtp.auth", "true");
        // Is StartTLS enabled
        connectionProperties.put("mail.smtp.starttls.enable", "true");
        // SSL Port
        connectionProperties.put("mail.smtp.socketFactory.port", "465");
        // SSL Socket Factory class
        connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // SMTP port, the same as SSL port :)
        connectionProperties.put("mail.smtp.port", "465");
         
        System.out.print("Creating the session...");
         
        // Create the session
        Session session = Session.getDefaultInstance(connectionProperties,
                new javax.mail.Authenticator() {    // Define the authenticator
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("Your mail ID","Your Password");
                    }
                });
         
        System.out.println("done!");
         
        // Create and send the message
        try {
            // Create the message 
            Message message = new MimeMessage(session);
            // Set sender
            message.setFrom(new InternetAddress("Your Mail id"));
            // Set the recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Receiver mail id"));
            // Set message subject
            message.setSubject("XYZ");
            // Set message text
            message.setText("Report ;)");
             
            System.out.print("Sending message...");
            // Send the message
            Transport.send(message);
             
            System.out.println("done!");
             
        } catch (Exception e) {
            e.printStackTrace();
        }
         
    }
	public static void mailTheReport(String reportFileName) {
		  final String username = "xyz";
		  final String password = "pqr";

		   Properties props = new Properties();
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.host", "smtp.gmail.com");
		  props.put("mail.smtp.port", "587");

		   Session session = Session.getInstance(props,
		    new javax.mail.Authenticator() {
		     protected PasswordAuthentication getPasswordAuthentication() {
		      return new PasswordAuthentication(username, password);
		     }
		    });

		   try {

		    Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(username));
		   message.setRecipients(Message.RecipientType.TO,
		     InternetAddress.parse("to mail"));
		   message.setSubject("Reports Availalbe!");
		   message.setText("Dear Mail Crawler,"
		     + "\n\n No spam to my email, please!");

		    MimeBodyPart messageBodyPart = new MimeBodyPart();

		    Multipart multipart = new MimeMultipart();

		    messageBodyPart = new MimeBodyPart();
		   String file = "D:/Appium/haikujam/test-output/";
		   String fileName = reportFileName;
		   DataSource source = new FileDataSource(file + fileName);
		   messageBodyPart.setDataHandler(new DataHandler(source));
		   messageBodyPart.setFileName(fileName);
		   multipart.addBodyPart(messageBodyPart);

		    message.setContent(multipart);
		   System.out.println("Sending");
		   Transport.send(message);
		   System.out.println("Done");
		  } catch (MessagingException e) {
		   throw new RuntimeException(e);
		  }
		 }
	
}

