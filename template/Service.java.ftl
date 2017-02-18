<#if model??>
package ${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import ${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.dao.${model.className}Dao;
import ${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.entity.${model.className};

/**
* ${model.tableDesc} Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class ${model.className}Service extends CrudService<${model.className}Dao, ${model.className}> {

}
</#if>