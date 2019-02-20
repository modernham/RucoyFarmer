package Tools;

import botMain.State;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class configSaver {
    ObjectOutputStream oos = null;
    FileOutputStream fout = null;
    ObjectInputStream objectinputstream = null;
    FileInputStream streamIn;
    public static float[] COLOR1 = new float[2];
    public static float[] COLOR2 = new float[2];
    public savedColors loadedColors = new savedColors();

    public configSaver(){

    }

    public void saveConfig(float[] color1, float[] color2, float[] color3) {

    }

    public void loadConfigChoose() {
        State.window.getPos();

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Load Config File");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Config", "dat");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showDialog(null, "Load");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println(jfc.getSelectedFile().getPath());



            try {
                FileInputStream fis = new FileInputStream(jfc.getSelectedFile().getPath());
                ObjectInputStream ois = new ObjectInputStream(fis);
                savedColors savedColorsFromFile = (savedColors) ois.readObject();
                loadedColors = savedColorsFromFile;
                ois.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    public void saveConfig(savedColors saveData) {


        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Save Config File");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Config", "dat");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showDialog(null, "Save");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println(jfc.getSelectedFile().getPath());


            try {
                Files.deleteIfExists(Paths.get(jfc.getSelectedFile().getPath()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                fout = new FileOutputStream(jfc.getSelectedFile().getPath() + ".dat", true);
                oos = new ObjectOutputStream(fout);
                oos.writeObject(saveData);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (oos != null) {
                    // oos.close();
                }
            }

        }
    }


}
