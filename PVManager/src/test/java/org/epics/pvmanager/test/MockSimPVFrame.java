/*
 * Copyright 2010 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */

/*
 * MockPVFrame.java
 *
 * Created on Feb 16, 2010, 3:43:37 PM
 */

package org.epics.pvmanager.test;

import javax.swing.JOptionPane;
import org.epics.pvmanager.ThreadSwitch;
import org.epics.pvmanager.sim.SimulationDataSource;
import org.epics.pvmanager.PV;
import org.epics.pvmanager.PVManager;
import org.epics.pvmanager.PVValueChangeListener;
import org.epics.pvmanager.data.VDouble;
import static org.epics.pvmanager.data.ExpressionLanguage.*;

/**
 *
 * @author carcassi
 */
public class MockSimPVFrame extends javax.swing.JFrame {

    /** Creates new form MockPVFrame */
    public MockSimPVFrame() {
        PVManager.setDefaultThread(ThreadSwitch.onSwingEDT());
        PVManager.setDefaultDataSource(SimulationDataSource.simulatedData());
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        scanRateSpinner = new javax.swing.JSpinner();
        createPVButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        pvNameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PV value:");

        valueLabel.setText("0");

        jLabel6.setText("UI scan rate (Hz):");

        scanRateSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));

        createPVButton.setText("Create ");
        createPVButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPVButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("PV name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pvNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(10, 10, 10)
                        .addComponent(scanRateSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                    .addComponent(createPVButton, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(valueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pvNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scanRateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createPVButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    PV<VDouble> pv;

    private void createPVButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPVButtonActionPerformed
        if (pv != null)
            pv.close();
        int scanRate = ((Integer) scanRateSpinner.getModel().getValue()).intValue();
        try {
            pv = PVManager.read(vDouble(pvNameField.getText())).atHz(scanRate);
            pv.addPVValueChangeListener(new PVValueChangeListener() {

                @Override
                public void pvValueChanged() {
                    valueLabel.setText(Double.toString(pv.getValue().getValue()));
                }
            });
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_createPVButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MockSimPVFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createPVButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField pvNameField;
    private javax.swing.JSpinner scanRateSpinner;
    private javax.swing.JLabel valueLabel;
    // End of variables declaration//GEN-END:variables

}
