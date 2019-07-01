/*
 * Copyright © 2018, Salesforce.com, Inc
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class Base64EncodeTest {

  /**
   * Not really a test, but a hack to base64 encode images in to css Files.
   * @throws IOException
   */
  @Test
  public void encodeImagesAsBase64CSS() throws IOException {
    String testResourcesFile = Base64EncodeTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
    File testResourcesDir = new File(testResourcesFile);
    File targetDir = testResourcesDir.getParentFile();
    File[] files = testResourcesDir.listFiles( (FileFilter) (File file) -> file.getName().endsWith(".jpg") && !file.isDirectory() );
    for (File jpgFile : files) {
        String fileName = jpgFile.getName();
        File cssFile = new File(targetDir.getAbsolutePath()+File.separator+"classes"+File.separator+"css"+File.separator+
                        fileName.substring(0, fileName.length() - 4)+".css");
        try (InputStream stream = new FileInputStream(jpgFile); FileWriter writer = new FileWriter(cssFile)) {
          byte[] imageBytes = IOUtils.toByteArray(stream);
          String base64 = Base64.getEncoder().encodeToString(imageBytes);
          String css = "body {\n"+"background-image: url(\"data:image/jpeg;base64,"+base64+"\");\n}";
          IOUtils.write(css, writer);
      }
    }
  }
}
