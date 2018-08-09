package knightsoftheblood.exercise_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    TextView txtketqua;
    Button btn_Back;
    private static final int REQ_CODE = 123;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_result);
        this.btn_Back = (Button) this.findViewById(R.id.btn_Back);
        this.txtketqua = (TextView) this.findViewById(R.id.tv_kq);
        Intent callerIntent = this.getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        final int a = packageFromCaller.getInt("soa");
        final int b = packageFromCaller.getInt("sob");
        giaipt(a, b);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String data = "a=" + a + ", b= " + b;
                Intent intent = new Intent();
                intent.putExtra("message", data);
                setResult(REQ_CODE, intent);
                finish();
            }
        });
    }

    public void giaipt(int a, int b) {
        String kq = "";
        if (a == 0 && b == 0) {
            kq = "Vô số nghiệm";
        } else if (a == 0 && b != 0) {
            kq = "Vô nghiệm";
        } else {
            DecimalFormat df = new DecimalFormat("0.##");
            kq = df.format((float) (-b) * 1.0D / (float) a);
        }
        this.txtketqua.setText(kq);
    }
}
