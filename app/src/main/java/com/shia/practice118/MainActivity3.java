package com.shia.practice118;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity3 extends AppCompatActivity {

    LinearLayout linearLayout3;
    TextInputLayout layoutUserName, layoutEmail, layoutPassword;
    EditText editUserName, editEmail, editPassword;
    Button btnRegistration;

    DataBaseManager registrationDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        linearLayout3 = findViewById(R.id.linearLayout3);

        layoutUserName = findViewById(R.id.layoutUserName);
        layoutEmail = findViewById(R.id.layoutEmail);
        layoutPassword = findViewById(R.id.layoutPassword);

        editUserName = findViewById(R.id.editUserName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        btnRegistration = findViewById(R.id.btnRegistration);

        registrationDataBase = new DataBaseManager(this);

        linearLayout3.setOnClickListener(null);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameReg = editUserName.getText().toString();
                String emailReg = editEmail.getText().toString();
                String passReg = editPassword.getText().toString();

                if (TextUtils.isEmpty(userNameReg) || TextUtils.isEmpty(userNameReg)|| TextUtils.isEmpty(userNameReg)){
                    Toast.makeText(getApplicationContext(), "not inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Person insertPersonReg = new Person();
                    insertPersonReg.userNamePerson = userNameReg;
                    insertPersonReg.emailPerson = emailReg;
                    insertPersonReg.passwordPerson = passReg;
                    registrationDataBase.registrationInsert(insertPersonReg);
                    Toast.makeText(getApplicationContext(), "info inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}