package com.hb.quizapp.repository;

import com.hb.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public List<Question> findByCategory(String quizId);

    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQs", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQs);

}
