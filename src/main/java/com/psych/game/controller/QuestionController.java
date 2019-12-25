package com.psych.game.controller;

import com.psych.game.model.Question;
import com.psych.game.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public List<Question> getAllQuestions()
    {
        return questionRepository.findAll();
    }

    @GetMapping("/questions/{id}") //path variable
    public Question getQuestionById(@PathVariable(value = "id") Long id) throws Exception
    {
        System.out.println("Get Question By Id");
        return questionRepository.findById(id).orElseThrow(Exception::new);
    }
}
