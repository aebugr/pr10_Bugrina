 package com.example.pr10_bugrina;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void AlertDialogs(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(message).setCancelable(false).setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) { dialog.cancel(); }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Consider(View view) {
        EditText course = findViewById(R.id.editText1);
        EditText count = findViewById(R.id.editText2);
        Switch dollar = findViewById(R.id.switch1);
        TextView tv = findViewById(R.id.textView4);

        if (course.getText().length() > 0) {
            if (count.getText().length() > 0) {
                float f_course = Float.parseFloat(String.valueOf(course.getText()));
                float f_count = Float.parseFloat(String.valueOf(count.getText()));
                float composition = 0;
                if(dollar.isChecked() == true){
                    composition = f_course * f_count;
                    tv.setText(composition + " р.");
                }
                else{
                    composition = f_count / f_course;
                    tv.setText(composition + " $.");
                }
            } else AlertDialogs("Уведомление", "Введите кол-во доллара.");
        } else AlertDialogs("Уведомление", "Введите курс доллара.");
    }

    public void URI(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.banki.ru/products/currency/cash/usd/perm~/"));
        startActivity(intent);
    }
}