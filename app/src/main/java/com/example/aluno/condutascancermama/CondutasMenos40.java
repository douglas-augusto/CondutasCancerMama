package com.example.aluno.condutascancermama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CondutasMenos40 extends AppCompatActivity {

    private Button botaoResultado;
    private EditText campoIdade;
    private Button botaoCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condutas_menos40);

        botaoResultado = (Button)findViewById(R.id.botaoResultado);
        campoIdade = (EditText)findViewById(R.id.campoIdade);
        botaoCalcular = (Button)findViewById(R.id.botaoCalcular);

        botaoResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CondutasMenos40.this, Resultados.class);
                startActivity(intent);
            }
        });

            botaoCalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   if(campoIdade.getText().toString().isEmpty()) {
                       Toast toast = Toast.makeText(CondutasMenos40.this, "Insira um valor para realizar o cálculo", Toast.LENGTH_LONG);
                       toast.show();
                   }else {
                       String valor = campoIdade.getText().toString();
                       int idade = Integer.parseInt(valor);
                       int calculo = idade - 14;

                       Toast toast = Toast.makeText(CondutasMenos40.this, "Paciente deve começar a fazer exames anuais a partir dos "+calculo+" anos.", Toast.LENGTH_LONG);
                       toast.show();
                   }

                }
            });

        }

    }
