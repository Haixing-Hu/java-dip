////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter.color;

import static com.qubit.dip.utils.ImageMath.mixColors;
import static com.qubit.dip.utils.ImageMath.smoothStep;
import static com.qubit.dip.utils.ImageUtils.brightness;

import com.qubit.dip.filter.PointFilter;

/**
 * An image filter which performs a threshold operation on an image.
 * <p>
 * This filter converts an color image black and white by changing all pixels
 * lighter than a threshold to white and all pixel darker than the threshold to
 * black.
 * </p>
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public class ThresholdFilter extends PointFilter {

  private int lowerThreshold;
  private int upperThreshold;
  private int white = 0xffffff;
  private int black = 0x000000;

  /**
   * Construct a ThresholdFilter.
   */
  public ThresholdFilter() {
    this(127);
  }

  /**
   * Construct a ThresholdFilter.
   *
   * @param t the threshold value
   */
  public ThresholdFilter(int t) {
    setLowerThreshold(t);
    setUpperThreshold(t);
  }

  /**
   * Set the lower threshold value.
   *
   * @param lowerThreshold the threshold value
   * @see #getLowerThreshold
   */
  public void setLowerThreshold(int lowerThreshold) {
    this.lowerThreshold = lowerThreshold;
  }

  /**
   * Get the lower threshold value.
   *
   * @return the threshold value
   * @see #setLowerThreshold
   */
  public int getLowerThreshold() {
    return lowerThreshold;
  }

  /**
   * Set the upper threshold value.
   *
   * @param upperThreshold the threshold value
   * @see #getUpperThreshold
   */
  public void setUpperThreshold(int upperThreshold) {
    this.upperThreshold = upperThreshold;
  }

  /**
   * Get the upper threshold value.
   *
   * @return the threshold value
   * @see #setUpperThreshold
   */
  public int getUpperThreshold() {
    return upperThreshold;
  }

  /**
   * Set the color to be used for pixels above the upper threshold.
   *
   * @param white the color
   * @see #getWhite
   */
  public void setWhite(int white) {
    this.white = white;
  }

  /**
   * Get the color to be used for pixels above the upper threshold.
   *
   * @return the color
   * @see #setWhite
   */
  public int getWhite() {
    return white;
  }

  /**
   * Set the color to be used for pixels below the lower threshold.
   *
   * @param black the color
   * @see #getBlack
   */
  public void setBlack(int black) {
    this.black = black;
  }

  /**
   * Set the color to be used for pixels below the lower threshold.
   *
   * @return the color
   * @see #setBlack
   */
  public int getBlack() {
    return black;
  }

  public int filterRGB(int x, int y, int rgb) {
    int v = brightness(rgb);
    float f = smoothStep(lowerThreshold, upperThreshold, v);
    return (rgb & 0xff000000) | (mixColors(f, black, white) & 0xffffff);
  }
}
