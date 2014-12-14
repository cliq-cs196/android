package com.example.tgentile42.cliq;



import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.os.AsyncTask;
import android.widget.SimpleAdapter;
import com.example.tgentile42.cliq.JSONParser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


    public class NewsFeedActivity extends ListActivity {
    private Context context;
    private static String url ="https://cs196-cliq.herokuapp.com:443/api/events";

    private static final String name = "name";
    private static final String lat = "lat";
    private static final String lng = "lng";
    private static final String daTE = "dateTime" ;
    private static final String desc = "description";
    private static final String tags = "tag1";
    private static final String maxSize = "maxSize";
    private static final String owner = "owner";
    private static final String location = "location";
    ArrayList<HashMap<String,String>> jsonlist = new ArrayList<>();
    ListView listView;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
            new ProgressTask(NewsFeedActivity.this).execute();


        }
        private class ProgressTask extends AsyncTask<String, Void, Boolean> {

            private ProgressDialog dialog;
            private ListActivity activity;
            public ProgressTask(ListActivity activity){
                this.activity = activity;
                context = activity;
                dialog = new ProgressDialog(context);
            }

            private Context context;

            protected void onPreExecute(){
                this.dialog.setMessage("Progress Start");
                this.dialog.show();
            }
            @Override
            protected void onPostExecute(final Boolean success){
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
                ListAdapter adapter;
                adapter = new SimpleAdapter(context, jsonlist,R.layout.list_item,
                        new String[] {name,lat,lng,desc,daTE,maxSize},
                        new int[]{R.id.name, R.id.lat,R.id.lng,R.id.date,R.id.description,R.id.maxSize}
                        );

                setListAdapter(adapter);
                listView = getListView();
            }

            protected Boolean doInBackground(final String... args){
                JSONParser jParser = new JSONParser();
                JSONArray json = jParser.getJSONFromUrl(url);

                for(int i =0; i< json.length(); i++){
                         try{
                             JSONObject o = json.getJSONObject(i);
                             String nAME = o.getString(name);
                             String desC = o.getString(desc);
                             JSONObject locations = o.getJSONObject(location);
                             Long dateDouble = o.getLong(daTE);
                             Date date1 = new Date(dateDouble * 1000);
                             String max = "Max Size: " + Integer.toString(o.getInt(maxSize));
                             String dateString ="Date: " +  date1.toString();
                             String longitude = locations.getString(lng);
                             String latitude = locations.getString(lat);
                             HashMap<String,String> map = new HashMap<>();
                             map.put(name,nAME);
                             map.put(daTE, dateString);
                             map.put(desc,desC);
                             map.put(maxSize,max);
                             map.put(lng,longitude);
                             map.put(lat,latitude);
                             jsonlist.add(map);
                         }
                         catch (JSONException e){
                             e.printStackTrace();
                         }
                }
                return null;
        }




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_feed, menu);
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
