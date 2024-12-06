import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class runProgram extends JFrame{
    private JPanel backgroundPanel,
                   topPanel, 
                   bottomPanel, 
                   loginFormPanel, 
                   loginFormTopPadding, 
                   loginFormBottomPadding, 
                   loginFormLeftPadding, 
                   loginFormRightPadding,
                   mainContentPanel,
                   textBackgroundPanel;

    private JLabel title,
                   actionTextLabel;

    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton,
                    exitButton;

    public DecimalFormat decimalFormat = new DecimalFormat();
    final Dimension top_bottom_border_size = new Dimension(640, 66);
    final Dimension background_size = new Dimension(640, 349);
    final Dimension login_form_size = new Dimension(326, 242);
    final Dimension text_field = new Dimension(267, 28);
    final Dimension debit_credit_button_size = new Dimension(350, 42);
    final Dimension exit_button_size = new Dimension(100, 40);

    // ========================= LOGIN WINDOW =====================
    public runProgram(){
        decimalFormat.setMaximumFractionDigits(2);
        setTitle("Banking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        
        topPanel = new JPanel();
        topPanel.setPreferredSize(top_bottom_border_size);
        topPanel.setBackground(new Color(0x032F30));
        topPanel.setFocusable(true);

        backgroundPanel = new JPanel();
        backgroundPanel.setPreferredSize(background_size);
        backgroundPanel.setBackground(new Color(0x0C969C));
        backgroundPanel.setLayout(new BorderLayout());

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0x032F30));
        bottomPanel.setPreferredSize(top_bottom_border_size);


        // ____________________ EXIT BUTTON ____________________
        exitButton = new JButton("Exit");
        exitButton.setBounds(500, 250, exit_button_size.width, exit_button_size.height);
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(0x032F30));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // _________________^^^ EXIT BUTTON ^^^_________________


        // ____________________ ACTION TEXT AND BACKGROUND ____________________
        textBackgroundPanel = new JPanel();
        textBackgroundPanel.setBounds(0, 3, 309, 44);
        textBackgroundPanel.setPreferredSize(new Dimension(309, 44));
        textBackgroundPanel.setBackground(new Color(0x274D60));

        actionTextLabel = new JLabel();
        actionTextLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        actionTextLabel.setForeground(Color.WHITE);
        textBackgroundPanel.add(actionTextLabel);
        // _________________^^^ ACTION TEXT AND BACKGROUND ^^^_________________


        // ____________________ MAIN CONTENT PANEL ____________________
        // Magbutang bago panel para ma change ang layout sa mga shit
        mainContentPanel = new JPanel();
        mainContentPanel.setSize(background_size);
        mainContentPanel.setBackground(backgroundPanel.getBackground());
        mainContentPanel.setLayout(null);
        // _________________^^^ MAIN CONTENT PANEL ^^^_________________
        

        add(topPanel, BorderLayout.NORTH);
        add(backgroundPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
        runLogin();
    }

    // ========================= LOGIN WINDOW =========================  
    public void runLogin(){
        backgroundPanel.removeAll();

        JPanel loginPanel = new JPanel();
        loginPanel.setSize(background_size);
        loginPanel.setBackground(backgroundPanel.getBackground());
        loginPanel.setLayout(new BorderLayout());

        loginFormPanel = new JPanel();
        loginFormPanel.setPreferredSize(login_form_size);
        loginFormPanel.setBackground(Color.WHITE);
        loginFormPanel.setLayout(null);

        title = new JLabel("Banking System");
        title.setBounds(40, 10, 640, 50);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        loginFormPanel.add(title);

        usernameTextField = new JTextField("Username");
        usernameTextField.setBounds(20, title.getY() + 55, 267, 28);
        usernameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(usernameTextField.getText().equals("Username")){
                    usernameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(usernameTextField.getText().equals("Username") || usernameTextField.getText().isEmpty()){
                    usernameTextField.setText("Username");
                }
            }
        });
        loginFormPanel.add(usernameTextField);

        passwordField = new JPasswordField("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.setBounds(20, usernameTextField.getY() + 40, 267, 28);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(passwordField.getText().equals("Password")){
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(passwordField.getText().equals("Password") || passwordField.getText().isEmpty()){
                    passwordField.setText("Password");
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        loginFormPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0x0C969C));
        loginButton.setBounds(passwordField.getX(), passwordField.getY() + 41, passwordField.getWidth(), 30);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 10));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //=====debuger
                if(usernameTextField.getText().equals("debug")){
                    usernameTextField.setText("Username");
                    runDebug();
                }
                //=====debuger
                Login login = new Login();
                ArrayList<UserAccount> userAccountList = login.getAllUserAccounts();
                for (UserAccount useraccount : userAccountList) {
                    if(usernameTextField.getText().trim().equalsIgnoreCase(useraccount.getUsername()) && passwordField.getText().equals(useraccount.getPassword())){
                        System.out.println("login success");
                        runPreSelect(useraccount);
                        break;
                    }
                    else{
                        System.out.println("login failed");
                    }
                }          
            }
        });
        loginFormPanel.add(loginButton);

        loginFormTopPadding = new JPanel();
        loginFormTopPadding.setPreferredSize(new Dimension(213, 54));
        loginFormTopPadding.setBackground(backgroundPanel.getBackground());

        loginFormBottomPadding = new JPanel();
        loginFormBottomPadding.setPreferredSize(new Dimension(213, 54));
        loginFormBottomPadding.setBackground(backgroundPanel.getBackground());

        loginFormLeftPadding = new JPanel();
        loginFormLeftPadding.setPreferredSize(new Dimension(157, 349));
        loginFormLeftPadding.setBackground(backgroundPanel.getBackground());

        loginFormRightPadding = new JPanel();
        loginFormRightPadding.setPreferredSize(new Dimension(157, 349));
        loginFormRightPadding.setBackground(backgroundPanel.getBackground());

        loginPanel.add(loginFormPanel, BorderLayout.CENTER);
        loginPanel.add(loginFormTopPadding, BorderLayout.NORTH);
        loginPanel.add(loginFormBottomPadding, BorderLayout.SOUTH);
        loginPanel.add(loginFormLeftPadding, BorderLayout.WEST);
        loginPanel.add(loginFormRightPadding, BorderLayout.EAST);

        backgroundPanel.add(loginPanel);
        repaint();
        setVisible(true);
    }


    // ========================= PRESELECT WINDOW =========================  
    public void runPreSelect(UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        // change ang sulod sa setText() according action na buhaton, basta sunda ang sa figma nga design.
        // kani man tung rectangle nga naay text sa top left
        // ____________________ ACTION TEXT ____________________
        actionTextLabel.setText("Choose Transaction");
        // _________________^^^ ACTION TEXT ^^^_________________


        JButton debitButton = new JButton("Debit");
        debitButton.setBounds(130, 110, debit_credit_button_size.width, debit_credit_button_size.height);
        debitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        debitButton.setBackground(Color.WHITE);
        debitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runDebitSelection(useraccount);
            }
        });

        JButton creditButton = new JButton("Credit");
        creditButton.setBounds(130, debitButton.getY() + 60, debit_credit_button_size.width, debit_credit_button_size.height);
        creditButton.setFont(new Font("Arial", Font.PLAIN, 20));
        creditButton.setBackground(Color.WHITE);
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runCreditSelection(useraccount);
            }
        });


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        mainContentPanel.add(debitButton);
        mainContentPanel.add(creditButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    }


    // ========================= CREDIT SELECTION WINDOW =====================
    public void runCreditSelection(UserAccount useraccount){
        String accountType = "c";
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        actionTextLabel.setText("Credit");

        JLabel accountNumberLabel = new JLabel("Account number: " + useraccount.getDebitAccountNum());
        accountNumberLabel.setBounds(actionTextLabel.getX() + 22, actionTextLabel.getY() + 50, mainContentPanel.getWidth(), 30);
        accountNumberLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        accountNumberLabel.setForeground(Color.WHITE);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(accountNumberLabel.getX() + 2, accountNumberLabel.getY() + 35, debit_credit_button_size.width + 150, debit_credit_button_size.height);
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 20));
        withdrawButton.setBackground(Color.WHITE);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runWithdraw(useraccount, accountType);
            }
        });
        
        JButton paymentButton = new JButton("Payment");
        paymentButton.setBounds(withdrawButton.getX(), withdrawButton.getY() + 55, debit_credit_button_size.width + 150, debit_credit_button_size.height);
        paymentButton.setFont(new Font("Arial", Font.PLAIN, 20));
        paymentButton.setBackground(Color.WHITE);
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runPayment(useraccount);
            }
        });

        JButton BalancebButton = new JButton("Show Balance");
        BalancebButton.setBounds(paymentButton.getX(), paymentButton.getY() + 55, debit_credit_button_size.width + 150, debit_credit_button_size.height);
        BalancebButton.setFont(new Font("Arial", Font.PLAIN, 20));
        BalancebButton.setBackground(Color.WHITE);
        BalancebButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runBalance(useraccount, accountType);                
            }
        });
        mainContentPanel.add(withdrawButton);
        mainContentPanel.add(paymentButton);
        mainContentPanel.add(BalancebButton);
        mainContentPanel.add(exitButton);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(accountNumberLabel);   

        backgroundPanel.add(mainContentPanel);
        
        repaint();
        setVisible(true);
    } 


    // ========================= DEBIT SELECTION WINDOW =====================
    public void runDebitSelection(UserAccount useraccount){
        String accountType = "d";
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        // ____________________ ACTION TEXT ____________________
        actionTextLabel.setText("Debit");
        // _________________^^^ ACTION TEXT ^^^_________________

        JLabel accountNumberLabel = new JLabel("Account number: " + useraccount.getDebitAccountNum());
        accountNumberLabel.setBounds(actionTextLabel.getX() - 10, actionTextLabel.getY() + 60, mainContentPanel.getWidth(), 30);
        accountNumberLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        accountNumberLabel.setForeground(Color.WHITE);

        JPanel buttonGridPanel = new JPanel();
        buttonGridPanel.setOpaque(false);
        buttonGridPanel.setBounds(30, 110, 560, 107);
        buttonGridPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 20));
        withdrawButton.setForeground(Color.BLACK);
        withdrawButton.setBackground(Color.WHITE);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runWithdraw(useraccount, accountType);
            }
        });
        buttonGridPanel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.PLAIN, 20));
        depositButton.setForeground(Color.BLACK);
        depositButton.setBackground(Color.WHITE);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runDeposit(useraccount);
            }
        });
        buttonGridPanel.add(depositButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.setFont(new Font("Arial", Font.PLAIN, 20));
        transferButton.setForeground(Color.BLACK);
        transferButton.setBackground(Color.WHITE);
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runTransfer(useraccount);
            }
        });
        buttonGridPanel.add(transferButton);

        JButton showBalanceButton = new JButton("Show Balance");
        showBalanceButton.setFont(new Font("Arial", Font.PLAIN, 20));
        showBalanceButton.setForeground(Color.BLACK);
        showBalanceButton.setBackground(Color.WHITE);
        showBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runBalance(useraccount, accountType);
            }
        });
        buttonGridPanel.add(showBalanceButton);

        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        mainContentPanel.add(accountNumberLabel);
        mainContentPanel.add(buttonGridPanel);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= WITHDRAW WINDOW =====================
    public void runWithdraw (UserAccount useraccount, String accountType){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        actionTextLabel.setText("Withdraw");

        Balance balanceManager = new Balance();
        Withdraw withdraw;
        if(accountType.equals("c")){
            withdraw = new Withdraw(useraccount.getCreditAccountNum());
        }
        else{
            withdraw = new Withdraw(useraccount.getDebitAccountNum());
        }

        JLabel BalanceLabel = new JLabel();
        if(accountType.equals("c")){
            BalanceLabel.setText("Current Balance to Pay: " + decimalFormat.format(balanceManager.getCurrentBalance(useraccount.getCreditAccountNum())));
        }
        else{
            BalanceLabel.setText("Current Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(useraccount.getDebitAccountNum())));
        }
        BalanceLabel.setBounds(actionTextLabel.getX() - 25, actionTextLabel.getY() + 50, 300, 30);
        BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        BalanceLabel.setForeground(Color.WHITE);

        JLabel AmountLabel = new JLabel("Enter amount to withdraw");
        AmountLabel.setBounds(BalanceLabel.getX(), BalanceLabel.getY() + 30, 250, 30);
        AmountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        AmountLabel.setForeground(Color.WHITE);

        JTextField amounttTextField = new JTextField("Amount");
        amounttTextField.setBounds(AmountLabel.getX(),  AmountLabel.getY() + 35 , debit_credit_button_size.width + 90,48);
        amounttTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount")){
                    amounttTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount") || amounttTextField.getText().isEmpty()){
                    amounttTextField.setText("Amount");
                }
            }
        });
        
        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(amounttTextField.getX(), amounttTextField.getY() + 115, 400, 30);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setForeground(Color.WHITE);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(amounttTextField.getX() + 70, amounttTextField.getY() + 60, 300,42);
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 20));
        withdrawButton.setBackground(new Color(0x032F30));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double amount = Double.parseDouble(amounttTextField.getText());
                    double limit = useraccount.getCreditAccount().getLimit();

                    if(amount <= 0){
                        statusLabel.setText("Withdraw failed. Amount must be greater than 0.");
                    }
                    else if(accountType.equalsIgnoreCase("c")){
                        if(amount > limit || amount + balanceManager.getCurrentBalance(useraccount.getCreditAccountNum()) > limit){
                            statusLabel.setText("Withdraw failed. Limit exeeded.");
                            System.out.println("CURRENT TYPE: " + accountType);
                        }
                        else{
                            withdraw.amountWithdraw(amount);
                            statusLabel.setText("Withdraw successful.");
                            // amounttTextField.setText("");
                            runLogin();
                        }
                    }
                    else if(accountType.equalsIgnoreCase("d")){
                        if(amount > balanceManager.getCurrentBalance(useraccount.getDebitAccountNum())){
                            statusLabel.setText("Withdraw failed. Balance exeeded.");
                        }
                        else{
                            withdraw.amountWithdraw(amount);
                            statusLabel.setText("Withdraw successful.");
                            // amounttTextField.setText("");
                            runLogin();
                        }
                    }
                }
                catch(Exception err){
                    statusLabel.setText("Withdraw failed. Amount must be a number.");
                }
            }
        });


        mainContentPanel.add(statusLabel);
        mainContentPanel.add(AmountLabel);
        mainContentPanel.add(BalanceLabel);
        mainContentPanel.add(amounttTextField);
        mainContentPanel.add(withdrawButton);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= PAYMENT WINDOW =====================
    public void runPayment(UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        // ____________________ ACTION TEXT ____________________
        // Set setText()
        actionTextLabel.setText("Payment");
        // _________________^^^ ACTION TEXT ^^^_________________


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= DEPOSIT WINDOW =====================
    public void runDeposit(UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        actionTextLabel.setText("Deposit");

        Balance balanceManager = new Balance();
        Deposit deposit = new Deposit(useraccount.getDebitAccountNum());
        JLabel BalanceLabel = new JLabel();
        BalanceLabel.setText("Current Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(useraccount.getDebitAccountNum())));
        BalanceLabel.setBounds(actionTextLabel.getX() - 35, actionTextLabel.getY() + 60, 300, 30);
        BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        BalanceLabel.setForeground(Color.WHITE);

        JLabel AmountLabel = new JLabel("Enter amount to deposit");
        AmountLabel.setBounds(BalanceLabel.getX(), BalanceLabel.getY() + 25, 250, 30);
        AmountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        AmountLabel.setForeground(Color.WHITE);

        JTextField amounttTextField = new JTextField("Amount");
        amounttTextField.setBounds(AmountLabel.getX(),  AmountLabel.getY() + 40 , debit_credit_button_size.width + 90,48);
        amounttTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount")){
                    amounttTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount") || amounttTextField.getText().isEmpty()){
                    amounttTextField.setText("Amount");
                }
            }
        });

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(amounttTextField.getX(), amounttTextField.getY() + 115, 400, 30);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setForeground(Color.WHITE);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(amounttTextField.getX() + 70, amounttTextField.getY() + 65, 300,42);
        depositButton.setFont(new Font("Arial", Font.PLAIN, 20));
        depositButton.setBackground(new Color(0x032F30));
        depositButton.setForeground(Color.WHITE);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double amount = Double.parseDouble(amounttTextField.getText());
                    if(amount <= 0){
                        statusLabel.setText(    "Deposit failed. Amount must be greater than 0.");
                    }
                    else{
                        deposit.amountDeposit(amount);
                        runLogin();
                        // statusDialog
                    }
                }
                catch(Exception err){
                    statusLabel.setText("Deposit failed. Amount must be a number.");
                }
            }
        });

        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(statusLabel);
        mainContentPanel.add(AmountLabel);
        mainContentPanel.add(BalanceLabel);
        mainContentPanel.add(amounttTextField);
        mainContentPanel.add(depositButton);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        backgroundPanel.add(mainContentPanel);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= TRANSFER WINDOW =====================
    public void runTransfer(UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        actionTextLabel.setText("Transfer");
        Balance balanceManager = new Balance();
        Transfer transfer = new Transfer(useraccount.getDebitAccountNum());

        JLabel BalanceLabel = new JLabel("Current Balance: " + balanceManager.getCurrentBalance(useraccount.getCreditAccountNum()));
        BalanceLabel.setBounds(actionTextLabel.getX() - 25, actionTextLabel.getY() + 50, 300, 20);
        BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        BalanceLabel.setForeground(Color.WHITE);

        JLabel amountJLabel = new JLabel("Enter Amount");
        amountJLabel.setBounds(BalanceLabel.getX(),  BalanceLabel.getY() + 25, debit_credit_button_size.width + 90,20);
        amountJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        amountJLabel.setForeground(Color.WHITE);

        JTextField amounttTextField = new JTextField("Amount");
        amounttTextField.setBounds(BalanceLabel.getX(),  BalanceLabel.getY() + 50 , debit_credit_button_size.width + 90,30); 
        amounttTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount")){
                    amounttTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount") || amounttTextField.getText().isEmpty()){
                    amounttTextField.setText("Amount");
                }
            }
        });

        JLabel AccounJLabel = new JLabel("Account number");
        AccounJLabel.setBounds(BalanceLabel.getX(),  amounttTextField.getY() + 35, debit_credit_button_size.width + 90,20);
        AccounJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        AccounJLabel.setForeground(Color.WHITE);

        JTextField accountJTextField = new JTextField("Enter Account Number");
        accountJTextField.setBounds(BalanceLabel.getX(),  AccounJLabel.getY() + 25 , debit_credit_button_size.width + 90,30); 
        accountJTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(accountJTextField.getText().equals("Enter Account Number")){
                    accountJTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(accountJTextField.getText().equals("Enter Account Number") || accountJTextField.getText().isEmpty()){
                    accountJTextField.setText("Enter Account Number");
                }
            }

        });

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(accountJTextField.getX() + 70, accountJTextField.getY() + 40, 300,30);
        transferButton.setFont(new Font("Arial", Font.PLAIN, 20));
        transferButton.setBackground(new Color(0x032F30));
        transferButton.setForeground(Color.WHITE);
        transferButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("TRANSFERED");
            }
        });

        mainContentPanel.add(transferButton);
        mainContentPanel.add(accountJTextField);
        mainContentPanel.add(AccounJLabel);
        mainContentPanel.add(amountJLabel);
        mainContentPanel.add(amounttTextField);
        mainContentPanel.add(BalanceLabel);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        
        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= BALANCE WINDOW =====================
    public void runBalance (UserAccount useraccount, String accountType){ 
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();

        // TO DO: 
        //  add condition para mag check sa acconunt type 
        //  para mu display ug limit kung credit account 
        
        // ____________________ ACTION TEXT ____________________
        // Set setText()
        actionTextLabel.setText("Balance");
        // _________________^^^ ACTION TEXT ^^^_________________


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    }

    // ========================= DEBUG WINDOW =====================
    public void runDebug(){
        Account credAcc = new Account();credAcc.setAccountNum(1122334455);credAcc.setBalance(1000000000.99);credAcc.setLimit(9999999999999.00);credAcc.setType("c");
        Account debAcc = new Account();debAcc.setAccountNum(1122334455);debAcc.setBalance(1000000000.99);debAcc.setType("d");
        UserAccount debugAccount = new UserAccount(); debugAccount.setAccountID(9999999); debugAccount.setCreditAccount(credAcc); debugAccount.setCreditAccountNum(credAcc.getAccountNum()); debugAccount.setDebitAccount(debAcc); debugAccount.setDebitAccountNum(debAcc.getAccountNum());
        String accountType = "c";

        JFrame debugFrame = new JFrame("Debug Buttons");
        debugFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        debugFrame.setSize(login_form_size);
        debugFrame.setLayout(new GridLayout(3, 3, 2, 2));
        debugFrame.setResizable(false);
        debugFrame.setLocationRelativeTo(null);

        JButton loginWindowButton = new JButton("Login");
        loginWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runLogin();
            }
        });

        JButton preSelectWindowButton = new JButton("Pre Select");
        preSelectWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runPreSelect(debugAccount);
            }
        });

        JButton cSelectWindowButton = new JButton("C Select");
        cSelectWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runCreditSelection(debugAccount);
            }
        });

        JButton dSelectWindowButton = new JButton("D Select");
        dSelectWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runDebitSelection(debugAccount);
            }
        });

        JButton withdrawWindowButton = new JButton("Withdraw");
        withdrawWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runWithdraw(debugAccount, accountType);
            }
        });

        JButton paymentWindowButton = new JButton("Payment");
        paymentWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runPayment(debugAccount);
            }
        });

        JButton depositWindowButton = new JButton("Deposit");
        depositWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runDeposit(debugAccount);
            }
        });
        
        JButton transferWindowButton = new JButton("Transfer");
        transferWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runTransfer(debugAccount);
            }
        });

        JButton balanceWindowButton = new JButton("Balance");
        balanceWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runBalance(debugAccount, accountType);
            }
        });

        debugFrame.add(loginWindowButton);
        debugFrame.add(preSelectWindowButton);
        debugFrame.add(cSelectWindowButton);
        debugFrame.add(dSelectWindowButton);
        debugFrame.add(withdrawWindowButton);
        debugFrame.add(paymentWindowButton);
        debugFrame.add(depositWindowButton);
        debugFrame.add(transferWindowButton);
        debugFrame.add(balanceWindowButton);
        debugFrame.setVisible(true);
    }
}
