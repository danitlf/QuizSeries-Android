package com.example.dani.quizseries.web;

/**
 * Created by dani on 20/08/17.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dani.quizseries.ClickListener;
import com.example.dani.quizseries.MyAdapter;
import com.example.dani.quizseries.RecyclerTouchListener;

import com.example.dani.quizseries.models.Serie;
import com.example.dani.quizseries.serieScreen;

import java.util.ArrayList;
import java.util.List;



public class SeriesTasks extends AsyncTask<Void, Void, String> {
    private Context context;
    private ProgressDialog dialog;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public List<String> myDataset = new ArrayList();
    private RecyclerView rootView;
    List<Serie> resposta;

    public SeriesTasks(Context context,  RecyclerView rootView) {
        this.context = context;
        this.rootView = rootView;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", " Series...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {

        WebClient client = new WebClient();
        resposta = client.get();
        String series = "";
        for(int i = 0; i < resposta.size(); i++){
            series += resposta.get(i).getName()+",";
        }
        return series;
    }

    @Override
    protected void onPostExecute(String response) {
        dialog.dismiss();




        String[] series = response.split(",");
        for(int i = 0; i < series.length; i++){
            myDataset.add(series[i]);
        }


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rootView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        rootView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(resposta);
        rootView.setAdapter(mAdapter);

        rootView.addOnItemTouchListener(new RecyclerTouchListener(context, rootView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Toast.makeText(context, myDataset.get(position) , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, serieScreen.class);
                intent.putExtra("serie", resposta.get(position));
                context.startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
    }
}

