////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2016 - 2022.
//    Haixing Hu @ Qubit Technology Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////

package com.qubit.dip;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import static org.junit.Assert.assertNotNull;

/**
 * Base class for testing the image processors.
 *
 * @author Haixing Hu
 */
public abstract class ImageProcessorTest {

  private static final String RESOURCE_PREFIX = "/images/";

  private static final String[] TEST_IMAGES = {
          "flower.jpg",
          "Lenna.png"
  };

  protected abstract ImageProcessor createProcessor();

  private String getOutputDirName() {
    return "ImageProcessorTest-" + String.valueOf(System.currentTimeMillis());
  }

  private String getOutputFileName(ImageProcessor processor, String image) {
    return processor.toString() + "-" + image;
  }

  @Test
  public void testProcess() throws IOException {
    File tempDir = SystemUtils.getJavaIoTmpDir();
    File outputDir = new File(tempDir, getOutputDirName());
    FileUtils.forceMkdir(outputDir);
    ImageProcessor processor = createProcessor();
    for (String image : TEST_IMAGES) {
      URL url = this.getClass().getResource(RESOURCE_PREFIX + image);
      assertNotNull(url);
      BufferedImage src = ImageIO.read(url);
      BufferedImage dst = processor.process(src, null);
      assertNotNull(dst);
      File output = new File(outputDir, getOutputFileName(processor, image));
      ImageIO.write(dst, "jpeg", output);
      System.out.println("Successfully process " + image + " and output to " + output);
    }
  }

}
