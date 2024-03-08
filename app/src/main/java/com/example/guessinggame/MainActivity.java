package com.example.guessinggame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int randomNumber;
    private int chancesLeft = 3;

    private EditText guessEditText;
    private Button guessButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessEditText = findViewById(R.id.guessEditText);
        guessButton = findViewById(R.id.guessButton);
        resultTextView = findViewById(R.id.resultTextView);

        randomNumber = generateRandomNumber();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    guessNumber();
            }
        });
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }

    private void guessNumber() {
        if (chancesLeft > 0) {
            int guess = Integer.parseInt(guessEditText.getText().toString());
            if (guess < randomNumber) {
                chancesLeft--;
                resultTextView.setText("You guessed a lower number...Chances left: " + chancesLeft);
                if (chancesLeft == 0) {
                    resultTextView.setText("You have used all your chances. The correct number was " + randomNumber);
                    guessButton.setEnabled(false);
                }
            } else if(guess > randomNumber){
                chancesLeft--;
                resultTextView.setText("You guessed a higher number...Chances left: " + chancesLeft);
                if (chancesLeft == 0) {
                    resultTextView.setText("You have used all your chances. The correct number was " + randomNumber);
                    guessButton.setEnabled(false);
                } /*else {
                    resultTextView.setText("Incorrect guess. Chances left: " + chancesLeft);
                }*/
            }else{
                resultTextView.setText("Congratulation!!! You guessed the correct number.");
                guessButton.setEnabled(false);
            }
        } else {
            Toast.makeText(this, "Game over!", Toast.LENGTH_SHORT).show();
            resultTextView.setText("You have used all your chances. The correct number was " + randomNumber);
            guessButton.setEnabled(false);
        }
    }
}