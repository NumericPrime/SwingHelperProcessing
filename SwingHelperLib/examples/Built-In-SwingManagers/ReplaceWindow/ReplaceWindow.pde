import swinghelper.*;
import swinghelper.util.*;

import javax.swing.*;

@ColorScheme(background="white",text="50,100,20",fontSize=50)
@MethodButton(method="press")
JButton jb=new JButton("Big Button");
void setup() {
  ButtonManager.readSettings(this);
  JFrame jf=new JFrame();
  jf.setSize(500, 500);
  jf.setTitle("Test");
  jf.setResizable(false);
  jf.getContentPane().add(jb);
  ReplaceManager rm=new ReplaceManager(jf);
}

void press(){
println("The Button has been pressed!");
}