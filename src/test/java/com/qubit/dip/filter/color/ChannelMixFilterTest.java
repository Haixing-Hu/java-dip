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
 * Unit test of the {@link ChannelMixFilter}.
 *
 * @author Haixing Hu
 */
public class ChannelMixFilterTest extends ImageProcessorTest {

  @Override
  protected ImageProcessor createProcessor() {
    return new ChannelMixFilter();
  }
}
