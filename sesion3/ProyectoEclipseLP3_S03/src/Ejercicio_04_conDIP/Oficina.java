package Ejercicio_04_conDIP;

import java.util.List;

// Clase de alto nivel que depende solo de abstracciones
public class Oficina {
    private List<Imprimible> dispositivosImpresion;
    private List<Escaneable> dispositivosEscaneo;

    public Oficina(List<Imprimible> dispositivosImpresion, List<Escaneable> dispositivosEscaneo) {
        this.dispositivosImpresion = dispositivosImpresion;
        this.dispositivosEscaneo = dispositivosEscaneo;
    }

    public void realizarTareaImpresion() {
        for (Imprimible imprimible : dispositivosImpresion) {
            imprimible.imprimir();
        }
    }

    public void realizarTareaEscaneo() {
        for (Escaneable escaneable : dispositivosEscaneo) {
            escaneable.escanear();
        }
    }
}