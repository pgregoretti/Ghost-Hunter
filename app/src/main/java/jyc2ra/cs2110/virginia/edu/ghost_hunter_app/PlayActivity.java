package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class PlayActivity extends ActionBarActivity {
    public ImageButton soundb;
    public ImageView dpad;
    private double time;
    private double touch_x;
    private double touch_y;
    private double center_x;
    private double center_y;
    final MediaPlayer mp = new MediaPlayer();
    final MediaPlayer bp = new MediaPlayer();
    final MediaPlayer sp = new MediaPlayer();
    private ImageView coinImage, redHeartImage, goldHeartImage, ghostBombImage, mightyOakenShieldImage;
    private ArrayList<ImageView> drops;
    //    public ImageView h;
//    public ImageView ghost;
    ImageView h;
    ImageView ghost1,ghost2,ghost3,ghost4,ghost5,ghost6,ghost7,
            ghost8,ghost9,ghost10,ghost11,ghost12,ghost13,ghost14, ghost15;
    ArrayList<ImageView> ghosts;
    private MotionEvent e_event;
    HumanMoveHandler hmh;
    GhostMoveHandler gmh;
    RandomDropHandler rdh;
    private boolean deathScore = true;
    private int scoreatDeath = 0;
    ImageView thing;
    ImageView explode;
    private long score1;
    private long score2;
    private long score3;
    private long score4;
    private long score5;
    private int difficulty = 0;

//    public Rect rc1;
//
//    public Rect rc2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_play);
        e_event = null;
        dpad = (ImageView) findViewById(R.id.dpad);

        LinearLayout.LayoutParams ex = new LinearLayout.LayoutParams(120, 120);
        explode = new ImageView(PlayActivity.this);
        explode.setLayoutParams(ex);
        explode.setImageResource(R.drawable.bombexplosion);
        explode.setX(-1550);
        explode.setY(-1550);

        SharedPreferences sharedPref = getSharedPreferences("SCORE_HOLDER", MODE_PRIVATE);

        score1 = sharedPref.getInt(getString(R.string.score1), 0);
        score2 = sharedPref.getInt(getString(R.string.score2), 0);
        score3 = sharedPref.getInt(getString(R.string.score3), 0);
        score4 = sharedPref.getInt(getString(R.string.score4), 0);
        score5 = sharedPref.getInt(getString(R.string.score5), 0);

//        for(int i = 0; i < 15; i++) {
//            ghosts.add((ImageView) findViewById(R.id.ghostLeft));
//        }

        final RelativeLayout mylayout = (RelativeLayout) findViewById(R.id.container);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
        ghosts = new ArrayList<ImageView>();

        ghost1 = new ImageView(PlayActivity.this);
        ghost1.setLayoutParams(layoutParams);
        mylayout.addView(ghost1);
        ghost2 = new ImageView(PlayActivity.this);
        ghost2.setLayoutParams(layoutParams);
        mylayout.addView(ghost2);
        ghost3 = new ImageView(PlayActivity.this);
        ghost3.setLayoutParams(layoutParams);
        mylayout.addView(ghost3);
        ghost4 = new ImageView(PlayActivity.this);
        ghost4.setLayoutParams(layoutParams);
        mylayout.addView(ghost4);
        ghost5 = new ImageView(PlayActivity.this);
        ghost5.setLayoutParams(layoutParams);
        mylayout.addView(ghost5);
        ghost6 = new ImageView(PlayActivity.this);
        ghost6.setLayoutParams(layoutParams);
        mylayout.addView(ghost6);
        ghost7 = new ImageView(PlayActivity.this);
        ghost7.setLayoutParams(layoutParams);
        mylayout.addView(ghost7);
        ghost8 = new ImageView(PlayActivity.this);
        ghost8.setLayoutParams(layoutParams);
        mylayout.addView(ghost8);
        ghost9 = new ImageView(PlayActivity.this);
        ghost9.setLayoutParams(layoutParams);
        mylayout.addView(ghost9);
        ghost10 = new ImageView(PlayActivity.this);
        ghost10.setLayoutParams(layoutParams);
        mylayout.addView(ghost10);
        ghost11 = new ImageView(PlayActivity.this);
        ghost11.setLayoutParams(layoutParams);
        mylayout.addView(ghost11);
        ghost12 = new ImageView(PlayActivity.this);
        ghost12.setLayoutParams(layoutParams);
        mylayout.addView(ghost12);
        ghost13 = new ImageView(PlayActivity.this);
        ghost13.setLayoutParams(layoutParams);
        mylayout.addView(ghost13);
        ghost14 = new ImageView(PlayActivity.this);
        ghost14.setLayoutParams(layoutParams);
        mylayout.addView(ghost14);
        ghost15 = new ImageView(PlayActivity.this);
        ghost15.setLayoutParams(layoutParams);
        mylayout.addView(ghost15);

        ghosts.add(ghost1);
        ghosts.add(ghost2);
        ghosts.add(ghost3);
        ghosts.add(ghost4);
        ghosts.add(ghost5);
        ghosts.add(ghost6);
        ghosts.add(ghost7);
        ghosts.add(ghost8);
        ghosts.add(ghost9);
        ghosts.add(ghost10);
        ghosts.add(ghost11);
        ghosts.add(ghost12);
        ghosts.add(ghost13);
        ghosts.add(ghost14);
        ghosts.add(ghost15);



        h = (ImageView) findViewById(R.id.playerFront);
//        LinearLayout.LayoutParams layoutthing = new LinearLayout.LayoutParams(36, 63);
//        h = new ImageView(PlayActivity.this);
//        h.setLayoutParams(layoutthing);
//        mylayout.addView(h);

//        e_ghost1 = new ImageView(PlayActivity.this);
//        e_ghost1.setLayoutParams(layoutParams);
//        mylayout.addView(e_ghost1);
//        GhostMoveHandler gmh2 = new GhostMoveHandler();
//        StartAsyncTaskInParallellogram(gmh2);
//        e_ghost2 = new ImageView(PlayActivity.this);
//        e_ghost2.setLayoutParams(layoutParams);
//        mylayout.addView(e_ghost2);
//        GhostMoveHandler gmh3 = new GhostMoveHandler();
//        StartAsyncTaskInParallellogram(gmh3);
//        e_ghost3 = new ImageView(PlayActivity.this);
//        e_ghost3.setLayoutParams(layoutParams);
//        mylayout.addView(e_ghost3);
//        GhostMoveHandler gmh4 = new GhostMoveHandler();
//        StartAsyncTaskInParallellogram(gmh4);

        TextView scoreCard = (TextView) findViewById(R.id.scoreCard);
        TextView health = (TextView) findViewById((R.id.health));
        Context context = this;
        hmh = new HumanMoveHandler(e_event, health);
        gmh = new GhostMoveHandler(ghosts.size());
        GhostCollisionHandler gch = new GhostCollisionHandler(hmh,gmh,explode);
        health.setText("Health: " + hmh.getHealth());
        StartAsyncTaskInParallel(hmh);
        StartAsyncTaskInParallellogram(gmh);
        StartAsyncTaskInPar(gch);
        //StartAsyncTaskInPar(bombAsyncTask);


        final int[] viewCoords = new int[2];
        dpad.getLocationOnScreen(viewCoords);
        center_x = viewCoords[0] + 100;
        center_y = viewCoords[1] + 100;
        hmh.setHealth(5);
        dpad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                e_event = event;
                hmh.setEvent(e_event);

                if(event.getAction() == event.ACTION_UP){
                    hmh.setEvent(null);
                }
                return true;
            }

        });


        Rect rc1 = new Rect();
        h.getDrawingRect(rc1);
        Rect rc2 = new Rect();
        ghosts.get(14).getDrawingRect(rc2);

