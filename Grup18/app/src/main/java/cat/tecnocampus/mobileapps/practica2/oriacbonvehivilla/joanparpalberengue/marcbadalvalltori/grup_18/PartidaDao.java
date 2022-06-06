package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PartidaDao {
    @Query("SELECT * FROM Partida")
    List<Partida> getPartidas();

    @Query("SELECT * FROM Partida WHERE nickname=:nickname")
    List<Partida> getPartida(String nickname);

    @Query("SELECT nickname, sum(puntuacion) FROM Partida GROUP BY nickname")
    Cursor getPartidasAgrupadas();

    @Insert
    void insertPartida(Partida partida);

    @Update
    void updatePartida(Partida partida);
}
