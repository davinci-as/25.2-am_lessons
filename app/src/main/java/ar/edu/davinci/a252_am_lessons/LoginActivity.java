package ar.edu.davinci.a252_am_lessons;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onLogin(View v) {
        EditText editTextText = findViewById(R.id.editTextText);
        EditText editTextTextPassword = findViewById(R.id.editTextTextPassword);

        String email = editTextText.getText().toString();
        String password = editTextTextPassword.getText().toString();

        if(email.equalsIgnoreCase("")) return;
        if(password.equalsIgnoreCase("")) return;

        Log.i("firebase-auth", "no hay usuario logueado");
        Log.i("firebase-auth", email);
        Log.i("firebase-auth", password);
        mAuth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Log.i("firebase-auth", "login exitoso");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
    }

    public void goSignup (View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);

    }
}
