package com.example.planai_front.create;

public class Event {
    private String name;
    private String description;

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}