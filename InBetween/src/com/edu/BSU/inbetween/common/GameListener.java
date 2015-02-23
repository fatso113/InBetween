package com.edu.BSU.inbetween.common;

public interface GameListener {
    
    void onPotChange(int newPotAmount);
    void onPlayerMoneyChange(int newValue);
    void onAiMoneyChange(int index, int newValue);
    void onPlayerBet();
    void onAIPlayerBet(int index, int betAmount);
    void onRoundOver();
    void gameOver(boolean lose);
    void onAIKick(int index);
    void onPlayerKick();
    
}
