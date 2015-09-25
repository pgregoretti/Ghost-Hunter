package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by John on 4/19/2015.
 */


public class GhostMoveHandler extends AsyncTask<ArrayList<ImageView>, ArrayList<ImageView>, Void> {
    //float x, y;
    //float vx, vy;
    float leftWall = 0, rightWall = 730, topWall = 0, bottomWall = 810;
    private boolean direction = true;
    private int difficulty = 0;
    private int cons;
    private Rect rect = new Rect();
    //    float[] x = new float[15];
//    float[] y = new float[15];
//    float[] vx = new float[15];
//    float[] vy = new float[15];
    ArrayList<Float> x = new ArrayList<Float>();
    ArrayList<Float> y = new ArrayList<Float>();
    ArrayList<Float> vx = new ArrayList<Float>();
    ArrayList<Float> vy = new ArrayList<Float>();
    ArrayList<ImageView> gh;
    RelativeLayout relat;
    ArrayList<Boolean> a = new ArrayList<Boolean>();
    ArrayList<Boolean> mos = new ArrayList<Boolean>();


    public GhostMoveHandler(float s) {
        for (int i=0; i<s; i++){ //set all ghosts to alive
            a.add(true);
            mos.add(false);
        }

        Random rand = new Random();
        Random dir = new Random();

        for(int i = 0; i < s; i++) {
//            x[i] = ((float)(rand.nextInt((int) rightWall -  60) + 30));
//            y[i] = ((float)(rand.nextInt((int) bottomWall - 60) + 30));
            x.add(i,(float)(rand.nextInt((int) rightWall -  60) + 30) );
            y.add(i,(float)(rand.nextInt((int) rightWall -  60) + 30) );
        }


        if(dir.nextInt(2) == 1) {
            cons = 1;
        }
        else {
            cons = -1;
        }
        for(int i = 0; i < s; i++) {
//            vx[i] = (float)cons*(rand.nextInt(5));
            vx.add(i,(float)cons*(rand.nextInt(5)));
        }


        if(dir.nextInt(2) == 1) {
            cons = 1;
        }
        else {
            cons = -1;
        }
        for(int i = 0; i < s; i++) {
//            vy[i] = (float)cons*(rand.nextInt(5));
            vy.add(i,(float)cons*(rand.nextInt(5)));

        }

    }

    protected Void doInBackground(ArrayList<ImageView>... ghosts) {
//        Log.v("thing","whoaa" + ghosts.length);
        gh = ghosts[0];
        for(int i = 0; i < gh.size(); i++) {
            gh.get(i).getDrawingRect(rect);
        }

        while (true) {
            move();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress();
        }

    }

    public void move() {
//        for(int i = 0; i < 15; i++){
//            if (x[i] < rightWall && x[i] > leftWall) {
//                direction = false;
//                x[i] += vx[i];
//            }
//            else {
//                direction = true;
//                if(x[i] >= rightWall) x[i] = rightWall;
//                if(x[i] <= leftWall) x[i] = leftWall;
//                vx[i] *= -1 ;
//                x[i] += vx[i];
//            }
//
//
//            if (y[i] < bottomWall && y[i] > topWall) {
//                y[i] += vy[i];
//            }
//            else {
//                if(y[i] <= topWall) y[i] = topWall;
//                if(y[i] >= bottomWall) y[i] = bottomWall;
//                vy[i] *= -1 ;
//                y[i] += vy[i];
//            }
//        }

        for(int i = 0; i < gh.size(); i++){
            if (x.get(i) < rightWall && x.get(i) > leftWall) {
                direction = false;
                x.set(i,x.get(i) + vx.get(i));
            }
            else {
                direction = true;
                if(x.get(i) >= rightWall) x.set(i,rightWall);
                if(x.get(i) <= leftWall) x.set(i,leftWall);
                vx.set(i, vx.get(i) * -1);
                x.set(i, x.get(i) + vx.get(i));
            }


            if (y.get(i) < bottomWall && y.get(i) > topWall) {
                y.set(i, y.get(i) + vy.get(i));
            }
            else {
                if(y.get(i) <= topWall) y.set(i, topWall);
                if(y.get(i) >= bottomWall) y.set(i,bottomWall);
                vy.set(i, vy.get(i) * -1);
                y.set(i, y.get(i) + vy.get(i));
            }


        }}

