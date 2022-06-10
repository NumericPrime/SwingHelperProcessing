# SwingHelperProcessing
A library for using swing components with Processing.

Adding swing components to processing is hard this library makes this process easier by adding a few classes and annotations that allow to merge a processing content with swing components. The library adds a few classes to do that in various ways and allow including custom ways with just a few lines of code. The library also includes utility to format buttons by e.g. edit their color or strap methods onto them along with a few other QOL functions.

(under construction)
This is an example using just normal swing without my library:
<details><summary>Example</summary>

```processing
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

void setup() {
  setupFrame();
  //The background of a JComponent is the background only edits the background nothing else
  editor.setBackground(Color.BLACK);
  //The foreground of a JComponent is the color of the text
  editor.setForeground(Color.WHITE);
  
  setToDarkTheme(menubar);
  setToDarkTheme(general);
  setToDarkTheme(edit);
  
  //you could manually update the theme of the MenuItems however you can do it faster using this
  UIManager.put("MenuItem.acceleratorForeground", new Color(255,100,0));
  UIManager.put("MenuItem.background", Color.BLACK);
  UIManager.put("MenuItem.foreground", Color.WHITE);
  
  //You can also change the font and fontsize of the text:
  Font f=new Font("arial",Font.PLAIN,14);
  editor.setFont(f);
  menubar.setFont(f);
  general.setFont(f);
  edit.setFont(f);
  UIManager.put("MenuItem.acceleratorFont", f);
  UIManager.put("MenuItem.font",f);
  
  //You can also add borders to your components using the BorderFactory:
  editor.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
  
  //you can now update the TreeUI to implement these changes made by there UIManager
  SwingUtilities.updateComponentTreeUI(frame);
  
  //You can also have the sketch exit when the window is closed
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  frame.setVisible(true);
}

void setToDarkTheme(JComponent c) {
  c.setBackground(Color.BLACK);
  c.setForeground(Color.WHITE);
}

JFrame frame=new JFrame();
JEditorPane editor=new JEditorPane();

JMenuBar menubar=new JMenuBar();
JMenu general=new JMenu("General");
JMenu edit=new JMenu("Edit");

//Like JButtons JMenuItems (and JMenus) can also use ActionListeners
JMenuItem button=new JMenuItem("Duplicate");
JMenuItem button2=new JMenuItem("Delete Text");
JMenuItem button3=new JMenuItem("ALL CAPS");
JMenuItem button4=new JMenuItem("Exit");
void setupFrame() {  
  frame.setSize(500, 500);
  frame.setTitle("Example");
  frame.setResizable(true);
  ImageIcon iconMain=new ImageIcon(sketchPath("data/Icon.png"));
  frame.setIconImage(iconMain.getImage());
  frame.getContentPane().add(editor, "Center");
  ActionListener dupe=new ActionListener() {
    @Override
      public void actionPerformed(ActionEvent e) {
      dupeText();
    }
  };
  button.addActionListener(dupe);

  button2.addActionListener(
    new ActionListener() {
    @Override
      public void actionPerformed(ActionEvent e) {
      editor.setText("");
    }
  }
  );
  button3.addActionListener(
    new ActionListener() {
    @Override
      public void actionPerformed(ActionEvent e) {
      editor.setText(editor.getText().toUpperCase());
    }
  }
  );
  button4.addActionListener(
    new ActionListener() {
    @Override
      public void actionPerformed(ActionEvent e) {
      exit();
    }
  }
  );
  //add the submenus to the bar
  menubar.add(general);
  menubar.add(edit);
  //adds the options to the submenu
  general.add(button2);
  general.add(button4);
  edit.add(button);
  //You can also use a dividing line to separate different sections
  edit.addSeparator();
  edit.add(button3);

  //You can also add an accellerator that makes a button go off when a key is pressed
  button.setAccelerator(KeyStroke.getKeyStroke("control D"));
  button2.setAccelerator(KeyStroke.getKeyStroke("control N"));
  button3.setAccelerator(KeyStroke.getKeyStroke("control alt C"));
  button4.setAccelerator(KeyStroke.getKeyStroke("control alt E"));

  //adds the MenuBar to the frame
  frame.setJMenuBar(menubar);
}
void dupeText() {
  editor.setText(editor.getText()+"\n"+editor.getText());
}
```
</details>
and this would be the same example using my library:

<details><summary>Example</summary>
  
```processing
import swinghelper.*;
import swinghelper.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

void setup() {
  //processes the annotations
  ButtonManager.readSettings(this);
  setupFrame();
  
  @SuppressWarnings("unused")
    SwingManager sm=new SwingManager() {
      //allows a custon name and icon
    @RestrictedMerger(transfer_name=false, transfer_icon=false)
    //generates a resizable window with set size and title
      @GenerationSettings(width=500, height=500, name="Example",auto_add=false)
      public void merge()
    {
      ImageIcon iconMain=new ImageIcon(sketchPath("data/Icon.png"));
      generated.setIconImage(iconMain.getImage());
      generated.add(editor, "Center");
      generated.setJMenuBar(menubar);
    }
  };
}
//define custom styling for the editor
@ColorScheme(text="white", background="black", fontSize=14, border="255", thickness=3)
  JEditorPane editor=new JEditorPane();

  JMenuBar menubar=new JMenuBar();
  JMenu general=new JMenu("General");
  JMenu edit=new JMenu("Edit");

//These Annotations determin the method to be called on button press
@MethodButton(method="dupeText")
  JMenuItem button=new JMenuItem("Duplicate");

@MethodButton(method="deleteText")
  JMenuItem button2=new JMenuItem("Delete Text");

@MethodButton(method="allCaps")
  JMenuItem button3=new JMenuItem("ALL CAPS");

@MethodButton(method="exitProgram")
  JMenuItem button4=new JMenuItem("Exit");

//To save space the color scheme will be applied to an array
@ColorScheme(text="white", background="black", fontSize=14)
  JComponent colorschemapplied[]={button, button2, button3, button4,menubar,general,edit};
void setupFrame() {
  //add the submenus to the bar
  menubar.add(general);
  menubar.add(edit);
  //adds the options to the submenu
  general.add(button2);
  general.add(button4);
  edit.add(button);
  //You can also use a dividing line to separate different sections
  edit.addSeparator();
  edit.add(button3);

  //You can also add an accellerator that makes a button go off when a key is pressed
  button.setAccelerator(KeyStroke.getKeyStroke("control D"));
  button2.setAccelerator(KeyStroke.getKeyStroke("control N"));
  button3.setAccelerator(KeyStroke.getKeyStroke("control alt C"));
  button4.setAccelerator(KeyStroke.getKeyStroke("control alt E"));
}
void dupeText() {
  editor.setText(editor.getText()+"\n"+editor.getText());
}
void deleteText() {
  editor.setText("");
}
void allCaps() {
  editor.setText(editor.getText().toUpperCase());
}
void exitProgram() {
  exit();
}
```

</details>
