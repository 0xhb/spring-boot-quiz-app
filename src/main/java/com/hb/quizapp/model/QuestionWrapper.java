package com.hb.quizapp.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionWrapper {
    private int id;
    private String question;
    private List<String> options = new ArrayList<String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
