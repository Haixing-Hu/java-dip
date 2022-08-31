////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.annotation.Nullable;

/**
 * The base class of image filters which need to have the whole image in memory
 * to do their stuff.
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public abstract class WholeImageFilter extends ImageFilter {

  /**
   * The output image bounds.
   */
  protected Rectangle transformedSpace;

  /**
   * The input image bounds.
   */
  protected Rectangle originalSpace;

  /**
   * Construct a WholeImageFilter.
   */
  public WholeImageFilter() {
  }

  public BufferedImage process(final BufferedImage src, @Nullable BufferedImage dst) {
    int width = src.getWidth();
    int height = src.getHeight();
    int type = src.getType();
    WritableRaster srcRaster = src.getRaster();
    originalSpace = new Rectangle(0, 0, width, height);
    transformedSpace = new Rectangle(0, 0, width, height);
    transformSpace(transformedSpace);
    if (dst == null) {
      dst = createCompatibleImage(src, transformedSpace.width,
              transformedSpace.height, null);
    }
    WritableRaster dstRaster = dst.getRaster();
    int[] inPixels = getRGB(src, 0, 0, width, height, null);
    inPixels = filterPixels(width, height, inPixels, transformedSpace);
    setRGB(dst, 0, 0, transformedSpace.width, transformedSpace.height,
            inPixels);
    return dst;
  }

  /**
   * Calculate output bounds for given input bounds.
   *
   * @param rect input and output rectangle
   */
  protected void transformSpace(Rectangle rect) {
    //  empty
  }

  /**
   * Actually filter the pixels.
   *
   * @param width            the image width
   * @param height           the image height
   * @param inPixels         the image pixels
   * @param transformedSpace the output bounds
   * @return the output pixels
   */
  protected abstract int[] filterPixels(int width, int height, int[] inPixels,
          Rectangle transformedSpace);
}
