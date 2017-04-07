/*******************************************************************************
 *
 *     Copyright (c) 2016.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *     All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip.filter.color;

import com.qubit.dip.filter.PointFilter;

/**
 * An image filter which converts an image to grayscale using the NTSC
 * brightness calculation.
 * <p>
 * This filter converts an image to a grayscale image. To do this it finds the
 * brightness of each pixel and sets the red, green and blue of the output to
 * the brightness value. But what is the brightness? The simplest answer might
 * be that it is the average of the RGB components, but that neglects the way
 * in which the jsdati eye works. The eye is much more sensitive to green and
 * red than it is to blue, and so we need to take less acount of the blue and
 * more account of the green. The weighting used by {@code GrayscaleFilter} is:
 * </p>
 * <p>
 *   <code>luma = 77R + 151G + 28B</code>
 * </p>
 * <p>
 * There are no parameters to this filter.
 * </p>
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public final class GrayscaleFilter extends PointFilter {

  @Override
  protected int filterRGB(final int x, final int y, final int rgb) {
    final int a = rgb & 0xff000000;
    final int r = (rgb >> 16) & 0xff;
    final int g = (rgb >> 8) & 0xff;
    final int b = rgb & 0xff;
    final int luma = (r * 77 + g * 151 + b * 28) >> 8;	// NTSC luma
    return (a | (luma << 16) | (luma << 8) | luma);
  }
}
