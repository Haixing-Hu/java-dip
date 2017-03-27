/*******************************************************************************
 *
 *     Copyright (c) 2016.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *     All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip.filter;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * The base class of image filters which filter images point by point.
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public abstract class PointFilter extends ImageFilter {

  @Override
  public BufferedImage process(final BufferedImage input) {
    final BufferedImage output = createCompatibleImage(input, null);
    final int width = input.getWidth();
    final int height = input.getHeight();
    final int[] pixels = new int[width];
    if (input.getType() == BufferedImage.TYPE_INT_ARGB) {
      final WritableRaster inRaster = input.getRaster();
      final WritableRaster outRaster = output.getRaster();
      for (int y = 0; y < height; ++y) {
        // We try to avoid calling getRGB on images as it causes them to become
        // unmanaged, causing horrible performance problems.
        inRaster.getDataElements(0, y, width, 1, pixels);
        for (int x = 0; x < width; ++x) {
          pixels[x] = filterRGB(x, y, pixels[x]);
        }
        outRaster.setDataElements(0, y, width, 1, pixels);
      }
    } else {
      for (int y = 0; y < height; ++y) {
        input.getRGB(0, y, width, 1, pixels, 0, width);
        for (int x = 0; x < width; ++x) {
          pixels[x] = filterRGB(x, y, pixels[x]);
        }
        output.setRGB(0, y, width, 1, pixels, 0, width);
      }
    }
    return output;
  }

  protected abstract int filterRGB(int x, int y, int rgb);
}
