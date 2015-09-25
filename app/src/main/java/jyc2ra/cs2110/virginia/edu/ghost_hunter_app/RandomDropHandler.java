package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
* Created by pamelons on 4/26/15.
*/
public class RandomDropHandler extends AsyncTask<ImageView, Void, Void> {
    private HumanMoveHandler p;
    private GhostMoveHandler g;
    private int dropDecider;
    private float playerX;
    private float playerY;
    private float playerXRight;
    private float playerYBottom;
    private float dropX;
    private float dropY;
    private float ghostX;
    private float ghostXRight;
    private float ghostY;
    private float ghostYBottom;
    private boolean redHeartAppear; //booleans to turn on/off collision detector
    private boolean goldHeartAppear;
    private boolean ghostBombAppear;
    private boolean mightyOakenShieldAppear;
    private boolean coinAppear;
    private boolean ghostDetect;
    private boolean checkLeftX;
    private boolean checkTopY;
    private boolean checkRightX;
    private boolean checkBottomY;
    private boolean mossound;
    private ArrayList<AnimationDrawable> drops = new ArrayList<>();
    private ArrayList<ImageView> dr;
    private ImageView coinImage, redHeartImage, goldHeartImage, ghostBombImage, mightyOakenShieldImage;
    private Random randomGen;
    private AnimationDrawable coinAnimation, redHeartAnimation, goldHeartAnimation, ghostBombAnimation;

    public RandomDropHandler(HumanMoveHandler p, GhostMoveHandler g, ArrayList<ImageView> z) {
        mossound = false;
        dr = new ArrayList<ImageView>();
        this.dr = z;
        Log.v("Thing", "Inside RDH:  " + dr.size());
        coinImage = dr.get(0);
        redHeartImage = dr.get(1);
        goldHeartImage = dr.get(2);
        ghostBombImage = dr.get(3);
        mightyOakenShieldImage = dr.get(4);
//        coinImage = dr.get(0);
//        coinImage.setImageResource(R.drawable.coin1);
        coinImage.setBackgroundResource(R.drawable.spin_animation);
        coinAnimation = (AnimationDrawable) coinImage.getBackground();
        coinAnimation.start();
//
//        redHeartImage.setImageResource(R.drawable.redheart1);
        redHeartImage.setBackgroundResource(R.drawable.redheart_animation);
        redHeartAnimation = (AnimationDrawable) redHeartImage.getBackground();
        redHeartAnimation.start();
//
//        goldHeartImage.setImageResource(R.drawable.goldheart1);
        goldHeartImage.setBackgroundResource(R.drawable.goldheart_animation);
        goldHeartAnimation = (AnimationDrawable) goldHeartImage.getBackground();
        goldHeartAnimation.start();
//
//        ghostBombImage.setImageResource(R.drawable.ghostbombblue);
        ghostBombImage.setBackgroundResource(R.drawable.ghostbomb_animation);
        ghostBombAnimation = (AnimationDrawable) ghostBombImage.getBackground();
        ghostBombAnimation.start();
//
//        drops.add(coinAnimation); //coin 0
//        drops.add(redHeartAnimation); //red heart 1
//        drops.add(goldHeartAnimation); //gold heart 2
//        drops.add(ghostBombAnimation); //ghost bomb 3
//        coinImage.setVisibility(View.INVISIBLE);
//        redHeartImage.setVisibility(View.INVISIBLE);
//        goldHeartImage.setVisibility(View.INVISIBLE);
//        ghostBombImage.setVisibility(View.INVISIBLE);

        this.p = p;
        this.g = g;
        this.playerX = p.getX();
        this.playerY = p.getY();

        randomGen = new Random(); //change to 3 when make MOS
        //Random randomX = new Random(512); //+ 120
        // Random randomY = new Random(720); //+65

    }

