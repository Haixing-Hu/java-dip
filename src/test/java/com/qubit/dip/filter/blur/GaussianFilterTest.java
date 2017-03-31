/*******************************************************************************
 *
 *     Copyright (c) 2016.  Haixing Hu @ Qubit Technology Co. Ltd.
 *
 *     All rights reserved.
 *
 ******************************************************************************/

package com.qubit.dip.filter.blur;

import com.qubit.dip.ImageProcessor;
import com.qubit.dip.ImageProcessorTest;

/**
 * Unit test of the {@link GaussianFilter}.
 *
 * @author Haixing Hu
 */
public class GaussianFilterTest extends ImageProcessorTest {

  @Override
  protected ImageProcessor createProcessor() {
    GaussianFilter filter = new GaussianFilter();
    filter.setRadius(10.0f);
    return filter;
  }
}
