
<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "Unauthorized" >
<script src="#"></script>

<br class="clear"/>

<div class="widget">

    <div class="header" style="text-align: center;">
        <span style="color: #0000ff; font-size: 20px; font-weight: bold">Sorry ! You have <b style="color: red">NO PERMISSION</b> to access this feature, Sir.</span>

    </div>

    <div class="content">
        <div id="ele1" class="content scaffold-list" role="main">
            <div class="promo" style="min-height: 350px; text-align: center; background-image: url('../resources/images/icm/gate_locked.jpg'); background-repeat: repeat-x">
               <!--<img src="../resources/images/common/mans.gif" style="">    -->

            </div>
            <div class="copyright">
                Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>

</div>

</@layout.gen_app_layout>