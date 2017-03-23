/*******************************************************************************
 *
 *     Copyright (c) 2016.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *     All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip;

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
   * @param input
   *    the input image.
   * @return
   *    the output image.
   */
  BufferedImage process(BufferedImage input);
}
