<#macro mis_layout title="">
    <#assign ctx = rc.getContextPath()/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>QRF</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link rel="icon" type="image/ico" href="favicon.ico"/>

    <link href="../resources/taurus/HTML/css/stylesheets.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/css/random.css" rel="stylesheet" type="text/css"/>

    <#include "script.ftl">

</head>
<body class="block <#--bg-img-num3 -->bg-white2 bg-light"> <#-- bg-img-num1 class="bg-img-num3" 3 == block bg-lblue bg-light-->

<div class="container">
    <div class="row">
        <div class="col-md-12">

            <#include "mis_nav.ftl">

        </div>
    </div>
    <div class="row">

        <div class="col-md-2">

            <#include "menu.ftl">

        </div>


        <div class="col-md-10 nested" style="padding-right: 20px">
            <div class="row">
                <div class="col-md-12">
                    <#nested>
                </div>
            </div>
        </div>


    </div>
    <div class="row">
        <#include "footer.ftl">
    </div>
</div>

</body>
</html>
</#macro>
