import java.util.ArrayList;
import java.util.HashSet;

public class Table {
	
	private int col = 0, row = 0;
	private ArrayList<Row> table = new ArrayList<>();
	private HashSet<Cell> cells = new HashSet<>();
	
	public Table(){
		create();
	}

	public void addCell(){
		try{
			row = Helper.askNumericInput("y");
			Helper.isValidIndex(row, table.size() + 1, "y");
				
			if(row != table.size()){
				col = Helper.askNumericInput("x");
				Helper.isValidIndex(col, table.get(row).getRowSize() + 1, "x");
			}
				
			String key = inputKey();
			String value = Helper.askString("Enter Value: ").trim();
				
			if(row == table.size()){ 
				addRow();
				col = 0;
			}
			addCell(key, value);
			System.out.println("Sucessfully Added Cell!");
		}catch(InputException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void addCell(String key, String value){
		Cell newCell = new Cell(key,value);
		cells.add(newCell);
		table.get(row).addCell(col, newCell);
		saveData();
	}
	
	private void addRow(){
		table.add(new Row());
	}
	
	public void create(){
		String txt = null;
		while(txt == null){
			try{
				txt = FileHandler.readFile();
			}catch(InputException exception){
				System.out.println(exception.getMessage() + "\nSwitching to Default.txt...");
				FileHandler.setFileName("Default");
			}
		}

		String[] lines = txt.split("\n");
		String delimiter = lines[0];
		
		for(int lineIndex = 1; lineIndex < lines.length; lineIndex++){
			String[] cells = lines[lineIndex].split(delimiter.trim());
			if(cells.length % 2 != 0){
				System.out.println("Please check the file used and try again. \nExiting Program...");
				System.exit(0);
			}
			addRow();
			row = lineIndex - 1;
			col = 0;
			for(int index = 0; index < cells.length; index +=2, col++){
				addCell(cells[index].trim(), cells[index+1].trim());
			}
		}
		display();
	}
	
	public void display(){
		System.out.println("");
		for(Row row : table){
			row.display();
			System.out.println("");
		}
	}
	
	public void editKey() throws InputException{
		getTargetCell();
		String key = inputKey();
			
		table.get(row).setCellKey(col, key);
		
		System.out.println(row + "," + col + " key changed into " + table.get(row).getCellKey(col));
		saveData();
	}
	
	public void editValue() throws InputException{	
		getTargetCell();
		
		String value = Helper.askString("Enter New Value: ");
		
		table.get(row).setCellValue(col, value);
		System.out.println(row + "," + col + " value changed into " + table.get(row).getCellValue(col));
		saveData();
	}
	
	public void search(){
		String pattern = Helper.askString("Enter pattern to be searched: ");
		Row row;
		String key, value, keyMsg, valueMsg;
		int keyCount, valueCount;
		
		System.out.println();
		for(int rowIndex = 0; rowIndex < table.size(); rowIndex++){
			row = table.get(rowIndex);
			for(int colIndex = 0; colIndex < table.get(rowIndex).getRowSize(); colIndex++){
				key = row.getCellKey(colIndex);
				value = row.getCellValue(colIndex);
				keyCount = Helper.countPatternOccurence(key, pattern);
				valueCount = Helper.countPatternOccurence(value, pattern);
				keyMsg =  keyCount + (keyCount > 1? " instances" : " instance") + " in Key and ";
				valueMsg =  valueCount + (valueCount > 1? " instances" : " instance") + " in Value";
				
				if(keyCount + valueCount > 0)
					System.out.println(rowIndex + ", " + colIndex + " with " + keyMsg + valueMsg);
			}
		}
	}
	
	public void sort(boolean asc){
		for(Row row : table){
			row.sort(asc);
		}
		
		display();
		saveData();
	}
	
	private void getTargetCell() throws InputException{
		try{
			row = Helper.askNumericInput("y");
			Helper.isValidIndex(row, table.size(), "y");
			
			col = Helper.askNumericInput("x");
			Helper.isValidIndex(col, table.get(row).getRowSize(), "x");
		}catch(InputException e){
			throw e;
		}
		
		System.out.println("(" + row + "," + col + ")");
	}
	
	private String inputKey() throws InputException{
		String key = Helper.askString("Enter New Key: ").trim();
		if(cells.contains(new Cell(key, ""))){
			throw new InputException("Error: Key exist in Table!");
		}
		return key;
	}

	private void saveData(){
		StringBuilder data = new StringBuilder();
		String delimiter = Helper.generateRandomString(5).trim();
		
		data.append(delimiter + System.lineSeparator());
		
		for(int y = 0; y < table.size(); y++){
			Row tableRow = table.get(y);
			for(int x = 0; x < tableRow.getRowSize(); x++){
				data.append((x!=0? delimiter : "") + tableRow.getCellKey(x) + delimiter + tableRow.getCellValue(x));
			}
			data.append(System.lineSeparator());
		}
		
		FileHandler.writeToFile(data.toString());
	}
}
