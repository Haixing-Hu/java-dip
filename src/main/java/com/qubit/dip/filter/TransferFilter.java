////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter;

import java.awt.image.BufferedImage;
import javax.annotation.Nullable;

import static com.qubit.dip.utils.ImageUtils.clamp;

/**
 * The base class of image filters which transforms colors of images point by
 * point.
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public abstract class TransferFilter extends PointFilter {

  protected int[] rTable, gTable, bTable;
  protected boolean initialized = false;

  public int filterRGB(int x, int y, int rgb) {
    final int a = rgb & 0xff000000;
    int r = (rgb >> 16) & 0xff;
    int g = (rgb >> 8) & 0xff;
    int b = rgb & 0xff;
    r = rTable[r];
    g = gTable[g];
    b = bTable[b];
    return (a | (r << 16) | (g << 8) | b);
  }

  public BufferedImage process(final BufferedImage src, @Nullable BufferedImage dst) {
    if (! initialized) {
      initialize();
    }
    return super.process(src, dst);
  }

  protected void initialize() {
    initialized = true;
    rTable = gTable = bTable = makeTable();
  }

  protected int[] makeTable() {
    int[] table = new int[256];
    for (int i = 0; i < 256; i++) {
      table[i] = clamp((int)(255 * transferFunction(i / 255.0f)));
    }
    return table;
  }

  public int[] getLUT() {
    if (! initialized) {
      initialize();
    }
    int[] lut = new int[256];
    for ( int i = 0; i < 256; i++ ) {
      lut[i] = filterRGB( 0, 0, (i << 24) | (i << 16) | (i << 8) | i );
    }
    return lut;
  }

  protected float transferFunction(final float v) {
    return 0;
  }
}
