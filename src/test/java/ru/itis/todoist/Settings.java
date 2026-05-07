package ru.itis.todoist;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.itis.todoist.model.AccountData;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public final class Settings {
    public static final String FILE = "/Settings.xml";

    private static final Document DOCUMENT;

    private static String baseUrl;
    private static String login;
    private static String password;

    static {
        try (InputStream inputStream = Settings.class.getResourceAsStream(FILE)) {
            DOCUMENT = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(inputStream);
            DOCUMENT.getDocumentElement().normalize();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private Settings() {
    }

    public static String getBaseUrl() {
        if (baseUrl == null) {
            baseUrl = read("baseUrl");
        }
        return baseUrl;
    }

    public static String getLogin() {
        if (login == null) {
            login = read("login");
        }
        return login;
    }

    public static String getPassword() {
        if (password == null) {
            password = read("password");
        }
        return password;
    }

    public static AccountData getValidAccount() {
        return new AccountData(getLogin(), getPassword());
    }

    private static String read(String tagName) {
        Element root = DOCUMENT.getDocumentElement();
        return root.getElementsByTagName(tagName).item(0).getTextContent().trim();
    }
}
