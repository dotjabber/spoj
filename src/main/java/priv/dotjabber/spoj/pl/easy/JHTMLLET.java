package priv.dotjabber.spoj.pl.easy;

import java.util.ArrayList;
import java.util.List;

public class JHTMLLET {
	public static class Node {
		public String name;
		public String content;
		public List<Node> children;

		public Node(String n, String c) {
			name = n;
			content = c;
			children = new ArrayList<>();
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	public static List<Node> getNodes(String content) {
		List<Node> top = new ArrayList<>();
		
		while(content.length() > 0) {
			int openIndex = content.indexOf("<");
			int closeIndex = content.indexOf(">");
		
			if(openIndex == -1) {
				top.add(new Node("#", content));
				content = "";
			
			} else if(openIndex > 0) {
				top.add(new Node("#", content.substring(0, openIndex)));
				content = content.substring(openIndex);
				
			} else {
				String nodeName = content.substring(openIndex + 1, closeIndex);
				String openingNode = "<" + nodeName + ">";
				String closingNode = "</" + nodeName + ">";
				
				Node bottom = new Node(nodeName, null);
				
				int contentStart = openingNode.length();
				int contentEnd = content.indexOf(closingNode);
				String nodeContent = content.substring(contentStart, contentEnd);
				
				bottom.children.addAll(getNodes(nodeContent));
				content = content.substring(contentEnd + closingNode.length(), content.length());
				
				top.add(bottom);
			}
		}
		
		return top;
	}
	
	public static void print(List<Node> nodes, int level) {
		String prefix = new String(new char[level]).replace("\0", " ");
		
		for(Node n : nodes) {
			if(n.content == null) {
				System.out.println(prefix + n.name);
				print(n.children, level + 1);
				
			} else {
				System.out.println(prefix + n.content);
			}
		}
	}
	
	public static void main(String[] args) {
		String input = "<html><head><title>To jest tytul</title></head><body>fsdfsdfsd<b>Cos tam 1</b><b>Cos tam 2</b></body></html>";
		
		List<Node> parsed = getNodes(input);
		print(parsed, 0);
	}
}
