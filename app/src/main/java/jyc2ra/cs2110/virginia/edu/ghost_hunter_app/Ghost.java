package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.app.Activity;
import android.widget.ImageView;

/**
 * Created by Jeremy on 4/10/2015.
 */
public class Ghost extends Activity {
    protected double x,y;
    protected double vx,vy;
    protected double direction;
    protected int health; //default = 1
    private boolean alive;
    public ImageView ghostImage;

    /**Constructor*/
    public Ghost(){
        this.x = 0;
        this.y = 0;
        this.vx = 5;
        this.vy = 5;
        this.direction = 0;
        this.alive = true;
        this.health = 1;
        //this.ghost = null;
//        ghostImage = (ImageView) findViewById(R.id.ghostLeft);
        //ghostImage = (ImageView) setImageDrawable();
    }




    /**Getters*/
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

    public boolean getAlive() {
        return this.alive;
    }

    public ImageView getGhost() {
        return this.ghostImage;
    }

    /**Setters*/
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
    public void setAlive(boolean b) {
        this.alive = b;
    }
    public void setGhost(ImageView v) { this.ghostImage = v; }

    public void move () {
        //ImageView mover = (ImageView) findViewById(R.id.ghostthing);
       // double math_x = touch_x - center_x;
        //double math_y = touch_y - center_y;

        //double angle = (Math.atan2(this.getX(), this.getY()) * 180 / Math.PI);
        //double x_way = Math.cos(angle * Math.PI / 180);
        //double y_way = Math.sin(angle * Math.PI / 180);
        //this.setVX(Math.cos(angle * Math.PI / 180));
        //this.setVY(Math.sin(angle * Math.PI / 180));

       // if (touch_x != 0 || touch_y != 0) {
        double angle = (Math.atan2(ghostImage.getY(), ghostImage.getX()) * 180 / Math.PI);
        double x_way = Math.cos(angle * Math.PI / 180);
        double y_way = Math.sin(angle * Math.PI / 180);
            ghostImage.setX((float) (ghostImage.getX() + this.getVX() * 5));
            ghostImage.setY((float) (ghostImage.getY() + this.getVY() * 5));
            //change this to ghostImage and run both

            if (ghostImage.getX() < 0) {
                ghostImage.setX(0);
                this.setVX(this.getVX() * -1);
                ghostImage.setX((float) (ghostImage.getX() + x_way * this.getVX() * 5));
            }
            if (ghostImage.getX() > 800) {
                ghostImage.setX(800);
                this.setVX(this.getVX() * -1);
                ghostImage.setX((float) (ghostImage.getX() + x_way * this.getVX() * 5));
            }
            if (ghostImage.getY() < 0) {
                ghostImage.setY(0);
                this.setVY(this.getVY() * -1);
                ghostImage.setY((float) (ghostImage.getY() + y_way * this.getVY() * 5));
            }
            if (ghostImage.getY() > 785) {
                ghostImage.setY(785);
                this.setVY(this.getVY() * -1);
                ghostImage.setY((float) (ghostImage.getY() + y_way * this.getVY() * 5));
            }
        //}

       // return true;



    }

    public void die(){

    }

}
