package com.timcoville.overflow.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.timcoville.overflow.models.Question;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Long>{
	
	List<Question> findAll();
	
	@Query("SELECT q.question, t.subject, q.id FROM Question q LEFT JOIN q.tags t")
	List<Object[]> findAllJoined();
}

