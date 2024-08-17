package com.hb.quizapp.controller;

import com.hb.quizapp.model.QuestionWrapper;
import com.hb.quizapp.model.Quiz;
import com.hb.quizapp.model.Response;
import com.hb.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQs, @RequestParam String title) {
        return quizService.createQuiz(category, numQs, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Double> addScore(@PathVariable int id, @RequestBody List<Response> responses) {
        return quizService.getScore(id, responses);
    }
}
