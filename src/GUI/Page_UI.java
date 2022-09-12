package GUI;

import LOGIC.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.*;
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

    public void visualise(){
        session = JOptionPane.showInputDialog(null, "请输入年份: ");
        JFrame jf = new JFrame("Page");
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
        JButton btn_save = new JButton("保存");
        JButton btn_print = new JButton("打印");
        JButton btn_nextPage = new JButton("下一页");
        JButton btn_lastPage = new JButton("上一页");

        statistics_1.add(INCOME);
        statistics_1.add(EXPEND);
        statistics_1.add(TOTAL);
        statistics_2.add(totalIncome);
        statistics_2.add(totalExpend);
        statistics_2.add(total);

        bottom.add(btn_delete);
        bottom.add(btn_save);
        bottom.add(btn_nextPage);
        bottom.add(btn_lastPage);
        bottom.add(btn_print);

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
                        String data[] = {date_TF.getText(), id_TF.getText(), message_TF.getText(), income_TF.getText(), "-"+expend_TF.getText(), ""};
                        pageTableM.addRow(data);
                        valueOfINCOME = valueOfINCOME + Double.valueOf(income_TF.getText());
                        valueOfEXPEND = valueOfEXPEND + Double.valueOf(expend_TF.getText());
                        valueOfTOTAL = valueOfINCOME - valueOfEXPEND;
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
                String filename = JOptionPane.showInputDialog(null, "请输入文件名: ");
                for(int i=0; i<pageTable.getRowCount(); i++){
                    p.addLine((String)pageTable.getValueAt(i, 0),(String)pageTable.getValueAt(i, 1),(String)pageTable.getValueAt(i, 2),(String)pageTable.getValueAt(i, 3),(String)pageTable.getValueAt(i, 4));
                }
                int result = 0;
                String path = null;
                JFileChooser fileChooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView(); 
                System.out.println(fsv.getHomeDirectory()); 
                fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
                fileChooser.setDialogTitle("please choose path...");
                fileChooser.setApproveButtonText("确定");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                result = fileChooser.showOpenDialog(null);
                if (JFileChooser.APPROVE_OPTION == result) {
                    path=fileChooser.getSelectedFile().getPath();
                    String filePath = path + "/" + filename + ".csv";
                    System.out.println(filePath);
                    p.savePage(filePath);
                }            
            }
        });

        btn_nextPage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //别动
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

        btn_lastPage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            }
        });

        btn_print.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MessageFormat header = new MessageFormat("万通超市财务流水账簿"+"  "+session);
                MessageFormat footer = new MessageFormat("");
                int rowNumber = pageTable.getRowCount();
                for(int i=0; i<15-rowNumber; i++){
                    String data[] = {"", "", "", "", "",""};
                    pageTableM.addRow(data); 
                }
                String pageOverall[]={"本页共计","/","/",String.valueOf(INCOME),String.valueOf(EXPEND),String.valueOf(TOTAL)};
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
        vbox.add(bottom);

        jf.add(line, BorderLayout.NORTH);
        jf.add(page, BorderLayout.CENTER);
        jf.add(vbox, BorderLayout.SOUTH);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}