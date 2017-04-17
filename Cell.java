public class Cell implements Comparable<Cell>{
		
	private String key;
	private String value;
		
	public Cell(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public void setKey(String key){
		this.key = key;
	}
		
	public void setValue(String value){
		this.value = value;
	}
		
	public String getKey(){
		return key;
	}
		
	public String getValue(){
		return value;
	}

	@Override
	public int compareTo(Cell e) {
		String thisConcat = (this.key + this.value).toLowerCase();
		String paramConcat = (e.key + e.value).toLowerCase();
		
		if(thisConcat.equals(paramConcat)){
			return this.key.compareTo(e.key);
		}
		
		return thisConcat.compareTo(paramConcat);
	}
		
	@Override
	public boolean equals(Object o){
		Cell c = (Cell) o;
		return this.key.equals(c.getKey()); 
	}
		
	@Override
	public int hashCode(){
		return java.util.Objects.hash(key);
	}
}