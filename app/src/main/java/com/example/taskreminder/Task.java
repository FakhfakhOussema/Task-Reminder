package com.example.taskreminder;

public class Task
{
    private int id;

    private String title ,description , date;

    public Task(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Task(int id, String title, String description, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Task()
    {}

    @Override
    public String toString() {
        return "\n"+"Title : " + title + '\n' +
                "Description : " + description + '\n' +
                "Date : " + date+'\n' ;
    }
    public int getId () { return id; }
    public void setId (int id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
