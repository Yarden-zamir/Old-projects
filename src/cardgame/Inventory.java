package cardgame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

/*
 * @author Yarden zamir
 */
public class Inventory extends JTabbedPane implements MouseListener, ActionListener {

	public static JButton learnSkill = new JButton("Learn skill"),
			equipSkill = new JButton("Equip skill");
	public static JLabel skillPointDisplay = new JLabel();
	public static JPanel skillActions = new JPanel();
	public static boolean IsInvOpen = false;
	public static boolean IsMoving = false;
	public static slot[] Slots = new slot[30];
	public static slot[] eqwSlots = new slot[6];
	public static JLayeredPane invPane = new JLayeredPane();
	public JScrollPane skillsScrollerPane = new JScrollPane();
	public static JLayeredPane skillsPane = new JLayeredPane();
	public static JPanel craftingPane = new JPanel();
	public JPanel statsPane = new JPanel();
	public JPanel jornalPane = new JPanel();
	public Card cardDisplay = new Card();
	private JPanel statusTable = new JPanel();
	private JPanel overStat = new JPanel();
	private Timer T = new Timer(1, this);
	private Scanner in = new Scanner(System.in);
	private JButton SkillScrollerRight = new BasicArrowButton(SwingConstants.EAST);
	private JButton SkillScrollerLeft = new BasicArrowButton(SwingConstants.WEST);
	private JButton SkillScrollerUp = new BasicArrowButton(SwingConstants.NORTH);
	private JButton SkillScrollerDown = new BasicArrowButton(SwingConstants.SOUTH);
	public static overStat DmgOverStat = new overStat("Damage"),
			AccOverStat = new overStat("Accuracy"),
			DefOverStat = new overStat("Defence"),
			LootOverStat = new overStat("Looting"),
			AgilityOverStat = new overStat("Agility");

	public statDisplay strD = new statDisplay("STR", 25),
			dexD = new statDisplay("DEX", 25),
			conD = new statDisplay("CON", 25),
			wisD = new statDisplay("WIS", 105),
			intD = new statDisplay("INT", 105),
			lckD = new statDisplay("LCK", 105),
			lvlD = new statDisplay("LVL", 25);

	public Inventory() {

		invPane.setBackground(Color.red);
		addMouseListener(this); //adds the listener that opens and closes the inv.
	}

	public static void drager(Item i, int a) {
		invPane.setLayer(i, JLayeredPane.DRAG_LAYER + a);
	}

	public void Up() {
		setSize(1000, 652);
		setBackground(Color.gray);
		setOpaque(true);
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setLocation(-975, 25);
		IsInvOpen = false;
		setTabs();
		setInv();
		setSkills();
		setCraftingBench();
		addMouseListener(null);
		setOverStat();
	}

