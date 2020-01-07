package com.psych.game;

import com.psych.game.model.EllenAnswer;
import com.psych.game.model.Round;

public class RandomTop5Answers implements EllenStrategy
{
    @Override
    public EllenAnswer getAnswer(Round round) {
        return null;
//        todo:
//        make a db query
//        find top 5 voted answer and return one of them based on the game id, round id, question id
    }
}
