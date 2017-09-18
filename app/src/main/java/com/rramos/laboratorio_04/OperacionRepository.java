package com.rramos.laboratorio_04;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RODRIGO on 11/09/2017.
 */

public class OperacionRepository {


    private static OperacionRepository _INSTANCIA= null;
    public static OperacionRepository getInstancia(){
        if(_INSTANCIA==null){
            _INSTANCIA =new OperacionRepository();
        }
        return _INSTANCIA;

    }

    private OperacionRepository(){

    }
    private List<Operacion> operaciones = new ArrayList<>();

    public List<Operacion> getOperaciones(){return operaciones;}

    public void agregarOperacion(Operacion operacion){
        this.operaciones.add(operacion);
    }
}
