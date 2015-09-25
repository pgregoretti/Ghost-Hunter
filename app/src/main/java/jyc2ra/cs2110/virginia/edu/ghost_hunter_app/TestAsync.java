package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by John on 4/12/2015.
 */
public class TestAsync extends AsyncTask<Void, Integer, String> {
    protected void onPreExecute (){
        Log.d("PreExceute", "On pre Exceute......");
    }

    protected String doInBackground(Void...arg0) {
        Log.d("DoINBackGround","On doInBackground...");

        for(int i=0; i<10; i++){
            Integer in = new Integer(i);
            publishProgress(i);
        }
        return "You are at PostExecute";
    }

    protected void onProgressUpdate(Integer...a){
        Log.d("You are in progress update ... " + a[0],"");
    }

    protected void onPostExecute(String result) {
        Log.d(""+result,"");
    }
}
