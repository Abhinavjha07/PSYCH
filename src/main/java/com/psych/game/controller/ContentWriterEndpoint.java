package com.psych.game.controller;

import com.psych.game.model.ContentWriter;
import com.psych.game.model.Question;
import com.psych.game.repository.ContentWriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/content-writer")
public class ContentWriterEndpoint
{

    @Autowired
    ContentWriterRepository contentWriterRepository;

    @GetMapping("/get-questions/{id}")
    public List<Question> getQuestions(@PathVariable(value = "id") Long id) throws Exception {
        ContentWriter writer = contentWriterRepository.findById(id).orElseThrow(() -> new Exception("No content writer exists with the given id!"));

        return writer.getEditedQuestions();
    }

    @PostMapping("/edit_question")
    public void editQuestion()
    {}

    // TODO: 05/01/20 : edit a Question
//    create a question
//    delete a question

    @PostMapping("/update-profile/{id}")
    public String updateProfile(@PathVariable(value = "id") Long id,
                                @Valid @RequestBody ContentWriter writer) throws Exception {
        ContentWriter contentWriter = contentWriterRepository.findById(id)
                .orElseThrow(() -> new Exception("No content writer exist with given id!!"));

        contentWriter.setName(writer.getName());
        contentWriter.setAddress(writer.getAddress());
        contentWriter.setEmail(writer.getEmail());
        contentWriter.setPhoneNumber(writer.getPhoneNumber());

        contentWriterRepository.save(contentWriter);
        return "Updated successfully";

    }
}
