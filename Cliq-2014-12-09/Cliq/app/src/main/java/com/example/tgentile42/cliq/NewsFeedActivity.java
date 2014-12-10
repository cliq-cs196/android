package com.example.tgentile42.cliq;



import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
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
import java.util.ArrayList;
import java.util.HashMap;


    public class NewsFeedActivity extends ListActivity {
    private Context context;
    private static String url ="https://cs196-cliq.herokuapp.com:443/api/events";

    private static final String name = "name";
    private static final String lat = "0.0";
    private static final String lng = "0.0";
    private static final String date = "date";
    private static final String tags = "tag1";
    private static final String maxSize = "0";
    private static final String owner = "owner";

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
                        new String[] {name,lat,lng, date,tags,maxSize,owner},
                        new int[]{R.id.name, R.id.lat,R.id.lng,R.id.date,R.id.tags,R.id.maxSize,R.id.owner}
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
                             String dATE = o.getString(date);



                             HashMap<String,String> map = new HashMap<>();

                             map.put(name,nAME);
                             map.put(date, dATE);

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
