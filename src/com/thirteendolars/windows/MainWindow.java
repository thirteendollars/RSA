package com.thirteendolars.windows;

import com.thirteendolars.RSA;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author damian
 */
public abstract class MainWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int EN_LANGUAGE = 100;
    private static final int PL_LANGUAGE = 101;
    
    private static int languageMode;
    private FileChooserDialog chooseDialog;

    private JButton generateKeyButton;
    private JButton inOpenFromFileButton;
    private JButton inSaveToFileButton;
    private JButton outSaveToFileButton;
    private JButton privKeyFromFileButton;
    private JButton savePrivKeyButton;
    private JButton savePubKeyButton;
    private JButton startEncryptionButton;
    private JButton startDecryptionButton;
    private JButton pubKeyFromFileButton;
    
    private JLabel swapIcon;
    private JLabel inputCleanIcon;
    private JLabel outputCleanIcon;

    private JLabel generateKeysLabel;
    private JLabel inputLabel;
    private JLabel keyLengthLabel;
    private JLabel outputLabel;
    private JLabel privKeyLabel;
    private JLabel pubKeyLabel;
    private JLabel statusLabel;

    private JMenuBar jMenuBar1;
    private JButton infoMenu;
    private JMenu languageChoose;
    private JCheckBoxMenuItem plLanguageMenuItem;
    private JCheckBoxMenuItem enLanguageMenuItem;
    
    private JProgressBar progressBar;

    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;

    private KeyLengthSpinner keyLengthSpinner;

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;

    private JTextArea publicKeyETextField;
    private JTextArea publicKeyNTextField;
    private JTextArea privateKeyTextField;
    
    private JScrollPane publicKeyETextScroll;
    private JScrollPane publicKeyNTextScroll;
    private JScrollPane privateKeyTextScroll;
         

    public MainWindow() {
        languageMode=EN_LANGUAGE;
        createMainViews();
        setProperText();
        setColors();
        setActionsListeners();
        chooseDialog = new FileChooserDialog(this);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(900, 600));
    }
    
    

    private void createMainViews() {

        inputLabel = new JLabel();
        inSaveToFileButton = new JButton();
        inOpenFromFileButton = new JButton();
        jScrollPane1 = new JScrollPane();
        inputTextArea = new JTextArea();
        jScrollPane2 = new JScrollPane();
        outputTextArea = new JTextArea();
        outputLabel = new JLabel();
        outSaveToFileButton = new JButton();
        progressBar = new JProgressBar();
        statusLabel = new JLabel();
        startEncryptionButton = new JButton();
        startDecryptionButton = new JButton();
        generateKeysLabel = new JLabel();
        keyLengthLabel = new JLabel();
        keyLengthSpinner = new KeyLengthSpinner();
        generateKeyButton = new JButton();
        pubKeyLabel = new JLabel();
        pubKeyFromFileButton = new JButton();
        savePubKeyButton = new JButton();
        privKeyLabel = new JLabel();
        savePrivKeyButton = new JButton();
        privKeyFromFileButton = new JButton();
        publicKeyETextField = new JTextArea();
        publicKeyNTextField = new JTextArea();
        privateKeyTextField = new JTextArea();
        privateKeyTextScroll = new JScrollPane(privateKeyTextField);
        privateKeyTextScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        privateKeyTextScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        publicKeyETextScroll = new JScrollPane(publicKeyETextField);
        publicKeyETextScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        publicKeyETextScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        publicKeyNTextScroll = new JScrollPane(publicKeyNTextField);
        publicKeyNTextScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        publicKeyNTextScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jMenuBar1 = new JMenuBar();
        infoMenu = new JButton();
        languageChoose = new JMenu();
        plLanguageMenuItem = new JCheckBoxMenuItem();
        enLanguageMenuItem = new JCheckBoxMenuItem();
        swapIcon = new JLabel(new ImageIcon(MainWindow.class.getResource("/swap_icon.png")));
        inputCleanIcon = new JLabel(new ImageIcon(MainWindow.class.getResource("/clean_icon.png")));
        outputCleanIcon = new JLabel(new ImageIcon(MainWindow.class.getResource("/clean_icon.png")));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        inputLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
              
        publicKeyETextField.setWrapStyleWord(true);
        publicKeyETextField.setLineWrap(true);
        publicKeyNTextField.setWrapStyleWord(true);
        publicKeyNTextField.setLineWrap(true);
        privateKeyTextField.setWrapStyleWord(true);
        privateKeyTextField.setLineWrap(true);
        
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(inputTextArea);

        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(outputTextArea);

        outputLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        generateKeysLabel.setFont(new java.awt.Font("Noto Sans", Font.BOLD, 14)); // NOI18N
        generateKeyButton.setForeground( new Color(0,120,0) );
        pubKeyLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        privKeyLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        
        infoMenu.setContentAreaFilled(false);
        infoMenu.setBorder(null);
        infoMenu.setAlignmentY(0.6f);
        
        enLanguageMenuItem.setText("EN");
        plLanguageMenuItem.setText("PL");
        languageChoose.add(enLanguageMenuItem);
        languageChoose.add(plLanguageMenuItem);
        plLanguageMenuItem.setState(false);
        enLanguageMenuItem.setState(true);
        jMenuBar1.add(languageChoose);
        jMenuBar1.add(infoMenu);
        setJMenuBar(jMenuBar1);

        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(statusLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(progressBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startEncryptionButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startDecryptionButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(generateKeysLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(keyLengthLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(pubKeyLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(privKeyLabel, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(privKeyFromFileButton, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                                .addComponent(keyLengthSpinner)
                                                .addComponent(pubKeyFromFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(savePrivKeyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(generateKeyButton, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                                .addComponent(savePubKeyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(publicKeyETextScroll,200,300,Short.MAX_VALUE)
                                .addComponent(publicKeyNTextScroll,200,300,Short.MAX_VALUE)
                                .addComponent(privateKeyTextScroll,200,300,Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1)
                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        
                                        .addGap(298, 298, 298))
                                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING,false)
                                            .addComponent(inputCleanIcon, 30, 30, 30)
                                            .addComponent(outputCleanIcon, 30, 30, 30))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING,false)
                                            .addComponent(inputLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(outputLabel, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(inOpenFromFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(swapIcon, 30, 30, 30))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(inSaveToFileButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(outSaveToFileButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(inSaveToFileButton)
                                .addComponent(inOpenFromFileButton)
                                .addComponent(inputCleanIcon, 30, 30, 30)
                                .addComponent(inputLabel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(startEncryptionButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(startDecryptionButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(outSaveToFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(swapIcon, 30, 30, 30)
                                .addComponent(outputCleanIcon, 30, 30, 30)
                                .addComponent(outputLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(generateKeysLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(keyLengthSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(generateKeyButton))
                                                .addComponent(keyLengthLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(pubKeyFromFileButton)
                                                .addComponent(savePubKeyButton)
                                                .addComponent(pubKeyLabel))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(publicKeyETextScroll, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(publicKeyNTextScroll, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(savePrivKeyButton)
                                                .addComponent(privKeyFromFileButton)
                                                .addComponent(privKeyLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(privateKeyTextScroll, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2))
                        .addGap(32, 32, 32))
        );

        pack();
    }

    private void setActionsListeners() {

        generateKeyButton.addActionListener(this);
        inOpenFromFileButton.addActionListener(this);
        inSaveToFileButton.addActionListener(this);
        outSaveToFileButton.addActionListener(this);
        privKeyFromFileButton.addActionListener(this);
        savePrivKeyButton.addActionListener(this);
        savePubKeyButton.addActionListener(this);
        startEncryptionButton.addActionListener(this);
        startDecryptionButton.addActionListener(this);
        pubKeyFromFileButton.addActionListener(this);
        infoMenu.addActionListener(this);
        plLanguageMenuItem.addActionListener(this);
        enLanguageMenuItem.addActionListener(this);
        swapIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // swap input text for output text 
                String inputText = inputTextArea.getText();
                String outputText = outputTextArea.getText();
                inputTextArea.setText( outputText );
                outputTextArea.setText( inputText );      
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        inputCleanIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // clear input text
                inputTextArea.setText("");     
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
                
      outputCleanIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // clear output text
                outputTextArea.setText("");      
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    protected void showErrorWindow(String mssg) {

        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(mssg, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error");
        dialog.setAlwaysOnTop(true);
        dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

    }

    protected void showInfoWindow(String mssg) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(mssg, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Information");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Input from file
        if (e.getSource() == inOpenFromFileButton) {
            File chosenFile = chooseDialog.showOpenDialog(FileChooserDialog.FOR_IN_OUT_TEXT);
            if (chosenFile != null) {
                inputTextArea.setText(openInputText(chosenFile.getPath()));
            }
        } // Input to file                                   
        else if (e.getSource() == inSaveToFileButton) {
            File chosenFile = chooseDialog.showSaveDialog(FileChooserDialog.FOR_INPUT_TEXT);
            if (chosenFile != null) {
                saveInputText(chosenFile.getPath(), inputTextArea.getText());
            }
        } // Output to file
        else if (e.getSource() == outSaveToFileButton) {
            File chosenFile = chooseDialog.showSaveDialog(FileChooserDialog.FOR_OUTPUT_TEXT);
            if (chosenFile != null) {
                saveOutputText(chosenFile.getPath(), outputTextArea.getText());
            }
        } // Public key to file
        else if (e.getSource() == savePubKeyButton) {
            File chosenFile = chooseDialog.showSaveDialog(FileChooserDialog.FOR_PUB_KEY);
            if (chosenFile != null) {
                savePublicKey(chosenFile.getPath(), publicKeyETextField.getText(), publicKeyNTextField.getText());
            }
        } // Private key to file
        else if (e.getSource() == savePrivKeyButton) {
            File chosenFile = chooseDialog.showSaveDialog(FileChooserDialog.FOR_PRIV_KEY);
            if (chosenFile != null) {
                savePrivateKey(chosenFile.getPath(), privateKeyTextField.getText());
            }
        } // Public key from file
        else if (e.getSource() == pubKeyFromFileButton) {
            File chosenFile = chooseDialog.showOpenDialog(FileChooserDialog.FOR_PUB_KEY);
            if (chosenFile != null) {
                String keyE = openPublicKeyE( chosenFile.getPath() );
                String keyN = openPublicKeyN( chosenFile.getPath() );
                if( !keyE.isEmpty() ){
                    publicKeyETextField.setText( keyE );
                }
                if( !keyN.isEmpty() ){
                    publicKeyNTextField.setText( keyN );
                }
            }
        } // Private key from file
        else if (e.getSource() == privKeyFromFileButton) {
            File chosenFile = chooseDialog.showOpenDialog(FileChooserDialog.FOR_PRIV_KEY);
            if (chosenFile != null) {
                String key= openPrivateKey(chosenFile.getPath());
                if( !key.isEmpty() ){
                    privateKeyTextField.setText( key );
                }
            }
        } // Generate keys
        else if (e.getSource() == generateKeyButton) {
            int keyLength = (int) keyLengthSpinner.getValue();
            generateKeys(keyLength);
        } // Start encryption
        else if (e.getSource() == startEncryptionButton) {
            RSA.MODE = RSA.ENCRYPTION;
            startAlgorithm();
        } // Start decryption
        else if (e.getSource() == startDecryptionButton) {
            RSA.MODE = RSA.DECRYPTION;
            startAlgorithm();
        } // Show info dialog
        else if (e.getSource() == infoMenu) {
            new InfoDialog(MainWindow.this).setVisible(true);
        }
        else if (e.getSource() == plLanguageMenuItem) {
            plLanguageMenuItem.setState(true);
            enLanguageMenuItem.setState(false);
            languageMode=PL_LANGUAGE;
            setProperText();
        }
        else if (e.getSource() == enLanguageMenuItem) {
            plLanguageMenuItem.setState(false);
            enLanguageMenuItem.setState(true);
            languageMode=EN_LANGUAGE;
            setProperText();
        }

    }

    public void setProgressBar(int progress) {
        statusLabel.setText(progress + " %");
        progressBar.setValue(progress);
    }

    protected int getKeyLength() {
        return (int) keyLengthSpinner.getValue();
    }

    protected void setPublicKeyE(String pubKeyE) {
        publicKeyETextField.setText(pubKeyE);
    }
    
    protected void setPublicKeyN(String pubKeyN) {
       publicKeyNTextField.setText(pubKeyN);
    }

    protected void setPrivateKeyD(String privKey) {
        privateKeyTextField.setText(privKey);
    }

    protected void getPublicKey(String pubKey) {
        publicKeyETextField.getText();
    }

    protected void getPrivateKey(String privKey) {
        privateKeyTextField.getText();
    }

    protected abstract void saveInputText(String directory, String inputText);

    protected abstract String openInputText(String directory);

    protected abstract void saveOutputText(String directory, String outputText);

    protected abstract void savePublicKey(String directory, String keyE, String keyN);

    protected abstract void savePrivateKey(String directory, String privateKey);

    protected abstract String openPublicKeyE(String directory);
    
    protected abstract String openPublicKeyN(String directory);

    protected abstract String openPrivateKey(String directory);

    protected abstract void generateKeys(int keyLength);

    protected abstract String startRSA(String keyEorD, String keyN, String inputText);
    
     public void setButtonsEnabled(boolean setStartEnabled){
        startEncryptionButton.setEnabled(setStartEnabled);
        startDecryptionButton.setEnabled(setStartEnabled);
        keyLengthSpinner.setEnabled(setStartEnabled);
        generateKeyButton.setEnabled(setStartEnabled);
        pubKeyFromFileButton.setEnabled(setStartEnabled);
        privKeyFromFileButton.setEnabled(setStartEnabled);
    }
    
    private void startAlgorithm() {
        
        String inputText = inputTextArea.getText();
        String keyN = publicKeyNTextField.getText();
        String keyEorD;
        switch (RSA.MODE) {
            case RSA.ENCRYPTION:
                keyEorD = publicKeyETextField.getText();
                break;
            case RSA.DECRYPTION:
                keyEorD = privateKeyTextField.getText();
                break;
            default:
                keyEorD = "";
                keyN = "";
        }
        String output = startRSA(keyEorD,keyN,inputText);
        outputTextArea.setText(output);
    }

    private void setColors() {
        startEncryptionButton.setForeground( new Color(190,0,0) );
        startDecryptionButton.setForeground( new Color(190,0,0) );
        inputTextArea.setBackground( new Color(240,240,240,255) );
        outputTextArea.setBackground( new Color(240,240,240,255) );
        publicKeyETextField.setBackground( new Color(240,240,240,255) );
        publicKeyNTextField.setBackground( new Color(240,240,240,255) );
        privateKeyTextField.setBackground( new Color(240,240,240,255) );
        getContentPane().setBackground( new Color(220,225,220,255) );
    }


    private void setProperText() {
        
        String [] enLanguage={"RSA Simulator","Input","Save...","Open...","Output","Save...","Status","START ENCRYPTION","START DECRYPTION",
            "Generate your own keys","Length [bits]","Generate","Public Key (e,n)","Open...","Save...","Private Key (d)","Save...","Open...",
            " Info","Language"};
        String [] plLanguage={"Symulator RSA","Wejście","Zapisz...","Otwórz...","Wyjście","Zapisz...","Status","SZYFRUJ","ROZSZYFRUJ",
            "Generuj klucze automatycznie","Długość [bity]","Generuj","Klucz publiczny (e,n)","Otwórz...","Zapisz...","Klucz prywatny (d)","Zapisz...","Otwórz...",
            " Info","Język"};
        
        
        String[] language;
        switch(languageMode){
            case PL_LANGUAGE: language=plLanguage; break;
            default: language=enLanguage;
        }
        
        this.setTitle( language[0] );
        inputLabel.setText( language[1] );
        inSaveToFileButton.setText( language[2] );
        inOpenFromFileButton.setText( language[3] );
        outputLabel.setText( language[4] );
        outSaveToFileButton.setText( language[5] );
        statusLabel.setText( language[6] );
        startEncryptionButton.setText( language[7] );
        startDecryptionButton.setText( language[8] );
        generateKeysLabel.setText( language[9] );
        keyLengthLabel.setText( language[10] );
        generateKeyButton.setText( language[11] );
        pubKeyLabel.setText( language[12] );
        pubKeyFromFileButton.setText( language[13] );
        savePubKeyButton.setText( language[14] );
        privKeyLabel.setText( language[15] );
        savePrivKeyButton.setText( language[16] );
        privKeyFromFileButton.setText( language[17] );
        infoMenu.setText( language[18] );
        languageChoose.setText( language[19] );
    }
    
    

}
