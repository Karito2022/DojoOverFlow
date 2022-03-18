package com.karitoreyes.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karitoreyes.project.models.Tag;
import com.karitoreyes.project.repositories.TagRepository;

@Service
public class TagService {
	@Autowired
	TagRepository tagRepository;
	
	public List<Tag> getAllTags() {
	    return tagRepository.findAll();
	}
	public void saveTag(Tag tag) {
		tagRepository.save(tag);
	}
	public Tag getTagById(Long id) {
	    Optional<Tag> optionalBook = tagRepository.findById(id);
	    if(optionalBook.isPresent()) {
	        return optionalBook.get();
	    } else {
	        return null;
	    }
	}
}