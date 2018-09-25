package dico;

public interface IDictionary <Tkey, Tval>{
	Tval get(Tkey key);
	void put(Tkey key, Tval value);
	boolean isEmpty();
	boolean containsKey(Tkey key);
}
