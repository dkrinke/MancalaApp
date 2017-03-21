package com.example.daniel.mancalaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        String result = intent.getStringExtra("Result");

        TextView resultTextView = (TextView) findViewById(R.id.result);
        Button button1 = (Button) findViewById(R.id.buttonPlayAgain);
        Button button2 = (Button) findViewById(R.id.buttonQuit);

        resultTextView.setText(result);

        button1.setOnClickListener(myOnlyhandler);
        button2.setOnClickListener(myOnlyhandler);

    }

    View.OnClickListener myOnlyhandler = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonPlayAgain:
                    Intent intent = new Intent(GameOver.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.buttonQuit:
                    finish();
                    break;
            }
        }
    };
}
