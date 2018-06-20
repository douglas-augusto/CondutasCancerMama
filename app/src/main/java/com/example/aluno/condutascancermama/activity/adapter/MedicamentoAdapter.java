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
import com.example.aluno.condutascancermama.activity.model.Exame;
import com.example.aluno.condutascancermama.activity.model.Medicamento;

import java.util.ArrayList;

/**
 * Created by Douglas Augusto on 19/06/2018.
 */

public class MedicamentoAdapter extends ArrayAdapter<Medicamento> {

    private ArrayList<Medicamento> medicamentos;
    private Context context;

    public MedicamentoAdapter(@NonNull Context c, @NonNull ArrayList<Medicamento> objects) {
        super(c,0, objects);
        this.medicamentos = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if(medicamentos != null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.lista_medicamentos, parent, false);

            TextView nomeExame = (TextView) view.findViewById(R.id.tv_nomeMedicamento);
            TextView infoExame = (TextView) view.findViewById(R.id.tv_infoMedicamento);

            Medicamento medicamento = medicamentos.get(position);
            nomeExame.setText(medicamento.getNome());
            infoExame.setText("Quantidade: "+medicamento.getQuantidade() + "  Frequencia: "+medicamento.getFrequencia());

        }

        return view;

    }

}


