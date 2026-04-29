package ru.itis.todoist.model;

public class TodoData {
    private final String title;
    private final String description;

    public TodoData(String title) {
        this(title, "");
    }

    public TodoData(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
