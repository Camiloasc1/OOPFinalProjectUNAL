/*******************************************************************************
 * File: DisplayMessage.java
 * Project: Stratego
 * 
 * Copyright (C) 2014 Camiloasc1.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package gui.util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DisplayMessage extends JDialog
{
	private static final long serialVersionUID = 9101381006883587464L;
	private final JPanel contentPanel = new JPanel();
	private boolean ready;
	private boolean response;
	
	/**
	 * Show player message dialog.
	 * 
	 * @param str
	 *            Caption to show
	 * @return
	 *         <b>false</b> if !wait
	 *         <p>
	 *         <b>true</b> if OK pressed
	 *         <p>
	 *         <b>false</b> if Cancel pressed
	 */
	public static boolean show(String str)
	{
		return show(false, false, str);
	}
	
	/**
	 * Show player message dialog.
	 * 
	 * @param wait
	 *            If wait for response
	 * @param str
	 *            Caption to show
	 * @return
	 *         <b>false</b> if !wait
	 *         <p>
	 *         <b>true</b> if OK pressed
	 *         <p>
	 *         <b>false</b> if Cancel pressed
	 */
	public static boolean show(boolean wait, String str)
	{
		return show(false, false, str);
	}
	
	/**
	 * Show player message dialog.
	 * 
	 * @param cancelOpt
	 *            If add cancel button
	 * @param wait
	 *            If wait for response
	 * @param str
	 *            Caption to show
	 * @return
	 *         <b>false</b> if !wait
	 *         <p>
	 *         <b>true</b> if OK pressed
	 *         <p>
	 *         <b>false</b> if Cancel pressed
	 */
	public static boolean show(boolean cancelOpt, boolean wait, String str)
	{
		DisplayMessage dialog = new DisplayMessage(cancelOpt, str);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		if (wait)
		{
			while (!dialog.ready)
			{
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
				}
			}
		}
		return (dialog.response && wait);
	}
	
	/**
	 * Create the dialog.
	 * 
	 * @param cancelOpt
	 *            If add cancel button
	 * @param str
	 *            Caption to show
	 */
	private DisplayMessage(boolean cancelOpt, String str)
	{
		ready = false;
		
		// setModal(true);
		setAlwaysOnTop(true);
		setBounds(512, 256, 300, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel lblMessage = new JLabel(str);
			contentPanel.add(lblMessage);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						response = true;
						ready = true;
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				if (cancelOpt)
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg0)
						{
							response = false;
							ready = true;
							dispose();
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}
}
