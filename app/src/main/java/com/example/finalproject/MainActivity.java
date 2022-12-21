package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.io.StringReader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Display the MainActivity layout
        setContentView(R.layout.activity_main);

        //Retrieve the SharedPreferences data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");

        //Set the greeting TextView with the SharedPreferences data entered by the user in the PreferencesActivity
        TextView welcomeGreeting = findViewById(R.id.welcome);
        welcomeGreeting.setText(getString(R.string.welcome) + s1);

        //This button brings you to Latest News Articles activity
        Button headlines;
        headlines = findViewById(R.id.button_headlines);
        headlines.setOnClickListener(new View.OnClickListener() {

            //OnClickListener to handle Button click -> "Latest News Headlines"
            //This will launch the new activity
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ArticlesActivity.class);
                startActivity(i);
            }
        });

        //Button to go directly to your favourite articles
        Button favourites;
        favourites = findViewById(R.id.button_favourites);
        favourites.setOnClickListener(new View.OnClickListener() {
            //This will launch the FavouritesActivity activity
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FavouritesActivity.class);
                startActivity(i);
            }
        });

        //Building the Alert Message resource we want to display
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.help_title1);
        builder.setMessage(R.string.help_dialog1);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //Identify the Help Button ImageButton
        ImageButton helpMain;
        helpMain = findViewById(R.id.help_main);

        //Set OnClickListener to display our Alert Dialogue when the ImageButton is clicked
        helpMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog ad = builder.create();
                ad.show();
            }
        });

        //Identify the Toolbar
        Toolbar tBar = findViewById(R.id.toolbar);
        setSupportActionBar(tBar);
    }


    //This is needed to display the Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    //Switch case to handle the toolbar icon choices above
    //If you are already on that activity, a Snackbar message is displayed to inform the user
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        String snackMessage = null;
        Snackbar snackBar;
        TextView snackView = findViewById(R.id.snackView);

        switch (item.getItemId()) {
            //what to do when the menu item is selected:
            case R.id.item1:
                //If item1 is selected, there is no Toast to display and message remains null
                message = null;
                break;
            case R.id.item2:
                message = getResources().getString(R.string.choice_articles);
                Intent a = new Intent(MainActivity.this, ArticlesActivity.class);
                startActivity(a);
                break;
            case R.id.item3:
                message = getResources().getString(R.string.choice_favourites);
                Intent f = new Intent(MainActivity.this, FavouritesActivity.class);
                startActivity(f);
                break;
            case R.id.item4:
                message = getResources().getString(R.string.choice_pref);
                Intent p = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(p);
                break;
        }
        //If message is not null, this means a new activity was selected, and a Toast is displayed to confirm
        if (message != null)
        {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        //If there is no message, this means the user selected the activity that is already in use
        //A Snackbar message informs the user that nothing will occur, already viewing
        else if (snackMessage == null)
        {
            snackBar = Snackbar.make(snackView, R.string.snackHome, Snackbar.LENGTH_LONG);
            snackBar.show();
        }
        return true;
    }
}