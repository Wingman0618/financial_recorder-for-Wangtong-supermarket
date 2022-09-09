package LOGIC;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.BufferedReader;
import java.util.ArrayList;
 
public class Page implements Serializable{
	
	private ArrayList<ArrayList<String>> page = new ArrayList<ArrayList<String>>();
	private double overall;


	public void deleteLine(int id){
		ArrayList<String> l = this.getLine(id);
		if (l == null){
			System.out.println("id not found");
		}
		else{
			this.setLine(l, Integer.toString(id), null, null, null, null);
		}
	}

	public void addLine(String id, String ab, String in, String ex, String dt){
			if(page.size() >= 16){
				System.out.println("Page is full, please save this page and create a new page.");
				return;
			}
			ArrayList<String> l = new ArrayList<String>();
			this.setLine(l, id, ab, in, ex, dt);
			page.add(l);
			overall = overall + Double.parseDouble(in) - Double.parseDouble(ex);
		}
	
	
	public Void savePage(String ppath){
		try{
			File f = new File(ppath);
			f.createNewFile();
			FileWriter fw = new FileWriter(f,true);
			for(int i = 0; i < 16; i++){
				ArrayList<String> l;
				try{
					l = page.get(i);
				}
				catch(IndexOutOfBoundsException e){
					break;
				}
				String id = l.get(0);
				String date = l.get(1);
				String ab = l.get(2);
				String income = l.get(3);
				String expense = l.get(4);
				fw.write(id + ","+ date + ","+ ab +","+ income +","+ expense + "\n");
				fw.flush();
			}
			fw.close();
			System.out.println("Saved");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public Void loadPage(String pagename){
		try{
			FileReader f = new FileReader("../Database/Pages/"+pagename+".csv");
            BufferedReader bf = new BufferedReader(f);
            String line = bf.readLine();
            while(line != null){
				String[] contents = line.split(",");
                int count = 0;
				ArrayList<String> l = new ArrayList<String>();
				this.setLine(l, contents[0], contents[1], contents[2], contents[3], contents[4]);
				page.add(l);
                line = bf.readLine();
            }
			System.out.println("Loaded");
            f.close();		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	public double calculate(){
		return overall;
	}

	public ArrayList<String> getLine(int id){
		for(int i = 0; i <16; i++){
			if(page.get(i).get(0).equals(Integer.toString(id))){
				return page.get(i);
			}
		}
		return null;
	}

	public void setLine(ArrayList<String> l, String id, String ab, String dt,String in, String ex){
		l.add(id);
		l.add(dt);
		l.add(ab);
		l.add(in);
		l.add(ex);
	}

	
}