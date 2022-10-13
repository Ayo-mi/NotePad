
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import notebook.MyCustomFilter;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on Mar 4, 2019, 7:58:54 PM
 */

/**
 *
 * @author AYO
 */
public class NewJFrame extends javax.swing.JFrame {

    /** Creates new form NewJFrame */
    private String fileName;
    private String pathName;
    private File saveFile;
    private File file=null;
    private File fileToSave;
    private File writeFile;
    private final String fileIcon ="/Users/AY/Pictures/accessories_text_editor.png";
    private final  FileWriter fileWriter=null;
    private final BufferedWriter bufWriter = null;
    private final String v="Untitled";
    private final String d="*";
    private JFrame window;
    private boolean saved=false;
    private boolean notSaved=false;
    private boolean opened=false;
    private final boolean newn=false;
    private File openedFile;
   
    public void close(){
        if(saved==true){
             System.exit(0);
        }else{
            int i = JOptionPane.showConfirmDialog(null, "Do You Want To Save Your Changes To This Document?", "NoteBook", JOptionPane.YES_NO_CANCEL_OPTION,  JOptionPane.WARNING_MESSAGE, null);
            switch (i) {
                case JOptionPane.YES_OPTION:
                    if( notSaved== true){
                        int r = fileSave.showSaveDialog(null);
                        if(r==JFileChooser.APPROVE_OPTION){
                            file=getSelectedFileWithExtension(fileSave);
                            try{
                                FileWriter wr = new FileWriter(file, false);
                                BufferedWriter w = new BufferedWriter(wr);
                                w.write(jTextArea1.getText());
                                w.flush();
                                w.close();
                                notSaved = false;
                                System.exit(0);
                            }catch (IOException ex){
                                JOptionPane.showMessageDialog(null, "Exception : "+ex.toString());
                            }
                        }
                    }else{
                        try{
                            FileWriter wr = new FileWriter(file, false);
                            BufferedWriter w = new BufferedWriter(wr);
                            w.write(jTextArea1.getText());
                            w.flush();
                            w.close();
                            saved=true;
                            System.exit(0);
                        }catch (IOException ex){
                            JOptionPane.showMessageDialog(null, "Exception : "+ex.toString());
                        }
                    }  break;
                case JOptionPane.CANCEL_OPTION:
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    break;
                case JOptionPane.NO_OPTION:
                    System.exit(0);
                default:
                    break;
            }

        }
       
    }

    public void ntxtArea(){
        if (notSaved=true){
                   int r = fileSave.showSaveDialog(this);
        if(r==JFileChooser.APPROVE_OPTION){
            file=getSelectedFileWithExtension(fileSave);
            try{
                if(file.exists()){
                int u= JOptionPane.showConfirmDialog(null, file+" Already Exist. \n Do You Want To Replace It?", "NoteBook", JOptionPane.YES_NO_OPTION,  JOptionPane.WARNING_MESSAGE, null);
              if (u==JOptionPane.YES_OPTION){
                 FileWriter wr = new FileWriter(file, false);
                BufferedWriter w = new BufferedWriter(wr);
                w.write(jTextArea1.getText());
                w.flush();
                w.close();
                  setTitle(file.getAbsolutePath()+" - NoteBook");
                  saved=true;
                  notSaved=false;
              }
                }else if(!file.exists()){
                    try{
                        FileWriter wr = new FileWriter(file, false);
                BufferedWriter w = new BufferedWriter(wr);
                w.write(jTextArea1.getText());
                w.flush();
                w.close();
                  setTitle(file.getAbsolutePath()+" - NoteBook");
                  saved=true;
                  notSaved=false;
                    }catch (IOException exc){
                        JOptionPane.showMessageDialog(this, exc.getMessage());
                    }

                }
            }catch (IOException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        }
    }

    public void titleBar(){
        this.setTitle(fileToSave+" - NoteBook");
    }
    public void createFile(){
        fileName = JOptionPane.showInputDialog(null, "Enter File Name To Save");
        try{
             saveFile = new File (pathName+"/"+fileName+".txt");
             if(saveFile.createNewFile()){
                 JOptionPane.showMessageDialog(null, "Saved Successfully");
             }else{
                 JOptionPane.showMessageDialog(null, "Failed To Save");
 }
       }catch(HeadlessException Ex) {
     JOptionPane.showMessageDialog(null, "Exception : "+Ex.toString());
 }      catch (IOException Ex) {
     JOptionPane.showMessageDialog(null, "Exception : "+Ex.toString());
        }
      
    }

    public void showJWindow(){
        JWindow jw = new JWindow();
        jw.setBounds(70,100,270,1700);
        

        jw.setVisible(true);
    }

    public void directoryFolder(){ 
  pathName= JOptionPane.showInputDialog("Enter Path Location To Save File");
  File file1 = new File (pathName);
   if(!file1.exists()){
       if(file1.mkdir()){
           JOptionPane.showMessageDialog(null, "Directory Created in "+pathName);
       }
 else{
           JOptionPane.showMessageDialog(null, "Problem Occured While Creating Directory");
 }
        }
       else{
           JOptionPane.showMessageDialog(null, "Directory Already Exist "+pathName);
       }
    }

    public void textAppend() throws FileNotFoundException, IOException{
        String text = jTextArea1.getText();
        try{
        FileOutputStream out = new FileOutputStream(saveFile, true);
        out.write(text.getBytes());
        out.close();
        JOptionPane.showMessageDialog(null, "File Saved..");
        }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "FileNotFoundException : "+ex.toString());
        }catch(IOException ioe){
            JOptionPane.showMessageDialog(null, "IOExceotion : "+ioe.toString());
        }catch (HeadlessException e){
            JOptionPane.showMessageDialog(null, "Exception : "+e.toString());
        }
    }

