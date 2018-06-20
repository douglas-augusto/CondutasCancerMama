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

public class NovoUsuarioMedicamento extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;
    private EditText repetirSenha;
    private Button botaoCadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    public FirebaseAuth getAutenticacao() {
        return autenticacao;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario_medicamento);

        setTitle("Novo Administrador");

        campoNome = findViewById(R.id.campoNomeCadastro);
        campoEmail = findViewById(R.id.campoEmailCadastro);
        campoSenha = findViewById(R.id.campoSenhaCadastro);
        repetirSenha = findViewById(R.id.campoRepetirSenha);
        botaoCadastrar = findViewById(R.id.botaoCadastrarUsuario);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(campoNome.getText().toString().equals("") || campoEmail.getText().toString().equals("") || campoSenha.getText().toString().equals("") || repetirSenha.getText().toString().equals("")){

                    Toast.makeText(NovoUsuarioMedicamento.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();

                } else if (campoSenha.getText().toString().equals(repetirSenha.getText().toString())) {

                    usuario = new Usuario();
                    usuario.setNome(campoNome.getText().toString());
                    usuario.setEmail(campoEmail.getText().toString());
                    usuario.setSenha(campoSenha.getText().toString());
                    cadastrarUsuario();
                }else{
                    Toast.makeText(NovoUsuarioMedicamento.this, "As senhas digitadas não são iguais!", Toast.LENGTH_LONG).show();
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
        ).addOnCompleteListener(NovoUsuarioMedicamento.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(NovoUsuarioMedicamento.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show();

                    //Salvar os dados do usuario no banco
                    String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setId(identificadorUsuario);
                    usuario.salvar();

                    Preferencias preferencias = new Preferencias(NovoUsuarioMedicamento.this);
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

                    Toast.makeText(NovoUsuarioMedicamento.this, "Erro: "+erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void abrirLoginUsuario(){

        Intent intent = new Intent(NovoUsuarioMedicamento.this, MedicamentoLogin.class);
        startActivity(intent);
        finish();
    }
}
