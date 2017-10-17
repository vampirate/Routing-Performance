import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeSet;


public class PathFinder {
	
	public HashMap<String, String> findPath (Graph g, String source, String dest, String routingScheme) {
		//TODO
		if (routingScheme == "SHP") {
			
		} else if (routingScheme == "LLP") {
			
		}
		
		return findPathSDP(g, source, dest); //SDP
	}
	
	public HashMap<String, String> findPathSDP(Graph g, String source, String dest) {
		HashMap<String,Integer> nodeCosts = new HashMap<String,Integer>();
		HashMap<String, String> prevNode = new HashMap<String,String>();
		HashMap<String, ArrayList<Link>> nodeList = new HashMap<String,ArrayList<Link>>(g.getNodes());
		
		nodeCosts.put(source, 0);
		prevNode.put(source, null);
		
		while (!nodeList.isEmpty()) {
			 String cur = getMinimum(nodeList, nodeCosts);
			 LinkedList<Link> neighbours = new LinkedList<Link>(nodeList.get(cur));
			 
			 nodeList.remove(cur);
			 
			 while (!neighbours.isEmpty()) {
				 Link n = neighbours.removeFirst();
				 String neighbourNode = n.otherEnd(cur);
				 
				 if (nodeCosts.containsKey(neighbourNode)) {
					 if (nodeCosts.get(neighbourNode) > nodeCosts.get(cur)+n.getDelay()) {
						 nodeCosts.put(neighbourNode, nodeCosts.get(cur)+n.getDelay());
						 prevNode.put(neighbourNode, cur);
					 }
				 } else {					 
					 nodeCosts.put(neighbourNode, nodeCosts.get(cur)+n.getDelay());
					 prevNode.put(neighbourNode, cur);
				 }
				 
			 }
		}
		return prevNode;
	}
	
	private String getMinimum(HashMap<String, ArrayList<Link>> nodeList, HashMap<String, Integer> nodeCosts) {
		String minimum = null;
		int minCost = Integer.MAX_VALUE;
		for (String key : nodeList.keySet()) {
			if (nodeCosts.containsKey(key) && nodeCosts.get(key)<minCost) {
				minimum = key;
				minCost = nodeCosts.get(key);
			}
		}
		return minimum;
	}
	
	
	
}
