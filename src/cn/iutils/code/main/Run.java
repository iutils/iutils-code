package cn.iutils.code.main;

import cn.iutils.code.config.Config;
import cn.iutils.code.entity.TableModel;
import cn.iutils.code.service.CodeService;
import cn.iutils.code.service.DbService;
import cn.iutils.code.utils.StringUtils;
import cn.iutils.code.utils.db.DBManger;
import cn.iutils.code.utils.db.DBMangerPool;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * iutils-code代码自动生成器
 */
public class Run {

    private JPanel RunMain;//主面板
    private JTextArea statusLog;//状态显示
    private JTextField username;//用户名
    private JTextField pwd;//密码
    private JTextField dataBaseUrl;//数据库连接串
    private JTextField myModel;//当前模块
    private JTextField mySubModel;//子模块
    private JTextField charFilter;//字符过滤
    private JTextField myPackage;//当前包名
    private JButton connect;//连接数据库
    private JTree dbTree;//数据库树
    private JTextField projectPath;//项目路径
    private JPopupMenu menu;//右键菜单
    private TreePath[] treePath=null;//节点路径

    //定义参数
    String dbUrl;//数据库地址
    String usernameParam;//数据库帐号
    String pwdParam;//数据库密码
    String charFilterParam;//过滤字符
    String projectPathParam;//项目路径
    String myPackageParam;//包名
    String myModelParam;//模块
    String mySubModelParam;//子模块
    DbService dbService = new DbService();
    CodeService codeService = new CodeService();

    public Run() {
        //初始组件
        initComponents();
        initBindComponents();
        dbTree.addMouseListener(new MouseAdapter() {
        });
        dbTree.addMouseListener(new MouseAdapter() {
        });
    }

    /**
     * 初始化组件
     */
    private void initComponents() {
        initValue();
        //正在刷新数据树
        updateTree();
        //右键菜单
        RightMouse();
    }

    /**
     * 初始化绑定组件
     */
    private void initBindComponents(){
        //连接按钮点击
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initValue();
                //正在刷新数据树
                updateTree();
            }
        });
        //树点击
        dbTree.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                //选择数据库
                if (evt.isMetaDown()) {
                    //右键 没有子节点
                    dbTree = (JTree) evt.getSource();
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) dbTree
                            .getLastSelectedPathComponent();
                    if (node != null && node.getChildCount() == 0) {
                        //最终子节点
                        treePath = dbTree.getSelectionPaths();
                        //创建右键菜单
                        menu.show(dbTree, evt.getX(), evt.getY());
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    /**
     * 初始化变量
     */
    public void initValue(){
        //获取数据库链接串
        dbUrl = dataBaseUrl.getText();
        //获取数据库帐号
        usernameParam = username.getText();
        //获取数据库密码
        pwdParam = pwd.getText();
        //获取过滤字符
        charFilterParam = charFilter.getText();
        Config.charfilters = charFilterParam.split(",");
        //获取包名
        myPackageParam = myPackage.getText();
        //获取模块
        myModelParam = myModel.getText();
        //获取子模块
        mySubModelParam = mySubModel.getText();
        //获取项目路径
        projectPathParam = projectPath.getText();

    }

    /**
     * 更新数据库树
     */
    public void updateTree(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("数据库列表");
        DefaultTreeModel treeMode = new DefaultTreeModel(root);
        DBManger dBManger = new DBManger(dbUrl, usernameParam, pwdParam);
        DBMangerPool dBMangerPool = DBMangerPool.getInstance();
        dBMangerPool.setDbMangerPools(Config.dbKey, dBManger);
        List<String> dbs = dbService.getDatabase(Config.dbKey);
        for (int j = 0; j < dbs.size(); j++) {
            DefaultMutableTreeNode leftnode1 = new DefaultMutableTreeNode(
                    dbs.get(j));
            root.add(leftnode1);
            List<TableModel> tables = dbService.getTables(Config.dbKey, dbs
                    .get(j));
            for (int k = 0; k < tables.size(); k++) {
                DefaultMutableTreeNode leftnode2 = new DefaultMutableTreeNode(
                        tables.get(k).getTableName()+"("+tables.get(k).getTableDesc()+")");
                leftnode1.add(leftnode2);
            }
        }
        dbTree.setModel(treeMode);
        statusLog.append("数据库列表刷新成功\r\n");
    }

    /**
     * 创建右键菜单
     */
    public void RightMouse() {
        JMenuItem mCode;
        menu = new JPopupMenu();
        mCode = new JMenuItem("生成代码");
        menu.add(mCode);
        //生成实体
        mCode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initValue();
                if(myModelParam==null || "".equals(myModelParam)){
                    statusLog.append("警告：没有填写要生成的模块\r\n");
                }else if(projectPathParam==null || "".equals(projectPathParam)){
                    statusLog.append("警告：没有填写项目路径\r\n");
                }else{
                    if(mySubModelParam==null || "".equals(mySubModelParam)){
                        mySubModelParam = null;
                    }
                    String msg = codeService.auotoCode(treePath,myPackageParam,myModelParam,mySubModelParam,projectPathParam);
                    if (msg != null) {
                        statusLog.append(msg+"\r\n");
                    }
                }
            }
        });
    }

    /**
     * 方法入口
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("iutils-code 代码生成器（Mysql版）");
        JPanel rootPanel = new Run().RunMain;
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(850, 700);//设置界面大小
        frame.setLocationRelativeTo(rootPanel);
        frame.setVisible(true);

        new Run();
    }

}