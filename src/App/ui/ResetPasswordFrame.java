package app.ui;

import app.service.AuthService;
import app.service.CryptoUtil;
import app.service.Localization;

import javax.swing.*;
import java.awt.*;

public class ResetPasswordFrame extends JFrame {
    public ResetPasswordFrame() {
        setTitle(Localization.get("reset.title"));
        setSize(420, 430); // tambahkan tinggi agar tombol tidak terpotong
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color primaryBlue = new Color(33, 150, 243);
        Color secondaryBlue = new Color(100, 181, 246);
        Color lightBlue = new Color(227, 242, 253);
        Color white = Color.WHITE;

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(lightBlue);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        formPanel.setBackground(white);
        formPanel.setPreferredSize(new Dimension(320, 350)); // pastikan cukup tinggi

        // Judul
        JLabel lblTitle = new JLabel(Localization.get("reset.title"));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(primaryBlue);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Username
        JLabel lblUser = new JLabel(Localization.get("label.username"));
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setForeground(primaryBlue);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField txtUser = new JTextField(20);
        txtUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Password Baru
        JLabel lblNewPass = new JLabel(Localization.get("label.newpassword"));
        lblNewPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNewPass.setForeground(primaryBlue);
        lblNewPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPasswordField txtNewPass = new JPasswordField(20);
        txtNewPass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtNewPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNewPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Konfirmasi
        JLabel lblConfirm = new JLabel(Localization.get("label.confirmpassword"));
        lblConfirm.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblConfirm.setForeground(primaryBlue);
        lblConfirm.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPasswordField txtConfirm = new JPasswordField(20);
        txtConfirm.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtConfirm.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtConfirm.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Tombol Reset
        JButton btnReset = new JButton(Localization.get("btn.resetpassword"));
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReset.setBackground(primaryBlue);
        btnReset.setForeground(white);
        btnReset.setFocusPainted(false);
        btnReset.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Tombol Kembali
        JButton btnBack = new JButton(Localization.get("btn.back"));
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setBackground(secondaryBlue);
        btnBack.setForeground(white);
        btnBack.setFocusPainted(false);
        btnBack.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Event Handler
        btnReset.addActionListener(e -> {
            String username = txtUser.getText().trim();
            String newPass = new String(txtNewPass.getPassword()).trim();
            String confirmPass = new String(txtConfirm.getPassword()).trim();

            if (username.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field wajib diisi!", "Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "Password tidak cocok!", "Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (newPass.length() < 8) {
                JOptionPane.showMessageDialog(this, "Password minimal 8 karakter!", "Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (AuthService.resetPassword(username, newPass)) {
                JOptionPane.showMessageDialog(this, Localization.get("msg.reset.success"));
                dispose();
                new LoginFrame();
            } else {
                JOptionPane.showMessageDialog(this, Localization.get("msg.reset.fail"));
            }
        });

        btnBack.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        // Tambahkan ke Panel
        formPanel.add(lblTitle);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        formPanel.add(lblUser);
        formPanel.add(txtUser);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(lblNewPass);
        formPanel.add(txtNewPass);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(lblConfirm);
        formPanel.add(txtConfirm);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        formPanel.add(btnReset);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(btnBack);

        // Bungkus dan tampilkan
        mainPanel.add(formPanel);
        add(mainPanel);
        setVisible(true);
    }
}