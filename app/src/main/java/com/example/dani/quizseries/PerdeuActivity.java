package com.example.dani.quizseries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PerdeuActivity extends AppCompatActivity {
    TextView score ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdeu);
        score = (TextView) findViewById(R.id.scorePartida);
        Intent intent = getIntent();
        score.setText((CharSequence) Integer.toString((Integer) intent.getExtras().get("score")));
    }
}
