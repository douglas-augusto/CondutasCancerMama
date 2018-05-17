package com.example.aluno.condutascancermama.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.aluno.condutascancermama.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private CardView botaoCondutas;
    private CardView botaoFluxograma;
    private CardView botaoAcompanhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoCondutas = (CardView) findViewById(R.id.condutasId);
        botaoFluxograma = (CardView) findViewById(R.id.botaoFluxograma);
        botaoAcompanhar = (CardView) findViewById(R.id.botaoAcompanhamento);

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

        botaoAcompanhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
