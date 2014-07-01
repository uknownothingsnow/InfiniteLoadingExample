package infiniteLoading.brucelee.me.inifiniteloadingexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


public class MyActivity extends Activity {
    private List<String> items = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        for (int i = 0; i < 20; i++) {
            items.add("items " + i);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, android.R.id.text1, items);
        ListView listView = ((ListView) findViewById(android.R.id.list));
        listView.setAdapter(adapter);

        listView.setOnScrollListener(new InfiniteScrollListener() {
            @Override
            public void loadMore() {
                Toast.makeText(MyActivity.this, "loadMore", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < 5; i++) {
                            items.add("items: " + items.size());
                        }
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MyActivity.this, "loaded", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
