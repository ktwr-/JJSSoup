import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class JJSSoup{
	
	ArrayList<Integer> dq = new ArrayList<Integer>();
	ArrayList<Integer> q = new ArrayList<Integer>();
	
	public static void main(String args[]){
		JJSSoup js = new JJSSoup();
		StringBuilder sb = js.fileReader("./test/sample.js");
		sb = js.InsertLinebreak(sb);
		
	}
	
	public StringBuilder fileReader(String filename){
		StringBuilder sb = new StringBuilder("");
		try{
			BufferedReader bf = new BufferedReader(new FileReader(filename));
			String line;
			while((line = bf.readLine()) != null){
				sb.append(line + System.getProperty("line.separator"));
			}
			bf.close();
		}catch(Exception e){
			System.err.println("No such file or directory");
			System.exit(0);
		}
		return sb;
	}
	/**
	 * insert line break after ; ,{ and }
	 * @param sb source code
	 * @return modify source code
	 */
	private StringBuilder InsertLinebreak(StringBuilder sb){
		// find " and ' not \' \"
		System.out.println(sb.length());
		int tmp=0;
		// Srearch double quotation and quotation
		dq = SearchWord(sb, "\"");
		q = SearchWord(sb,"'");
		// remove quotation inside quotation and double quotation inseide quotation
		
		return sb;
		
	}
	
	private ArrayList<Integer> SearchWord(StringBuilder sb,String word){
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		int i = sb.indexOf(word);
		while(i != -1){
			tmp.add(i);
			i++;
			i = sb.indexOf(word, i);
		}
		return tmp;
	}
	
	private void modifyAL() {
		
	}
}