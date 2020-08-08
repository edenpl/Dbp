package com.example.eden.carteracliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText et_nombre, et_emaill, et_telefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_nombre = (EditText)findViewById(R.id.txt_nombre);
        et_emaill = (EditText)findViewById(R.id.txt_emaill);
        et_telefono = (EditText)findViewById(R.id.txt_telefono);
    }

    //Méotdo para dar de alta
    public void Registrar(View view) {
        Datos admin = new Datos(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();


        String nombre = et_nombre.getText().toString();
        String emaill = et_emaill.getText().toString();
        String telefono = et_telefono.getText().toString();


        if(!nombre.isEmpty() && !emaill.isEmpty() && !telefono.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("nombre", nombre);
            registro.put("emaill", emaill);
            registro.put("precio", telefono);

            BaseDeDatos.insert("registro", null, registro);

            BaseDeDatos.close();
            et_nombre.setText("");
            et_emaill.setText("");
            et_telefono.setText("");


            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }



    }

    //metodo para consultar


    public  void Buscar(View view) {
        Datos admin = new Datos(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();

        if (!nombre.isEmpty()) {
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select emaill, telefono from registro where nombre = " + nombre +"", null);

            if(fila.moveToFirst()){
                et_emaill.setText(fila.getString(0));
                et_telefono.setText(fila.getString(1));
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this,"No existe ", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el nombre", Toast.LENGTH_SHORT).show();
        }

    }

    //Método para consultar

    public void Eliminar(View view){
        Datos admin = new Datos(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();

        if(!nombre.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select emaill, telefono from registro where nombre =" + nombre, null);

            if(fila.moveToFirst()){
                et_emaill.setText(fila.getString(0));
                et_telefono.setText(fila.getString(1));
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this,"No existe", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el nombre", Toast.LENGTH_SHORT).show();
        }


    }

    public  void Modificar(View view){
        Datos admin = new Datos(this, "administracion", null, 1);
        SQLiteDatabase BataDatabase = admin.getWritableDatabase();


        String nombre = et_nombre.getText().toString();
        String emaill = et_emaill.getText().toString();
        String telefono = et_telefono.getText().toString();

        if(!nombre.isEmpty() && !emaill.isEmpty() && !telefono.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("nombre", nombre);
            registro.put("emaill", emaill);
            registro.put("telefono", telefono);

            int cantidad =BataDatabase.update("registro",registro, "nomnre" + nombre, null);
            BataDatabase.insert("registroa", null, registro);

            BataDatabase.close();
            if(cantidad ==1 ){
                Toast.makeText( this," modificado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText( this," modificado correctamente", Toast.LENGTH_SHORT).show();
            }

        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}