package knightsoftheblood.optionalbai10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Cong = (Button) findViewById(R.id.btn_Cong);
        Cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText etA = (EditText) findViewById(R.id.etA);
                int a = Integer.parseInt(etA.getText() + "");
                EditText etB = (EditText) findViewById(R.id.etB);
                int b = Integer.parseInt(etB.getText() + "");
                TextView tv_Result_Anony = (TextView) findViewById(R.id.tvResult);
                tv_Result_Anony.setText(a+"+" + b + " = "+ (a + b));
            }
        });
        Button Tru = (Button) findViewById(R.id.btn_Tru);
        Tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText etA = (EditText) findViewById(R.id.etA);
                int a = Integer.parseInt(etA.getText() + "");
                EditText etB = (EditText) findViewById(R.id.etB);
                int b = Integer.parseInt(etB.getText() + "");
                TextView tv_Result_Anony = (TextView) findViewById(R.id.tvResult);
                tv_Result_Anony.setText(a+"-" + b + " = "+ (a - b));
            }
        });
        Button Nhan = (Button) findViewById(R.id.btn_Nhan);
        Nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText etA = (EditText) findViewById(R.id.etA);
                int a = Integer.parseInt(etA.getText() + "");
                EditText etB = (EditText) findViewById(R.id.etB);
                int b = Integer.parseInt(etB.getText() + "");
                TextView tv_Result_Anony = (TextView) findViewById(R.id.tvResult);
                tv_Result_Anony.setText(a+"×" + b + " = "+ (a * b));
            }
        });
        Button Chia = (Button) findViewById(R.id.btn_Chia);
        Chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText etA = (EditText) findViewById(R.id.etA);
                int a = Integer.parseInt(etA.getText() + "");
                EditText etB = (EditText) findViewById(R.id.etB);
                int b = Integer.parseInt(etB.getText() + "");
                TextView tv_Result_Anony = (TextView) findViewById(R.id.tvResult);
                tv_Result_Anony.setText(a+"/" + b + " = "+ (a / b));
            }
        });

    }
}