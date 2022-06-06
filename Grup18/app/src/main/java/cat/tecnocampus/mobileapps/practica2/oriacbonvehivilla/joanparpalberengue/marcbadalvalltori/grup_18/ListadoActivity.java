package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);


        RecyclerView recyclerViewPuntuacion = findViewById(R.id.RecyclerViewPuntuacion);
        recyclerViewPuntuacion.setLayoutManager(new LinearLayoutManager(this));
        AppDatabase appDatabase = AppDatabase.getDatabase(this);
        PartidaDao partidaDao = appDatabase.partidaDao();
        Cursor cursor = partidaDao.getPartidasAgrupadas();
        List<PuntuacionUsuario> listadoPuntuacion = new ArrayList<>();
        while(cursor.moveToNext()){
            String nickname = cursor.getString(0);
            Integer puntuacion = cursor.getInt(1);
            PuntuacionUsuario puntuacionUsuario = new PuntuacionUsuario();
            puntuacionUsuario.setTotalPuntuacion(puntuacion);
            puntuacionUsuario.setNickname(nickname);
            listadoPuntuacion.add(puntuacionUsuario);
        }

PuntuacionAdapter puntuacionAdapter = new PuntuacionAdapter();
        puntuacionAdapter.setPuntuaciones(listadoPuntuacion);
        puntuacionAdapter.setListadoActivity(this);
recyclerViewPuntuacion.setAdapter(puntuacionAdapter);


        Button botonAtras = findViewById(R.id.ButtonAtrasListadoUsuarios);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void listarPuntos(String nickname){
        Intent intent = new Intent(this, PuntuacionUsuarioActivity.class);
        intent.putExtra("nickname", nickname);
        startActivity(intent);
    }
}