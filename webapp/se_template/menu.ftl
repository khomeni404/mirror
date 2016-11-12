<style>#se-main-menu a {color: #000000; font-weight: bold}</style>
<div class="block block-drop-shadow">
    <div class="user bg-default bg-light-rtl">
        <div class="info">
            <#--<a href="#" class="informer informer-three">
                <span>14 / 255</span>
                Messages
            </a>-->
           <#-- <a href="#" class="informer informer-four">
                <span>$549.44</span>
                Balance
            </a>-->
            <img src="../resources/images/logo/qrf_logo_big.png" class="img-circle img-thumbnail"/>
        </div>
    </div>
    <div class="content list-group list-group-icons" id="se-main-menu">
        <a href="${ctx}/home/home.se" class="list-group-item">0. <span class="icon-home"></span>DASHBOARD</a>
        <a href="${ctx}/security/home.se" class="list-group-item">1. <span class="icon-key"></span>ACCESS CONTROL HOME</a>
        <a href="${ctx}/admin/home.se" class="list-group-item">2. <span class="icon-user"></span>ADMIN HOME</a>
        <a href="${ctx}/admin/home.se" class="list-group-item">3. <span class="icon-user"></span>ORG. MGT. HOME</a>
        <a href="${ctx}/admin/home.se" class="list-group-item">4. <span class="icon-user"></span>HRM HOME</a>
        <a href="${ctx}/pub/home.se" class="list-group-item">5. <span class="icon-book"></span>PUBLICATION HOME</a>
        <a href="${ctx}/accounts/home.se" class="list-group-item">6. <span class="icon-dollar"></span>ACCOUNTS HOME</a>
        <a href="${ctx}/accounts/home.se" class="list-group-item">7. <span class="icon-dollar"></span>EDUCATION HOME</a>
        <a href="${ctx}/mis/home.se" class="list-group-item">8. <span class="icon-key"></span>MIS HOME</a>
        <a href="${ctx}/security/editPassword.se" class="list-group-item"><span class="icon-key"></span>Change Password</a>
        <a href="${ctx}/auth/logout.se" class="list-group-item"><span class="icon-off text-danger"></span>Logout</a>
    </div>
</div>

<#--
<div class="block block-drop-shadow">
    <div class="head bg-dot20">
        <div class="form-row">
            <div class="col-md-12">
                <form action="${ctx}/shop/searchSalesLedger.se" method="GET">
                    <div class="input-group">
                        <div class="input-group-addon">
                            <span class="icon-search"></span>
                        </div>
                        <input type="text" name="salesLedgerSearchText" id="salesLedgerSearchText" class="form-control"
                               style="color: #ffffff" placeholder="Search"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>-->

<div class="block block-drop-shadow">
    <div class="user bg-default bg-light-rtl" id="menu-advertisement">
        <img src="../resources/images/logo/qrf3.jpg" class="img-rounded img-thumbnail"/>
    </div>
</div>
<#--
<div class="block block-drop-shadow">
    <div class="head bg-dot20">
        <h2>CPU</h2>
        <div class="side pull-right">
            <ul class="buttons">
                <li><a href="#"><span class="icon-cogs"></span></a></li>
            </ul>
        </div>
        <div class="head-subtitle">Intel Core2 Duo T6670 2.20GHz</div>
        <div class="head-panel nm">
            <div class="hp-info hp-simple pull-left hp-inline">
                <span class="hp-main">Core 0 <span class="icon-angle-right"></span> 89%</span>

                <div class="hp-sm">
                    <div class="progress progress-small">
                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="89"
                             aria-valuemin="0" aria-valuemax="100" style="width: 89%"></div>
                    </div>
                </div>
            </div>
            <div class="hp-info hp-simple pull-left hp-inline">
                <span class="hp-main">Core 1 <span class="icon-angle-right"></span> 56%</span>

                <div class="hp-sm">
                    <div class="progress progress-small">
                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="56"
                             aria-valuemin="0" aria-valuemax="100" style="width: 56%"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="block block-drop-shadow">
    <div class="head bg-dot20">
        <h2>Memory</h2>

        <div class="side pull-right">
            <ul class="buttons">
                <li><a href="#"><span class="icon-cogs"></span></a></li>
            </ul>
        </div>
        <div class="head-subtitle">Kingston 2x8Gb DDR3 1866MHz</div>
        <div class="head-panel nm tac">
            <div class="sparkline">
                <span sparkType="pie" sparkWidth="100" sparkHeight="100">5079,3768,7537</span>
            </div>
        </div>
        <div class="head-panel nm">
            <div class="hp-info hp-simple pull-left hp-inline">
                <span class="hp-main"><span class="icon-circle"></span> Allocated 5079 Mb [ 31% ]</span>
            </div>
            <div class="hp-info hp-simple pull-left hp-inline">
                <span class="hp-main"><span class="icon-circle text-info"></span> In Cache 3768 Mb [ 23% ]</span>
            </div>
            <div class="hp-info hp-simple pull-left hp-inline">
                <span class="hp-main"><span class="icon-circle text-primary"></span> Aviable 7537 Mb [ 46% ]</span>
            </div>
        </div>
    </div>
</div>
<div class="block block-drop-shadow">
    <div class="head bg-dot20">
        <h2>Volumes status</h2>

        <div class="side pull-right">
            <ul class="buttons">
                <li><a href="#"><span class="icon-cogs"></span></a></li>
            </ul>
        </div>
        <div class="head-subtitle">WD Caviar Blue 1TB</div>
        <div class="head-panel nm tac" style="line-height: 0px;">
            <div class="knob">
                <input type="text" data-fgColor="#3F97FE" data-min="0" data-max="1024" data-width="100"
                       data-height="100" value="654" data-readOnly="true"/>
            </div>
        </div>
        <div class="head-panel nm">
            <div class="hp-info hp-simple pull-left hp-inline">
                <span class="hp-main">Volume 1 [ 0.5 TB ]</span>
                <span class="hp-sm">Used: 450.0 GB [ 90% ] </span>
                <span class="hp-sm">Free: 50.0 GB [ 10% ] </span>
            </div>
            <div class="hp-info hp-simple pull-left hp-inline">
                <span class="hp-main">Volume 2 [ 0.5 TB ]</span>
                <span class="hp-sm">Used: 154.0 GB [ 30% ] </span>
                <span class="hp-sm">Free: 346.0 GB [ 70% ] </span>
            </div>
        </div>
    </div>
</div>
-->

<script type="text/javascript">
    $(function () {
        $('#menu-advertisement').css("height", ($(window).height())-533);
    })
</script>