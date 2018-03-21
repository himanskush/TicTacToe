package com.example.hali.tictactoe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public boolean game=true;

    public int count = 0;                               //for changing turns of players

    public int[] tapped ={1,1,1,1,1,1,1,1,1};           //=0 if tapped
                                                        //=1 if untapped
    public int[] ans={0,0,0,0,0,0,0,0,0};                 //for results  =1 for cross
                                                        //             =2 for circle

    public void retry (View view)
    {
        count=0;
        game=true;
        for(int i=0;i<9;i++)
        {
            tapped[i]=1;
            ans[i]=0;
        }
        LinearLayout LL= (LinearLayout) findViewById(R.id.ll);
        GridLayout GL = (GridLayout) findViewById(R.id.gl);
        LL.setVisibility(View.INVISIBLE);
        for(int i=0;i<9;i++)
        {
            ((ImageView)GL.getChildAt(i)).setImageResource(0);
        }
    }

    public void tap (View view)
    {
        int tag,loopvar=0;
        LinearLayout LL= (LinearLayout) findViewById(R.id.ll);
        TextView text = (TextView) findViewById(R.id.textView);
        ImageView img = (ImageView) view ;
        tag=Integer.parseInt(img.getTag().toString());
        if(tapped[tag]==1 && game) {
            if (count % 2 == 0)
            {
                img.setImageResource(R.drawable.cross);
                ans[tag]=1;
            }
            else
            {
                img.setImageResource(R.drawable.circle);
                ans[tag]=2;
            }

            count++;

            tapped[tag]=0;
            if (    (ans[0] == ans[1] && ans[1] == ans[2] && (ans[2]==1 || ans[2]==2)) ||
                    (ans[3] == ans[4] && ans[4] == ans[5] && (ans[5]==1 || ans[5]==2)) ||
                    (ans[6] == ans[7] && ans[7] == ans[8] && (ans[8]==1 || ans[8]==2)) ||
                    (ans[0] == ans[3] && ans[3] == ans[6] && (ans[6]==1 || ans[6]==2)) ||
                    (ans[1] == ans[4] && ans[4] == ans[7] && (ans[7]==1 || ans[7]==2)) ||
                    (ans[2] == ans[5] && ans[5] == ans[8] && (ans[8]==1 || ans[8]==2)) ||
                    (ans[0] == ans[4] && ans[4] == ans[8] && (ans[8]==1 || ans[8]==2)) ||
                    (ans[2] == ans[4] && ans[4] == ans[6] && (ans[6]==1 || ans[6]==2))) {

                if (ans[tag] == 1) {
                    LL.setVisibility(View.VISIBLE);
                    text.setText("The Player with cross won !!");
                }
                else {
                    LL.setVisibility(View.VISIBLE);
                    text.setText("The Player with circle won !!");
                }
                game=false;
            } else {
                for (int i = 0; i < 9; i++)
                    if (ans[i] == 0)
                        loopvar++;
                if (loopvar == 0) {
                    LL.setVisibility(View.VISIBLE);
                    text.setText("Its a DRAW !!");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout LL= (LinearLayout) findViewById(R.id.ll);
        LL.setVisibility(View.INVISIBLE);
    }
}
