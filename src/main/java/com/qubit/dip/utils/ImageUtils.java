////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.utils;

import java.awt.Color;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Some more useful math functions for image processing.
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public class ImageUtils {

  private static final Random randomGenerator = new Random();

  /**
   * Clamp a value to an interval.
   *
   * @param value
   *    the input parameter.
   * @param lower
   *    the lower clamp threshold.
   * @param upper
   *    the upper clamp threshold.
   * @return
   *  the clamped value.
   */
  public static byte clamp(byte value, byte lower, byte upper) {
    return (value < lower ? lower : (value > upper ? upper : value));
  }

  /**
   * Clamp a value to an interval.
   *
   * @param value
   *    the input parameter.
   * @param lower
   *    the lower clamp threshold.
   * @param upper
   *    the upper clamp threshold.
   * @return
   *  the clamped value.
   */
  public static short clamp(short value, short lower, short upper) {
    return (value < lower ? lower : (value > upper ? upper : value));
  }

  /**
   * Clamp a value to an interval.
   *
   * @param value
   *    the input parameter.
   * @param lower
   *    the lower clamp threshold.
   * @param upper
   *    the upper clamp threshold.
   * @return
   *  the clamped value.
   */
  public static int clamp(int value, int lower, int upper) {
    return (value < lower ? lower : (value > upper ? upper : value));
  }

  /**
   * Clamp a value to an interval.
   *
   * @param value
   *    the input parameter.
   * @param lower
   *    the lower clamp threshold.
   * @param upper
   *    the upper clamp threshold.
   * @return
   *  the clamped value.
   */
  public static long clamp(long value, long lower, long upper) {
    return (value < lower ? lower : (value > upper ? upper : value));
  }

  /**
   * Clamp a value to an interval.
   *
   * @param value
   *    the input parameter.
   * @param lower
   *    the lower clamp threshold.
   * @param upper
   *    the upper clamp threshold.
   * @return
   *  the clamped value.
   */
  public static float clamp(float value, float lower, float upper) {
    return (value < lower ? lower : (value > upper ? upper : value));
  }

  /**
   * Clamp a value to an interval.
   *
   * @param value
   *    the input parameter.
   * @param lower
   *    the lower clamp threshold.
   * @param upper
   *    the upper clamp threshold.
   * @return
   *  the clamped value.
   */
  public static double clamp(double value, double lower, double upper) {
    return (value < lower ? lower : (value > upper ? upper : value));
  }


  /**
   * Clamp a value to the interval <code>[0, 255]</code>.
   *
   * @param value
   *    the input parameter.
   * @return
   *  the clamped value.
   */
  public static byte clamp(byte value) {
    return (value < 0 ? 0 : (value > 255 ? (byte)255 : value));
  }

  /**
   * Clamp a value to the interval <code>[0, 255]</code>.
   *
   * @param value
   *    the input parameter.
   * @return
   *  the clamped value.
   */
  public static short clamp(short value) {
    return (value < 0 ? 0 : (value > 255 ? (short)255 : value));
  }

  /**
   * Clamp a value to the interval <code>[0, 255]</code>.
   *
   * @param value
   *    the input parameter.
   * @return
   *  the clamped value.
   */
  public static int clamp(int value) {
    return (value < 0 ? 0 : (value > 255 ? 255 : value));
  }

  /**
   * Clamp a value to the interval <code>[0, 255]</code>.
   *
   * @param value
   *    the input parameter.
   * @return
   *  the clamped value.
   */
  public static long clamp(long value) {
    return (value < 0 ? 0 : (value > 255 ? 255 : value));
  }

  /**
   * Clamp a value to the interval <code>[0, 255]</code>.
   *
   * @param value
   *    the input parameter.
   * @return
   *  the clamped value.
   */
  public static float clamp(float value) {
    return (value < 0 ? 0 : (value > 255 ? 255 : value));
  }

  /**
   * Clamp a value to the interval <code>[0, 255]</code>.
   *
   * @param value
   *    the input parameter.
   * @return
   *  the clamped value.
   */
  public static double clamp(double value) {
    return (value < 0 ? 0 : (value > 255 ? 255 : value));
  }

  // Catmull-Rom splines
  private final static float m00 = -0.5f;
  private final static float m01 =  1.5f;
  private final static float m02 = -1.5f;
  private final static float m03 =  0.5f;
  private final static float m10 =  1.0f;
  private final static float m11 = -2.5f;
  private final static float m12 =  2.0f;
  private final static float m13 = -0.5f;
  private final static float m20 = -0.5f;
  private final static float m21 =  0.0f;
  private final static float m22 =  0.5f;
  private final static float m23 =  0.0f;
  private final static float m30 =  0.0f;
  private final static float m31 =  1.0f;
  private final static float m32 =  0.0f;
  private final static float m33 =  0.0f;

  /**
   * Compute a Catmull-Rom spline.
   *
   * @param x        the input parameter
   * @param numKnots the number of knots in the spline
   * @param knots    the array of knots
   * @return the spline value
   */
  public static float spline(float x, int numKnots, float[] knots) {
    int span;
    int numSpans = numKnots - 3;
    float k0, k1, k2, k3;
    float c0, c1, c2, c3;
    if (numSpans < 1) {
      throw new IllegalArgumentException("Too few knots in spline");
    }
    x = clamp(x, 0, 1) * numSpans;
    span = (int) x;
    if (span > numKnots - 4) {
      span = numKnots - 4;
    }
    x -= span;
    k0 = knots[span];
    k1 = knots[span + 1];
    k2 = knots[span + 2];
    k3 = knots[span + 3];
    c3 = m00 * k0 + m01 * k1 + m02 * k2 + m03 * k3;
    c2 = m10 * k0 + m11 * k1 + m12 * k2 + m13 * k3;
    c1 = m20 * k0 + m21 * k1 + m22 * k2 + m23 * k3;
    c0 = m30 * k0 + m31 * k1 + m32 * k2 + m33 * k3;
    return ((c3 * x + c2) * x + c1) * x + c0;
  }

  /**
   * Compute a Catmull-Rom spline, but with variable knot spacing.
   *
   * @param x        the input parameter
   * @param numKnots the number of knots in the spline
   * @param xknots   the array of knot x values
   * @param yknots   the array of knot y values
   * @return the spline value
   */
  public static float spline(float x, int numKnots, int[] xknots, int[] yknots) {
    int span;
    int numSpans = numKnots - 3;
    float k0, k1, k2, k3;
    float c0, c1, c2, c3;
    if (numSpans < 1) {
      throw new IllegalArgumentException("Too few knots in spline");
    }
    for (span = 0; span < numSpans; span++) {
      if (xknots[span + 1] > x) {
        break;
      }
    }
    if (span > numKnots - 3) {
      span = numKnots - 3;
    }
    float t = (x - xknots[span]) / (xknots[span + 1] - xknots[span]);
    span--;
    if (span < 0) {
      span = 0;
      t = 0;
    }
    k0 = yknots[span];
    k1 = yknots[span + 1];
    k2 = yknots[span + 2];
    k3 = yknots[span + 3];
    c3 = m00 * k0 + m01 * k1 + m02 * k2 + m03 * k3;
    c2 = m10 * k0 + m11 * k1 + m12 * k2 + m13 * k3;
    c1 = m20 * k0 + m21 * k1 + m22 * k2 + m23 * k3;
    c0 = m30 * k0 + m31 * k1 + m32 * k2 + m33 * k3;
    return ((c3 * t + c2) * t + c1) * t + c0;
  }


  // Return rgb1 painted onto rgb2
  public static int combinePixels(int rgb1, int rgb2, Operation op) {
    return combinePixels(rgb1, rgb2, op, 0xff);
  }

  public static int combinePixels(int rgb1, int rgb2, Operation op, int extraAlpha,
          int channelMask) {
    return (rgb2 & ~channelMask) | combinePixels(rgb1 & channelMask, rgb2, op,
            extraAlpha);
  }

  public static int combinePixels(int rgb1, int rgb2, Operation op, int extraAlpha) {
    if (op == Operation.REPLACE) {
      return rgb1;
    }
    int a1 = (rgb1 >> 24) & 0xff;
    int r1 = (rgb1 >> 16) & 0xff;
    int g1 = (rgb1 >> 8) & 0xff;
    int b1 = rgb1 & 0xff;
    int a2 = (rgb2 >> 24) & 0xff;
    int r2 = (rgb2 >> 16) & 0xff;
    int g2 = (rgb2 >> 8) & 0xff;
    int b2 = rgb2 & 0xff;
    switch (op) {
      case NORMAL:
        break;
      case MIN:
        r1 = Math.min(r1, r2);
        g1 = Math.min(g1, g2);
        b1 = Math.min(b1, b2);
        break;
      case MAX:
        r1 = Math.max(r1, r2);
        g1 = Math.max(g1, g2);
        b1 = Math.max(b1, b2);
        break;
      case ADD:
        r1 = clamp(r1 + r2);
        g1 = clamp(g1 + g2);
        b1 = clamp(b1 + b2);
        break;
      case SUBTRACT:
        r1 = clamp(r2 - r1);
        g1 = clamp(g2 - g1);
        b1 = clamp(b2 - b1);
        break;
      case DIFFERENCE:
        r1 = clamp(abs(r1 - r2));
        g1 = clamp(abs(g1 - g2));
        b1 = clamp(abs(b1 - b2));
        break;
      case MULTIPLY:
        r1 = clamp(r1 * r2 / 255);
        g1 = clamp(g1 * g2 / 255);
        b1 = clamp(b1 * b2 / 255);
        break;
      case DISSOLVE:
        if ((randomGenerator.nextInt() & 0xff) <= a1) {
          r1 = r2;
          g1 = g2;
          b1 = b2;
        }
        break;
      case AVERAGE:
        r1 = (r1 + r2) / 2;
        g1 = (g1 + g2) / 2;
        b1 = (b1 + b2) / 2;
        break;
      case HUE:
      case SATURATION:
      case VALUE:
      case COLOR: {
        float[] hsb1 = Color.RGBtoHSB(r1, g1, b1, null);
        float[] hsb2 = Color.RGBtoHSB(r2, g2, b2, null);
        switch (op) {
          case HUE:
            hsb2[0] = hsb1[0];
            break;
          case SATURATION:
            hsb2[1] = hsb1[1];
            break;
          case VALUE:
            hsb2[2] = hsb1[2];
            break;
          case COLOR:
            hsb2[0] = hsb1[0];
            hsb2[1] = hsb1[1];
            break;
        }
        rgb1 = Color.HSBtoRGB(hsb2[0], hsb2[1], hsb2[2]);
        r1 = (rgb1 >> 16) & 0xff;
        g1 = (rgb1 >> 8) & 0xff;
        b1 = rgb1 & 0xff;
        break;
      }
      case SCREEN:
        r1 = 255 - ((255 - r1) * (255 - r2)) / 255;
        g1 = 255 - ((255 - g1) * (255 - g2)) / 255;
        b1 = 255 - ((255 - b1) * (255 - b2)) / 255;
        break;
      case OVERLAY:
        int m, s;
        s = 255 - ((255 - r1) * (255 - r2)) / 255;
        m = r1 * r2 / 255;
        r1 = (s * r1 + m * (255 - r1)) / 255;
        s = 255 - ((255 - g1) * (255 - g2)) / 255;
        m = g1 * g2 / 255;
        g1 = (s * g1 + m * (255 - g1)) / 255;
        s = 255 - ((255 - b1) * (255 - b2)) / 255;
        m = b1 * b2 / 255;
        b1 = (s * b1 + m * (255 - b1)) / 255;
        break;
      case CLEAR:
        r1 = g1 = b1 = 0xff;
        break;
      case DST_IN:
        r1 = clamp((r2 * a1) / 255);
        g1 = clamp((g2 * a1) / 255);
        b1 = clamp((b2 * a1) / 255);
        a1 = clamp((a2 * a1) / 255);
        return (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
      case ALPHA:
        a1 = a1 * a2 / 255;
        return (a1 << 24) | (r2 << 16) | (g2 << 8) | b2;
      case ALPHA_TO_GRAY:
        int na = 255 - a1;
        return (a1 << 24) | (na << 16) | (na << 8) | na;
    }
    if (extraAlpha != 0xff || a1 != 0xff) {
      a1 = a1 * extraAlpha / 255;
      int a3 = (255 - a1) * a2 / 255;
      r1 = clamp((r1 * a1 + r2 * a3) / 255);
      g1 = clamp((g1 * a1 + g2 * a3) / 255);
      b1 = clamp((b1 * a1 + b2 * a3) / 255);
      a1 = clamp(a1 + a3);
    }
    return (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
  }


  public static int interpolate(int v1, int v2, float f) {
    return clamp((int) (v1 + f * (v2 - v1)));
  }

  public static int brightness(int rgb) {
    int r = (rgb >> 16) & 0xff;
    int g = (rgb >> 8) & 0xff;
    int b = rgb & 0xff;
    return (r + g + b) / 3;
  }

  public static boolean nearColors(int rgb1, int rgb2, int tolerance) {
    int r1 = (rgb1 >> 16) & 0xff;
    int g1 = (rgb1 >> 8) & 0xff;
    int b1 = rgb1 & 0xff;
    int r2 = (rgb2 >> 16) & 0xff;
    int g2 = (rgb2 >> 8) & 0xff;
    int b2 = rgb2 & 0xff;
    return abs(r1 - r2) <= tolerance
            && abs(g1 - g2) <= tolerance
            && abs(b1 - b2) <= tolerance;
  }

}
