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
 * An image filter which applies adjustment curves to an image.
 * <p>
 * This filter applies a transfer curve to each channel of an image. The curve
 * is defined as a series of x,y points to which a spline is fitted. There's no
 * limit on the number of points used to define a curve. If you want to apply
 * the same curve to each channel, you need only define a single curve.
 * </p>
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public class CurvesFilter extends TransferFilter {

  private Curve[] curves;

  public CurvesFilter() {
    curves = new Curve[3];
    curves[0] = new Curve();
    curves[1] = new Curve();
    curves[2] = new Curve();
  }

  protected void initialize() {
    initialized = true;
    if (curves.length == 1) {
      rTable = gTable = bTable = curves[0].makeTable();
    } else {
      rTable = curves[0].makeTable();
      gTable = curves[1].makeTable();
      bTable = curves[2].makeTable();
    }
  }

  public void setCurve(Curve curve) {
    curves = new Curve[]{curve};
    initialized = false;
  }

  public void setCurves(Curve[] curves) {
    if (curves == null || (curves.length != 1 && curves.length != 3)) {
      throw new IllegalArgumentException("Curves must be length 1 or 3");
    }
    this.curves = curves;
    initialized = false;
  }

  public Curve[] getCurves() {
    return curves;
  }
}
