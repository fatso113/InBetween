package com.edu.BSU.inbetween.common;

public interface GameListener {
    
    void onPotChange(int newPotAmount);
    void onPlayerBet();
    void onAIPlayerBet(int index, int betAmount);
    void onRoundOver();
    void onGameOver();
    void onAIKick(int index);
    void onPlayerKick();
    
}
