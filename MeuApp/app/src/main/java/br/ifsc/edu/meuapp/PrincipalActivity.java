package br.ifsc.edu.meuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.ifsc.edu.meuapp.Adapter.PessoaAdapter;
import br.ifsc.edu.meuapp.model.Pessoa;

public class PrincipalActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser myUser = mAuth.getCurrentUser();
        if(myUser != null){
           Log.d("FirebaseAuth", myUser.getEmail());
        }else{
            Log.d("FirebaseAuth", "usuario nao autenticado");
            finish();
        }
        findViewById(R.id.btnCadPessoa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastrarUsuarioActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnlogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        recyclerView = findViewById(R.id.idRec);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(getApplicationContext(),
                        DividerItemDecoration.VERTICAL)
        );


        final List<Pessoa> arrayListPessoa = new ArrayList<Pessoa>();
        mRef = FirebaseDatabase.getInstance().getReference("pessoas");
        mRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("RetriveData",  dataSnapshot.toString());
                Pessoa p;
                for (DataSnapshot obj:dataSnapshot.getChildren()){
                    p = obj.getValue(Pessoa.class);
                    arrayListPessoa.add(p);
                }
                PessoaAdapter pAdapter = new PessoaAdapter(
                        getApplicationContext(),
                         R.layout.item_pessoa_list,
                        arrayListPessoa
                );
                recyclerView.setAdapter(pAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}
