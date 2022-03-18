package com.karitoreyes.project.controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.karitoreyes.project.services.AnswerService;
import com.karitoreyes.project.services.QuestionService;
import com.karitoreyes.project.models.Answer;
import com.karitoreyes.project.models.Question;
import com.karitoreyes.project.repositories.TagRepository;


@Controller
public class MainController {
	@Autowired
	QuestionService questionService;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	AnswerService answerService;
	
	@GetMapping("/questions/new")
	public String newquestion(Model model) {
		model.addAttribute("newQuestion", new Question());
		return "index.jsp";
	}
	
	@GetMapping("/questions")
	public String dashboard(Model model) {
		List<Question> questions = questionService.getAllQuestions();
		model.addAttribute("questions", questions);
		return "dashboard.jsp";
	}
	
	@GetMapping("/questions/{id}")
	public String questionbyid(Model model, @PathVariable("id") Long id) {
		Question question = questionService.getQuestionById(id);
		if(question == null) {
			return "redirect:/questions";
		}
		model.addAttribute("question", question);
		return "questionid.jsp";
	}
	
	@PostMapping("/questions/save")
	public String questionsave(
			Model model,
			@RequestParam("tags") String tags,
			@RequestParam("question") String question,
			RedirectAttributes redirectAttributes
			) {
		if(tags.equals("") || question.equals("")) {
			redirectAttributes.addFlashAttribute("error_vacios", "Error: Llenar los campos");
			return "redirect:/questions/new";
		}
		ArrayList<String> tagList = new ArrayList<>(Arrays.asList(tags.split(",[ ]*")));
		if(tagList.size() > 3) {
			redirectAttributes.addFlashAttribute("error_cantidad_tag", "Error: No debe ingresar más de 3 tags");
			return "redirect:/questions/new";
		}
		Question questionToAdd = new Question();
		questionToAdd.setQuestion(question);
		questionService.saveNewTagsOnlyInQuestion(questionToAdd,tagList);
		return "index.jsp";
	}
	
	@PostMapping("/questions/sendanswer")
	public String saveanswer(
			@RequestParam("id") Long id,
			@RequestParam("answer") String answer
			) {
		Question question = questionService.getQuestionById(id);
		Answer answerObj = new Answer();
		answerObj.setAnswer(answer);
		Answer answerSaved = answerService.saveAnswer(answerObj);
		answerSaved.setQuestion(question);
		answerService.saveAnswer(answerSaved);
		return "redirect:/questions/"+id;
	}
}