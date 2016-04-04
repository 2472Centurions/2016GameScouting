package scootingProgram;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class scootScoot implements ActionListener {
	JFrame f = new JFrame("2016 Strongholds Scouting Program");
	JPanel pmain, ptitle;
	JTextField team = new JTextField(4);
	JLabel TITTLE = new JLabel("Team Scouting:");
	JLabel[] auto = new JLabel[] { new JLabel("Autonomous:                                "), new JLabel("Defense:"),
			new JLabel("Goals:") };
	JLabel lgoal = new JLabel("           Goal:");
	JLabel lowgoal = new JLabel("Low Goals");
	JLabel higoal = new JLabel("High Goals");
	JButton go = new JButton("Finish");
	JButton reset = new JButton("Reset");
	JButton[] add = new JButton[] { new JButton("+"), new JButton("-"), new JButton("+"), new JButton("-") };
	int[] goals = new int[] { 0, 0 };
	JLabel[] lgoals = { new JLabel("0"), new JLabel("0") };
	String[] defenses = { "Portcullis", "Cheval de Frise", "Moat", "Ramparts", "Drawbridge", "Sally Port", "Rock Wall",
			"Rough Terrain", "Low Bar" };
	JComboBox<String>[] box;
	JRadioButton[] non, dam, destr, bauto, bgoal;
	ButtonGroup[] grup, autogrup;
	ButtonGroup ggoal;
	String nl = System.getProperty("line.separator");

	public static void main(String[] args) {
		new scootScoot();
	}

	@SuppressWarnings("unchecked")
	public scootScoot() {
		for (int i = 0; i < 3; i++)
			auto[i].setFont(Font.getFont(Font.SANS_SERIF));
		lgoal.setFont(Font.getFont(Font.SANS_SERIF));
		non = new JRadioButton[5];
		dam = new JRadioButton[5];
		destr = new JRadioButton[5];
		box = new JComboBox[5];
		grup = new ButtonGroup[5];
		bauto = new JRadioButton[6];
		bgoal = new JRadioButton[3];
		{
			bauto[0] = new JRadioButton("Reached");
			bauto[1] = new JRadioButton("Crossed");
			bauto[2] = new JRadioButton("Not Reached                                   ");
			bauto[3] = new JRadioButton("High");
			bauto[4] = new JRadioButton("Low");
			bauto[5] = new JRadioButton("None");
			bgoal[0] = new JRadioButton("Scaled");
			bgoal[1] = new JRadioButton("Reached");
			bgoal[2] = new JRadioButton("Not Reached");
		}
		for (int i = 0; i < add.length; i++)
			add[i].addActionListener(this);
		autogrup = new ButtonGroup[2];
		ggoal = new ButtonGroup();
		{
			for (int i = 0; i < 2; i++)
				autogrup[i] = new ButtonGroup();
			for (int i = 0; i < 3; i++) {
				autogrup[0].add(bauto[i]);
				autogrup[1].add(bauto[i + 3]);
			}
			for (int i = 0; i < 3; i++)
				ggoal.add(bgoal[i]);
			ggoal.setSelected(bgoal[2].getModel(), true);
			autogrup[0].setSelected(bauto[2].getModel(), true);
			autogrup[1].setSelected(bauto[5].getModel(), true);
		}
		pmain = new JPanel();
		go.addActionListener(this);
		reset.addActionListener(this);
		for (int i = 0; i < 5; i++) {
			grup[i] = new ButtonGroup();
			box[i] = new JComboBox<String>(defenses);
			non[i] = new JRadioButton("Undamaged");
			dam[i] = new JRadioButton("Damaged");
			destr[i] = new JRadioButton("Destroyed         ");
			grup[i].add(non[i]);
			grup[i].add(dam[i]);
			grup[i].add(destr[i]);
			grup[i].setSelected(non[i].getModel(), true);

		}
		ptitle = new JPanel();
		f.setSize(233, 630);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(ptitle, BorderLayout.NORTH);
		ptitle.add(TITTLE);
		ptitle.add(team);
		ptitle.add(reset);
		f.add(pmain);
		pmain.add(auto[0], BorderLayout.LINE_START);
		pmain.add(auto[1]);
		for (int i = 0; i < 3; i++)
			pmain.add(bauto[i]);
		pmain.add(auto[2]);
		for (int i = 3; i < 6; i++)
			pmain.add(bauto[i]);
		for (int i = 0; i < 5; i++) {
			pmain.add(box[i]);
			pmain.add(non[i]);
			pmain.add(dam[i]);
			pmain.add(destr[i]);
		}
		pmain.add(lowgoal);
		pmain.add(add[0]);
		pmain.add(lgoals[0]);
		pmain.add(add[1]);
		pmain.add(higoal);
		pmain.add(add[2]);
		pmain.add(lgoals[1]);
		pmain.add(add[3]);
		pmain.add(lgoal);
		for (int i = 0; i < 3; i++)
			pmain.add(bgoal[i]);
		f.add(go, BorderLayout.SOUTH);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add[0]) {
			goals[0]++;
		}
		if (e.getSource() == add[1]) {
			if (goals[0] > 0) {
				goals[0]--;
			}
		}
		if (e.getSource() == add[2]) {
			goals[1]++;
		}
		if (e.getSource() == add[3]) {
			if (goals[1] > 0) {
				goals[1]--;
			}
		}
		if (e.getSource() == go) {

			try {
				FileWriter fw = new FileWriter("Scout Data.txt", true);
				fw.write("------Team #" + team.getText() + nl + "AUTONOMOUS:" + nl + nl + "Defense ");
				if (bauto[0].isSelected())
					fw.write("reached");
				if (bauto[1].isSelected())
					fw.write("crossed");
				if (bauto[2].isSelected())
					fw.write("not reached");
				fw.write(nl);
				if (bauto[3].isSelected())
					fw.write("High goal");
				if (bauto[4].isSelected())
					fw.write("Low goal");
				if (bauto[5].isSelected())
					fw.write("No goals");
				fw.write(nl + nl + "TELEOP:" + nl);
				for (int i = 0; i < 5; i++) {
					fw.write(box[i].getItemAt(box[i].getSelectedIndex()));
					if (non[i].isSelected())
						fw.write(" not even touched");
					if (dam[i].isSelected())
						fw.write(" damaged");
					if (destr[i].isSelected())
						fw.write(" destroyed");
					fw.write(nl + nl);
				}
				fw.write("Low goals: " + goals[0] + nl + "High goals: " + goals[1] + nl + "Tower ");
				if (bgoal[0].isSelected())
					fw.write("scaled");
				if (bgoal[1].isSelected())
					fw.write("reached");
				if (bgoal[2].isSelected())
					fw.write("not even reached!");
				fw.write(nl + nl);
				fw.flush();
				fw.close();
				go.setText("Saved!");
			} catch (IOException e1) {
				e1.printStackTrace();
				go.setText("Error writing to file!");
			}

		}
		if (e.getSource() == reset) {
			team.setText("");
			go.setText("Finish");
			goals[0] = 0;
			goals[1] = 0;
		}
		lgoals[0].setText("" + goals[0]);
		lgoals[1].setText("" + goals[1]);
	}
}
