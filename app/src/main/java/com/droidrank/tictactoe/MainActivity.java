package com.droidrank.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result;
    Button startButton;
    int activePlayer = 0; // 0 for O
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winningLocations[][] = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};
    boolean gameOver = false;
    String message;
    LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView)findViewById(R.id.tv_show_result);
        startButton = (Button)findViewById(R.id.bt_restart_game);
        startButton.setOnClickListener(this);
        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
    }


    public void buttonViewChange(View view){
        Button btn = (Button)view;
        int tappedLocation = Integer.parseInt(String.valueOf(view.getTag()));
        if(gameState[tappedLocation] == 2 && !gameOver) {
            gameState[tappedLocation] = activePlayer;
            if (activePlayer == 0) {
                btn.setText("O");
                setButtonBackground(btn, "O");
                activePlayer = 1;
            } else {
                btn.setText("X");
                setButtonBackground(btn, "X");
                activePlayer = 0;
            }
        }

        for(int[] winningPostion : winningLocations){
            if(gameState[winningPostion[0]] == gameState[winningPostion[1]]
                    && gameState[winningPostion[1]] == gameState[winningPostion[2]]
                    && gameState[winningPostion[0]] != 2 && !gameOver){
                if(activePlayer == 0){
                    message = "X wins";
                }else
                    message = "O wins";
                gameOver = true;
                result.setText(message);

            }
        }
    }

    private void setButtonBackground(Button btn,String startGame){
        if(startGame.equalsIgnoreCase("X"))
        {
            btn.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        }else{
            btn.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
        }
    }

    private void resetGame(){
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    @Override
    public void onClick(View v) {
        resetGame();
    }
}
