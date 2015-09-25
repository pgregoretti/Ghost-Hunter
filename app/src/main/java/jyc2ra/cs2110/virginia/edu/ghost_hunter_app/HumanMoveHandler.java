package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.os.AsyncTask;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by John on 4/12/2015.
 */
public class HumanMoveHandler extends AsyncTask<ImageView, ImageView, Void> {

    float x, y;
    float thing1,thing2;
    private double touch_x;
    private double touch_y;
    private double center_x;
    private double center_y;
    final int[] viewCoords = new int[2];
    private final int maxHealth = 10;
    private int bombs;
    private final int maxBombs = 5;
    private int bombDamage = 1;
    private int gunDamage;
    private boolean alive;
    private MotionEvent event;
    TextView t;
    double math_x;
    double math_y;
    double x_way;
    double y_way;
    private float score = 0;
    private int health;
    //    ImageView h;
    private double angle = 0;
    private TextView Hhealth;
    private double xwaycons = 1;
    private double ywaycons = 1;

    public HumanMoveHandler(MotionEvent dpad,TextView health) {
        this.event = dpad;
        this.center_x = 146;
        this.center_y = 1070;
        this.t = t;
        alive = true;
        Hhealth = health;
        this.health = 5;
    }

    protected Void doInBackground(ImageView... humans) {
        ImageView h = humans[0];
//        this.health = 5;
        while (true) {
            if (isCancelled()) {
                break;
            } else {
                if (event == null) {

                } else {
                    x = h.getX();
                    y = h.getY();
                    move();
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(h);
            }
        }
        return null;
    }

    public void move() {
        touch_x = event.getX();
        touch_y = event.getY();
        if (touch_x > 250) {
            touch_x = 0;
            touch_y = 0;
        }
        if (touch_y < 950) {
            touch_x = 0;
            touch_y = 0;
        }
        math_x = touch_x - center_x;
        math_y = touch_y - center_y;
        angle = (Math.atan2(math_y, math_x) * 180 / Math.PI);
        x_way = Math.cos(angle * Math.PI / 180);
        y_way = Math.sin(angle * Math.PI / 180);

//        if (touch_x != 0 || touch_y != 0) {
//            x = (float) (x + x_way * 20);
//            y = (float) (x + y_way * 20);
//            if (x < 120) {
//                this.x = 120;
//            }
//            if (x > 632) {
//                this.x = 632;
//            }
//            if (y < 65) {
//                this.y = 65;
//            }
//            if (y > 785) {
//                this.y = 785;
//            }
//        }
    }

    protected void onProgressUpdate(ImageView... humans) {
        ImageView h = humans[0];
        if (touch_x != 0 || touch_y != 0) {
            h.setX((float) (x +  xwaycons * x_way * 5));
            h.setY((float) (y +  ywaycons * y_way * 5));
            if (h.getX() < 120) {
                h.setX(120);
            }
            if (h.getX() > 632) {
                h.setX(632);
            }
            if (h.getY() < 65) {
                h.setY(65);
            }
            if (h.getY() > 785) {
                h.setY(785);
            }
            if(this.getAlive() == false) {
                Hhealth.setText("Health: " + 0 + " of " + maxHealth);
            }
            else {
                Hhealth.setText("Health: " + health + " of " + maxHealth);
            }


        }
        //h.setImageResource(R.drawable.playerfront);
        // h.setX(x);
        //  h.setY(y);
        //Log.v("angle", ""+angle);
        if ((angle <= 45) && (angle >= -45)) {
            h.setImageResource(R.drawable.playerright);
        } else if ((angle < 135) && (angle > 45 * Math.PI / 4)) {
            h.setImageResource(R.drawable.playerfront);
        } else if (((angle <= 180) && (angle >= 135 * Math.PI / 4)) || (angle <= -135)) {
            h.setImageResource(R.drawable.playerleft);
        }else if(touch_y == 0 && touch_x ==0){
            h.setImageResource(R.drawable.playerfront);
        }else {
            h.setImageResource(R.drawable.playerback);
        }
        // t.setText(touch_x + " \n " + center_x + "\n" + touch_y + "\n" + center_y +"\n" + math_x +"\n" + math_y + "\n" + x_way +"\n" + y_way);
        // t.setText("        Score: " + score);
    }



    protected void onPostExecute(Void v) {

    }

    /*getters and setters*/

    public int getHealth() {
        return this.health;
    }
    public int getBombs() {
        return this.bombs;
    }
    public boolean getAlive() {
        return this.alive;
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
    public MotionEvent getEvent() {
        return this.event;
    }
    public void setEvent(MotionEvent event) {
        this.event = event;
    }
    public float getScore(){
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public final int getMaxHealth() {
        return this.maxHealth;
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public double getXway() { return this.x_way; }
    public void setXway(double x_way) { this.x_way = x_way; }
    public double getYway() { return this.y_way; }
    public void setYway(double y_way) { this.y_way = y_way; }
    public double getXwaycons() { return this.xwaycons; }
    public void setXwaycons(double con) { this.xwaycons = con;}
    public double getYwaycons() { return this.ywaycons; }
    public void setYwaycons(double con) { this.ywaycons = con;}

}
