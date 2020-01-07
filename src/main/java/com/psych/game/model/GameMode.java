package com.psych.game.model;

import lombok.Getter;

public enum GameMode {
    IS_THIS_A_FACT(1),
    UNSCRAMBLE(2),
    WORD_UP(3);


    @Getter
    private int value;

    GameMode(int value)
    {
        this.value = value;
    }

    public static GameMode fromValue(int value)
    {
        switch (value)
        {
            case 1:
                return GameMode.IS_THIS_A_FACT;
            case 2:
                return GameMode.UNSCRAMBLE;
            case 3:
                return GameMode.WORD_UP;
        }

        return GameMode.IS_THIS_A_FACT;
    }

}
