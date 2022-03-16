package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class menuAdmin extends AppCompatActivity {
    Button btnsalir, btninsertarPaciente,btneditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

            btnsalir=findViewById(R.id.btnExit);
            btninsertarPaciente=findViewById(R.id.btnAgregarPaciente);
            btneditar=findViewById(R.id.btnEditarDatos);

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences preferences= getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();*/

                finish();

            }
        });
            btninsertarPaciente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(menuAdmin.this, InsertarPaciente.class);
                    startActivity(intent);

                }
            });

               btneditar.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       
                   }
               });



    }





}