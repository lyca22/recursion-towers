import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static int caseNumber;
	public static int[] cases;
	public static String text = "";
	
	public static int n;
	public static int tower1;
	public static int tower2;
	public static int tower3;
	public static int[] positions = new int[3];

	public static void main(String[] args) {
		
		try {
			importData("Input.txt");
			executeHanoiTowers();
			exportData("Output.txt");
		} catch (IOException e) {
			System.out.println("An error ocurred. Please make sure you have Input.txt inside the project's folder and you have permission to open the file.");
		}
		
	}

	public static void executeHanoiTowers() {
		for(int i = 0; i < cases.length; i++) {
			tower1 = 0;
			tower2 = 1;
			tower3 = 2;
			positions[0] = cases[i];
			positions[1] = 0;
			positions[2] = 0;
			
			text += showTowers() + "\n";
			hanoiTowers(cases[i], tower1, tower3, tower2);
			text += showTowers() + "\n\n";
		}
	}
	
	public static void hanoiTowers(int ringNumber, int start, int end, int aux) {
		if(ringNumber == 1) {
			positions[start]--;
			positions[end]++;
		}else {
			hanoiTowers(ringNumber-1, start, aux, end);
			text += showTowers() + "\n";
			hanoiTowers(1, start, end, aux);
			text += showTowers() + "\n";
			hanoiTowers(ringNumber-1, aux, end, start);
		}
	}
	
	public static String showTowers() {
		String text = Arrays.toString(positions)
				.replace(",", "")
				.replace("[", "")
				.replace("]", "");
		return text;
	}

	public static void importData(String fn) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fn));
		caseNumber = Integer.parseInt(bufferedReader.readLine());
		cases = new int[caseNumber];
		int line;
		for(int i = 0; i < caseNumber; i++) {
			line = Integer.parseInt(bufferedReader.readLine());
			cases[i] = line;
		}
		bufferedReader.close();
	}
	
	public static void exportData(String fn) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fn));
		bufferedWriter.write(text);
		bufferedWriter.close();
		System.out.println("Data exported to file " + fn);
	}
	
}
