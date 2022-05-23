package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class HeroStraightStrategy implements Strategy{
    @Override
    public List<BaseBullet> shoot(int locationX, int locationY, int direction, int aircraftSpeedY, int shootNum, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = locationX;
        int y = locationY + direction * 2;
        int bulletSpeedX = 0;
        int bulletSpeedY = aircraftSpeedY + direction * 8;

        BaseBullet baseBullet;
        for(int i = 0; i < shootNum; i++) {
            baseBullet = new HeroBullet(x + (i * 2 - shootNum + 1) * 100, y, bulletSpeedX, bulletSpeedY, power);
            res.add(baseBullet);
        }

        return res;
    }
}
