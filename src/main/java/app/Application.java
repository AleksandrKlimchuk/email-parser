package app;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.http.HttpClient;

public class Application {
    public static void main(String[] args) {
        try {
            Mail mail = new Mail("config.properties");
            String lastMessage = mail.getLastMessageAsString();
            System.out.println(mail.getLastMessageAsString());

            EmailParser parser = new EmailParser();
            var parsedString = parser.parse(lastMessage);
            System.out.println(parsedString);

            System.out.println();

            String jsonString = JsonConverterFromMap.convert(parsedString);
            System.out.println(jsonString);

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://127.0.0.1:80");
            HttpEntity stringEntity = new StringEntity(jsonString, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
