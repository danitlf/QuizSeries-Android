package com.example.dani.quizseries;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dani.quizseries.web.SeriesTasks;
import com.example.dani.quizseries.web.WebClient;
import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.list_series);
        new SeriesTasks(this, this.mRecyclerView).execute();






        // specify an adapter (see also next example)



    }
}
