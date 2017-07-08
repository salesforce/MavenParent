import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        try (InputStream stream = new FileInputStream(jpgFile); OutputStream outputFile = new FileOutputStream(cssFile)) {
          byte[] imageBytes = IOUtils.toByteArray(stream);
          String base64 = Base64.getEncoder().encodeToString(imageBytes);
          String css = "body {\n"+"background-image: url(\"data:image/jpeg;base64,"+base64+"\");\n}";
          IOUtils.write(css, new FileOutputStream(cssFile));
      }
    }
  }
}
