package com.example.aluno.condutascancermama.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.adapter.PacientesAdapter;
import com.example.aluno.condutascancermama.activity.helper.Base64Custom;
import com.example.aluno.condutascancermama.activity.model.Exame;

public class NovoExame extends AppCompatActivity {

    private EditText nome;
    private EditText data;
    private EditText medico;
    private EditText local;
    private EditText resultado;
    private EditText observacao;
    private Button botaoCadastrarExame;
    private Exame exame;
    private String chave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_exame);

        setTitle("Novo exame");

        nome = findViewById(R.id.campoNomeExame);
        data = findViewById(R.id.campoDataExame);
        medico = findViewById(R.id.campoMedicoResponsavel);
        local = findViewById(R.id.campoLocalExame);
        resultado = findViewById(R.id.campoResultadoExame);
        observacao = findViewById(R.id.campoObservacoes);
        botaoCadastrarExame = findViewById(R.id.botaoCadastrarExame);

        botaoCadastrarExame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nome.getText().toString().isEmpty() || data.getText().toString().isEmpty() || medico.getText().toString().isEmpty() || local.getText().toString().isEmpty() || resultado.getText().toString().isEmpty() || observacao.getText().toString().isEmpty()){
                    Toast.makeText(NovoExame.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
                }else{
                    exame = new Exame();
                    exame.setNome(nome.getText().toString());
                    exame.setData(data.getText().toString());
                    exame.setMedico(medico.getText().toString());
                    exame.setLocal(local.getText().toString());
                    exame.setResultado(resultado.getText().toString());
                    exame.setObservacao(observacao.getText().toString());

                    Intent intent = getIntent();

                    Bundle bundle = intent.getExtras();

                    chave = Base64Custom.codificarBase64(bundle.getString("inscricao")) ;

                    exame.setId(chave);

                    exame.salvar();

                    Toast.makeText(NovoExame.this, "Exame cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    finish();
                }

            }
        });


    }
}
