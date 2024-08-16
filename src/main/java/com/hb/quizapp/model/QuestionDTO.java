package com.hb.quizapp.model;


import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {
    private int id;
    private String question;
    private String category;
    private String difficulty;
    private List<String> options = new ArrayList<String>();
    private String answer;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }


    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionDTO() {
    }
    public QuestionDTO(int id, String question, String answer, List<String> options, String category, String difficulty) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.category = category;
        this.difficulty = difficulty;
    }
}
