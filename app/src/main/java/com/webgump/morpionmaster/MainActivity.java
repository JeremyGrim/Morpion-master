package com.webgump.morpionmaster;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView  result;
    private Button a, b, c, d, e, f, g, h, i, rejouer;
    private Button[] bArray;
    boolean part = true;
    int part_count = 0;
    private TableRow partie;
    private int j1 = 0;
    private int j2 = 0;
    private int N = 0;
    ViewGroup TransitionContainer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Slide slideOut = new Slide(Gravity.LEFT);
        getWindow().setExitTransition(slideOut);
        Slide slideIn = new Slide(Gravity.RIGHT);
        getWindow().setEnterTransition(slideIn);
        TransitionContainer = (ViewGroup) findViewById(R.id.r);

        rejouer = (Button) TransitionContainer.findViewById(R.id.rejouer);
        result = (TextView) TransitionContainer.findViewById(R.id.result);


        //on recupère tous les boutons a partir de leurs ids
        a = (Button)findViewById(R.id.a);
        b = (Button)findViewById(R.id.b);
        c = (Button)findViewById(R.id.c);
        d = (Button)findViewById(R.id.d);
        e = (Button)findViewById(R.id.e);
        f = (Button)findViewById(R.id.f);
        g = (Button)findViewById(R.id.g);
        h = (Button)findViewById(R.id.h);
        i = (Button)findViewById(R.id.i);

        //on insère tout les button dans un tableau
        bArray = new Button[]{a,b,c,d,e,f,g,h,i};

        //on asscocie le clic a chaque bouton

        for (Button b : bArray){

            b.setOnClickListener(this);

        }

        rejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                part = true;
                part_count = 0;
                zero(true);
                TransitionManager.beginDelayedTransition(TransitionContainer);
                result.setVisibility(View.GONE);
                rejouer.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        Button b = (Button)v;
        buttonClicked((Button) v);

    }

    public void buttonClicked(Button b){

        if(part){

            b.setBackgroundResource(R.drawable.croix_animation);
            b.setText("x");
            AnimationDrawable frameAnimation = (AnimationDrawable) b.getBackground();
            // Start the animation (looped playback by default).
            frameAnimation.start();

        }
        else {
            b.setBackgroundResource(R.drawable.round_animation);
            b.setText("o");
            AnimationDrawable frameAnimation = (AnimationDrawable) b.getBackground();
            // Start the animation (looped playback by default).
            frameAnimation.start();
        }

        part_count++;

            /*b.setBackgroundColor(Color.YELLOW);*/
        b.setClickable(false);
        part=!part;

        gagner();

    }

    private void gagner(){

        //on verifie l'horizontale
        boolean gagne = false;
        if(a.getText() == b.getText() && b.getText() == c.getText() && !a.isClickable()){

            gagne = true;
        }
        else if(d.getText() == e.getText() && e.getText() == f.getText() && !d.isClickable()){

            gagne = true;
        }

        else if(g.getText() == h.getText() && h.getText() == i.getText() && !g.isClickable()){

            gagne = true;
        }


        //on verifie la verticale

        else if(a.getText() == d.getText() && d.getText() == g.getText() && !a.isClickable()){

            gagne = true;
        }

        else if(b.getText() == e.getText() && e.getText() == h.getText() && !e.isClickable()){

            gagne = true;
        }

        else if(c.getText() == f.getText() && f.getText() == i.getText() && !i.isClickable()){

            gagne = true;
        }

        //on verifie la diagonale

        else if(a.getText() == e.getText() && e.getText() == i.getText() && !a.isClickable()){

            gagne = true;
        }

        else if(c.getText() == e.getText() && e.getText() == g.getText() && !e.isClickable()){

            gagne = true;
        }

        if(gagne){
            TransitionManager.beginDelayedTransition(TransitionContainer);
            rejouer.setVisibility(View.VISIBLE);

            if(!part){
                TransitionManager.beginDelayedTransition(TransitionContainer);
                result.setText("Joueur 1 à gagner");
                result.setTextColor(getResources().getColor(R.color.j1));
                result.setVisibility(View.VISIBLE);
                j1++;
                TextView J1 = (TextView) findViewById(R.id.j1);
                J1.setText(Integer.toString(j1));


            }
            else{
                TransitionManager.beginDelayedTransition(TransitionContainer);
                result.setText("Joueur 2 à gagner");
                result.setTextColor(getResources().getColor(R.color.j2));
                result.setVisibility(View.VISIBLE);
                j2++;
                TextView J2 = (TextView) findViewById(R.id.j2);
                J2.setText(Integer.toString(j2));
            }

            //remettre à zero tous les bouttons
            zero(false);

        }else if (part_count == 9 ){

            TransitionManager.beginDelayedTransition(TransitionContainer);
            result.setText("Match Null");
            result.setTextColor(getResources().getColor(R.color.nul));
            result.setVisibility(View.VISIBLE);
            N++;
            TextView nul = (TextView) findViewById(R.id.nul);
            nul.setText(Integer.toString(N));

        }
    }

    //Calcul des scrores




    private void zero(boolean choix){

        for (Button b: bArray ){

            b.setClickable(choix);


            if (choix){

                b.setText("");
                b.setBackgroundResource(0);

            }
            else {

            }
        }
    }

}