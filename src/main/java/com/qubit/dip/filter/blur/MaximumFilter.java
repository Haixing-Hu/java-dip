////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter.blur;

import com.qubit.dip.filter.WholeImageFilter;
import com.qubit.dip.utils.Operation;

import java.awt.Rectangle;

import static com.qubit.dip.utils.ImageUtils.combinePixels;

/**
 * An image filter which is often used for noise reduction.
 * <p>
 * This filter replaces each pixel by the maximum of the input pixel and its
 * eight neighbours. Each of the RGB channels is considered separately.
 * </p>
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public class MaximumFilter extends WholeImageFilter {

  protected int[] filterPixels(int width, int height, int[] inPixels,
          Rectangle transformedSpace) {
    int index = 0;
    int[] outPixels = new int[width * height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = 0xff000000;
        for (int dy = -1; dy <= 1; dy++) {
          int iy = y + dy;
          if (0 <= iy && iy < height) {
            int ioffset = iy * width;
            for (int dx = -1; dx <= 1; dx++) {
              int ix = x + dx;
              if (0 <= ix && ix < width) {
                pixel = combinePixels(pixel, inPixels[ioffset + ix], Operation.MAX);
              }
            }
          }
        }
        outPixels[index++] = pixel;
      }
    }
    return outPixels;
  }

}
