<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<#if model??>
<html>
<head>
    <title>${model.tableDesc}</title>
    <%@ include file="../<#if model.model!=model.className?uncap_first>../</#if>include/head.jsp"%>
    <style>
        .tpl-content-wrapper{margin-left:0}
    </style>
</head>
<body>
<script src="${"$"}{ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">${model.tableDesc}</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="${model.className?uncap_first}" action="${"$"}{ctx}/${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>/<c:choose><c:when test="${"$"}{empty ${model.className?uncap_first}.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
                            <input type="hidden" name="id" value="${"$"}{${model.className?uncap_first}.id}"/>
                            <#list model.columns as column>
                                <#if column.columnName=="id">
                                <#elseif column.columnName=="createDate">
                                <#elseif column.columnName=="updateDate">
                                <#elseif column.columnName=="createBy">
                                <#elseif column.columnName=="updateBy">
                                <#elseif column.columnName=="status">
                                <#else>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">${column.columnDesc}：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="${column.columnName?uncap_first}" placeholder="${column.columnDesc}" value="${"$"}{${model.className?uncap_first}.${column.columnName?uncap_first}}" required/>
                                        </div>
                                    </div>
                                </#if>
                            </#list>
                            <div class="am-form-group">
                                <div class="am-u-sm-9 am-u-sm-push-3">
                                    <button type="submit" class="am-btn am-btn-primary">保存</button>
                                    <button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">关闭</button>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../include/bottom.jsp"%>
<script type="text/javascript">
    ${"$"}(document).ready(function () {
        var msg = '${"$"}{msg}';
        if(msg!=''){
            showMsg(msg);
            closeModel(true);//关闭窗口
        }
    });
</script>
</body>
</html>
</#if>