//        rc1  = new Rect();
//        rc2  = new Rect();
//        AsyncTask<Void, Void, Void> hgcoll = new AsyncTask<Void, Void, Void>() {
//            Toast gameOver = Toast.makeText(PlayActivity.this, "You hit a ghost and died!", Toast.LENGTH_SHORT);
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                try {
//                    while(true){
//                        Thread.sleep(40);
//                        h.getDrawingRect(rc1);
//                        ghost.getDrawingRect(rc2);
//                        if (Math.abs(h.getX() - ghost.getX()) < 10){
//                            Log.v("Point1", "Collision check happened");
//                            publishProgress();
//                            //hmh.cancel(true);
//                        }
//                    }
//                }
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onProgressUpdate(Void... params) {
//                gameOver.setGravity(Gravity.CENTER, 0, 0);
//                gameOver.show();
//            }
//
//        };
//        StartAsyncTaskInPar(hgcoll);

        coinImage = new ImageView(PlayActivity.this);
        coinImage.setImageResource(R.drawable.coin1);
        coinImage.setBackgroundResource(R.drawable.spin_animation);
        coinImage.setLayoutParams(layoutParams);
        mylayout.addView(coinImage);
        coinImage.setX(-4000);
        redHeartImage = new ImageView(PlayActivity.this);
        redHeartImage.setImageResource(R.drawable.redheart1);
        redHeartImage.setBackgroundResource(R.drawable.redheart_animation);
        redHeartImage.setLayoutParams(layoutParams);
        mylayout.addView(redHeartImage);
        redHeartImage.setX(-4000);
        goldHeartImage = new ImageView(PlayActivity.this);
        goldHeartImage.setImageResource(R.drawable.goldheart1);
        goldHeartImage.setBackgroundResource(R.drawable.goldheart_animation);
        goldHeartImage.setLayoutParams(layoutParams);
        mylayout.addView(goldHeartImage);
        goldHeartImage.setX(-4000);
        ghostBombImage = new ImageView(PlayActivity.this);
        ghostBombImage.setImageResource(R.drawable.ghostbombblue);
        ghostBombImage.setBackgroundResource(R.drawable.ghostbomb_animation);
        ghostBombImage.setLayoutParams(layoutParams);
        mylayout.addView(ghostBombImage);
        ghostBombImage.setX(-4000);
        mightyOakenShieldImage = new ImageView(PlayActivity.this);
        mightyOakenShieldImage.setImageResource(R.drawable.mos);
        mightyOakenShieldImage.setLayoutParams(layoutParams);
        mylayout.addView(mightyOakenShieldImage);
        mightyOakenShieldImage.setX(-4000);
        drops =  new ArrayList<ImageView>();
        drops.add(coinImage);
        drops.add(redHeartImage);
        drops.add(goldHeartImage);
        drops.add(ghostBombImage);
        drops.add(mightyOakenShieldImage);
        rdh = new RandomDropHandler(hmh, gmh, drops);
        StartSpawnerInParallel(rdh);

        //Declare the timer
        Timer t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView tv = (TextView) findViewById(R.id.scoreCard);
                        int score = (int) (SystemClock.currentThreadTimeMillis() / 100 +
                                hmh.getScore());
                        gmh.setDifficulty(difficulty);
                        if (score >= 1000 && difficulty == 0) {
                            hmh.setXwaycons(hmh.getXwaycons() * 0.75);
                            hmh.setYwaycons(hmh.getYwaycons() * 0.75);
                            difficulty = 1;
                            Toast dif = Toast.makeText(PlayActivity.this,
                                    "Difficulty increased! Player speed decreased! " +
                                            "Ghost spawn rate increased!",
                                    Toast.LENGTH_LONG);
                            dif.setGravity(Gravity.BOTTOM, 0, 200);
                            dif.show();
                        }
                        if (score >= 2000 && difficulty == 1) {
                            hmh.setXwaycons(hmh.getXwaycons() * 0.75);
                            hmh.setYwaycons(hmh.getYwaycons() * 0.75);
                            difficulty = 2;
                            Toast dif = Toast.makeText(PlayActivity.this,
                                    "Difficulty increased! Player speed decreased! " +
                                            "Ghost spawn rate increased!",
                                    Toast.LENGTH_LONG);
                            dif.setGravity(Gravity.BOTTOM, 0, 200);
                            dif.show();
                        }
                        if (score >= 3000 && difficulty == 2) {
                            hmh.setXwaycons(hmh.getXwaycons() * 0.75);
                            hmh.setYwaycons(hmh.getYwaycons() * 0.75);
                            difficulty = 3;
                            Toast dif = Toast.makeText(PlayActivity.this,
                                    "Maximum difficulty! Player speed decreased! " +
                                            "Ghost spawn rate increased!",
                                    Toast.LENGTH_LONG);
                            dif.setGravity(Gravity.BOTTOM, 0, 200);
                            dif.show();
                        }
                        if (deathScore && (hmh.isCancelled() || gmh.isCancelled())) {
                            scoreatDeath = score;
                            deathScore = false;
                        }
                        if (hmh.isCancelled() || gmh.isCancelled()) {
                            tv.setText("Score: " + scoreatDeath);
                        }
                        else {
                            tv.setText("Score: "+ score);
                        }

//                        if(rdh.getMosssound() == true){
//                            executeSound(sp,"MOSaudio.mp3");
//                        }

                    }

                });
            }

        }, 0, 100);


    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartAsyncTaskInParallel(HumanMoveHandler tasker) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