    protected Void doInBackground(ImageView...o) {
        while(true){
            try {
                //dropDecider = randomGen.nextInt(4); //change to 5 when add MOS
                dropDecider = randomGen.nextInt(4);
                dropX = randomGen.nextInt(512) + 120;
                dropY = randomGen.nextInt(720) + 65;
                //checkLeftX = (dropX <= playerXRight) && (dropX > playerX);
                //checkTopY = (dropY >= playerY) && (dropY < playerYBottom);

                if(dropDecider==4) { //mos

                    mightyOakenShieldImage = dr.get(4);
                    mightyOakenShieldAppear = true;
                    publishProgress();


                    while (mightyOakenShieldAppear) {
                        playerX = p.getX();
                        playerY = p.getY();
                        playerXRight = playerX + 36;
                        playerYBottom = playerY + 63;
                        checkLeftX = (dropX <= playerXRight) && (dropX > playerX);
                        checkTopY = (dropY >= playerY) && (dropY < playerYBottom);
                        checkRightX = ((dropX + 24) >= playerX) && ((dropX + 24) < playerXRight);
                        checkBottomY = ((dropY + 31) >= playerY) && ((dropY + 31) < playerYBottom);

                        if (checkLeftX || checkTopY || checkRightX || checkBottomY) {
                            for (int i = 0; i < g.getMos().size(); i++) {
                                g.getMos().set(i, true);
                            }


                            mightyOakenShieldAppear = false;
                            publishProgress();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }



                if(dropDecider==0) { //coin
//                    dr.get(0).setX(dropX);
//                    dr.get(0).setY(dropY);
                    coinImage = dr.get(0);
//                    coinImage.setVisibility(View.VISIBLE);
//                    coinAnimation.start();
                    coinAppear=true;
                    publishProgress();

                    while(coinAppear){
                        playerX = p.getX();
                        playerY = p.getY();
                        playerXRight = playerX + 36;
                        playerYBottom = playerY + 63;
                        checkLeftX = (dropX <= playerXRight) && (dropX > playerX);
                        checkTopY = (dropY >= playerY) && (dropY < playerYBottom);
                        checkRightX = ((dropX + 12) >= playerX) && ((dropX + 12) < playerXRight);
                        checkBottomY = ((dropY + 16) >= playerY) && ((dropY + 16) < playerYBottom);

                        if(checkLeftX || checkTopY || checkRightX || checkBottomY){
                            //increase score
                            p.setScore(p.getScore() + 75);
//                            coinImage.setVisibility(View.INVISIBLE);
//                            coinAnimation.stop();
                            coinAppear=false;
                            publishProgress();
                        }
                        try {
                            Thread.sleep(50);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }

                if(dropDecider==1) { //red heart

                    redHeartImage = dr.get(1);

                    redHeartAppear=true;
                    publishProgress();


                    while(redHeartAppear){
                        playerX = p.getX();
                        playerY = p.getY();
                        playerXRight = playerX + 36;
                        playerYBottom = playerY + 63;
                        checkLeftX = (dropX <= playerXRight) && (dropX > playerX);
                        checkTopY = (dropY >= playerY) && (dropY < playerYBottom);
                        checkRightX = ((dropX + 20) >= playerX) && ((dropX + 20) < playerXRight);
                        checkBottomY = ((dropY + 20) >= playerY) && ((dropY + 20) < playerYBottom);

                        if(checkLeftX || checkTopY || checkRightX || checkBottomY){
                            //increase health by 1
                            if(p.getMaxHealth() > p.getHealth()) {
                                p.setHealth(p.getHealth()+1);
                            }

                            redHeartAppear=false;
                            publishProgress();
                        }
                        try {
                            Thread.sleep(50);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }

                if(dropDecider==2) { //gold heart

                    goldHeartImage = dr.get(2);

                    goldHeartAppear=true;
                    publishProgress();


                    while(goldHeartAppear){
                        playerX = p.getX();
                        playerY = p.getY();
                        playerXRight = playerX + 36;
                        playerYBottom = playerY + 63;
                        checkLeftX = (dropX <= playerXRight) && (dropX > playerX);
                        checkTopY = (dropY >= playerY) && (dropY < playerYBottom);
                        checkRightX = ((dropX + 20) >= playerX) && ((dropX + 20) < playerXRight);
                        checkBottomY = ((dropY + 20) >= playerY) && ((dropY + 20) < playerYBottom);

                        if(checkLeftX || checkTopY || checkRightX || checkBottomY){
                            //increase health to max
                            p.setHealth(p.getMaxHealth());
//                            goldHeartImage.setVisibility(View.INVISIBLE);
//                            goldHeartAnimation.stop();
                            goldHeartAppear=false;
                            publishProgress();
                        }
                        try {
                            Thread.sleep(50);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }

                if(dropDecider==3) { //ghost bomb

                    ghostBombImage = dr.get(3);

                    ghostBombAppear=true;
                    publishProgress();


                    while(ghostBombAppear){
                        playerX = p.getX();
                        playerY = p.getY();
                        playerXRight = playerX + 36;
                        playerYBottom = playerY + 63;
                        checkLeftX = (dropX <= playerXRight) && (dropX > playerX);
                        checkTopY = (dropY >= playerY) && (dropY < playerYBottom);
                        checkRightX = ((dropX + 10) >= playerX) && ((dropX + 10) < playerXRight);
                        checkBottomY = ((dropY + 10) >= playerY) && ((dropY + 10) < playerYBottom);

                        if(checkLeftX || checkTopY || checkRightX || checkBottomY){
                            //kill all ghosts
                            ghostDetect = true;

                            while(ghostDetect) { //look for ghosts
                                for(int i=0; i < g.getX().size(); i++) {

                                    ghostX = g.getX().get(i) + 40;
                                    ghostXRight = g.getX().get(i);
                                    ghostY = g.getY().get(i);
                                    ghostYBottom = g.getY().get(i) + 40;

                                    checkLeftX = ((dropX - 163) <=  ghostX) && ((dropX + 163) > ghostX);
                                    checkTopY = ((dropY + 166) >= ghostY) && ((dropY - 166) < ghostY);
                                    checkRightX = ((dropX - 163) <= ghostXRight) && ((dropX + 163) > ghostXRight);
                                    checkBottomY = ((dropY + 166) <= ghostYBottom) && ((dropY + 166) > ghostYBottom);

                                    if(checkLeftX || checkTopY || checkRightX || checkBottomY){
                                        g.getA().set(i, false);
                                        p.setScore(p.getScore() + 25);
                                    }

                                }
                                try {
                                    Thread.sleep(50);
                                } catch(InterruptedException e) {
                                    e.printStackTrace();
                                }
                                ghostDetect=false;
                            }



                            ghostBombAppear = false;
                            publishProgress();
                        }
                    }


                }
                Thread.sleep(15000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    protected void onProgressUpdate(Void...voids) {
        if(coinAppear == true){
            coinImage.setX(dropX);
            coinImage.setY(dropY);
            coinImage.setVisibility(View.VISIBLE);
            coinAnimation.start();

        }
        else if(coinAppear == false){
            coinImage.setVisibility(View.INVISIBLE);
            coinAnimation.stop();
        }
        if(redHeartAppear == true){
            redHeartImage.setX(dropX);
            redHeartImage.setY(dropY);
            redHeartImage.setVisibility(View.VISIBLE);
            redHeartAnimation.start();
        }
        else if (redHeartAppear == false) {
            redHeartImage.setVisibility(View.INVISIBLE);
            redHeartAnimation.stop();
        }
        if(goldHeartAppear == true){
            goldHeartImage.setX(dropX);
            goldHeartImage.setY(dropY);
            goldHeartImage.setVisibility(View.VISIBLE);
            goldHeartAnimation.start();
        }
        else if (goldHeartAppear == false) {
            goldHeartImage.setVisibility(View.INVISIBLE);
            goldHeartAnimation.stop();
        }
        if(ghostBombAppear == true){
            ghostBombImage.setX(dropX);
            ghostBombImage.setY(dropY);
            ghostBombImage.setVisibility(View.VISIBLE);
            ghostBombAnimation.start();
        }
        else if (ghostBombAppear == false) {
            ghostBombImage.setVisibility(View.INVISIBLE);
            ghostBombAnimation.stop();
        }
        if(mightyOakenShieldAppear == true){
            mightyOakenShieldImage.setX(dropX);
            mightyOakenShieldImage.setY(dropY);
            mightyOakenShieldImage.setVisibility(View.VISIBLE);
        }
        else if(mightyOakenShieldAppear == false){
            mossound = true;
            mightyOakenShieldImage.setVisibility(View.INVISIBLE);
        }
    }

    public ArrayList<ImageView> getDr() {
        return this.dr;
    }
    public boolean getMosssound() { return this.mossound;}

}