package com.example.aluno.condutascancermama.activity.activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.fragment.ExameFragment;
import com.example.aluno.condutascancermama.activity.helper.ExamePreferencias;
import com.example.aluno.condutascancermama.activity.helper.Preferencias;
import com.example.aluno.condutascancermama.activity.model.Paciente;

public class PacienteActivity extends AppCompatActivity {

    private TextView nomePaciente;
    private TextView inscricaoPaciente;
    private TextView nascimentoPaciente;
    private TextView sexoPaciente;
    private TextView municipioPaciente;
    private TextView estadoPaciente;
    private String nome;
    private String inscricao;
    private String nascimento;
    private String sexo;
    private String municipio;
    private String estado;
    private Button botaoCadastraExame;
    private Button botaoVerExames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        nomePaciente = findViewById(R.id.nomePaciente);
        inscricaoPaciente = findViewById(R.id.incricaoPaciente);
        nascimentoPaciente = findViewById(R.id.nascimentoPaciente);
        sexoPaciente = findViewById(R.id.sexoPaciente);
        municipioPaciente = findViewById(R.id.municipioPaciente);
        estadoPaciente = findViewById(R.id.estadoPaciente);
        botaoCadastraExame = findViewById(R.id.botaoCadastrarExames);
        botaoVerExames = findViewById(R.id.botaoHistoricoExames);

        setTitle("Dados do paciente");

        Bundle extra = getIntent().getExtras();

        if( extra != null ){
            nome = extra.getString("nome");
            inscricao = extra.getString("inscricao");
            nascimento = extra.getString("nascimento");
            sexo = extra.getString("sexo");
            municipio = extra.getString("municipio");
            estado = extra.getString("estado");
        }

        nomePaciente.setText("Nome: "+nome);
        inscricaoPaciente.setText("Inscrição: "+inscricao);
        nascimentoPaciente.setText("Nascimento: "+nascimento);
        sexoPaciente.setText("Sexo: "+sexo);
        municipioPaciente.setText("Município: "+municipio);
        estadoPaciente.setText("Estado "+estado);

        botaoCadastraExame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PacienteActivity.this, NovoExame.class );
                Bundle bundle = new Bundle();
                bundle.putString("inscricao", inscricao);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        botaoVerExames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExamePreferencias preferencias = new ExamePreferencias(PacienteActivity.this);
                preferencias.salvarDados(inscricao, inscricao);
                Intent intent = new Intent(PacienteActivity.this, HistoricoExames.class);
                startActivity(intent);
            }
        });
    }

}
