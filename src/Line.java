import java.util.Date;

public class Line{

    int id;
    Date date;
    String summary;
    double income;
    double expense;

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getAbstract(){
        return summary;
    }

    public void setAbstract(String ab){
        this.summary = ab;
    }
    
    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
    public double getIncome(){
        return income;
    }

    public void setIncome(double income){
        this.income = income;
    }
    
    public double getExpense(){
        return expense;
    }

    public void setExpense(double expense){
        this.expense = expense;
    }

}