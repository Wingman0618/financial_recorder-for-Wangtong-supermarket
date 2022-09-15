package GUI;

import LOGIC.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.regex.Pattern;
import java.text.MessageFormat;

import javax.swing.table.*;

import java.lang.Object;

public class Page_UI{

    String session;

    Double valueOfINCOME=0.00;
    Double valueOfEXPEND=0.00;
    Double valueOfTOTAL=0.00;
    Double valueOftotalIncome=0.00;
    Double valueOftotalExpend=0.00;
    Double valueOftotal=0.00;
    int index = 1;

    public void visualise(){
        session = JOptionPane.showInputDialog(null, "请输入年份: ");
        String dir = "../Database/Pages/" + session + "/";
        File Dir = new File("../Database/Pages/" + session);
        Dir.mkdir();
        File check = new File(dir + Integer.toString(index) + ".csv");
        Boolean exist = check.exists();
        while(exist){
            index++;
            check = new File(dir + Integer.toString(index) + ".csv");
            exist = check.exists();
        }
        JFrame jf = new JFrame("账目表");
        jf.setSize(800, 655);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel line = new JPanel();
        JPanel page = new JPanel(new BorderLayout());
        JPanel statistics_1 = new JPanel();
        JPanel statistics_2 = new JPanel();
        JPanel bottom = new JPanel();

        line.setLayout(null);
        line.setPreferredSize(new Dimension(800, 100));
        statistics_1.setPreferredSize(new Dimension(800, 25));
        statistics_2.setPreferredSize(new Dimension(800, 25));
        bottom.setPreferredSize(new Dimension(800, 40));

        JLabel id = new JLabel("货记编号");
        JLabel date = new JLabel("月/日");
        JLabel message = new JLabel("摘要");
        JLabel income = new JLabel("收入");
        JLabel expend = new JLabel("支出");

        JLabel INCOME = new JLabel("总计收入: "+valueOfINCOME);
        JLabel EXPEND = new JLabel("总计支出: "+String.valueOf(valueOfEXPEND));
        JLabel TOTAL = new JLabel("总计："+String.valueOf(valueOfTOTAL));
        JLabel totalIncome = new JLabel("累计收入: "+String.valueOf(valueOftotalIncome));
        JLabel totalExpend = new JLabel("累计支出: "+String.valueOf(valueOftotalExpend));
        JLabel total = new JLabel("累计总计: "+String.valueOf(valueOftotal));

        TextField id_TF = new TextField();
        TextField date_TF = new TextField();
        TextField message_TF = new TextField();
        TextField income_TF = new TextField();
        TextField expend_TF = new TextField();
        JButton btn_add = new JButton("添加");

        JButton btn_delete = new JButton("删除");
        JButton btn_save = new JButton("另存本页至...");
        JButton btn_print = new JButton("打印");
        JButton btn_nextPage = new JButton("下一页");
        JButton btn_lastPage = new JButton("上一页");
        JButton btn_save1 = new JButton("保存本页");
        
        statistics_1.add(INCOME);
        statistics_1.add(EXPEND);
        statistics_1.add(TOTAL);
        statistics_2.add(totalIncome);
        statistics_2.add(totalExpend);
        statistics_2.add(total);

        bottom.add(btn_delete);
        bottom.add(btn_save1);
        bottom.add(btn_lastPage);
        bottom.add(btn_nextPage);
        bottom.add(btn_print);
        bottom.add(btn_save);

        date.setBounds(60, 30, 60, 20);
        id.setBounds(150, 30, 50, 20);
        message.setBounds(335, 30, 50, 20);
        income.setBounds(510, 30, 50, 20);
        expend.setBounds(610, 30, 50, 20);

        date_TF.setBounds(50, 55, 50, 20);
        id_TF.setBounds(150, 55, 50, 20);
        message_TF.setBounds(250, 55, 200, 20);
        income_TF.setBounds(500, 55, 50, 20);
        expend_TF.setBounds(600, 55, 50, 20);
        btn_add.setBounds(680, 55, 80, 20);

        line.add(date);
        line.add(id);
        line.add(message);
        line.add(income);
        line.add(expend);
        line.add(date_TF);
        line.add(id_TF);
        line.add(message_TF);
        line.add(income_TF);
        line.add(expend_TF);
        line.add(btn_add);
        
        String[] columnNames = {"日期", "货记编号", "摘要", "收入", "支出", "结存"};
        Object[][] rowData = {{null,null,null,null,null,null}};
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

        //pageTable.setPreferredSize(new Dimension(800, 475));
        //JScrollPane scrollPane = new JScrollPane(pageTable);

        page.add(pageTable.getTableHeader(), BorderLayout.NORTH);
        page.add(pageTable, BorderLayout.CENTER);

        btn_add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Pattern p = Pattern.compile("^[-\\+]?[\\d]*$");
                if(pageTable.getRowCount()>15){
                    JOptionPane.showMessageDialog(null, "本页已满，请添加新页");
                }else{
                    if(id_TF.getText().equals("")||(p.matcher(id_TF.getText()).matches()==false)){
                        JOptionPane.showMessageDialog(null, "请输入货计编号");
                    }else if(date_TF.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "请以mm/dd格式输入日期");
                    }else if(message_TF.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "请输入摘要内容");
                    }else{
                        if(income_TF.getText().equals("")){
                            income_TF.setText("0");
                        }
                        if(expend_TF.getText().equals("")){
                            expend_TF.setText("0");
                        }
                        valueOfINCOME = valueOfINCOME + Double.valueOf(income_TF.getText());
                        valueOfEXPEND = valueOfEXPEND + Double.valueOf(expend_TF.getText());
                        valueOfTOTAL = valueOfINCOME - valueOfEXPEND;
                        String data[] = {date_TF.getText(), 
                            id_TF.getText(), 
                            message_TF.getText(), 
                            income_TF.getText(), 
                            "-"+expend_TF.getText(), 
                            Double.toString(Double.valueOf(income_TF.getText()) - Double.valueOf(expend_TF.getText()))};
                        pageTableM.addRow(data);
                        valueOftotalIncome = valueOftotalIncome + Double.valueOf(income_TF.getText());
                        valueOftotalExpend = valueOftotalExpend + Double.valueOf(expend_TF.getText());
                        valueOftotal = valueOftotalIncome - valueOftotalExpend;

                        INCOME.setText("总计收入: "+ String.valueOf(valueOfINCOME));
                        EXPEND.setText("总计支出: "+ String.valueOf(valueOfEXPEND));
                        TOTAL.setText("总计: "+ String.valueOf(valueOfTOTAL));
                        totalIncome.setText("累计收入: "+ String.valueOf(valueOftotalIncome));
                        totalExpend.setText("累计支出: "+ String.valueOf(valueOftotalExpend));
                        total.setText("累计总计: "+ String.valueOf(valueOftotal));

                        id_TF.setText(String.valueOf((Integer.valueOf(id_TF.getText())+1)));
                        date_TF.setText("");
                        message_TF.setText("");
                        income_TF.setText("");
                        expend_TF.setText("");
                    }
                }
            }
        });

        btn_delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String input = JOptionPane.showInputDialog(null, "请输入欲删除条目所在行数");
                Pattern p = Pattern.compile("^[-\\+]?[\\d]*$");
                if(!p.matcher(input).matches()||input.equals("")){
                    JOptionPane.showMessageDialog(null, "输入非法");
                }else{
                    int row = Integer.valueOf(input);
                    pageTable.setEnabled(true);
                    if(row<=pageTable.getRowCount()){
                        for(int n=0; n<6;n++){
                            if(n == 1) continue;
                            pageTableM.setValueAt("", row-1, n);
                        }
                        pageTableM.setValueAt("已删除", row-1, 2);
                    }else{
                        JOptionPane.showMessageDialog(null, "找不到对象QAQ");
                    }
                    pageTable.setEnabled(false);
                }
            }
        });

        btn_save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Page p = new Page();
                String filename = JOptionPane.showInputDialog(null, "请输入文件名: ");
                for(int i=0; i<15; i++){
                    try{
                        String l[] = {(String)pageTable.getValueAt(i, 0),//date
                            (String)pageTable.getValueAt(i, 1),//id
                            (String)pageTable.getValueAt(i, 2),//abstract
                            (String)pageTable.getValueAt(i, 3),//income
                            (String)pageTable.getValueAt(i, 4),//expence
                            (String)pageTable.getValueAt(i, 5),//expence
                            //overall
                        };
                        p.addLine(l);
                    }
                    catch(Exception exception){
                        String l[] = {" ", " ", " ", " ", " ", " "};
                        p.addLine(l);
                    }
                }
                int result = 0;
                String path = null;
                JFileChooser fileChooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView(); 
                fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
                fileChooser.setDialogTitle("请选择文件路径");
                fileChooser.setApproveButtonText("确定");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                result = fileChooser.showOpenDialog(null);
                if (JFileChooser.APPROVE_OPTION == result) {
                    path=fileChooser.getSelectedFile().getPath();
                    String filePath = path + "/" + filename + ".csv";
                    p.savePage(filePath);
                }            
            }
        });

        btn_save1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                save(pageTable, dir);
            }
        });

        btn_nextPage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //save current page
                save(pageTable, dir);
                //clear the table
                int rowCount = pageTableM.getRowCount();
                for(int i=0; i<rowCount; i++){
                    pageTableM.removeRow(0);
                }
                valueOfINCOME = 0.0;
                valueOfEXPEND = 0.0;
                valueOfTOTAL = 0.0;
                //load next page
                index++;
                File f = new File(dir + Integer.toString(index) + ".csv");
                //if next page exists: load next page
                if(f.exists()){
                    Page pp = new Page();
                    pp.loadPage(dir + Integer.toString(index) + ".csv");
                    int id = 0;
                    for(int i = 0; i < 17; i++){
                        String data[] = pp.getLine(i);
                        pageTableM.addRow(data);
                        if(i < 15 && !data[0].equals(" ")){
                            valueOfINCOME = valueOfINCOME + Double.valueOf(data[3]);
                            valueOfEXPEND = valueOfEXPEND + Double.valueOf(data[4]);
                            valueOfTOTAL = valueOfINCOME + valueOfEXPEND;
                            id = Integer.valueOf(data[1]);
                        }
                    }
                    INCOME.setText("总计收入: "+ Double.toString(valueOfINCOME));
                    EXPEND.setText("总计支出: "+ Double.toString(valueOfEXPEND));
                    TOTAL.setText("总计: "+ Double.toString(valueOfTOTAL));
                    totalIncome.setText("累计收入: "+ Double.toString(valueOftotalIncome));
                    totalExpend.setText("累计支出: "+ Double.toString(valueOftotalExpend));
                    total.setText("累计总计: "+ Double.toString(valueOftotal));
    
                    if(id == 0)
                        id_TF.setText("");
                    else
                        id_TF.setText(Integer.toString(id + 1));
                    date_TF.setText("");
                    message_TF.setText("");
                    income_TF.setText("");
                    expend_TF.setText("");                
                    }
                //not exist: create new page
                else{
                    INCOME.setText("总计收入: "+ String.valueOf(valueOfINCOME));
                    EXPEND.setText("总计支出: "+ String.valueOf(valueOfEXPEND));
                    TOTAL.setText("总计: "+ String.valueOf(valueOfTOTAL));
                    totalIncome.setText("累计收入: "+ String.valueOf(valueOftotalIncome));
                    totalExpend.setText("累计支出: "+ String.valueOf(valueOftotalExpend));
                    total.setText("累计总计: "+ String.valueOf(valueOftotal));

                    id_TF.setText("");
                    date_TF.setText("");
                    message_TF.setText("");
                    income_TF.setText("");
                    expend_TF.setText("");
                }
            }
        });

        btn_lastPage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(index == 1){
                    JOptionPane.showMessageDialog(null, "已经是第一页");
                    return;
                }
                //save current page
                save(pageTable, dir);
                //clear the table
                int rowCount = pageTableM.getRowCount();
                for(int i=0; i<rowCount; i++){
                    pageTableM.removeRow(0);
                }
                valueOfINCOME = 0.0;
                valueOfEXPEND = 0.0;
                valueOfTOTAL = 0.0;
                //load previous page
                index --;       
                Page pp = new Page();
                pp.loadPage(dir + Integer.toString(index) + ".csv");
                int id = 0;
                for(int i = 0; i < 17; i++){
                    String data[] = pp.getLine(i);
                    pageTableM.addRow(data);
                    if(i < 15 && !data[0].equals(" ")){
                        valueOfINCOME = valueOfINCOME + Double.valueOf(data[3]);
                        valueOfEXPEND = valueOfEXPEND + Double.valueOf(data[4]);
                        valueOfTOTAL = valueOfINCOME + valueOfEXPEND;
                        id = Integer.valueOf(data[1]);
                    }
                }
                
                INCOME.setText("总计收入: "+ String.valueOf(valueOfINCOME));
                EXPEND.setText("总计支出: "+ String.valueOf(valueOfEXPEND));
                TOTAL.setText("总计: "+ String.valueOf(valueOfTOTAL));
                totalIncome.setText("累计收入: "+ String.valueOf(valueOftotalIncome));
                totalExpend.setText("累计支出: "+ String.valueOf(valueOftotalExpend));
                total.setText("累计总计: "+ String.valueOf(valueOftotal));

                if(id == 0)
                    id_TF.setText("");
                else
                    id_TF.setText(Integer.toString(id + 1));
                date_TF.setText("");
                message_TF.setText("");
                income_TF.setText("");
                expend_TF.setText("");       
            }
        });

        btn_print.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MessageFormat header = new MessageFormat("万通超市财务流水账簿"+"  "+session);
                MessageFormat footer = new MessageFormat("");
                int rowNumber = pageTable.getRowCount();
                for(int i=0; i<14-rowNumber; i++){
                    String data[] = {"", "", "", "", "",""};
                    pageTableM.addRow(data); 
                }
                String pageOverall[]={"本页共计","/","/",String.valueOf(valueOfINCOME),String.valueOf(valueOfEXPEND),String.valueOf(valueOfTOTAL)};
                String total[]={"累计","/","/",String.valueOf(valueOftotalIncome),String.valueOf(valueOftotalExpend),String.valueOf(valueOftotal)};
                pageTableM.addRow(pageOverall);
                pageTableM.addRow(total);
                try{
                    pageTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                }catch(Exception ex){
                    System.out.println("failed");
                }
            }
        });

        Box vbox = Box.createVerticalBox();
        vbox.add(statistics_1);
        vbox.add(statistics_2);
        //vbox.add(statistics_3);
        vbox.add(bottom);

        jf.add(line, BorderLayout.NORTH);
        jf.add(page, BorderLayout.CENTER);
        jf.add(vbox, BorderLayout.SOUTH);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    public void save(JTable t, String dir){
        Page p = new Page();
        for(int i=0; i<15; i++){
            try{
                String l[] = {(String)t.getValueAt(i, 0),//date
                    (String)t.getValueAt(i, 1),//id
                    (String)t.getValueAt(i, 2),//abstract
                    (String)t.getValueAt(i, 3),//income
                    (String)t.getValueAt(i, 4),//expence
                    (String)t.getValueAt(i, 5)//overall
                };
                p.addLine(l);
            }
            catch(Exception exception){
                String l[] = {" ", " ", "  ", " ", " ", " "};
                p.addLine(l);
            }
        }
        String pageOverall[]={"本页共计","/","/",String.valueOf(valueOfINCOME),
            String.valueOf(valueOfEXPEND),String.valueOf(valueOfTOTAL)};
        String total[]={"累计","/","/",String.valueOf(valueOftotalIncome),
            String.valueOf(valueOftotalExpend),String.valueOf(valueOftotal)};
        p.addLine(pageOverall);
        p.addLine(total);
        p.savePage(dir + Integer.toString(index) + ".csv");

    }


}