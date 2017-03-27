/*******************************************************************************
 *
 *     Copyright (c) 2016.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *     All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip.utils;

/**
 * Some more useful math functions for image processing.
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public class PixelUtils {

  /**
   * Clamp a value to the range 0..255.
   *
   * @param c
   *    the value to be clamped.
   * @return
   *    the result value.
   */
  public static int clamp(int c) {
    if (c < 0) {
      return 0;
    } else if (c > 255) {
      return 255;
    } else {
      return c;
    }
  }
}
