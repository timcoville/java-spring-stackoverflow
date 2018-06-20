package com.timcoville.overflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.timcoville.overflow.models.Question;
import com.timcoville.overflow.models.Tag;
import com.timcoville.overflow.repos.QuestionRepo;

@Service
public class QuestionService {
	private final QuestionRepo qr;
	public QuestionService(QuestionRepo qr) {
		this.qr = qr;
	}
	
	public List<Object[]> allQuestions(){
		return qr.findAllJoined();
	}
	
	public List<Question> findAll(){
		return qr.findAll();
	}
	
	public Question createQuestion(String question, List<Tag> tags) {
		Question newQuestion = new Question();
		newQuestion.setQuestion(question);
		newQuestion.setTags(tags);
		return qr.save(newQuestion);
	}
	
	public Question findQuestion(Long id) {
		Optional<Question> record = qr.findById(id);
		if (record.isPresent()) {
			return record.get();
		} else {
			return null;
		}
	}
	
}
