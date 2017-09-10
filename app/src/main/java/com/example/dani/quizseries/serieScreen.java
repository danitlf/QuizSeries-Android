package com.example.dani.quizseries;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dani.quizseries.models.Pergunta;
import com.example.dani.quizseries.models.Serie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class serieScreen extends AppCompatActivity {
    Serie serie;TextView serieName;
    RequestQueue queue;
    String url ="https://quizseries.herokuapp.com/series/";
    JsonObjectRequest jsObjRequest;
    List<Pergunta> perguntas = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_screen);

        Intent intent = getIntent();
        queue = Volley.newRequestQueue(this);
        serie = (Serie) intent.getExtras().getSerializable("serie");
        url+= serie.getId() + "/questions";

        TextView serieName = (TextView) findViewById(R.id.SerieCardText);
        CardView cardSerie = (CardView) findViewById(R.id.nameCardSerie);

        cardSerie.setCardBackgroundColor(Color.parseColor(serie.getCor()));
        serieName.setText(serie.getName());

        jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray questions = response.getJSONArray("questions");
                            for(int i = 0; i< questions.length(); i++){
                                JSONObject question = questions.getJSONObject(i);

                                //adicionando perguntas na lista de perguntas

                                perguntas.add(new Pergunta(question.getString("label"), question.getString("a"),
                                        question.getString("b"),
                                        question.getString("c"),
                                        question.getString("d"),
                                        question.getString("right")));
                            }

                            Intent intent = new Intent(getApplicationContext(), Jogo_Screen.class);
                            intent.putExtra("perguntas", (Serializable) perguntas);
                            getApplicationContext().startActivity(intent);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), error.toString(),
                        Toast.LENGTH_LONG).show();

                    }
                });


    }

    public void jogar(View v){
        queue.add(jsObjRequest);

    }

}
