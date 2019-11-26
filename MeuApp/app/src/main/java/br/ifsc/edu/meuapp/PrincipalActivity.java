package br.ifsc.edu.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PrincipalActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
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
    }
    public void logOut(View view){
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();


    }
}
