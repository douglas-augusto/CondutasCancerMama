package com.example.aluno.condutascancermama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CardView botaoCondutas;
    private CardView botaoFluxograma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoCondutas = (CardView) findViewById(R.id.condutasId);
        botaoFluxograma = (CardView) findViewById(R.id.botaoFluxograma);

        botaoCondutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Condutas.class);
                startActivity(intent);
            }
        });

        botaoFluxograma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Fluxogramas.class);
                startActivity(intent);
            }
        });
    }
}
