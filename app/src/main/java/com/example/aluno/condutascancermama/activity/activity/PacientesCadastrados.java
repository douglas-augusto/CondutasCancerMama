package com.example.aluno.condutascancermama.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.model.Paciente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PacientesCadastrados extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Paciente> locais;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerLocais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes_cadastrados);
    }
}
