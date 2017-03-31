/*******************************************************************************
 *
 *     Copyright (c) 2016.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *     All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip;

import javax.annotation.Nullable;
import java.awt.image.BufferedImage;

/**
 * The interface of image processors.
 *
 * @author Haixing Hu
 */
public interface ImageProcessor {

  /**
   * Process a image.
   *
   * @param src
   *    the source image.
   * @param dst
   *    the destination image; if this argument is {@code null}, a new
   *    destination image will be created and processed.
   * @return
   *    the processed destination image.
   */
  BufferedImage process(BufferedImage src, @Nullable BufferedImage dst);
}