//            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ghost);
            tasker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, h);
        }
        else
            tasker.execute(h);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartAsyncTaskInParallellogram(GhostMoveHandler task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ghosts, ghosts);
        }
        else
            task.execute(ghosts, ghosts);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartAsyncTaskInPar(AsyncTask task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
            task.execute();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartSpawnerInParallel(RandomDropHandler task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
            task.execute();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        soundb = (ImageButton) findViewById(R.id.soundButton);
        executeSoundLoop(mp, "ambiance.mp3");
        soundb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    stopSound(mp);
                } else {
                    executeSoundLoop(mp, "ambiance.mp3");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopSound(mp);
        SharedPreferences sharedPref = getSharedPreferences("SCORE_HOLDER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(scoreatDeath > score1) {
            editor.putInt(getString(R.string.score1), scoreatDeath)
                    .putInt(getString(R.string.score2),(int) score1)
                    .putInt(getString(R.string.score3),(int) score2)
                    .putInt(getString(R.string.score4),(int) score3)
                    .putInt(getString(R.string.score5),(int) score4);
            editor.commit();

        }
        else if(scoreatDeath > score2) {
            editor.putInt(getString(R.string.score2), scoreatDeath)
                    .putInt(getString(R.string.score3),(int) score2)
                    .putInt(getString(R.string.score4),(int) score3)
                    .putInt(getString(R.string.score5),(int) score4);
            editor.commit();

        }
        else if(scoreatDeath > score3) {
            editor.putInt(getString(R.string.score3), scoreatDeath)
                    .putInt(getString(R.string.score4),(int) score3)
                    .putInt(getString(R.string.score5),(int) score4);
            editor.commit();

        }
        else if(scoreatDeath > score4) {
            editor.putInt(getString(R.string.score4), scoreatDeath)
                    .putInt(getString(R.string.score5),(int) score4);
            editor.commit();

        }
        else if(scoreatDeath > score5) {
            editor.putInt(getString(R.string.score5), scoreatDeath);
            editor.commit();

        }
    }

    public void executeSoundLoop(MediaPlayer mp, String fileName) {
        try {
            mp.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd(fileName);
            mp.setDataSource(afd.getFileDescriptor(),
                    afd.getStartOffset(), afd.getLength());
            mp.prepare();
            mp.start();
            mp.setLooping(true);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeSound(MediaPlayer mp, String fileName) {
        try {
            mp.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd(fileName);
            mp.setDataSource(afd.getFileDescriptor(),
                    afd.getStartOffset(), afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopSound(MediaPlayer mp) {
        mp.stop();
        mp.setLooping(false);
    }


    float x_coord;
    float y_coord;
    private int saver;
    private Rect expRect = new Rect();

    public void bomb(View view){
        if(saver ==1){
            final Toast bob = Toast.makeText(PlayActivity.this, "Bomb already dropped!", Toast.LENGTH_SHORT);
            bob.setGravity(Gravity.BOTTOM, 0, 200);
            bob.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bob.cancel();
                }
            }, 400);
        }
        if(saver == 0) {
            saver = 1;
            final Toast toast = Toast.makeText(PlayActivity.this, "Bomb dropped!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 400);

            thing = new ImageView(PlayActivity.this);
            final RelativeLayout mylayout = (RelativeLayout) findViewById(R.id.container);
            thing.setImageResource(R.drawable.bombdrop);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
            thing.setLayoutParams(layoutParams);
            mylayout.addView(thing);
            x_coord = (float)h.getX() + 7;
            y_coord = (float)h.getY() + 17;
            thing.setX(x_coord);
            thing.setY(y_coord);
            executeSound(bp,"Explosion Sound.mp3");

//            LinearLayout.LayoutParams ex = new LinearLayout.LayoutParams(500, 500);
//            explode = new ImageView(PlayActivity.this);
//            explode.setLayoutParams(ex);
//            explode.setImageResource(R.drawable.bombexplosion);

            //explode.getDrawingRect(expRect);


            Handler timer = new Handler();
            final Handler timer2 = new Handler();
            timer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mylayout.removeView(thing);
                    mylayout.addView(explode);
                    explode.setX(x_coord - 39);
                    explode.setY(y_coord - 35);

                }
            }, 1100);

//            int i = 0;
//            while(i < gmh.getGh().size()){
//                Rect one = new Rect(explode.getLeft(), explode.getTop(),explode.getRight(),explode.getBottom());
//                Rect two = new Rect(gmh.getGh().get(i).getLeft(),gmh.getGh().get(i).getTop(),gmh.getGh().get(i).getRight(),gmh.getGh().get(i).getBottom());
//
//                if(one.intersect(two)){
//                    Log.v("thing","Bomb Intersected!");
//                    gmh.getA().set(i,false);
//                }
//                i++;
//            }

            timer2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    explode.setX(-1000);
                    explode.setY(-1000);
                    mylayout.removeView(explode);
                    saver = 0;
                }
            }, 2000);
        }

        if(Rect.intersects(expRect, gmh.getRect())) {
            Toast gdeath = Toast.makeText(PlayActivity.this, "Ghost Dead", Toast.LENGTH_SHORT);
            gdeath.setGravity(Gravity.BOTTOM, 0, 200);
            gdeath.show();

        }

    }

