/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectingCities;

import gray.adts.graph.Vertex;
import gray.adts.graph.WeightedAdjMatrixGraph;
import gray.misc.Pair;
import gray.misc.Tuple;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Zachary Pratt Gibbs
 */
public class Backend {
    
    private Scanner scr;
    private WeightedAdjMatrixGraph<String> data;
    private int[] distances;
    private Pair<String>[] connections;
    
    public Backend()
    {
        scr = new Scanner(System.in);
        data = new WeightedAdjMatrixGraph();
        distances = new int[17];
        connections = new Pair[17];
        connections[0] = new Pair<>("SEA","SFR");
        distances[0] = 808;
        connections[1] = new Pair<>("SEA","CHI");
        distances[1] = 2060;
        connections[2] = new Pair<>("SFR","LA");
        distances[2] = 414;
        connections[3] = new Pair<>("SFR","MIL");
        distances[3] = 2257;
        connections[4] = new Pair<>("LA","LV");
        distances[4] = 414;
        connections[5] = new Pair<>("LA","DAL");
        distances[5] = 1440;
        connections[6] = new Pair<>("LV","CHI");
        distances[6] = 1780;
        connections[7] = new Pair<>("PHO","MIL");
        distances[7] = 1771;
        connections[8] = new Pair<>("OKC","MIN");
        distances[8] = 792;
        connections[9] = new Pair<>("DAL","MIN");
        distances[9] = 949;
        connections[10] = new Pair<>("DAL","NOR");
        distances[10] = 571;
        connections[11] = new Pair<>("DAL","NYC");
        distances[11] = 1614;
        connections[12] = new Pair<>("MIN","NYC");
        distances[12] = 1217;
        connections[13] = new Pair<>("MIL","WDC");
        distances[13] = 811;
        connections[14] = new Pair<>("CHI","NOR");
        distances[14] = 948;
        connections[15] = new Pair<>("CHI","MIA");
        distances[15] = 1423;
        connections[16] = new Pair<>("NYC","WDC");
        distances[16] = 237;
        
        data.addVertex(new Vertex<>("SEA"));
        data.addVertex(new Vertex<>("SFR"));
        data.addVertex(new Vertex<>("LA"));
        data.addVertex(new Vertex<>("LV"));
        data.addVertex(new Vertex<>("PHO"));
        data.addVertex(new Vertex<>("OKC"));
        data.addVertex(new Vertex<>("DAL"));
        data.addVertex(new Vertex<>("MIN"));
        data.addVertex(new Vertex<>("MIL"));
        data.addVertex(new Vertex<>("CHI"));
        data.addVertex(new Vertex<>("NOR"));
        data.addVertex(new Vertex<>("NYC"));
        data.addVertex(new Vertex<>("WDC"));
        data.addVertex(new Vertex<>("MIA"));
        
        for(int index = 0; index < 17; index++)
        {
            data.addEdge(new Vertex<>(connections[index].getFirstElement()), 
                    distances[index], new Vertex<>(connections[index].getSecondElement()));
        }
    }
    public boolean mainMenu()
    {
        System.out.println("City Connection");
        System.out.println("Choose and option by typing the"
                + " corresponding number and pressing enter:");
        System.out.println("1. Show Connecting Cities");
        System.out.println("2. Get Shortest Path");
        System.out.println("3. Exit");
        int choice = scr.nextInt();
        switch(choice)
        {
            case 1:
                this.showConnections();
                break;
            case 2:
                this.getShortestPath();
                break;
            case 3:
                System.out.println("Goodbye!!!");
                return false;
            default:
                System.out.println("Invalid input restarting.");
                return true;
        }
        return true;
    }
    public void showConnections()
    {
        System.out.println("City to City Connections");
        System.out.println("\nCity\tCity\tDistance");
        for(int index = 0; index < 17; index++)
        {
            System.out.println(connections[index].getFirstElement()+"\t"+
                   connections[index].getSecondElement()+"\t"+distances[index]);
        }
    }
    public void getShortestPath()
    {
        System.out.println("Shortest path between two cities!");
        System.out.println("Enter the first city:");
        String src = scr.next();
        System.out.println("Enter the second city:");
        String dest = scr.next();
        Tuple<ArrayList<Vertex<String>>,Double> path;
        try
        {
            path = data.minimalPath(new Vertex<>(src), new Vertex<>(dest));
            if(path.getSecondElement() == -1)
            {
                System.out.println("Path doesn't exist.");
            }
            else
            {
                System.out.println("Path from " + src+ " to " + dest);
                System.out.println("From\tTo");
                for(int index = 0; index < path.getFirstElement().size()-1; index++)
                {
                    System.out.println(path.getFirstElement().get(index).getLabel()
                           + "\t" + path.getFirstElement().get(index+1).getLabel());
                }
                System.out.println("Total Distance: "+path.getSecondElement());
            }
        }
        catch(Exception e)
        {
            System.out.println("Invalid Input");
        }  
    }
}
