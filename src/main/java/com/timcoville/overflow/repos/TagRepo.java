package com.timcoville.overflow.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.timcoville.overflow.models.Tag;

public interface TagRepo extends CrudRepository<Tag, Long> {
	
	@Query("SELECT t FROM Tag t WHERE t.subject = ?1")
	Optional<Tag> findTagByName(String name);
}
