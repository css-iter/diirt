/*
 * Copyright 2010-11 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */

/*
 * MockWaterfallPlot.java
 *
 * Created on Jan 10, 2011, 3:59:13 PM
 */
package org.epics.graphene.pvmanager;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import org.epics.pvmanager.CompositeDataSource;
import org.epics.pvmanager.jca.JCADataSource;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.epics.graphene.Histogram1DUpdate;
import org.epics.pvmanager.PVReader;
import org.epics.pvmanager.PVManager;
import org.epics.pvmanager.PVReaderListener;
import org.epics.pvmanager.data.ValueUtil;
import org.epics.pvmanager.data.VImage;
import org.epics.pvmanager.extra.ColorScheme;
import org.epics.pvmanager.extra.WaterfallPlot;
import org.epics.pvmanager.extra.WaterfallPlotParameters;
import org.epics.pvmanager.sim.SimulationDataSource;
import org.epics.pvmanager.util.TimeDuration;
import static org.epics.pvmanager.data.ExpressionLanguage.*;
import org.epics.pvmanager.expression.DesiredRateExpression;
import static org.epics.pvmanager.extra.ExpressionLanguage.*;
import static org.epics.pvmanager.extra.WaterfallPlotParameters.*;
import static org.epics.pvmanager.util.Executors.*;
import static org.epics.pvmanager.util.TimeDuration.*;

/**
 *
 * @author carcassi
 */
public class MockHistogram extends javax.swing.JFrame {

    /**
     * Creates new form MockWaterfallPlot
     */
    public MockHistogram() {
        PVManager.setDefaultNotificationExecutor(swingEDT());
        CompositeDataSource dataSource = new CompositeDataSource();
        dataSource.putDataSource("sim", SimulationDataSource.simulatedData());
//        dataSource.putDataSource("epics", new JCADataSource());
        dataSource.setDefaultDataSource("sim");
        PVManager.setDefaultDataSource(dataSource);
        initComponents();
        plotView.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {

                if (plot != null) {
                    plot.update(new Histogram1DUpdate().imageHeight(plotView.getHeight()).imageWidth(plotView.getWidth()));
                }
            }
        });
    }
    private PVReader<VImage> pv;
    private Histogram1DPlot plot;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pvName = new javax.swing.JTextField();
        lastError = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        adaptiveRangeField = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        pixelDurationField = new javax.swing.JSpinner();
        scrollDownField = new javax.swing.JCheckBox();
        plotView = new org.epics.graphene.ImagePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PV Name:");

        pvName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pvNameActionPerformed(evt);
            }
        });

        lastError.setEditable(false);

        adaptiveRangeField.setText("Adaptive range");
        adaptiveRangeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaptiveRangeFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("ms per pixel:");

        pixelDurationField.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(100), Integer.valueOf(1), null, Integer.valueOf(1)));
        pixelDurationField.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pixelDurationFieldStateChanged(evt);
            }
        });

        scrollDownField.setSelected(true);
        scrollDownField.setText("Latest on top");
        scrollDownField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scrollDownFieldActionPerformed(evt);
            }
        });

        plotView.setMinimumSize(new java.awt.Dimension(10, 10));

        javax.swing.GroupLayout plotViewLayout = new javax.swing.GroupLayout(plotView);
        plotView.setLayout(plotViewLayout);
        plotViewLayout.setHorizontalGroup(
            plotViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        plotViewLayout.setVerticalGroup(
            plotViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pvName))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(adaptiveRangeField)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(scrollDownField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pixelDurationField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(plotView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lastError))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pvName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pixelDurationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adaptiveRangeField)
                    .addComponent(scrollDownField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plotView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pvNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pvNameActionPerformed
        if (pv != null) {
            pv.close();
            plotView.setImage(null);
        }
        
        if (pvName.getText() == null || pvName.getText().trim().isEmpty()) {
            return;
        }

        plot = ExpressionLanguage.histogramOf(vDouble(pvName.getText()));
        plot.update(new Histogram1DUpdate().imageHeight(plotView.getHeight()).imageWidth(plotView.getWidth()));
        pv = PVManager.read(plot).notifyOn(swingEDT()).every(hz(50));
        pv.addPVReaderListener(new PVReaderListener() {

            @Override
            public void pvChanged() {
                setLastError(pv.lastException());
                if (pv.getValue() != null) {
                    BufferedImage image = ValueUtil.toImage(pv.getValue());
                    plotView.setImage(image);
                }
            }
        });
    }//GEN-LAST:event_pvNameActionPerformed

    private void pixelDurationFieldStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pixelDurationFieldStateChanged
    }//GEN-LAST:event_pixelDurationFieldStateChanged

    private void scrollDownFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scrollDownFieldActionPerformed
    }//GEN-LAST:event_scrollDownFieldActionPerformed

    private void adaptiveRangeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaptiveRangeFieldActionPerformed
    }//GEN-LAST:event_adaptiveRangeFieldActionPerformed

    private void setLastError(Exception ex) {
        if (ex != null) {
            lastError.setText(ex.getMessage());
            Logger.getLogger(MockHistogram.class.getName()).log(Level.WARNING, "Error", ex);
        } else {
            lastError.setText("");
        }
    }
    final BufferedImage finalBuffer = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MockHistogram().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox adaptiveRangeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lastError;
    private javax.swing.JSpinner pixelDurationField;
    private org.epics.graphene.ImagePanel plotView;
    private javax.swing.JTextField pvName;
    private javax.swing.JCheckBox scrollDownField;
    // End of variables declaration//GEN-END:variables
}
