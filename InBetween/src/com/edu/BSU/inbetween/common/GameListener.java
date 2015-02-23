package com.edu.BSU.inbetween.common;

public interface GameListener {
    
    void onPotChange();
    void onPlayerBet();
    void onAIPlayerBet(int index, int betAmount);
    void onRoundOver();
    void onGameOver();
    
}
