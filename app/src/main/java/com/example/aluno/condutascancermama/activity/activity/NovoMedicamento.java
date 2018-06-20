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
import com.example.aluno.condutascancermama.activity.model.Medicamento;
import com.google.firebase.auth.FirebaseAuth;

public class NovoMedicamento extends AppCompatActivity {

    private EditText nome;
    private EditText quantidade;
    private EditText frequencia;
    private EditText horario;
    private Button botaoCadastrarMedicamento;
    private FirebaseAuth usuarioFirebase;
    private Medicamento medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_medicamento);

        setTitle("Novo medicamento");

        usuarioFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        nome = findViewById(R.id.campoNomeMedicamento);
        quantidade = findViewById(R.id.campoQtdMedicamento);
        frequencia = findViewById(R.id.campoFrequenciaMedicamento);
        horario = findViewById(R.id.campoHorarioMedicamento);
        botaoCadastrarMedicamento = findViewById(R.id.botaoCadastrarMedicamento);

        botaoCadastrarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nome.getText().toString().isEmpty() || quantidade.getText().toString().isEmpty() || frequencia.getText().toString().isEmpty() || horario.getText().toString().isEmpty()){
                    Toast.makeText(NovoMedicamento.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
                }else{
                    medicamento = new Medicamento();
                    medicamento.setNome(nome.getText().toString());
                    medicamento.setQuantidade(quantidade.getText().toString());
                    medicamento.setFrequencia(frequencia.getText().toString());
                    medicamento.setHorario(horario.getText().toString());

                    String usuarioAtual = Base64Custom.codificarBase64(usuarioFirebase.getCurrentUser().getEmail());
                    medicamento.setId(usuarioAtual);
                    medicamento.salvarMedicamento();

                    Toast.makeText(NovoMedicamento.this, "Cadastrando!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NovoMedicamento.this, MedicamentoMain.class );
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
