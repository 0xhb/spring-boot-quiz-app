package com.hb.quizapp;

import com.hb.quizapp.model.Question;
import com.hb.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Sample data
        Question question1 = new Question(1,
                "What is the capital of France?",
                "Paris",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"),
                "Geography",
                "Easy");

        Question question2 = new Question(2,
                "What is the largest planet in our solar system?",
                "Jupiter",
                Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"),
                "Science",
                "Medium");

        Question question3 = new Question(3,
                "Who wrote 'Romeo and Juliet'?",
                "William Shakespeare",
                Arrays.asList("William Shakespeare", "Charles Dickens", "J.K. Rowling", "Mark Twain"),
                "Literature",
                "Easy");

        Question question4 = new Question(4,
                "What is the chemical symbol for water?",
                "H2O",
                Arrays.asList("H2O", "CO2", "O2", "NaCl"),
                "Science",
                "Easy");

        Question question5 = new Question(5,
                "What is the fastest land animal?",
                "Cheetah",
                Arrays.asList("Cheetah", "Lion", "Horse", "Kangaroo"),
                "Biology",
                "Medium");


        // Save to the database
        questionRepository.saveAll(Arrays.asList(question1, question2, question3, question4, question5));
    }
}
