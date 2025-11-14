package Modelo;

import java.util.List;


public interface IRepositorioResultados {


    void guardarResultado(Resultado resultado);


    List<Resultado> obtenerTodos();


    void cargarDatos();


    void guardarDatos();
}
