package tranlam.exercise_sqlite;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView Name, Number, Address, Gender, Day, Time;
    ImageView imgCall;
    private MyDatabase db;
    Contact contact;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_detail);
        toolbar.setTitle("Detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Name = (TextView) findViewById(R.id.tv_name_detail);
        Number = (TextView) findViewById(R.id.tv_number_detail);
        Address = (TextView) findViewById(R.id.tv_address_detail);
        Gender = (TextView) findViewById(R.id.tv_gender_detail);
        Day = (TextView) findViewById(R.id.tv_day_detail);
        Time = (TextView) findViewById(R.id.tv_time_detail);

        Intent callerIntent = this.getIntent();
        final Bundle packageFromCaller = callerIntent.getBundleExtra("MainPackage");
        id = packageFromCaller.getInt("key_id");

        db = new MyDatabase(this);
        contact = db.getContact(id);
        Name.setText(contact.getName());
        Number.setText(contact.getNumber());
        Address.setText(contact.getAddress());
        Gender.setText(contact.getGender());
        Day.setText(contact.getDay());
        Time.setText(contact.getTime());


        imgCall = (ImageView) findViewById(R.id.img_call);
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.fromParts("tel",contact.getNumber(),null));
                if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    ActivityCompat.requestPermissions(DetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},10);
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                try{
                    startActivity(callIntent);
                } catch (Exception e){
                    Toast.makeText(DetailActivity.this, "khong goi duoc" , Toast.LENGTH_SHORT)
                            .show();
                }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_edit:
                Intent intent = new Intent(DetailActivity.this, AddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key_iddetail",id);
                bundle.putInt("key_c",123);
                intent.putExtra("DetailPackage", bundle);
                startActivity(intent);
                break;
            case R.id.menu_delete_one:
                db.deleteOneContact(contact);
                Intent myIntent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(myIntent);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK)
            super.onBackPressed();
        return super.onKeyDown(keycode, event);
    }
}
