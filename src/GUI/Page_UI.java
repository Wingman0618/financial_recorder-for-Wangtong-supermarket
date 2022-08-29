package GUI;

import LOGIC.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

import javax.swing.table.*;

public class Page_UI{

    public void visualise(){
        JFrame jf = new JFrame("Page");
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel line = new JPanel();
        JPanel page = new JPanel(new BorderLayout());
        JPanel bottom = new JPanel();

        line.setLayout(null);
        line.setPreferredSize(new Dimension(800, 100));
        bottom.setPreferredSize(new Dimension(800, 40));

        JLabel id = new JLabel("ID number");
        JLabel date = new JLabel("MM/DD");
        JLabel message = new JLabel("Abstract");
        JLabel income = new JLabel("Income");
        JLabel expend = new JLabel("Expend");

        TextField id_TF = new TextField();
        TextField date_TF = new TextField();
        TextField message_TF = new TextField();
        TextField income_TF = new TextField();
        TextField expend_TF = new TextField();
        JButton btn_add = new JButton("add");

        JButton btn_delete = new JButton("delete");
        JButton btn_save = new JButton("save");
        JButton btn_print = new JButton("print");
        JButton btn_new = new JButton("new page");
        JLabel overall = new JLabel("Overall: ");
        bottom.add(btn_delete);
        bottom.add(btn_save);
        bottom.add(btn_new);
        bottom.add(btn_print);
        bottom.add(overall);

        id.setBounds(50, 30, 60, 20);
        date.setBounds(150, 30, 50, 20);
        message.setBounds(325, 30, 50, 20);
        income.setBounds(500, 30, 50, 20);
        expend.setBounds(600, 30, 50, 20);

        id_TF.setBounds(50, 55, 50, 20);
        date_TF.setBounds(150, 55, 50, 20);
        message_TF.setBounds(250, 55, 200, 20);
        income_TF.setBounds(500, 55, 50, 20);
        expend_TF.setBounds(600, 55, 50, 20);
        btn_add.setBounds(700, 55, 50, 20);

        line.add(id);
        line.add(date);
        line.add(message);
        line.add(income);
        line.add(expend);
        line.add(id_TF);
        line.add(date_TF);
        line.add(message_TF);
        line.add(income_TF);
        line.add(expend_TF);
        line.add(btn_add);
        
        String[] columnNames = {"ID number", "Date", "Abstract", "Income", "Expend"};
        Object[][] rowData = {{null,null,null,null,null}};
        DefaultTableModel pageTableM = new DefaultTableModel(rowData, columnNames);
        pageTableM.removeRow(0);
        JTable pageTable = new JTable(pageTableM);
        pageTable.setRowHeight(25);
        //pageTable.getTableHeader()
        pageTable.getColumnModel().getColumn(0).setPreferredWidth(55);
        pageTable.getColumnModel().getColumn(1).setPreferredWidth(55);
        pageTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        pageTable.getColumnModel().getColumn(3).setPreferredWidth(55);
        pageTable.getColumnModel().getColumn(4).setPreferredWidth(55);

        pageTable.getTableHeader().setResizingAllowed(false);
        pageTable.getTableHeader().setReorderingAllowed(false);
        pageTable.setEnabled(false);

        page.add(pageTable.getTableHeader(), BorderLayout.NORTH);
        page.add(pageTable, BorderLayout.CENTER);

        btn_add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Pattern p = Pattern.compile("^[-\\+]?[\\d]*$");
                if(pageTable.getRowCount()>15){
                    JOptionPane.showMessageDialog(null, "page is full, please add new page");
                }else{
                    if(id_TF.getText().equals("")||(p.matcher(id_TF.getText()).matches()==false)){
                        JOptionPane.showMessageDialog(null, "Please input Id number or input invalid");
                    }else if(date_TF.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Please input date or input invalid");
                    }else if(message_TF.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Please input message or input invalid");
                    }else{
                        if(income_TF.getText().equals("")){
                            income_TF.setText("0");
                        }
                        if(expend_TF.getText().equals("")){
                            expend_TF.setText("0");
                        }
                        String data[] = {id_TF.getText(), date_TF.getText(), message_TF.getText(), income_TF.getText(), expend_TF.getText()};
                        pageTableM.addRow(data);
                        id_TF.setText(String.valueOf((Integer.valueOf(id_TF.getText())+1)));
                        date_TF.setText(date_TF.getText());
                        message_TF.setText("");
                        income_TF.setText("");
                        expend_TF.setText("");
                    }
                }
            }
        });

        btn_delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String input = JOptionPane.showInputDialog(null, "Input the ID number: ");
                Pattern p = Pattern.compile("^[-\\+]?[\\d]*$");
                if(!p.matcher(input).matches()||input.equals("")){
                    JOptionPane.showMessageDialog(null, "Input invalid");
                }else{
                    int row = Integer.valueOf(input);
                    pageTable.setEnabled(true);
                    if(row<=pageTable.getRowCount()){
                        for(int n=1; n<5;n++){
                            pageTableM.setValueAt("", row-1, n);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "ID is not found!");
                    }
                    pageTable.setEnabled(false);
                }
            }
        });

        btn_save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Page p = new Page();
                String filename = JOptionPane.showInputDialog(null, "Input the file name: ");
                for(int i=0; i<pageTable.getRowCount(); i++){
                    p.addLine((String)pageTable.getValueAt(i, 0),(String)pageTable.getValueAt(i, 1),(String)pageTable.getValueAt(i, 2),(String)pageTable.getValueAt(i, 3),(String)pageTable.getValueAt(i, 4));
                }
                p.savePage(filename);
            }
        });

        btn_new.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                id_TF.setText("");
                date_TF.setText("");
                message_TF.setText("");
                income_TF.setText("");
                expend_TF.setText("");
                int rowCount = pageTableM.getRowCount();
                for(int i=0; i<rowCount; i++){
                    pageTableM.removeRow(0);
                }
            }
        });

        btn_print.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    pageTable.print();
                }catch(Exception ex){
                    System.out.println("failed");
                }
            }
        });

        jf.add(line, BorderLayout.NORTH);
        jf.add(page, BorderLayout.CENTER);
        jf.add(bottom, BorderLayout.SOUTH);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}