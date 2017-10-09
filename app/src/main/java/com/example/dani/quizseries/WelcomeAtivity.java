package com.example.dani.quizseries;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dani.quizseries.DB.CriaBanco;
import com.example.dani.quizseries.DB.UserQueries;

public class WelcomeAtivity extends AppCompatActivity {
    Intent nicknamIntent;
    Intent intentMain;
    CriaBanco mBanco;
    SQLiteDatabase db;
    UserQueries userQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_ativity);

        mBanco = new CriaBanco(getApplicationContext());
        db = mBanco.getWritableDatabase();
        userQ = new UserQueries(db);
        nicknamIntent = new Intent(this, CadastroActivity.class);
        intentMain = new Intent(this, MainActivity.class);

        //caso usuario ja exista vai direto para a main
        if(userQ.verificaUsuario()){
            startActivity(intentMain);
            finish();
        }

        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(nicknamIntent);
                finish();
            }
        });
    }
}