    protected void onProgressUpdate(ArrayList<ImageView>... ghosts) {
//        Log.v("Thing","Lol" + ghosts.length);
//        ArrayList<ImageView> gh = new ArrayList<ImageView>(Arrays.asList(ghosts));
//        ArrayList<ImageView> gh = ghosts[0];
        for(int i = 0; i < gh.size(); i++) {
            Boolean alive = a.get(i);
            gh.get(i).setX(x.get(i));
            gh.get(i).setY(y.get(i));
            if(alive == true) {
                if (vx.get(i) > 0) {
                    gh.get(i).setImageResource(R.drawable.ghostright);
                }
                if (vx.get(i) < 0) {
                    gh.get(i).setImageResource(R.drawable.ghostleft);
                }
            }
            else{
                final int number = i;
                gh.get(number).setVisibility(View.INVISIBLE);
                Handler timer = new Handler();
                timer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gh.get(number).setVisibility(View.VISIBLE);
                        a.set(number, true);
                    }
                }, (int) (5000 / (this.difficulty + 1)));
            }
        }


    }

    protected void onPostExecute(Void v) {

    }

    public boolean getDirection() {
        return direction;
    }

    public ArrayList<Float> getVx(){
        return vx;
    }

    public Rect getRect() {
        return this.rect;
    }

    public ArrayList<Float> getX(){
        return this.x;
    };
    public ArrayList<Float> getY(){
        return this.y;
    };
    public ArrayList<ImageView> getGh(){
        return this.gh;
    }
    public ArrayList<Boolean> getA(){
        return a;
    }
    public ArrayList<Boolean> getMos(){
        return mos;
    }
    public int getDifficulty() { return this.difficulty; }
    public void setDifficulty(int diff) { this.difficulty = diff;}


}



//public class GhostMoveHandler extends AsyncTask<ArrayList<ImageView>, ArrayList<ImageView>, Void> {
//    //float x, y;
//    //float vx, vy;
//    float leftWall = 0, rightWall = 730, topWall = 0, bottomWall = 810;
//    private boolean direction = true;
//    private int cons;
//    private Rect rect = new Rect();
//    float[] x = new float[15];
//    float[] y = new float[15];
//    float[] vx = new float[15];
//    float[] vy = new float[15];
//    ArrayList<ImageView> gh;
//
//    public GhostMoveHandler() {
//        Random rand = new Random();
//        Random dir = new Random();
//        for(int i = 0; i < 15; i++) {
//            x[i] = ((float)(rand.nextInt((int) rightWall -  60) + 30));
//            y[i] = ((float)(rand.nextInt((int) bottomWall - 60) + 30));
//        }
//
//
//        if(dir.nextInt(2) == 1) {
//            cons = 1;
//        }
//        else {
//            cons = -1;
//        }
//        for(int i = 0; i < 15; i++) {
//            vx[i] = (float)cons*(rand.nextInt(5));
//        }
//
//
//        if(dir.nextInt(2) == 1) {
//            cons = 1;
//        }
//        else {
//            cons = -1;
//        }
//        for(int i = 0; i < 15; i++) {
//            vy[i] = (float)cons*(rand.nextInt(5));
//        }
//
//    }
//
//    protected Void doInBackground(ArrayList<ImageView>... ghosts) {
//        Log.v("thing","whoaa" + ghosts.length);
//        gh = ghosts[0];
//        for(int i = 0; i < 15; i++) {
//            gh.get(i).getDrawingRect(rect);
//        }
//
//        while (true) {
//            move();
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            publishProgress();
//        }
//
//    }
//
//    public void move() {
//        for(int i = 0; i < 15; i++){
//            if (x[i] < rightWall && x[i] > leftWall) {
//                direction = false;
//                x[i] += vx[i];
//            }
//            else {
//                direction = true;
//                if(x[i] >= rightWall) x[i] = rightWall;
//                if(x[i] <= leftWall) x[i] = leftWall;
//                vx[i] *= -1 ;
//                x[i] += vx[i];
//            }
//
//
//            if (y[i] < bottomWall && y[i] > topWall) {
//                y[i] += vy[i];
//            }
//            else {
//                if(y[i] <= topWall) y[i] = topWall;
//                if(y[i] >= bottomWall) y[i] = bottomWall;
//                vy[i] *= -1 ;
//                y[i] += vy[i];
//            }
//        }
//
//
//    }
//
//    protected void onProgressUpdate(ArrayList<ImageView>... ghosts) {
//        Log.v("Thing","Lol" + ghosts.length);
////        ArrayList<ImageView> gh = new ArrayList<ImageView>(Arrays.asList(ghosts));
////        ArrayList<ImageView> gh = ghosts[0];
//        for(int i = 0; i < 15; i++) {
//            gh.get(i).setX(x[i]);
//            gh.get(i).setY(y[i]);
//            if(vx[i] > 0){
//                gh.get(i).setImageResource(R.drawable.ghostright);
//            }
//            if(vx[i] < 0){
//                gh.get(i).setImageResource(R.drawable.ghostleft);
//            }
//        }
//
//
//    }
//
//    protected void onPostExecute(Void v) {
//
//    }
//
//    public boolean getDirection() {
//        return direction;
//    }
//
//    public float[] getVx(){
//        return vx;
//    }
//
//    public Rect getRect() {
//        return this.rect;
//    }
//}
