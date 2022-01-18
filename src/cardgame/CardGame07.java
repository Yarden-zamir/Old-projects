package cardgame;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/*
 * @author Yarden zamir
 */
public class CardGame07 {

    public static GameOperator GO;

    public static void main(String[] args) {
        if (Player.RsrcFolder.exists()) {
            startGame();
        } else {
            try {
                URL d = new URL("https://www.dropbox.com/sh/okohk7xizajt89n/AABZgP1FjckY0hSHFxBeIBJxa?dl=1");
                java.io.BufferedInputStream in = new java.io.BufferedInputStream(d.openStream());
                java.io.FileOutputStream fos = new java.io.FileOutputStream(new File(".\\.\\RsrcTemp.zip"));
                java.io.BufferedOutputStream bout = new BufferedOutputStream(fos);
                byte data[] = new byte[1024];
                int read;
                JFrame downLoader = new JFrame("Downloading...");
                downLoader.setSize(500, 400);
                downLoader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JProgressBar dp = new JProgressBar(0, 55000);
                downLoader.add(dp);
                downLoader.setVisible(true);
                while ((read = in.read(data, 0, 1024)) >= 0) {
                    bout.write(data, 0, read);
                    downLoader.setTitle("Downloading... (" + (int) new File(".\\.\\RsrcTemp.zip").length() / 1000 + "KB)");
                    dp.setValue((int) new File(".\\.\\RsrcTemp.zip").length() / 1000);
                }
                bout.close();
                InputStream zipFile = new FileInputStream(new File(".\\.\\RsrcTemp.zip"));
                unZipIt(zipFile, Player.RsrcFolder);
                in.close();
                Files.delete(new File(".\\.\\RsrcTemp.zip").toPath());
                downLoader.setVisible(false);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CardGame07.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CardGame07.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CardGame07.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("done");
            startGame();
        }
    }

    public static void startGame() {
        Random rnd = new Random();
        System.err.println("Files good!");
        GO = new GameOperator();
        GO.startGame();
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\enemy\\enm" + (rnd.nextInt(6)) + ".wav"));
    }

    public static void unZipIt(InputStream stream, File dest) {

        byte[] buffer = new byte[1024];

        try {
            //create output directory is not exists
            File folder = dest;
            if (!folder.exists()) {
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis
                    = new ZipInputStream(stream);
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {

                String fileName = ze.getName();
                File newFile = new File(dest + File.separator + fileName);

                System.out.println("file unzip : " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                if (ze.isDirectory()) {
                    new File(newFile.getParent()).mkdirs();
                } else {
                    FileOutputStream fos = null;

                    new File(newFile.getParent()).mkdirs();

                    fos = new FileOutputStream(newFile);

                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }

                    fos.close();
                }

                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}