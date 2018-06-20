package com.example.aluno.condutascancermama.activity.model;

import com.example.aluno.condutascancermama.activity.config.ConfiguracaoFirebase;
import com.example.aluno.condutascancermama.activity.helper.Base64Custom;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Douglas Augusto on 19/06/2018.
 */

public class Medicamento {

    private String id;
    private String nome;
    private String quantidade;
    private String frequencia;
    private String horario;

    public Medicamento() {
    }

    public void salvarMedicamento(){

        String nomeCodificado = Base64Custom.codificarBase64(getNome());

        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("medicamentos").child(getId()).child(nomeCodificado).setValue(this);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
