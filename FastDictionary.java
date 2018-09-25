package dico;

public class FastDictionary extends AbstractDictionary<Object, Object>{

	//Default
	FastDictionary(){
		this.tabKeys = new String[4];
		this.tabVals = new String[4];
		this.setSize(4);
	}
	
	//Sized
	FastDictionary(int taille){
		this.tabKeys = new String[taille];
		this.tabVals = new String[taille];
		this.setSize(taille);
	}
	
	int sizeNow(){
		int sum = 0;
		for(int i = 0; i < this.getSize(); i++)
			if(this.tabKeys[i] != "")
				sum++;
		return sum;
	}
		
	void grow(){
		this.setSize(this.getSize() * 2);
		Object tempoKeys[] = new Object[this.getSize()];
		Object tempoVals[] = new Object[this.getSize()];
		
		System.arraycopy(this.tabKeys, 0, tempoKeys, 0, this.getSize()/2);
		System.arraycopy(this.tabVals, 0, tempoVals, 0, this.getSize()/2);
		
		this.tabKeys = new Object[this.getSize()];
		this.tabVals = new Object[this.getSize()];
		
		System.arraycopy(tempoKeys, 0, this.tabKeys, 0, this.getSize());
		System.arraycopy(tempoVals, 0, this.tabVals, 0, this.getSize());
	}
	
	@Override
	public int indexOf(Object k) {
		int hash = k.hashCode();
		hash = hash % this.getSize();
		
		if(tabKeys[hash] == k)
			return hash;
		else{
			for(int i = hash + 1; i < this.getSize(); i++){
				if(tabKeys[i] == k)
					return i;
			}
		}
		return -1;
	}

	@Override
	public int newIndexOf(Object key) {
		if(this.sizeNow() < 3/4 * this.getSize()){
			grow();
		}
		
		int hash = key.hashCode();
		hash = hash % this.getSize();
		
		if(tabKeys[hash] == "")
			return hash;
		else{
			for(int i = hash + 1; i < this.getSize(); i++){
				if(tabKeys[i] == "")
					return i;
			}
		}
		return 1;
	}
}
