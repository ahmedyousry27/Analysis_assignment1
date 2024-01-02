import java.util.*;

class Graph1 {
    private Map<Integer, List<Integer>> graph = new HashMap<>();

    public Map<Integer, List<Integer>> getGraph() {
		return graph;
	}

	public void setGraph(Map<Integer, List<Integer>> graph) {
		this.graph = graph;
	}

	public void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>());

    }

    public void dfs(int node, boolean[] visited, List<Integer> order) {
        String [] color = new String[graph.size()+1];
        for (int i=0;i<color.length;i++)
        {
        	color[i]="white";
        }
        for (int u : graph.keySet())
        {
        	if (color[u]=="white")
        	{
        		DFS_Visit(u,color);
        	}
        }

        System.out.println();
    }
    
    public void DFS_Visit(int u,String[]color)
    {
        color[u]="grey";
        List<Integer> adjs= graph.getOrDefault(u, Collections.emptyList());
        Collections.sort(adjs);
        for (int adj: adjs)
        {
        	if (color[adj]=="white")
        	{
        		DFS_Visit(adj, color);
        	}
        }
        
       color[u]="black";
       System.out.print(u+ " ");
    	
    }

    public void bfs(int start) {
    	String [] color= new String [graph.size()+1];
    	for(int i =0 ; i<color.length;i++)
    	{
    		color[i]="white";
    	}
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start]="grey";
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            
           List<Integer> adjs= graph.getOrDefault(node, Collections.emptyList());
           Collections.sort(adjs);
           for (int adj : adjs)
           {
        	   if (color[adj]=="white")
        	   {
        		   color[adj]="grey";
        		   queue.add(adj);
        	   }
           }
           color[node]="black";

                };
        
        System.out.println();
    }

    public void findCycles(int node, boolean[] visited, Stack<Integer> stack, boolean[] inStack, List<List<Integer>> cycles) {
        visited[node] = true;
        stack.push(node);
        inStack[node] = true;
        List<Integer> adjs = graph.getOrDefault(node, Collections.emptyList());
        for (int adj:adjs)
        {
        	if (!visited[adj])
        	{
        		findCycles(adj, visited, stack, inStack, cycles);
        	}
        	else if (inStack[adj])
        	{
        		List<Integer> cycle = new ArrayList<>(stack.subList(stack.indexOf(adj), stack.size()));
        		cycle.add(adj);
        		cycles.add(cycle);
        	}
        }
        stack.pop();
        inStack[node]=false;
    }

    public void printCycles() {
        boolean[] visited = new boolean[graph.size() + 1];
        boolean[] inStack = new boolean[graph.size() + 1];
        List<List<Integer>> cycles = new ArrayList<>();

         for (int node : graph.keySet())
         {
        if (!visited[node]) {
                findCycles(node, visited, new Stack<>(), inStack, cycles);
            }
        };

        if (cycles.isEmpty()) {
            System.out.println("No cycles in the graph.");
        } else {
            System.out.println("Cycles in the graph:");
            for (List<Integer> cycle : cycles) {
                System.out.println(cycle);
            }
        }
    }

    public boolean isBipartite() {
        int[] visited = new int[graph.size() + 1];
        Arrays.fill(visited, -1);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});  
        visited[1] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int color = current[1];

         List<Integer> adjs=  graph.getOrDefault(node, Collections.emptyList());
         for (int adj: adjs)
         {
        	 if (visited[adj]==-1)
        	 {
        		visited[adj]=1-color;
        		queue.add(new int [] {adj,1-color});
        	 }
         }
        
           List<Integer> adjs1= graph.getOrDefault(node, Collections.emptyList());
           for (int adj :adjs)
           {
        	   if (visited[adj] != -1 && visited[adj] == visited[node])
        	   {
                 System.out.println("The graph is not bipartite.");
                 System.exit(0);
        	   }
           }

        }

        System.out.println("The graph is bipartite.");
        return true;
    }
}

public class Graph {
    public static void main(String[] args) {
        Graph1 graph = new Graph1();
    //    int[][] edges = {{1, 3}, {1, 4}, {2, 1}, {2, 3}, {3, 4}, {4, 1}, {4, 2}};
        int[][] edges = {{1,4},{2,5},{3,6}};

        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        

        System.out.println("DFS:");
        graph.dfs(1, new boolean[graph.getGraph().size() + 1], new ArrayList<>());
        System.out.println("\nBFS:");
        graph.bfs(1);
        System.out.println("\nCycles:");
        graph.printCycles();
        graph.isBipartite();
    }


}
