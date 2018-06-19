package com.example.aluno.condutascancermama.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.activity.ExameActivity;
import com.example.aluno.condutascancermama.activity.adapter.ExameAdapter;
import com.example.aluno.condutascancermama.activity.config.ConfiguracaoFirebase;
import com.example.aluno.condutascancermama.activity.helper.Base64Custom;
import com.example.aluno.condutascancermama.activity.helper.ExamePreferencias;
import com.example.aluno.condutascancermama.activity.model.Exame;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExameFragment extends android.support.v4.app.Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Exame> exames;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerExames;

    public ExameFragment(){}

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerExames);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerExames);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        exames = new ArrayList<>();

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_exames, container, false);

        listView = (ListView) view.findViewById(R.id.lv_exames);

        /*adapter = new ArrayAdapter(
                getActivity(),
                R.layout.lista_locais,
                locais
        );*/

        adapter = new ExameAdapter(getActivity(), exames);
        listView.setAdapter(adapter);

        ExamePreferencias preferencias = new ExamePreferencias(getActivity());
        String pacienteAtual = Base64Custom.codificarBase64(preferencias.getIdentificador());
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("exames")
                .child(pacienteAtual);

        valueEventListenerExames = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar a lista
                exames.clear();

                //Listar exames
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Exame exame = dados.getValue(Exame.class);
                    exames.add(exame);
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

                Intent intent = new Intent(getActivity(), ExameActivity.class);

                // recupera dados a serem passados
                Exame exame = exames.get(position);

                // enviando dados para conversa activity
                intent.putExtra("nome", exame.getNome() );
                intent.putExtra("data", exame.getData() );
                intent.putExtra("medico", exame.getMedico() );
                intent.putExtra("local", exame.getLocal() );
                intent.putExtra("resultado", exame.getResultado() );
                intent.putExtra("observacao", exame.getObservacao() );

                startActivity(intent);

            }
        });

        return view;
    }


}
