package com.hb.quizapp.service;

import com.hb.quizapp.model.Question;
import com.hb.quizapp.model.QuestionDTO;
import com.hb.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }




    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        try {
            List<Question> questions = questionRepository.findAll();
            List<QuestionDTO> questionDTOs = new ArrayList<>();
            for (Question question : questions) {
                questionDTOs.add(convertQuestionToDTO(question));
            }
            return new ResponseEntity<>(questionDTOs, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<QuestionDTO> getQuestion(int id) {
        try {
            Question question = questionRepository.findById(id).get();
            return new ResponseEntity<>(convertQuestionToDTO(question), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> addQuestion(QuestionDTO questionDTO) {
        try {
            questionRepository.save(convertDTOToQuestion(questionDTO));
            return new ResponseEntity<>("Question added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            questionRepository.deleteById(id);
            return new ResponseEntity<>("Question deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionDTO>> getByCategory(String category) {
        try {
            List<Question> questions = questionRepository.findByCategory(category);
            List<QuestionDTO> questionDTOs = new ArrayList<>();
            for (Question question : questions) {
                questionDTOs.add(convertQuestionToDTO(question));
            }
            return new ResponseEntity<>(questionDTOs, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    public QuestionDTO convertQuestionToDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestion(question.getQuestion());
        questionDTO.setAnswer(question.getAnswer());
        questionDTO.setCategory(question.getCategory());
        questionDTO.setDifficulty(question.getDifficulty());
        questionDTO.setOptions(question.getOptions());
        return questionDTO;
    }

    public Question convertDTOToQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion());
        question.setAnswer(questionDTO.getAnswer());
        question.setCategory(questionDTO.getCategory());
        question.setDifficulty(questionDTO.getDifficulty());
        question.setOptions(questionDTO.getOptions());
        return question;
    }
}
