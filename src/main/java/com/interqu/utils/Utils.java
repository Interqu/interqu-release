package com.interqu.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
    
     public static boolean assertImageDim(MultipartFile file, int width, int height) throws IOException {
         BufferedImage image = ImageIO.read(file.getInputStream());
         return image.getWidth() == width && image.getHeight() == height;
     }

}
