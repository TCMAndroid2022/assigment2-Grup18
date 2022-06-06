package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonJugar = findViewById(R.id.botonJugar);

        final EditText editTextNickName = findViewById(R.id.editTextTextPersonName);

        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = editTextNickName.getText().toString();
                Pattern pat = Pattern.compile("[a-zA-Z0-9]{5,20}");
                if (pat.matcher(nickname).matches()&&!nickname.equalsIgnoreCase("nickname")) {
                    Intent intent = new Intent(MainActivity.this, JuegoActivity.class);
                    intent.putExtra("nickname", nickname);
                    startActivity(intent);
                } else {
                    editTextNickName.setError("Nickname invalid");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.accion_listado:
                Intent intent = new Intent(this, ListadoActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}