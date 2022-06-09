package edu.hitsz.dao;

import android.app.Activity;
import android.content.Context;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO{
    private static List<Player> playerList = new LinkedList<>();
    public Activity act;
    public PlayerDAOImpl(Activity act){
        this.act = act;
    }

    public static List<Player> getPlayerList() {
        return playerList;
    }

    public void readPlayerList() {
        playerList.clear();
        try {
            FileInputStream fis = act.openFileInput("player.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                Player player = (Player) ois.readObject();
                playerList.add(player);
            }
        } catch(EOFException e) {

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void addPlayer(String playerName, int score) {
        Player newPlayer = new Player(playerName, score);
        for(Player player: playerList) {
            if(player.getScore() >= score) {
                continue;
            } else {
                playerList.add(playerList.indexOf(player), newPlayer);
                return;
            }
        }
        playerList.add(newPlayer);
        savePlayerList();
    }

    public void deletePlayer(int playerIndex) {
        playerList.remove(playerIndex);
        savePlayerList();
    }

    public void savePlayerList() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(act.openFileOutput("player.dat", Context.MODE_PRIVATE));
            for(Player player: playerList) {
                oos.writeObject(player);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void printPlayerList() {
        System.out.println("***************");
        System.out.println("  得分排行榜");
        System.out.println("***************");
        for(Player player: playerList) {
            System.out.print("第" + (playerList.indexOf(player) + 1) + "名 ");
            System.out.print(player.getPlayerName() + "," + player.getScore() + " ");
            System.out.println(player.getDateTime());
        }
    }
}
