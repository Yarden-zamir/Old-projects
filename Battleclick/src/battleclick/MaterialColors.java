package battleclick;



import battleclick.settings;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Yarden zamir
 */
public class MaterialColors {

    //colors
    public static Color RED = Color.decode("#f44336");
    public static Color RED_LIGHTEN_5 = Color.decode("#ffebee");
    public static Color RED_LIGHTEN_4 = Color.decode("#ffcdd2");
    public static Color RED_LIGHTEN_3 = Color.decode("#ef9a9a");
    public static Color RED_LIGHTEN_2 = Color.decode("#e57373");
    public static Color RED_LIGHTEN_1 = Color.decode("#ef5350");
    public static Color RED_DARKEN_1 = Color.decode("#e53935");
    public static Color RED_DARKEN_2 = Color.decode("#d32f2f");
    public static Color RED_DARKEN_3 = Color.decode("#c62828");
    public static Color RED_DARKEN_4 = Color.decode("#b71c1c");
    public static Color RED_ACCENT_1 = Color.decode("#ff8a80");
    public static Color RED_ACCENT_2 = Color.decode("#ff5252");
    public static Color RED_ACCENT_3 = Color.decode("#ff1744");
    public static Color RED_ACCENT_4 = Color.decode("#d50000");
    public static Color PINK = Color.decode("#e91e63");
    public static Color PINK_LIGHTEN_5 = Color.decode("#fce4ec");
    public static Color PINK_LIGHTEN_4 = Color.decode("#f8bbd0");
    public static Color PINK_LIGHTEN_3 = Color.decode("#f48fb1");
    public static Color PINK_LIGHTEN_2 = Color.decode("#f06292");
    public static Color PINK_LIGHTEN_1 = Color.decode("#ec407a");
    public static Color PINK_DARKEN_1 = Color.decode("#d81b60");
    public static Color PINK_DARKEN_2 = Color.decode("#c2185b");
    public static Color PINK_DARKEN_3 = Color.decode("#ad1457");
    public static Color PINK_DARKEN_4 = Color.decode("#880e4f");
    public static Color PINK_ACCENT_1 = Color.decode("#ff80ab");
    public static Color PINK_ACCENT_2 = Color.decode("#ff4081");
    public static Color PINK_ACCENT_3 = Color.decode("#f50057");
    public static Color PINK_ACCENT_4 = Color.decode("#c51162");
    public static Color PURPLE = Color.decode("#9c27b0");
    public static Color PURPLE_LIGHTEN_5 = Color.decode("#f3e5f5");
    public static Color PURPLE_LIGHTEN_4 = Color.decode("#e1bee7");
    public static Color PURPLE_LIGHTEN_3 = Color.decode("#ce93d8");
    public static Color PURPLE_LIGHTEN_2 = Color.decode("#ba68c8");
    public static Color PURPLE_LIGHTEN_1 = Color.decode("#ab47bc");
    public static Color PURPLE_DARKEN_1 = Color.decode("#8e24aa");
    public static Color PURPLE_DARKEN_2 = Color.decode("#7b1fa2");
    public static Color PURPLE_DARKEN_3 = Color.decode("#6a1b9a");
    public static Color PURPLE_DARKEN_4 = Color.decode("#4a148c");
    public static Color PURPLE_ACCENT_1 = Color.decode("#ea80fc");
    public static Color PURPLE_ACCENT_2 = Color.decode("#e040fb");
    public static Color PURPLE_ACCENT_3 = Color.decode("#d500f9");
    public static Color PURPLE_ACCENT_4 = Color.decode("#aa00ff");
    public static Color DEEP_PURPLE = Color.decode("#673ab7");
    public static Color DEEP_PURPLE_LIGHTEN_5 = Color.decode("#ede7f6");
    public static Color DEEP_PURPLE_LIGHTEN_4 = Color.decode("#d1c4e9");
    public static Color DEEP_PURPLE_LIGHTEN_3 = Color.decode("#b39ddb");
    public static Color DEEP_PURPLE_LIGHTEN_2 = Color.decode("#9575cd");
    public static Color DEEP_PURPLE_LIGHTEN_1 = Color.decode("#7e57c2");
    public static Color DEEP_PURPLE_DARKEN_1 = Color.decode("#5e35b1");
    public static Color DEEP_PURPLE_DARKEN_2 = Color.decode("#512da8");
    public static Color DEEP_PURPLE_DARKEN_3 = Color.decode("#4527a0");
    public static Color DEEP_PURPLE_DARKEN_4 = Color.decode("#311b92");
    public static Color DEEP_PURPLE_ACCENT_1 = Color.decode("#b388ff");
    public static Color DEEP_PURPLE_ACCENT_2 = Color.decode("#7c4dff");
    public static Color DEEP_PURPLE_ACCENT_3 = Color.decode("#651fff");
    public static Color DEEP_PURPLE_ACCENT_4 = Color.decode("#6200ea");
    public static Color INDIGO = Color.decode("#3f51b5");
    public static Color INDIGO_LIGHTEN_5 = Color.decode("#e8eaf6");
    public static Color INDIGO_LIGHTEN_4 = Color.decode("#c5cae9");
    public static Color INDIGO_LIGHTEN_3 = Color.decode("#9fa8da");
    public static Color INDIGO_LIGHTEN_2 = Color.decode("#7986cb");
    public static Color INDIGO_LIGHTEN_1 = Color.decode("#5c6bc0");
    public static Color INDIGO_DARKEN_1 = Color.decode("#3949ab");
    public static Color INDIGO_DARKEN_2 = Color.decode("#303f9f");
    public static Color INDIGO_DARKEN_3 = Color.decode("#283593");
    public static Color INDIGO_DARKEN_4 = Color.decode("#1a237e");
    public static Color INDIGO_ACCENT_1 = Color.decode("#8c9eff");
    public static Color INDIGO_ACCENT_2 = Color.decode("#536dfe");
    public static Color INDIGO_ACCENT_3 = Color.decode("#3d5afe");
    public static Color INDIGO_ACCENT_4 = Color.decode("#304ffe");
    public static Color BLUE = Color.decode("#2196f3");
    public static Color BLUE_LIGHTEN_5 = Color.decode("#e3f2fd");
    public static Color BLUE_LIGHTEN_4 = Color.decode("#bbdefb");
    public static Color BLUE_LIGHTEN_3 = Color.decode("#90caf9");
    public static Color BLUE_LIGHTEN_2 = Color.decode("#64b5f6");
    public static Color BLUE_LIGHTEN_1 = Color.decode("#42a5f5");
    public static Color BLUE_DARKEN_1 = Color.decode("#1e88e5");
    public static Color BLUE_DARKEN_2 = Color.decode("#1976d2");
    public static Color BLUE_DARKEN_3 = Color.decode("#1565c0");
    public static Color BLUE_DARKEN_4 = Color.decode("#0d47a1");
    public static Color BLUE_ACCENT_1 = Color.decode("#82b1ff");
    public static Color BLUE_ACCENT_2 = Color.decode("#448aff");
    public static Color BLUE_ACCENT_3 = Color.decode("#2979ff");
    public static Color BLUE_ACCENT_4 = Color.decode("#2962ff");
    public static Color LIGHT_BLUE = Color.decode("#03a9f4");
    public static Color LIGHT_BLUE_LIGHTEN_5 = Color.decode("#e1f5fe");
    public static Color LIGHT_BLUE_LIGHTEN_4 = Color.decode("#b3e5fc");
    public static Color LIGHT_BLUE_LIGHTEN_3 = Color.decode("#81d4fa");
    public static Color LIGHT_BLUE_LIGHTEN_2 = Color.decode("#4fc3f7");
    public static Color LIGHT_BLUE_LIGHTEN_1 = Color.decode("#29b6f6");
    public static Color LIGHT_BLUE_DARKEN_1 = Color.decode("#039be5");
    public static Color LIGHT_BLUE_DARKEN_2 = Color.decode("#0288d1");
    public static Color LIGHT_BLUE_DARKEN_3 = Color.decode("#0277bd");
    public static Color LIGHT_BLUE_DARKEN_4 = Color.decode("#01579b");
    public static Color LIGHT_BLUE_ACCENT_1 = Color.decode("#80d8ff");
    public static Color LIGHT_BLUE_ACCENT_2 = Color.decode("#40c4ff");
    public static Color LIGHT_BLUE_ACCENT_3 = Color.decode("#00b0ff");
    public static Color LIGHT_BLUE_ACCENT_4 = Color.decode("#0091ea");
    public static Color CYAN_LIGHTEN_5 = Color.decode("#e0f7fa");
    public static Color CYAN_LIGHTEN_4 = Color.decode("#b2ebf2");
    public static Color CYAN_LIGHTEN_3 = Color.decode("#80deea");
    public static Color CYAN_LIGHTEN_2 = Color.decode("#4dd0e1");
    public static Color CYAN_LIGHTEN_1 = Color.decode("#26c6da");
    public static Color CYAN = Color.decode("#00bcd4");
    public static Color CYAN_DARKEN_1 = Color.decode("#00acc1");
    public static Color CYAN_DARKEN_2 = Color.decode("#0097a7");
    public static Color CYAN_DARKEN_3 = Color.decode("#00838f");
    public static Color CYAN_DARKEN_4 = Color.decode("#006064");
    public static Color CYAN_ACCENT_1 = Color.decode("#84ffff");
    public static Color CYAN_ACCENT_2 = Color.decode("#18ffff");
    public static Color CYAN_ACCENT_3 = Color.decode("#00e5ff");
    public static Color CYAN_ACCENT_4 = Color.decode("#00b8d4");
    public static Color TEAL_LIGHTEN_5 = Color.decode("#e0f2f1");
    public static Color TEAL_LIGHTEN_4 = Color.decode("#b2dfdb");
    public static Color TEAL_LIGHTEN_3 = Color.decode("#80cbc4");
    public static Color TEAL_LIGHTEN_2 = Color.decode("#4db6ac");
    public static Color TEAL_LIGHTEN_1 = Color.decode("#26a69a");
    public static Color TEAL = Color.decode("#009688");
    public static Color TEAL_DARKEN_1 = Color.decode("#00897b");
    public static Color TEAL_DARKEN_2 = Color.decode("#00796b");
    public static Color TEAL_DARKEN_3 = Color.decode("#00695c");
    public static Color TEAL_DARKEN_4 = Color.decode("#004d40");
    public static Color TEAL_ACCENT_1 = Color.decode("#a7ffeb");
    public static Color TEAL_ACCENT_2 = Color.decode("#64ffda");
    public static Color TEAL_ACCENT_3 = Color.decode("#1de9b6");
    public static Color TEAL_ACCENT_4 = Color.decode("#00bfa5");
    public static Color GREEN_LIGHTEN_5 = Color.decode("#e8f5e9");
    public static Color GREEN_LIGHTEN_4 = Color.decode("#c8e6c9");
    public static Color GREEN_LIGHTEN_3 = Color.decode("#a5d6a7");
    public static Color GREEN_LIGHTEN_2 = Color.decode("#81c784");
    public static Color GREEN_LIGHTEN_1 = Color.decode("#66bb6a");
    public static Color GREEN = Color.decode("#4caf50");
    public static Color GREEN_DARKEN_1 = Color.decode("#43a047");
    public static Color GREEN_DARKEN_2 = Color.decode("#388e3c");
    public static Color GREEN_DARKEN_3 = Color.decode("#2e7d32");
    public static Color GREEN_DARKEN_4 = Color.decode("#1b5e20");
    public static Color GREEN_ACCENT_1 = Color.decode("#b9f6ca");
    public static Color GREEN_ACCENT_2 = Color.decode("#69f0ae");
    public static Color GREEN_ACCENT_3 = Color.decode("#00e676");
    public static Color GREEN_ACCENT_4 = Color.decode("#00c853");
    public static Color LIGHT_GREEN_LIGHTEN_5 = Color.decode("#f1f8e9");
    public static Color LIGHT_GREEN_LIGHTEN_4 = Color.decode("#dcedc8");
    public static Color LIGHT_GREEN_LIGHTEN_3 = Color.decode("#c5e1a5");
    public static Color LIGHT_GREEN_LIGHTEN_2 = Color.decode("#aed581");
    public static Color LIGHT_GREEN_LIGHTEN_1 = Color.decode("#9ccc65");
    public static Color LIGHT_GREEN = Color.decode("#8bc34a");
    public static Color LIGHT_GREEN_DARKEN_1 = Color.decode("#7cb342");
    public static Color LIGHT_GREEN_DARKEN_2 = Color.decode("#689f38");
    public static Color LIGHT_GREEN_DARKEN_3 = Color.decode("#558b2f");
    public static Color LIGHT_GREEN_DARKEN_4 = Color.decode("#33691e");
    public static Color LIGHT_GREEN_ACCENT_1 = Color.decode("#ccff90");
    public static Color LIGHT_GREEN_ACCENT_2 = Color.decode("#b2ff59");
    public static Color LIGHT_GREEN_ACCENT_3 = Color.decode("#76ff03");
    public static Color LIGHT_GREEN_ACCENT_4 = Color.decode("#64dd17");
    public static Color LIME_LIGHTEN_5 = Color.decode("#f9fbe7");
    public static Color LIME_LIGHTEN_4 = Color.decode("#f0f4c3");
    public static Color LIME_LIGHTEN_3 = Color.decode("#e6ee9c");
    public static Color LIME_LIGHTEN_2 = Color.decode("#dce775");
    public static Color LIME_LIGHTEN_1 = Color.decode("#d4e157");
    public static Color LIME = Color.decode("#cddc39");
    public static Color LIME_DARKEN_1 = Color.decode("#c0ca33");
    public static Color LIME_DARKEN_2 = Color.decode("#afb42b");
    public static Color LIME_DARKEN_3 = Color.decode("#9e9d24");
    public static Color LIME_DARKEN_4 = Color.decode("#827717");
    public static Color LIME_ACCENT_1 = Color.decode("#f4ff81");
    public static Color LIME_ACCENT_2 = Color.decode("#eeff41");
    public static Color LIME_ACCENT_3 = Color.decode("#c6ff00");
    public static Color LIME_ACCENT_4 = Color.decode("#aeea00");
    public static Color YELLOW_LIGHTEN_5 = Color.decode("#fffde7");
    public static Color YELLOW_LIGHTEN_4 = Color.decode("#fff9c4");
    public static Color YELLOW_LIGHTEN_3 = Color.decode("#fff59d");
    public static Color YELLOW_LIGHTEN_2 = Color.decode("#fff176");
    public static Color YELLOW_LIGHTEN_1 = Color.decode("#ffee58");
    public static Color YELLOW = Color.decode("#ffeb3b");
    public static Color YELLOW_DARKEN_1 = Color.decode("#fdd835");
    public static Color YELLOW_DARKEN_2 = Color.decode("#fbc02d");
    public static Color YELLOW_DARKEN_3 = Color.decode("#f9a825");
    public static Color YELLOW_DARKEN_4 = Color.decode("#f57f17");
    public static Color YELLOW_ACCENT_1 = Color.decode("#ffff8d");
    public static Color YELLOW_ACCENT_2 = Color.decode("#ffff00");
    public static Color YELLOW_ACCENT_3 = Color.decode("#ffea00");
    public static Color YELLOW_ACCENT_4 = Color.decode("#ffd600");
    public static Color AMBER_LIGHTEN_5 = Color.decode("#fff8e1");
    public static Color AMBER_LIGHTEN_4 = Color.decode("#ffecb3");
    public static Color AMBER_LIGHTEN_3 = Color.decode("#ffe082");
    public static Color AMBER_LIGHTEN_2 = Color.decode("#ffd54f");
    public static Color AMBER_LIGHTEN_1 = Color.decode("#ffca28");
    public static Color AMBER = Color.decode("#ffc107");
    public static Color AMBER_DARKEN_1 = Color.decode("#ffb300");
    public static Color AMBER_DARKEN_2 = Color.decode("#ffa000");
    public static Color AMBER_DARKEN_3 = Color.decode("#ff8f00");
    public static Color AMBER_DARKEN_4 = Color.decode("#ff6f00");
    public static Color AMBER_ACCENT_1 = Color.decode("#ffe57f");
    public static Color AMBER_ACCENT_2 = Color.decode("#ffd740");
    public static Color AMBER_ACCENT_3 = Color.decode("#ffc400");
    public static Color AMBER_ACCENT_4 = Color.decode("#ffab00");
    public static Color ORANGE_LIGHTEN_5 = Color.decode("#fff3e0");
    public static Color ORANGE_LIGHTEN_4 = Color.decode("#ffe0b2");
    public static Color ORANGE_LIGHTEN_3 = Color.decode("#ffcc80");
    public static Color ORANGE_LIGHTEN_2 = Color.decode("#ffb74d");
    public static Color ORANGE_LIGHTEN_1 = Color.decode("#ffa726");
    public static Color ORANGE = Color.decode("#ff9800");
    public static Color ORANGE_DARKEN_1 = Color.decode("#fb8c00");
    public static Color ORANGE_DARKEN_2 = Color.decode("#f57c00");
    public static Color ORANGE_DARKEN_3 = Color.decode("#ef6c00");
    public static Color ORANGE_DARKEN_4 = Color.decode("#e65100");
    public static Color ORANGE_ACCENT_1 = Color.decode("#ffd180");
    public static Color ORANGE_ACCENT_2 = Color.decode("#ffab40");
    public static Color ORANGE_ACCENT_3 = Color.decode("#ff9100");
    public static Color ORANGE_ACCENT_4 = Color.decode("#ff6d00");
    public static Color DEEP_ORANGE_LIGHTEN_5 = Color.decode("#fbe9e7");
    public static Color DEEP_ORANGE_LIGHTEN_4 = Color.decode("#ffccbc");
    public static Color DEEP_ORANGE_LIGHTEN_3 = Color.decode("#ffab91");
    public static Color DEEP_ORANGE_LIGHTEN_2 = Color.decode("#ff8a65");
    public static Color DEEP_ORANGE_LIGHTEN_1 = Color.decode("#ff7043");
    public static Color DEEP_ORANGE = Color.decode("#ff5722");
    public static Color DEEP_ORANGE_DARKEN_1 = Color.decode("#f4511e");
    public static Color DEEP_ORANGE_DARKEN_2 = Color.decode("#e64a19");
    public static Color DEEP_ORANGE_DARKEN_3 = Color.decode("#d84315");
    public static Color DEEP_ORANGE_DARKEN_4 = Color.decode("#bf360c");
    public static Color DEEP_ORANGE_ACCENT_1 = Color.decode("#ff9e80");
    public static Color DEEP_ORANGE_ACCENT_2 = Color.decode("#ff6e40");
    public static Color DEEP_ORANGE_ACCENT_3 = Color.decode("#ff3d00");
    public static Color DEEP_ORANGE_ACCENT_4 = Color.decode("#dd2c00");
    public static Color BROWN_LIGHTEN_5 = Color.decode("#efebe9");
    public static Color BROWN_LIGHTEN_4 = Color.decode("#d7ccc8");
    public static Color BROWN_LIGHTEN_3 = Color.decode("#bcaaa4");
    public static Color BROWN_LIGHTEN_2 = Color.decode("#a1887f");
    public static Color BROWN_LIGHTEN_1 = Color.decode("#8d6e63");
    public static Color BROWN = Color.decode("#795548");
    public static Color BROWN_DARKEN_1 = Color.decode("#6d4c41");
    public static Color BROWN_DARKEN_2 = Color.decode("#5d4037");
    public static Color BROWN_DARKEN_3 = Color.decode("#4e342e");
    public static Color BROWN_DARKEN_4 = Color.decode("#3e2723");
    public static Color GREY = Color.decode("#9e9e9e");
    public static Color GREY_LIGHTEN_5 = Color.decode("#fafafa");
    public static Color GREY_LIGHTEN_4 = Color.decode("#f5f5f5");
    public static Color GREY_LIGHTEN_3 = Color.decode("#eeeeee");
    public static Color GREY_LIGHTEN_2 = Color.decode("#e0e0e0");
    public static Color GREY_LIGHTEN_1 = Color.decode("#bdbdbd");
    public static Color GREY_DARKEN_1 = Color.decode("#757575");
    public static Color GREY_DARKEN_2 = Color.decode("#616161");
    public static Color GREY_DARKEN_3 = Color.decode("#424242");
    public static Color GREY_DARKEN_4 = Color.decode("#212121");
    public static Color BLUE_GREY = Color.decode("#607d8b");
    public static Color BLUE_GREY_LIGHTEN_5 = Color.decode("#eceff1");
    public static Color BLUE_GREY_LIGHTEN_4 = Color.decode("#cfd8dc");
    public static Color BLUE_GREY_LIGHTEN_3 = Color.decode("#b0bec5");
    public static Color BLUE_GREY_LIGHTEN_2 = Color.decode("#90a4ae");
    public static Color BLUE_GREY_LIGHTEN_1 = Color.decode("#78909c");
    public static Color BLUE_GREY_DARKEN_1 = Color.decode("#546e7a");
    public static Color BLUE_GREY_DARKEN_2 = Color.decode("#455a64");
    public static Color BLUE_GREY_DARKEN_3 = Color.decode("#37474f");
    public static Color BLUE_GREY_DARKEN_4 = Color.decode("#263238");
    public static Color BLACK = Color.decode("#000000");
    public static Color WHITE = Color.decode("#eceff1");
    public static Color WHITE_GREY = Color.decode("#cfd8dc");
    

