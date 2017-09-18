package com.rramos.laboratorio_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class RegistroActivity extends AppCompatActivity {

    private EditText get_monto;
    private Spinner tipo_cuenta_spinner;
    private String elegido="", elegido2="";
    private RadioGroup grupo_ie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        get_monto=(EditText)findViewById(R.id.get_monto);
        tipo_cuenta_spinner=(Spinner)findViewById(R.id.tipo_cuenta_spinner);
        grupo_ie = (RadioGroup)findViewById(R.id.grupo_ie);


        tipo_cuenta_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                elegido=adapterView.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void registrarOperacion(View view) {
        String monto = get_monto.getText().toString();
        OperacionRepository operacionRepository1 = OperacionRepository.getInstancia();
        List<Operacion> operaciones= operacionRepository1.getOperaciones();
        double neto_credito=2000,neto_ahorro=1200,neto_efectivo=120,neto_elegido=0;

        for (Operacion operacion : operaciones){
            if(operacion.getTipo_cuenta().equals("Tarjeta de credito")){
                if(operacion.getTipo().equals("Ingreso")){
                    neto_credito=neto_credito+operacion.getMonto();
                }else{
                    neto_credito=neto_credito-operacion.getMonto();
                }
            }else if(operacion.getTipo_cuenta().equals("Ahorro")){
                if(operacion.getTipo().equals("Ingreso")){
                    neto_ahorro=neto_ahorro+operacion.getMonto();
                }else{
                    neto_ahorro=neto_ahorro-operacion.getMonto();
                }
            }else{
                if(operacion.getTipo().equals("Ingreso")){
                    neto_efectivo=neto_efectivo+operacion.getMonto();
                }else{
                    neto_efectivo=neto_efectivo-operacion.getMonto();
                }

            }

        }

        if (elegido.equals("Tarjeta de credito")){
            neto_elegido=neto_credito;
        }else if(elegido.equals("Ahorro")){
            neto_elegido=neto_ahorro;
        }else{
            neto_elegido=neto_efectivo;
        }

        if(monto.length()==0){
            Toast.makeText(this, "El monto es requerido", Toast.LENGTH_SHORT).show();
            return;
        }
        /*Toast.makeText(this,monto, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,elegido, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, elegido2, Toast.LENGTH_SHORT).show();*/

        int radioButtonId = grupo_ie.getCheckedRadioButtonId();
        if (radioButtonId == -1){
            Toast.makeText(this, "Es necesario que coloque el tipo de operacion", Toast.LENGTH_LONG).show();
            return;
        }else {
            // Individual selected


            if (radioButtonId == R.id.radio_egreso) {
                elegido2 = "Egreso";
                if(Double.parseDouble(monto)>neto_elegido){
                    Toast.makeText(this, "El monto es mayor que el total del elemento seleccionado", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else if (radioButtonId == R.id.radio_ingreso) {
                elegido2 = "Ingreso";
            }

        }
            Operacion operacion= new Operacion(Double.parseDouble(monto),elegido2,elegido);

        OperacionRepository operacionRepository = OperacionRepository.getInstancia();
        operacionRepository.agregarOperacion(operacion);
        finish();


    }


}
