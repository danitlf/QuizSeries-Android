package com.example.dani.quizseries;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dani.quizseries.DB.CriaBanco;
import com.example.dani.quizseries.DB.UserQueries;
import com.example.dani.quizseries.models.Pergunta;
import com.example.dani.quizseries.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class CadastroActivity extends AppCompatActivity {
    Intent intentToMain;
    EditText fieldNickName;
    TextView labelCadastro;
    RequestQueue queue;
    CriaBanco mBanco;
    SQLiteDatabase db;
    UserQueries userQ;
    String url ="https://quizseries.herokuapp.com/users/";
    JsonObjectRequest jsObjRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //cria instancia do banco
        mBanco = new CriaBanco(getApplicationContext());
        db = mBanco.getWritableDatabase();
        userQ = new UserQueries(db);

        fieldNickName = (EditText) findViewById(R.id.inputdNickname);
        labelCadastro = (TextView) findViewById(R.id.labelInserirNickname);
        intentToMain = new Intent(this, MainActivity.class);
        queue = Volley.newRequestQueue(this);

        Button btnCadastro = (Button) findViewById(R.id.btnEscolherNickname);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //verifica se o nickname tem espaço no meio
                    if(!fieldNickName.getText().toString().trim().contains(" ")){
                        insereUser(fieldNickName.getText().toString().trim());
                    }
                    else{
                        labelCadastro.setText("Por favor Retire os espaços");
                        labelCadastro.setTextColor(Color.parseColor("#930000"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public void insereUser(String name) throws JSONException {
        JSONObject nameJson = new JSONObject();
        nameJson.put("name", name);
        jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, nameJson , new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //caso o usuario consiga ser cadastrado
                            salvaUser(response.get("name").toString(), response.get("user_id").toString());
                            Toast.makeText(CadastroActivity.this, response.get("user_id").toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        //caso usuario já existe


                        labelCadastro.setText("Nickname Já Existe");
                        labelCadastro.setTextColor(Color.parseColor("#930000"));

                    }
                });
        queue.add(jsObjRequest);


    }

    public void salvaUser(String user_id, String name){
        User user = new User(name, user_id);
        userQ.insereUser(user);
        startActivity(intentToMain);
        finish();

    }
}
