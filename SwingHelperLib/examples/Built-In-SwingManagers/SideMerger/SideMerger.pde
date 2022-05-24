import swinghelper.*;
import swinghelper.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@ColorScheme(background="white",border="blue",thickness=1)
@MethodButton(method="action")
JButton jbtns[]={new JButton("Reset"), new JButton("Randomize")};
JTextField props[]=new JTextField[11];
String outputs[]=new String[props.length];
int outputsInt[]=new int[props.length];
void setup() {
  size(200, 200);
  for (int i=0; i<props.length; i++) props[i]=new JTextField(10);
  JPanel description_added[]=new JPanel[props.length];

  //Joins components vertically
  description_added[0]=SwingUtils.list_col(new JLabel("x:"), props[0]);
  description_added[1]=SwingUtils.list_col(new JLabel("y:"), props[1]);
  description_added[2]=SwingUtils.list_col(new JLabel("r1:"), props[2]);
  description_added[3]=SwingUtils.list_col(new JLabel("r2:"), props[3]);
  description_added[4]=SwingUtils.list_col(new JLabel("r:"), props[4]);
  description_added[5]=SwingUtils.list_col(new JLabel("g:"), props[5]);
  description_added[6]=SwingUtils.list_col(new JLabel("b:"), props[6]);
  description_added[7]=SwingUtils.list_col(new JLabel("r:"), props[7]);
  description_added[8]=SwingUtils.list_col(new JLabel("g:"), props[8]);
  description_added[9]=SwingUtils.list_col(new JLabel("b:"), props[9]);
  description_added[10]=SwingUtils.list_col(new JLabel("w:"), props[10]);
  //Adds the method reset to the button
  ButtonManager.actionList(this);
  ButtonManager.colorScheme(this);
  //Joins components horizonaly
  JPanel prompt=SwingUtils.list_row(new JLabel("Setting:"), description_added[0], description_added[1], description_added[2], description_added[3], 
    SwingUtils.list_col(new JLabel("Color inside:")), description_added[4], description_added[5], description_added[6], 
    SwingUtils.list_col(new JLabel("Outside line:")), description_added[7], description_added[8], description_added[9], description_added[10], 
    SwingUtils.list_col(jbtns[0], jbtns[1]));

  //Fiddling about with the dimensions
  Dimension size_prompt=prompt.getPreferredSize();
  size_prompt.width=150;
  prompt.setPreferredSize(size_prompt);

  //Adds the processing-content to the Right
  SidePlacement con=SidePlacement.standart;
  con.add(SwingUtils.scrollable(prompt, 190, height), RIGHT);
  reset();
}
void draw() {
  background(255);
  updateOutputsInt();
  strokeWeight(outputsInt[10]);
  stroke(outputsInt[7], outputsInt[8], outputsInt[9]);
  fill(outputsInt[4], outputsInt[5], outputsInt[6]);
  ellipse(outputsInt[0], outputsInt[1], outputsInt[2], outputsInt[3]);
}
void action(int i) {
  if (i==0)reset();
  if (i==1)randomize();
}
void reset() {
  props[0].setText(""+(width/2));
  props[1].setText(""+(height/2));
  props[2].setText("30");
  props[3].setText("20");
  props[4].setText("255");
  props[5].setText("255");
  props[6].setText("255");
  props[7].setText("0");
  props[8].setText("0");
  props[9].setText("0");
  props[10].setText("1");
}
void randomize() {
  props[0].setText(""+(int)random(0, width));
  props[1].setText(""+(int)random(0, width));
  props[2].setText(""+(int)random(0, 50));
  props[3].setText(""+(int)random(0, 50));
  props[4].setText(""+(int)random(0, 255));
  props[5].setText(""+(int)random(0, 255));
  props[6].setText(""+(int)random(0, 255));
  props[7].setText(""+(int)random(0, 255));
  props[8].setText(""+(int)random(0, 255));
  props[9].setText(""+(int)random(0, 255));
  props[10].setText(""+(int)random(0, 10));
}
void updateOutputs() {
  for (int i=0; i<props.length; i++) outputs[i]=props[i].getText();
}
void updateOutputsInt() {
  updateOutputs();
  for (int i=0; i<props.length; i++) outputsInt[i]=int(outputs[i]);
}