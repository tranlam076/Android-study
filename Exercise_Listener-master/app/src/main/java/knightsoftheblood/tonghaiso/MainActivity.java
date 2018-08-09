package knightsoftheblood.tonghaiso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etA_A, etB_A;
    TextView tv_Result_Activi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anonymous listener
        Button Result_Anony = (Button) findViewById(R.id.btn_Result_anony);
        Result_Anony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText etA = (EditText) findViewById(R.id.etA);
                int a = Integer.parseInt(etA.getText() + "");
                EditText etB = (EditText) findViewById(R.id.etB);
                int b = Integer.parseInt(etB.getText() + "");
                TextView tv_Result_Anony = (TextView) findViewById(R.id.tvResult);
                tv_Result_Anony.setText((a + b) + "");
            }
        });
        //activity is listener
        Button btn_Result_Activi = (Button) findViewById(R.id.btn_Result_Activi);
        btn_Result_Activi.setOnClickListener(this);
        etA_A = (EditText) findViewById(R.id.etA);
        etB_A = (EditText) findViewById(R.id.etB);
        tv_Result_Activi= (TextView) findViewById(R.id.tvResult);
    }
    @Override
    public void onClick(View arg0) {
        int a = Integer.parseInt(etA_A.getText() + "");
        int b = Integer.parseInt(etB_A.getText() + "");
        tv_Result_Activi.setText((a + b) + "");
    }
    //XML onclick
    public void btn_Result_xml(View view) {
        EditText etA_An = (EditText) findViewById(R.id.etA);
        int a = Integer.parseInt(etA_An.getText() + "");
        EditText etb_Bn = (EditText) findViewById(R.id.etB);
        int b = Integer.parseInt(etb_Bn.getText() + "");
        TextView Result_xml = (TextView) findViewById(R.id.tvResult);
        Result_xml.setText((a + b) + "");
    }
}