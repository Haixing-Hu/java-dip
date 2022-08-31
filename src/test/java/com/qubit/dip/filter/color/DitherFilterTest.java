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
 * Unit test of the {@link DitherFilter}.
 *
 * @author Haixing Hu
 */
public class DitherFilterTest extends ImageProcessorTest {

  @Override
  protected ImageProcessor createProcessor() {
    DitherFilter filter = new DitherFilter();
    filter.setLevels(30);
    return filter;
  }
}
