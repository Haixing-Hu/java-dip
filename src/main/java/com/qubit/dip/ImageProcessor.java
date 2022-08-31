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
import javax.annotation.Nullable;

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
