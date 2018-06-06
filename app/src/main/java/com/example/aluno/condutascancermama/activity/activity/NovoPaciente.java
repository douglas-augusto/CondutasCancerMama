package com.example.aluno.condutascancermama.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.config.ConfiguracaoFirebase;
import com.example.aluno.condutascancermama.activity.helper.Base64Custom;
import com.example.aluno.condutascancermama.activity.model.Paciente;
import com.google.firebase.auth.FirebaseAuth;

public class NovoPaciente extends AppCompatActivity {

    private EditText nome;
    private EditText inscricao;
    private EditText nascimento;
    private EditText sexo;
    private EditText municipio;
    private EditText estado;
    private Button botaoCadastrar;
    private FirebaseAuth usuarioFirebase;
    private Paciente paciente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_paciente);

        usuarioFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        nome = findViewById(R.id.campoNomePaciente);
        inscricao = findViewById(R.id.campoInscricaoPaciente);
        nascimento = findViewById(R.id.campoNascimentoPaciente);
        sexo = findViewById(R.id.campoSexoPaciente);
        municipio = findViewById(R.id.campoMunicipioPaciente);
        estado = findViewById(R.id.campoEstadoPaciente);
        botaoCadastrar = findViewById(R.id.botaoCadastrarPaciente);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nome.getText().toString().isEmpty() || inscricao.getText().toString().isEmpty() || nascimento.getText().toString().isEmpty() || sexo.getText().toString().isEmpty() || municipio.getText().toString().isEmpty() || estado.getText().toString().isEmpty()){
                    Toast.makeText(NovoPaciente.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
                }else{
                    paciente = new Paciente();
                    paciente.setNome(nome.getText().toString());
                    paciente.setInscricao(inscricao.getText().toString());
                    paciente.setNascimento(nascimento.getText().toString());
                    paciente.setSexo(sexo.getText().toString());
                    paciente.setMunicipio(municipio.getText().toString());
                    paciente.setEstado(estado.getText().toString());

                    String pacienteAtual = Base64Custom.codificarBase64(usuarioFirebase.getCurrentUser().getEmail());
                    paciente.setId(pacienteAtual);
                    paciente.salvarPaciente();

                    Toast.makeText(NovoPaciente.this, "Cadastrando!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NovoPaciente.this, Acompanhamento.class );
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}
