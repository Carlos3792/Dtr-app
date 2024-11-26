/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 *
 * @author carlos
 */
public class ImageUtils {
    
    // Método para redimensionar la imagen y devolverla como un BufferedImage (sin convertir a bytes)
    public static BufferedImage resizeImage(File originalImageFile, int targetWidth, int targetHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalImageFile);
        Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

        // Crear una imagen redimensionada con el tamaño especificado
        BufferedImage bufferedResizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.drawImage(resizedImage, 0, 0, null);
        g2d.dispose();

        return bufferedResizedImage;
    }
    
    // Redimensionar la imagen y devolverla como byte[]
    public static byte[] resizeImageToBytes(File originalImageFile, int targetWidth, int targetHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalImageFile);
        Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

        // Crear una imagen redimensionada con el tamaño especificado
        BufferedImage bufferedResizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.drawImage(resizedImage, 0, 0, null);
        g2d.dispose();

        // Convertir la imagen redimensionada a byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedResizedImage, "jpg", baos);  // Puedes cambiar "jpg" por el formato que prefieras
        return baos.toByteArray();
    }
    
    public static File bytesToFile(byte[] bytes, String fileName) throws IOException {
    File file = new File(fileName);
    try (FileOutputStream fos = new FileOutputStream(file)) {
        fos.write(bytes);
    }
    return file;
}

}
