package com.example.codesmell.detector.gui;

import com.example.codesmell.detector.detector.SmellDetector;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class FileChooser extends JFrame {

    private static List<File> files;
    File file = new File("");
    JFileChooser jfc = new JFileChooser();
    JLabel lj = new JLabel("Path：");
    JTextField p1 = new JTextField(26);
    JButton open = new JButton("...");
    JButton upload = new JButton("Upload");
    JButton back = new JButton("Cancel");
    JLabel describe1 = new JLabel();
    JLabel describe2 = new JLabel();
    JLabel describe3 = new JLabel();

    public FileChooser() {
        setTitle("CodeSmell");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(400, 200, 500, 400);
        this.setVisible(true);
        this.add(describe1);
        this.add(describe2);
        this.add(describe3);
        this.add(p1);
        this.add(open);
        this.add(lj);
        this.add(upload);
        this.add(back);

        p1.setEditable(false);
        p1.setText("Drag in a file here or browse for it");
        describe1.setText("The program can find:");
        describe1.setBounds(80,10,300,30);
        describe2.setText("- Long Method");
        describe2.setBounds(90,30,300,30);
        describe3.setText("- Large Class");
        describe3.setBounds(90,50,300,30);
        lj.setBounds(40, 240, 40, 30);
        lj.setFont(new Font("Basic", 0, 12));
        p1.setBounds(80, 240, 320, 30);
        open.setBounds(410, 240, 40, 30);
        back.setBounds(120, 300, 100, 30);
        upload.setBounds(260, 300, 100, 30);

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "Choose");
                file = jfc.getSelectedFile();

                if (file.isDirectory()) {
                    p1.setText("Folder:" + file.getAbsolutePath());
                } else if (file.isFile()) {
                    p1.setText("File:" + file.getAbsolutePath());
                }
                System.out.println(jfc.getSelectedFile().getName());
            }
        });
        //setTransferHandler is used for dragging file into Blanket
        p1.setTransferHandler(new TransferHandler() {
            public boolean importData(JComponent comp, Transferable t) {
                try {
                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);

                    String filepath = o.toString();
                    if (filepath.startsWith("[")) {
                        filepath = filepath.substring(1);
                    }
                    if (filepath.endsWith("]")) {
                        filepath = filepath.substring(0, filepath.length() - 1);
                    }
                    p1.setText(filepath);
                    file = new File(filepath);

                    return true;
                } catch (Exception e) {

                }
                return false;
            }

            @Override
            public boolean canImport(JComponent comp, DataFlavor[] flavors) {
                for (int i = 0; i < flavors.length; i++) {
                    if (DataFlavor.javaFileListFlavor.equals(flavors[i])) {
                        return true;
                    }
                }
                return false;
            }
        });

        upload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!file.isFile()) {
                    JOptionPane.showMessageDialog(null, "No file selected!");
                } else if (file.isFile()) {
                    File defaultFolder = new File("D:\\");
                    copyFile(file.getAbsolutePath(), defaultFolder + file.getName());
                    JOptionPane.showMessageDialog(null, "Upload successful");
                    System.out.println(file.getPath());
//                    JOptionPane.showMessageDialog(null,file.getName());
                    SmellDetector sd = new SmellDetector();
                    sd.Detector(file.getPath());
                }
            }
        });


        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void copyFile(String oldPath, String newPath) {
        try {
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //if file has existed
                InputStream inStream = new FileInputStream(oldPath); //read original file
                FileOutputStream fs = new FileOutputStream(newPath);
                inStream.close();
                fs.close();
            }
        } catch (Exception e) {
            System.out.println("Upload error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new FileChooser();

    }
}
