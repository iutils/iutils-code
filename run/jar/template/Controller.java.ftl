<#if model??>
package ${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.iutils.common.Page;
import cn.iutils.common.utils.JStringUtils;
import cn.iutils.common.BaseController;
import ${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.entity.${model.className};
import ${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.service.${model.className}Service;

/**
* ${model.tableDesc} 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${"$"}{adminPath}/${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>")
public class ${model.className}Controller extends BaseController {

    @Autowired
    private ${model.className}Service ${model.className?uncap_first}Service;

    @ModelAttribute
    public ${model.className} get(@RequestParam(required = false) String id) {
        ${model.className} entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = ${model.className?uncap_first}Service.get(id);
        }
        if (entity == null) {
            entity = new ${model.className}();
        }
        return entity;
    }

    @RequiresPermissions("${model.model}<#if model.model!=model.className?uncap_first>:${model.className?uncap_first}</#if>:view")
    @RequestMapping()
    public String list(Model model, Page<${model.className}> page) {
        model.addAttribute("page", page.setList(${model.className?uncap_first}Service.findPage(page)));
        return "${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>/list";
    }

    @RequiresPermissions("${model.model}<#if model.model!=model.className?uncap_first>:${model.className?uncap_first}</#if>:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(${model.className} ${model.className?uncap_first},Model model) {
        model.addAttribute("${model.className?uncap_first}", ${model.className?uncap_first});
        return "${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>/form";
    }

    @RequiresPermissions("${model.model}<#if model.model!=model.className?uncap_first>:${model.className?uncap_first}</#if>:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(${model.className} ${model.className?uncap_first}, RedirectAttributes redirectAttributes) {
        ${model.className?uncap_first}Service.save(${model.className?uncap_first});
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>/update?id="+${model.className?uncap_first}.getId();
    }

    @RequiresPermissions("${model.model}<#if model.model!=model.className?uncap_first>:${model.className?uncap_first}</#if>:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(${model.className} ${model.className?uncap_first}, Model model) {
        model.addAttribute("${model.className?uncap_first}", ${model.className?uncap_first});
        return "${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>/form";
    }

    @RequiresPermissions("${model.model}<#if model.model!=model.className?uncap_first>:${model.className?uncap_first}</#if>:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(${model.className} ${model.className?uncap_first}, RedirectAttributes redirectAttributes) {
        ${model.className?uncap_first}Service.save(${model.className?uncap_first});
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>/update?id="+${model.className?uncap_first}.getId();
    }

    @RequiresPermissions("${model.model}<#if model.model!=model.className?uncap_first>:${model.className?uncap_first}</#if>:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        ${model.className?uncap_first}Service.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
</#if>