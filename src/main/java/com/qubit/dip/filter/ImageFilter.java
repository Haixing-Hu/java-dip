/*******************************************************************************
 *
 *     Copyright (c) 2016.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *     All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip.filter;

import com.qubit.dip.ImageProcessor;
import org.apache.commons.lang3.ClassUtils;

import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * The interface of image filters.
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public abstract class ImageFilter implements ImageProcessor {

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

  public String toString() {
    return ClassUtils.getShortCanonicalName(this.getClass());
  }
}
