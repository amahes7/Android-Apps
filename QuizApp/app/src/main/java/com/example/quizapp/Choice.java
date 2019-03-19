package com.example.quizapp;

public class Choice {

    private String choice;
    private int id;
    private boolean isCorrect;
    private int qId;

    public Choice(int newId, String choice, Boolean isCorrect, int qId) {
        setId(newId);
        setChoice(choice);
        setIsCorrect(isCorrect);
        setQId(qId);
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getQId() {
        return qId;
    }

    public void setQId(int qId) {
        this.qId = qId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
