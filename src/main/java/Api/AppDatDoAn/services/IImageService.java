package Api.AppDatDoAn.services;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public interface IImageService {
    String getImageUrl(String folderName, String fileName);

    String save(MultipartFile file, String folderName) throws IOException;

    String save(BufferedImage bufferedImage, String originalFileName) throws IOException;

    void delete(String name) throws IOException;

    default String getExtension(String originalFileName) {
        Path path = Paths.get(originalFileName);
        return "." + path.getFileName().toString().split("\\.")[1];
    }

    default String generateFileName(String originalFileName) {
        String extension = getExtension(originalFileName);
        String randomName = UUID.randomUUID().toString();
        return randomName + extension;
    }

    default byte[] getByteArrays(BufferedImage bufferedImage, String format) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {

            ImageIO.write(bufferedImage, format, baos);

            baos.flush();

            return baos.toByteArray();

        } catch (IOException e) {
            throw e;
        } finally {
            baos.close();
        }
    }
}
