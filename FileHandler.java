import java.io.*;

public class FileHandler {
	
	private static String fileName;
	
	public static String readFile() throws InputException{
		String data = "";
		try(Reader reader = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
	        BufferedReader bReader = new BufferedReader(reader);) {
			
			String s;
			while((s = bReader.readLine()) != null){
				data +=s + System.lineSeparator();
			}

        } catch(IOException exception) {
        	throw new InputException(exception.getMessage());
        }
		return data;
		//replace("\uFEFF", ""); //remove UTF8 BOM;
	}
	
	public static void writeToFile(String data){
		try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileName, false), "UTF-8");
			 BufferedWriter bWriter = new BufferedWriter(writer);) {
			
			bWriter.write(data);
			
		} catch (IOException exception) {
			System.out.println(exception.getMessage() + "\nExiting Program...");
            System.exit(0);
		}	
	}
	
	public static void setFileName(String name){
		fileName = name + ".txt";
		System.out.println(fileName);
	}

}
