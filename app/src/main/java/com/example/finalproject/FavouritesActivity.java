package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class FavouritesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the layout for this activity
        setContentView(R.layout.activity_favourites);

        //Create the Alert Dialogue resource we want to use
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.help_title1);
        builder.setMessage(R.string.help_dialog3);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //Set the image help button to the builder dialogue
        ImageButton helpArticles;
        helpArticles = findViewById(R.id.help_favourites);
        helpArticles.setOnClickListener(new View.OnClickListener() {
            //Display the Alert Dialogue on click of ImageButton
            @Override
            public void onClick(View view) {
                AlertDialog ad = builder.create();
                ad.show();
            }
        });

        //Identify the toolbar
        Toolbar tBar = findViewById(R.id.toolbar);
        setSupportActionBar(tBar);

        //Declare and instantiate integer variables for our ProgressBar
        int progress = 0;
        int max = 5;

        //Identify the ProgressBar
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView pBarStatus = findViewById(R.id.pbStatus);

        //Set the values of our ProgressBar
        //TODO: If I had completed my FavouritesActivity page, I would link my "progress" variable to the Count() of my Favourites ListView,
        //TODO: so that the value of "progress" would match the number of saved articles in the list. If there is 1 article saved, "progress" value should be 1.

        //Set the progress value
        progressBar.setProgress(progress);
        //TextView message to display the status of the progress bar
        pBarStatus.setText(progress + getString(R.string.slash) + max + getString(R.string.space) + getString(R.string.savedTo));


    }

    //Inflate and display the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    //Code to handle toolbar icon selections made by the user (when clicked)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        String snackMessage = null;
        Snackbar snackBar;
        TextView snackView = findViewById(R.id.snackView);

        switch (item.getItemId()) {
            //What to do when a toolbar item is selected
            case R.id.item1:
                message = getResources().getString(R.string.choice_home);
                Intent h = new Intent(FavouritesActivity.this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.item2:
                message = getResources().getString(R.string.choice_articles);
                Intent a = new Intent(FavouritesActivity.this, ArticlesActivity.class);
                startActivity(a);
                break;
            case R.id.item3:
                //If item3 is selected, there is no Toast to display and message remains null
                message = null;
                break;
            case R.id.item4:
                message = getResources().getString(R.string.choice_pref);
                Intent p = new Intent(FavouritesActivity.this, PreferencesActivity.class);
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
            snackBar = Snackbar.make(snackView, R.string.snackFavourites, Snackbar.LENGTH_LONG);
            snackBar.show();
        }
        return true;
    }
}

