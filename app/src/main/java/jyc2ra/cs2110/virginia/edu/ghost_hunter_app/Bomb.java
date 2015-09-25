package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.widget.ImageView;

/**
 * Created by Jeremy on 4/14/2015.
 */
public class Bomb {
    private int damage;
    private double timer;
    ImageView bomb;

    public Bomb(){
        this.damage = 1;
        this.timer= 5;
    }

    public int getDamage(){
        return this.damage;
    }
    public void setDamage(int d){
        this.damage = d;
    }

}
