package com.example.finalproject.RSS;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalproject.Article;
import com.example.finalproject.CustomAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RSSParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    InputStream is;
    ListView lv;


    //Declaring the Array we use to store article entries
    ArrayList<Article> articles = new ArrayList<>();

    public RSSParser(Context c, InputStream is, ListView lv) {
        this.c = c;
        this.is = is;
        this.lv = lv;
    }

    //doInBackground method to parseRSS with AsyncTask
    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseRSS();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        if (isParsed)
        {
            //BIND
            lv.setAdapter(new CustomAdapter(c, articles));
        }
        else
        {
            Toast.makeText(c, "Unable To Parse.", Toast.LENGTH_SHORT).show();
        }
    }


    //To parse XML data from source URL
    //This method will parse the RSS feed data and create text values for each resource (Title, Description, Date, Link)
    private Boolean parseRSS()
    {
        try
        {
            XmlPullParserFactory factory =  XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(is, null);
            int event = parser.getEventType();

            String tagValue = null;
            Boolean isSiteMeta = true;

            articles.clear();
            Article article = new Article();

            do {
                String tagName = parser.getName();

                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item"))
                        {
                            article = new Article();
                            isSiteMeta = false;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        tagValue = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (!isSiteMeta)
                        {
                            if (tagName.equalsIgnoreCase("title"))
                            {
                                article.setTitle(tagValue);


                            }else if (tagName.equalsIgnoreCase("description"))
                            {
                                String desc = tagValue;
                                article.setDescription(desc);

                            }else if (tagName.equalsIgnoreCase("pubDate"))
                            {
                                article.setDate(tagValue);

                            }else if (tagName.equalsIgnoreCase("link"))
                            {
                                article.setLink(tagValue);
                            }
                        }

                        if (tagName.equalsIgnoreCase("item"))
                        {
                            articles.add(article);
                            isSiteMeta = true;
                        }

                        break;
                }

                event = parser.next();

            } while (event != XmlPullParser.END_DOCUMENT);

            return true;

        } catch (XmlPullParserException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
