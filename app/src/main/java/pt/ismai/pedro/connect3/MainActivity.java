package pt.ismai.pedro.connect3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    //0 -> yellow and 1-> red
    int activePlayer = 0;

    // Unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    boolean gameIsActive = true;

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        int [][] winningPositions = {
                {0,1,2} , {3,4,5} , {6,7,8},
                {0,3,6}, {1,4,7} , {2,5,8},
                {0,4,8}, {2,4,6}
        };

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            counter.setTranslationY(-1000f);

            gameState[tappedCounter] = activePlayer;


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions){

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2){

                    //Someone has won

                    gameIsActive = false;

                    String winner = "Red";

                    if(gameState[winningPosition[0]] == 0){

                        winner = "Yellow";
                    }

                    TextView won = findViewById(R.id.winnerMessage);
                    won.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgain);

                    layout.setVisibility(View.VISIBLE);

                }else {

                    boolean gameIsOver = true;

                    for(int counterState : gameState){

                        if(counterState == 2){

                            gameIsOver = false;
                        }
                    }

                    if (gameIsOver){

                        TextView won = findViewById(R.id.winnerMessage);
                        won.setText("It's a draw!");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgain);

                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgainButton(View view){

        gameIsActive = true;

        LinearLayout layout = findViewById(R.id.playAgain);

        layout.setVisibility(View.INVISIBLE);

        //0 -> yellow and 1-> red
        activePlayer = 0;

        for(int i = 0; i < gameState.length; i++){

            gameState[i] = 2;
        }

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i = 0 ; i < gridLayout.getChildCount(); i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
