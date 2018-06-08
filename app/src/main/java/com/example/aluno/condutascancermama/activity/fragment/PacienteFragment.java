package com.example.aluno.condutascancermama.activity.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.activity.PacienteActivity;
import com.example.aluno.condutascancermama.activity.adapter.PacientesAdapter;
import com.example.aluno.condutascancermama.activity.config.ConfiguracaoFirebase;
import com.example.aluno.condutascancermama.activity.helper.Preferencias;
import com.example.aluno.condutascancermama.activity.model.Paciente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PacienteFragment extends android.support.v4.app.Fragment{

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Paciente> pacientes;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerLocais;

    public PacienteFragment() {}

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerLocais);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerLocais);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pacientes = new ArrayList<>();

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pacientes, container, false);

        listView = (ListView) view.findViewById(R.id.lv_locais);

        /*adapter = new ArrayAdapter(
                getActivity(),
                R.layout.lista_locais,
                locais
        );*/


        adapter = new PacientesAdapter(getActivity(), pacientes);
        listView.setAdapter(adapter);

        Preferencias preferencias = new Preferencias(getActivity());
        String usuarioLogado = preferencias.getIdentificador();
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("pacientes")
                .child(usuarioLogado);

        valueEventListenerLocais = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar a lista
                pacientes.clear();

                //Listar pacientes
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Paciente paciente = dados.getValue(Paciente.class);
                    pacientes.add(paciente);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), PacienteActivity.class);

                // recupera dados a serem passados
                Paciente paciente = pacientes.get(position);

                // enviando dados para conversa activity
                intent.putExtra("nome", paciente.getNome() );
                intent.putExtra("inscricao", paciente.getInscricao() );
                intent.putExtra("nascimento", paciente.getNascimento());
                intent.putExtra("sexo", paciente.getSexo());
                intent.putExtra("municipio",paciente.getMunicipio());
                intent.putExtra("estado",paciente.getEstado());

                startActivity(intent);

            }
        });

        return view;
    }

}
