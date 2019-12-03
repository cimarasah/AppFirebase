package br.ifsc.edu.meuapp.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.ifsc.edu.meuapp.R;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvId, tvNome,tvCpf, tvSexo;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
//            tvId = itemView.findViewById(R.id.textViewId);
//            tvNome = itemView.findViewById(R.id.textViewId);
//            tvCpf = itemView.findViewById(R.id.textViewId);
//            tvSexo = itemView.findViewById(R.id.textViewId);
        }

    }
}
