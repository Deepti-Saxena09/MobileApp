package AppiumAutomation.GridDynamics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class DataSTORE {

	public static void main(String[] args) {
		
		
	File file = new File("C://Users//Prakarsh gupta//eclipse-workspace//MobileAutomation//src//test//java//resources//data.csv");
	
	try {
		FileWriter outputFile = new FileWriter(file);
		
		CSVWriter writer = new CSVWriter(outputFile);
		
		
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[]{"id", "emp_name", "Department"});
		data.add(new String[] {"1", "Tanu" , "HR"});
		data.add(new String[] {"2", "Kammo", "Technology"});
		
		//data.remove(1);
		//data.removeAll(data);
		writer.writeAll(data);
		
		writer.close();
	}
	
	catch(IOException e) {
	e.printStackTrace();
	
	}

}
}

