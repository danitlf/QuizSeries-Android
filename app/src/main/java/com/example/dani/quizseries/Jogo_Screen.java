package com.example.dani.quizseries;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dani.quizseries.models.Pergunta;

import java.util.List;

public class Jogo_Screen extends AppCompatActivity {
    List<Pergunta> perguntas;
    int numPergunta = 0;
    Pergunta perguntaAtual;
    Button opc1;
    Button opc2;
    Button opc3;
    Button opc4;
    TextView box_pergunta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo__screen);

        Intent intent = getIntent();
        perguntas = (List<Pergunta>) intent.getExtras().getSerializable("perguntas");
        opc1 = (Button) findViewById(R.id.opc1);
        opc2 = (Button) findViewById(R.id.opc2);
        opc3 = (Button) findViewById(R.id.opc3);
        opc4 = (Button) findViewById(R.id.opc4);
        box_pergunta = (TextView) findViewById(R.id.box_pergunta_text);

        try {
            perguntaAtual =  perguntas.get(numPergunta);

            trocaValores(perguntaAtual);
        }catch (Exception e){

        }

        opc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcEscolhida("a", opc1);
            }
        });

        opc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcEscolhida("b", opc2);
            }
        });

        opc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcEscolhida("c", opc3);
            }
        });

        opc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcEscolhida("d", opc4);
            }
        });


    }

    public void trocaValores(Pergunta p){
        opc1.setText(p.getOpt1());
        opc2.setText(p.getOpt2());
        opc3.setText(p.getOpt3());
        opc4.setText(p.getOpt4());
        box_pergunta.setText(p.getLabel());

    }

    public void opcEscolhida(String opc, Button btn) {

        if(perguntaAtual.getCorreta().equals(opc)){
            btn.setBackgroundResource(R.drawable.pergunta_certa);
            btn.setTextColor(Color.parseColor("#ffffff"));

        }
        else{
            btn.setBackgroundResource(R.drawable.pergunta_errada);
            btn.setTextColor(Color.parseColor("#ffffff"));
            pintaOpcCerta(perguntaAtual.getCorreta());
        }

        numPergunta+=1;
        perguntaAtual = perguntas.get(numPergunta);

        proximaPergunta();


    }

    private void proximaPergunta() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                opc1.setBackgroundResource(R.drawable.opc_pergunta);
                opc2.setBackgroundResource(R.drawable.opc_pergunta);
                opc3.setBackgroundResource(R.drawable.opc_pergunta);
                opc4.setBackgroundResource(R.drawable.opc_pergunta);

                opc1.setTextColor(Color.parseColor("#424242"));
                opc2.setTextColor(Color.parseColor("#424242"));
                opc3.setTextColor(Color.parseColor("#424242"));
                opc4.setTextColor(Color.parseColor("#424242"));

                trocaValores(perguntaAtual);
            }
        }, 1500);

    }

    private void pintaOpcCerta(String opc) {
        if(opc.equals("a")){
            opc1.setBackgroundResource(R.drawable.pergunta_certa);
            opc1.setTextColor(Color.parseColor("#ffffff"));
        }
        else if(opc.equals("b")){
            opc2.setBackgroundResource(R.drawable.pergunta_certa);
            opc2.setTextColor(Color.parseColor("#ffffff"));
        }
        else if(opc.equals("c")){
            opc3.setBackgroundResource(R.drawable.pergunta_certa);
            opc3.setTextColor(Color.parseColor("#ffffff"));
        }
        else if(opc.equals("d")){
            opc4.setBackgroundResource(R.drawable.pergunta_certa);
            opc4.setTextColor(Color.parseColor("#ffffff"));
        }
    }


}
