package com.hb.quizapp.service;

import com.hb.quizapp.model.Question;
import com.hb.quizapp.model.QuestionWrapper;
import com.hb.quizapp.model.Quiz;
import com.hb.quizapp.model.Response;
import com.hb.quizapp.repository.QuestionRepository;
import com.hb.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, QuestionService questionService) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

    public ResponseEntity<String> createQuiz(String category, int numQs, String title) {
        try {
            List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQs);

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizRepository.save(quiz);

            return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        try {
            Quiz quiz = quizRepository.findById(id).get();
            List<QuestionWrapper> questions = new ArrayList<>();
            for (Question question : quiz.getQuestions()) {
                QuestionWrapper questionWrapper = new QuestionWrapper();
                questionWrapper.setId(question.getId());
                questionWrapper.setQuestion(question.getQuestion());
                questionWrapper.setOptions(question.getOptions());
                questions.add(questionWrapper);
            }
            return new ResponseEntity<>(questions, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Double> getScore(int id, List<Response> responses) {
        try {
            double score = 0.0;
            Quiz quiz = quizRepository.findById(id).get();
            for (Response response : responses) {
                Question question = questionRepository.findById(response.getId()).get();
                if (response.getResponse().equals(question.getAnswer())) {
                    score += 1;
                }
            }
            return new ResponseEntity<>(score / quiz.getQuestions().size(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
