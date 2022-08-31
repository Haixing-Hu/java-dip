////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ClassUtils;

/**
 * The abstract base class for implementing {@link ImageProcessor}.
 *
 * @author Haixing Hu
 */
public abstract class AbstractImageProcessor implements ImageProcessor {

  protected BufferedImage createCompatibleImage(BufferedImage image,
          @Nullable ColorModel colorModel) {
    if (colorModel == null) {
      colorModel = image.getColorModel();
    }
    final WritableRaster raster = colorModel.createCompatibleWritableRaster(
            image.getWidth(), image.getHeight());
    return new BufferedImage(colorModel, raster,
            colorModel.isAlphaPremultiplied(), null);
  }

  protected BufferedImage createCompatibleImage(BufferedImage image,
          int width, int height, @Nullable ColorModel colorModel) {
    if (colorModel == null) {
      colorModel = image.getColorModel();
    }
    final WritableRaster raster = colorModel.createCompatibleWritableRaster(width, height);
    return new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
  }

  /**
   * A convenience method for getting ARGB pixels from an image. This function
   * tries to avoid the performance penalty of
   * {@link BufferedImage#getRGB(int, int, int, int, int[], int, int)} unmanaging the image.
   *
   * @param image  a BufferedImage object
   * @param x      the left edge of the pixel block
   * @param y      the right edge of the pixel block
   * @param width  the width of the pixel arry
   * @param height the height of the pixel arry
   * @param pixels the array to hold the returned pixels. May be null.
   * @return the pixels
   * @see #setRGB
   */
  public int[] getRGB(BufferedImage image, int x, int y, int width, int height,
          int[] pixels) {
    int type = image.getType();
    if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB) {
      return (int[]) image.getRaster()
                          .getDataElements(x, y, width, height, pixels);
    }
    return image.getRGB(x, y, width, height, pixels, 0, width);
  }

  /**
   * A convenience method for setting ARGB pixels in an image. This function
   * tries to avoid the performance penalty of
   * {@link BufferedImage#setRGB(int, int, int, int, int[], int, int)} unmanaging the image.
   *
   * @param image  a BufferedImage object
   * @param x      the left edge of the pixel block
   * @param y      the right edge of the pixel block
   * @param width  the width of the pixel arry
   * @param height the height of the pixel arry
   * @param pixels the array of pixels to set
   * @see #getRGB
   */
  public void setRGB(BufferedImage image, int x, int y, int width, int height,
          int[] pixels) {
    int type = image.getType();
    if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB) {
      image.getRaster().setDataElements(x, y, width, height, pixels);
    } else {
      image.setRGB(x, y, width, height, pixels, 0, width);
    }
  }

  public String toString() {
    return ClassUtils.getShortCanonicalName(this.getClass());
  }
}
