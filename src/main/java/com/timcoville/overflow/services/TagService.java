package com.timcoville.overflow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.timcoville.overflow.models.Tag;
import com.timcoville.overflow.repos.TagRepo;

@Service
public class TagService {
	private final TagRepo ts;
	public TagService(TagRepo ts) {
		this.ts = ts;
		
	}
	public Tag findTag(String tag) {
		Optional<Tag> record = ts.findTagByName(tag);
		if(record.isPresent()) {
			return record.get();
		}
		else {
			return null;
		}
	}
	
	public List<Tag> createTags(List<String> tags){
		
		List<Tag> tagArr = new ArrayList<Tag>();
		
		if(tags.get(0).isEmpty()) {
			tags.remove(0);
		}


		if (tags.size() == 0) {
			return tagArr; 
		}
		
		for (String tag: tags) {
			String space = " ";
			if (tag.charAt(0) == space.charAt(0)) {
				String temp = tag.substring(1);
				
				Tag record = findTag(temp);
				if (record == null) {
					Tag newTag = new Tag();
					newTag.setSubject(temp);
					Tag newRecord = ts.save(newTag);
					tagArr.add(newRecord);
				} else {
					tagArr.add(record);
				}
			} else {
				Tag record = findTag(tag);
				if (record == null) {
					Tag newTag = new Tag();
					newTag.setSubject(tag);
					Tag newRecord = ts.save(newTag);
					tagArr.add(newRecord);
				} else {
					tagArr.add(record);
				}
			}
		}
		
		return tagArr;
	}
}
