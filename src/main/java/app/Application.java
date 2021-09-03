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

            Parser parser = new EmailParser294681();
            var parsedString = parser.parse(lastMessage);
            System.out.println("\n****************************************\nRequest:");
            System.out.println(parsedString);

            System.out.println();

            String uri = "https://itintegrator.bitrix24.ru/rest/25/4rzi9f7cqfxvzrlh/crm.lead.add.json?"
                    + URLEncoder.encode(parsedString, StandardCharsets.UTF_8);

//
//            /*var paramsString = parsedString.split("&");
//
//            Map<String, String> paramMap = new LinkedHashMap<>();
//            for (var str : paramsString) {
//                int indexOfEquals = str.indexOf("=");
//                paramMap.put(str.substring(0, indexOfEquals), str.substring(indexOfEquals + 1));
//            }
//
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(uri);
//
//            List<NameValuePair> postParameters = new ArrayList<>();
//
//            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
//                //System.out.println(entry.getKey() + " " + entry.getValue());
//                postParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//            }
//
//            httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));*/

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(uri);
            CloseableHttpResponse response = httpClient.execute(httpPost);


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
