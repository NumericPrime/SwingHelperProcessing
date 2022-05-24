import swinghelper.*;
import swinghelper.util.*;

import javax.swing.*;
import java.awt.*;

@ColorScheme(background="white", text="50,100,20", fontSize=50)
  @MethodButton(method="press")
  JButton jb=new JButton("Big Button");
color c=color(255, 0, 0);
int c_count=0;
void setup() {
  size(500, 500);
  ButtonManager.readSettings(this);
  jb.setPreferredSize(new Dimension(500, height));

  @SuppressWarnings("unused")

    SwingManager sm=new SwingManager() {
      //Allow custom Name
      @RestrictedMerger(transfer_name=false)
      //Pregenerate Window
      @GenerationSettings(name="Example Custom Manager", resizable=false, widthOffset=500)
      //define merge()
      @Override
      public void merge() {
      generated.getContentPane().add(jb, "East");
      //Optional:
      //generated.getContentPane().add(contentOrg, "Center");
    }
  };
}
void draw() {
  background(c);
}
void press() {
  switch(c_count) {
  case 0:
    c=color(255, 0, 0);
    break;

  case 1:
    c=color(0, 255, 0);
    break;

  case 2:
    c=color(0, 0, 255);
    break;
  }
  c_count++;
  c_count=c_count%3;
}