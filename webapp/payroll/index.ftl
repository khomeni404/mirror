<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
	<div id="content">
	
	<#--	${allowanceType.allowanceName}<br />-->
		<#--${allowanceType.allowanceDescription}<br />-->
		${allowanceName.allowanceName}
		${allowanceName.allowanceDescription}<br />
		Name in List Object ${allowanceListObject.allowanceName}<br />
		Name in List Object ${allowanceListObject.allowanceDescription}<br />
		<br /><br />
		
		<#list allowanceList as x>
  			${x_index + 1}. ${x.allowanceName}</br>
		</#list>
		
	</div>
</@layout.gen_app_layout>