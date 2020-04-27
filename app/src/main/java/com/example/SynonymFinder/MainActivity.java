package com.example.SynonymFinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Begin).setVisibility(View.VISIBLE);
        EditText edit = findViewById(R.id.wordInput);
        String word = edit.getText().toString();
        Button enter = findViewById(R.id.enter);
        final TextView synonym = findViewById(R.id.output);
        synonym.setVisibility(View.INVISIBLE);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                synonym.setVisibility(View.VISIBLE);
            }
        });
    }

}
