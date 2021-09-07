package app;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        try {

            Mail mail = new Mail("config.properties");
            String lastMessage = mail.getLastMessageAsString();
            System.out.println("Last message:");
            System.out.println(mail.getLastMessageAsString());


            Parser parser = new EmailParser294675();
            var parsedString = parser.parse(lastMessage);
            System.out.println("\n****************************************\nRequest:");
            System.out.println(parsedString);

            System.out.println();


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
