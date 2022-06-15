package com.project.LeaugeOfLegendsApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.LeaugeOfLegendsApp.model.QuestionLang;

@Repository
public interface QuestionLangRepository extends MongoRepository<QuestionLang, String>{

}
