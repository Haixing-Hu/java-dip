////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip.filter.color;

import com.qubit.dip.ImageProcessor;
import com.qubit.dip.ImageProcessorTest;

/**
 * Unit test of the {@link CurvesFilter}.
 *
 * @author Haixing Hu
 */
public class CurvesFilterTest extends ImageProcessorTest {

  @Override
  protected ImageProcessor createProcessor() {
    return new CurvesFilter();
  }
}
