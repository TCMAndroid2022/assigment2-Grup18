package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class JocActivity extends AppCompatActivity {

    private GestorDeJoc gestorDeJoc = new GestorDeJoc("llaves");
    private final String url = "https://palabras-aleatorias-public-api.herokuapp.com/random";
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        this.nickname = getIntent().getStringExtra("nickname");

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject body = response.getJSONObject("body");
                            String palabra = body.getString("Word");
                            JocActivity.this.gestorDeJoc = new GestorDeJoc(palabra);
                            refrescarPanell();
                        } catch (Exception ex) {
                           Log.d("SwA", "Error parsing json array");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SwA", "Error in request ");
                    }
                }
        );
        queue.add(jsonArrayRequest);


        refrescarPanell();

        final EditText quadreDeText = findViewById(R.id.editTextTextUserChoice);
        Button botoLletra = findViewById(R.id.botonSetLetter);
        botoLletra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cadenaCuadro = quadreDeText.getText().toString();
                Pattern pat = Pattern.compile("[a-zA-Z]{1}");
                if (pat.matcher(cadenaCuadro).matches()) {
                    char letra = cadenaCuadro.charAt(0);
                    gestorDeJoc.afegirLletra(letra);
                    refrescarPanell();
                    comprovarFinalDelJoc();
                } else {
                    quadreDeText.setError("Introdueix una sola lletra");
                }
            }
        });

        Button botonResolve = findViewById(R.id.botonResolve);
        botonResolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cadena = quadreDeText.getText().toString();
                Pattern pat = Pattern.compile("[a-zA-Z]{1,50}");
                if (pat.matcher(cadena).matches()) {
                    gestorDeJoc.resolver(cadena);
                    comprovarFinalDelJoc();
                }
                else {
                    quadreDeText.setError("Només pot contenir lletres");
                }
            }
        });

        Button botonAtras = findViewById(R.id.ButtonAtrasPartida);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void comprovarFinalDelJoc() {
        if(gestorDeJoc.finalitzat()) {
            TextView textViewPuntuacio = findViewById(R.id.textViewPuntuacion);
            int puntuacio = gestorDeJoc.getPuntuacion();
            textViewPuntuacio.setText("La teva puntuació es " +  puntuacio);
            AppDatabase appDatabase = AppDatabase.getDatabase(this);
            PartidaDao partidaDao = appDatabase.partidaDao();

            Partida partida = new Partida(nickname, puntuacio);


            partidaDao.insertPartida(partida);

        }
    }


    private void refrescarPanell() {
        String cadenaPanell = gestorDeJoc.getCadenaPanel();
        TextView panel = findViewById(R.id.textViewPanel);
        panel.setText(cadenaPanell);
    }
}