////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter.blur;

import static java.lang.Math.abs;

import com.qubit.dip.filter.WholeImageFilter;
import java.awt.Rectangle;

/**
 * An image filter which is often used for noise reduction.
 * <p>
 * This filter replaces each pixel by the median of the input pixel and its
 * eight neighbours. Each of the RGB channels is considered separately.
 * </p>
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public class MedianFilter extends WholeImageFilter {

  private int median(int[] array) {
    int max, maxIndex;
    for (int i = 0; i < 4; i++) {
      max = 0;
      maxIndex = 0;
      for (int j = 0; j < 9; j++) {
        if (array[j] > max) {
          max = array[j];
          maxIndex = j;
        }
      }
      array[maxIndex] = 0;
    }
    max = 0;
    for (int i = 0; i < 9; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }

  private int rgbMedian(int[] r, int[] g, int[] b) {
    int sum, index = 0, min = Integer.MAX_VALUE;
    for (int i = 0; i < 9; i++) {
      sum = 0;
      for (int j = 0; j < 9; j++) {
        sum += abs(r[i] - r[j]);
        sum += abs(g[i] - g[j]);
        sum += abs(b[i] - b[j]);
      }
      if (sum < min) {
        min = sum;
        index = i;
      }
    }
    return index;
  }

  protected int[] filterPixels(int width, int height, int[] inPixels,
          Rectangle transformedSpace) {
    int index = 0;
    int[] argb = new int[9];
    int[] r = new int[9];
    int[] g = new int[9];
    int[] b = new int[9];
    int[] outPixels = new int[width * height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int k = 0;
        for (int dy = -1; dy <= 1; dy++) {
          int iy = y + dy;
          if (0 <= iy && iy < height) {
            int ioffset = iy * width;
            for (int dx = -1; dx <= 1; dx++) {
              int ix = x + dx;
              if (0 <= ix && ix < width) {
                int rgb = inPixels[ioffset + ix];
                argb[k] = rgb;
                r[k] = (rgb >> 16) & 0xff;
                g[k] = (rgb >> 8) & 0xff;
                b[k] = rgb & 0xff;
                k++;
              }
            }
          }
        }
        while (k < 9) {
          argb[k] = 0xff000000;
          r[k] = g[k] = b[k] = 0;
          k++;
        }
        outPixels[index++] = argb[rgbMedian(r, g, b)];
      }
    }
    return outPixels;
  }

}
