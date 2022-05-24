import swinghelper.*;
import swinghelper.util.*;


color bg=color(255);
color fl=color(0);

void setup() {
  size(300, 300);
  //autoMerge must be disabled for custom SwingManagers to work
  Settings.autoMerge(false);
  //create instance of the SwingManager
  CustomSwingManager s=new CustomSwingManager();
}
void draw() {
  background(bg);
  fill(fl);
  ellipse(noise((float)frameCount/60)*((float)width),noise(5+(float)frameCount/60)*((float)height),30,30);
}