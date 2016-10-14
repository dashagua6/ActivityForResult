package bid.woou.activityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button bn;
    EditText city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn = (Button) findViewById(R.id.bn);
        city = (EditText) findViewById(R.id.city);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        SelectCityActivity.class);
                startActivityForResult(intent,0);
            }
        });

    }
    // 重写该方法，该方法以回调的方式来获取指定Activity返回的结果
    @Override
    public void onActivityResult(int requestCode
        , int resultCode, Intent intent) {
        if (requestCode==0 && resultCode==0) {
            Bundle data = intent.getExtras();
            String resultCity = data.getString("city");
            city.setText(resultCity);
        }
    }
}
