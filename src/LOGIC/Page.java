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
		String[] l = this.getLine(id);
		if (l == null){
			System.out.println("id not found");
		}
		else{
			return;
		}
	}

	public void addLine(String[] line){
		//line:[date, id, abstract, income, expence, overall]
		ArrayList<String> l = new ArrayList<String>();
		this.setLine(l, line);
		page.add(l);
		}
	
	
	public Void savePage(String ppath){
		try{
			File f = new File(ppath);
			f.createNewFile();
			FileWriter fw = new FileWriter(f,false);
			for(int i = 0; i < 16; i++){
				ArrayList<String> l;
				try{
					l = page.get(i);
				}
				catch(IndexOutOfBoundsException e){
					break;
				}
				String date = l.get(0);
				String id = l.get(1);
				String ab = l.get(2);
				String income = l.get(3);
				String expense = l.get(4);
				String overall = l.get(5);
				fw.write(date + ","+ id + ","+ ab +","+ income +","+ expense + "," + overall + "\n");
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

	public Void loadPage(String pathname){
		try{
			FileReader f = new FileReader(pathname);
            BufferedReader bf = new BufferedReader(f);
            String line = bf.readLine();
            while(line != null){
				String[] contents = line.split(",");
				ArrayList<String> l = new ArrayList<String>();
				this.setLine(l, contents);
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

	public String[] getLine(int ln){
		String[] line = new String[6];
		ArrayList<String> l;
		try{
			l = page.get(ln);
		}
		catch(IndexOutOfBoundsException e){
			return null;			
		}
		//line:[date, id, abstract, income, expence, overall]
		line[0] = l.get(0);
		line[1] = l.get(1);
		line[2] = l.get(2);
		line[3] = l.get(3);
		line[4] = l.get(4);
		line[5] = l.get(5);
		return line;
	}

	public void setLine(ArrayList<String> l, String[] line){
		//line:[date, id, abstract, income, expence, overall]
		l.add(line[0]);
		l.add(line[1]);
		l.add(line[2]);
		l.add(line[3]);
		l.add(line[4]);
		l.add(line[5]);
	}

	
}