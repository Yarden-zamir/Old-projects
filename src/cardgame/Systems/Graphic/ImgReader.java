package Systems.Graphic;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
 * @author Yarden zamir
 */
public class ImgReader {

    public static BufferedImage ReadImage(File f) {
        BufferedImage outP = null;
        try {
            outP = ImageIO.read(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outP;
    }

    public static BufferedImage ResizeImage(File f) {
        BufferedImage outP = ReadImage(f);
        outP = scale(outP, outP.getType(), 50, 50, 0.25, 0.25);
        return outP;
    }

    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if (sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }
}
