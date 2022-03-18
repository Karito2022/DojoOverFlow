package com.karitoreyes.project.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.karitoreyes.project.models.Tag;


public interface TagRepository extends CrudRepository<Tag, Long> {
	List<Tag> findAll();
}