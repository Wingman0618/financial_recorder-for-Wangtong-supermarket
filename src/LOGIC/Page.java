package LOGIC;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.util.Date;
import java.util.ArrayList;

// import itex7.kernel.pdf.PdfDocument; 
// import com.itextpdf.kernel.pdf.PdfWriter; 
// import com.itextpdf.layout.Document; 
// import com.itextpdf.layout.element.Paragraph; 
 
public class Page implements Serializable{
	
	private String pagename;
	private ArrayList<ArrayList<String>> page;
	private double overall;
	private int current;
	private Console console = System.console();
	protected PageOperation operation;

	public void initializePage(){
		this.page = new ArrayList<ArrayList<String>>();
		// initializeLines();
	}

	// public void initializeLines(){	
	// 	//for each line: id,date,abstract,income,expence	
	// 	for(int i = 0; i <16; i++){
	// 		ArrayList<String> line = new ArrayList<String>();
	// 		page.add(line);
	// 	}
	// 	current = 0;
	// }

	public void deleteLine(int id){
		ArrayList<String> l = this.getLine(id);
		if (l == null){
			System.out.println("id not found");
		}
		else{
			this.setLine(l, Integer.toString(id), null, null, null, null);
		}
	}
		//modify line
		// else{
		// 	for(int i = 0; i <16; i++){
		// 		if(page[i].getID() == id){
		// 			String content = console.readLine("Please input the abstract of the line");
		// 			int	income = Integer.parseInt(console.readLine("Please input the income: "));
		// 			int	expense = Integer.parseInt(console.readLine("Please input the expense: "));
		// 			String d = console.readLine("Please input the date like yyyy-mm-dd");
		// 			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 			Date date = null;
		// 			try {
		// 				date = simpleDateFormat.parse(d);
		// 			} catch (ParseException e) {
		// 				System.out.println("please input the correct pattern");
		// 				e.printStackTrace();
		// 			}	
		// 			page[current].setAbstract(content);
		// 			page[current].setDate(date);
		// 			page[current].setExpense(expense);
		// 			page[current].setID(id);
		// 			page[current].setIncome(income);
		// 			return;
		// 		}
		// 	}
		// 	System.out.println("Line not found");
		// }

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
	
	
	public Void savePage(){
		try{
			File f = new File("../Database/Pages/"+this.pagename+".csv");
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

	
	// public void printPage(){
	// 	for(int i = 0; i < 16; i++){
	// 		Line l = page[i];
	// 		if(l.getAbstract().equals("-")){
	// 			break;
	// 		}
	// 		String id = Integer.toString(l.getID());
	// 		String income = Double.toString(l.getIncome());
	// 		String expense = Double.toString(l.getExpense());
	// 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// 		Date d = l.getDate();
	// 		String date = sdf.format(d);
	// 		System.out.println(id + "|"+date + "|"+l.getAbstract() +"|"+ income +"|"+ expense + "\n");
	// 	}
	// }

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