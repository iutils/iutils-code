<#if model??>
package ${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.entity;

import cn.iutils.sys.entity.DataEntity;

/**
* ${model.tableDesc}
* @author iutils.cn
* @version 1.0
*/
public class ${model.className} extends DataEntity<${model.className}>{

    private static final long serialVersionUID = 1L;

<#list model.columns as column>
    <#if column.columnName=="id">
    <#elseif column.columnName=="createDate">
    <#elseif column.columnName=="updateDate">
    <#elseif column.columnName=="createBy">
    <#elseif column.columnName=="updateBy">
    <#elseif column.columnName=="status">
    <#elseif column.columnName=="remarks">
    <#else>
    private String ${column.columnName};//${column.columnDesc}
    </#if>
</#list>

    public ${model.className}() {
        super();
    }
    public ${model.className}(String id){
        super(id);
    }

<#list model.columns as column>
    <#if column.columnName=="id">
    <#elseif column.columnName=="createDate">
    <#elseif column.columnName=="updateDate">
    <#elseif column.columnName=="createBy">
    <#elseif column.columnName=="updateBy">
    <#elseif column.columnName=="status">
    <#elseif column.columnName=="remarks">
    <#else>
    public String get${column.columnName?cap_first}(){
        return ${column.columnName};
    }

    public void set${column.columnName?cap_first}(String ${column.columnName}){
        this.${column.columnName} = ${column.columnName};
    }

    </#if>
</#list>
}
</#if>