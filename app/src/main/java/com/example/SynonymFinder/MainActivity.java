package com.example.SynonymFinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
public class MainActivity extends AppCompatActivity {

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
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                synonym.setVisibility(View.VISIBLE);
                synonym.setText(edit.getText());
                FindSynonym findSynonym = new FindSynonym(edit.getText().toString());
                String url = findSynonym.getUrl();
                //synonym.setText(url);
                try {
                    setSynonym(url);
                } finally {
                    synonym.setText("Please try a new word");
                }
            }
        });


    }
    public void setSynonym(String url) {
        final TextView textView = findViewById(R.id.output);
        final RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        response = response.replaceAll(",", "   ");
                        response = response.replaceAll("\"", "");
                        response = response.replaceAll("\\p{P}","");
                        int startIndex = response.indexOf("syns");
                        int lastIndex = response.indexOf("ants");
                        textView.setText(response.substring(startIndex + 4, lastIndex));
                    }
                }, new Response.ErrorListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
}
