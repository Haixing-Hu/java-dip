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
 * Unit test of the {@link DiffusionFilter}.
 *
 * @author Haixing Hu
 */
public class DiffusionFilterTest extends ImageProcessorTest {

  @Override
  protected ImageProcessor createProcessor() {
    return new DiffusionFilter();
  }
}
