package model.data_structures;

import java.util.ArrayList;

import edu.princeton.cs.algs4.CC;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;

/*************************************************************************
 * Codigo basado en la implementación hecha en el libro algoritmics
 * https://github.com/kevin-wayne/algs4
 ************************************************************************/
public class grafoNoDirigido <K,V>
{

	private EdgeWeightedGraph grafo;
	private SeparateChainingHashST<K, Integer> llaveAEntero;
	private SeparateChainingHashST<Integer, K> enteroALlave;
	private SeparateChainingHashST<K, V> llaveAInfoVertex;
	
	private int V=0;
	
	public grafoNoDirigido(int tamano )
	{
		grafo= new EdgeWeightedGraph(tamano);
		llaveAEntero= new SeparateChainingHashST<K, Integer>();
		llaveAInfoVertex= new SeparateChainingHashST<K, V>();
	}
	
	public void addEdge(K from, K to, double peso)
	{
		if(!llaveAEntero.contains(from))
		{
			llaveAEntero.put(from,V );
			enteroALlave.put(V, from);

			V++;
		}
		if(!llaveAEntero.contains(to))
		{
			llaveAEntero.put(to,V );
			enteroALlave.put(V, to);

			V++;
		}
		int fromEntero= llaveAEntero.get(from);
		int toEntero= llaveAEntero.get(from);
		
		grafo.addEdge(new Edge(fromEntero, toEntero, peso));
	}

	public V getInfoVertex(K key)
	{
		return llaveAInfoVertex.get(key);
	}
	
	public void	addVertex(K key,V info)
	{
		llaveAInfoVertex.put(key, info);
		if(!llaveAEntero.contains(key))
		{
			llaveAEntero.put(key,V );
			enteroALlave.put(V, key);
			V++;
		}
	}
	
	public Iterable<K> getCC(K key)
	{
		CC cc= new CC(grafo);
		ArrayList<K> lista= new ArrayList<K>();
	    
		for(int v=0; v< grafo.V(); v++)
		{
			if(cc.id(v)==cc.id(llaveAEntero.get(key)))
			{
				lista.add(enteroALlave.get(v));
			}
		}
		return lista;
	}
	
	public Iterable<K> adj(K key)
	{
		Iterable<Edge> a=grafo.adj(llaveAEntero.get(key));	
		
		ArrayList<K> resultado= new ArrayList<K>();
		for(Edge e:a)
		{
			int v=e.other(llaveAEntero.get(key));
			resultado.add(enteroALlave.get(v));
		}
		return resultado;
	}
}
