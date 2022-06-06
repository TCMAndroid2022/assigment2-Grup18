package cat.tecnocampus.mobileapps.practica2.oriacbonvehivilla.joanparpalberengue.marcbadalvalltori.grup_18;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class PuntuacionAdapter extends RecyclerView.Adapter<PuntuacionAdapter.ViewHolder> {

        private List<PuntuacionUsuario> data = Collections.emptyList();
        private ListadoActivity listadoActivity = null;

        public PuntuacionAdapter() {
        }

        public void setPuntuaciones(List<PuntuacionUsuario> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        public void setListadoActivity(ListadoActivity listadoActivity){
            this.listadoActivity = listadoActivity;
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
            PuntuacionUsuario puntuacionUsuario = data.get(position);
            holder.nicknameTextView.setText(puntuacionUsuario.getNickname());
            holder.puntuacionTextView.setText(puntuacionUsuario.getTotalPuntuacion() + "");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listadoActivity.listarPuntos(puntuacionUsuario.getNickname());
                }
            });
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
