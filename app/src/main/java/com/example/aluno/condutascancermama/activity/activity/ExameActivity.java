package com.example.aluno.condutascancermama.activity.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.config.ConfiguracaoFirebase;
import com.example.aluno.condutascancermama.activity.helper.Base64Custom;
import com.example.aluno.condutascancermama.activity.helper.ExamePreferencias;
import com.google.firebase.database.DatabaseReference;

public class ExameActivity extends AppCompatActivity {

    private TextView nomeExame;
    private TextView dataExame;
    private TextView medicoExame;
    private TextView localExame;
    private TextView resultadoExame;
    private TextView observacaoExame;
    private String nome;
    private String data;
    private String medico;
    private String local;
    private String resultado;
    private String observacao;
    private Button botaoApagarExame;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exame);

        reference = ConfiguracaoFirebase.getFirebase();

        nomeExame = findViewById(R.id.nomeExame);
        dataExame = findViewById(R.id.dataExame);
        medicoExame = findViewById(R.id.medicoExame);
        localExame = findViewById(R.id.localExame);
        resultadoExame = findViewById(R.id.resultadoExame);
        observacaoExame = findViewById(R.id.observacaoExame);
        botaoApagarExame = findViewById(R.id.botaoApagarExame);

        setTitle("Dados do exame");

        Bundle info = getIntent().getExtras();

        if( info != null ){
            nome = info.getString("nome");
            data = info.getString("data");
            medico = info.getString("medico");
            local = info.getString("local");
            resultado = info.getString("resultado");
            observacao = info.getString("observacao");
        }

        nomeExame.setText("Exame: "+nome);
        dataExame.setText("Data: "+data);
        medicoExame.setText("Médico: "+medico);
        localExame.setText("Local: "+local);
        resultadoExame.setText("Resultado: "+resultado);
        observacaoExame.setText("Observação: "+observacao);

        botaoApagarExame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExamePreferencias preferencias = new ExamePreferencias(ExameActivity.this);
                String pacienteAtual = Base64Custom.codificarBase64(preferencias.getIdentificador());
                String exameAtual = Base64Custom.codificarBase64(data);
                reference.child("exames").child(pacienteAtual).child(exameAtual).removeValue();
                finish();

            }
        });

    }
}
