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

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private TextView novoUsuario;

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

                if(task.isSuccessful()){

                    Preferencias preferencias = new Preferencias(Login.this);
                    String usuarioLogado = Base64Custom.codificarBase64(usuario.getEmail());
                    preferencias.salvarDados(usuarioLogado);

                    abrirTelaPrincipal();


                }else{

                    String erroExcecao = "";

                    try{

                        throw task.getException();

                    } catch (FirebaseAuthInvalidUserException e) {
                        erroExcecao = "Essa conta de usuário não existe ou está desativada!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "Verifique seu e-mail e senha!";
                    } catch (Exception e) {
                        erroExcecao = "Não foi possível fazer o login!";
                        e.printStackTrace();
                    }

                    Toast.makeText(Login.this, "Erro: "+erroExcecao, Toast.LENGTH_LONG).show();

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
