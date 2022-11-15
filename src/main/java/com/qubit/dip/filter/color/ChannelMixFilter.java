////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter.color;

import com.qubit.dip.filter.PointFilter;

import static com.qubit.dip.utils.ImageUtils.clamp;

/**
 * An image filter which mixes the RGB channels.
 * <p>
 * This filter mixes the RGB channels of an image; i.e., it allows the red,
 * green and blue channels of an image to be mixed into each other. For each
 * channel, you specify how much of the other two channels to mix in.
 * </p>
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public class ChannelMixFilter extends PointFilter {

  private int blueGreen, redBlue, greenRed;
  private int intoR, intoG, intoB;

  public final int getBlueGreen() {
    return blueGreen;
  }

  public final void setBlueGreen(final int blueGreen) {
    this.blueGreen = blueGreen;
  }

  public final int getRedBlue() {
    return redBlue;
  }

  public final void setRedBlue(final int redBlue) {
    this.redBlue = redBlue;
  }

  public final int getGreenRed() {
    return greenRed;
  }

  public final void setGreenRed(final int greenRed) {
    this.greenRed = greenRed;
  }

  public final int getIntoR() {
    return intoR;
  }

  public final void setIntoR(final int intoR) {
    this.intoR = intoR;
  }

  public final int getIntoG() {
    return intoG;
  }

  public final void setIntoG(final int intoG) {
    this.intoG = intoG;
  }

  public final int getIntoB() {
    return intoB;
  }

  public final void setIntoB(final int intoB) {
    this.intoB = intoB;
  }

  @Override
  protected int filterRGB(final int x, final int y, final int rgb) {
    int a = rgb & 0xff000000;
    int r = (rgb >> 16) & 0xff;
    int g = (rgb >> 8) & 0xff;
    int b = rgb & 0xff;
    int nr = clamp((intoR * (blueGreen * g + (255 - blueGreen) * b) / 255 + (255 - intoR) * r) / 255);
    int ng = clamp((intoG * (redBlue * b + (255 - redBlue) * r) / 255 + (255 - intoG) * g) / 255);
    int nb = clamp((intoB * (greenRed * r + (255 - greenRed) * g) / 255 + (255 - intoB) * b) / 255);
    return (a | (nr << 16) | (ng << 8) | nb);
  }
}
