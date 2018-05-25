package com.example.zhangj0585.mycontactapp1;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
            Toast.makeText(MainActivity.this,"Success- contact inserted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Failed- contact not inserted",Toast.LENGTH_LONG).show();
        }
    }
    public void viewData(View view){
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp","MainActivity: viewData: received cursor " + res.getCount());
        if (res.getCount() == 0){
            showMessage("Error", "No data found is database");
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            //Append res column 0,1,2,3 to the buffer,delimited by "\n"
            buffer.append("ID: "+res.getString(0));
            buffer.append(("\n"));
            buffer.append("Name: "+res.getString(1));
            buffer.append(("\n"));
            buffer.append("Address: "+res.getString(2));
            buffer.append(("\n"));
            buffer.append("Phone Number: "+res.getString(3));
            buffer.append(("\n"));
        }
        Log.d("MyContactApp","MainActivity: viewData: assembled StringBuffer ");
        showMessage("Data",buffer.toString());

    }

    public void showMessage(String title, String message) {
        Log.d("MyContactApp","MainActivity: viewData: alert dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
