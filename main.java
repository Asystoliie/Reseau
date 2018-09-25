package dico;

public class main {

	public static void main(String[] args) {
		
		OrderedDictionary orderedDictionary = new OrderedDictionary(5);
		//FastDictionary fastDictionary = new FastDictionary(10);
		SortedDictionary1 sortedDictionary1 = new SortedDictionary1(5);
		
		//remplissage
		orderedDictionary.put("1","2");
		orderedDictionary.put("3", "4");
		orderedDictionary.put("5", "6");
		orderedDictionary.put("7", "8");
		orderedDictionary.put("9", "10");
		
		sortedDictionary1.put("a","1");
		sortedDictionary1.put("c", "2");
		sortedDictionary1.put("d", "3");
		sortedDictionary1.put("e", "4");
		sortedDictionary1.print();
		sortedDictionary1.put("f", "5");
		
		
		System.out.println("---- orderedDictionnary tests ----");
		System.out.println("get 1 = " + orderedDictionary.get("1"));
		System.out.println("get 7 = " + orderedDictionary.get("7"));
		orderedDictionary.put("11", "12");
		System.out.println("add 11 12 and get 11 = " + orderedDictionary.get("11"));
		
		System.out.println("---- sortedDictionary1 tests ----");
		System.out.println("get a = " + orderedDictionary.get("a"));
		System.out.println("get e = " + orderedDictionary.get("e"));
		sortedDictionary1.put("b", "6");
		System.out.println("add b 6 and get b = " + sortedDictionary1.get("b"));
		sortedDictionary1.print();
				
	}

}
