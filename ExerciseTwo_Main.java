
public class ExerciseTwo_Main {

	public static void main(String[] args){
		
		FileHandler.setFileName(args.length > 0? args[0] : "Default");
		
		Table table = new Table();
		
		while(true){
			System.out.println("\n1. PRINT        5. EDIT VALUE");
			System.out.println("2. SEARCH       6. SORT ASCENDING");
			System.out.println("3. ADD CELL     7. SORT DESCENDING");
			System.out.println("4. EDIT KEY     8. EXIT");
			
			String choice = Helper.askString("What do you want to do? (Enter choice number): ");
			
			switch(choice){
				case "1":
					table.display();
					break;
				case "2":
					table.search();
					break;
				case "3":
					table.addCell();
					break;
				case "4":
					try{
						table.editKey();
					}catch(InputException e){
						System.out.println(e.getMessage());
					}
					break;
				case "5":
					try{
						table.editValue();
					}catch(InputException e){
						System.out.println(e.getMessage());
					}
					break;
				case "6":
					table.sort(true);
					break;
				case "7":
					table.sort(false);
					break;
				case "8":
					System.exit(0);
				default:
					System.out.println("Invalid choice!");
			}

		}
	}
	
}
