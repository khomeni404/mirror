<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle!}" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../gen_template/resources_of_bootstrap.ftl">


    <#include "../common/address__create_form.ftl">

</@layout.gen_app_layout>


