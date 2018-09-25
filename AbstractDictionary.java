package dico;
                                                      
public abstract class AbstractDictionary<Tkey, Tval> implements IDictionary<Tkey, Tval>{
	protected Tkey tabKeys[];
	protected Tval tabVals[];
	private int size;

	abstract int indexOf(Tkey k);
	abstract int newIndexOf(Tkey key);
	
	public Tval get(Tkey key){
		int i = this.indexOf(key);
		if (i != -1)
			return tabVals[i];
		return null;
	}
	
	public void put(Tkey key, Tval value){
		int i = this.newIndexOf(key);
		this.tabKeys[i] = key;
		this.tabVals[i] = value;
	}
	
	public boolean isEmpty(){
		if(tabKeys.length == 0)
			return true;
		return false;
	}
	
	public boolean containsKey(Tkey key){
		if(this.indexOf(key) != -1)
			return true;
		return false;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public void print(){
		for(int i = 0; i < this.getSize(); i++){
			System.out.print(tabKeys[i] + " ");
			System.out.print(tabVals[i]);
			System.out.println();
		}
	}
}
