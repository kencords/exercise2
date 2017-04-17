import java.util.Collections;
import java.util.LinkedList;

public class Row{

	private LinkedList<Cell> cells = new LinkedList<>();
	
	public void addCell(int index, Cell cell){
		cells.add(index, cell);
	}
		
	public void display(){
		for(int index = 0; index < cells.size(); index++){
			System.out.print("(" + cells.get(index).getKey() + " , " + cells.get(index).getValue() + ")   " );
		}
	}
	
	public void sort(boolean asc){
		if(asc){
			Collections.sort(cells);
			return;
		}
		Collections.sort(cells, Collections.reverseOrder());
	}
	
	public void setCellKey(int index, String key){
		cells.get(index).setKey(key);
	}
	
	public String getCellValue(int index){
		return cells.get(index).getValue();
	}
	
	public void setCellValue(int index, String value){
		cells.get(index).setValue(value);
	}
	
	public String getCellKey(int index){
		return cells.get(index).getKey();
	}
	
	public int getRowSize(){
		return cells.size();
	}
}