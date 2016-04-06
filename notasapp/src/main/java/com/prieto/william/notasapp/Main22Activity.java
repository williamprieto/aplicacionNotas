package com.prieto.william.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main22Activity extends AppCompatActivity {


    EditText epExp,epPrac,epProy;
    TextView ptE;
    Button bGuardar;
    Button bLimp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        epExp=(EditText)findViewById(R.id.epExp);
        epPrac=(EditText)findViewById(R.id.epPrac);
        epProy=(EditText)findViewById(R.id.epProy);
        bGuardar=(Button)findViewById(R.id.bguardar);
        bLimp= (Button)findViewById(R.id.blimpiar);

        ptE=(TextView)findViewById(R.id.ptE);//cambiar el idioma de los textos

        Bundle extras= getIntent().getExtras();//PARA PODR RECIBIR LOS DATOS DE LAS OTRAS ACTIVIDADES
//LA VARIABL pExpo,pPract,pProy esta en el activity main
        //se cargan los valores en lo editText
        epExp.setText(String.valueOf(extras.getInt("pExpo")));
        epPrac.setText(String.valueOf(extras.getInt("pPrac")));
        epProy.setText(String.valueOf(extras.getInt("pProy")));

// se obtienen los nuevos datos y se envian
        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double suma=0.0,nue_pExp=0.0,nue_pPrac=0.0,nue_pProy=0.0;



                if( (String.valueOf(epExp.getText()).equals("")) | ( String.valueOf(epPrac.getText()).equals("")) | (String.valueOf(epProy.getText()).equals("")) ){
                         if(ptE.getText().equals("Exposiciones")) {
                             Toast.makeText(getApplicationContext(), "por favor llenar todos los campos", Toast.LENGTH_LONG).show();
                             // tdatosr.setText("por favor llenar todos los campos ");
                         }else{
                             Toast.makeText(getApplicationContext(),"Please fill all spaces", Toast.LENGTH_LONG).show();
                         }
                }else {
                    nue_pExp=Double.parseDouble(epExp.getText().toString());
                    nue_pPrac=Double.parseDouble(epPrac.getText().toString());
                    nue_pProy=Double.parseDouble(epProy.getText().toString());

                    suma = nue_pExp + nue_pPrac + nue_pProy;
                    if (suma == 100.0) {
                        Intent intent = new Intent();
                        intent.putExtra("npExp", epExp.getText().toString());
                        intent.putExtra("npPrac", epPrac.getText().toString());
                        intent.putExtra("npProy", epProy.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                            if(ptE.getText().equals("Exposiciones")) {
                                Toast.makeText(getApplicationContext(), "verificar que la suma de (%)s sea igual a 100", Toast.LENGTH_LONG).show();

                                // tdatosr.setText("por favor llenar todos los campos ");
                            }else{
                                Toast.makeText(getApplicationContext(),"verify that the sum of (%)s equals 100", Toast.LENGTH_LONG).show();
                            }

                    }
                }
            }
        });

        bLimp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epExp.setText("");
                epPrac.setText("");
                epProy.setText("");

            }
        });



    }


}
