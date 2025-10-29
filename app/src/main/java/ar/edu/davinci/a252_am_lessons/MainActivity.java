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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            //TODO: eliminar después de la prueba
            //mAuth.signOut();
        } else {
           Intent intent = new Intent(this, LoginActivity.class);
           startActivity(intent);
        }

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
                button.setText("Le hice click");

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
    }
}