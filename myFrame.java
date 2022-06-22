    import java.awt.even.ActionListener;
    import generateOTP;

    import javax.swing.JFrame;
    import havax.swing.JButton;

    public class MyFrame extends JFrame{
        JButton button;
        JLabel label;

        MyFrame(){
            label = new Label();
        // label.setText()
            button = new JButoon();
            button.setBounds(200, 100, 100, 50);
            button.addActionListener(this);
            button.setText("Generate OTP");
            button.setFocusable(false);

            this.setDefaultClostOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(500,500);
            this.setVisible(true);
            this.add(button);
        }

        @Override
        public void actionPerformed(ActionEven e){
            if(e.getSource() == button){
                generateOTP otp = new generateOTP();
            }
        }
    }
