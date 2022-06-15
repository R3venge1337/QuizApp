package com.project.LeaugeOfLegendsApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.LeaugeOfLegendsApp.model.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

}
