////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter.color;

import com.qubit.dip.filter.TransferFilter;

/**
 * An image filter which changes the exposure of an image.
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public class ExposureFilter extends TransferFilter {

  private float exposure = 1.0f;

  protected float transferFunction(float f) {
    return 1 - (float) Math.exp(-f * exposure);
  }

  /**
   * Set the exposure level.
   *
   * @param exposure the exposure level
   * @min-value 0
   * @max-value 5+
   * @see #getExposure
   */
  public void setExposure(float exposure) {
    this.exposure = exposure;
    initialized = false;
  }

  /**
   * Get the exposure level.
   *
   * @return the exposure level
   * @see #setExposure
   */
  public float getExposure() {
    return exposure;
  }

}
