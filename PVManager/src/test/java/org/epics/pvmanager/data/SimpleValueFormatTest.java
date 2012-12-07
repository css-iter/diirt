/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.pvmanager.data;

import java.text.DateFormat;
import java.util.Arrays;
import org.epics.pvmanager.util.NumberFormats;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.epics.pvmanager.data.ValueFactory.*;
import org.epics.util.array.ArrayFloat;
import org.epics.util.array.ArrayInt;
import org.epics.util.array.ListFloat;
import org.mockito.Mockito;

/**
 *
 * @author carcassi
 */
public class SimpleValueFormatTest {
    Display display = newDisplay(Double.MIN_VALUE, Double.MIN_VALUE,
            Double.MIN_VALUE, "", NumberFormats.format(3), Double.MAX_VALUE,
            Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE, Double.MAX_VALUE);
    
    Display displayInt = newDisplay(Double.MIN_VALUE, Double.MIN_VALUE,
            Double.MIN_VALUE, "", NumberFormats.format(0), Double.MAX_VALUE,
            Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE, Double.MAX_VALUE);

    @Test
    public void defaultPrecision() {
        ValueFormat f = new SimpleValueFormat(3);
        assertThat(f.format(newVDouble(1234.5678, display)), equalTo("1234.568"));
        assertThat(f.format(newVIntArray(new ArrayInt(1, 2, 3), alarmNone(), timeNow(), displayInt)), equalTo("[1, 2, 3]"));
        assertThat(f.format(newVIntArray(new ArrayInt(1), alarmNone(), timeNow(), displayInt)), equalTo("[1]"));
        assertThat(f.format(newVIntArray(new ArrayInt(1, 2, 3, 4, 5), alarmNone(), timeNow(), displayInt)), equalTo("[1, 2, 3, ...]"));
        assertThat(f.format(newVFloatArray(new ArrayFloat(new float[] {1, 2, 3}), alarmNone(), timeNow(), display)), equalTo("[1.000, 2.000, 3.000]"));
        assertThat(f.format(newVFloatArray(new ArrayFloat(new float[] {1}), alarmNone(), timeNow(), display)), equalTo("[1.000]"));
        assertThat(f.format(newVFloatArray(new ArrayFloat(new float[] {1, 2, 3, 4, 5}), alarmNone(), timeNow(), display)), equalTo("[1.000, 2.000, 3.000, ...]"));
        assertThat(f.format(newVDoubleArray(new double[] {1, 2, 3}, display)), equalTo("[1.000, 2.000, 3.000]"));
        assertThat(f.format(newVDoubleArray(new double[] {1}, display)), equalTo("[1.000]"));
        assertThat(f.format(newVDoubleArray(new double[] {1, 2, 3, 4, 5}, display)), equalTo("[1.000, 2.000, 3.000, ...]"));
        assertThat(f.format(newVStringArray(Arrays.asList("A", "B", "C"), alarmNone(), timeNow())), equalTo("[A, B, C]"));
        assertThat(f.format(newVStringArray(Arrays.asList("A"), alarmNone(), timeNow())), equalTo("[A]"));
        assertThat(f.format(newVStringArray(Arrays.asList("A", "B", "C", "D", "E"), alarmNone(), timeNow())), equalTo("[A, B, C, ...]"));
        assertThat(f.format(newVEnumArray(new ArrayInt(2, 0, 0), Arrays.asList("A", "B", "C"), alarmNone(), timeNow())), equalTo("[C, A, A]"));
        assertThat(f.format(newVEnumArray(new ArrayInt(2), Arrays.asList("A", "B", "C"), alarmNone(), timeNow())), equalTo("[C]"));
        assertThat(f.format(newVEnumArray(new ArrayInt(2, 0, 0, 1, 0), Arrays.asList("A", "B", "C"), alarmNone(), timeNow())), equalTo("[C, A, A, ...]"));
    }

