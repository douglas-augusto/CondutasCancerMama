package com.example.aluno.condutascancermama.activity.activity;

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
import com.example.aluno.condutascancermama.activity.helper.Preferencias;
import com.google.firebase.database.DatabaseReference;

public class MedicamentoActivity extends AppCompatActivity {

    private TextView nomeMedicamento;
    private TextView quantidadeMedicamento;
    private TextView frequenciaMedicamento;
    private TextView horarioMedicamento;
    private String nome;
    private String quantidade;
    private String frequencia;
    private String horario;
    private Button botaoApagarMedicamento;
    private DatabaseReference reference;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        reference = ConfiguracaoFirebase.getFirebase();

        nomeMedicamento = findViewById(R.id.nomeMedicamento);
        quantidadeMedicamento = findViewById(R.id.quantidadeMedicamento);
        frequenciaMedicamento = findViewById(R.id.frequenciaMedicamento);
        horarioMedicamento = findViewById(R.id.horarioMedicamento);
        botaoApagarMedicamento = findViewById(R.id.botaoApagarMedicamento);

        setTitle("Dados do medicamento");

        Bundle extra = getIntent().getExtras();

        if( extra != null ){
            nome = extra.getString("nome");
            quantidade = extra.getString("quantidade");
            frequencia = extra.getString("frequencia");
            horario = extra.getString("horario");
        }

        nomeMedicamento.setText("Nome: "+nome);
        quantidadeMedicamento.setText("Quantidade: "+quantidade);
        frequenciaMedicamento.setText("Frequencia: "+frequencia);
        horarioMedicamento.setText("Horarios: "+horario);

        botaoApagarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MedicamentoActivity.this);
                builder.setTitle("Confirmação");
                builder.setMessage("Deseja mesmo apagar esse medicamento?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Preferencias preferencias = new Preferencias(MedicamentoActivity.this);
                        String usuarioLogado = preferencias.getIdentificador();
                        String medicamentoAtual = Base64Custom.codificarBase64(nome);
                        reference.child("medicamentos").child(usuarioLogado).child(medicamentoAtual).removeValue();
                        finish();
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

                alerta = builder.create();
                alerta.show();
            }
        });

    }
}
