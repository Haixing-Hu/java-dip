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
 * An image filter which changes the brightness and contrast of an image.
 * <p>
 * This filter changes the brightness and contrast of an image.
 * </p>
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public final class ContrastFilter extends TransferFilter {

  private float brightness = 1.0f;
  private float contrast = 1.0f;

  public final float getBrightness() {
    return brightness;
  }

  public final void setBrightness(final float brightness) {
    this.brightness = brightness;
    this.initialized = false;
  }

  public final float getContrast() {
    return contrast;
  }

  public final void setContrast(final float contrast) {
    this.contrast = contrast;
    this.initialized = false;
  }

  protected float transferFunction(float f) {
    f = f * brightness;
    f = (f - 0.5f) * contrast + 0.5f;
    return f;
  }
}
