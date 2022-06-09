package edu.hitsz.dao;

import java.util.List;

public interface PlayerDAO {
    static List<Player> getPlayerList() {
        return null;
    }

    void addPlayer(String playerName, int score);
    void deletePlayer(int playerIndex);

    void printPlayerList();
    void savePlayerList();

    void readPlayerList();
}
