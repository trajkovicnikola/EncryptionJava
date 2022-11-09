package encryption.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import encryption.EncryptionProgram;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EncryptionGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNewKey;
	private JButton btnExport;
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JButton btnImport;

	private EncryptionProgram ep = new EncryptionProgram();
	private JScrollPane scrollPane;
	private JTextArea textMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptionGUI frame = new EncryptionGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EncryptionGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(contentPane,
						"You should export your key before closing." + "\n" + "Are you sure you want to exit?", "Exit",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		setTitle("Encryption");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 731, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		getPanel().setLayout(null);
		contentPane.add(getPanel(), BorderLayout.WEST);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(150, 10));
			panel.add(getBtnNewKey());
			panel.add(getBtnExport());
			panel.add(getBtnEncrypt());
			panel.add(getBtnDecrypt());
			panel.add(getButton_1());
		}
		return panel;
	}

	private JButton getBtnNewKey() {
		if (btnNewKey == null) {
			btnNewKey = new JButton("New key");
			btnNewKey.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ep.newKey();
//					textKey.setText("");
//					for(Character c:ep.getList())
//						textKey.append(c.toString());
//					textKey.append("\n");
//					for(Character c:ep.getShuffledList())
//						textKey.append(c.toString());
////					textKey.setText(ep.getList().toString());
////					textKey.append("\n");
////					textKey.append(ep.getShuffledList().toString());
				}
			});
			btnNewKey.setBounds(6, 6, 117, 29);
		}
		return btnNewKey;
	}

	private JButton getBtnExport() {
		if (btnExport == null) {
			btnExport = new JButton("Export key");
			btnExport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						JFileChooser fileChooser = new JFileChooser();
						int option  = fileChooser.showSaveDialog(null);
						if(option == fileChooser.APPROVE_OPTION)
							ep.saveKey(fileChooser.getSelectedFile().getAbsolutePath());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnExport.setBounds(6, 47, 117, 29);
		}
		return btnExport;
	}

	private JButton getBtnEncrypt() {
		if (btnEncrypt == null) {
			btnEncrypt = new JButton("Encrypt");
			btnEncrypt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String message = textMessage.getText();
						String newMessage = ep.encrypt(message);
						textMessage.setText(newMessage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnEncrypt.setBounds(6, 129, 117, 29);
		}
		return btnEncrypt;
	}

	private JButton getBtnDecrypt() {
		if (btnDecrypt == null) {
			btnDecrypt = new JButton("Decrypt");
			btnDecrypt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String message = textMessage.getText();
						String newMessage = ep.decrypt(message);
						textMessage.setText(newMessage);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnDecrypt.setBounds(6, 170, 117, 29);
		}
		return btnDecrypt;
	}

	private JButton getButton_1() {
		if (btnImport == null) {
			btnImport = new JButton("Import key");
			btnImport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						JFileChooser fileChooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
						fileChooser.setFileFilter(filter);
						int option = fileChooser.showOpenDialog(null);
						if(option == fileChooser.APPROVE_OPTION) {
							String path = fileChooser.getSelectedFile().getAbsolutePath();
							String key = ep.readKey(path);
							ep.setKey(key);
//							textKey.setText("");
//							for(Character c:ep.getList())
//								textKey.append(c.toString());
//							textKey.append("\n");
//							for(Character c:ep.getShuffledList())
//								textKey.append(c.toString());
							
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnImport.setBounds(6, 88, 117, 29);
		}
		return btnImport;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextMessage());
		}
		return scrollPane;
	}
	private JTextArea getTextMessage() {
		if (textMessage == null) {
			textMessage = new JTextArea();
		}
		return textMessage;
	}
}