//    private AsyncTask<Void, Void, Void> bombAsyncTask = new AsyncTask<Void, Void, Void>() {
//        LinearLayout.LayoutParams ex = new LinearLayout.LayoutParams(120, 120);
//        Handler timer = new Handler();
//        final Handler timer2 = new Handler();
//        final RelativeLayout mylayout = (RelativeLayout) findViewById(R.id.container);
//        @Override
//        protected Void doInBackground(Void... params) {
//            try {
//                Thread.sleep(20);
//                if (saver == 1) {
//                    final Toast bob = Toast.makeText(PlayActivity.this, "Bomb already dropped!", Toast.LENGTH_SHORT);
//                    bob.setGravity(Gravity.BOTTOM, 0, 200);
//                    bob.show();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            bob.cancel();
//                        }
//                    }, 400);
//                }
//                if (saver == 0) {
//                    saver = 1;
//                    final Toast toast = Toast.makeText(PlayActivity.this, "Bomb dropped!", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.BOTTOM, 0, 200);
//                    toast.show();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            toast.cancel();
//                        }
//                    }, 400);
//
//                    thing = new ImageView(PlayActivity.this);
////                    final RelativeLayout mylayout = (RelativeLayout) findViewById(R.id.container);
//                    thing.setImageResource(R.drawable.bombdrop);
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
//                    thing.setLayoutParams(layoutParams);
//                    mylayout.addView(thing);
//                    x_coord = (float) h.getX() + 7;
//                    y_coord = (float) h.getY() + 17;
////                    thing.setX(x_coord);
////                    thing.setY(y_coord);
//
////                    LinearLayout.LayoutParams ex = new LinearLayout.LayoutParams(120, 120);
//                    explode = new ImageView(PlayActivity.this);
////                    explode.setImageResource(R.drawable.bombexplosion);
////                    explode.setLayoutParams(ex);
//                    explode.getDrawingRect(expRect);
//
//
////                    Handler timer = new Handler();
////                    final Handler timer2 = new Handler();
////                    timer.postDelayed(new Runnable() {
////                        @Override
////                        public void run() {
////                            mylayout.removeView(thing);
////                            mylayout.addView(explode);
////                            explode.setX(x_coord - 39);
////                            explode.setY(y_coord - 35);
////                        }
////                    }, 1000);
////
////                    timer2.postDelayed(new Runnable() {
////                        @Override
////                        public void run() {
////                            mylayout.removeView(explode);
////                            saver = 0;
////                        }
////                    }, 2000);
//                }
//
//                if(Rect.intersects(expRect, gmh.getRect())) {
//                    final Toast gdeath = Toast.makeText(PlayActivity.this, "Ghost dead",
//                            Toast.LENGTH_SHORT);
//                    gdeath.setGravity(Gravity.BOTTOM, 0, 200);
//                    gdeath.show();
//                    //this.cancel(true);
//                }
//                publishProgress();
//
//            } catch(InterruptedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//        @Override
//        protected void onProgressUpdate(Void... params) {
//            thing.setX(x_coord);
//            thing.setY(y_coord);
//            explode.setImageResource(R.drawable.bombexplosion);
//            explode.setLayoutParams(ex);
//            timer.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mylayout.removeView(thing);
//                    mylayout.addView(explode);
//                    explode.setX(x_coord - 39);
//                    explode.setY(y_coord - 35);
//                }
//            }, 1000);
//
//            timer2.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mylayout.removeView(explode);
//                    saver = 0;
//                }
//            }, 2000);
//        }
//    };

    public void ghostSetUp() {

    }

    public void humanSetUp() {
        Log.v("Here", "Nigga we made it");
    }

