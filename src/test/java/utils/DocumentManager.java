package utils;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DocumentManager {

    public static void createWordDocument(String[] imagePaths, String docFilename) {
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream out = new FileOutputStream(new File(docFilename))) {

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Evidencias Test X");

            for (String imagePath : imagePaths) {
                run.addBreak();
                run.addPicture(new FileInputStream(imagePath), XWPFDocument.PICTURE_TYPE_PNG, imagePath,
                        Units.toEMU(500), Units.toEMU(300));
            }

            document.write(out);
            System.out.println("Evidencias generas: " + docFilename);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String captureScreenshot(String filename) {
        try {
            Robot robot = new Robot();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            String filePath = filename + ".png";
            ImageIO.write(screenFullImage, "png", new File(filePath));
            return filePath;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
