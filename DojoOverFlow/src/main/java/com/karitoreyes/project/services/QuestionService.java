package com.karitoreyes.project.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karitoreyes.project.models.Question;
import com.karitoreyes.project.models.Tag;
import com.karitoreyes.project.repositories.QuestionRepository;
import com.karitoreyes.project.repositories.TagRepository;

@Service
public class QuestionService{
	
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	TagRepository tagRepository;
	
	public List<Question> getAllQuestions() {
	    return questionRepository.findAll();
	}
	public Question saveQuestion(Question question) {
	    return questionRepository.save(question);
	}
	public Question getQuestionById(Long id) {
	    Optional<Question> optionalBook = questionRepository.findById(id);
	    if(optionalBook.isPresent()) {
	        return optionalBook.get();
	    } else {
	        return null;
	    }
	}
	
	public void saveNewTagsOnlyInQuestion(Question question, List<String> tagList) {
		List<Tag> tags = tagRepository.findAll();
		List<String> copyTagList = new ArrayList<String>(tagList);
		List<Tag> newTags = new ArrayList<Tag>();
		List<Long> idTags = new ArrayList<Long>();
		System.out.println(tags.size());
		System.out.println(tagList.size());
		for (int i = 0; i < tags.size(); i++) {
			System.out.println("===================================");
			System.out.print("Tag en mi Base de datos: ");
			System.out.println(tags.get(i).getSubject());
			for (int j = 0; j < tagList.size(); j++) {
				if(tagList.get(j).equals(tags.get(i).getSubject())) {
					idTags.add(tags.get(i).getId());
					copyTagList.remove(tags.get(i).getSubject());
					System.out.print("Tag formulario: ");
					System.out.println(tagList.get(j));
				}
			}
			System.out.println(" ");
		}
		System.out.println("*******************************");
		System.out.println(copyTagList.size());
		System.out.println(idTags.size());
		if(copyTagList.size()>0) {
			for (int i = 0; i < copyTagList.size(); i++) {
				Tag tag = new Tag();
				tag.setSubject(copyTagList.get(i));
				newTags.add(tag);
			}
			question.setTags(newTags);
			Question questionSaved = questionRepository.save(question);
			System.out.println(questionSaved.getTags().size());
			setRelationWithExisitingTags(idTags,questionSaved);
		}
	}
	
	public void setRelationWithExisitingTags(List<Long> idTags, Question questionSaved) {
		if(idTags.size()>0) {
			for (Long id : idTags) {
				Tag tag = tagRepository.findById(id).get();
				tag.getQuestions().add(questionSaved);
				tagRepository.save(tag);
			}
		}
	}
	
}