//    public void move(ImageView view) {
//        final int[] viewCoords = new int[2];
//        view.getLocationOnScreen(viewCoords);
//        center_x = viewCoords[0] + 100;
//        center_y = viewCoords[1] + 100;
//
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                touch_x = event.getX();
//                touch_y = event.getY();
//                if (touch_x > 190) {
//                    touch_x = 0;
//                    touch_y = 0;
//                }
//                if (touch_y < -10) {
//                    touch_x = 0;
//                    touch_y = 0;
//                }
//                double math_x = touch_x - center_x;
//                double math_y = touch_y - center_y;
//
//                double angle = (Math.atan2(math_y, math_x) * 180 / Math.PI);
//                double x_way = Math.cos(angle * Math.PI / 180);
//                double y_way = Math.sin(angle * Math.PI / 180);
//
//
//                if (touch_x != 0 || touch_y != 0) {
//                    h.setX((float) (h.getX() + x_way * 5));
//                    h.setY((float) (h.getY() + y_way * 5));
//
//                    if (h.getX() < 120) {
//                        h.setX(120);
//                    }
//                    if (h.getX() > 632) {
//                        h.setX(632);
//                    }
//                    if (h.getY() < 65) {
//                        h.setY(65);
//                    }
//                    if (h.getY() > 785) {
//                        h.setY(785);
//                    }
//                }
//
//                return true;
//            }
//        });
//    }

}
