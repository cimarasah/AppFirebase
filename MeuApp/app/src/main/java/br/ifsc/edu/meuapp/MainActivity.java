package br.ifsc.edu.meuapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;

import model.Pessoa;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    EditText login,senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.etEmail);
        senha = findViewById(R.id.edSenha);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =  firebaseDatabase.getReference();

        //Pessoa pessoa = new Pessoa( "Cimara", "07403729471", "f");

        //databaseReference.child("pessoas").push().setValue(pessoa);-
        databaseReference.child("pessoas").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Pessoa p = dataSnapshot.getValue(Pessoa.class);
                Log.d("databasePessoa", p.getNome());
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

    public void autenticar(View view) {
        mAuth.signInWithEmailAndPassword(
                login.getText().toString(),
                senha.getText().toString()
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Usuario Logado!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Erro Login!", Toast.LENGTH_LONG).show();;
                }
            }
        });;
    }

    public void cadastrar(View view) {
        mAuth.createUserWithEmailAndPassword(
                login.getText().toString(),
                senha.getText().toString()
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();;
                }else{
                    Toast.makeText(getApplicationContext(), "Falha no cadastro!", Toast.LENGTH_LONG).show();;
                }
            }
        });
    }

}
