/**
 * Copyright (C) 2010-18 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.diirt.vtype;

import java.util.List;
import org.diirt.util.array.ListNumber;

/**
 * Numeric array with alarm, timestamp, display and control information.
 * <p>
 * This class allows to use any numeric array (i.e. {@link VIntArray} or
 * {@link VDoubleArray}) through the same interface.
 *
 * @author carcassi
 */
public interface VNumberArray extends Array, Alarm, Time, Display, VType {
    @Override
    ListNumber getData();

    /**
     * Returns the boundaries of each cell.
     *
     * @return the dimension display; can't be null
     */
    List<ArrayDimensionDisplay> getDimensionDisplay();
}
