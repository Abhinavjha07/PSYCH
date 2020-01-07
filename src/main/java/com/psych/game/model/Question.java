package com.psych.game.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.psych.game.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question extends Auditable{
    @NotBlank @Getter @Setter
    @Column(length = Constants.MAX_QUESTION_LENGTH)
    private String questionText;

    @NotBlank @Getter @Setter
    @Column(length = Constants.MAX_ANSWER_LENGTH)
    private String correctAnswer;

    @NotNull
    @Getter @Setter
    private GameMode gameMode;

    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    @Getter @Setter
    private List<EllenAnswer> ellenAnswers = new ArrayList<>();


    public Question() {
    }

    private Question(Builder builder)
    {
        setQuestionText(builder.questionText);
        setCorrectAnswer(builder.correctAnswer);
        setGameMode(builder.gameMode);
    }


    public static final class Builder {
        private String questionText;
        private String correctAnswer;
        private GameMode gameMode;

        private Builder() {
        }

        public Builder questionText(String questionText) {
            this.questionText = questionText;
            return this;
        }

        public Builder correctAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public Builder gameMode(GameMode gameMode) {
            this.gameMode = gameMode;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }
}
