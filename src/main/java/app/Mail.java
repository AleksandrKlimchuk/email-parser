package app;

import javax.mail.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class Mail {

    private Store mailStore;

    public Mail(String configureFileName) throws IOException,
                                                 MessagingException {

        configureFileName = Objects.requireNonNull(configureFileName);

        try (FileInputStream configFile = new FileInputStream(configureFileName);) {
            Properties properties = new Properties();
            properties = new Properties();
            properties.load(configFile);

            String username = properties.getProperty("mail.user");
            String password = properties.getProperty("mail.password");
            String host = properties.getProperty("mail.host");

            properties = new Properties();
            properties.put("mail.store.protocol", "imaps");

            mailStore = Session.getInstance(properties).getStore();
            mailStore.connect(host, username, password);
        }
    }

    public String getLastMessageAsString() throws MessagingException, IOException {
        try (Folder inbox = mailStore.getFolder("INBOX");) {
            inbox.open(Folder.READ_ONLY);

            Message message = inbox.getMessage(inbox.getMessageCount());
            Multipart multipart = (Multipart) message.getContent();

            BodyPart body = multipart.getBodyPart(0);
            String messageContent = body.getContent().toString();
            String translatedMessageContent = new String(
                    messageContent.getBytes(StandardCharsets.UTF_8)
            );

            return translatedMessageContent;
        }
    }

    public void close() throws MessagingException {
        mailStore.close();
    }
}
