package com.shia.practice118;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText customerId, customerName, address, city, postalCode, country;
    Button insert, read, update, delete;

    TextView countText;

    DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        customerId = findViewById(R.id.customerId);
        customerName = findViewById(R.id.customerName);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        postalCode = findViewById(R.id.postalCode);
        country = findViewById(R.id.country);

        insert = findViewById(R.id.insert);
        read = findViewById(R.id.read);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        countText = findViewById(R.id.countText);

        dataBaseManager = new DataBaseManager(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String insertId = customerId.getText().toString();
                String insertName = customerName.getText().toString();
                String insertAddress = address.getText().toString();
                String insertCity = city.getText().toString();
                String insertPostalCode = postalCode.getText().toString();
                String insertCountry = country.getText().toString();

                if (TextUtils.isEmpty(insertId) || TextUtils.isEmpty(insertName) || TextUtils.isEmpty(insertAddress) || TextUtils.isEmpty(insertCity) || TextUtils.isEmpty(insertPostalCode) || TextUtils.isEmpty(insertCountry)){
                    Toast.makeText(getApplicationContext(), "please insert your info", Toast.LENGTH_SHORT).show();
                } else {
                    Person herePerson = new Person();
                    herePerson.customerIdClass = insertId;
                    herePerson.customerNameClass = insertName;
                    herePerson.addressClass = insertAddress;
                    herePerson.cityClass = insertCity;
                    herePerson.postalCodeClass = insertPostalCode;
                    herePerson.countryClass = insertCountry;

                    dataBaseManager.savePerson(herePerson);
                    Toast.makeText(getApplicationContext(), "your info inserted", Toast.LENGTH_SHORT).show();
                    countCustomer();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String readId = customerId.getText().toString();
                Person readPerson = dataBaseManager.customerShowPerson(readId);
                customerName.setText(readPerson.customerNameClass);
                address.setText(readPerson.addressClass);
                city.setText(readPerson.cityClass);
                postalCode.setText(readPerson.postalCodeClass);
                country.setText(readPerson.countryClass);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateIdCustomer = customerId.getText().toString();
                String updateNameCustomer = customerName.getText().toString();
                String updateCityCustomer = city.getText().toString();
                String updateAddressCustomer = address.getText().toString();
                String updatePostalCodeCustomer = postalCode.getText().toString();
                String updateCountryCustomer = country.getText().toString();

                Person updatePersonHere = new Person();
                updatePersonHere.customerIdClass = updateIdCustomer;
                updatePersonHere.customerNameClass = updateNameCustomer;
                updatePersonHere.cityClass = updateCityCustomer;
                updatePersonHere.addressClass = updateAddressCustomer;
                updatePersonHere.postalCodeClass = updatePostalCodeCustomer;
                updatePersonHere.countryClass = updateCountryCustomer;

                dataBaseManager.updateCustomerPerson(updatePersonHere);
            }
        });
        
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteIdCustomer = customerId.getText().toString();
                
                Person deletePersonCustomer = new Person();
                deletePersonCustomer.customerIdClass = deleteIdCustomer;
                
                boolean deleteCustomer = dataBaseManager.deleteCustomerPerson(deletePersonCustomer);
                
                if (deleteCustomer){
                    Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                    countCustomer();
                } else {
                    Toast.makeText(getApplicationContext(), "not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void countCustomer(){
        int setCustomerCount = dataBaseManager.customerCount();
        countText.setText(Integer.toString(setCustomerCount));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}