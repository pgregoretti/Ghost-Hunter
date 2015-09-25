package jyc2ra.cs2110.virginia.edu.ghost_hunter_app;
/*
T104-05
Jeffery Cui			jyc2ra
John Zhang			jwz2kn
Jeremy Little	    jdl2fr
Pam Gregoretti		prg5rv

 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private long score1;
    private long score2;
    private long score3;
    private long score4;
    private long score5;

    public void sendMessage(View view) {
        //Intent intent = new Intent();
        //startActivity(intent);
        Toast toast = Toast.makeText(MainActivity.this, "Game started!", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(MainActivity.this, PlayActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish(); //I think we'll have to finish the main activity some other way
        //but we can leave this in for now and see.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("SCORE_HOLDER", MODE_PRIVATE);

        score1 = sharedPref.getInt(getString(R.string.score1), 0);
        score2 = sharedPref.getInt(getString(R.string.score2), 0);
        score3 = sharedPref.getInt(getString(R.string.score3), 0);
        score4 = sharedPref.getInt(getString(R.string.score4), 0);
        score5 = sharedPref.getInt(getString(R.string.score5), 0);

        TextView scores = (TextView) findViewById(R.id.scores2);
        scores.setText("1.   " + score1 + " \n\n2.   " + score2 + "\n\n3.   " + score3 + "\n\n4.   " + score4 + "\n\n5.   " + score5);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
