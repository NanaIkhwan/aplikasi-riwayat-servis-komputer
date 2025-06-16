package app.ui;

import app.service.AuthService;
import app.service.CryptoUtil;
import app.service.Localization;
import app.service.RememberMeUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle(Localization.get("login.title"));
        setSize(420, 460);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color primaryBlue = new Color(33, 150, 243);
        Color lightBlue = new Color(227, 242, 253);
        Color secondaryBlue = new Color(100, 181, 246);
        Color white = Color.WHITE;

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(lightBlue);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        formPanel.setBackground(white);
        formPanel.setPreferredSize(new Dimension(320, 370));

        // ComboBox Pemilihan Bahasa
        String[] languages = {"Bahasa Indonesia", "English"};
        JComboBox<String> cmbLanguage = new JComboBox<>(languages);
        cmbLanguage.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        cmbLanguage.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbLanguage.setSelectedItem(Localization.getCurrentLocale().getLanguage().equals("en") ? "English" : "Bahasa Indonesia");

        // Listener ganti bahasa
        cmbLanguage.addActionListener(e -> {
            String selected = (String) cmbLanguage.getSelectedItem();
            Locale newLocale = selected.equals("English") ? new Locale("en") : new Locale("id");
            Localization.setLocale(newLocale);
            dispose();
            new LoginFrame();
        });

        JLabel lblTitle = new JLabel(Localization.get("login.title"));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(primaryBlue);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblUser = new JLabel(Localization.get("label.username"));
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setForeground(primaryBlue);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField txtUser = new JTextField(20);
        txtUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblPass = new JLabel(Localization.get("label.password"));
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPass.setForeground(primaryBlue);
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPasswordField txtPass = new JPasswordField(20);
        txtPass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        JCheckBox chkRememberMe = new JCheckBox(Localization.get("label.remember_me"));
        chkRememberMe.setForeground(primaryBlue);
        chkRememberMe.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnForgot = new JButton(Localization.get("btn.forgot"));
        btnForgot.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnForgot.setBorderPainted(false);
        btnForgot.setContentAreaFilled(false);
        btnForgot.setForeground(primaryBlue);
        btnForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnForgot.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnLogin = new JButton(Localization.get("btn.login"));
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(primaryBlue);
        btnLogin.setForeground(white);
        btnLogin.setFocusPainted(false);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnRegister = new JButton(Localization.get("btn.register"));
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setBackground(secondaryBlue);
        btnRegister.setForeground(white);
        btnRegister.setFocusPainted(false);
        btnRegister.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Load Remember Me jika ada
        String[] rememberData = RememberMeUtil.loadRememberMe();
        if (rememberData != null) {
            String savedUsername = rememberData[0];
            String savedHashedPassword = rememberData[1];

            txtUser.setText(savedUsername);
            chkRememberMe.setSelected(true);

            if (AuthService.login(savedUsername, savedHashedPassword, true)) {
                dispose();
                new MainFrame();
                return;
            } else {
                RememberMeUtil.clearRememberMe();
            }
        }

        // Action login
        btnLogin.addActionListener(e -> {
            String username = txtUser.getText().trim();
            String password = new String(txtPass.getPassword()).trim();

            if (AuthService.login(username, password)) {
                if (chkRememberMe.isSelected()) {
                    RememberMeUtil.saveRememberMe(username, CryptoUtil.hashPassword(password));
                } else {
                    RememberMeUtil.clearRememberMe();
                }
                JOptionPane.showMessageDialog(this, Localization.get("msg.login.success"));
                dispose();
                new MainFrame();
            } else {
                JOptionPane.showMessageDialog(this, Localization.get("msg.login.fail"));
            }
        });

        btnRegister.addActionListener(e -> {
            dispose();
            new RegisterFrame();
        });

        btnForgot.addActionListener(e -> {
            dispose();
            new ResetPasswordFrame();
        });

        // Tambahkan komponen ke formPanel
        formPanel.add(cmbLanguage);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(lblTitle);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        formPanel.add(lblUser);
        formPanel.add(txtUser);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(lblPass);
        formPanel.add(txtPass);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(chkRememberMe);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(btnForgot);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(btnLogin);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(btnRegister);

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBackground(lightBlue);
        wrapper.add(formPanel);

        panel.add(wrapper, BorderLayout.CENTER);
        add(panel);
        setVisible(true);
    }
}