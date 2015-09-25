package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by John on 4/10/2015.
 */
public class Human extends ActionBarActivity {
    private double x, y;
    private double vx, vy;
    private double touch_x;
    private double touch_y;
    private double center_x;
    private double center_y;
    private int health;
    private final int maxHealth = 5;
    private int bombs;
    private final int maxBombs = 5;
    private int bombDamage = 1;
    private int gunDamage;
    private boolean alive;
    public ImageView player;

    /**constructor*/
    public Human() {
        this.x = 250;
        this.y = 250;
        this.vx = 5;
        this.vy = 5;
        this.health = 3;
        this.bombs = 3;
        this.alive = true;
//        this.player = null;
        Log.v("something", "check0");
        player = (ImageView) findViewById(R.id.playerFront);

    }

    /**getters*/

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getVX() {
        return this.vx;
    }

    public double getVY() {
        return this.vy;
    }

    public int getHealth() {
        return this.health;
    }

    public int getBombs() {
        return this.bombs;
    }

    public boolean getAlive() {
        return this.alive;
    }

    public ImageView getPlayer() {
        return this.player;
    }

    /**setters*/

    public void setX(double d){
        this.x = d;
    }
    public void setY(double d){
        this.y = d;
    }
    public void setVX(double d){
        this.vx = d;
    }
    public void setVY(double d){
        this.vy = d;
    }
    public void setHealth(int x){
        this.health = x;
    }
    public void setBombs(int x){
        this.bombs = x;
    }
    public void setAlive(boolean b) {
        this.alive = b;
    }



    public void move(ImageView view) {
        final int[] viewCoords = new int[2];
        view.getLocationOnScreen(viewCoords);
        Log.v("something", "check1");

        center_x = viewCoords[0] + 100;
        center_y = viewCoords[1] + 100;

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.v("something", "check2");

                // Interpret MotionEvent data
                // Handle touch here
//                myTimer = new Timer();
//                myTimer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        double temp = time;
//                        time = time + 1 - temp;
//                    }
//
//                }, 0, 1000);

                touch_x = event.getX();
                touch_y = event.getY();
                if (touch_x > 190) {
                    touch_x = 0;
                    touch_y = 0;
                }
                if (touch_y < -10) {
                    touch_x = 0;
                    touch_y = 0;
                }
                double math_x = touch_x - center_x;
                double math_y = touch_y - center_y;

                double angle = (Math.atan2(math_y, math_x) * 180 / Math.PI);
                double x_way = Math.cos(angle * Math.PI / 180);
                double y_way = Math.sin(angle * Math.PI / 180);


                if (touch_x != 0 || touch_y != 0) {
                    Log.v("something", "check3");

                    player.setX((float) (player.getX() + x_way * 5));
                    player.setY((float) (player.getY() + y_way * 5));
                    Log.v("something", "check4");

                    if (player.getX() < 120) {
                        player.setX(120);
                    }
                    if (player.getX() > 632) {
                        player.setX(632);
                    }
                    if (player.getY() < 65) {
                        player.setY(65);
                    }
                    if (player.getY() > 785) {
                        player.setY(785);
                    }
                }

//                TextView thing = (TextView) findViewById(R.id.slope);
//                thing.setText("" + math_x + "\n" +  math_y + "\n" + angle + "\n" + cosAngle);
                return true;
            }
        });
    }

}
