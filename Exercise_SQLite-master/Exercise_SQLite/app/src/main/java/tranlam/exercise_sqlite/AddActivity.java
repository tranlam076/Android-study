package tranlam.exercise_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtNumber;
    EditText edtAddress;
    RadioButton rbMale, rbFemale;
    RadioGroup rbGender;
    String day, time, gender = "";
    Button btnCancel;
    Button btnAdd;
    Contact before_c, after_c;
    private MyDatabase db;
    private int ck = 0;
    private int idadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbMale.setChecked(true);
        rbGender = (RadioGroup) findViewById(R.id.rbGender);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        db = new MyDatabase(this);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");
        day = sd.format(cal.getTime());
        SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
        time = st.format(cal.getTime());

        rbGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup rg, int i) {
                if (rbMale.isChecked()) gender = "Male";
                if (rbFemale.isChecked()) gender = "Female";
            }
        });

        ck = 0;
        Intent callerIntent = this.getIntent();
        try {
            final Bundle packageFromCaller = callerIntent.getBundleExtra("DetailPackage");
            if (packageFromCaller.getInt("key_c") == 123) ck = 123;
            idadd = packageFromCaller.getInt("key_iddetail");
            before_c = db.getContact(idadd);
            if (ck == 123) {
                toolbar.setTitle("Edit");
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                edtName.setText(before_c.getName());
                edtNumber.setText(before_c.getNumber());
                edtAddress.setText(before_c.getAddress());
                if (before_c.getGender() == "Male") {
                    gender = "Male";
                    rbMale.setChecked(true);
                    rbFemale.setChecked(false);
                } else {
                    gender = "Female";
                    rbMale.setChecked(false);
                    rbFemale.setChecked(true);
                }
            }
        } catch (Exception e) {
        }
        Log.d("checkkk", "onCreate: " + ck + "gender: " + gender);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ck == 123) {
                    after_c = new Contact(before_c.getId(), edtName.getText().toString(), edtNumber.getText().toString(),
                            edtAddress.getText().toString(), gender, day, time);
                    db.updateContact(after_c);
                } else {
                    after_c = new Contact(edtName.getText().toString(), edtNumber.getText().toString(),
                            edtAddress.getText().toString(), gender, day, time);
                    db.addContact(after_c);
                }
                Intent myIntent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK)
            super.onBackPressed();
        return super.onKeyDown(keycode, event);
    }
}
