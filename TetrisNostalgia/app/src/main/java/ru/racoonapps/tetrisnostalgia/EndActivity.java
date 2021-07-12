package ru.racoonapps.tetrisnostalgia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


public class EndActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button StartNgID;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        StartNgID = (Button)findViewById(R.id.StartNgID);

        StartNgID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

    }

}
