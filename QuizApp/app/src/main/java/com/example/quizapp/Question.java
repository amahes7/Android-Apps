package com.example.quizapp;

public class Question {
    private String question;
    private int id;

    public Question(int newId, String question) {
        setId(newId);
        setQuestion(question);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
