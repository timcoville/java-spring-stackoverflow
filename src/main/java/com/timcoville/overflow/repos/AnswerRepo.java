package com.timcoville.overflow.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.timcoville.overflow.models.Answer;

public interface AnswerRepo extends CrudRepository<Answer, Long> {

	List<Answer> findByQuestion_id(Long id);
}
