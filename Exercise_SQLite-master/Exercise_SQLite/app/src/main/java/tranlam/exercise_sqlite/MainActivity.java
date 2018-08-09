package tranlam.exercise_sqlite;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int KEY_DETAIL = 419;
    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;
    private MyDatabase db;
    private List<Contact> contacts;
    private int posClick = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_main);
        setSupportActionBar(toolbar);

        db = new MyDatabase(this);
        contacts = new ArrayList<>();
        getData();
        handle();
    }

    private void getData() {
        contacts.clear();
        contacts = db.getAllContacts();
        db.close();
    }

    private void handle() {
        mRecyclerView = (RecyclerView) findViewById(R.id.lv_main);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ContactAdapter(contacts, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key_id",contacts.get(position).getId());
                intent.putExtra("MainPackage", bundle);
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(this, AddActivity.class);
                this.startActivity(intent);
                break;
            case R.id.menu_delete_all:
                db = new MyDatabase(this);
                db.deleteAllContact();
                getData();
                handle();
                Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }
        return true;
    }


}
