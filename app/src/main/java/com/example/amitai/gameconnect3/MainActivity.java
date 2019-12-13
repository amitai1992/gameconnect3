package com.example.amitai.gameconnect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] state = {2,2,2,2,2,2,2,2,2}; //2 = empty, 0 = yellow, 1 = red
    int active = 0; //yellow = 0, red = 1
    boolean gameactive = true;
    public void play(View view){
        boolean yellow = false;
        boolean red = false;
        ImageView test = (ImageView) view;
        int tag =Integer.parseInt((String) test.getTag());
        if(state[tag] == 2 && gameactive) {
            test.setTranslationY(-1500);
            if (active == 0) {
                test.setImageResource(R.drawable.yellow);
                test.animate().translationYBy(1500).setDuration(500);
                state[tag] = active;
                if (check_state(active)) {
                    Toast.makeText(this, "yellow player won", Toast.LENGTH_LONG).show();
                }
                active++;
            } else if (active == 1) {
                test.setImageResource(R.drawable.red);
                test.animate().translationYBy(1500).setDuration(500);
                state[tag] = active;
                if (check_state(active)) {
                    Toast.makeText(this, "red player won", Toast.LENGTH_LONG).show();
                }
                active--;
            }


        }
    }
    public boolean check_state(int num){
        for (int i = 0;i < 8; i = i + 3){
            if((state[i] == num) && (state[i] == state[i+1]) && (state[i] == state[i+2])){
                gameactive = false;
                return true;
            }
        }
        for(int i = 0; i < 4; i++){
            if((state[i] == num) && (state[i] == state[i + 3]) && (state[i] == state[i + 6])){
                gameactive = false;
                return true;
            }
        }
        if((state[0] == num) && (state[0] == state[4]) && (state[0] == state[8])){
            gameactive = false;
            return true;
        }
        else if ((state[2] == num) && (state[4] == state[2]) && (state[6] == state[2])){
            gameactive = false;
            return true;
        }
        return false;
    }

    public void restart(View view){
        Button res = (Button)findViewById(R.id.button);
        GridLayout grid = (GridLayout)findViewById(R.id.gridlayout);
        for(int i=0; i<grid.getChildCount(); i++) {
            ImageView child = (ImageView) grid.getChildAt(i);
            // do stuff with child view
            child.setImageDrawable(null);
        }
        gameactive = true;
        for (int i = 0; i < state.length;i++){
            state[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
