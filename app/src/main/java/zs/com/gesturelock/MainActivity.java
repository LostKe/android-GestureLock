package zs.com.gesturelock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button setLock;
    Button verifyLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLock = (Button) findViewById(R.id.setLock);
        setLock.setOnClickListener(this);
        verifyLock = (Button) findViewById(R.id.verifyLock);
        verifyLock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()) {
            case R.id.setLock:
                intent.setClass(this,GestureEditActivity.class);
                break;
            case R.id.verifyLock:
                intent.setClass(this,GestureVerifyActivity.class);

                break;
        }
        startActivity(intent);

    }
}
