package com.webgump.morpionmaster;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Play extends AppCompatActivity {

    final String EXTRA_JOUEUR1 = "joueur1";
    final String EXTRA_JOUEUR2 = "joueur2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final EditText joueur1 = (EditText) findViewById(R.id.Joueur1);
        final EditText joueur2 = (EditText) findViewById(R.id.Joueur2);

        Slide slideOut = new Slide(Gravity.LEFT);
        getWindow().setExitTransition(slideOut);
        Slide slideIn = new Slide(Gravity.RIGHT);
        getWindow().setEnterTransition(slideIn);

        final Button connexion = (Button) findViewById(R.id.Play);
        connexion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play.this, MainActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(Play.this).toBundle();
                if (joueur1.getText().toString().isEmpty()){
                    intent.putExtra(EXTRA_JOUEUR1, "Joueur 1");
                } else {
                    intent.putExtra(EXTRA_JOUEUR1, joueur1.getText().toString());
                }
                if (joueur1.getText().toString().isEmpty()){
                    intent.putExtra(EXTRA_JOUEUR2, "Joueur 2");
                } else {
                    intent.putExtra(EXTRA_JOUEUR2, joueur2.getText().toString());
                }
                startActivity(intent, bundle);
            }
        });

    }
}
