package com.example.aluno.condutascancermama.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.aluno.condutascancermama.R;

public class Fluxogramas extends AppCompatActivity {

    private CardView botaoResultados;
    private CardView botaoCondutas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluxogramas);

        this.setTitle("Fluxogramas");

        botaoCondutas = (CardView)findViewById(R.id.botaoFluxoCondutas);
        botaoResultados = (CardView)findViewById(R.id.botaoFluxoResultados);

        botaoResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fluxogramas.this, FluxogramaResultados.class);
                startActivity(intent);
            }
        });

        botaoCondutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fluxogramas.this, FluxogramaCondutas.class);
                startActivity(intent);
            }
        });
    }
}
