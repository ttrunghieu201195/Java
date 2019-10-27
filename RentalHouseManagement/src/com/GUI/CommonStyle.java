package com.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CommonStyle {

	static final String FONT = "Serif";

	Border line = BorderFactory.createLineBorder(Color.GRAY);

	Border empty = new EmptyBorder(0, 3, 0, 0);

	CompoundBorder roundedBorder = new CompoundBorder(line, empty);

	Font title = new Font(FONT, Font.BOLD, 24);

	Font label = new Font(FONT, Font.BOLD, 16);

	Font text = new Font(FONT, Font.PLAIN, 16);

}
