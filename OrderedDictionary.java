package dico;

public class OrderedDictionary extends AbstractDictionary<Object, Object>{

	//Default
	OrderedDictionary(){
		this.tabKeys = new Object[1];
		this.tabVals = new Object[1];
		this.setSize(1);
	}
	
	//Sized
	OrderedDictionary(int taille){
		this.tabKeys = new Object[taille];
		this.tabVals = new Object[taille];
		this.setSize(taille);
	}
	
	void grow(){
		this.setSize(this.getSize() + 1);
		Object tempoKeys[] = new Object[this.getSize()];
		Object tempoVals[] = new Object[this.getSize()];
		
		System.arraycopy(this.tabKeys, 0, tempoKeys, 0, this.getSize()-1);
		System.arraycopy(this.tabVals, 0, tempoVals, 0, this.getSize()-1);
		
		this.tabKeys = new Object[this.getSize()];
		this.tabVals = new Object[this.getSize()];
		
		System.arraycopy(tempoKeys, 0, this.tabKeys, 0, this.getSize());
		System.arraycopy(tempoVals, 0, this.tabVals, 0, this.getSize());
	}
	
	@Override 
	public int indexOf(Object k) {
		for(int i = 0; i < this.getSize(); i++){
			if(this.tabKeys[i] == k)
				return i;
		}
		return -1;
	}

	@Override
	public int newIndexOf(Object key) {
		for(int i = 0; i < this.getSize(); i++){
			if(this.tabKeys[i] == ""){
				return i;
			}
		}
		
		this.grow();
		return this.getSize() - 1;
	}
}
