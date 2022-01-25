package com.shia.practice118;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, number;
    Button save, show, update, delete;

    TextView txtCount;

    DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        save = findViewById(R.id.save);
        show = findViewById(R.id.show);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        txtCount = findViewById(R.id.txtCount);

        dataBaseManager = new DataBaseManager(this);

        ultimateCount();
        
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String saveId = number.getText().toString();
                String saveName = name.getText().toString();
                
                if (TextUtils.isEmpty(saveId) || TextUtils.isEmpty(saveName)){
                    Toast.makeText(getApplicationContext(), "insert your info", Toast.LENGTH_SHORT).show();
                } else {
                    Person savePerson = new Person();
                    savePerson.personId = saveId;
                    savePerson.personName = saveName;

                    dataBaseManager.insertPerson(savePerson);
                    Toast.makeText(getApplicationContext(), "your info inserted", Toast.LENGTH_SHORT).show();
                    ultimateCount();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String showId = number.getText().toString();
                Person showPersonHere = dataBaseManager.showPerson(showId);
                name.setText(showPersonHere.personName);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateId = number.getText().toString();
                String updateName = name.getText().toString();

                Person personUpdate = new Person();
                personUpdate.personId = updateId;
                personUpdate.personName = updateName;

                dataBaseManager.updatePerson(personUpdate);
            }
        });
        
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteId = number.getText().toString();
                
                Person personDelete = new Person();
                personDelete.personId = deleteId;
                
                boolean del = dataBaseManager.deletePerson(personDelete);
                
                if (del) {
                    Toast.makeText(getApplicationContext(), "data deleted", Toast.LENGTH_SHORT).show();
                    ultimateCount();
                }else {
                    Toast.makeText(getApplicationContext(), "you don't have any data for deleting", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ultimateCount() {
        int setCount = dataBaseManager.personCount();
        txtCount.setText(Integer.toString(setCount));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("page2").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                return false;
            }
        });
        menu.add("page3").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
                return false;
            }
        });
        menu.add("page4").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MainActivity.this, MainActivity4.class));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}