	public void setTabs() {
		invPane = new JLayeredPane();
		add("Inventory", invPane);
		add("Skills", skillsScrollerPane);
		add("Stats", statsPane);
		add("Jornal", jornalPane);
		setIconAt(0, new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(Player.RsrcFolder + "\\icons\\GUI\\invIcon1.png"))));
		addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\clicks\\click" + (rnd.nextInt(3) + 1) + ".ogg"));
				switch (getSelectedIndex()) {

					case 0:
						setIconAt(0, new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(Player.RsrcFolder + "\\icons\\GUI\\invIcon1.png"))));
						setIconAt(1, null);
						setIconAt(2, null);
						setIconAt(3, null);
						break;
					case 1:
						setIconAt(1, new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(Player.RsrcFolder + "\\icons\\GUI\\skills.png"))));
						setIconAt(0, null);
						setIconAt(2, null);
						setIconAt(3, null);
						break;
					case 2:
						setIconAt(2, new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(Player.RsrcFolder + "\\icons\\GUI\\statistics.png"))));
						setIconAt(0, null);
						setIconAt(1, null);
						setIconAt(3, null);
						break;
					case 3:
						setIconAt(3, new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(Player.RsrcFolder + "\\icons\\GUI\\journal.png"))));
						setIconAt(1, null);
						setIconAt(2, null);
						setIconAt(0, null);
						break;
				}
			}
		});
	}

	public void setInv() {

		invPane.setLayout(null);
		invPane.add(strD);
		invPane.add(dexD);
		invPane.add(conD);
		invPane.add(wisD);
		invPane.add(intD);
		invPane.add(lckD);
		invPane.add(lvlD);

		invPane.add(cardDisplay);
		cardDisplay.setLocation(705, 25);
		addStatusTable();
		addSlots();
	}

	private Random rnd = new Random();
	public static Card skillsCardD = new Card();
	public static JPanel extraLayerSkill = new JPanel();

	public void setSkills() {

		skillsScrollerPane.setViewportView(extraLayerSkill);
		extraLayerSkill.setLayout(null);
		extraLayerSkill.add(skillsPane);

		skillsPane.setLayout(null);
		skillsPane.setSize(700, (getParent().getSize().height - 50));
		skillsPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		skillsPane.setLayer(SkillScrollerDown, JLayeredPane.DRAG_LAYER);
		skillsPane.setLayer(SkillScrollerUp, JLayeredPane.DRAG_LAYER);
		skillsPane.setLayer(SkillScrollerRight, JLayeredPane.DRAG_LAYER);
		skillsPane.setLayer(SkillScrollerLeft, JLayeredPane.DRAG_LAYER);
		extraLayerSkill.add(skillsCardD);
		skillsCardD.setLocation(735, 25);

		Skill.loadDisabledSkills();
		Skill.shownSkills.get(0).setShown();

		extraLayerSkill.add(skillActions);
		skillActions.setSize(220, 150);
		skillActions.setLocation(735, 410);
		skillActions.setBorder(new BevelBorder(BevelBorder.RAISED));
		skillActions.setBackground(Color.lightGray);
		skillActions.add(learnSkill);
		learnSkill.setEnabled(false);
		skillActions.add(equipSkill);
		equipSkill.setEnabled(false);
		skillActions.add(skillPointDisplay);
		skillPointDisplay.setText(Player.skillpoints + "");
		skillPointDisplay.setFont(skillPointDisplay.getFont().deriveFont(3, 40));

		learnSkill.setActionCommand("LernSkill");
		equipSkill.setActionCommand("EquipSkill");
		learnSkill.addActionListener(this);
		equipSkill.addActionListener(this);

		skillsPane.setBackground(Color.lightGray);
		skillsPane.setOpaque(true);
		SkillScrollerRight.setLocation(655, (getParent().getSize().height - 50) / 2 - 125);
		SkillScrollerRight.setSize(25, 200);
		SkillScrollerLeft.setLocation(10, (getParent().getSize().height - 50) / 2 - 125);
		SkillScrollerLeft.setSize(25, 200);
		SkillScrollerUp.setLocation(240, 15);
		SkillScrollerUp.setSize(200, 25);
		SkillScrollerDown.setLocation(240, (getParent().getSize().height - 50) - 105);
		SkillScrollerDown.setSize(200, 25);
		skillsPane.add(SkillScrollerRight);
		skillsPane.add(SkillScrollerLeft);
		skillsPane.add(SkillScrollerDown);
		skillsPane.add(SkillScrollerUp);
		SkillScrollerDown.setActionCommand("skillDown");
		SkillScrollerUp.setActionCommand("skillUp");
		SkillScrollerLeft.setActionCommand("skillLeft");
		SkillScrollerRight.setActionCommand("skillRight");
		SkillScrollerDown.addActionListener(this);
		SkillScrollerLeft.addActionListener(this);
		SkillScrollerRight.addActionListener(this);
		SkillScrollerUp.addActionListener(this);

	}

	public void addStatusTable() {
		invPane.add(statusTable);
		statusTable.setSize(660, 55);
		statusTable.setLocation(25, 330);
		statusTable.setBorder(new BevelBorder(BevelBorder.RAISED));
		statusTable.setBackground(Color.lightGray);
	}

	private void setOverStat() {
		invPane.add(overStat);
		overStat.setSize(265, 285);
		overStat.setBorder(new BevelBorder(BevelBorder.RAISED));
		overStat.setLocation(190, 25);
		overStat.setBackground(Color.LIGHT_GRAY);
		overStat.add(DmgOverStat);
		overStat.add(DefOverStat);
		overStat.add(AccOverStat);
		overStat.add(LootOverStat);
		overStat.add(AgilityOverStat);
		refreshStats();
	}

	public void refreshStats() {
		strD.setText(Player.STR + "");
		dexD.setText(Player.DEX + "");
		conD.setText(Player.CON + "");
		wisD.setText(Player.WIS + "");
		intD.setText(Player.INT + "");
		lckD.setText(Player.LCK + "");
		lvlD.setText(Player.LVL + "");
		skillPointDisplay.setText(Player.skillpoints + "");
		Player.DMG = (Player.LVL + Player.STR + (Player.CON - 10) / 2 + (Player.DEX - 10) / 4 + Player.DMGb - 1);
		Player.DEF = (Player.LVL + (Player.CON - 5) / 2 + Player.DEFb + (Player.DEX - 5) + 2);
		Player.ACC = (Player.LVL + Player.DEX + (Player.STR - 10) / 2 + Player.ACCb - 1);
		Player.LOOT = (Player.LVL + Player.LCK + (Player.DEX - 10) / 2 + Player.LOTb - 1);
		Player.AGL = (Player.LVL + Player.DEX - 1 + (Player.STR - 10) / 2);
		DmgOverStat.value.setText(Player.DMG + "");
		DefOverStat.value.setText(Player.DEF + "");
		AccOverStat.value.setText(Player.ACC + "");
		LootOverStat.value.setText(Player.LOOT + "");
		AgilityOverStat.value.setText(Player.AGL + "");
	}

	public void addSlots() {
		//storage slots
		int Pl = 0;
		int X = 0, Y = 330;
		for (int i = 0; i < 3; i++) {
			Y += 65;
			X = 25;
			for (int I = 0; I < 10; I++) {
				Slots[Pl] = new slot(X, Y);
				invPane.add(Slots[Pl]);
				Pl++;
				X += 67;
			}
		}

		addItem(new Weapon());
		addItem(new Weapon());
		addItem(new Weapon());
		addItem(new Weapon());
		addItem(new Weapon());
		addItem(new Weapon());
		addItem(new Weapon());

		addItem(new Food());
		addItem(new Food());
		addItem(new Food());
		addItem(new Food());
		addItem(new Food());
		addItem(new Food());

		addItem(new Armour());
		addItem(new Armour());
		addItem(new Armour());
		addItem(new Armour());
		addItem(new Armour());
		addItem(new Armour());

		//(right = (630,100))
		eqwSlots[0] = new slot(630, 100);
		invPane.add(eqwSlots[0]);
		eqwSlots[1] = new slot(480, 100);
		invPane.add(eqwSlots[1]);
		int x = 555, y = 25;
		for (int i = 2; i < 6; i++) {
			eqwSlots[i] = new slot(x, y);
			invPane.add(eqwSlots[i]);
			y += 78;
		}
	}

	public void setCraftingBench() {
		invPane.add(craftingPane);
		craftingPane.setLayout(null);
		craftingPane.setSize(220, 180);
		craftingPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		craftingPane.setLocation(705, 395);
		craftingPane.setBackground(Color.LIGHT_GRAY);
	}

	public static void addItem(Item I) {
		for (int i = 0; i < 30; i++) {
			if (Slots[i].isEmpty) {
				Slots[i].isEmpty = false;
				invPane.add(I);
				I.setLocation(Slots[i].getLocation());
				invPane.setLayer(I, JLayeredPane.DRAG_LAYER);
				I.mySlot = Slots[i].getLocation();
				I.id = i;
				i = 30;
				Journal.append("\nYou got : " + I.name);
				Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\pickUp.wav"));
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (getMousePosition().x >= 950 && !IsMoving) {  //clicked on the "Move"
			T.setActionCommand("Timer");
			T.start();
			IsMoving = true;
			Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\inventory.wav"));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	private static int x = 0;

	@Override
	public void actionPerformed(ActionEvent e) {  //the timer's lisener
		if (e.getActionCommand().equals("Timer")) {

			int speed = 15;
			if (IsInvOpen) {
				//close
				setLocation(getLocation().x - speed, 25);
				x++;
				if (x >= 65) {
					T.stop();
					IsMoving = false;
					x = 0;
					IsInvOpen = false;
				}
			} else {
				//open
				setLocation(getLocation().x + speed, 25);
				x++;
				if (x >= 65) {
					T.stop();
					IsMoving = false;
					x = 0;
					IsInvOpen = true;
				}
			}
		} else if (e.getActionCommand().equals("skillRight")) {
			Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\slotSound.wav"));
			for (int i = 0; i < Skill.shownSkills.size(); i++) {
				Skill.shownSkills.get(i).setLocation(Skill.shownSkills.get(i).getX() - 25, Skill.shownSkills.get(i).getY());
			}

		} else if (e.getActionCommand().equals("skillLeft")) {
			Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\slotSound.wav"));
			for (int i = 0; i < Skill.shownSkills.size(); i++) {
				Skill.shownSkills.get(i).setLocation(Skill.shownSkills.get(i).getX() + 25, Skill.shownSkills.get(i).getY());
			}

		} else if (e.getActionCommand().equals("skillDown")) {
			Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\slotSound.wav"));
			for (int i = 0; i < Skill.shownSkills.size(); i++) {
				Skill.shownSkills.get(i).setLocation(Skill.shownSkills.get(i).getX(), Skill.shownSkills.get(i).getY() - 25);
			}

		} else if (e.getActionCommand().equals("skillUp")) {
			Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\slotSound.wav"));
			for (int i = 0; i < Skill.shownSkills.size(); i++) {
				Skill.shownSkills.get(i).setLocation(Skill.shownSkills.get(i).getX(), Skill.shownSkills.get(i).getY() + 25);
			}
		} else if (e.getActionCommand().equals("LernSkill")) {
			Player.skillpoints -= Integer.parseInt(Skill.displayedSkill.stats.getProperty("LVL"));
			skillPointDisplay.setText(Player.skillpoints + "");
			Skill.displayedSkill.learned = true;

			learnSkill.setEnabled(false);
			equipSkill.setEnabled(true);

			System.out.println(Skill.displayedSkill.stats.getProperty("type"));
			if ("@permAttackSkill".equals(Skill.displayedSkill.stats.getProperty("type"))) {
				equipSkill.setEnabled(false);
				Player.criticBunus.add(Skill.displayedSkill.stats.getProperty("criticBunus"));
				Player.DMGb += Integer.parseInt(Skill.displayedSkill.stats.getProperty("HitBunos"));
				Player.ACCb += Integer.parseInt(Skill.displayedSkill.stats.getProperty("AccBunos"));
			}
			if ("@permAntiSkill".equals(Skill.displayedSkill.stats.getProperty("type"))) {
				equipSkill.setEnabled(false);
				Player.immunites.add(Skill.displayedSkill.stats.getProperty("target"));
			}

			skillsPane.getComponentAt(Skill.displayedSkill.getLocation().x, Skill.displayedSkill.getLocation().y - 100).setEnabled(true);
			skillsPane.getComponentAt(Skill.displayedSkill.getLocation().x, Skill.displayedSkill.getLocation().y + 100).setEnabled(true);
			skillsPane.getComponentAt(Skill.displayedSkill.getLocation().x + 100, Skill.displayedSkill.getLocation().y).setEnabled(true);
			skillsPane.getComponentAt(Skill.displayedSkill.getLocation().x - 100, Skill.displayedSkill.getLocation().y).setEnabled(true);
		} else if (e.getActionCommand().equals("EquipSkill")) {
			SkillTray s = new SkillTray(Skill.displayedSkill);
			GameBoard.hotbar.setLayer(s, 100);
			GameBoard.hotbar.add(s);
			for (int i = 0; i < 9; i++) {
				if (GameBoard.skillSlots[i].isEmpty) {
					s.setLocation(GameBoard.skillSlots[i].getX(), GameBoard.skillSlots[i].getY());
					GameBoard.skillSlots[i].isEmpty = false;
					i = 10;
				}
			}
		}
	}
}

class statDisplay extends JLabel {

	static int Y = 25;
	static int t = 0;

	public statDisplay(String name, int x) {
		setBorder(new TitledBorder(name));
		setLocation(x, Y);
		setSize(65, 65);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(getFont().deriveFont(1, 20));
		Y += 71;
		t++;
		if (t == 3) {
			Y = 25;
		}
		if ("LVL".equals(name)) {
			setSize(145, 65);
		}
	}

}

class slot extends JLabel implements MouseListener {

	boolean isEmpty = true;

	public slot(int x, int y) {
		setBackground(Color.lightGray);
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setSize(50, 50);
		setLocation(x, y);
		setOpaque(true);
		addMouseListener(this);
	}

	public boolean IsInside(Point loc) {
		return (loc.x + 25 > getLocation().x && loc.x + 25 < getLocation().x + 50 && loc.y + 25 > getLocation().y && loc.y + 25 < getLocation().y + 50);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\slotSound.wav"));
		setBorder(new BevelBorder(BevelBorder.RAISED));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBorder(new BevelBorder(BevelBorder.LOWERED));
	}

}

class overStat extends JLabel {

	public JLabel value = new JLabel();

	public overStat(String n) {
		add(value);
		value.setSize(40, 40);
		value.setText("");
		value.setBorder(new BevelBorder(BevelBorder.LOWERED));
		value.setLocation(205, 5);
		value.setHorizontalAlignment(SwingConstants.CENTER);
		value.setFont(getFont().deriveFont(3, 20));
		setSize(250, 50);
		setPreferredSize(getSize());
		setText(n);
		setVerticalAlignment(SwingConstants.CENTER);
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setIcon(new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(Player.RsrcFolder + "\\StatsIcons\\" + n + ".png"))));
	}

}
