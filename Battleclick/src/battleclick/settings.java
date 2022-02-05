/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleclick;

import java.awt.Color;
import java.util.ArrayList;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;
import net.xeoh.plugins.base.util.uri.ClassURI;

/**
 *
 * @author hrsid
 */
public class settings {

    public static ArrayList<String> forbidenColors = new ArrayList<>();
    public static Color HomeColor, PaneColor, accentColor, color4;
    public static int starterHeroCards = 5;
    public static int starterItemCards = 10;
    public static float animationScale = 1f;
    public static int hCardWidth = 280;
    public static int hCardHeight = 130;
    public static int vCardWidth = 135;
    public static int vCardHeight = 280;

    public static PluginManagerUtil pmu;

    public static void setUp() {
        HomeColor = MaterialColors.BLUE_GREY;
        forbidColors();

    }

    private static void forbidColors() {
        forbidenColors.add("RED");
        forbidenColors.add("BLUE");
        forbidenColors.add("BLACK");
    }

    private static void setPlugins() {
        PluginManager pm = PluginManagerFactory.createPluginManager();
        pmu = new PluginManagerUtil(pm);
        pmu.addPluginsFrom(ClassURI.CLASSPATH);
    }
}
