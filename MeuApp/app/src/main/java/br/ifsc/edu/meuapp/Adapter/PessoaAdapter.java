package br.ifsc.edu.meuapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ifsc.edu.meuapp.R;
import br.ifsc.edu.meuapp.model.Pessoa;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {

    Context mContext;
    int mResource;
    List<Pessoa> mDataSet;

    public PessoaAdapter(Context mContext, int mResource, List<Pessoa> mDataSet){
        this.mContext = mContext;
        this.mDataSet = mDataSet;
        this.mResource = mResource;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(mResource,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
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
