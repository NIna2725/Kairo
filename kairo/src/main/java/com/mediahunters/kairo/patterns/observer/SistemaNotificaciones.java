package com.mediahunters.kairo.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class SistemaNotificaciones {
        
    private List<Observador> observadores = new ArrayList<>();

    public void suscribir(Observador o){
        observadores.add(o);
    }

    public void notificar(String mensaje){
        observadores.forEach(o -> o.actualizar(mensaje));
    }

}
