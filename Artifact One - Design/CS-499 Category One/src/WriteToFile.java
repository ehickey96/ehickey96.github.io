//CS-499 Category One - Write to File class
//Elijah Hickey
//9.13.21


//Class to enable writing to files
//Utilized by the loginRecorder() method called in the main() function in the driver class
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteToFile {
	private String path;
	private boolean appendToFile = true;
	
	//two choices, one has the appendValue option
	//Might use the appendValue option in a later iteration
	public WriteToFile(String filePath) {
		path = filePath;
	}
	
	public WriteToFile(String filePath, boolean appendValue) {
		path = filePath;
		appendToFile = appendValue;
		
	}
	
	public void writeToFile(String textLine) throws IOException{
		FileWriter write = new FileWriter(path , appendToFile);
		PrintWriter printLine = new PrintWriter(write);
		
		printLine.printf("%s" + "%n", textLine);
		
		printLine.close();
	}
}

