package com.project.LeaugeOfLegendsApp.question;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionLangRepository extends MongoRepository<QuestionLang, String>{

}
