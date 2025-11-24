package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {

    private List<Resultado> historialPersistente = new ArrayList<>();
    private static final String NOMBRE_ARCHIVO = "historial_ruleta.dat";

    public RepositorioArchivo() {
        cargarDatos();
    }

    @Override
    public void guardarResultado(Resultado resultado) {
        historialPersistente.add(0, resultado);
        // Opcional: guardar inmediatamente después de cada jugada
        // guardarDatos();
    }

    @Override
    public List<Resultado> obtenerTodos() {
        return historialPersistente;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            historialPersistente = (List<Resultado>) ois.readObject();
            System.out.println("Repositorio Archivo: Datos cargados desde " + NOMBRE_ARCHIVO);
        } catch (FileNotFoundException e) {
            System.out.println("Repositorio Archivo: No se encontro archivo previo. Se creara uno nuevo");
            historialPersistente = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Repositorio Archivo: Error al leer el archivo. Se usara historial vacio " + e.getMessage());
            historialPersistente = new ArrayList<>();
        }
    }

    @Override
    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            oos.writeObject(historialPersistente);
            System.out.println("Repositorio archivo: Datos guardados en " + NOMBRE_ARCHIVO);
        } catch (IOException e) {
            System.err.println("Repositorio archivo: Error al guardar los datos " + e.getMessage());
        }
    }
}
