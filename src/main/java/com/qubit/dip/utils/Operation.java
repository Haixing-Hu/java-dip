/*******************************************************************************
 *
 *    Copyright (c) 2016-2017.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *    All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip.utils;

/**
 * The enumeration of image combining operations.
 *
 * @author Jerry Huxtable
 * @author Haixing Hu
 */
public enum Operation {
  REPLACE,
  NORMAL,
  MIN,
  MAX,
  ADD,
  SUBTRACT,
  DIFFERENCE,
  MULTIPLY,
  HUE,
  SATURATION,
  VALUE,
  COLOR,
  SCREEN,
  AVERAGE,
  OVERLAY,
  CLEAR,
  EXCHANGE,
  DISSOLVE,
  DST_IN,
  ALPHA,
  ALPHA_TO_GRAY,
}
