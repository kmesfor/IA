package com.gmail.kianmesforush.tutormanagement;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//https://stackoverflow.com/questions/4172940/how-to-set-background-color-of-a-button-in-java-gui
//https://stackoverflow.com/questions/14159536/creating-jbutton-with-customized-look
//https://stackoverflow.com/questions/29460600/how-to-set-a-background-color-to-a-jframe-containing-multiple-jpanels-jbuttons
//https://gist.github.com/anonymous/ceab52cc7082c34baf1c


public class StylingManager {
	public static class StylePreset {
		public static final Color LIGHT_GREEN = new Color(204, 234, 209);
		public static final Color PRIMARY_GREEN = new Color(105, 190, 124);
		public static final Color GRAY = new Color(79, 87, 80);
		public static final Color WHITE = new Color(255, 255, 255);
		public static final Color ERROR = Color.RED;
		public static final Font FONT_BOLD = new Font("Open Sans", Font.BOLD, 16);
		public static final Font FONT_NORMAL = new Font("Open Sans", Font.PLAIN, 14);
		
	}
	
	public static void stylize(JButton button, StyleType type) {
		button.setOpaque(true);
		button.setFont(StylePreset.FONT_BOLD);
		if (type == StyleType.PRIMARY) {
			button.setBackground(StylePreset.PRIMARY_GREEN);
			button.setForeground(StylePreset.WHITE);
		} else if (type == StyleType.SECONDARY) {
			button.setBackground(StylePreset.LIGHT_GREEN);
			button.setForeground(StylePreset.PRIMARY_GREEN);
		}
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
		
		button.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(StylePreset.GRAY, 1, true),
				BorderFactory.createEmptyBorder(5,5,5,5)
		));
	}
	
	public static void stylize(JPanel panel, StyleType type) {
		panel.setOpaque(true);
		makeChildrenTranslucent(panel);
		if (type == StyleType.PRIMARY) {
			panel.setBackground(StylePreset.LIGHT_GREEN);
		} else if (type == StyleType.SECONDARY) {
			panel.setBackground(StylePreset.WHITE);
		}
	}
	
	public static void stylize(JTextField textField, StyleType type) {
		textField.setFont(StylePreset.FONT_BOLD);
		textField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(StylePreset.GRAY, 1, true),
				BorderFactory.createEmptyBorder(5,5,5,5)
		));
		if (type == StyleType.PRIMARY) {
			textField.setBackground(StylePreset.LIGHT_GREEN);
			textField.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			textField.setBackground(StylePreset.WHITE);
			textField.setForeground(StylePreset.GRAY);
		}
	}
	
	public static void stylize(JLabel label, StyleType type) {
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		if (type == StyleType.PRIMARY) {
			label.setFont(StylePreset.FONT_BOLD);
			label.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			label.setFont(StylePreset.FONT_NORMAL);
			label.setForeground(StylePreset.GRAY);
		} else if (type == StyleType.ERROR) {
			label.setFont(StylePreset.FONT_NORMAL);
			label.setForeground(StylePreset.ERROR);
		}
	}
	
	public static void stylize(JCheckBox checkbox, StyleType type) {
		if (type == StyleType.PRIMARY) {
			checkbox.setFont(StylePreset.FONT_BOLD);
			checkbox.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			checkbox.setFont(StylePreset.FONT_BOLD);
			checkbox.setForeground(StylePreset.GRAY);
		}
	}
	
	public static void stylize(JSpinner spinner, StyleType type) {
		spinner.setFont(StylePreset.FONT_BOLD);
		if (type == StyleType.PRIMARY) {
			spinner.setBackground(StylePreset.LIGHT_GREEN);
			spinner.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			spinner.setBackground(StylePreset.WHITE);
			spinner.setForeground(StylePreset.GRAY);
		}
	}
	
	public static void stylize(JTabbedPane pane, StyleType type) {
		pane.setFont(StylePreset.FONT_BOLD);
		if (type == StyleType.PRIMARY) {
			pane.setBackground(StylePreset.LIGHT_GREEN);
			pane.setForeground(StylePreset.PRIMARY_GREEN);
		} else if (type == StyleType.SECONDARY) {
			pane.setBackground(StylePreset.WHITE);
			pane.setForeground(StylePreset.GRAY);
		}
	}
	
	private static void makeChildrenTranslucent(JPanel panel) {
		for (Component component : panel.getComponents()) {
			if (component instanceof JPanel) {
				makeChildrenTranslucent((JPanel) component);
				((JPanel) component).setOpaque(false);
			}
		}
	}
}
