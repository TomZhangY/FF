package ff.main.log;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout; // Controls general layout on JFrame
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font; // A Font (for those at the back)
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel; // for your homework
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane; // A scrollable pane for lists etc.
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JPanel; // A panel for our radio buttons
import java.awt.GridBagLayout; // Layout for our radio buttons
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup; // groups radio buttons


public class GUI implements ActionListener{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private  JPanel line;
    private  JLabel label;
    private  JFrame bigframe;
    private  JPanel super_big_panel;
    //Buttons
    private  JButton open;
    private  JButton close;
    private  JButton send;
    private  JButton clear;
    //Text
    private  JTextField textFieldInterval;
    private JTextField textFieldIP;
    public String ip;
    private JTextField textFieldServerPort;
    private JTextField textFieldURL;
    private JTextField textFieldMethod;
    private JTextField textFieldNamespace;
    private JTextField textFieldtimeout;
    //List
    private JTextArea box1;
    private JTextArea box2;
    //private  JList<String> wordList;
    //CheckBox
    private  JCheckBox IsEncrypt;
    private JCheckBox AutoSend;
    private  JPanel radioButtonPanel;
    private  ButtonGroup radioButtons;
    //RadioButton
    private  JRadioButton radioButtonFor_Pos;
    private  JRadioButton radioButtonFor_Kiosk;
    private  JRadioButton radioButtonMessageCenter;
    private  JRadioButton radioButtonActiveMQ;
    private  JRadioButton radioButtonSVAWebService;
    private  JRadioButton radioButton2_Length_Tag;
    private  JRadioButton radioButton4_Length_Tag;
    private  JRadioButton radioButtonWrapper;
    private  JRadioButton radioButtonNo_Length;
    private  JRadioButton radioButtonChar;
    private  JRadioButton radioButtonHex_Char;

    public void actionPerformed(ActionEvent e){
    	
    	
    	String s = box2.getText();
    	Client.TTT(s);
    	
    	
    	
        if(radioButtonFor_Pos.isSelected()){
            System.out.print("pos is selected");
            ip = textFieldIP.getText();
        }
        if(radioButtonFor_Kiosk.isSelected()){
            System.out.print("For Kiosk is selected");
        }
        if(radioButtonMessageCenter.isSelected()){
            System.out.print("MessageCenter is selected");
        }
        if(radioButtonActiveMQ.isSelected()){
            System.out.print("ActiveMQ is selected");
        }
        if(radioButtonSVAWebService.isSelected()){
            System.out.print("SVAWebservice is selected");
        }


    }
    public String getip(){
        return ip;
    }

