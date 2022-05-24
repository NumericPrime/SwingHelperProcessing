import javax.swing.*;
@RestrictedMerger(transfer_name=false)
@GenerationSettings(name="Example Custom Manager",resizable=false)
class CustomSwingManager extends SwingManager {
  CustomSwingManager() {
    super();
  }
  @ColorScheme(background="230")
  JMenuItem itemBg[]=new JMenuItem[5];
  
  @ColorScheme(background="230")
  JMenuItem itemFl[]=new JMenuItem[5];
  
  @Override 
  void merge() {
    itemBg=new JMenuItem[5];
    itemFl=new JMenuItem[5];
    JMenuBar menu=new JMenuBar();
    generated.setJMenuBar(menu);
    JMenu m1=new JMenu("Background");
    JMenu m2=new JMenu("Fill");
    menu.add(m1);
    menu.add(m2);
    String names[]={"Red", "Green", "Blue", "White", "Black"};
    for (int i=0;i<names.length;i++) {
      String n=names[i];
      JMenuItem item=new JMenuItem(n);
      this.itemBg[i]=item;
      SwingUtils.callMethodOnClick(item, n+"MethodBackgr", this);
      m1.add(item);
    }
    for (int i=0;i<names.length;i++) {
      String n=names[i];
      JMenuItem item=new JMenuItem(n);
      itemFl[i]=item;
      SwingUtils.callMethodOnClick(item, n+"MethodFill", this);
      m2.add(item);
    }
  }
  void RedMethodBackgr() {
    bg=color(255, 0, 0);
  }
  void GreenMethodBackgr() {
    bg=color(0, 255, 0);
  }
  void BlueMethodBackgr() {
    bg=color(0, 0, 255);
  }
  void WhiteMethodBackgr() {
    bg=color(255);
  }
  void BlackMethodBackgr() {
    bg=color(0);
  }
  void RedMethodFill() {
    fl=color(255, 0, 0);
  }
  void GreenMethodFill() {
    fl=color(0, 255, 0);
  }
  void BlueMethodFill() {
    fl=color(0, 0, 255);
  }
  void WhiteMethodFill() {
    fl=color(255);
  }
  void BlackMethodFill() {
    fl=color(0);
  }
}