package com.example.aluno.condutascancermama.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class Acompanhamento extends AppCompatActivity {

    private FirebaseAuth usuarioFirebase;
    private CardView botaoNovoPaciente;
    private CardView botaoVerCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhamento);

        usuarioFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        setTitle("Acompanhamento");

       botaoNovoPaciente = findViewById(R.id.botaoNovoPaciente);
       botaoVerCadastrados = findViewById(R.id.botaoVerCadastrados);

        botaoNovoPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acompanhamento.this, NovoPaciente.class);
                startActivity(intent);
            }
        });

        botaoVerCadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acompanhamento.this, PacientesCadastrados.class);
                startActivity(intent);
            }
        });
    }

    private void deslogarUsuario(){

        usuarioFirebase.signOut();

        Intent intent = new Intent(Acompanhamento.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ){
            case R.id.item_sair :
                deslogarUsuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
