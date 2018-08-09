package knightsoftheblood.exercise_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ket qua:";
    private static final int REQ_CODE = 123;
    EditText txta;
    EditText txtb;
    Button btnketqua;

    public MainActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txta = (EditText) this.findViewById(R.id.edt_soA);
        txtb = (EditText) this.findViewById(R.id.edt_soB);
        btnketqua = (Button) this.findViewById(R.id.btn_kq);
        btnketqua.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle bundle = new Bundle();
                try {
                    int a = Integer.parseInt(txta.getText().toString());
                    int b = Integer.parseInt(txtb.getText().toString());
                    bundle.putInt("soa", a);
                    bundle.putInt("sob", b);
                    myIntent.putExtra("MyPackage", bundle);
                    startActivityForResult(myIntent, REQ_CODE);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhâp số nguyên!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQ_CODE) {
            String data = intent.getStringExtra("message");
            Toast.makeText(this, "Wellcome back to MainActivity ! Your last edit text: " + data, Toast.LENGTH_SHORT)
                    .show();
            txta.setText("0");
            txtb.setText("0");
        }
    }
}
