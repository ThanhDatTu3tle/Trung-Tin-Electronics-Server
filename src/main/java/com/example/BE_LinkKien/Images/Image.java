package com.example.BE_LinkKien.Images;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
@RequiredArgsConstructor
public class Image {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    public String saveImage(String imgPath, MultipartFile image) throws IOException
    {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get(imgPath);
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        String imageLink = imagePath.resolve(image.getOriginalFilename()).toString();
        return imageLink.replace("\\", "/");
    }

    public void deleteImage(String imagePath) throws IOException {
        String link = imagePath.replace("/", "\\");
        Path oldImagePath = CURRENT_FOLDER.resolve("static").resolve(link);
        try {
            boolean check = Files.deleteIfExists(oldImagePath);
            if (check) {
                System.out.println("File deleted successfully: " + oldImagePath);
            } else {
                System.out.println("Failed to delete file: " + oldImagePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
