/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//package com.mycompany.netbeanstest2;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

//import java.math.BigInteger;
//import java.util.Stack;
/**
 *
 * @author Admin
 */
public class TicketUI extends javax.swing.JFrame {

    /**
     * Creates new form TicketUI
     */

    private int movieIndex;
    DefaultTableModel model;
    //luu du lieu vao 1 mang
    private ArrayList<Ticket> t = new ArrayList<>();
    //luu gia
    private int[] tiketPrice = new int[25];
    //ghe da duoc ngoi
    private boolean[] isTaken = new boolean[25];
    //mau cua nut bam
    private Color[] color = new Color[25];
    public TicketUI(int movieIndex) {
        this.movieIndex = movieIndex;
//        this.movieIndex = 1;
        initComponents();
        //cho mau (white = ght thuong) (yellow = ghe vip)
        jButton1.setBackground(Color.WHITE);
        jButton2.setBackground(Color.WHITE);
        jButton3.setBackground(Color.WHITE);
        jButton4.setBackground(Color.WHITE);
        jButton5.setBackground(Color.WHITE);
        jButton6.setBackground(Color.WHITE);
        jButton7.setBackground(Color.WHITE);

        jButton8.setBackground(Color.YELLOW);
        jButton9.setBackground(Color.YELLOW);
        jButton10.setBackground(Color.YELLOW);
        jButton11.setBackground(Color.YELLOW);

        jButton12.setBackground(Color.WHITE);
        jButton13.setBackground(Color.WHITE);

        jButton14.setBackground(Color.YELLOW);
        jButton15.setBackground(Color.YELLOW);
        jButton16.setBackground(Color.YELLOW);
        jButton17.setBackground(Color.YELLOW);

        jButton18.setBackground(Color.WHITE);
        jButton19.setBackground(Color.WHITE);
        jButton20.setBackground(Color.WHITE);
        jButton21.setBackground(Color.WHITE);
        jButton22.setBackground(Color.WHITE);
        jButton23.setBackground(Color.WHITE);
        jButton24.setBackground(Color.WHITE);

        this.setLocationRelativeTo(null);   //bang hien o giua
        model = (DefaultTableModel) jTable1.getModel();

        for(int i=1;i<=24;i++) {
            if ((i > 7 && i < 12) || (i > 13 && i < 18)) {
                //luu ghe vip
                tiketPrice[i] = 20;
                color[i] = Color.YELLOW;
            } else {
                //luu ghe thuong
                tiketPrice[i] = 10;
                color[i] = Color.WHITE;
            }
        }
        updateButtonColorsFromCSV();
        readDataFromCSV();
    }
    //ghi mau cac ghe da dc chon tu truoc
    private void readDataFromCSV() {
        // Doc data roi in ra jTable1
        String csvFile = "Ticket" + movieIndex + ".csv";  //File da co
        String line;
        String csvSplitBy = ",";    //Chia du lieu

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // bo qua dong dau tien (ten header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                model.addRow(data);
                // So ghe nam o cot thu 3 (tinh theo java la cot 2)
                int seat = Integer.parseInt(data[2].trim());
                // Dat ghe ngoi thanh da duoc chon
                if (seat >= 1 && seat <= 24) {
                    isTaken[seat] = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateButtonColorsFromCSV() {
        // Doc data da co tu Ticket.csv and va doi mau nut bam
        String csvFile = "Ticket" + movieIndex + ".csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                // Kiem tra xem co du du lieu khong va ghe co phai dang int khong
                if (data.length >= 4 && isNumber(data[2].trim())) {
                    int seat = Integer.parseInt(data[2].trim());
                    updateButtonColor(seat);    //doi mau ghe
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Ktra so
    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //cho ghe thanh mau do
    private void updateButtonColor(int seat) {
        // chuyen ghe thanh da ngoi
        if (seat >= 1 && seat <= 24) {
            isTaken[seat] = true;

            switch (seat) {
                //vi moi button la 1 cai ten khac nhau nen phai lam tung cai mot
                //co 24 button
                case 1:
                    jButton1.setBackground(Color.RED);
                    break;
                case 2:
                    jButton2.setBackground(Color.RED);
                    break;
                case 3:
                    jButton3.setBackground(Color.RED);
                    break;
                case 4:
                    jButton4.setBackground(Color.RED);
                    break;
                case 5:
                    jButton5.setBackground(Color.RED);
                    break;
                case 6:
                    jButton6.setBackground(Color.RED);
                    break;
                case 7:
                    jButton7.setBackground(Color.RED);
                    break;
                case 8:
                    jButton8.setBackground(Color.RED);
                    break;
                case 9:
                    jButton9.setBackground(Color.RED);
                    break;
                case 10:
                    jButton10.setBackground(Color.RED);
                    break;
                case 11:
                    jButton11.setBackground(Color.RED);
                    break;
                case 12:
                    jButton12.setBackground(Color.RED);
                    break;
                case 13:
                    jButton13.setBackground(Color.RED);
                    break;
                case 14:
                    jButton14.setBackground(Color.RED);
                    break;
                case 15:
                    jButton15.setBackground(Color.RED);
                    break;
                case 16:
                    jButton16.setBackground(Color.RED);
                    break;
                case 17:
                    jButton17.setBackground(Color.RED);
                    break;
                case 18:
                    jButton18.setBackground(Color.RED);
                    break;
                case 19:
                    jButton19.setBackground(Color.RED);
                    break;
                case 20:
                    jButton20.setBackground(Color.RED);
                    break;
                case 21:
                    jButton21.setBackground(Color.RED);
                    break;
                case 22:
                    jButton22.setBackground(Color.RED);
                    break;
                case 23:
                    jButton23.setBackground(Color.RED);
                    break;
                case 24:
                    jButton24.setBackground(Color.RED);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        donebutton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Cinema Screen");

        jButton1.setText("1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("11");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("12");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("13");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("14");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("15");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("16");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("17");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("18");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("19");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("20");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("21");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("22");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText("23");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setText("24");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        donebutton.setText("Done");
        donebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donebuttonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Name", "Date", "Seats", "Price"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Customer name");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel2.setText("Date");

        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Discount ?");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(237, 237, 237)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jButton19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jButton22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(txtName)
                                                                        .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(donebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(204, 204, 204))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(38, 38, 38)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(37, 37, 37)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(donebutton)
                                        .addComponent(jLabel3)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    //them du lieu vao bang
    private void addData(int price, int seat) {
        System.out.println("Price: " + price);

        // Ten va ngay
        String name = txtName.getText().trim();
        String dateString = txtDate.getText().trim();

        // Kiem tra xem da nhap chua
        if (name.isEmpty() || dateString.isEmpty()) {
            showErrorPanel("Please enter name and date.");
            return;
        }

        // Set du lieu
        VipTicket Vtik = new VipTicket();
        NormalTicket Ntik = new NormalTicket();

        if ((seat > 7 && seat < 12) || (seat > 13 && seat < 18)) {
            Vtik.setName(name);

            // Kiem tra ngay thang roi set
            try {
                Vtik.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateString));
            } catch (ParseException ex) {
                ex.printStackTrace();
                showErrorPanel("Invalid date format. Please enter date in dd/MM/yyyy format.");
                return;
            }

            // Set du lieu tu nut bam
            Vtik.setVipPrice(price);
            System.out.println("Vip Price: " + Vtik.getVipPrice());
            Vtik.setSeat(seat);

            // Them vao array
            t.add(Vtik);
            showResult(seat);
        } else {
            Ntik.setName(name);

            // Kiem tra ngay thang roi set
            try {
                Ntik.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateString));
            } catch (ParseException ex) {
                ex.printStackTrace();
                showErrorPanel("Invalid date format. Please enter date in dd/MM/yyyy format.");
                return;
            }

            // Set du lieu tu nut bam
            if (discount) {
                Ntik.setHasDiscount(true);
                Ntik.setNormalprice(price / 2);
                System.out.println("Normal Price with Discount: " + Ntik.getNormalprice());
            } else {
                Ntik.setHasDiscount(false);
                Ntik.setNormalprice(price);
                System.out.println("Normal Price without Discount: " + Ntik.getNormalprice());
            }

            Ntik.setSeat(seat);

            // Them vao array
            t.add(Ntik);
            showResult(seat);
        }
    }
    //panel loi
    private void showErrorPanel(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void showResult(int seat) {
        if((seat > 7 && seat < 12) || (seat > 13 && seat < 18)) {
            VipTicket tik = (VipTicket) t.get(t.size() - 1);
            //them hang
            model.addRow(new Object[]{
                    tik.getName(),
                    tik.getDate(),
                    tik.getSeat(),
                    tik.getVipPrice()
            });
        }
        else {
            NormalTicket tik = (NormalTicket) t.get(t.size() - 1);
            //them hang
            model.addRow(new Object[]{
                    tik.getName(),
                    tik.getDate(),
                    tik.getSeat(),
                    tik.getNormalprice()
            });
        }
    }
    //xoa hang neu xoa ghe
    private void removeData(int seat) {
        for (int row = 0; row < model.getRowCount(); row++) {
            try {
                //lay du lieu tu cot thu 3 (2) (cot seats)
                int seatValue = Integer.parseInt(model.getValueAt(row, 2).toString());

                if (seatValue == seat) {
                    //neu nut bam mau do(da co trong bang jTable1) thi xoa hang do di
                    model.removeRow(row);
                    if (row < t.size()) {
                        t.remove(row);
                    }
                    break;
                }
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
                System.err.println("Error parsing seat value: " + model.getValueAt(row, 2));
            }
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Button">    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[1];
        System.out.println(tiketPrice[1]);
        int seat = 1;
        if (isTaken[1]) {
            // Neu ghe da ngoi -> sua mau thanh mau goc
            // Xoa du lieu
            jButton1.setBackground(color[1]);
            removeData(seat);
        }
        else {
            // Neu ghe chua ngoi -> chuyen ghe thanh mau do (bao hieu da co nguoi ngoi)
            // Them du lieu
            jButton1.setBackground(Color.RED);
            addData(price, seat);
        }

        //Neu dung -> sai con neu sai -> dung
        isTaken[1] = !isTaken[1];
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[2];
        int seat = 2;
        if (isTaken[2]) {
            jButton2.setBackground(color[2]);
            removeData(seat);
        }
        else {
            jButton2.setBackground(Color.RED);
            addData(price, seat);
        }
        isTaken[2] = !isTaken[2];
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[3];
        int seat = 3;
        if (isTaken[3]) {
            jButton3.setBackground(color[3]);
            removeData(seat);
        }
        else {
            jButton3.setBackground(Color.RED);
            addData(price, seat);
        }
        isTaken[3] = !isTaken[3];
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[4];
        int seat = 4;
        if (isTaken[4]) {
            jButton4.setBackground(color[4]);
            removeData(seat);
        }
        else {
            jButton4.setBackground(Color.RED);
            addData(price, seat);
        }
        isTaken[4] = !isTaken[4];
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[5];
        int seat = 5;
        // Toggle button color based on the current color in the color array
        if (isTaken[5]) {
            // If the seat is taken, set the original color from the color array
            jButton5.setBackground(color[5]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton5.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[5] = !isTaken[5];
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[6];
        int seat = 6;
        // Toggle button color based on the current color in the color array
        if (isTaken[6]) {
            // If the seat is taken, set the original color from the color array
            jButton6.setBackground(color[6]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton6.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[6] = !isTaken[6];
    }


    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[7];
        int seat = 7;
        // Toggle button color based on the current color in the color array
        if (isTaken[7]) {
            // If the seat is taken, set the original color from the color array
            jButton7.setBackground(color[7]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton7.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[7] = !isTaken[7];
    }

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[8];
        int seat = 8;
        // Toggle button color based on the current color in the color array
        if (isTaken[8]) {
            // If the seat is taken, set the original color from the color array
            jButton8.setBackground(color[8]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton8.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[8] = !isTaken[8];
    }

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[9];
        int seat = 9;
        // Toggle button color based on the current color in the color array
        if (isTaken[9]) {
            // If the seat is taken, set the original color from the color array
            jButton9.setBackground(color[9]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton9.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[9] = !isTaken[9];
    }

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[10];
        int seat = 10;
        // Toggle button color based on the current color in the color array
        if (isTaken[10]) {
            // If the seat is taken, set the original color from the color array
            jButton10.setBackground(color[10]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton10.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[10] = !isTaken[10];
    }

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[11];
        int seat = 11;
        // Toggle button color based on the current color in the color array
        if (isTaken[11]) {
            // If the seat is taken, set the original color from the color array
            jButton11.setBackground(color[11]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton11.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[11] = !isTaken[11];
    }

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[12];
        int seat = 12;
        // Toggle button color based on the current color in the color array
        if (isTaken[12]) {
            // If the seat is taken, set the original color from the color array
            jButton12.setBackground(color[12]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton12.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[12] = !isTaken[12];
    }

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[13];
        int seat = 13;
        // Toggle button color based on the current color in the color array
        if (isTaken[13]) {
            // If the seat is taken, set the original color from the color array
            jButton13.setBackground(color[13]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton13.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[13] = !isTaken[13];
    }

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[14];
        int seat = 14;
        // Toggle button color based on the current color in the color array
        if (isTaken[14]) {
            // If the seat is taken, set the original color from the color array
            jButton14.setBackground(color[14]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton14.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[14] = !isTaken[14];
    }
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[15];
        int seat = 15;
        // Toggle button color based on the current color in the color array
        if (isTaken[15]) {
            // If the seat is taken, set the original color from the color array
            jButton15.setBackground(color[15]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton15.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[15] = !isTaken[15];
    }

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[16];
        int seat = 16;
        // Toggle button color based on the current color in the color array
        if (isTaken[16]) {
            // If the seat is taken, set the original color from the color array
            jButton16.setBackground(color[16]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton16.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[16] = !isTaken[16];
    }

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[17];
        int seat = 17;
        // Toggle button color based on the current color in the color array
        if (isTaken[17]) {
            // If the seat is taken, set the original color from the color array
            jButton17.setBackground(color[17]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton17.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[17] = !isTaken[17];
    }

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[18];
        int seat = 18;
        // Toggle button color based on the current color in the color array
        if (isTaken[18]) {
            // If the seat is taken, set the original color from the color array
            jButton18.setBackground(color[18]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton18.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[18] = !isTaken[18];
    }

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[19];
        int seat = 19;
        // Toggle button color based on the current color in the color array
        if (isTaken[19]) {
            // If the seat is taken, set the original color from the color array
            jButton19.setBackground(color[19]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton19.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[19] = !isTaken[19];
    }

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[20];
        int seat = 20;
        // Toggle button color based on the current color in the color array
        if (isTaken[20]) {
            // If the seat is taken, set the original color from the color array
            jButton20.setBackground(color[20]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton20.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[20] = !isTaken[20];
    }
    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[21];
        int seat = 21;
        // Toggle button color based on the current color in the color array
        if (isTaken[21]) {
            // If the seat is taken, set the original color from the color array
            jButton21.setBackground(color[21]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton21.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[21] = !isTaken[21];
    }
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[22];
        int seat = 22;
        // Toggle button color based on the current color in the color array
        if (isTaken[22]) {
            // If the seat is taken, set the original color from the color array
            jButton22.setBackground(color[22]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton22.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[22] = !isTaken[22];
    }
    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[23];
        int seat = 23;
        // Toggle button color based on the current color in the color array
        if (isTaken[23]) {
            // If the seat is taken, set the original color from the color array
            jButton23.setBackground(color[23]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton23.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[23] = !isTaken[23];
    }

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int price = tiketPrice[24];
        int seat = 24;
        // Toggle button color based on the current color in the color array
        if (isTaken[24]) {
            // If the seat is taken, set the original color from the color array
            jButton24.setBackground(color[24]);
            removeData(seat);
        }
        else {
            // If the seat is not taken, set the color to red
            jButton24.setBackground(Color.RED);
            addData(price, seat);
        }

        // Toggle the isTaken value for the corresponding button
        isTaken[24] = !isTaken[24];
    }
    // </editor-fold>  
    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    //giam gia
    boolean discount;
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        discount = jCheckBox1.isSelected();
    }
    //nut save (save vao csv)
    private void donebuttonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.out.println("done button "+ movieIndex);

        for (Ticket ticket : t) {
            System.out.println(ticket);
            System.out.println("-----------");
        }
        saveToCSV("Ticket" + movieIndex + ".csv");
//        saveToCSV("Ticket.csv");

    }
    private void saveToCSV(String filePath) {
        System.out.println(movieIndex);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // ten cot
            for (int col = 0; col < jTable1.getColumnCount(); col++) {
                writer.write(jTable1.getColumnName(col));
                if (col < jTable1.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
            // data
            for (int row = 0; row < jTable1.getRowCount(); row++) {
                for (int col = 0; col < jTable1.getColumnCount(); col++) {
                    writer.write(jTable1.getValueAt(row, col).toString());
                    if (col < jTable1.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }

            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//    /**
//     * @param args the command line arguments
//     */
    public void runCode() {
//    public static void main(String[] args) {
        System.out.println(movieIndex);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TicketUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicketUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicketUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicketUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicketUI(movieIndex).setVisible(true);
                System.out.println(movieIndex);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton donebutton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtName;
    // End of variables declaration                   
}
