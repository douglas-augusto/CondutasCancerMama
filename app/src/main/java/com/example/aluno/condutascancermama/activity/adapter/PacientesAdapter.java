package com.example.aluno.condutascancermama.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.model.Paciente;

import java.util.ArrayList;

public class PacientesAdapter extends ArrayAdapter<Paciente> {

    private ArrayList<Paciente> pacientes;
    private Context context;

    public PacientesAdapter(@NonNull Context c, @NonNull ArrayList<Paciente> objects) {
        super(c,0, objects);
        this.pacientes = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if(pacientes != null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.lista_pacientes, parent, false);

            TextView nomePaciente = (TextView) view.findViewById(R.id.tv_nome);
            TextView municipioPaciente = (TextView) view.findViewById(R.id.tv_email);

            Paciente paciente = pacientes.get(position);
            nomePaciente.setText(paciente.getNome());
            municipioPaciente.setText((paciente.getMunicipio()));

        }

        return view;

    }

}
