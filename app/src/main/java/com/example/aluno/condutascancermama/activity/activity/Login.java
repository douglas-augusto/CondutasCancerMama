package com.example.aluno.condutascancermama.activity.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.config.ConfiguracaoFirebase;
import com.example.aluno.condutascancermama.activity.helper.Base64Custom;
import com.example.aluno.condutascancermama.activity.helper.Preferencias;
import com.example.aluno.condutascancermama.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private TextView novoUsuario;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerUsuario;
    private String identificadorUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();

        email = (EditText) findViewById(R.id.campo_email_login);
        senha = (EditText) findViewById(R.id.campo_senha_login);
        botaoLogar = (Button) findViewById(R.id.botao_entrar_login);
        novoUsuario = (TextView) findViewById(R.id.entrarNovoUsuario);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().equals("") || senha.getText().toString().equals("")){

                    Toast.makeText(Login.this, "Informe e-mail e senha!", Toast.LENGTH_LONG).show();

                }else {
                    usuario = new Usuario();
                    usuario.setEmail(email.getText().toString());
                    usuario.setSenha(senha.getText().toString());
                    validarLogin();
                    Toast.makeText(Login.this, "Carregando...", Toast.LENGTH_LONG).show();
                }

            }
        });

        novoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NovoUsuario.class);
                startActivity(intent);
            }
        });
    }

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if( task.isSuccessful() ){


                    identificadorUsuarioLogado = Base64Custom.codificarBase64(usuario.getEmail());

                    firebase = ConfiguracaoFirebase.getFirebase()
                            .child("usuarios")
                            .child( identificadorUsuarioLogado );

                    valueEventListenerUsuario = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Usuario usuarioRecuperado = dataSnapshot.getValue( Usuario.class );

                            Preferencias preferencias = new Preferencias(Login.this);
                            preferencias.salvarDados( identificadorUsuarioLogado, usuarioRecuperado.getNome() );

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };

                    firebase.addListenerForSingleValueEvent( valueEventListenerUsuario );



                    abrirTelaPrincipal();
                    Toast.makeText(Login.this, "Sucesso ao fazer login!", Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText(Login.this, "Erro ao fazer login!", Toast.LENGTH_LONG ).show();
                }

            }
        });

    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(Login.this, Acompanhamento.class);
        startActivity(intent);
        finish();
    }

    public void verificarUsuarioLogado(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if(autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }

    }
}
