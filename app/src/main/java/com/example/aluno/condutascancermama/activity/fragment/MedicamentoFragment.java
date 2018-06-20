package com.example.aluno.condutascancermama.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.activity.ExameActivity;
import com.example.aluno.condutascancermama.activity.activity.MedicamentoActivity;
import com.example.aluno.condutascancermama.activity.adapter.ExameAdapter;
import com.example.aluno.condutascancermama.activity.adapter.MedicamentoAdapter;
import com.example.aluno.condutascancermama.activity.config.ConfiguracaoFirebase;
import com.example.aluno.condutascancermama.activity.helper.Base64Custom;
import com.example.aluno.condutascancermama.activity.helper.ExamePreferencias;
import com.example.aluno.condutascancermama.activity.helper.Preferencias;
import com.example.aluno.condutascancermama.activity.model.Exame;
import com.example.aluno.condutascancermama.activity.model.Medicamento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Douglas Augusto on 19/06/2018.
 */

public class MedicamentoFragment extends android.support.v4.app.Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Medicamento> medicamentos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerMedicamento;

    public MedicamentoFragment(){}

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerMedicamento);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerMedicamento);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        medicamentos = new ArrayList<>();

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_medicamentos, container, false);

        listView = (ListView) view.findViewById(R.id.lv_medicamentos);

        /*adapter = new ArrayAdapter(
                getActivity(),
                R.layout.lista_locais,
                locais
        );*/

        adapter = new MedicamentoAdapter(getActivity(), medicamentos);
        listView.setAdapter(adapter);

        Preferencias preferencias = new Preferencias(getActivity());
        String usuarioLogado = preferencias.getIdentificador();
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("medicamentos")
                .child(usuarioLogado);

        valueEventListenerMedicamento = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar a lista
                medicamentos.clear();

                //Listar exames
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Medicamento medicamento = dados.getValue(Medicamento.class);
                    medicamentos.add(medicamento);
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

                Intent intent = new Intent(getActivity(), MedicamentoActivity.class);

                // recupera dados a serem passados
                Medicamento medicamento = medicamentos.get(position);

                // enviando dados para conversa activity
                intent.putExtra("nome", medicamento.getNome() );
                intent.putExtra("quantidade", medicamento.getQuantidade() );
                intent.putExtra("frequencia", medicamento.getFrequencia() );
                intent.putExtra("horario", medicamento.getHorario() );

                startActivity(intent);

            }
        });

        return view;
    }

}
