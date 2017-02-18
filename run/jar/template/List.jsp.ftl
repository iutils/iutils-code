<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<#if model??>
<html>
<head>
    <title>${model.tableDesc}</title>
    <%@ include file="../<#if model.model!=model.className?uncap_first>../</#if>include/head.jsp"%>
    <link href="${"$"}{ctxStatic}/custom/css/amazeui.select.css" type="text/css" rel="stylesheet" charset="UTF-8" />
    <style>
        .tpl-content-wrapper{margin-left:0}
    </style>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
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
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <button type="button" class="am-btn am-btn-default am-btn-success"
                                                onclick="openModel(false,'${"$"}{ctx}/${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>/create')"><span class="am-icon-plus"></span> 新增
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm" action="${"$"}{ctx}/${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>" method="post">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${"$"}{page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${"$"}{page.pageSize}"/>
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <div class="tagsinput">
                                            <c:if test="${"$"}{not empty page.key}"><span class="tags"><input type="hidden" name="key" value="${"$"}{page.key}" />关键字=${"$"}{page.key} <a href="javascript:;" onclick="${"$"}(this).parent().remove()">x</a></span></c:if>
                                                <span class="am-select am-input-group-sm">
                                                     <input type="text" class="am-select-input" autocomplete="off" style="border: none;"
                                                            placeholder="关键字" am-data='[{"field":"key","desc":"关键字","type":"string"}]'>
                                                    <ul class="am-select-ul"></ul>
                                                </span>
                                        </div>
                                        <span class="am-input-group-btn">
                                            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="submit" onclick="initSearchForm()"></button>
                                        </span>
                                    </div>
                                </form>
                            </div>

                            <div class="am-u-sm-12">
                                <table id="contentTable" class="am-table am-table-compact am-table-striped tpl-table-black">
                                    <thead>
                                    <tr>
                                        <#list model.columns as column>
                                            <th>${column.columnDesc}</th>
                                        </#list>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${"$"}{page.list}" var="${model.className?uncap_first}" varStatus="status">
                                        <tr>
                                            <#list model.columns as column>
                                                <td>${"$"}{${model.className?uncap_first}.${column.columnName?uncap_first}}</td>
                                            </#list>
                                            <td>
                                                <a href="javascript:;" onclick="openModel('修改${model.tableDesc}','${"$"}{ctx}/${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>/update?id=${"$"}{${model.className?uncap_first}.id}')" title="编辑"><span class="am-icon-pencil"></span></a>
                                                <a href="${"$"}{ctx}/${model.model}<#if model.model!=model.className?uncap_first>/${model.className?uncap_first}</#if>/${"$"}{${model.className?uncap_first}.id}/delete?pageNo=${"$"}{page.pageNo}&pageSize=${"$"}{page.pageSize}" onclick="return confirm('确认要删除该条数据吗？', this.href)" title="删除"><span class="am-text-danger am-icon-trash-o"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="am-u-lg-12 am-cf">
                                <%@ include file="../<#if model.model!=model.className?uncap_first>../</#if>utils/pagination.jsp"%>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../<#if model.model!=model.className?uncap_first>../</#if>include/bottom.jsp"%>
<script type="text/javascript" src="${"$"}{ctxStatic}/custom/js/amazeui.select.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${"$"}{msg}';
        if(msg!=''){
            showMsg(msg);
        }
    });
</script>
</body>
</html>
</#if>