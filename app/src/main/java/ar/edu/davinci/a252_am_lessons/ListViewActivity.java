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
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class ListViewActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    FirebaseFirestore db;

    String idUser;

    public void loadProfileData (String uid) {
        Log.i("editar-perfil", "editando");
        this.db
            .collection("users")
            .whereEqualTo("uid", uid)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()) {
                        Log.i("editar-perfil", "llegó el dato");

                        for(QueryDocumentSnapshot doc: task.getResult()) {

                            idUser = doc.getId();
                            String name = (String) doc.getData().get("name");

                            EditText userName = findViewById(R.id.userName);
                            userName.setText(name);
                        }
                    }
                }
            });
    }

    public void updateUser(View v) {
        if(idUser == null) return;
        if(db == null) return;

        HashMap newUserData = new HashMap<>();
        EditText userName = findViewById(R.id.userName);

        newUserData.put("name", userName.getText().toString());

        db
            .collection("users")
            .document(idUser)
            .update(newUserData)
            .addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        this.mAuth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();


        if(this.mAuth == null ) return;
        if(this.db == null) return;

        Log.i("editar-perfil", "ingresando");


        if(this.mAuth.getCurrentUser() == null) return;

        String uid = this.mAuth.getCurrentUser().getUid();

        loadProfileData(uid);
    }
}