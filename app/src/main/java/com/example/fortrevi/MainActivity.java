package com.example.fortrevi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText rowInput, columnInput;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowInput = findViewById(R.id.row_input);
        columnInput = findViewById(R.id.column_input);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String row = rowInput.getText().toString();
                String column = columnInput.getText().toString();
                if (isValidNumber(row) && isValidNumber(column)){
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("row", Integer.parseInt(row));
                    intent.putExtra("column", Integer.parseInt(column));
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Please input correct numbers.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean isValidNumber(String string){
        try{
            int number = Integer.parseInt(string);
            if (number > 0)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }
}
