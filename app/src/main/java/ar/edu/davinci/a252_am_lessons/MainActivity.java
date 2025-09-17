package ar.edu.davinci.a252_am_lessons;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
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
        button.setBackgroundColor(R.color.black);
        button.setText(R.string.button_message);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                button.setText("Le hice click");

                //TextView textView = findViewById(R.id.textView);
                textView.setText("Le hice click también");
            }
        });

        Button button2 = new Button(this);
        button2.setBackgroundColor(R.color.black);
        button2.setText(R.string.button_message);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                button.setText("Le hice click");

                //TextView textView = findViewById(R.id.textView);
                textView.setText("Le hice click desde otro lado");
            }
        });

        // 4. Agregar los hijos al container
        container.addView(textView);
        container.addView(button);
        container.addView(button2);

        Log.i("testing", String.valueOf(R.string.log_message));
    }
}