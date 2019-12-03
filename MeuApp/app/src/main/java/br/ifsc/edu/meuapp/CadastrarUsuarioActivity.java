package br.ifsc.edu.meuapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.ifsc.edu.meuapp.model.Pessoa;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    EditText nome,cpf, sexo;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        nome = findViewById(R.id.edNome);
        cpf = findViewById(R.id.edCpf);
        sexo = findViewById(R.id.edSexo);
        mRef = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.btnCadastrarPessoa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar(view);
            }
        });

    }
 public void cadastrar(View view){

     Pessoa p = new Pessoa(
             nome.getText().toString(),
             cpf.getText().toString(),
             sexo.getText().toString());

     mRef.child("pessoas").push().setValue(p).addOnCompleteListener(
             new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     if(task.isSuccessful()){
                         Toast.makeText(getApplicationContext(), "Pessoa cadastrada com sucesso!!", Toast.LENGTH_LONG).show();

                     }else{
                         Toast.makeText(getApplicationContext(), "Falha ao cadastrar Pessoa!", Toast.LENGTH_LONG).show();

                     }
                 }
             }
     );

        mRef.child("pessoas").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
 }
}
