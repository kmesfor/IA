package com.gmail.kianmesforush.tutormanagement;

import com.gmail.kianmesforush.tutormanagement.datatypes.StyleType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//https://stackoverflow.com/questions/4172940/how-to-set-background-color-of-a-button-in-java-gui
//https://stackoverflow.com/questions/14159536/creating-jbutton-with-customized-look
//https://stackoverflow.com/questions/29460600/how-to-set-a-background-color-to-a-jframe-containing-multiple-jpanels-jbuttons
//https://gist.github.com/anonymous/ceab52cc7082c34baf1c

/**
 * The static management class for stylizing the program's components.
 * Utilizes StylePreset and StyleType
 */
public class StylingManager {
	/**
	 * The static constant class holding color and font presets.
	 */
	public static class StylePreset {
		public static final Color LIGHT_GREEN = new Color(204, 234, 209);
		public static final Color PRIMARY_GREEN = new Color(105, 190, 124);
		public static final Color GRAY = new Color(79, 87, 80);
		public static final Color WHITE = new Color(255, 255, 255);
		public static final Color ERROR = Color.RED;
		public static final Font FONT_BOLD = new Font("Open Sans", Font.BOLD, 16);
		public static final Font FONT_NORMAL = new Font("Open Sans", Font.PLAIN, 14);
		
	}
	
	/**
	 * Stylizes a JButton according to a StyleType.
	 * @param button the JButton to be styled
	 * @param type the type of styling to be applied
	 */
	public static void stylize(JButton button, StyleType type) {
		//Allows the button background to be seen
		button.setOpaque(true);
		//Set the font
		button.setFont(StylePreset.FONT_BOLD);
		//Stylize background and foreground depending on input StyleType
		if (type == StyleType.PRIMARY) {
			button.setBackground(StylePreset.PRIMARY_GREEN);
			button.setForeground(StylePreset.WHITE);
		} else if (type == StyleType.SECONDARY) {
			button.setBackground(StylePreset.LIGHT_GREEN);
			button.setForeground(StylePreset.PRIMARY_GREEN);
		}
		//Make the button react to hover movements if the button is enabled (can be clicked)
		Color bg = button.getBackground();
		Color fg = button.getForeground();
		if (button.isEnabled()) {
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					button.setBackground(StylePreset.WHITE);
					button.setForeground(StylePreset.PRIMARY_GREEN);
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					button.setBackground(bg);
					button.setForeground(fg);
				}
			});
		}
		
		//Add padding (empty border) around a regular line border
		button.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(StylePreset.GRAY, 1, true),
				BorderFactory.createEmptyBorder(5,5,5,5)
		));
	}
	
	/**
	 * Stylizes a JPanel according to a StyleType.
	 * @param panel the JPanel to be styled
	 * @param type the type of styling to be applied
	 */
	public static void stylize(JPanel panel, StyleType type) {
		//Allow the panel background to be seen
		panel.setOpaque(true);
		//Make all children JPanels transparent (see method definition for explanation)
		makeChildrenTransparent(panel);
		//Style the JPanel accordingly
		if (type == StyleType.PRIMARY) {
			panel.setBackground(StylePreset.LIGHT_GREEN);
		} else if (type == StyleType.SECONDARY) {
			panel.setBackground(StylePreset.WHITE);
		}
	}
	
	/**
	 * Stylizes a JTextField according to a StyleType.
	 * @param textField the JTextField to be styled
	 * @param type the type of styling to be applied
	 */
	public static void stylize(JTextField textField, StyleType type) {
		//Set the font
		textField.setFont(StylePreset.FONT_BOLD);
		//Add padding (empty border) around a regular line border
		textField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(StylePreset.GRAY, 1, true),
				BorderFactory.createEmptyBorder(5,5,5,5)
		));
		//Style the JTextField accordingly
		if (type == StyleType.PRIMARY) {
			textField.setBackground(StylePreset.LIGHT_GREEN);
			textField.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			textField.setBackground(StylePreset.WHITE);
			textField.setForeground(StylePreset.GRAY);
		}
	}
	
	/**
	 * Stylizes a JLabel according to a StyleType.
	 * @param label the JLabel to be styled
	 * @param type the type of styling to be applied
	 */
	public static void stylize(JLabel label, StyleType type) {
		//Align text vertically and horizontally center
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		//Style the JLabel accordingly
		if (type == StyleType.PRIMARY) {
			label.setFont(StylePreset.FONT_BOLD);
			label.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			label.setFont(StylePreset.FONT_NORMAL);
			label.setForeground(StylePreset.GRAY);
		//Error styling for red text
		} else if (type == StyleType.ERROR) {
			label.setFont(StylePreset.FONT_NORMAL);
			label.setForeground(StylePreset.ERROR);
		}
	}
	
	/**
	 * Stylizes a JCheckBox according to a StyleType.
	 * @param checkbox the JCheckBox to be styled
	 * @param type the type of styling to be applied
	 */
	public static void stylize(JCheckBox checkbox, StyleType type) {
		//Style the JCheckBox accordingly
		if (type == StyleType.PRIMARY) {
			checkbox.setFont(StylePreset.FONT_BOLD);
			checkbox.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			checkbox.setFont(StylePreset.FONT_BOLD);
			checkbox.setForeground(StylePreset.GRAY);
		}
	}
	
	/**
	 * Stylizes a JSpinner according to a StyleType.
	 * @param spinner the JSpinner to be styled
	 * @param type the type of styling to be applied
	 */
	public static void stylize(JSpinner spinner, StyleType type) {
		//Set the text font
		spinner.setFont(StylePreset.FONT_BOLD);
		//Style the JSpinner accordingly
		if (type == StyleType.PRIMARY) {
			spinner.setBackground(StylePreset.LIGHT_GREEN);
			spinner.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			spinner.setBackground(StylePreset.WHITE);
			spinner.setForeground(StylePreset.GRAY);
		}
	}
	
	/**
	 * Stylizes a JTabbedPane according to a StyleType.
	 * @param pane the JTabbedPane to be styled
	 * @param type the type of styling to be applied
	 */
	public static void stylize(JTabbedPane pane, StyleType type) {
		//Set the text font
		pane.setFont(StylePreset.FONT_BOLD);
		//Style the JTabbedPane accordingly
		if (type == StyleType.PRIMARY) {
			pane.setBackground(StylePreset.LIGHT_GREEN);
			pane.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			pane.setBackground(StylePreset.WHITE);
			pane.setForeground(StylePreset.GRAY);
		}
	}
	
	/**
	 * Recursively makes all JPanels embedded within a parent JPanel transparent.
	 * The purpose of this method is to ensure that when setting the background
	 * color of a JPanel, overlapping panels are not preventing the color from
	 * being applied.
	 * @param panel the parent JPanel
	 */
	private static void makeChildrenTransparent(JPanel panel) {
		for (Component component : panel.getComponents()) {
			if (component instanceof JPanel) {
				makeChildrenTransparent((JPanel) component);
				((JPanel) component).setOpaque(false);
			}
		}
	}
}
