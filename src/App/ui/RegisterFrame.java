package app.ui;

import app.service.AuthService;
import app.service.Localization;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle(Localization.get("register.title"));
        setSize(420, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color primaryBlue = new Color(33, 150, 243);
        Color secondaryBlue = new Color(100, 181, 246);
        Color backgroundBlue = new Color(227, 242, 253);
        Color white = Color.WHITE;

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundBlue);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        formPanel.setBackground(white);
        formPanel.setPreferredSize(new Dimension(320, 320));

        JLabel lblTitle = new JLabel(Localization.get("register.title"), SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(primaryBlue);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBackground(white);
        JLabel lblUser = new JLabel(Localization.get("label.username"));
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setForeground(primaryBlue);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField txtUser = new JTextField(20);
        txtUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userPanel.add(lblUser);
        userPanel.add(txtUser);

        JPanel passPanel = new JPanel();
        passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.Y_AXIS));
        passPanel.setBackground(white);
        JLabel lblPass = new JLabel(Localization.get("label.password"));
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPass.setForeground(primaryBlue);
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPasswordField txtPass = new JPasswordField(20);
        txtPass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passPanel.add(lblPass);
        passPanel.add(txtPass);

        JButton btnRegister = new JButton(Localization.get("btn.register"));
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setBackground(primaryBlue);
        btnRegister.setForeground(white);
        btnRegister.setFocusPainted(false);
        btnRegister.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton btnBack = new JButton(Localization.get("btn.back"));
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setBackground(secondaryBlue);
        btnBack.setForeground(white);
        btnBack.setFocusPainted(false);
        btnBack.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnRegister.addActionListener(e -> {
            String username = txtUser.getText().trim();
            String password = new String(txtPass.getPassword()).trim();

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, Localization.get("msg.username.empty"), "Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (password.length() < 8) {
                JOptionPane.showMessageDialog(this, Localization.get("msg.password.too_short"), "Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Hapus hash manual, kirim plain password saja
            if (AuthService.register(username, password)) {
                JOptionPane.showMessageDialog(this, Localization.get("msg.register.success"));
                dispose();
                new LoginFrame();
            } else {
                JOptionPane.showMessageDialog(this, Localization.get("msg.register.fail"));
            }
        });

        btnBack.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        formPanel.add(lblTitle);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(userPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(passPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(btnRegister);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(btnBack);

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBackground(backgroundBlue);
        wrapper.add(formPanel);

        panel.add(wrapper, BorderLayout.CENTER);
        add(panel);
        setVisible(true);
    }
}