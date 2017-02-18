<#if model??>
package ${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import ${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.entity.${model.className};

/**
* ${model.tableDesc} DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface ${model.className}Dao extends ICrudDao<${model.className}> {

}
</#if>