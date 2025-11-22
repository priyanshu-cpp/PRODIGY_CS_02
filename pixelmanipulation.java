package PRODIGY_CS_02;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class pixelmanipulation
 {

   
    public static void encryptImage(String inputPath, String outputPath) {
        try {
            BufferedImage img = ImageIO.read(new File(inputPath));

            int width = img.getWidth();
            int height = img.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    int pixel = img.getRGB(x, y);

                    int a = (pixel >> 24) & 0xff;
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >> 8) & 0xff;
                    int b = pixel & 0xff;

                    
                    int temp = r;
                    r = b;
                    b = temp;

                    
                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;

                    int newPixel = (a << 24) | (r << 16) | (g << 8) | b;

                    img.setRGB(x, y, newPixel);
                }
            }

            ImageIO.write(img, "png", new File(outputPath));
            System.out.println("Image Encrypted Successfully: " + outputPath);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    
    public static void decryptImage(String inputPath, String outputPath) {
        try {
            BufferedImage img = ImageIO.read(new File(inputPath));

            int width = img.getWidth();
            int height = img.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    int pixel = img.getRGB(x, y);

                    int a = (pixel >> 24) & 0xff;
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >> 8) & 0xff;
                    int b = pixel & 0xff;

                    
                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;

                    
                    int temp = r;
                    r = b;
                    b = temp;

                    int newPixel = (a << 24) | (r << 16) | (g << 8) | b;

                    img.setRGB(x, y, newPixel);
                }
            }

            ImageIO.write(img, "png", new File(outputPath));
            System.out.println("Image Decrypted Successfully: " + outputPath);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    
    public static void main(String[] args) {

        System.out.println("1 → Encrypt Image");
        System.out.println("2 → Decrypt Image");

        try {
            java.util.Scanner sc = new java.util.Scanner(System.in);
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter image path to encrypt: ");
                String input = sc.next();

                System.out.print("Save encrypted image as: ");
                String output = sc.next();

                encryptImage(input, output);
            } else if (choice == 2) {
                System.out.print("Enter encrypted image path: ");
                String input = sc.next();

                System.out.print("Save decrypted image as: ");
                String output = sc.next();

                decryptImage(input, output);
            } else {
                System.out.println("Invalid choice");
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
