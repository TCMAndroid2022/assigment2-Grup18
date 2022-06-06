package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PuntuacionUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion_usuario);

        String nickname = getIntent().getStringExtra("nickname");
        TextView textViewUsuario = findViewById(R.id.textViewNombreUsuario);
        textViewUsuario.setText(nickname);

        AppDatabase appDatabase = AppDatabase.getDatabase(this);
        PartidaDao partidaDao = appDatabase.partidaDao();
        List<Partida> listadoPartidas = partidaDao.getPartida(nickname);

        RecyclerView recyclerViewPuntuacion = findViewById(R.id.recyclerViewPuntos);
        recyclerViewPuntuacion.setLayoutManager(new LinearLayoutManager(this));
        PuntuacionUsuarioAdapter puntuacionUsuarioAdapter = new PuntuacionUsuarioAdapter();
        puntuacionUsuarioAdapter.setPuntuaciones(listadoPartidas);
        recyclerViewPuntuacion.setAdapter(puntuacionUsuarioAdapter);

        Button botonAtras = findViewById(R.id.ButtonAtrasListadobutton);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}