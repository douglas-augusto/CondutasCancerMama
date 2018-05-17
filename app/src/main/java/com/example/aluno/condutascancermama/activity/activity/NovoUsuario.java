package com.example.aluno.condutascancermama.activity.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class NovoUsuario extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;
    private Button botaoCadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    public FirebaseAuth getAutenticacao() {
        return autenticacao;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        setTitle("Novo Administrador");

        campoNome =         (EditText) findViewById(R.id.campo_nome_cadastro);
        campoEmail =        (EditText) findViewById(R.id.campo_email_cadastro);
        campoSenha =        (EditText) findViewById(R.id.campo_senha_cadastro);
        botaoCadastrar =    (Button) findViewById(R.id.botao_cadastrar_usuario);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(campoNome.getText().toString().equals("") || campoEmail.getText().toString().equals("") || campoSenha.getText().toString().equals("")){

                    Toast.makeText(NovoUsuario.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();

                } else {

                    usuario = new Usuario();
                    usuario.setNome(campoNome.getText().toString());
                    usuario.setEmail(campoEmail.getText().toString());
                    usuario.setSenha(campoSenha.getText().toString());
                    cadastrarUsuario();
                }

            }
        });
    }

    private void cadastrarUsuario(){

        //Configurar a autenticação junto ao Firebase
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //Criar com email e senha
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(NovoUsuario.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(NovoUsuario.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show();

                    //Salvar os dados do usuario no banco
                    String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setId(identificadorUsuario);
                    usuario.salvar();

                    Preferencias preferencias = new Preferencias(NovoUsuario.this);
                    preferencias.salvarDados(identificadorUsuario, usuario.getNome());

                    abrirLoginUsuario();

                }else{
                    //Tratamento de exceções
                    String erroExcecao = "";
                    try{

                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma sennha mais forte, contendo letras e numeros";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "O e-mail que você digitou não é valido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Já existe um usuário usando esse endereço de e-mail";
                    } catch (Exception e) {
                        erroExcecao = "ao efeturar o cadastro!";
                        e.printStackTrace();
                    }

                    Toast.makeText(NovoUsuario.this, "Erro: "+erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void abrirLoginUsuario(){

        Intent intent = new Intent(NovoUsuario.this, Login.class);
        startActivity(intent);
        finish();
    }
}
