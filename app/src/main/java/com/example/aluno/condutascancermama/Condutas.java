package com.example.aluno.condutascancermama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Condutas extends AppCompatActivity {

    private CardView botaoMaisQuarenta;
    private CardView botaoMenosQuarenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condutas);

        this.setTitle("Selecione a idade da paciente");

        botaoMaisQuarenta = (CardView)findViewById(R.id.botaoMais40);
        botaoMenosQuarenta = (CardView)findViewById(R.id.botaoMenos40);

        botaoMaisQuarenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Condutas.this, CondutasMais40.class);
                startActivity(intent);
            }
        });

        botaoMenosQuarenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Condutas.this, CondutasMenos40.class);
                startActivity(intent);
            }
        });
    }
}
