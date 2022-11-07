package encryption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EncryptionProgram {

	private ArrayList<Character> list = new ArrayList<>();
	private ArrayList<Character> shuffledList = new ArrayList<>();
	
	

	public ArrayList<Character> getList() {
		return list;
	}

	public void setList(ArrayList<Character> list) {
		this.list = list;
	}

	public ArrayList<Character> getShuffledList() {
		return shuffledList;
	}

	public void setShuffledList(ArrayList<Character> shuffledList) {
		this.shuffledList = shuffledList;
	}
	
	public EncryptionProgram() {
		char charachter = ' ';
		list.clear();
		shuffledList.clear();
		for (int i = 32; i < 127; i++) {
			list.add(Character.valueOf(charachter));
			charachter++;
		}
		shuffledList = new ArrayList<>(list);
		Collections.shuffle(shuffledList);
	}

	public void newKey() {
		shuffledList = new ArrayList<>(list);
		Collections.shuffle(shuffledList);
	}
	
	public void setKey(String text) throws Exception {
		if(text.length()!=95)
		throw new Exception("Enter the key in a proper format");
		char[] key = text.toCharArray();
		shuffledList.clear();
		for(int i=0;i<key.length;i++) 
			shuffledList.add(key[i]);
	}
	
	public void saveKey(String path) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		for(int i=0;i<list.size();i++)
			bw.append(shuffledList.get(i));
		bw.close();
	} 


	public String encrypt(String message) throws Exception {
		if(message==null || message.isEmpty())
			throw new Exception("Message is empty");
		char[] letters = message.toCharArray();
		for (int i = 0; i < letters.length; i++)
			for (int j = 0; j < list.size(); j++)
				if (letters[i] == list.get(j)) {
					letters[i] = shuffledList.get(j);
					break;
				}
		StringBuilder newMessage = new StringBuilder();
		for(int i=0;i<letters.length;i++)
			newMessage.append(letters[i]);
		String m=newMessage.toString();
		return m;
		

	}

	public String decrypt(String message) throws Exception {
		if(message==null || message.isEmpty())
			throw new Exception("Message is empty");
		char[] letters = message.toCharArray();
		for (int i = 0; i < letters.length; i++)
			for (int j = 0; j < shuffledList.size(); j++)
				if (letters[i] == shuffledList.get(j)) {
					letters[i] = list.get(j);
					break;
				}
		StringBuilder newMessage = new StringBuilder();
		for(int i=0;i<letters.length;i++)
			newMessage.append(letters[i]);
		String m=newMessage.toString();
		return m;

	}
	
	public String readKey(String path) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(path));
		String text = br.readLine();
		br.close();
		return text;
	}
	

}
