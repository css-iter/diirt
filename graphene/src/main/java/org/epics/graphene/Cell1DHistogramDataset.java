/**
 * Copyright (C) 2012-14 graphene developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.graphene;

import org.epics.util.stats.Range;
import org.epics.util.array.ArrayDouble;
import org.epics.util.array.IteratorNumber;
import org.epics.util.array.ListDouble;
import org.epics.util.array.ListNumber;
import org.epics.util.stats.Ranges;

/**
 *
 * @author carcassi
 */
class Cell1DHistogramDataset implements Cell1DDataset {
    
    private Statistics statistics;
    private Range xRange;
    private ListDouble xBoundaries;
    
    private double minValueRange;
    private double maxValueRange;
    private int minCountRange;
    private int maxCountRange;
    private double[] binValueBoundary;
    
    
    private double[] values;
    private boolean autoValueRange = true;
    private int nBins = 100;

    public Cell1DHistogramDataset(Point1DDataset dataset) {
        calculateFrom(dataset);
    }

    private void calculateFrom(Point1DDataset dataset) {
        if (dataset.getStatistics() == null) {
            throw new NullPointerException("dataset is null");
        }
        
        IteratorNumber newValues = dataset.getValues().iterator();
        if (autoValueRange) {
            this.minValueRange = dataset.getStatistics().getMinimum().doubleValue();
            this.maxValueRange = dataset.getStatistics().getMaximum().doubleValue();
            binValueBoundary = RangeUtil.createBins(minValueRange, maxValueRange, nBins);
            xBoundaries = new ArrayDouble(binValueBoundary);
            xRange = Ranges.range(binValueBoundary[0], binValueBoundary[nBins]);
        }
        values = new double[nBins];
        while (newValues.hasNext()) {
            addValueToBin(newValues.nextDouble());
        }

        statistics = StatisticsUtil.statisticsOf(new ArrayDouble(values));
    }
    
    private void addValueToBin(double value) {
        // Discard value outsie the binning area
        if (!Ranges.contains(xRange, value)) {
            return;
        }
        
        int bin = (int) Math.floor(MathUtil.scale(value, xRange.getMinimum().doubleValue(), xRange.getMaximum().doubleValue(), nBins));
        if (bin == nBins) {
            bin--;
        }
        
        values[bin]++;
    }

    @Override
    public double getValue(int x) {
        return values[x];
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public ListNumber getXBoundaries() {
        return xBoundaries;
    }

    @Override
    public Range getXRange() {
        return xRange;
    }

    @Override
    public int getXCount() {
        return values.length;
    }
    
}
