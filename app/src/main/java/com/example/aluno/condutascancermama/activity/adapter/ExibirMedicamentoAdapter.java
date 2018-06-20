package com.example.aluno.condutascancermama.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aluno.condutascancermama.activity.fragment.ExameFragment;
import com.example.aluno.condutascancermama.activity.fragment.MedicamentoFragment;

/**
 * Created by Douglas Augusto on 19/06/2018.
 */

public class ExibirMedicamentoAdapter extends FragmentStatePagerAdapter {

    private String[] tituloAbas = {"LISTA DE MEDICAMENTOS"};

    public ExibirMedicamentoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment  = null;

        switch (position) {
            case 0:
                fragment = new MedicamentoFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAbas[position];
    }
}
