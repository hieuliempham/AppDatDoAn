package Api.AppDatDoAn.utils;

import com.google.api.services.storage.Storage;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.StorageOptions;

import java.net.URL;
import java.util.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;

public class ImageUtils {
    public static String toBase64(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        byte[] bytes = is.readAllBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }


}
