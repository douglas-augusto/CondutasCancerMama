package com.example.aluno.condutascancermama.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.aluno.condutascancermama.R;

public class MedicamentoMain extends AppCompatActivity {

    private CardView botaoNovoMedicamento;
    private CardView botaoVerMedicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento_main);

        botaoNovoMedicamento = findViewById(R.id.botaoNovoMedicamento);
        botaoVerMedicamentos = findViewById(R.id.botaoMeusMedicamentos);

        botaoNovoMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicamentoMain.this, NovoMedicamento.class );
                startActivity(intent);
            }
        });

        botaoVerMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicamentoMain.this, MeusMedicamentos.class );
                startActivity(intent);
            }
        });
    }
}
