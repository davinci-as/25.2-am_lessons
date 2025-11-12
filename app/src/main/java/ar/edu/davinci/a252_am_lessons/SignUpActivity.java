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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_sign_up);
    }
}