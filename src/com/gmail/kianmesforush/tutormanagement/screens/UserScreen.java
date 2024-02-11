package com.gmail.kianmesforush.tutormanagement.screens;

import com.gmail.kianmesforush.tutormanagement.Screen;

import javax.swing.*;
import java.awt.*;

public class UserScreen implements Screen {
	JLabel messageLabel = new JLabel("Click on a button");
	
	public JComponent show() {
		panel.add(messageLabel);
		return panel;
	}
}
