package cn.iutils.code.entity;

/**
 * 公共model
 *
 * @author hc
 */
public class JavaModel {

    /**
     * 包名
     */
    private String packageName;

    /**
     * 类名
     */
    private String className;

    /**
     * 模块
     */
    private String model;

    /**
     * 子模块
     */
    private String subModel;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSubModel() {
        return subModel;
    }

    public void setSubModel(String subModel) {
        this.subModel = subModel;
    }
}
