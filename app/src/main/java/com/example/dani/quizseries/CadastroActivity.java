package com.example.dani.quizseries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dani.quizseries.models.Pergunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class CadastroActivity extends AppCompatActivity {
    Intent intentToMain;
    EditText fieldNickName;
    RequestQueue queue;
    String url ="https://quizseries.herokuapp.com/users/";
    JsonObjectRequest jsObjRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        fieldNickName = (EditText) findViewById(R.id.inputdNickname);

        intentToMain = new Intent(this, MainActivity.class);
        queue = Volley.newRequestQueue(this);

        Button btnCadastro = (Button) findViewById(R.id.btnEscolherNickname);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    insereUser(fieldNickName.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                startActivity(intentToMain);
//                finish();
            }
        });
    }

    public Integer insereUser(String name) throws JSONException {
        JSONObject nameJson = new JSONObject();
        nameJson.put("name", name);
        jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, nameJson , new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //caso o usuario consiga ser cadastrado
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
                        Toast.makeText(CadastroActivity.this, error.toString(),
                                Toast.LENGTH_LONG).show();

                    }
                });
        queue.add(jsObjRequest);
        return 1;

    }
}
