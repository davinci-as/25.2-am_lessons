package ar.edu.davinci.a252_am_lessons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public void updateUiWithUserInfo(String name, TextView nameTextView) {
        nameTextView.setText(String.format("Hola %s", name));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        setContentView(R.layout.activity_main);
        // 1. Encontrar container
        LinearLayout container = findViewById(R.id.container);

        // 2. Crear el textview
        TextView textView = new TextView(this);
        textView.setText(R.string.welcome_message);

        // 3. Crear el button
        Button button = new Button(this);
        button.setBackgroundColor(R.color.black);
        button.setText(R.string.button_message);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                button.setText(R.string.perfil_editado);

                //TextView textView = findViewById(R.id.textView);
                textView.setText("Le hice click también");
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);

            }
        });

        Button button2 = new Button(this);
        button2.setBackgroundColor(R.color.black);
        button2.setText(R.string.cerrar_sesion);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // 4. Agregar los hijos al container
        container.addView(textView);
        container.addView(button);
        container.addView(button2);

        Log.i("testing", String.valueOf(R.string.log_message));

        //(new ImageDownloader()).execute("https://www.gstatic.com/devrel-devsite/prod/va726f77ce19c264bc8ae4520f2ee26cc9641a80eead40c2c8c599dc34ccb25d1/android/images/lockup.png");
        (new ApiRequest()).execute("https://rickandmortyapi.com/api/character/");

        if(user != null) {
            //TODO: eliminar después de la prueba
            //mAuth.signOut();
            String UID = user.getUid();
            db.collection("users")
                    .whereEqualTo("uid", UID)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String name = (String) document.getData().get("name");
                                    updateUiWithUserInfo(name, textView);
                                }
                            } else {
                                Log.d("firebase-firestore", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}