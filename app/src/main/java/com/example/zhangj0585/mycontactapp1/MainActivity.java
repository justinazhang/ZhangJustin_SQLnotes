package com.example.zhangj0585.mycontactapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb= new DatabaseHelper(this);
        editName = findViewById(R.id.editText_Name);
        editAddress = findViewById(R.id.editText_Address);
        editNumber = findViewById(R.id.editText_Number);
        Log.d("MyContactApp","MainActivity: instantiated DataBaseHelper");
    }

    public void addData(View view){
        Log.d("MyContactApp","MainActivity: Add contact button passed");
        boolean isInserted=myDb.insertData(editName.getText().toString(),editAddress.getText().toString(),editNumber.getText().toString());
        if (isInserted){
            Toast.makeText(MainActivity.this,"Success- contact inserted",Toast.LENGTH_LONG);
        }
        else{
            Toast.makeText(MainActivity.this,"Failed- contact not inserted",Toast.LENGTH_LONG);
        }
    }
}
