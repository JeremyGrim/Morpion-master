package com.webgump.morpionmaster;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Play extends AppCompatActivity {

    final String EXTRA_JOUEUR1 = "joueur1";
    final String EXTRA_JOUEUR2 = "joueur2";
    final Boolean EXTRA_IAACTIVITY = true;
    ViewGroup TransitionContainer;
    private Button subscribe, play, playIa, play2;
    private EditText name1, name2;
    private TextView annuler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        TransitionContainer = (ViewGroup) findViewById(R.id.r);

        final EditText joueur1 = (EditText) findViewById(R.id.Joueur1);
        final EditText joueur2 = (EditText) findViewById(R.id.Joueur2);

        Slide slideOut = new Slide(Gravity.LEFT);
        getWindow().setExitTransition(slideOut);
        Slide slideIn = new Slide(Gravity.RIGHT);
        getWindow().setEnterTransition(slideIn);


        name1 = (EditText) TransitionContainer.findViewById(R.id.Joueur1);
        name2 = (EditText) TransitionContainer.findViewById(R.id.Joueur2);

        annuler = (Button) TransitionContainer.findViewById(R.id.annuler);
        annuler.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(TransitionContainer);
                name1.setVisibility(View.GONE);
                name2.setVisibility(View.GONE);
                annuler.setVisibility(View.GONE);
                subscribe.setVisibility(View.VISIBLE);
                play.setVisibility(View.GONE);
                playIa.setVisibility(View.VISIBLE);
                play2.setVisibility(View.GONE);
            }
        });

        subscribe = (Button) TransitionContainer.findViewById(R.id.subscribe);
        subscribe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(TransitionContainer);
                playIa.setVisibility(View.GONE);
                name1.setVisibility(View.VISIBLE);
                name2.setVisibility(View.VISIBLE);
                annuler.setVisibility(View.VISIBLE);
                subscribe.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
                play.setText("Enregistrer les joueurs");
            }
        });

        playIa = (Button) TransitionContainer.findViewById(R.id.PlayIa);
        playIa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(TransitionContainer);
                name1.setVisibility(View.VISIBLE);
                annuler.setVisibility(View.VISIBLE);
                subscribe.setVisibility(View.GONE);
                playIa.setVisibility(View.GONE);
                play.setVisibility(View.GONE);
                play2.setVisibility(View.VISIBLE);
                play.setText("Jouer");
            }
        });









        play = (Button) TransitionContainer.findViewById(R.id.Play);
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play.this, MainActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(Play.this).toBundle();


                if (joueur1.getText().toString().isEmpty()){
                    intent.putExtra(EXTRA_JOUEUR1, "Joueur 1");
                } else {
                    intent.putExtra(EXTRA_JOUEUR1, joueur1.getText().toString());
                }


                if (joueur2.getText().toString().isEmpty() && joueur2.getVisibility()== View.VISIBLE){
                    intent.putExtra(EXTRA_JOUEUR2, "Joueur 2");
                } else if(joueur2.getText().toString().isEmpty() && joueur2.getVisibility()== View.GONE) {
                    intent.putExtra(EXTRA_JOUEUR2, "IA Power");
                    intent.putExtra("EXTRA_IAACTIVITY", EXTRA_IAACTIVITY);

                } else {
                    intent.putExtra(EXTRA_JOUEUR2, joueur2.getText().toString());
                }

                startActivity(intent, bundle);
            }
        });

        play2 = (Button) TransitionContainer.findViewById(R.id.Play2);
        play2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play.this, IaActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(Play.this).toBundle();


                if (joueur1.getText().toString().isEmpty()){
                    intent.putExtra(EXTRA_JOUEUR1, "Joueur 1");
                } else {
                    intent.putExtra(EXTRA_JOUEUR1, joueur1.getText().toString());
                }


                startActivity(intent, bundle);
            }
        });



    }
}
