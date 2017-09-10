package com.example.dani.quizseries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CadastroActivity extends AppCompatActivity {
    Intent intentToMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        intentToMain = new Intent(this, MainActivity.class);

        Button btnCadastro = (Button) findViewById(R.id.btnEscolherNickname);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentToMain);
                finish();
            }
        });
    }
}
