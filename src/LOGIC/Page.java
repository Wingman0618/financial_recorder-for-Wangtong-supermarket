package LOGIC;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.util.Date;

public class Page{
	
	private String pagename;
	private int id;
	private Line[] page;
	private double overall;
	private int current;
	private Console console = System.console();
	protected PageOperation operation;
	
	public void initializePage(){
		this.pagename = console.readLine("Please input the name of the page: ");
		initializeLine();
	}

	public void initializeLine(){
		this.page = new Line[16];
		for(int i = 0; i <16; i++){
			page[i] = new Line();
		}
		current = 0;
	}

	public void modifyPage(int id, PageOperation operation){
		if(operation == PageOperation.DELETE){
			for(int i = 0; i <16; i++){
				if(page[i].getID() == id){
					page[i] = new Line();
					page[i].setID(id);
					return;
				}
			}
			System.out.println("Line not found");
		}
		else{
			for(int i = 0; i <16; i++){
				if(page[i].getID() == id){
					String content = console.readLine("Please input the abstract of the line");
					int	income = Integer.parseInt(console.readLine("Please input the income: "));
					int	expense = Integer.parseInt(console.readLine("Please input the expense: "));
					String d = console.readLine("Please input the date like yyyy-mm-dd");
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = null;
					try {
						date = simpleDateFormat.parse(d);
					} catch (ParseException e) {
						System.out.println("please input the correct pattern");
						e.printStackTrace();
					}	
					page[current].setAbstract(content);
					page[current].setDate(date);
					page[current].setExpense(expense);
					page[current].setID(id);
					page[current].setIncome(income);
					return;
				}
			}
			System.out.println("Line not found");
		}
	}

	public void modifyPage(PageOperation operation){
		if(operation == PageOperation.ADD){
			int id;
			if(current >= 16){
				System.out.println("Page is full, please save this page and create a new page.");
				return;
			}
			else if (current == 0){
				id = Integer.parseInt(console.readLine("Please input the id of the first line: "));
			}
			else{
				String o = console.readLine("the current id is "+ Integer.toString(page[current-1].getID()+1)+", do you want to modify it? y/n");
				if(o.equals("y")){ 
					id = Integer.parseInt(console.readLine("Please input the id of the line: "));
				}
				else{
					id = page[current-1].getID() + 1;
				}
			}
			String content = console.readLine("Please input the abstract of the line");
			int	income = Integer.parseInt(console.readLine("Please input the income: "));
			int	expense = Integer.parseInt(console.readLine("Please input the expense: "));
			String d = console.readLine("Please input the date like yyyy-mm-dd");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = simpleDateFormat.parse(d);
			} catch (ParseException e) {
				System.out.println("please input the correct pattern");
				e.printStackTrace();
			}	
			page[current].setAbstract(content);
			page[current].setDate(date);
			page[current].setExpense(expense);
			page[current].setID(id);
			page[current].setIncome(income);
			current++;
		}
		else{
			try{
				File f = new File("../Database/Pages/"+this.pagename+".txt");
				f.createNewFile();
				FileWriter fw = new FileWriter(f,true);

				for(int i = 0; i < 16; i++){
					Line l = page[i];
					if(l.getAbstract() == null){
						break;
					}
					String id = Integer.toString(l.getID());
					String income = Double.toString(l.getIncome());
					String expense = Double.toString(l.getExpense());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date d = l.getDate();
					System.out.println(d);
					String date = sdf.format(d);
					fw.write(id + "|"+date + "|"+l.getAbstract() +"|"+ income +"|"+ expense + "\n");
					fw.flush();
				}
				System.out.println("Saved");
				fw.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	

	public void printPage(){

	}

	public double calculate(){
		double c = 0;
		for(int i = 0; i< 16; i++){
			if(page[i] == null){
				break;
			}
			c = c + page[i].getIncome() - page[i].getExpense();
		}
		return c;
	}

	public Line getLine(int id){
		for(int i = 0; i <16; i++){
			if(page[i].getID() == id){
				return page[i];
			}
		}
		return null;
	}

	public void setLine(Line l){

	}
}