package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class PuntuacionUsuarioAdapter extends RecyclerView.Adapter<PuntuacionUsuarioAdapter.ViewHolder> {

    private List<Partida> data = Collections.emptyList();


    public PuntuacionUsuarioAdapter() {
    }

    public void setPuntuaciones(List<Partida> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jugador_puntuacion_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Partida partida = data.get(position);
        holder.nicknameTextView.setText("");
        holder.puntuacionTextView.setText(partida.getPuntuacion() + "");

    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nicknameTextView;
        public TextView puntuacionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nicknameTextView = itemView.findViewById(R.id.textViewNickname);
            puntuacionTextView = itemView.findViewById(R.id.textViewPuntuacionFinal);


        }
    }
}