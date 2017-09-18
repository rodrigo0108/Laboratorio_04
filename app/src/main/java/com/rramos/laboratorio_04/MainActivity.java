package com.rramos.laboratorio_04;

import android.content.Intent;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView total_ahorro,total_credito,total_efectivo;
    private static final int REGISTER_FORM_REQUEST = 100;
    private ProgressBar proporsion;
    private PieChart proporsion2;
    private int impr1,impr2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total_ahorro = (TextView)findViewById(R.id.total_ahorro);
        total_credito = (TextView)findViewById(R.id.total_credito);
        total_efectivo = (TextView)findViewById(R.id.total_efectivo);
        proporsion= (ProgressBar)findViewById(R.id.proporsion);
        proporsion2=(PieChart)findViewById(R.id.proporsion2);





    }

    public void agregarMonto(View view) {
        startActivityForResult(new Intent(this, RegistroActivity.class), REGISTER_FORM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        OperacionRepository operacionRepository = OperacionRepository.getInstancia();
        List<Operacion> operaciones= operacionRepository.getOperaciones();
        double neto_credito=2000,neto_ahorro=1200,neto_efectivo=120;
        double neto_ingresos=0,neto_egresos=0,neto_total=0,pro1,pro2;

        for (Operacion operacion : operaciones){

            //--------------------

            if(operacion.getTipo().equals("Ingreso")){
                neto_ingresos=neto_ingresos+operacion.getMonto();
            }else{
                neto_egresos=neto_egresos+operacion.getMonto();
            }
            //--------------------

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
        neto_total=neto_ingresos+neto_egresos;
        pro1=(neto_ingresos/neto_total)*100;
        pro2=(neto_egresos/neto_total)*100;
        int covert1=(int)pro1;
        int covert2=(int)pro2;

        impr1=covert1;
        impr2=covert2;
        proporsion.setProgress(covert1);

        total_ahorro.setText(String.valueOf(neto_ahorro));
        total_credito.setText(String.valueOf(neto_credito));
        total_efectivo.setText(String.valueOf(neto_efectivo));


        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(impr1, "Ingreso"));
        entries.add(new PieEntry(impr2, "Egreso"));
        PieDataSet pieDataSet = new PieDataSet(entries, "");
        PieData pieData = new PieData(pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        proporsion2.setData(pieData);
        proporsion2.animateY(3000);



    }







}
