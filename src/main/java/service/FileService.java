package service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public abstract class FileService {
    public synchronized static File save(String fileName, byte[] bytes) {
        OutputStream opStream = null;
        File f = null;
        try {
            f = new File("img");
            boolean b = !f.exists() && f.mkdir() || f.exists();
            if (!b) {
                throw new IOException("no create img");
            }
            f = new File("img/" + fileName);
            boolean exist = f.exists();
            if (!exist && f.createNewFile()) {
                opStream = new FileOutputStream(f);
                opStream.write(bytes);
                opStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (opStream != null) opStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return f;
    }

    public synchronized static void write(
            final HttpServletResponse response,
            File file
    ) throws IOException {
        final ServletOutputStream outputStream = response.getOutputStream();

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/force-download");
            response.setContentLength((int) file.length());
            String safariEncodedFileName = file.getName();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + safariEncodedFileName + "\"; filename*=UTF-8''" + safariEncodedFileName);
            InputStream in = new FileInputStream(file);
            byte[] readBuffer = new byte[1000];
            int length;
            while ((length = in.read(readBuffer)) != -1) {
                outputStream.write(readBuffer, 0, length);
            }
        } catch (Exception ignored) {
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}
