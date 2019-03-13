package com.example.activitease;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    EditText interestName, periodFrequency, basePeriodSpan, activityLength, numNotifications;
    Interest interest = new Interest();
    String startStopTimerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FragmentTransaction hp = getSupportFragmentManager().beginTransaction();
        hp.replace(R.id.fragment_container, new Home_Page_Fragment());
        hp.commit();


        User db = new User(this);





        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.homePage) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_Page_Fragment()).commit();
        }
        else if (id == R.id.FAQ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FAQ_Fragment()).commit();

        } else if (id == R.id.Interest) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Interest_Fragment()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openAddInterest(View view)
    {
        FragmentTransaction hp = getSupportFragmentManager().beginTransaction();
        hp.replace(R.id.fragment_container, new Add_Interest_Fragment());
        hp.commit();
    }
    /*
    public void submitEditInterest(View view)
    {
        //Throw error if in invalid format
        //Call instance of user to store data
        //From user call function to store data
        //Then return next page with updated content
        interestName = findViewById(R.id.editText);
        String theInterestName = interestName.getText().toString();
        interest.setInterestName(theInterestName);
        periodFrequency = findViewById(R.id.periodFreq);
        String periodFreq = periodFrequency.getText().toString();
        interest.setPeriodFreq(periodFreq);
        activityLength = findViewById(R.id.activityLength);
        String theActivityLength = activityLength.getText().toString();
        interest.setActivityLength(theActivityLength);
        basePeriodSpan = findViewById(R.id.basePeriodSpan);
        String thePeriodSpan = basePeriodSpan.getText().toString();
        interest.setBasePeriodSpan(thePeriodSpan);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Interest_Fragment()).commit();
    }
    */
    public void setButtonText(String buttonText)
    {
        Button b = findViewById(R.id.startStop);
        b.setText(buttonText);

    }

    public void startStopTimer(View view) {
        Button b = (Button)view;
        startStopTimerText = b.getText().toString();
        if(startStopTimerText.equals("Start Activity")) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Start Activity")
                    .setMessage("Are you sure you want to start this activity?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startStopTimerText = "Done";
                            setButtonText(startStopTimerText);
                        }

                    })
                    .setNegativeButton("no", null)
                    .show();
        }
        else if(startStopTimerText.equals("Done")) {
            startStopTimerText = "Start Activity";
            setButtonText(startStopTimerText);
            //Update timer. Update DB with new interest data
        }

    }

    public void addInterest(View view){

        User db = new User(this);
        //EditText interestName = findViewById(R.id.interestName);
//        String intName= interestName.getText().toString();
//        int periodFreq = Integer.parseInt(periodFrequency.getText().toString());
//        int periodSpan = Integer.parseInt(basePeriodSpan.getText().toString());
//        int length = Integer.parseInt(activityLength.getText().toString());
//        int numNot = Integer.parseInt(numNotifications.getText().toString());


        String intName = "Hello";
        int periodFreq = 3;
        int periodSpan = 2;
        int length = 1;
        int numNot = 4;
        interest = new Interest(intName, periodFreq, periodSpan, length, numNot);



        db.addInterest(interest);


    }

}