    /*public String TTT(){
       String ip = new String;


               con=
                       GUI'.set(ip)'

               cline.ttt(GUI);
    }
*/
    public GUI(){

        bigframe = new JFrame("d");

        bigframe.setLayout(new GridBagLayout());
        GridBagConstraints bigc = new GridBagConstraints();
        //bigc.gridheight = 1;
        //bigc.gridwidth = 10;
        bigc.gridy = GridBagConstraints.RELATIVE;
        bigc.gridx = 0;
        bigc.anchor=GridBagConstraints.WEST;
        bigframe.setSize(700,600);
        bigframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Sans-serif", Font.PLAIN, 12);

        //line one
        line = new JPanel(new GridLayout(1,6,0,0));
        line.setLayout(new GridBagLayout());
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = GridBagConstraints.RELATIVE;
        c3.gridy = 0;
        //c3.anchor = GridBagConstraints.WEST;
        label = new JLabel("Server Type: ");
        label.setFont(font);
        line.add(label,c3);

        radioButtons = new ButtonGroup();

        radioButtonFor_Pos = new JRadioButton("For Pos");
        radioButtonFor_Pos.setFont(font);
        radioButtonFor_Pos.setSelected(true);
        radioButtons.add(radioButtonFor_Pos);
        line.add(radioButtonFor_Pos,c3);

        radioButtonFor_Kiosk = new JRadioButton("For Kiosk");
        radioButtonFor_Kiosk.setFont(font);
        radioButtonFor_Kiosk.setSelected(true);
        radioButtons.add(radioButtonFor_Kiosk);
        line.add(radioButtonFor_Kiosk,c3);

        radioButtonMessageCenter = new JRadioButton("MessageCenter");
        radioButtonMessageCenter.setFont(font);
        radioButtonMessageCenter.setSelected(true);
        radioButtons.add(radioButtonMessageCenter);
        line.add(radioButtonMessageCenter,c3);

        radioButtonActiveMQ = new JRadioButton("ActiveMQ");
        radioButtonActiveMQ.setFont(font);
        radioButtonActiveMQ.setSelected(true);
        radioButtons.add(radioButtonActiveMQ);
        line.add(radioButtonActiveMQ,c3);

        radioButtonSVAWebService = new JRadioButton("SVAWebService");
        radioButtonSVAWebService.setFont(font);
        radioButtonSVAWebService.setSelected(true);
        radioButtons.add(radioButtonSVAWebService);
        line.add(radioButtonSVAWebService,c3);
        bigframe.add(line,bigc);
        //line2
        line = new JPanel(new GridLayout(1,4,0,0));
        line.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = GridBagConstraints.RELATIVE;
        c2.gridy = 0;
        //c2.anchor = GridBagConstraints.WEST;
        label= new JLabel("Service IP: ");
        label.setFont(font);
        line.add(label,c2);


        textFieldIP = new JTextField(15);
        textFieldIP.setFont(font);
        line.add(textFieldIP,c2);

        label = new JLabel("Server Port: ");
        label.setFont(font);
        line.add(label,c2);

        textFieldServerPort = new JTextField(5);
        textFieldServerPort.setFont(font);
        line.add(textFieldServerPort,c2);

        open = new JButton("open");
        open.addActionListener(this);
        open.setFont(font);
        line.add(open,c2);

        close = new JButton("close");
        close.addActionListener(this);
        close.setFont(font);
        line.add(close,c2);

        bigframe.add(line,bigc);

        //line 3
        line = new JPanel(new GridLayout(1,2,0,0));
        line.setLayout(new GridBagLayout());
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = GridBagConstraints.RELATIVE;
        c4.gridy = 0;
        c4.anchor = GridBagConstraints.WEST;
        label = new JLabel("Connection Status");
        label.setFont(font);
        line.add(label,c4);


        //Connection Status
        bigframe.add(line,bigc);

        //line 4
        line = new JPanel(new GridLayout(1,5,10,0));
        line.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        label = new JLabel("Append Length");
        label.setFont(font);
        line.add(label,c);

        radioButtons = new ButtonGroup();

        radioButton2_Length_Tag = new JRadioButton("2 Length");
        radioButton2_Length_Tag.setFont(font);
        radioButton2_Length_Tag.setSelected(true);
        radioButtons.add(radioButton2_Length_Tag);

        line.add(radioButton2_Length_Tag,c);

        radioButton4_Length_Tag = new JRadioButton("4 Length");
        radioButton4_Length_Tag.setFont(font);
        radioButton4_Length_Tag.setSelected(true);
        radioButtons.add(radioButton4_Length_Tag);

        line.add(radioButton4_Length_Tag,c);

        radioButtonWrapper = new JRadioButton("Wrapper");
        radioButtonWrapper.setFont(font);
        radioButtonWrapper.setSelected(true);
        radioButtons.add(radioButtonWrapper);

        line.add(radioButtonWrapper,c);

        radioButtonNo_Length = new JRadioButton("No Length");
        radioButtonNo_Length.setFont(font);
        radioButtonNo_Length.setSelected(true);
        radioButtons.add(radioButtonNo_Length);
        line.add(radioButtonNo_Length,c);

        bigframe.add(line,bigc);
        //line 5
        line = new JPanel(new GridLayout(1,3,0,0));
        line.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = GridBagConstraints.RELATIVE;
        c1.gridy = 0;
        c1.anchor = GridBagConstraints.WEST;
        label = new JLabel("Send Data Type: ");
        label.setFont(font);
        line.add(label,c1);

		/*radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new GridBagLayout());*/
		/*GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;*/
        radioButtons = new ButtonGroup();

        radioButtonChar = new JRadioButton("Char");
        radioButtonChar.setFont(font);
        radioButtonChar.setSelected(true);
        radioButtons.add(radioButtonChar);
        line.add(radioButtonChar,c1);

        radioButtonHex_Char= new JRadioButton("Hex_Char");
        radioButtonHex_Char.setFont(font);
        radioButtonHex_Char.setSelected(true);
        radioButtons.add(radioButtonHex_Char);
        line.add(radioButtonHex_Char,c1);

        bigframe.add(line,bigc);

        //line 6
        line = new JPanel(new GridLayout(1,4,0,0));
        line.setLayout(new GridBagLayout());
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = GridBagConstraints.RELATIVE;
        c5.gridy = 0;
        c5.anchor = GridBagConstraints.WEST;
        label = new JLabel("Webservice URL");
        label.setFont(font);
        line.add(label,c5);

        textFieldURL = new JTextField(20);
        textFieldURL.setFont(font);
        line.add(textFieldURL,c5);

        label =new JLabel("Method");
        label.setFont(font);
        line.add(label,c5);

        textFieldMethod = new JTextField(5);
        textFieldMethod.setFont(font);
        line.add(textFieldMethod,c5);

        bigframe.add(line,bigc);

        //line 7
        line = new JPanel(new GridLayout(1,5,0,0));
        line.setLayout(new GridBagLayout());
        GridBagConstraints c6 = new GridBagConstraints();
        c6.gridx = GridBagConstraints.RELATIVE;
        c6.gridy = 0;
        c6.anchor = GridBagConstraints.WEST;
        label = new JLabel("NameSpace");
        label.setFont(font);
        line.add(label,c6);

        textFieldNamespace = new JTextField(10);
        textFieldNamespace.setFont(font);
        line.add(textFieldNamespace,c6);

        IsEncrypt = new JCheckBox("IsEncrypt");
        IsEncrypt.setFont(font);
        line.add(IsEncrypt,c6);

        label = new JLabel("       ");
        line.add(label,c6);

        label = new JLabel("Timeout: ");
        label.setFont(font);
        line.add(label,c6);

        textFieldtimeout = new JTextField(5);
        textFieldtimeout.setFont(font);
        line.add(textFieldtimeout,c6);

        bigframe.add(line,bigc);
        //line 8

        GridBagConstraints c8 = new GridBagConstraints();
        c8.gridx = GridBagConstraints.RELATIVE;
        c8.gridy = 0;
        c8.anchor = GridBagConstraints.WEST;
        line = new JPanel(new GridBagLayout());
        line.setLayout(new GridBagLayout());
        label = new JLabel("Send Data: ");
        label.setFont(font);
        line.add(label,c8);

        AutoSend = new JCheckBox("Auto Send");
        AutoSend.setFont(font);
        line.add(AutoSend,c8);

        label = new JLabel("      ");
        line.add(label,c8);
        label = new JLabel("Interval");
        label.setFont(font);
        line.add(label,c8);

        textFieldInterval = new JTextField(5);
        line.add(textFieldInterval,c8);
        label = new JLabel("ms");
        label.setFont(font);
        line.add(label,c8);

        label = new JLabel("    ");
        line.add(label,c8);

        send = new JButton("Send");
        send.setFont(font);

        line.add(send,c8);
        label = new JLabel("    ");
        line.add(label,c8);

        clear = new JButton("Clear");
        clear.setFont(font);
        line.add(clear,c8);

        bigframe.add(line,bigc);



        //textField = new JTextField(10);
        //textField.setFont(font);
        //add(textField);

        //add(open);
        //checkBoxReverseString = new JCheckBox("Reverse string");
        //add(checkBoxReverseString);

        //line 9
        GridBagConstraints c9 = new GridBagConstraints();
        c9.gridx = GridBagConstraints.RELATIVE;
        c9.gridy = 0;
        c9.anchor = GridBagConstraints.WEST;
        line = new JPanel(new GridBagLayout());
        line.setLayout(new GridBagLayout());
        box1 = new JTextArea(10,45);

        line.add(box1,c9);
        bigframe.add(line,bigc);
        //line 10
        GridBagConstraints c10 = new GridBagConstraints();
        c10.gridx = GridBagConstraints.RELATIVE;
        c10.gridy = 0;
        c10.anchor = GridBagConstraints.WEST;
        line = new JPanel(new GridBagLayout());
        line.setLayout(new GridBagLayout());

        line.add(label,c10);label = new JLabel("Received Data");
        bigframe.add(line,bigc);
        //line 11
        GridBagConstraints c11 = new GridBagConstraints();
        c11.gridx = GridBagConstraints.RELATIVE;
        c11.gridy = 0;
        c11.anchor = GridBagConstraints.WEST;
        line = new JPanel(new GridBagLayout());
        line.setLayout(new GridBagLayout());
        box2 = new JTextArea(10,45);
        line.add(box2,c11);
        bigframe.add(line,bigc);
        bigframe.setVisible(true);

    }







}
