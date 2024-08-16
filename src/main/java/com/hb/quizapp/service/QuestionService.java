package com.hb.quizapp.service;

import com.hb.quizapp.model.Question;
import com.hb.quizapp.model.QuestionDTO;
import com.hb.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        question.setId(questionDTO.getId());
        question.setQuestion(questionDTO.getQuestion());
        question.setAnswer(questionDTO.getAnswer());
        question.setCategory(questionDTO.getCategory());
        question.setDifficulty(questionDTO.getDifficulty());
        question.setOptions(questionDTO.getOptions());
        return question;
    }


    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question question : questions) {
            questionDTOs.add(convertQuestionToDTO(question));
        }
        return questionDTOs;
    }

    public Optional<QuestionDTO> getQuestion(int id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Question not found")
        );

        return Optional.of(convertQuestionToDTO(question));
    }

    public String addQuestion(QuestionDTO questionDTO) {
        questionRepository.save(convertDTOToQuestion(questionDTO));
        return "Question added";
    }

    public String deleteQuestion(int id) {
        questionRepository.deleteById(id);
        return "Question deleted";
    }
}
