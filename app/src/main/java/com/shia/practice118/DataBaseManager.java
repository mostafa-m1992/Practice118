package com.shia.practice118;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String dataBaseName = "dataBase.db";
    private static final int Version = 1;

    //table1 tableName
    private static final String TableName = "tableName";
    private static final String Id = "id";
    private static final String Name = "name";

    //table2 Customers
    private static final String CustomerTable = "customer";
    private static final String CustomerId = "customerId";
    private static final String CustomerName = "customerName";
    private static final String Address = "address";
    private static final String City = "city";
    private static final String PostalCode = "postalCode";
    private static final String Country = "country";

    //table3 registration
    private static final String RegistrationTable = "registrationTable";
    private static final String UserName = "userName";
    private static final String Email = "email";
    private static final String Password = "password";

    public DataBaseManager(@Nullable Context context) {
        super(context, dataBaseName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String myQuery = " CREATE TABLE " + TableName + " ( " + Id + " INTEGER PRIMARY KEY, " + Name + " VARCHAR); ";
        sqLiteDatabase.execSQL(myQuery);

        String customQuery = " CREATE TABLE " + CustomerTable + " ( " + CustomerId + " INTEGER PRIMARY KEY, " + CustomerName + " VARCHAR, " + Address + " VARCHAR, " + City + " VARCHAR, " + PostalCode + " INTEGER, " + Country + " VARCHAR); ";
        sqLiteDatabase.execSQL(customQuery);

        String registrationQuery = " create table " + RegistrationTable + " ( " + UserName + " varchar, " + Email + " varchar, " + Password + " unique ); ";
        sqLiteDatabase.execSQL(registrationQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertPerson(Person insertPerson){
        SQLiteDatabase insertDataBase = this.getWritableDatabase();
        ContentValues insertContentValue = new ContentValues();

        insertContentValue.put(Id, insertPerson.personId);
        insertContentValue.put(Name, insertPerson.personName);

        insertDataBase.insert(TableName, null, insertContentValue);
        insertDataBase.close();
    }

    public Person showPerson(String showPersonId){
        Person showPersonObject = new Person();
        SQLiteDatabase showDataBase = this.getReadableDatabase();

        String getQuery = " select * from " + TableName + " where " + Id + " = " +showPersonId;
        Cursor showCursor = showDataBase.rawQuery(getQuery, null);

        if (showCursor.moveToFirst()){
            showPersonObject.personName = showCursor.getString(1);
        }
        return showPersonObject;
    }

    public void updatePerson(Person updatePerson){
        SQLiteDatabase updateDataBase = this.getWritableDatabase();
        ContentValues updateContentValues = new ContentValues();

        updateContentValues.put(Id, updatePerson.personId);
        updateContentValues.put(Name, updatePerson.personName);

        updateDataBase.update(TableName, updateContentValues, Id + " = ? ", new String[]{String.valueOf(updatePerson.personId)});
    }

    public boolean deletePerson(Person dPerson){
        SQLiteDatabase deleteDataBase = this.getWritableDatabase();

        long deleteResult = deleteDataBase.delete(TableName, Id + " = ? ", new String[]{String.valueOf(dPerson.personId)});

        if (deleteResult == 0)
            return false;
        else
            return true;
    }

    public int personCount() {
        String countQuery = "select * from " + TableName;

        SQLiteDatabase countDataBase = this.getReadableDatabase();

        Cursor countCursor = countDataBase.rawQuery(countQuery, null);

        int countResult = countCursor.getCount();
        return countResult;
    }

    //mainActivity2

    public int customerCount(){
        String customerCountQuery = " select * from " + CustomerTable;

        SQLiteDatabase customerCountDataBase = this.getReadableDatabase();

        Cursor customerCountCursor = customerCountDataBase.rawQuery(customerCountQuery, null);

        int customerResult = customerCountCursor.getCount();
        return customerResult;
    }

    public void savePerson(Person objectPerson){
        SQLiteDatabase saveDataBase = this.getWritableDatabase();
        ContentValues saveContentValue = new ContentValues();

        saveContentValue.put(CustomerId, objectPerson.customerIdClass);
        saveContentValue.put(CustomerName, objectPerson.customerNameClass);
        saveContentValue.put(Address, objectPerson.addressClass);
        saveContentValue.put(City, objectPerson.cityClass);
        saveContentValue.put(PostalCode, objectPerson.postalCodeClass);
        saveContentValue.put(Country, objectPerson.countryClass);

        saveDataBase.insert(CustomerTable, null, saveContentValue);
        saveDataBase.close();
    }

    public Person customerShowPerson(String strId){
        Person customerShowPersonObject = new Person();
        SQLiteDatabase customerShowDataBase = this.getReadableDatabase();

        String customerShowQuery = " select * from " + CustomerTable + " where " + CustomerId + " = " + strId;

        Cursor customerShowCursor = customerShowDataBase.rawQuery(customerShowQuery, null);
        if (customerShowCursor.moveToFirst()){
            customerShowPersonObject.customerNameClass = customerShowCursor.getString(1);
            customerShowPersonObject.addressClass = customerShowCursor.getString(2);
            customerShowPersonObject.cityClass = customerShowCursor.getString(3);
            customerShowPersonObject.postalCodeClass = customerShowCursor.getString(4);
            customerShowPersonObject.countryClass = customerShowCursor.getString(5);
        }
        return customerShowPersonObject;
    }

    public void updateCustomerPerson(Person updatePersonCustomer){
        SQLiteDatabase updateCustomerDataBase = this.getWritableDatabase();
        ContentValues updateCustomerContentValues = new ContentValues();

        updateCustomerContentValues.put(CustomerId, updatePersonCustomer.customerIdClass);
        updateCustomerContentValues.put(CustomerName, updatePersonCustomer.customerNameClass);
        updateCustomerContentValues.put(Address, updatePersonCustomer.addressClass);
        updateCustomerContentValues.put(City, updatePersonCustomer.cityClass);
        updateCustomerContentValues.put(PostalCode, updatePersonCustomer.postalCodeClass);
        updateCustomerContentValues.put(Country, updatePersonCustomer.countryClass);

        updateCustomerDataBase.update(CustomerTable, updateCustomerContentValues, CustomerId + " = ? ", new String[]{String.valueOf(updatePersonCustomer.customerIdClass)});
    }

    public boolean deleteCustomerPerson(Person dcPerson){
        SQLiteDatabase deleteCustomerDataBase = this.getWritableDatabase();

        long customerDeleteResult = deleteCustomerDataBase.delete(CustomerTable, CustomerId + " = ? ", new String[]{String.valueOf(dcPerson.customerIdClass)});

        if (customerDeleteResult == 0)
            return false;
        else
            return true;
    }

    public void registrationInsert(Person regInPerson){
        SQLiteDatabase regInDataBase = this.getWritableDatabase();
        ContentValues regInContentValues = new ContentValues();

        regInContentValues.put(UserName, regInPerson.userNamePerson);
        regInContentValues.put(Email, regInPerson.emailPerson);
        regInContentValues.put(Password, regInPerson.passwordPerson);

        regInDataBase.insert(RegistrationTable, null, regInContentValues);
        regInDataBase.close();
    }
}
