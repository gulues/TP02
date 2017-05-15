package grafos;

import java.util.ArrayList;


public class Dijkstra 
{
	
	private int cantVertices; 
	private double dist[] ;
	private double[][] grafo;
	private boolean verticeCamMin[];
	private int [] padres;
	private ArrayList<Integer> lista= new ArrayList<Integer>();
	
	public Dijkstra(GrafoPesado g, int origen) 
	{
		cantVertices=g.cantidadVertices();
		dist = new double[cantVertices];
		grafo=g.getMatriz();
		verticeCamMin= new boolean[cantVertices];
		padres = new int[cantVertices];
		
		for (int i = 0; i < cantVertices; i++) 
		{
			dist [i] = Integer.MAX_VALUE;
			verticeCamMin [i] = false;
		}
		dist [origen]  =  0;
		padres [0] = -1;
		
		
		for (int cont = 0; cont < cantVertices; cont++)
		{
			int indice = (int) minDistancia(dist, verticeCamMin, cantVertices);
			verticeCamMin[indice] = true;
			
			for (int i = 0; i < cantVertices; i++)
				if ( grafo[indice][i] != 0	&& dist[indice] + grafo[indice][i] < dist[i])
				{
					dist[i] = (int) (dist[indice] + grafo[indice][i]);
					padres[i]=indice;
				}
			
		}
		
	}
	
	
	public  double[] getDistanciasMin()
	{
		return  dist; 
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getCaminoMinimo(int s, int t)
	{
		if (s<0 || t<0  || s> padres.length || t >padres.length) 
			throw new IllegalArgumentException("Vertices fuera de rango");
		lista.add(s);
		armarCamino(padres, t);
		return (ArrayList<Integer>) lista.clone();
	}
	
	public void armarCamino(int padres[], int j)
	{
	    if (padres[j]==-1)
	        return;

	    armarCamino(padres, padres[j]);
	   lista.add(j); 
	}	
	
	private double minDistancia(double dist[], boolean verticeCamMin[], int cantVertices) 
	{
		double min = Integer.MAX_VALUE;
		double minIndice = -1;
		for (int v = 0; v < cantVertices; v++)
			if (verticeCamMin[v] == false && dist[v] <= min) 
			{
				min =  dist[v];
				minIndice = v;
			}

		return minIndice;
	}
	
}
