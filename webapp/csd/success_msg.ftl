<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>
<div class="widget">
    <h2>${msgHead!}</h2>

    <p>${msgBody!}</p>
    <p>
        <a href="viewCustomer.erp?cidView=${cid}" class="button">View
            Customer</a>
    </p>
</div>
</@layout.gen_app_layout>