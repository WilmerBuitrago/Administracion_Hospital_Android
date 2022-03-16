package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InsertarPaciente extends AppCompatActivity {
    SharedPreferences preferences;

    EditText edtidpac,edtnompac,edtapellidopac,edtuserpac,edtclavepac,edtfechapac, edtdireccionpac,edtpactel,edtbuscarpac, id_fk;
    Button btninsertarpaciente, btnbuscarpaciente,btnsalirpaciente;
    TextView id2;
    RequestQueue requestQueue;

    EditarDatosAdmin ob= new EditarDatosAdmin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);


        setContentView(R.layout.activity_insertar_paciente);
        edtidpac=(EditText)findViewById(R.id.edtinsertPAcId);
        edtnompac=(EditText)findViewById(R.id.edtInsertarPacNombre);
        edtapellidopac=(EditText)findViewById(R.id.edtInsertarPacApellido);
        edtuserpac=(EditText)findViewById(R.id.edtInsertarPacUsuario);
        edtclavepac=(EditText)findViewById(R.id.edtInsertarPacClave);
        edtfechapac=(EditText)findViewById(R.id.edtInsertarPacFecha);
        edtdireccionpac=(EditText)findViewById(R.id.edtInsertarDireccion);
        edtpactel=(EditText)findViewById(R.id.edtInsertarPacTelefono);
        edtbuscarpac=(EditText)findViewById(R.id.edtBuscarPacID);
        id2=findViewById(R.id.textView29);
        id_fk=findViewById(R.id.edtid_fk);
        btninsertarpaciente=(Button)findViewById(R.id.btnAgregarPaciente);
        String id_admin=preferences.getString("id_admin_fk",null);
        if (id_admin!=null){
            id_fk.setText(id_admin);
        }
        btnbuscarpaciente=(Button)findViewById(R.id.btnBuscarPaciente);
        btnsalirpaciente= findViewById(R.id.btnVolverins);

        btnsalirpaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences preferences= getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();*/

                finish();

            }
        });



        btninsertarpaciente.setOnClickListener(this::onClick);
        btnbuscarpaciente.setOnClickListener(this::onClick);
    }


    public void onClick(View v){
        int id= v.getId();
        if(id==R.id.btnAgregarPaciente){
            insertar("http://192.168.1.6/hos/InsertarPaciente.php");

        }else if(id==R.id.btnBuscarPaciente){
            buscar();
        }


    }

    private void insertar(String URL){

            //enviar los datos capturados
            StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Se registro el paciente ", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                }
            }){



                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> parametros = new HashMap<String, String>();
                    parametros.put("id_paciente",edtidpac.getText().toString());
                    parametros.put("nombre",edtnompac.getText().toString());
                    parametros.put("apellido",edtapellidopac.getText().toString());
                    parametros.put("direccion",edtdireccionpac.getText().toString());
                    parametros.put("usuario",edtuserpac.getText().toString());
                    parametros.put("clave",edtclavepac.getText().toString());
                    parametros.put("fecha_nac",edtfechapac.getText().toString());
                    parametros.put("telefono",edtpactel.getText().toString());
                    parametros.put("id_admin_fk",id_fk.getText().toString());



                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);



    }

    private void buscar(){
        Intent intent= new Intent(this,EditarPaciente.class);
        intent.putExtra("id",edtbuscarpac.getText().toString().trim());
        startActivity(intent);



    }
}
