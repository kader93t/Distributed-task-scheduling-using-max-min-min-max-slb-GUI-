/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ordonnacement.Scheduler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Drmz
 */
public class Resultat extends javax.swing.JFrame {

    /**
     * Creates new form Resultat
     */
    JTable jMinTable, jMaxTable, jSlbTable;
    JScrollPane jMinScrol, jMaxScrol, jSlbScrol;
    JPanel jMinPanel, jMaxPanel, jSlbPanel;
    DefaultTableModel jMinTableModel, jMaxTableModel, jSlbTableModel;
    JPanel container;
    JPanel controle;
    Main main;
    HashMap<String, Integer> maxMachines, minMachines, slbMachines;
    String maxMachinesResultat = "",minMachinesResultat="";
    public Resultat(gui.Main main) {

        this.main = main;
        container = new JPanel(new BorderLayout());
        this.setLayout(new BorderLayout());
        controle = new JPanel(new BorderLayout());
        JButton jcomparison = new JButton("Comparison");
        JButton jretour = new JButton("Commancer a nouveau");
        JButton jcontinue = new JButton("Continue");
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(jcomparison);
        buttons.add(jretour);
        buttons.add(jcontinue);
        controle.add(buttons, BorderLayout.EAST);
        this.add(controle, BorderLayout.SOUTH);

        this.add(container);
        this.setMinimumSize(new Dimension(700, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jretour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jCommancerANouveau();

            }
        });
        jcontinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jContinue();
            }
        });
        jcomparison.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jcomparison();
            }
        });
    }

    public void jcomparison() {
        String minmachine = "M1", maxmachine = "M1", slbmachine = "M1";
        int max = 0;
        HashMap<String, Integer> finalResult = new HashMap<>();
        if (minMachines != null) {
            System.out.println("dkhal hna");
            System.out.println();
            for (String machine : minMachines.keySet()) {

                if (minMachines.get(machine) > max) {
                    minmachine = machine;
                    max= minMachines.get(machine);
                }
            }
        }
        max=0;
        finalResult.put(minmachine+"min", minMachines.get(minmachine));
        if (maxMachines != null) {
            System.out.println("dkhal hn");
            System.out.println(maxMachines);
            for (String machine : maxMachines.keySet()) {

                if (maxMachines.get(machine) >max) {
                    maxmachine = machine;
                    max = maxMachines.get(machine);
                }
            }
        }
        finalResult.put(maxmachine+"max", maxMachines.get(maxmachine));
        max = 0;
        if (slbMachines != null) {
            System.out.println("dkhal h");
            for (String machine : slbMachines.keySet()) {

                if (slbMachines.get(machine) > max) {
                    slbmachine = machine;
                    max = slbMachines.get(machine);
                }
            }
        }
        finalResult.put(slbmachine+"slb", slbMachines.get(slbmachine));

        int min = 999999999;
        System.out.println(finalResult);
        for (String machine : finalResult.keySet()) {
            if (finalResult.get(machine) < min) {
                System.out.println("min min min " + min);
               min = finalResult.get(machine);
            }
        }
        System.out.println("min   " +min);
        
        String m = "";
        if (minMachines.get(minmachine)==min) {
            m += " MinMin";
        }
        if (maxMachines.get(maxmachine)==min) {
            m += " MaxMin";
        }
        if (slbMachines.get(slbmachine) == min) {
            m += " SLB";
        }
        
        String message = "";
        if (maxMachines != null) {
            message += "\nle Makespane de MAXMIN est " + maxMachines.get(maxmachine);
           // message += "\n      les machines => " + maxMachinesResultat;
            
        }
        if (slbMachines != null) {
            message += "\nL Makespane de SLB est " + slbMachines.get(slbmachine);
           //  message += "\n     les machines => " + main.slbObj.FileMachine;
            }
        if (minMachines != null) {
            message += "\nLe Makespane du MINMIN est " + minMachines.get(minmachine);
           // message += "\n      les machines => " + minMachinesResultat;
            }
        JOptionPane.showMessageDialog(main, message + "\n Le meilleur est " + m);

    }
   
    
    public void jCommancerANouveau() {
        this.dispose();
        main.deleteTableData();
        Scheduler.FileMachine = new HashMap<>();
        Scheduler.machines = new HashMap<>();
        Scheduler.schuduler = new HashMap<>();
    }

    public void jContinue() {

        this.dispose();

        if (maxMachines != null) {
            main.maxMachines = copieMachins(maxMachines);
        }
        if (minMachines != null) {
            main.minMachines = copieMachins(minMachines);
        }
        if (slbMachines != null) {
            main.slbMachines = copieMachins(slbMachines);
        }
        main.useOld = true;
        Scheduler.FileMachine = new HashMap<>();
    }

    public HashMap<String, Integer> copieMachins(HashMap<String, Integer> from) {

        HashMap<String, Integer> machines = new HashMap<>();
        for (String machine : from.keySet()) {
            machines.put(machine, 0);
            //System.out.println(machine);
        }
        // System.out.println(machines);
        // System.out.println("==> "+ from);
        for (String machine : from.keySet()) {
            machines.put(machine, from.get(machine));
        }
        return machines;
    }
    
   

    public void drawMinMin(int column, HashMap<String, HashMap<String, Integer>> FileMachine, HashMap<String, Integer> machines) {
        minMachines = copieMachins(Scheduler.machines);
        minMachinesResultat = FileMachine.toString();
        
        JLabel resultat = new JLabel();
        // System.out.println("mmmmmmmmm" + minMachines);
        // System.out.println("kkkkkkkkk" + Scheduler.machines);
        drawResult(column, "MinMin", FileMachine, machines, jMinTable, jMinScrol, jMinPanel, jMinTableModel, BorderLayout.NORTH,resultat);
    }

    public void drawMaxMin(int column, HashMap<String, HashMap<String, Integer>> FileMachine, HashMap<String, Integer> machines) {
        maxMachines = copieMachins(Scheduler.machines);
       
        JLabel resultat = new JLabel();
        drawResult(column, "MaxMin", FileMachine, machines, jMaxTable, jMaxScrol, jMaxPanel, jMaxTableModel, BorderLayout.CENTER,resultat);
    }

    public void drawSlb(int column, HashMap<String, HashMap<String, Integer>> FileMachine, HashMap<String, Integer> machines) {
        slbMachines = copieMachins(main.slbObj.machines);
       
        JLabel resultat = new JLabel();
        drawResult(column, "SLB", FileMachine, machines, jSlbTable, jSlbScrol, jSlbPanel, jSlbTableModel, BorderLayout.SOUTH,resultat);
    }

    public void drawResult(int column, String algoritm, HashMap<String, HashMap<String, Integer>> FileMachine, HashMap<String, Integer> machines, JTable jTableResult, JScrollPane jscrol,
            JPanel jPanel, DefaultTableModel tableModel, String position,JLabel lableResultat) {
        
        
        String resultatString="";
        for(String machine : FileMachine.keySet()){
            resultatString+= machine+ " = "+FileMachine.get(machine).keySet()+", ";
           
        }
        
        lableResultat.setText(resultatString);
        jPanel = new JPanel(new BorderLayout());
        jPanel.setPreferredSize(new Dimension(233, 233));
        jPanel.add(new JLabel(algoritm), BorderLayout.NORTH);
        //this.add(jPanel,position);

        this.setVisible(true);
        tableModel = new DefaultTableModel();

        ArrayList<String> header = new ArrayList<>();
        header.add(" ");
        for (int i = 1; i < column; i++) {
            header.add("t" + i);
        }
        for (String title : header) {
            tableModel.addColumn(title);
        }
        HashMap<String, String[]> taches = new HashMap<>();

        for (String machine : FileMachine.keySet()) {
            taches.put(machine, new String[column]);
            taches.get(machine)[0] = machine;
            for (String tache : FileMachine.get(machine).keySet()) {
                //System.out.println("tttt "+ tache);
                //  System.out.println("ffff " + tache.substring(1).toString());
                int index = Integer.parseInt((tache.substring(1)).toString());
                FileMachine.get(machine);
                    taches.get(machine)[index] = FileMachine.get(machine).get(tache).toString();
                }


        }

        tableModel.addColumn("Temp");
        jTableResult = new JTable(tableModel);

        TableColumnModel columnModel = jTableResult.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(new ColumnColorRenderer(Color.DARK_GRAY, Color.GREEN));
        TableColumn tcolumn;
        for (int i = 1; i < columnModel.getColumnCount(); i++) {
            tcolumn = columnModel.getColumn(i);
            tcolumn.setCellRenderer(new ColumnColorRenderer(Color.lightGray, Color.black));
        }
        for (String machine : taches.keySet()) {

            // System.out.println("machinessssss " + taches.get(machine).toString());
            for (String data : taches.get(machine)) {
                // System.out.println(machine + " =>  " + data);
            }
            tableModel.addRow(taches.get(machine));

        }

        // add the time  execution of every machine to he table 
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String machine = jTableResult.getValueAt(i, 0).toString();
            jTableResult.setValueAt(machines.get(machine), i, tableModel.getColumnCount() - 1);

        }
        jTableResult.setFont(new java.awt.Font("Tahoma", 1, 13));
        jTableResult.repaint();
        jscrol = new JScrollPane(jTableResult);
        jscrol.revalidate();
        jscrol.repaint();
        // jPanel1.setLayout(new BorderLayout());
        //jPanel1.add(jMinTable,BorderLayout.CENTER);
        jPanel.add(jscrol, BorderLayout.CENTER);
        jPanel.add(lableResultat,BorderLayout.SOUTH);
        jPanel.revalidate();
        jPanel.repaint();
       
        //System.out.println("ssssss " + jMinTable.getRowCount());
        container.add(jPanel, position);
       
        container.revalidate();
        container.repaint();
        this.revalidate();
        this.repaint();
        new Main();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Resultat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Resultat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Resultat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Resultat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //  new Resultat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
