package com.karitoreyes.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karitoreyes.project.models.Answer;
import com.karitoreyes.project.repositories.AnswerRepository;


@Service
public class AnswerService {
	@Autowired
	AnswerRepository answerRepository;
	public Answer saveAnswer(Answer answer) {
		return answerRepository.save(answer);
	}
}