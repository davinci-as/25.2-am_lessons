package ar.edu.davinci.a252_am_lessons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    public boolean validateName (String name) {
        if(name == "") return false;
        return true;
    }

    public boolean validateEmail (String email) {
        if(email == "") return false;
        return true;
    }

    public boolean validatePassword (String password) {
        if(password == "") return false;
        if(password.length() < 8) return false;
        return true;
    }


    public void signup (View v) {
        EditText nameInput = findViewById(R.id.signupName);
        EditText emailInput = findViewById(R.id.signupEmail);
        EditText passwordInput = findViewById(R.id.signupPassword);

        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(this.mAuth == null) return;

        if(!validateName(name)) return;
        if(!validateEmail(email)) return;
        if(!validatePassword(password)) return;

        this.mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.i("create-user", "usuario creado");
                        }
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_sign_up);
    }
}