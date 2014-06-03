/**
 * Copyright (C) 2010-14 pvmanager developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */

package org.epics.util.array;

/**
 * Provides a view of a wrapped list that only exposes the elements with
 * the given indexes.
 *
 * @author carcassi
 */
class ListView {

    /**
     * A ListView implementation for doubles.
     */
    static class Double extends ListDouble {
        private final ListDouble list;
        private final ListInt indexes;

        public Double(ListDouble list, ListInt indexes) {
            this.list = list;
            this.indexes = indexes;
        }
        
        @Override
        public double getDouble(int index) {
            return list.getDouble(indexes.getInt(index));
        }

        @Override
        public int size() {
            return indexes.size();
        }
        
    }

    /**
     * A ListView implementation for floats.
     */
    static class Float extends ListFloat {
        private final ListFloat list;
        private final ListInt indexes;

        public Float(ListFloat list, ListInt indexes) {
            this.list = list;
            this.indexes = indexes;
        }
        
        @Override
        public float getFloat(int index) {
            return list.getFloat(indexes.getInt(index));
        }

        @Override
        public int size() {
            return indexes.size();
        }
        
    }
}
