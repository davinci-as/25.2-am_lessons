package ar.edu.davinci.a252_am_lessons;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. Encontrar container
        LinearLayout container = findViewById(R.id.container);

        // 2. Crear el textview
        TextView textView = new TextView(this);
        textView.setText(R.string.welcome_message);

        // 3. Crear el button
        Button button = new Button(this);
        button.setText(R.string.button_message);

        // 4. Agregar los hijos al container
        container.addView(textView);
        container.addView(button);

        Log.i("testing", String.valueOf(R.string.log_message));
    }
}