    //
    public static Color getRandomColor() {
        Field f = null;
        try {
            Random rnd = new Random();
            String outP;
            outP = (MaterialColors.class.getFields()[(rnd.nextInt(MaterialColors.class.getFields().length))]).getName();
            f = MaterialColors.class.getField(outP);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(MaterialColors.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return (Color) (f.get(null));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MaterialColors.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Color getRandomMainColor() {
        Field f = null;
        Random rnd = new Random();
        try {
            String outP = (MaterialColors.class.getFields()[(rnd.nextInt(MaterialColors.class.getFields().length))]).getName();
            f = MaterialColors.class.getField(outP);
            for (String forbc : settings.forbidenColors) {
                while ((outP.contains(forbc))) {
                    outP = (MaterialColors.class.getFields()[(rnd.nextInt(MaterialColors.class.getFields().length))]).getName();

                    f = MaterialColors.class.getField(outP);
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(MaterialColors.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return (Color) (f.get(null));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MaterialColors.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Color getDarkenColor(Color c, int var) {

        String name = "";
        try {
            for (Field f : MaterialColors.class.getFields()) {
                Color tc = (Color) f.get(null);
                if (tc.equals(c)) {
                    name = f.getName().split("_")[0];
                }
            }
            for (Field f : MaterialColors.class.getFields()) {
                if (f.getName().contains(name)) {
                    if (f.getName().contains("DARKEN")) {
                        if (f.getName().contains("" + var)) {
                            return (Color) (f.get(null));
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MaterialColors.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Color getLightenColor(Color c, int var) {

        String name = "";
        try {
            for (Field f : MaterialColors.class.getFields()) {
                Color tc = (Color) f.get(null);
                if (tc.equals(c)) {
                    name = f.getName().split("_")[0];
                }
            }
            for (Field f : MaterialColors.class.getFields()) {
                if (f.getName().contains(name)) {
                    if (f.getName().contains("LIGHTEN")) {
                        if (f.getName().contains("" + var)) {
                            return (Color) (f.get(null));
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MaterialColors.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Color getAccentColor(Color c, int var) {

        String name = "";
        try {
            for (Field f : MaterialColors.class.getFields()) {
                Color tc = (Color) f.get(null);
                if (tc.equals(c)) {
                    name = f.getName().split("_")[0];
                }
            }
            for (Field f : MaterialColors.class.getFields()) {
                if (f.getName().contains(name)) {
                    if (f.getName().contains("ACCENT")) {
                        if (f.getName().contains("" + var)) {
                            return (Color) (f.get(null));
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MaterialColors.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
