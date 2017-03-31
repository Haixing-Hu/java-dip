/*******************************************************************************
 *
 *     Copyright (c) 2016.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *     All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip.filter.color;

import com.qubit.dip.ImageProcessor;
import com.qubit.dip.ImageProcessorTest;

/**
 * Unit test of the {@link ExposureFilter}.
 *
 * @author Haixing Hu
 */
public class ExposureFilterTest extends ImageProcessorTest {

  @Override
  protected ImageProcessor createProcessor() {
    ExposureFilter filter = new ExposureFilter();
    filter.setExposure(5.0f);
    return filter;
  }
}
