package com.example.aluno.condutascancermama.activity.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class ExamePreferencias {
    private Context contexto;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "condutas.preferenciasExame";
    private final  int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENTIFICADOR = "identificadorPaciente";
    private final String CHAVE_INSCRICAO = "inscricaoPacienteAtual";

    public ExamePreferencias (Context contextoParametro){

        contexto = contextoParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO,MODE);
        editor = preferences.edit();

    }

    public void salvarDados(String identificadorPaciente, String inscricaoPacienteAtual){

        editor.putString(CHAVE_IDENTIFICADOR, identificadorPaciente);
        editor.putString(CHAVE_INSCRICAO, inscricaoPacienteAtual);
        editor.commit();

    }

    public String getIdentificador(){

        return preferences.getString(CHAVE_IDENTIFICADOR, null);
    }

    public String getNome(){
        return preferences.getString(CHAVE_IDENTIFICADOR, null);
    }
}
