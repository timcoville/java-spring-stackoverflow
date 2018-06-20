package com.timcoville.overflow.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.timcoville.overflow.models.Answer;
import com.timcoville.overflow.repos.AnswerRepo;

@Service
public class AnswerService {
	private final AnswerRepo ar;
	public AnswerService(AnswerRepo ar) {
		this.ar = ar;
	}
	
	
	public Answer createAnswer(Answer answer) {
		return ar.save(answer);
	}
	
	public List<Answer> allAnswersById(Long id){
		List<Answer> records = ar.findByQuestion_id(id);
		List<Answer> answers = new ArrayList<Answer>();
		for (Answer record: records) {
			answers.add(0, record);
		}
		return answers;
	}
	
}
