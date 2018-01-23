<#macro gen_app_layout title="">
	<html>
        <head>
            <meta charset="utf-8">
            <title>${title}</title>
            <#include "stylesheets.ftl"/>

        </head>
		<body>
			<#-- Header content    -->
				<#include "header.ftl"/>
			<!-- Header content -->


			<!-- Header content -->
				<#include "menu.ftl"/>
			<!-- Header content -->

			<!--Body content-->
				<div id="content">
					<div class="inner"> <br/><br/>
						<!-- Top column -->
                        <#-- this is contain Company logo | Home | User | Setting
							<#include "top_column.ftl"/>
						-->
						<!-- Top column -->
						<div class="clear"></div>

						<!-- full width -->
                        <div id="mainContentNestedElement">
                            <#nested>
                        </div>

						<!-- End full width -->
					</div>

					<!-- clear fix -->
					<div class="clear"></div>
				</div>


			<!--Body content-->

			<!-- Footer content -->
				<#include "footer.ftl"/>
			<!-- Footer content -->

            <!-- Scripts -->
                <#include "scripts.ftl"/>
            <!-- Scripts -->


		</body>
	</html>
</#macro>