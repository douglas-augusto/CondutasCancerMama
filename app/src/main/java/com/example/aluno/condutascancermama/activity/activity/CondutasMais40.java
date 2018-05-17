package com.example.aluno.condutascancermama.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aluno.condutascancermama.R;

public class CondutasMais40 extends AppCompatActivity {

    private Button botaoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condutas_mais40);

        botaoResultado = (Button)findViewById(R.id.botaoResultado2);

        botaoResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CondutasMais40.this, Resultados.class);
                startActivity(intent);
            }
        });
    }
}
