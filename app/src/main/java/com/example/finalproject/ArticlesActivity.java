package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.finalproject.RSS.Downloader;
import com.example.finalproject.RSS.RSSParser;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.xmlpull.v1.XmlPullParser;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ArticlesActivity extends AppCompatActivity {

    ArrayList<Article> articles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Display the layout for this activity
        setContentView(R.layout.activity_articles);

        //Identify the toolbar
        Toolbar tBar = findViewById(R.id.toolbar);
        setSupportActionBar(tBar);


        //Identify the URL address we want to access
        final String urlAddress="http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml";
        //Identify the ListView
        final ListView newsList = (ListView) findViewById(R.id.news_list);

        //Create adapter resource and bind to our ListView
        CustomAdapter adapter = new CustomAdapter(this, articles);
        newsList.setAdapter(adapter);

        //This will execute and parse our XML data from the URL
        new Downloader(ArticlesActivity.this, urlAddress, newsList).execute();

        //OnItemClickListener to handle what happens when a list item is selected
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> newsList, View convertView, int position, long id)
            {
                //Intent data is used to take our Data (Title,Description,Date,Link) and pass it to the next Activity (SingleActivity)

                Intent intent = new Intent(getApplicationContext(), SingleItem.class);
                String title = ((TextView) convertView.findViewById(R.id.title_view)).getText().toString().trim();
                String desc = ((TextView) convertView.findViewById(R.id.descTxt)).getText().toString();
                String date = ((TextView) convertView.findViewById(R.id.dateTxt)).getText().toString().trim();
                String link = ((TextView) convertView.findViewById(R.id.linkTxt)).getText().toString().trim();
                intent.putExtra("title", title);
                intent.putExtra("description", desc);
                intent.putExtra("date", date);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });

        //To create the Alert Dialogue and its values
        AlertDialog.Builder help = new AlertDialog.Builder(this);
        help.setTitle(R.string.help_title1);
        help.setMessage(R.string.help_dialog2);
        help.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Do nothing and close the Alert Dialogue
            }
        });

        //Identify the Help ImageButton resource
        ImageButton helpArticles;
        helpArticles = findViewById(R.id.help_articles);

        //Display Alert Dialogue when user clicks the ImageButton
        helpArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog ad = help.create();
                ad.show();
            }
        });
    }


    //To display the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    //This handles the actions to do when the user selects an icon from the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        String snackMessage = null;
        Snackbar snackBar;
        TextView snackView = findViewById(R.id.snackView);

        switch (item.getItemId()) {
            //what to do when the menu item is selected:
            case R.id.item1:
                message = getResources().getString(R.string.choice_home);
                Intent h = new Intent(ArticlesActivity.this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.item2:
                //If item2 is selected, there is no Toast to display and message remains null
                message = null;
                break;
            case R.id.item3:
                message = getResources().getString(R.string.choice_favourites);
                Intent f = new Intent(ArticlesActivity.this, FavouritesActivity.class);
                startActivity(f);
                break;
            case R.id.item4:
                message = getResources().getString(R.string.choice_pref);
                Intent p = new Intent(ArticlesActivity.this, PreferencesActivity.class);
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
            snackBar = Snackbar.make(snackView, R.string.snackArticle, Snackbar.LENGTH_LONG);
            snackBar.show();
        }

        return true;
    }
}
