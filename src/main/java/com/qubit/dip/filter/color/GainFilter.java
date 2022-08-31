////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter.color;

import static com.qubit.dip.utils.ImageMath.bias;
import static com.qubit.dip.utils.ImageMath.gain;

import com.qubit.dip.filter.TransferFilter;

/**
 * An image filter which adjusts gain and bias of an image.
 * <p>
 * This filter changes the contrast of an image. More precisely, it allows you
 * to change the gain and bias of the colors in the image. Changing the bias
 * biases colors towards lighter or darker. changing the gain alters the
 * contrast.
 * </p>
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public class GainFilter extends TransferFilter {

  private float gain = 0.5f;
  private float bias = 0.5f;

  protected float transferFunction(float f) {
    f = gain(f, gain);
    f = bias(f, bias);
    return f;
  }

  /**
   * Set the gain.
   *
   * @param gain the gain
   * @min-value: 0
   * @max-value: 1
   * @see #getGain
   */
  public void setGain(float gain) {
    this.gain = gain;
    initialized = false;
  }

  /**
   * Get the gain.
   *
   * @return the gain
   * @see #setGain
   */
  public float getGain() {
    return gain;
  }

  /**
   * Set the bias.
   *
   * @param bias the bias
   * @min-value: 0
   * @max-value: 1
   * @see #getBias
   */
  public void setBias(float bias) {
    this.bias = bias;
    initialized = false;
  }

  /**
   * Get the bias.
   *
   * @return the bias
   * @see #setBias
   */
  public float getBias() {
    return bias;
  }
}
