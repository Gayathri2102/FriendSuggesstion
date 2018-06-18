import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/*
* Generates a map representation to suggest mutual friends
* from a given series of user actions of form "X joins", "X friends Y"
* The Friend.java provides a method:
*  ArrayList<String> compute(Scanner log) : Takes a scanner stream of
*  											user action sentences and 
*  											provides a map representation
* @author  Gayathri Balasubramanian
*/
public class Friend implements Tester {
	
	/*
	* Building entity of the friends map
	*/

	private ArrayList<String> output = new ArrayList<String>();
	private HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
	
	/**
	 * Main method to start the execution using console input
	 * Calls the compute method and prints output
	 */
	public static void main(String[] args) {
		Scanner objScanner = new Scanner(System.in);
		ArrayList<String> printOutput  = new Friend().compute(objScanner);
		
		for (int i = 0; i < printOutput.size(); i++) {
			System.out.println(printOutput.get(i));
		}		
	}
	
	/**
	 * Overriding interface method to compute friendship map
	 * 
	 * @param log Scanner object containing networking sentences
	 * @return ArrayList<String> Each element contains a line of output
	 */
	@Override
	public ArrayList<String> compute(Scanner log) {
		String line = log.nextLine();
		
		while (!line.equals("end")) {
			//Invoke map with the parsed names of people who newly join the network
			if (line.contains("joins"))
			{
				map.put(line.substring(0, line.indexOf(" joins")),new ArrayList<String>());
			}
			else if (line.contains("unfriends"))
			//Invoke mapping and remove people being unfriended from their list
			{
				String node1 = line.substring(0, line.indexOf(" unfriends"));
				String node2 = line.substring(line.indexOf(" unfriends")+11);
				map.get(node1).remove(node2);
				map.get(node2).remove(node1);
			}
			else if (line.contains("friends"))
			//Gets a list added with people joining new to the network and creates suggestion
			{
				String node1 = line.substring(0, line.indexOf(" friends"));
				String node2 = line.substring(line.indexOf(" friends")+9);
				if (0 < node1.compareTo(node2))
				{
					Suggest(node1, map.get(node1), map.get(node2));
					Suggest(node2, map.get(node2), map.get(node1));
				}
				else
				{
					Suggest(node2, map.get(node2), map.get(node1));
					Suggest(node1, map.get(node1), map.get(node2));
				}
				map.get(node1).add(node2);
				map.get(node2).add(node1);
				Collections.sort(map.get(node1));
				Collections.sort(map.get(node2));
			}
			else if (line.contains(" leaves"))
			//Remove user from all the lists when they leave the social neworking site
			{
				String rem_node = line.substring(0, line.indexOf(" leaves"));
				for (String rem_link : map.remove(rem_node))
				{
					map.get(rem_link).remove(rem_node);
				}
			}
			line = log.nextLine();
		}
		return output;
	}
	private void Suggest (String node1, ArrayList<String> frnd1, ArrayList<String> frnd2)
	//Invokes suggestion based on the previous arrays created for every user/node in a map
	{
		boolean before = true;
		for (String sug : frnd2)
		{
			if (frnd1.contains(sug))
			{
				continue;
			}
			else if (before && 0 < node1.compareTo(sug))
			{
				output.add(sug+" and "+node1+" should be friends");
				continue;
			}
			before = false;
			output.add(node1+" and "+sug+" should be friends");
		}
	}
}