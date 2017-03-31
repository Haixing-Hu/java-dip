/*******************************************************************************
 *
 *     Copyright (c) 2016.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *     All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip.filter;

import javax.annotation.Nullable;
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
  public BufferedImage process(final BufferedImage src, @Nullable
          BufferedImage dst) {
    if (dst == null) {
      dst = createCompatibleImage(src, null);
    }
    final int width = src.getWidth();
    final int height = src.getHeight();
    final int[] pixels = new int[width];
    if (src.getType() == BufferedImage.TYPE_INT_ARGB) {
      final WritableRaster inRaster = src.getRaster();
      final WritableRaster outRaster = dst.getRaster();
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
        src.getRGB(0, y, width, 1, pixels, 0, width);
        for (int x = 0; x < width; ++x) {
          pixels[x] = filterRGB(x, y, pixels[x]);
        }
        dst.setRGB(0, y, width, 1, pixels, 0, width);
      }
    }
    return dst;
  }

  protected abstract int filterRGB(int x, int y, int rgb);
}
