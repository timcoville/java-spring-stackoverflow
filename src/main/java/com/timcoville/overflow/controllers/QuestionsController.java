package com.timcoville.overflow.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.timcoville.overflow.models.Answer;
import com.timcoville.overflow.models.Question;
import com.timcoville.overflow.models.Tag;
import com.timcoville.overflow.services.AnswerService;
import com.timcoville.overflow.services.QuestionService;
import com.timcoville.overflow.services.TagService;

@Controller
public class QuestionsController {
	private final QuestionService qs;
	private final TagService ts;
	private final AnswerService as;
	public QuestionsController(QuestionService qs, TagService ts, AnswerService as) {
		this.qs = qs;
		this.ts = ts;
		this.as = as;
	}
	
	@RequestMapping("/questions")
	public String index(Model model) {
		//Get All Records with Joined Table
		List<Object[]> table = qs.allQuestions();
		//Create ArrayList to Concat Tags
		List<List<String>> records = new ArrayList<List<String>>();
		//Iterate through Joined Table
		for (Object[] row:table) {
			//Grab Question
			String tempQuestion = String.valueOf(row[0]);
			//Grab Tag
			String tempTag = String.valueOf(row[1]);
			//Grab Question ID
			Long tempId = (Long) row[2];
			
			//Create First Row/Record for New ArrayList
			if (records.isEmpty()) {
				//Create ArrayList to Augment Tags
				List<String> record = new ArrayList<String>();
				//Push New Question
				record.add(tempQuestion);
				//If Tag is not null, push
				if (!tempTag.contains("null")) {
					record.add(tempTag);
				} else {
					record.add("");
				}
				record.add(Long.toString(tempId));
				//Add Row/Record to ArrayList
				records.add(record);
				}
			else {
				// Grab last record/row in ArrayList
				List<String> lastRecord = records.get(records.size()-1);
				// See if value of the Question is equal to current tempQuestion
				if (tempQuestion.equals(lastRecord.get(0))) {
					// If it is, grab String of Tags
					String currTags = lastRecord.get(1);
					// Concat current tag to String of Tags
					currTags += ", " + tempTag;
					// Update tags to lastRecord
					lastRecord.set(1, currTags);
					// Update lastRecord to Records
					records.set(records.size()-1, lastRecord);
				} else {
					// Else, create new record/row for ArrayList
					List<String> newRecord = new ArrayList<String>();
					//Add New Question
					newRecord.add(tempQuestion);
					//If tag is not null, add to new Record
					if (!tempTag.contains("null")) {
						newRecord.add(tempTag);
					}   else {
						newRecord.add("");
					}
					newRecord.add(Long.toString(tempId));
					//Add new Record to ArrayList
					records.add(newRecord);
				}
			}
		}
		model.addAttribute("records", records);
		return "index.jsp";
	}
	
	@RequestMapping("/questions/new")
	public String newQuestion() {
		return "newQuestion.jsp";
	}
	
	@PostMapping("/questions")
	public String processQuestion(@RequestParam(value="question") String question, @RequestParam(value="tags") String tags, RedirectAttributes redirectAttributes) {
		List<String> errors = new ArrayList<String>();
		String [] tagArr = tags.split(",");
		List<String> items = new ArrayList<String>(Arrays.asList(tags.split("\\s*,\\s*")));
		System.out.println(tags);
		//Validations
		if (question.length() == 0) { errors.add("Question must exist"); }
		
		if (items.size() > 3) { errors.add("Maximum 3 tags per Question"); }
		
		for (String str:items) {
			char[] charArr = str.toCharArray();
			for(int i=0; i < charArr.length; i++) {
				if (!Character.isLowerCase(charArr[i])) {
					String space = " ";
					if (charArr[i] != space.charAt(0) ) {
						errors.add("Tags must be lowercase");
						break;
					}
			
				}
			}
			if (errors.contains("Tags must be lowercase")) {
				break;
			}
		}
		
		//Create Records
		if (errors.isEmpty()) {
			List<Tag> processedTags = ts.createTags(items);
			Question processedQuestion = qs.createQuestion(question, processedTags);
			return "redirect:/questions/new";
		} else {
			redirectAttributes.addFlashAttribute("errors", errors);
			return "redirect:/questions/new";
		}
	}
	
	@RequestMapping("/questions/{id}")
	public String viewQuestion(@PathVariable("id")Long id, Model model) {
		Question question = qs.findQuestion(id);
		List<Answer> answers = as.allAnswersById(id);
		model.addAttribute("question", question);
		model.addAttribute("answers", answers);
		return "newAnswer.jsp";
	}
	
	@PostMapping("/answers")
	public String processAnswer(@RequestParam("answer")String answer, @RequestParam("questionID")Long id, RedirectAttributes redirectAttributes) {
		if (answer.length() == 0) {
			redirectAttributes.addFlashAttribute("Answer needs to exist");
			return "redirect:/questions/"+id;
		} else {
			Answer record = new Answer();
			Question question = qs.findQuestion(id);
			record.setAnswer(answer);
			record.setQuestion(question);
			as.createAnswer(record);
			
			return "redirect:/questions/"+id;
		}
		
		
		
	}
}
