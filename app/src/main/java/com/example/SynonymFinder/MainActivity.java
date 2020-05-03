package com.example.SynonymFinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //RequestQueue queue = Volley.newRequestQueue(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Begin).setVisibility(View.VISIBLE);
        final EditText edit = findViewById(R.id.wordInput); //The text box that takes the user input
        Button enter = findViewById(R.id.enter); //The enter button
        final TextView synonym = findViewById(R.id.output); //The text box the displays the synonym output
        synonym.setVisibility(View.INVISIBLE);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                synonym.setVisibility(View.VISIBLE);
                synonym.setText(edit.getText());
                SynonymFinder synonymFinder = new SynonymFinder(edit.getText().toString());
                String url = synonymFinder.getSynonym();
                synonym.setText(url);
            }
        });
    }



}
