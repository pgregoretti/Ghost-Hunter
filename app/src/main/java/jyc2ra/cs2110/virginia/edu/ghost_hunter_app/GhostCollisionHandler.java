package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by pam on 4/23/15.
 */

public class GhostCollisionHandler extends AsyncTask<Object, Void, Void> {
    private HumanMoveHandler p;
    private GhostMoveHandler g;
    private boolean alive;
    private float GhostLeftX;
    private float GhostTopY;
    private float GhostRightX;
    private float GhostBottomY;
    private float PlayerLeftX;
    private float PlayerTopY;
    private float PlayerRightX;
    private float PlayerBottomY;
    boolean checkLeftX;
    boolean checkTopY;
    boolean checkRightX;
    boolean checkBottomY;
    boolean topRight;
    boolean topLeft;
    boolean bottomLeft;
    boolean bottomRight;
    private float bombLeftX;
    private float bombTopY;
    private float bombRightX;
    private float bombBottomY;
    boolean checkLeftX2;
    boolean checkTopY2;
    boolean checkRightX2;
    boolean checkBottomY2;
    boolean topRight2;
    boolean topLeft2;
    boolean bottomLeft2;
    boolean bottomRight2;
    private boolean mos;
    //    private int HealthCounter = p.getHealth(); //set health to 3
    ArrayList<ImageView> rects;
    private Context context;
    private ImageView bomb;

    public GhostCollisionHandler(HumanMoveHandler p, GhostMoveHandler g,ImageView bomber) {

        this.g = g;
        this.p = p;
        rects = new ArrayList<ImageView>();
        GhostLeftX = g.getX().get(0);
        GhostTopY = g.getY().get(0);
        GhostRightX = g.getX().get(0) + 40;
        GhostBottomY = g.getY().get(0) + 40;
        PlayerLeftX = p.getX();
        PlayerTopY = p.getY();
        PlayerRightX = p.getX() + 40;
        PlayerBottomY = p.getY() + 40;
        alive = g.getA().get(0);
        this.bomb = bomber;
        bombLeftX = bomb.getX();
        bombTopY = bomb.getY();
        bombBottomY = bomb.getY() + 120;
        bombRightX = bomb.getX() + 120;
        mos = g.getMos().get(0);


    }

