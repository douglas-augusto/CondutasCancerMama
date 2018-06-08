package com.example.aluno.condutascancermama.activity.activity;


import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aluno.condutascancermama.activity.adapter.FragmentAdapter;
import com.example.aluno.condutascancermama.activity.helper.SlidingTabLayout;
import com.example.aluno.condutascancermama.R;

import com.google.firebase.database.DatabaseReference;


public class PacientesCadastrados extends AppCompatActivity {

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes_cadastrados);

        setTitle("Acompanhamento");

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_pagina);

        //Configurar sliding tabs
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));

        //Configurar adapter
        FragmentAdapter tabAdapter = new FragmentAdapter( getSupportFragmentManager() );
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);

    }

}
