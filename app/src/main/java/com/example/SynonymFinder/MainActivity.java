package com.example.SynonymFinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //RequestQueue queue = Volley.newRequestQueue(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Begin).setVisibility(View.VISIBLE);
        EditText edit = findViewById(R.id.wordInput);
        final String word = edit.getText().toString();
        Button enter = findViewById(R.id.enter);
        final TextView synonym = findViewById(R.id.output);
        synonym.setVisibility(View.INVISIBLE);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                synonym.setVisibility(View.VISIBLE);
                synonym.setText(getSynonym(word));
            }
        });
    }
    public String getSynonym(String word) {
        String use = word.trim().toLowerCase();
        String startURL = "https://dictionaryapi.com/api/v3/references/thesaurus/json/";
        String key = "?key=48be692f-801c-4dd7-8f0b-f65e90717d89";
        String url = startURL + use + key;
        try{
            JSONArray jsonArray = getJSONObjectFromURL(url);
            JSONObject obj = jsonArray.getJSONObject(0);
            JSONObject meta = obj.getJSONObject("meta");
            JSONArray syns = meta.getJSONArray("syns");
            JSONArray synonyms = syns.getJSONArray(0);
            String toReturn = synonyms.getString(0);
            return toReturn;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static JSONArray getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);
        return new JSONArray(jsonString);
    }

}
