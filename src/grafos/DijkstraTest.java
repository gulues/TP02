package grafos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class DijkstraTest {

	@Test
	public void distancias() 
	{
		GrafoPesado grafo = crearGrafo();
		Dijkstra d = new Dijkstra(grafo, 0);
		double[] compDis = new double[] { 0.0, 7.0, 9.0, 20.0, 20.0, 11.0 };
		System.out.println(compDis.toString());
		double[] dis = d.getDistanciasMin();
		assertTrue(Arrays.equals(compDis, dis));

	}


	@Test
	public void caminoMinmo()
	{
		GrafoPesado grafo = crearGrafo();
		Dijkstra d = new Dijkstra(grafo,0);
		ArrayList<Integer> cam = d.getCaminoMinimo(0, 4);
		System.out.println(cam.toString());
		Integer[] compCam = new Integer[] {0,2,5,4};
		assertArrayEquals(cam.toArray(),(compCam));
	}

	private GrafoPesado crearGrafo() 
	{
		GrafoPesado grafo = new GrafoPesado(6);
		grafo.agregarArista(0, 1, 7);
		grafo.agregarArista(0, 2, 9);
		grafo.agregarArista(0, 5, 14);
		grafo.agregarArista(1, 3, 15);
		grafo.agregarArista(1, 2, 10);
		grafo.agregarArista(2, 3, 11);
		grafo.agregarArista(2, 5, 2);
		grafo.agregarArista(3, 4, 6);
		grafo.agregarArista(4, 5, 9);
		//grafo.agregarArista(3, 6, 9);
		
		return grafo;
	}


}