    @Test
    public void testMandatedPrecision() {
        Display display = newDisplay(Double.MIN_VALUE, Double.MIN_VALUE,
                Double.MIN_VALUE, "", NumberFormats.format(3), Double.MAX_VALUE,
                Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE, Double.MAX_VALUE);
        Display displayInt = newDisplay(Double.MIN_VALUE, Double.MIN_VALUE,
                Double.MIN_VALUE, "", NumberFormats.format(0), Double.MAX_VALUE,
                Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE, Double.MAX_VALUE);
        ValueFormat f = new SimpleValueFormat(3);
        f.setNumberFormat(NumberFormats.format(2));
        assertThat(f.format(newVDouble(1234.5678, display)), equalTo("1234.57"));
        assertThat(f.format(newVIntArray(new ArrayInt(1, 2, 3), alarmNone(), timeNow(), displayInt)), equalTo("[1.00, 2.00, 3.00]"));
        assertThat(f.format(newVIntArray(new ArrayInt(1), alarmNone(), timeNow(), displayInt)), equalTo("[1.00]"));
        assertThat(f.format(newVIntArray(new ArrayInt(1, 2, 3, 4, 5), alarmNone(), timeNow(), displayInt)), equalTo("[1.00, 2.00, 3.00, ...]"));
        assertThat(f.format(newVFloatArray(new ArrayFloat(new float[] {1, 2, 3}), alarmNone(), timeNow(), display)), equalTo("[1.00, 2.00, 3.00]"));
        assertThat(f.format(newVFloatArray(new ArrayFloat(new float[] {1}), alarmNone(), timeNow(), display)), equalTo("[1.00]"));
        assertThat(f.format(newVFloatArray(new ArrayFloat(new float[] {1, 2, 3, 4, 5}), alarmNone(), timeNow(), display)), equalTo("[1.00, 2.00, 3.00, ...]"));
        assertThat(f.format(newVDoubleArray(new double[] {1, 2, 3}, display)), equalTo("[1.00, 2.00, 3.00]"));
        assertThat(f.format(newVDoubleArray(new double[] {1}, display)), equalTo("[1.00]"));
        assertThat(f.format(newVDoubleArray(new double[] {1, 2, 3, 4, 5}, display)), equalTo("[1.00, 2.00, 3.00, ...]"));
        assertThat(f.format(newVStringArray(Arrays.asList("A", "B", "C"), alarmNone(), timeNow())), equalTo("[A, B, C]"));
        assertThat(f.format(newVStringArray(Arrays.asList("A"), alarmNone(), timeNow())), equalTo("[A]"));
        assertThat(f.format(newVStringArray(Arrays.asList("A", "B", "C", "D", "E"), alarmNone(), timeNow())), equalTo("[A, B, C, ...]"));
    }
    
    @Test
    public void parseVDouble1() {
        ValueFormat f = new SimpleValueFormat(3);
        VDouble reference = newVDouble(3.0);
        assertThat(f.parseObject("3.14", reference), equalTo((Object) 3.14));
        assertThat(f.parseDouble("3.14"), equalTo((Object) 3.14));
        assertThat(f.parseDouble("1333"), equalTo((Object) 1333.0));
    }
    
    @Test
    public void parseVFloat1() {
        ValueFormat f = new SimpleValueFormat(3);
        VFloat reference = Mockito.mock(VFloat.class);
        assertThat(f.parseObject("3.14", reference), equalTo((Object) 3.14f));
        assertThat(f.parseFloat("3.14"), equalTo((Object) 3.14f));
        assertThat(f.parseFloat("1333"), equalTo((Object) 1333.0f));
    }
    
    @Test
    public void parseVInt1() {
        ValueFormat f = new SimpleValueFormat(3);
        VInt reference = Mockito.mock(VInt.class);
        assertThat(f.parseObject("314", reference), equalTo((Object) 314));
        assertThat(f.parseInt("314"), equalTo((Object) 314));
        assertThat(f.parseInt("1333"), equalTo((Object) 1333));
    }
    
    @Test
    public void parseVShort1() {
        ValueFormat f = new SimpleValueFormat(3);
        VShort reference = Mockito.mock(VShort.class);
        assertThat(f.parseObject("314", reference), equalTo((Object) (short) 314));
        assertThat(f.parseShort("314"), equalTo((Object) (short) 314));
        assertThat(f.parseShort("1333"), equalTo((Object) (short) 1333));
    }
    
    @Test
    public void parseVByte1() {
        ValueFormat f = new SimpleValueFormat(3);
        VString reference = Mockito.mock(VString.class);
        assertThat(f.parseObject("Testing", reference), equalTo((Object) "Testing"));
        assertThat(f.parseString("Testing"), equalTo("Testing"));
        assertThat(f.parseString("Foo"), equalTo("Foo"));
    }

}