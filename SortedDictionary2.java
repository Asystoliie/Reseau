package dico;

public class SortedDictionary2 extends AbstractDictionary<Comparable, Object>{
	//default
		SortedDictionary2(){
			this.tabKeys = new Comparable[1];
			this.tabVals = new Object[1];
			this.setSize(1);
		}
		
		//sized
		SortedDictionary2(int taille){
			this.tabKeys = new Comparable[taille];
			this.tabVals = new Object[taille];
			this.setSize(taille);
		}

		//solution1
		@Override
		int indexOf(Comparable k) {
			for(int i = 0; i < this.getSize(); i++){
				if(this.tabKeys[i].compareTo(k) == 0)
					return i;
			}
			return -1;
		}
		
		int sizeNow(){
			int sum = 0;
			for(int i = 0; i < this.getSize(); i++)
				if(this.tabKeys[i] != null)
					sum++;
			return sum;
		}
		
		void grow(){
			this.setSize(this.getSize() + 1);
			Comparable tempoKeys[] = new Comparable[this.getSize()];
			Object tempoVals[] = new Object[this.getSize()];
			
			System.arraycopy(this.tabKeys, 0, tempoKeys, 0, this.getSize()-1);
			System.arraycopy(this.tabVals, 0, tempoVals, 0, this.getSize()-1);
			
			this.tabKeys = new Comparable[this.getSize()];
			this.tabVals = new Object[this.getSize()];
			
			System.arraycopy(tempoKeys, 0, this.tabKeys, 0, this.getSize());
			System.arraycopy(tempoVals, 0, this.tabVals, 0, this.getSize());
		}

		@Override
		int newIndexOf(Comparable key) {	
			if(this.sizeNow() == this.getSize()){
				grow(); //+1
			}
			
			if(this.tabKeys[0] == null){ //tableau vide
				return 0;
			}
			
			for(int i = 0; i < this.getSize() - 1; i++){
				System.out.println("i = " + i);
				if(this.tabKeys[i] != null && this.tabKeys[i+1] == null){
					System.out.println("return spécial i = " + (i+1));
					return i + 1;
				}
				else if(this.tabKeys[i].compareTo(key) <= 0 && this.tabKeys[i+1].compareTo(key) >= 0){
					
					System.out.println("key = " + key);
					System.out.println(this.tabKeys[i] + " / " + this.tabKeys[i+1]);
					System.out.println(this.tabKeys[i].compareTo(key) + " / " + this.tabKeys[i+1].compareTo(key));
					System.arraycopy(this.tabKeys, i+1, this.tabKeys, i+2, this.getSize()-(i+2));
					System.arraycopy(this.tabVals, i+1, this.tabVals, i+2, this.getSize()-(i+2));
					System.out.println("return standard i = " + (i+1));
					return i + 1;
				}
			}
			return -1;
		}
}
