package com.webgump.morpionmaster;

import android.graphics.drawable.AnimationDrawable;
import android.widget.Button;

/**
 * Created by Administrateur on 08/09/2017.
 */

public class IaPlayer {

    public IaPlayer() {

    }

    public void IaPlay(Button b){

        b.setBackgroundResource(R.drawable.round_animation);
        b.setText("o");
        AnimationDrawable frameAnimation = (AnimationDrawable) b.getBackground();
        frameAnimation.start();


    }

}
