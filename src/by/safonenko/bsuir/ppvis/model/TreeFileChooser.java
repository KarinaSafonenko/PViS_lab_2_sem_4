package by.safonenko.bsuir.ppvis.model;
import javax.swing.JFrame;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import javax.swing.event.TreeSelectionEvent;

/**
 * Created by Admin on 17.06.2017.
 */
public class TreeFileChooser {
    DefaultMutableTreeNode selectNode;
    DefaultTreeModel model;
    JTree tree;
    JLabel lab_hint = new JLabel("Мой компьютер");
    JButton openFile = new JButton();
    String path;
    int flag = 0;
    boolean isXmlSelected = false;
    boolean isHome = false;
    JPanel rootPanel;
    int mode;
    DefaultMutableTreeNode lastSelectedNode;
    String newName;
    JTextField fileName;


    public TreeFileChooser(int mode){
        this.mode = mode;
        if (mode == 1) openFile.setText("Открыть");
        else openFile.setText("Сохранить");
        JFrame jf = new JFrame();
        fileName = new JTextField(10);
        JLabel name = new JLabel("Название");
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(name);
        namePanel.add(fileName);
        JCheckBox onlyXml = new JCheckBox(".xml");
        JButton addFolder = new JButton("Создать папку");
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setPreferredSize(new Dimension(500,500));
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.setAutoscrolls(true);
        JScrollPane rootContentScroll = new JScrollPane(rootPanel);
        rootContentScroll.setPreferredSize(new Dimension(500,100));
        rootPanel.setVisible(true);

        DefaultMutableTreeNode super_root = new DefaultMutableTreeNode("My Computer", true);
        DefaultMutableTreeNode someNode;
        for (File f : File.listRoots()) {
            if (isAnyFolder(f)) {
                someNode = new DefaultMutableTreeNode(f, true);
            }
            else {
                someNode = new DefaultMutableTreeNode(f, false);
            }
            if(f.isDirectory())
                super_root.add(someNode);
        }

        model = new DefaultTreeModel(super_root, true);
        tree = new JTree(model);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                isHome = false;
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                lastSelectedNode = selectedNode;
                    rootPanel.removeAll();
                    addContennt(selectedNode);
                    rootPanel.repaint();
                    rootContentScroll.revalidate();
            }
        });

        tree.setCellRenderer(
                new TreeCellRenderer() {
                    JLabel jLabel = new JLabel("XXX");
                    public final ImageIcon ICON_DIR = new ImageIcon("D:\\q\\Test\\FileChooser\\src\\Resourses\\directory.png");
                    public final ImageIcon ICON_MYCOMP = new ImageIcon("D:\\q\\Test\\FileChooser\\src\\Resourses\\computer.png");

                    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
                        DefaultMutableTreeNode mtree = (DefaultMutableTreeNode) value;
                        if (mtree.getUserObject() instanceof String) {
                            jLabel.setText(mtree.getUserObject().toString());
                            if (jLabel.getText().equals("My Computer"))
                                jLabel.setIcon(ICON_MYCOMP);
                            return jLabel;
                        }
                        File f = (File) mtree.getUserObject();
                        if (mtree.getLevel() == 1)
                            try {
                                jLabel.setText(f.getCanonicalPath());
                            }catch (IOException e) {
                                e.printStackTrace();
                            }
                        else
                            jLabel.setText(f.getName());
                            jLabel.setIcon(ICON_DIR);
                            return jLabel;
                    }
                }
        );

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = 1;
                path = lab_hint.getText();
                jf.dispose();
            }
        });

        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                TreePath tp = event.getPath();
                selectNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
                rootPanel.removeAll();
                lastSelectedNode = selectNode;
                addContennt(selectNode);
                File selectFile = (File) selectNode.getUserObject();
                File[] fall = selectFile.listFiles();
                for (int i = 0; i < fall.length; i++) {
                    File file = fall[i];
                    DefaultMutableTreeNode currentNode;
                    if (isAnyFolder(file)) {
                        currentNode = new DefaultMutableTreeNode(file, true);
                    }
                    else {
                        currentNode = new DefaultMutableTreeNode(file, false);
                    }
                    if (file.isDirectory())
                        model.insertNodeInto(currentNode, selectNode, selectNode.getChildCount());
                }
                lab_hint.setText(selectFile.getAbsolutePath());
                    rootPanel.repaint();
                    rootContentScroll.revalidate();
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                TreePath tp = event.getPath();
                selectNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectNode.getParent();
                lastSelectedNode = parent;
                if (parent.getUserObject() instanceof String){}
                else addContennt(parent);
                DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
                selectNode.removeAllChildren();
                    rootPanel.removeAll();
                    treeModel.reload(selectNode);
                if (parent.getUserObject() instanceof String){
                    lab_hint.setText("My computer");
                }
                else {
                    File parentFile = (File) parent.getUserObject();
                    lab_hint.setText(parentFile.getAbsolutePath());
                }
                rootPanel.repaint();
                rootContentScroll.revalidate();
            }
        });

        onlyXml.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (onlyXml.isSelected()){
                    isXmlSelected =true;
                }
                else isXmlSelected = false;
                rootPanel.removeAll();
                if (lastSelectedNode != null)
                addContennt(lastSelectedNode);
                rootPanel.repaint();
                rootContentScroll.revalidate();
            }
        });

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                flag = 1;
            }
        });

        addFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        String folderName = fileName.getText();
                        File newFolder = new File(lab_hint.getText() + "\\" + folderName);
                        newFolder.mkdir();
                        DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode(newFolder, false);
                        File parent = (File) lastSelectedNode.getUserObject();
                        if (parent.isDirectory()) lastSelectedNode.setAllowsChildren(true);
                        if (lastSelectedNode.getChildCount() != 0) {
                            model.insertNodeInto(currentNode, lastSelectedNode, lastSelectedNode.getChildCount());
                            tree.scrollPathToVisible(new TreePath(currentNode.getPath()));
                        }
                        else
                        model.reload(lastSelectedNode);
            }
        });

        JToolBar toolBar = new JToolBar(SwingConstants.VERTICAL);
        toolBar.add(namePanel);
        toolBar.add(addFolder);
        toolBar.add(openFile);
        if (mode == 1) {
            toolBar.add(onlyXml);
        }

        toolBar.setFloatable(false);

        jf.add(new JScrollPane(tree));
        JScrollPane comp = new JScrollPane(lab_hint);

        jf.add(comp, BorderLayout.NORTH);
        if (mode == 1) {
            jf.getContentPane().add(rootContentScroll, BorderLayout.SOUTH);
        }
        jf.getContentPane().add(toolBar , BorderLayout.WEST);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    private void addContennt(DefaultMutableTreeNode selectedNode) {
        if (selectedNode.getUserObject() instanceof String) {}
        else{
            isHome = false;
            File selectFile = (File) selectedNode.getUserObject();
            lab_hint.setText(selectFile.getAbsolutePath());
            File[] children = selectFile.listFiles();
            if (selectedNode.getLevel() == 1) isHome = true;
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    if (children[i].isFile()) {
                        JLabel label;
                        if (isXmlSelected) {
                            if (children[i].getName().endsWith(".xml")) {
                                label = new JLabel(children[i].getName());
                                rootPanel.add(label);
                                label.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        if (isHome) {
                                            lab_hint.setText(lab_hint.getText() + label.getText());
                                        } else {
                                            lab_hint.setText(lab_hint.getText() + "\\" + label.getText());
                                        }
                                    }
                                });
                            }
                        } else {
                            label = new JLabel(children[i].getName());
                            rootPanel.add(label);
                            label.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (isHome) {
                                        lab_hint.setText(lab_hint.getText() + label.getText());
                                    } else {
                                        lab_hint.setText(lab_hint.getText() + "\\" + label.getText());
                                    }
                                    fileName.setText(label.getText());
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    public  void showOpenDialog(){
        while (isSelectedPath() !=1) try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isAnyFolder(File selectedFile){
        boolean isAnyFolder = false;
        File[] fileList = selectedFile.listFiles();
        if (fileList != null) {
            for (File currentFile : fileList) {
                if (currentFile.isDirectory()) {
                    isAnyFolder = true;
                    return isAnyFolder;
                }
            }
        }
        return  isAnyFolder;
   }

    public String getName() {
        newName = fileName.getText();
        return newName;
    }
    public String getPath(){
        return path;
    }

    public  int isSelectedPath() {
        return flag;
    }
}