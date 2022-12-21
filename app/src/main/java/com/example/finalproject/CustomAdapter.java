package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;
import java.util.ArrayList;

//This is the adapter used to populate our ListView for ArticlesActivity
public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Article> articles;

    public CustomAdapter(Context c, ArrayList<Article> articles) {
        this.c = c;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //If the view is null
        if (convertView == null)
        {   //populate the ListView and inflate with the list_items.xml as layout for each list item
            convertView = LayoutInflater.from(c).inflate(R.layout.list_items, parent, false);
        }

        //Identify the xml views we want and bind them
        TextView titleTxt = (TextView) convertView.findViewById(R.id.title_view);
        TextView descTxt = (TextView) convertView.findViewById(R.id.descTxt);
        TextView dateTxt = (TextView) convertView.findViewById(R.id.dateTxt);
        TextView linkTxt = (TextView) convertView.findViewById(R.id.linkTxt);


        Article article = (Article) this.getItem(position);

        //Creating String objects and assigning the values we pulled from our XML Parser
        final String title = article.getTitle();
        String desc = article.getDescription();
        String date = article.getDate();
        String link = article.getLink();

        //Setting the TextViews to the data we pulled
        titleTxt.setText(title);
        descTxt.setText(desc);
        dateTxt.setText(date);
        linkTxt.setText(link);

        //return our view as a single list item
        return convertView;
    }
}
