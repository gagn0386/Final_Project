package com.example.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class SingleItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item);

        //This gets the data sent from the previous activity via Intents
        Intent dataSent = getIntent();
        String titleSent = dataSent.getStringExtra("title");
        String descSent = dataSent.getStringExtra("description");
        String dateSent = dataSent.getStringExtra("date");
        String linkSent = dataSent.getStringExtra("link");



        //Attach the data from the item selected to this activity and display it
        TextView title = findViewById(R.id.title);
        title.setText(titleSent);

        //Retrieve description data
        TextView description = findViewById(R.id.description);
        description.setText(descSent);

        //Retrieve date data
        TextView pubDate = findViewById(R.id.pubDate);
        pubDate.setText(dateSent);

        //Retrieve link data
        TextView link = findViewById(R.id.link);
        link.setText(linkSent);

        //Button to go to the website link
        Button visit = (Button) findViewById(R.id.buttonLink);
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = linkSent;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(linkSent));
                startActivity(i);
            }
        });

        //Button to go back to the articles page
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Button to save to favourites

        //To Identify the toolbar we want to use
        Toolbar tBar = findViewById(R.id.toolbar);
        setSupportActionBar(tBar);

        //To create the Alert Dialogue and its values
        AlertDialog.Builder help = new AlertDialog.Builder(this);
        help.setTitle(R.string.help_title1);
        help.setMessage(R.string.help_dialog5);
        help.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //To link the alert dialog to the help button below and display its message to the user
        ImageButton helpItem;
        helpItem = findViewById(R.id.help_item);
        helpItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog ad = help.create();
                ad.show();
            }
        });

    }

    //This is required to display the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    //To handle toolbar selections
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;

        switch (item.getItemId()) {
            //what to do when the user selects an Icon:
            case R.id.item1:
                message = getResources().getString(R.string.choice_home);
                Intent h = new Intent(SingleItem.this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.item2:
                message = getResources().getString(R.string.choice_articles);
                Intent a = new Intent(SingleItem.this, ArticlesActivity.class);
                startActivity(a);
                break;
            case R.id.item3:
                message = getResources().getString(R.string.choice_favourites);
                Intent f = new Intent(SingleItem.this, FavouritesActivity.class);
                startActivity(f);
                break;
            case R.id.item4:
                message = getResources().getString(R.string.choice_pref);
                Intent p = new Intent(SingleItem.this, PreferencesActivity.class);
                startActivity(p);
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return true;
    }
}
