import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class JJSSoup{
	
	ArrayList<Integer> dq = new ArrayList<Integer>();
	ArrayList<Integer> q = new ArrayList<Integer>();
	
	public static void main(String args[]){
		JJSSoup js = new JJSSoup();
		StringBuilder sb = js.fileReader("./test/test.js");
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
		dq = new ArrayList<Integer>(SearchWord(sb, "\""));
		q = new ArrayList<Integer>(SearchWord(sb,"'"));
		// remove quotation inside quotation and double quotation inseide quotation
		modifyquote();
		
		for(int i=0;i< dq.size();i+=2){
			System.out.println(sb.substring(dq.get(i), dq.get(i+1)+1));
		}
		for(int i=0;i< q.size();i+=2){
			System.out.println(sb.substring(q.get(i), q.get(i+1)+1));
		}
		
		ArrayList<Integer> semic = new ArrayList<Integer>(SearchWord(sb,";"));
		semic = new ArrayList<Integer>(modifyWord(semic));
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
	/**
	 * This function is remove word inside quotation and double quotation
	 * @param word
	 */
	private ArrayList<Integer> modifyWord(ArrayList<Integer> word){
		int i=0,j=0;
		for(int k=0;k<word.size();k++){
			System.out.println(word.get(k));
		}
		
		
		while(i < word.size() && j < dq.size()){
				if(dq.get(j) < word.get(i) && word.get(i) <dq.get(j+1)){
					word.remove(i);
				}else if(dq.get(j+1)< word.get(i)){
					j+=2;
				}else if(word.get(i) < dq.get(j)){
					i++;
				}
		}
		i=0;
		j=0;
		while(i < word.size() && j < q.size()){
				if(q.get(j) < word.get(i) && word.get(i) <q.get(j+1)){
					word.remove(i);
				}else if(q.get(j+1)< word.get(i)){
					j+=2;
				}else if(word.get(i) < q.get(j)){
					i++;
				}
		}
		for(int k=0;k<word.size();k++){
			System.out.println(word.get(k));
		}
		return word;
	}
	
	/**
	 * This function is remove quotation inside double quotation,
	 * and double quotation inside quotation
	 */
	private void modifyquote() {
		int i=0,j=0;
		while(i < dq.size() && j < q.size()){
			if(dq.get(i) < q.get(j)){
				if(q.get(j)< dq.get(i+1)){
					q.remove(j);
				}else if(dq.get(i+1) < q.get(j)){
					i+=2;
				}
			}else if(q.get(j) < dq.get(i)){
				if(dq.get(i)< q.get(j+1)){
					dq.remove(i);
				}else if(q.get(j+1) < dq.get(i)){
					j+=2;
				}
			}
		}	
	}	
		
}