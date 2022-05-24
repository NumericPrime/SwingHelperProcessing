import swinghelper.*;
import swinghelper.util.*;

import javax.swing.*;
FreePlacement fp;
int sp=6;
color bg=color(255);
void setup() {
  fp=FreePlacement.standart;
  size(500, 500);
  SwingUtils.callMethodOnClick(c[0], "redOption", this);
  SwingUtils.callMethodOnClick(c[1], "greenOption", this);
  SwingUtils.callMethodOnClick(c[2], "blueOption", this);
  for (int i=0; i<c.length; i++)fp.add(c[i], x[i], y[i], 80, 20);
}
JButton c[]={new JButton("Red"), new JButton("Green"), new JButton("Blue")};
int x[]={-80, -80, -80};
int y[]={100, 150, 200};
int xtarg[]={100, 100, 100};
void draw() {
  background(bg);
  move();
}

void move() {
  for (int j=0; j<sp; j++)for (int i=0; i<c.length; i++) {
    if (x[i]!=xtarg[i]) {
      int m=x[i]<xtarg[i]?1:-1;
      x[i]+=m;
      fp.move(c[i], x[i], y[i], 80, 20);
    }
  }
}

void redOption() {
  if (x[0]==xtarg[0]) {
    bg=color(255, 0, 0);
    xtarg[0]=-80;
    xtarg[1]=-80;
    xtarg[2]=-80;
  }
}
void greenOption() {
  if (x[1]==xtarg[1]) {
    bg=color(0, 255, 0);
    xtarg[0]=-80;
    xtarg[1]=-80;
    xtarg[2]=-80;
  }
}
void blueOption() {
  if (x[2]==xtarg[2]) {
    bg=color(0, 0, 255);
    xtarg[0]=-80;
    xtarg[1]=-80;
    xtarg[2]=-80;
  }
}
void keyPressed() {
  if (key=='m') if (x[0]==-80) {
    xtarg[0]=100;
    xtarg[1]=100;
    xtarg[2]=100;
  } else {
    xtarg[0]=-80;
    xtarg[1]=-80;
    xtarg[2]=-80;
  }
}