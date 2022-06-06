package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Partida {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "puntuacion")
    public int puntuacion;

    public  Partida() {}

    @Ignore
    public Partida(String nickname, int puntuacion ) {
        this.nickname = nickname;
        this.puntuacion = puntuacion;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

}
