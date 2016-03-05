package crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Crawl_John {

	public static void main(String args[]) throws Exception
	{
		ArrayList<File> files = new ArrayList<File>();
		String directoryname = "D:/Neil/wiki-small/wiki-small";
		Crawl_John f = new Crawl_John();
		f.listf(directoryname, files);
		
		
	}

	@SuppressWarnings("unchecked")
	public void listf(String directoryname, ArrayList<File> files)
			throws Exception {
		

		// get all the files from a directory
		JSONObject obj = new JSONObject();
		File directory = new File(directoryname);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				
				files.add(file);
				
				obj.put("path", files);
				
				File f = new File("D:/Neil/linkshw3.json");

				BufferedWriter file1 = new BufferedWriter(new FileWriter(f, true));
				try {

					ObjectMapper mapper = new ObjectMapper();
					file1.write(mapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(obj));
					System.out.println(mapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(obj));

				} catch (IOException e) {
					e.printStackTrace();

				} finally {
					file1.flush();
					file1.close();
					
				}
				files.remove(file);
				
				
				
				
			} else if (file.isDirectory()) {
				listf(file.getAbsolutePath(), files);
			}
		}
		
	}
}
