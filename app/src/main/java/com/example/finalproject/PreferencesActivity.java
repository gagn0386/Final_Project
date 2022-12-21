package com.example.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class PreferencesActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load activity to the screen
        setContentView(R.layout.activity_preferences);

        //Identify the TextView, EditText, and Button
        TextView textView = findViewById(R.id.welcome);
        EditText editText = findViewById(R.id.edit_name);
        save = findViewById(R.id.button_save);

        //On click listener for the Save button
        //This takes the input from EditText and sets its as 1 TextView on this activity and saves it as a SharedPreferences object for use elsewhere in the app
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                //If the user input is empty
                if (input.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.empty, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Hello, " + input, Toast.LENGTH_SHORT).show();
                    textView.setText(getString(R.string.welcome) + input);

                    //Save the values to shared preferences
                    SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    String s1 = sh.getString("name", "");
                    SharedPreferences.Editor myEdit = sh.edit();

                    //Get the data to be saved as SharedPreferences object
                    myEdit.putString("name", editText.getText().toString());
                    myEdit.apply();
                }
            }
        });

        //Building the Alert Dialogue message resource
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.help_title1);
        builder.setMessage(R.string.help_dialog4);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        //Linking the Alert Message with the help button
        ImageButton helpPreferences;
        helpPreferences = findViewById(R.id.help_preferences);
        helpPreferences.setOnClickListener(new View.OnClickListener() {
            //Display the Alert Dialogue when the ImageButton is clicked
            @Override
            public void onClick(View view) {
                AlertDialog ad = builder.create();
                ad.show();
            }
        });

        //Identify the toolbar
        Toolbar tBar = findViewById(R.id.toolbar);
        setSupportActionBar(tBar);
    }


    //onResume method to display the SharedPreferences data when the onResume is called
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        EditText nameEdit = findViewById(R.id.edit_name);
        TextView welcome = findViewById(R.id.welcome);

        String s1 = sh.getString("name", "");
        welcome.setText(getString(R.string.welcome) + s1);
        nameEdit.setText(s1);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("name", editText.getText().toString());
        myEdit.apply();
    }

    //To inflate the toolbar items to be displayed
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    //To handle the Toolbar Icon selections made by the user and display a confirmation message
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        String snackMessage = null;
        Snackbar snackBar;
        TextView snackView = findViewById(R.id.snackView);

        switch (item.getItemId()) {
            //Switch cases for each toolbar icon selection made
            case R.id.item1:
                message = getResources().getString(R.string.choice_home);
                Intent h = new Intent(PreferencesActivity.this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.item2:
                message = getResources().getString(R.string.choice_articles);
                Intent a = new Intent(PreferencesActivity.this, ArticlesActivity.class);
                startActivity(a);
                break;
            case R.id.item3:
                message = getResources().getString(R.string.choice_favourites);
                Intent f = new Intent(PreferencesActivity.this, FavouritesActivity.class);
                startActivity(f);
                break;
                //If item4 is selected, there is no Toast to display and message remains null
            case R.id.item4:
                message = null;
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
            snackBar = Snackbar.make(snackView, R.string.snackPreferences, Snackbar.LENGTH_LONG);
            snackBar.show();
        }
        return true;
    }
}
