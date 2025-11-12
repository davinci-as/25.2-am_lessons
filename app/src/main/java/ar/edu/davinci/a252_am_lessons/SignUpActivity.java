package ar.edu.davinci.a252_am_lessons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;

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
        if(this.db == null) return;

        if(!validateName(name)) return;
        if(!validateEmail(email)) return;
        if(!validatePassword(password)) return;

        this.mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.i("create-user", "usuario creado");
                            String uid = task.getResult().getUser().getUid();
                            HashMap newUser = new HashMap();
                            newUser.put("uid", uid);
                            newUser.put("name", name);
                            db
                                .collection("users")
                                .add(newUser)
                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if(task.isSuccessful()) {
                                            Log.i("create-user", "usuario guardado");

                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                });

                        }
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mAuth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();

        setContentView(R.layout.activity_sign_up);
    }
}