package com.prieto.william.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText eExp,ePrac,eProy,eNota;
    TextView tExp,tPrac,tProy,tE;
    Button bCalc;
    Button bLimp;

   // String exp,pra,pro;

    static double valor_exp=0.0;
    static double valor_prac=0.0;
    static double valor_proy=0.0;
    static int cont_validar=0;
    static int validar=0;
    //porcentajes actuales
     int por_exp=15;
     int por_prac=50;
     int por_proy=35;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //creando las vaibales para poder utilizar los edittext
        eExp= (EditText)findViewById(R.id.eExp);
        ePrac= (EditText)findViewById(R.id.ePrac);
        eProy= (EditText)findViewById(R.id.eProy);
        eNota= (EditText)findViewById(R.id.eNotaF);
        bCalc= (Button)findViewById(R.id.bcalcular);
        bLimp= (Button)findViewById(R.id.blimpiar);
        //asignamos una opcion al boton calcular
        tE=(TextView)findViewById(R.id.tE);
//------------------colocando los porcentajes--------------------//
      tExp=(TextView)findViewById(R.id.tExpo);
      tPrac=(TextView)findViewById(R.id.tPrac);
      tProy=(TextView)findViewById(R.id.tProy);
//-----------------fin de colocando los porcentajes---------------------//
        tExp.setText(String.valueOf(por_exp));
        tPrac.setText(String.valueOf(por_prac));
        tProy.setText(String.valueOf(por_proy));
        por_exp=Integer.parseInt(String.valueOf(por_exp));
        por_prac=Integer.parseInt(String.valueOf(por_prac));
        por_proy=Integer.parseInt(String.valueOf(por_proy));
 //-----------------------------------------------------------------------
//-----------recuperando los valores de los text view----------
            if(savedInstanceState != null){

                tExp.setText(savedInstanceState.getString("exposiciontex"));
                tPrac.setText(savedInstanceState.getString("practicatex"));
                tProy.setText(savedInstanceState.getString("proyectotex"));

                por_exp=Integer.parseInt(savedInstanceState.getString("exposiciontex"));
                por_prac=Integer.parseInt(savedInstanceState.getString("practicatex"));
                por_proy=Integer.parseInt(savedInstanceState.getString("proyectotex"));

            }

//-----------recuperando los valores de los text view----------
        bCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nota;
                DecimalFormat df = new DecimalFormat("0.0");
                if( (String.valueOf(eExp.getText()).equals("")) | ( String.valueOf(ePrac.getText()).equals("")) | (String.valueOf(eProy.getText()).equals("")) ){
                        if(tE.getText().equals("Exposiciones")){
                            Toast.makeText(getApplicationContext(),"por favor llenar todos los campos", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Please fill all spaces", Toast.LENGTH_LONG).show();
                        }

                   // tdatosr.setText("por favor llenar todos los campos ");
                }else{

                validar=validar_campos();

                 if (validar==0){
                    nota = valor_exp* por_exp / 100 +
                            valor_prac * por_prac / 100 +
                            valor_proy * por_proy / 100;
//campoDolar.setText(df.format(cantidad* 1.3117));
                    String nota1=df.format(nota);
                    eNota.setText(String.valueOf(nota1));
               }else {
                     if (tE.getText().equals("Exposiciones")) {
                         Toast.makeText(getApplicationContext(), "Nota invalida rango de 0.0 a 5.0 verificar campos", Toast.LENGTH_LONG).show();
                         //Toast.makeText(this, "Nota invalida rango de 0.0 a 5.0",Toast.LENGTH_SHORT).show();
                     }else{
                         Toast.makeText(getApplicationContext(), "Note invalidates range of 0.0 to 5.0 verify spaces", Toast.LENGTH_LONG).show();
                     }
                }
                }
            }
        });

        bLimp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eExp.setText("");
                ePrac.setText("");
                eProy.setText("");
                eNota.setText("");

            }
        });


    }
 //------------validar ------------------


public int validar_campos (){
     valor_exp=Double.parseDouble(eExp.getText().toString());
     valor_prac=Double.parseDouble(ePrac.getText().toString());
     valor_proy=Double.parseDouble(eProy.getText().toString());
     if( (valor_exp >5.0)||(valor_prac >5.0 )||(valor_proy>5.0 ) ){
          cont_validar=1;
      }else{
         cont_validar=0;
     }

   return cont_validar;
 }

 //---------fin validar------------------
//---------------BOTON DE MENU-------------------------
    //creacion el menú configurar
    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//recurso
        return super.onCreateOptionsMenu(menu);
    }
    //dandolo la funcionalidad al menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.mConfigurar){
           // Toast.makeText(this, "Presionó configurar",Toast.LENGTH_SHORT).show();
        //EL INTENT PERMITE COMPARTIR INFORMACION ENTRE ACTIVIDADES
            Intent intent = new Intent(this,Main22Activity.class);
            intent.putExtra("pExpo", por_exp);//esta es la variable que hace la conexion manda
            intent.putExtra("pPrac", por_prac);
            intent.putExtra("pProy", por_proy);

            //startActivity(intent);
            startActivityForResult(intent, 1234);
        }

        return super.onOptionsItemSelected(item);
    }
//obteniendo los nuevos datos los cuales fueron modificados en el main 22
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String exp,pra,pro;
        if (requestCode==1234 && resultCode==RESULT_OK){
            exp=data.getExtras().getString("npExp");
            pra=data.getExtras().getString("npPrac");
            pro=data.getExtras().getString("npProy");


            por_exp=Integer.parseInt(exp);
            por_prac=Integer.parseInt(pra);
            por_proy=Integer.parseInt(pro);
            tExp.setText(String.valueOf(por_exp));
            tPrac.setText(String.valueOf(por_prac));
            tProy.setText(String.valueOf(por_proy) );

            //Toast.makeText(MainActivity.this,"Nuevos datos  :Exposiciones"+exp +"Practicas"+pra+"Proyecto"+pro,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("exposiciontex", (String) tExp.getText());
        outState.putString("practicatex", (String) tPrac.getText());
        outState.putString("proyectotex", (String) tProy.getText());
        //-------------------valores en los edittext

        super.onSaveInstanceState(outState);
    }
}
