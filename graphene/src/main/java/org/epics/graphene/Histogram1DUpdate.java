/**
 * Copyright (C) 2012 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.graphene;

/**
 *
 * @author carcassi
 */
public class Histogram1DUpdate {

    private Point1DDataset recalculateFrom;
    
    /**
     *Updates the dataset used for the corresponding Histogram.
     * @param dataset
     * @return this 
     */
    public Histogram1DUpdate recalculateFrom(Point1DDataset dataset) {
        recalculateFrom = dataset;
        return this;
    }

    /**
     *
     * @return recalculateFrom - current dataset.
     */
    public Point1DDataset getDataset() {
        return recalculateFrom;
    }
    
}
