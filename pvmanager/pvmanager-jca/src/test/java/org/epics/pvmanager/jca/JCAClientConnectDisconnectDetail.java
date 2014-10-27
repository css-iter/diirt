/**
 * Copyright (C) 2010-14 pvmanager developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.pvmanager.jca;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.epics.pvmanager.PVReader;
import org.epics.pvmanager.PVManager;
import org.epics.pvmanager.PVReaderListener;
import static org.epics.pvmanager.ExpressionLanguage.*;
import org.epics.pvmanager.PVReaderEvent;
import org.epics.pvmanager.test.CountDownPVReaderListener;
import static org.diirt.util.time.TimeDuration.*;

/**
 * This class tests opening and closing the same channel multiple times
 * to check whether any of the initial events are lost. This test is designed
 * to give some debug information if problems appear.
 * 
 * @author carcassi
 */
public class JCAClientConnectDisconnectDetail {
    public static void main(String[] args) throws Exception {
        JCADataSource jca = new JCADataSource();
        PVManager.setDefaultDataSource(jca);
        
        String channelName = "TEST_1";


        final AtomicInteger connectCount = new AtomicInteger(0);
        final AtomicInteger valueNotificationCount = new AtomicInteger(0);
        final AtomicInteger connectionNotificationCount = new AtomicInteger(0);
        final AtomicInteger totalNotificationCount = new AtomicInteger(0);
        
        int countToPrint = 10;
        while (true) {
            final CountDownLatch latch = new CountDownLatch(1);
            
            final PVReader<?> pv = PVManager.read(channel(channelName))
                        .readListener(new PVReaderListener<Object>() {
                            @Override
                            public void pvChanged(PVReaderEvent<Object> event) {
                                if (event.isConnectionChanged()) {
                                    connectionNotificationCount.incrementAndGet();
                                }
                                if (event.isValueChanged()) {
                                    valueNotificationCount.incrementAndGet();
                                }
                                totalNotificationCount.incrementAndGet();
                                if (event.getPvReader().isConnected() && event.getPvReader().getValue() != null) {
                                    latch.countDown();
                                }
                            }
                        }).maxRate(ofHertz(100));
            connectCount.incrementAndGet();
            boolean notified = latch.await(500, TimeUnit.MILLISECONDS);
            if (!notified) {
                System.out.println("Connection: " + pv.isConnected() + " Value: " + pv.getValue());
                System.out.println(jca.getChannels().get(channelName).getProperties());
            }
            pv.close();
            
            if (connectCount.get() == countToPrint) {
                System.out.println("Connections: " + connectCount + " Conn notifications: " + connectionNotificationCount 
                        + " Value notifications: " + valueNotificationCount + " Total notifications: " +totalNotificationCount);
                countToPrint += 10;
            }
        }
    }
}
