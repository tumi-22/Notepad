/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author hp
 */
public class NotePad20 extends JFrame{
    private JMenuBar mb;
    private JMenu File;
    private JMenuItem openRecentFiles;
    private JMenu save;
    private JMenuItem saveToFile;
    private JMenu open;
    private JMenuItem openFile;
    private JMenu tools;
    private JMenuItem options;
    private JMenu help;
    private JMenuItem guide;
    private JMenu Recent;
    private JMenu history;
    private JLabel heading;
    
    private JTextArea NotesBox;
    private JScrollPane scrolls;
    private LineBorder line;
    private TitledBorder titleBorder;
    
    private JButton clear;
    private JButton exit;
    
    private JPanel headerPnl;
    private JPanel displayPnl;
    private JPanel buttonsPnl;
    private JPanel mainPnl;
    
    private File file;
    private BufferedWriter bw;
    private FileWriter fw;
    private BufferedReader br;
    private FileReader fr;
    private JFileChooser filechooser;
    String date;
    public NotePad20(){
        mb=new JMenuBar();
        File=new JMenu("FILE");
        openRecentFiles=new JMenuItem("Open Recent Files");
        File.add(openRecentFiles);
        save=new JMenu("SAVE");
        saveToFile=new JMenuItem("Save To File");
        save.add(saveToFile);
        open=new JMenu("OPEN");
        openFile=new JMenuItem("Open File");
        open.add(openFile);
        tools=new JMenu("TOOLS");
        //tools.add(history);
        //tools.add(Recent);
        //tools.add(history);
        options=new JMenuItem("Options");
        tools.add(options);
        help=new JMenu("HELP");
        guide=new JMenuItem("GUIDE");
        help.add(guide);
        mb.add(File);
        mb.add(save);
        mb.add(open);
        mb.add(tools);
        mb.add(help);
        
        heading=new JLabel("NotePad 2.0");
        heading.setFont(new Font(Font.SANS_SERIF, Font.ITALIC + Font.BOLD, 25));
        heading.setForeground(Color.BLUE);
        heading.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        
        NotesBox=new JTextArea(25,40);
        scrolls=new JScrollPane(NotesBox);
        date=JOptionPane.showInputDialog("Enter date (yyyy/mm/dd)");
        line=new LineBorder(Color.BLACK);
        titleBorder=new TitledBorder(line,date);
        NotesBox.setBorder(titleBorder);
        scrolls.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        clear=new JButton("CLEAR");
        exit=new JButton("EXIT");
        
        headerPnl=new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPnl.add(heading);
        
        displayPnl=new JPanel();
        displayPnl.add(scrolls);
        
        buttonsPnl=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPnl.add(clear);
        buttonsPnl.add(exit);
        
        mainPnl=new JPanel();
        mainPnl.setLayout(new BoxLayout(mainPnl,BoxLayout.Y_AXIS));
        mainPnl.add(headerPnl);
        mainPnl.add(displayPnl);
        mainPnl.add(buttonsPnl);
        
        setJMenuBar(mb);
        add(mainPnl);
        setSize(500,700);
        setTitle("NotePad 2.0");
        setVisible(true);
    
        saveToFile.addActionListener(new Save());
        openFile.addActionListener(new Open());
        guide.addActionListener(new Guide());
        options.addActionListener(new Options());
        clear.addActionListener(new Clear());
        exit.addActionListener(new Exit());
    }
    public class Save implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            filechooser=new JFileChooser();
            int val=filechooser.showSaveDialog(NotePad20.this);
            if(val==JFileChooser.APPROVE_OPTION){
                file=filechooser.getSelectedFile();
                try{
                    fw=new FileWriter(file,true);
                    bw=new BufferedWriter(fw);
                    Notes notes=new Notes(date,NotesBox.getText());
                    DBHandler DB=new DBHandler(notes.toString(),"jdbc:derby://localhost:1527/Notes","notes","123");
                    bw.write(notes.toString());
                    bw.newLine();
                    bw.close();
                    fw.close();
                }catch(IOException error){
                    JOptionPane.showMessageDialog(rootPane, error);
                } catch (SQLException ex) {
                    Logger.getLogger(NotePad20.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
    
        }
    
    }
    public class Open implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            filechooser=new JFileChooser();
            int val=filechooser.showOpenDialog(NotePad20.this);
            if(val==JFileChooser.APPROVE_OPTION){
                file=filechooser.getSelectedFile();
                try{
                    fr=new FileReader(file);
                    br=new BufferedReader(fr);
                    String data=br.readLine();
                    String info="";
                    while(data!=null){
                        info+=data+"\n";
                        data=br.readLine();
                        
                    }
                    Notes notes=new Notes(date,info);
                    NotesBox.setText(notes.getNotes());
                    br.close();
                    fr.close();
                }catch(IOException error){
                    JOptionPane.showMessageDialog(rootPane, error);
                }    
            }
        }
    
    }
    public class Guide implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(rootPane, "Guide will be added soon");
        }
    
    }
    public class Options implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(rootPane, "Options will be added in due time");
        }
    
    }
    public class Clear implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            NotesBox.setText("");
        }
    
    }
    public class Exit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    
    }
    
}
