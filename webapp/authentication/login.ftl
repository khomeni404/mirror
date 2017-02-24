<!--
  Created by IntelliJ IDEA.
  User: hossaindoula
  Date: 2/22/12
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
-->

<#assign ctx = rc.getContextPath()/>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" media="screen" href="resources/css/login_new_style.css">
        <!--[if IE 9]>
            <link rel="stylesheet" href="resources/css/ie9_login.css">
        <![endif]-->

        <!--[if IE 8]>
            <link rel="stylesheet" href="resources/css/ie8_login.css">
        <![endif]-->

        <!--[if IE 7]>
            <link rel="stylesheet" href="resources/css/ie7_login.css">
        <![endif]-->

        <script src="resources/js/jquery.min.js" type="text/javascript"></script>
        <script src="resources/js/login_gen.js" type="text/javascript"></script>
        <script>
            jQuery(document).ready(function() {
                jQuery("#loginform").each (function(){
                    this.reset();
                });
                jQuery("#userCode").val("")
                jQuery("#password").val("")
                jQuery("#password").bind("click", function(){
                     $(this).val("")
                })
            });
        </script>
    </head>
    <body>
        <div class="body">
            <div class="loginlogo">
                <img src="resources/images/logo.png" alt="Logo" style="padding-left: 80px;">
            </div>
            <form name="loginform" action="auth/authenticateUser.erp" method="post" >

                <div class="loginbox">
                    <div class="loginbox_inner">
                        <div class="loginbox_content">
                            <input type="text" id="login" name="username" class="username" style="background-position: 0px 0px; " value="">
                            <input type="password" name="password" class="password" style="background-position: 0px 0px; " value="">
                            <button name="submit" class="submit">Login</button>
                        </div><!--loginbox_content-->
                    </div><!--loginbox_inner-->
                </div><!--loginbox-->

                <div class="loginoption" style="text-align: center">
                    <#--<a href="${host_address!}/ctx/" class="cant"></a>-->
                        Host Address : ${host_address!}
                </div><#--
                <div class="loginoption">
                    <a href="" class="cant">Can't access your account?</a>

                    <input type="checkbox" name="remember"> Remember me on this computer.
                </div>-->
            </form> <!-- It was </g:form>   -->
        </div>
    </body>
</html>