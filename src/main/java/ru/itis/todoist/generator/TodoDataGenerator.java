package ru.itis.todoist.generator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.itis.todoist.model.TodoData;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TodoDataGenerator {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            return;
        }

        int count = Integer.parseInt(args[0]);
        Path output = Path.of(args[1]);

        writeTodos(generateTodos(count), output);
    }

    private static List<TodoData> generateTodos(int count) {
        Random random = new Random();
        List<TodoData> todos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            todos.add(new TodoData(
                    "TITLE " + randomString(random, 8),
                    "DESCRIPTION " + randomString(random, 12)
            ));
        }

        return todos;
    }

    private static void writeTodos(List<TodoData> todos, Path output) throws Exception {
        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();
        Element root = document.createElement("todos");
        document.appendChild(root);

        for (TodoData todo : todos) {
            Element todoElement = document.createElement("todo");
            root.appendChild(todoElement);

            appendTextElement(document, todoElement, "title", todo.getTitle());
            appendTextElement(document, todoElement, "description", todo.getDescription());
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(new DOMSource(document), new StreamResult(output.toFile()));
    }

    private static void appendTextElement(Document document, Element parent, String name, String text) {
        Element element = document.createElement(name);
        element.setTextContent(text);
        parent.appendChild(element);
    }

    private static String randomString(Random random, int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return result.toString();
    }
}