    protected Void doInBackground(Object...o) {
//        Object bob = o[0];
        Log.v("Thing", "In do in background");

        checkLeftX = (this.GhostLeftX <= this.PlayerRightX) && (this.GhostLeftX > this.PlayerLeftX); //check left x for ghost
        checkTopY = (this.GhostTopY >= this.PlayerTopY) && (this.GhostTopY < this.PlayerBottomY);
        checkRightX = (this.GhostRightX >= this.PlayerLeftX) && (this.GhostRightX < this.PlayerRightX);
        checkBottomY = (this.GhostBottomY >= this.PlayerTopY) && (this.GhostBottomY < this.PlayerBottomY);
        topRight = checkRightX && checkTopY;
        topLeft = checkLeftX && checkTopY;
        bottomLeft = checkLeftX && checkBottomY;
        bottomRight = checkRightX && checkBottomY;
        while (true) {
            for (int i = 0; i < g.getX().size(); i++) {
//            for(int i = 0; i < g.getX().size(); i++){

                PlayerLeftX = p.getX();
                PlayerTopY = p.getY();
                PlayerRightX = p.getX() + 36;
                PlayerBottomY = p.getY() + 63;

                checkLeftX = (g.getX().get(i) <= this.PlayerRightX) && (g.getX().get(i) > this.PlayerLeftX);
                checkTopY = (g.getY().get(i) >= this.PlayerTopY) && (g.getY().get(i) < this.PlayerBottomY);
                checkRightX = ((g.getX().get(i) + 40) >= this.PlayerLeftX) && ((g.getX().get(i) + 40) < this.PlayerRightX);
                checkBottomY = ((g.getY().get(i) + 40) >= this.PlayerTopY) && ((g.getY().get(i) + 40) < this.PlayerBottomY);
                alive = g.getA().get(i);
                mos = g.getMos().get(i);


                topRight = checkRightX && checkTopY;
                topLeft = checkLeftX && checkTopY;
                bottomLeft = checkLeftX && checkBottomY;
                bottomRight = checkRightX && checkBottomY;


                bombLeftX = bomb.getX();
                bombTopY = bomb.getY();
                bombBottomY = bomb.getY() + 120;
                bombRightX = bomb.getX() + 120;

                checkLeftX2 = (g.getX().get(i) <= this.bombRightX) && (g.getX().get(i) > this.bombLeftX);
                checkTopY2 = (g.getY().get(i) >= this.bombTopY) && (g.getY().get(i) < this.bombBottomY);
                checkRightX2 = ((g.getX().get(i) + 40) >= this.bombLeftX) && ((g.getX().get(i) + 40) < this.bombRightX);
                checkBottomY2 = ((g.getY().get(i) + 40) >= this.bombTopY) && ((g.getY().get(i) + 40) < this.bombBottomY);

                topRight2 = checkRightX2 && checkTopY2;
                topLeft2 = checkLeftX2 && checkTopY2;
                bottomLeft2 = checkLeftX2 && checkBottomY2;
                bottomRight2 = checkRightX2 && checkBottomY2;
//                Rect one = new Rect(g.getGh().get(i).getLeft(),g.getGh().get(i).getTop(),g.getGh().get(i).getRight(),g.getGh().get(i).getBottom());
//                Rect two = new Rect((int)p.getX(),(int)p.getY(),(int)p.getX() + 36,(int)p.getY() + 63);

                if (alive && (topRight || topLeft || bottomLeft || bottomRight) && (mos==true)) {
//                if(one.intersect(two)){
                    //take away health
//                    HealthCounter--;

                    g.getA().set(i, false);
                    //p.setHealth(p.getHealth() - 1);
                    p.setScore(p.getScore() + 25);


//                    g.getGh().get(i).setVisibility(View.INVISIBLE);
                    Log.v("Collision", "Collision worked good job guys!");

                    try {
                        Thread.sleep(3000); //3 seconds of immunity
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (alive && (topRight || topLeft || bottomLeft || bottomRight) && (mos ==false) ) {
//                if(one.intersect(two)){
                    //take away health
//                    HealthCounter--;

                    g.getA().set(i, false);
                    p.setHealth(p.getHealth() - 1);
                    p.setScore(p.getScore() + 25);

                    if (p.getHealth() <= 0) {
                        p.setAlive(false);
                        g.cancel(true);


                        p.cancel(true);
                        this.cancel(true);
                        Intent intent = new Intent(context, MainActivity.class);

                    }
//                    g.getGh().get(i).setVisibility(View.INVISIBLE);
                    Log.v("Collision", "Collision worked good job guys!");

                    try {
                        Thread.sleep(3000); //3 seconds of immunity
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                publishProgress(o);



                if (alive && (topRight2 || topLeft2 || bottomLeft2 || bottomRight2)) {
                    g.getA().set(i,false);
                    Log.v("Bomb","Bomb Collisions WorkeD!");
                    p.setScore(p.getScore() + 25);
                }

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    protected void onProgressUpdate(ImageView... ghosts) {
//        ImageView ghost = ghosts[0];
//        if(HealthCounter==2) {
//
//            //Toast.makeText(null, "Your health dropped down to 2!", Toast.LENGTH_SHORT);
//
//        }
//
//        if(HealthCounter==1) {
//
//            //Toast.makeText(null, "Your health dropped down to 1!", Toast.LENGTH_SHORT);
//
//        }
//
//        if(HealthCounter==0) {
//
//            //Toast.makeText(null, "Your health dropped down to 0!", Toast.LENGTH_SHORT);
//            //somehow end game
//
//        }

//        Toast.makeText(context, "Health Left: " + health, Toast.LENGTH_SHORT).show();

    }


}