    public void newTextArea(){
       JTextArea jTextArea = new JTextArea();
        Component add = add(jTextArea);
        jTextArea1.setText("");
         setTitle(v+" - NoteBook");
         saved=true;
         notSaved=true;
          jTextArea1.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                  saved =false;
                 }
            @Override
            public void removeUpdate(DocumentEvent e) {
                  saved =false;
            }
             @Override
            public void changedUpdate(DocumentEvent e) {
                  saved =false;
            }

        });
        }
    public void openFiles(File file){
        try{
            openedFile = file;
             FileReader reader= new FileReader(file);
            jTextArea1.read(reader, null);
              opened=true;
              window.setTitle(file.getName()+" - NoteBook");
              }
          catch(IOException e){
          }
        }

    public void saveFile(File file){
         try{
              FileWriter fileWriter1= new FileWriter(file);
              BufferedWriter w = new BufferedWriter(fileWriter1);
              w.write(jTextArea1.getText());
              w.flush();
              fileWriter1.close();
              saved=true;
              window.setTitle(file.getName()+" - NoteBook");
              }
          catch(IOException e){
          }
    }

    public static File getSelectedFileWithExtension(JFileChooser c){
        File  fii =c.getSelectedFile();
        if(c.getFileFilter()instanceof FileNameExtensionFilter){
            String[] exts =((FileNameExtensionFilter)c.getFileFilter()).getExtensions();
            String nameLower=fii.getName().toLowerCase();
            for(String ext:exts){
                if(nameLower.endsWith(ext.toLowerCase())){
                    return fii;
                }
            }
            fii=new File(fii.toString()+exts[0]);
        }
        return fii;
    }

    public void quickSave(File file){
         try{
              FileWriter fileWriter1= new FileWriter(file);
              BufferedWriter w = new BufferedWriter(fileWriter1);
              w.write(jTextArea1.getText());
              w.flush();
              fileWriter1.close();
              window.setTitle(file.getName()+" - NootBook");
              }
          catch(IOException e){
          }
    }

    private void filfilter(){

        FileNameExtensionFilter filt = new FileNameExtensionFilter("Batch File (*.bat, *.cmd, *.nt)",".bat", "bat");
          fileChooser.setFileFilter(filt);
          filt = new FileNameExtensionFilter("C++ Source File (*.h, *.hh, *.hpp, *.hxx, *.cpp, *.cxx, *.cc, *.ino)", ".cpp", "cpp");
          fileChooser.setFileFilter(filt);
          filt = new FileNameExtensionFilter("C# Source File (*.cs)",".cs", "cs");
          fileChooser.setFileFilter(filt);
          filt = new FileNameExtensionFilter("FreeBasic File (*.bas, *.bi)",".bas", "bas");
          fileChooser.setFileFilter(filt);
          filt = new FileNameExtensionFilter("Hyper Text Markup Language (*.html, *.htm, *.shtml, *.shtm, *.xhtml, *.xht, *.hta)",".html", "html");
          fileChooser.setFileFilter(filt);
          filt = new FileNameExtensionFilter("Java Source File (*.java)",".java", "java");
          fileChooser.setFileFilter(filt);
          filt = new FileNameExtensionFilter("Java Script File (*.js, *.jsm, *.ts, *.tsx)",".js", "js");
          fileChooser.setFileFilter(filt);
          filt = new FileNameExtensionFilter("PHP Hypertext Preprocessor File (*.php, *.php3, *.php4, *.php5, *.phps, *.phpt, *.phtml)",".php", "php");
          fileChooser.setFileFilter(filt);
          filt = new FileNameExtensionFilter("Python File (*.py, *.pyw,)",".py", "py");
          fileChooser.setFileFilter(filt);
          filt = new FileNameExtensionFilter("Text Documents (*.txt)",".txt", "txt");
          fileChooser.setFileFilter(filt);

    }

    public NewJFrame() {
        initComponents();
        setTitle(v+" - NoteBook");
       
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("notepad-icon-27.png")));

        filfilter();

          FileNameExtensionFilter fil = new FileNameExtensionFilter("Batch File (*.bat, *.cmd, *.nt)",".bat", "bat");
          fileSave.setFileFilter(fil);
          fil = new FileNameExtensionFilter("C++ Source File (*.h, *.hh, *.hpp, *.hxx, *.cpp, *.cxx, *.cc, *.ino)", ".cpp", "cpp");
          fileSave.setFileFilter(fil);
          fil = new FileNameExtensionFilter("C# Source File (*.cs)",".cs", "cs");
          fileSave.setFileFilter(fil);
          fil = new FileNameExtensionFilter("FreeBasic File (*.bas, *.bi)",".bas", "bas");
          fileSave.setFileFilter(fil);
          fil = new FileNameExtensionFilter("Hyper Text Markup Language (*.html, *.htm, *.shtml, *.shtm, *.xhtml, *.xht, *.hta)",".html", "html");
          fileSave.setFileFilter(fil);
          fil = new FileNameExtensionFilter("Java Source File (*.java)",".java", "java");
          fileSave.setFileFilter(fil);
          fil = new FileNameExtensionFilter("Java Script File (*.js, *.jsm, *.ts, *.tsx)",".js", "js");
          fileSave.setFileFilter(fil);
          fil = new FileNameExtensionFilter("PHP Hypertext Preprocessor File (*.php, *.php3, *.php4, *.php5, *.phps, *.phpt, *.phtml)",".php", "php");
          fileSave.setFileFilter(fil);
          fil = new FileNameExtensionFilter("Python File (*.py, *.pyw,)",".py", "py");
          fileSave.setFileFilter(fil);
          fil = new FileNameExtensionFilter("Text Documents (*.txt)",".txt", "txt");
          fileSave.setFileFilter(fil);

        jTextArea1.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                  saved =false;
                 if(file.getName().endsWith(".java")){
                     while(jTextArea1.getText().contains("new")){
                         
                     }
                 }
                 }
            @Override
            public void removeUpdate(DocumentEvent e) {
                  saved =false;
            }
             @Override
            public void changedUpdate(DocumentEvent e) {
                  saved =false;
            }

        });
    

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowOpened(WindowEvent e){
                saved=true;
                notSaved=true;
            }
            @Override
           public void windowClosing(WindowEvent e){
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                if(saved==false){
                    int i = JOptionPane.showConfirmDialog(null, "Do You Want To Save Your Changes To This Document?", "NoteBook", JOptionPane.YES_NO_CANCEL_OPTION,  JOptionPane.WARNING_MESSAGE, null);
                    switch (i) {
                        case JOptionPane.YES_OPTION:
                            if( notSaved== true){
                                int r = fileSave.showSaveDialog(null);
                                if(r==JFileChooser.APPROVE_OPTION){
                                    file=getSelectedFileWithExtension(fileSave);
                                    try{
                                        FileWriter wr = new FileWriter(file, false);
                                        BufferedWriter w = new BufferedWriter(wr);
                                        w.write(jTextArea1.getText());
                                        w.flush();
                                        w.close();
                                        notSaved = false;
                                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    }catch (Exception ex){
                                        System.out.println("problem accessing File "+file.getAbsolutePath());
                                    }
                                }
                            }else{
                                try{
                                    FileWriter wr = new FileWriter(file, false);
                                    BufferedWriter w = new BufferedWriter(wr);
                                    w.write(jTextArea1.getText());
                                    w.flush();
                                    w.close();
                                    saved=true;
                                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                }catch (Exception ex){
                                    System.out.println("problem accessing File "+file.getAbsolutePath());
                                }
                            }          break;
                        case JOptionPane.CANCEL_OPTION:
                            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            break;
                        case JOptionPane.NO_OPTION:
                            System.exit(0);
                        default:
                            break;
                    }
                }else if(saved == true){
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        fileSave = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNew = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mCut = new javax.swing.JMenuItem();
        mCopy = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        mDel = new javax.swing.JMenuItem();
        timeDate = new javax.swing.JMenuItem();

        fileChooser.setDialogTitle("Open");

        fileSave.setDialogTitle("Save as");
        fileSave.setFileFilter(new MyCustomFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NoteBook");
        setBackground(new java.awt.Color(0, 204, 204));
        setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        setForeground(new java.awt.Color(0, 255, 204));

        jScrollPane1.setAutoscrolls(true);

        jTextArea1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jTextArea1.setCaretColor(new java.awt.Color(51, 255, 204));
        jTextArea1.setDoubleBuffered(true);
        jTextArea1.setDragEnabled(true);
        jTextArea1.setMargin(new java.awt.Insets(3, 5, 3, 3));
        jScrollPane1.setViewportView(jTextArea1);

        jMenu1.setBackground(new java.awt.Color(51, 204, 255));
        jMenu1.setText("File");
        jMenu1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jMenu1ComponentHidden(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        itemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        itemNew.setText("New");
        itemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewActionPerformed(evt);
            }
        });
        jMenu1.add(itemNew);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Open");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenuItem1.getAccessibleContext().setAccessibleParent(jMenu1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Save As");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu2MenuSelected(evt);
            }
        });

        mCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mCut.setText("Cut");
        mCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCutActionPerformed(evt);
            }
        });
        jMenu2.add(mCut);

        mCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mCopy.setText("Copy");
        mCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCopyActionPerformed(evt);
            }
        });
        jMenu2.add(mCopy);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Paste");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Select All");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        mDel.setText("Delete");
        mDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mDelActionPerformed(evt);
            }
        });
        jMenu2.add(mDel);

        timeDate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        timeDate.setText("Time/Date");
        timeDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeDateActionPerformed(evt);
            }
        });
        jMenu2.add(timeDate);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jMenu1ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ComponentHidden

    private void itemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewActionPerformed
      newTextArea();
    }//GEN-LAST:event_itemNewActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        close();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void timeDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeDateActionPerformed
          Date date = new Date();
          SimpleDateFormat sdf = new SimpleDateFormat ("hh:mm a MM/dd/yyyy");
          jTextArea1.replaceSelection(""+sdf.format(date));
    }//GEN-LAST:event_timeDateActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
              int open = fileSave.showSaveDialog(this);
      if(open ==JFileChooser.APPROVE_OPTION){
          file=getSelectedFileWithExtension(fileSave);
        if(file.exists()){
                 int u= JOptionPane.showConfirmDialog(fileSave, file.getName()+" Already Exist. \n Do You Want To Replace It?", "NoteBook", JOptionPane.YES_NO_OPTION,  JOptionPane.WARNING_MESSAGE, null);
                 if(u==JOptionPane.YES_OPTION){
                    try{
                      FileWriter fileWriter1= new FileWriter(file);
              BufferedWriter w = new BufferedWriter(fileWriter1);
              w.write(jTextArea1.getText());
              w.flush();
              fileWriter1.close();
              setTitle(file.getAbsolutePath()+" - NoteBook");
              saved=true;
              notSaved=false;
                 }catch (Exception e) {
                   System.out.println("Exception: "+e.toString());
                 }
                 }
          }else if(!file.exists()){
                    try{
                        FileWriter wr = new FileWriter(file, false);
                BufferedWriter w = new BufferedWriter(wr);
                w.write(jTextArea1.getText());
                w.flush();
                w.close();
                  setTitle(file.getAbsolutePath()+" - NoteBook");
                  saved=true;
                  notSaved=false;
                    }catch (Exception exc){
                        JOptionPane.showMessageDialog(this, exc.getMessage());
                    }

                }

      }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
     int returnVal = fileChooser.showOpenDialog(this);

        if(returnVal == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            if (!file.exists()){
             JOptionPane.showMessageDialog(this, file.getName()+"\n File not found. \n Check the file and try again.", "Open",JOptionPane.ERROR_MESSAGE );
           return;
            }else if(file.exists()){
           try{
               jTextArea1.read(new FileReader(file.getAbsolutePath() ), null);
              
            }catch (IOException ex){
                System.out.println("problem accessing File "+file.getAbsolutePath());
           }
        }else{
         System.out.println("file access canclled by user. ");
        }
    
     setTitle(file.getAbsolutePath()+" - NoteBook");
     saved=true;
     notSaved=false;
     jTextArea1.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
             saved=false;
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
           saved=false;
            }
             @Override
            public void changedUpdate(DocumentEvent e) {
           saved=false;
            }
        });
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
         jTextArea1.selectAll();
         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
           jTextArea1.paste();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
                ntxtArea();

        try{
                FileWriter wr = new FileWriter(file, false);
                BufferedWriter w = new BufferedWriter(wr);
                w.write(jTextArea1.getText());
                w.flush();
                w.close();
                saved=true;
            }catch (Exception e){
                int r = fileSave.showSaveDialog(this);
        if(r==JFileChooser.APPROVE_OPTION){
            file=getSelectedFileWithExtension(fileSave);
            try{
                if(file.exists()){
                int u= JOptionPane.showConfirmDialog(null, file+" Already Exist. \n Do You Want To Replace It?", "NoteBook", JOptionPane.YES_NO_OPTION,  JOptionPane.WARNING_MESSAGE, null);
              if (u==JOptionPane.YES_OPTION){
                 FileWriter wr = new FileWriter(file, false);
                BufferedWriter w = new BufferedWriter(wr);
                w.write(jTextArea1.getText());
                w.flush();
                w.close();
                  setTitle(file.getAbsolutePath()+" - NoteBook");
                  saved=true;
                  notSaved=false; 
              }
                }else if(!file.exists()){
                    try{
                        FileWriter wr = new FileWriter(file, false);
                BufferedWriter w = new BufferedWriter(wr);
                w.write(jTextArea1.getText());
                w.flush();
                w.close();
                  setTitle(file.getAbsolutePath()+" - NoteBook");
                  saved=true;
                  notSaved=false;
                    }catch (Exception exc){
                        JOptionPane.showMessageDialog(this, exc.getMessage());
                    }
          
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        }
      
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCutActionPerformed
            jTextArea1.cut();
    }//GEN-LAST:event_mCutActionPerformed

    private void mCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCopyActionPerformed
           jTextArea1.copy();
    }//GEN-LAST:event_mCopyActionPerformed

    private void mDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mDelActionPerformed
          jTextArea1.replaceSelection("");
    }//GEN-LAST:event_mDelActionPerformed

    private void jMenu2MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu2MenuSelected
            if(jTextArea1.getText().length()==0){
                jMenuItem10.setEnabled(false);
            }else{
                jMenuItem10.setEnabled(true);
            }if(jTextArea1.getSelectionStart()==jTextArea1.getSelectionEnd()){
                mCut.setEnabled(false);
                mCopy.setEnabled(false);
                mDel.setEnabled(false);
            }else{
                mCut.setEnabled(true);
                mCopy.setEnabled(true);
                mDel.setEnabled(true);
            }
    }//GEN-LAST:event_jMenu2MenuSelected

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JFileChooser fileSave;
    private javax.swing.JMenuItem itemNew;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane jTextArea1;
    private javax.swing.JMenuItem mCopy;
    private javax.swing.JMenuItem mCut;
    private javax.swing.JMenuItem mDel;
    private javax.swing.JMenuItem timeDate;
    // End of variables declaration//GEN-END:variables

}
