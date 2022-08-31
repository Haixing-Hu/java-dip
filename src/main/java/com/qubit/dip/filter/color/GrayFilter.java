////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter.color;

import com.qubit.dip.filter.PointFilter;

/**
 * An image filter which 'grays out' an image by averaging each pixel with white.
 * <p>
 * This filter produces a 'grayed-out' version of an image. It's useful for
 * producing disabled icons for buttons and simply works by averaging each pixel
 * with white. There are no parameters to this filter.
 * </p>
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public final class GrayFilter extends PointFilter {

  @Override
  protected int filterRGB(final int x, final int y, final int rgb) {
    final int a = rgb & 0xff000000;
    int r = (rgb >> 16) & 0xff;
    int g = (rgb >> 8) & 0xff;
    int b = rgb & 0xff;
    r = (r + 255) / 2;
    g = (g + 255) / 2;
    b = (b + 255) / 2;
    return (a | (r << 16) | (g << 8) | b);
  }
}
