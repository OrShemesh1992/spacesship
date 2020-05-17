package spacesship;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Or Shemesh
 *
 */
public class CSV {
	public static void write(String s) {

		try (PrintWriter writer = new PrintWriter(new FileWriter("out.csv",true))) {
			StringBuilder sb = new StringBuilder();
			sb.append(s);
			sb.append('\n');
			writer.write(sb.toString());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
}
}
