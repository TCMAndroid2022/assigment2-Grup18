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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class JuegoActivity extends AppCompatActivity {

    private GestorJuego gestorJuego = new GestorJuego("llaves");
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
                            JuegoActivity.this.gestorJuego = new GestorJuego(palabra);
                            refrescarPanel();
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


        refrescarPanel();

        final EditText cuadroTexto = findViewById(R.id.editTextTextUserChoice);
        Button botonLetra = findViewById(R.id.botonSetLetter);
        botonLetra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cadenaCuadro = cuadroTexto.getText().toString();
                if(cadenaCuadro.length() > 0){
                    char letra = cadenaCuadro.charAt(0);
                    gestorJuego.anadirLetra(letra);
                    refrescarPanel();
                    comprobarFinDeJuego();
                }
            }
        });

        Button botonResolve = findViewById(R.id.botonResolve);
        botonResolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cadena = cuadroTexto.getText().toString();
                gestorJuego.resolver(cadena);
                comprobarFinDeJuego();
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

    private void comprobarFinDeJuego() {
        if(gestorJuego.terminado()) {
            TextView textViewPuntuacion = findViewById(R.id.textViewPuntuacion);
            int puntuacion = gestorJuego.getPuntuacion();
            textViewPuntuacion.setText("Tu puntuacion es " +  puntuacion);
            AppDatabase appDatabase = AppDatabase.getDatabase(this);
            PartidaDao partidaDao = appDatabase.partidaDao();

            Partida partida = new Partida(nickname, puntuacion);


            partidaDao.insertPartida(partida);

        }
    }


    private void refrescarPanel() {
        String cadenaPanel = gestorJuego.getCadenaPanel();
        TextView panel = findViewById(R.id.textViewPanel);
        panel.setText(cadenaPanel);
    }
}