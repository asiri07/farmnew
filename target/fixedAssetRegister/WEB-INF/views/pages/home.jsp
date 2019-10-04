<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>

<head>
    <title>Home</title>
    <style>
        .no-js #loader {
            display: none;
        }

        .js #loader {
            display: block;
            position: absolute;
            left: 100px;
            top: 0;
        }

        .se-pre-con {
            position: fixed;
            left: 0px;
            top: 0px;
            width: 100%;
            height: 100%;
            z-index: 9999;
            background: url(${root}/resources/images/Preloader_3.gif) center no-repeat #fff;
            opacity: .8;
        }

        /*.stats-small__value {*/
            /*font-family: Roboto, -apple-system, BlinkMacSystemFont, "Segoe UI", "Helvetica Neue", Arial, sans-serif;*/
            /*font-size: 1.5rem;*/
            /*font-weight: 500;*/
        /*}*/

        .dash{
            text-align: center;
            background-color: red;
        }

        .bg-night-fade {
            background-image: linear-gradient(to top, #a18cd1 0%, #fbc2eb 100%) !important;
        }

        .bg-arielle-smile {
            background-image: radial-gradient(circle 248px at center, #16d9e3 0%, #30c7ec 47%, #46aef7 100%) !important;
        }

        .bg-midnight-bloom {
            background-image: linear-gradient(-20deg, #2b5876 0%, #4e4376 100%) !important;
        }

        .bg-grow-early {
            background-image: linear-gradient(to top, #0ba360 0%, #3cba92 100%) !important;
        }

        .chartjs-render-monitor {
            -webkit-animation: chartjs-render-animation 0.001s;
            animation: chartjs-render-animation 0.001s;
        }

        .text-white {
            color: #fff !important;
        }


    </style>

</head>
<body style="background: #eef1f3">
<br/>
<!-- Paste this code after body tag -->
<%--<div class="row">--%>
<%--<div class="card col-md-3 no-padding" style="background: #bbf3ff">--%>
<%--<div class="card-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-9">--%>
<%--<P class="font-weight-bold">Registered Assets</P>--%>
<%--<div class="h4 mb-0">--%>
<%--<label class="count" style="font-size: 20px" id="noOfAssect"></label>--%>
<%--</div>--%>

<%--</div>--%>
<%--<div class="h1 text-muted text-right mb-4">--%>
<%--<i style="font-size: 60px" class="fa fa-truck"></i>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-lg col-md-6 col-sm-6 mb-4">--%>
<%--&lt;%&ndash;<div class="card col-md-3 no-padding" style="background: rgba(255,203,130,0.85)">&ndash;%&gt;--%>
<%--<div class="card-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-9">--%>
<%--<P class="font-weight-bold">Damaged Assets</P>--%>
<%--<div class="h4 mb-0">--%>
<%--<label class="count" style="font-size: 20px" id="noOfDamage"></label>--%>
<%--</div>--%>

<%--</div>--%>
<%--<div class="h1 text-muted text-right mb-4">--%>
<%--<i style="font-size: 60px" class="fa fa-unlink (alias)"></i>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="card col-md-3 no-padding" style="background: #d9c1ff">--%>
<%--<div class="card-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-9">--%>
<%--<P class="font-weight-bold">Transferd Assets</P>--%>
<%--<div class="h4 mb-0">--%>
<%--<label class="count" style="font-size: 20px" id="noOfTransferd"></label>--%>
<%--</div>--%>

<%--</div>--%>
<%--<div class="h1 text-muted text-right mb-4">--%>
<%--<i style="font-size: 60px" class="fa fa-exchange"></i>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--&lt;%&ndash;<div class="card col-md-3 no-padding" style="background: #ffc0a2">&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="card-body">&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-9">&ndash;%&gt;--%>
<%--&lt;%&ndash;<P class="font-weight-bold">Disposed Assets</P>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="h4 mb-0">&ndash;%&gt;--%>
<%--&lt;%&ndash;<label class="count" style="font-size: 20px" id="noOfDisposal"></label>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>

<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="h1 text-muted text-right mb-4">&ndash;%&gt;--%>
<%--&lt;%&ndash;<i style="font-size: 60px" class="fa fa-times"></i>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>

<%--</div>--%>

<div class="row">
    <div class="col-12 col-md-6 col-lg-3 mb-4">
        <div class="stats-small card card-small">
            <div class="card-body px-0 pb-0 bg-night-fade">
                <div style="position: absolute; inset: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                    <%--<div class="chartjs-size-monitor-expand"--%>
                         <%--style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">--%>
                        <%--<div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div>--%>
                    <%--</div>--%>
                    <%--<div class="chartjs-size-monitor-shrink"--%>
                         <%--style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">--%>
                        <%--<div style="position:absolute;width:200%;height:200%;left:0; top:0"></div>--%>
                    <%--</div>--%>
                </div>
                <div class="d-flex px-3">
                    <div class="text-white">
                        <span>Registered Assets</span>
                        <h4><label class="count" style="font-size: 20px; " id="noOfAssect"></label>
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-12 col-md-6 col-lg-3 mb-4">
        <div class="stats-small card card-small bg-arielle-smile">
            <div class="card-body px-0 pb-0">
                <div style="position: absolute; inset: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"
                     class="chartjs-size-monitor">

                </div>
                <div class="d-flex px-3">
                    <div class="text-white">
                        <span>Damaged Assets</span>
                        <h4><label class="count" style="font-size: 20px; " id="noOfDamage"></label>
                        </h4>
                    </div>

                </div>

            </div>
        </div>
    </div>
    <div class="col-12 col-md-6 col-lg-3 mb-4">
        <div class="stats-small card card-small bg-midnight-bloom">
            <div class="card-body px-0 pb-0">
                <div style="position: absolute; inset: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"
                     class="chartjs-size-monitor">

                </div>
                <div class="d-flex px-3">
                    <div class="text-white">
                        <span>Transferd Assets</span>
                        <h4><label class="count" style="font-size: 20px; " id="noOfTransferd"></label>
                        </h4>
                    </div>

                </div>

            </div>
        </div>
    </div>
    <div class="col-12 col-md-6 col-lg-3 mb-4">
        <div class="stats-small card card-small bg-grow-early">
            <div class="card-body px-0 pb-0">
                <div style="position: absolute; inset: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"
                     class="chartjs-size-monitor">

                </div>
                <div class="d-flex px-3">
                    <div class="text-white">
                        <span>Disposed Assets</span>
                        <h4><label class="count" style="font-size: 20px; " id="noOfDisposal"></label>
                        </h4>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <input type="hidden" name="branchId" id="branchId" value="${branchId}">
</div>

<%--<br/>--%>
<%--<div class="row" >--%>
<%--<div class="card col-md-4 no-padding" style="background: rgba(58,255,85,0.54)">--%>
<%--<div class="card-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-9">--%>
<%--<P class="font-weight-bold">Number of Users</P>--%>
<%--<div class="h4 mb-0">--%>
<%--<span class="count" style="font-size: 20px">124</span>--%>
<%--</div>--%>
<%--<div class="progress progress-xs mt-3 mb-0 bg-flat-color-1" style="width: 40%; height: 5px;"></div>--%>
<%--</div>--%>
<%--<div class="h1 text-muted text-right mb-4">--%>
<%--<i style="font-size: 60px" class="fa fa-users"></i>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="card col-md-4 no-padding" style="background: #e0c5ff">--%>
<%--<div class="card-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-9">--%>
<%--<P class="font-weight-bold">Transferred Assets</P>--%>
<%--<div class="h4 mb-0">--%>
<%--<span class="count" style="font-size: 20px">48500</span>--%>
<%--</div>--%>
<%--<div class="progress progress-xs mt-3 mb-0 bg-flat-color-1" style="width: 90%; height: 5px;"></div>--%>
<%--</div>--%>
<%--<div class="h1 text-muted text-right mb-4">--%>
<%--<i style="font-size: 60px" class="fa fa-exchange"></i>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="card col-md-4 no-padding" style="background: rgba(255,31,25,0.66)">--%>
<%--<div class="card-body">--%>
<%--<div class="row">--%>
<%--<div class="col-md-9">--%>
<%--<P class="font-weight-bold">Disposed Assets</P>--%>
<%--<div class="h4 mb-0">--%>
<%--<span class="count" style="font-size: 20px">3500</span>--%>
<%--</div>--%>
<%--<div class="progress progress-xs mt-3 mb-0 bg-flat-color-1" style="width: 70%; height: 5px;"></div>--%>
<%--</div>--%>
<%--<div class="h1 text-muted text-right mb-4">--%>
<%--<i style="font-size: 60px" class="fa fa-times"></i>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<br/>


<div class="row" style="background: #eef1f3">
    <%--<div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div>--%>
    <div class="col-md-7" style="background: #FFFFFF">

        <div class="row" >
            <div class="col-md-6" id="myChart"></div>
             <div class="col-md-1"></div>
            <div class="col-md-5">
                <div style="height: 90px"></div>
                <div class="row">
                    <span style="font-size:14px;color:#3558b0" class="fa fa-circle"></span> <label>' Furniture &
                    Fittings</label>
                </div>
                <div class="row" style="height: 13px"></div>
                <div class="row">
                    <span style="font-size:14px;color:#3a74db" class="fa fa-circle"></span><label>' Plant & Machinery</label>
                </div>
                <div class="row" style="height: 13px"></div>
                <div class="row">
                    <span style="font-size:14px;color:#3986e2" class="fa fa-circle"></span><label>'
                    Lab Equipments</label>
                </div>
                <div class="row" style="height: 13px"></div>
                <div class="row">
                    <span style="font-size:14px;color:#bbd3fa" class="fa fa-circle"></span><label>' Sport Equipments</label>
                </div>
                <div class="row" style="height: 13px"></div>
                <div class="row">
                    <span style="font-size:14px;color:#c3a0f2" class="fa fa-circle"></span><label>' Vehicle</label>
                </div>
                <div class="row" style="height: 13px"></div>
                <div class="row">
                    <span style="font-size:14px;color:#a385ce" class="fa fa-circle"></span><label>' Land</label>
                </div>
                <div class="row" style="height: 13px"></div>
                <div class="row">
                    <span style="font-size:14px;color:#655088" class="fa fa-circle"></span><label>' Other Asset</label>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-1"  style="background: #FFFFFF">
        <%--<table id="tblVerification" style="overflow-y: auto" class="responstable" width="100%"--%>
        <%--cellspacing="0">--%>
        <%--<thead>--%>
        <%--<tr>--%>
        <%--<th style='width: 137px'>Asset Code</th>--%>
        <%--<th style='width: 100px'>Transaction No</th>--%>
        <%--<th style='width: 100px'>Due Date</th>--%>
        <%--<th style='width: 100px'>Value</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--</tbody>--%>
        <%--</table>--%>
        <div id="reminderDataId"></div>
        <%--<div id="myLineChart">--%>
        <%--</div>--%>
        <%--<div >--%>
        <%--<label style="width: 40px"></label>--%>
        <%--<span style="font-size:14px;color:#5ec2eb" class="fa fa-circle"></span><label style="width: 70px">2017</label>--%>
        <%--<span style="font-size:14px;color:#6de75e" class="fa fa-circle"></span><label style="width: 70px">2018</label>--%>
        <%--<span style="font-size:14px;color:#e31117" class="fa fa-circle"></span><label style="width: 70px">2019</label>--%>
        <%--</div>--%>
    </div>
    <div class="col-md-4">
        <div class="card card-small">
            <%--<div class="card-header border-bottom">--%>
                <%--&lt;%&ndash;<h3 class="m-0"><b>Reminders</b></h3>&ndash;%&gt;--%>
            <%--</div>--%>
            <div class="card-body p-0">
                <ul class="list-group list-group-small list-group-flush">
                    <%--<li class="list-group-item d-flex bg-night-fade px-3">--%>
                        <%--<span class="text-white">EADAD00001--2019-07-13(Premium)</span>--%>
                        <%--<span class="ml-auto text-right text-white"><a class="close" href="#">x</a></span>--%>
                    <%--</li>--%>
                    <%--<li class="list-group-item bg-midnight-bloom d-flex px-3">--%>
                        <%--<span class="text-semibold text-white">ECOCO00159--2019-07-15(Service Agreement)</span>--%>
                        <%--<span class="ml-auto text-right text-white"><a class="close" href="#">x</a></span>--%>
                    <%--</li>--%>
                    <%--<li class="list-group-item d-flex bg-danger px-3">--%>
                        <%--<span class="text-white">FFFFF00001--2019-07-18(Warranty)</span>--%>
                        <%--<span class="ml-auto text-right text-white"><a class="close" href="#">x</a></span>--%>
                    <%--</li>--%>
                    <%--<li class="list-group-item d-flex px-3">--%>
                        <%--<span class="text-semibold text-fiord-blue">Reddit</span>--%>
                        <%--<span class="ml-auto text-right text-semibold text-reagent-gray">8,281</span>--%>
                    <%--</li>--%>
                    <%--<li class="list-group-item d-flex px-3">--%>
                        <%--<span class="text-semibold text-fiord-blue">The Next Web</span>--%>
                        <%--<span class="ml-auto text-right text-semibold text-reagent-gray">7,128</span>--%>
                    <%--</li>--%>
                    <%--<li class="list-group-item d-flex px-3">--%>
                        <%--<span class="text-semibold text-fiord-blue">Tech Crunch</span>--%>
                        <%--<span class="ml-auto text-right text-semibold text-reagent-gray">6,218</span>--%>
                    <%--</li>--%>
                    <%--<li class="list-group-item d-flex px-3">--%>
                        <%--<span class="text-semibold text-fiord-blue">YouTube</span>--%>
                        <%--<span class="ml-auto text-right text-semibold text-reagent-gray">1,218</span>--%>
                    <%--</li>--%>
                    <%--<li class="list-group-item d-flex px-3">--%>
                        <%--<span class="text-semibold text-fiord-blue">Adobe</span>--%>
                        <%--<span class="ml-auto text-right text-semibold text-reagent-gray">827</span>--%>
                    <%--</li>--%>
                </ul>
            </div>
            <%--<div class="card-footer border-top">--%>
                <%--<div class="row">--%>
                    <%--<div class="col">--%>
                        <%--<select class="custom-select custom-select-sm">--%>
                            <%--<option selected="">Last Week</option>--%>
                            <%--<option value="1">Today</option>--%>
                            <%--<option value="2">Last Month</option>--%>
                            <%--<option value="3">Last Year</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <%--<div class="col text-right view-report">--%>
                        <%--<a href="#">Full report →</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
    <div class="col-md-1"></div>
</div>


<%--<div>--%>
<%--<br/>--%>
<%--<div style="height: 20px">--%>
<%--<h3>Most Useful Items</h3>--%>
<%--</div>--%>
<%--<div style="height: 7px"></div>--%>
<%--<div class="row">--%>
<%--<div class="col-md-4">--%>
<%--<aside class="profile-nav alt">--%>
<%--<section class="card">--%>
<%--<div class="card-header user-header alt" style="background: #7a96a3">--%>
<%--<div class="media">--%>
<%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
<%--&lt;%&ndash;<img class="align-self-center rounded-circle mr-3" style="width:85px; height:85px;"&ndash;%&gt;--%>
<%--&lt;%&ndash;alt="" src="images/admin.jpg">&ndash;%&gt;--%>
<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
<%--<div class="media-body">--%>
<%--<h2 class="text-light display-6">Plant & Machinary </h2>--%>
<%--&lt;%&ndash;<p>To The Printer</p>&ndash;%&gt;--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>


<%--<ul class="list-group list-group-flush">--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-archive"></i> Number of units <span--%>
<%--class="badge badge-primary pull-right">81</span></a>--%>
<%--</li>--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-wrench"></i> Damaged <span--%>
<%--class="badge badge-danger pull-right">11</span></a>--%>
<%--</li>--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-bell-o"></i> Transferred <span--%>
<%--class="badge badge-success pull-right">11</span></a>--%>
<%--</li>--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-trash-o"></i> Disposed <span--%>
<%--class="badge badge-warning pull-right r-activity">3</span></a>--%>
<%--</li>--%>
<%--</ul>--%>

<%--</section>--%>
<%--</aside>--%>
<%--</div>--%>
<%--<div class="col-md-4">--%>
<%--<aside class="profile-nav alt">--%>
<%--<section class="card">--%>
<%--<div class="card-header user-header alt" style="background: #7a96a3">--%>
<%--<div class="media">--%>
<%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
<%--&lt;%&ndash;<img class="align-self-center rounded-circle mr-3" style="width:85px; height:85px;"&ndash;%&gt;--%>
<%--&lt;%&ndash;alt="" src="images/admin.jpg">&ndash;%&gt;--%>
<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
<%--<div class="media-body">--%>
<%--<h2 class="text-light display-6">Furniture</h2>--%>
<%--&lt;%&ndash;<p>For programers</p>&ndash;%&gt;--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>


<%--<ul class="list-group list-group-flush">--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-archive"></i> Number of units <span--%>
<%--class="badge badge-primary pull-right">127</span></a>--%>
<%--</li>--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-wrench"></i> Damaged <span--%>
<%--class="badge badge-danger pull-right">5</span></a>--%>
<%--</li>--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-bell-o"></i> Transferred <span--%>
<%--class="badge badge-success pull-right">23</span></a>--%>
<%--</li>--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-trash-o"></i> Disposed <span--%>
<%--class="badge badge-warning pull-right r-activity">9</span></a>--%>
<%--</li>--%>
<%--</ul>--%>

<%--</section>--%>
<%--</aside>--%>
<%--</div>--%>
<%--<div class="col-md-4">--%>
<%--<aside class="profile-nav alt">--%>
<%--<section class="card">--%>
<%--<div class="card-header user-header alt" style="background: #7a96a3">--%>
<%--<div class="media">--%>
<%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
<%--&lt;%&ndash;<img class="align-self-center rounded-circle mr-3" style="width:85px; height:85px;"&ndash;%&gt;--%>
<%--&lt;%&ndash;alt="" src="images/admin.jpg">&ndash;%&gt;--%>
<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
<%--<div class="media-body">--%>
<%--<h2 class="text-light display-6">Printing Equipment</h2>--%>
<%--&lt;%&ndash;<p>Cars of the Managers</p>&ndash;%&gt;--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>


<%--<ul class="list-group list-group-flush">--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-archive"></i> Number of units <span--%>
<%--class="badge badge-primary pull-right">53</span></a>--%>
<%--</li>--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-wrench"></i> Damaged <span--%>
<%--class="badge badge-danger pull-right">12</span></a>--%>
<%--</li>--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-bell-o"></i> Transferred <span--%>
<%--class="badge badge-success pull-right">8</span></a>--%>
<%--</li>--%>
<%--<li class="list-group-item">--%>
<%--<a href="#"> <i class="fa fa-trash-o"></i> Disposed <span--%>
<%--class="badge badge-warning pull-right r-activity">30</span></a>--%>
<%--</li>--%>
<%--</ul>--%>

<%--</section>--%>
<%--</aside>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div style="height: 15px"></div>--%>
<%--</div>--%>

<div class="se-pre-con"></div>
<!-- Ends -->

</body>


<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
<script type="text/javascript">

    loadNoOFAsset();
    loadAssetTypes();

    // loadData();

    $('.close').click(function(){
        var $target = $(this).parents('li');
        $target.hide('slow', function(){ $target.remove(); });
    })

    function loadAssetTypes() {
        $.ajax({
            url: '${root}/category/loadTypeOfMainCode',
            success: function (data1) {
                if (data1 != 0) {
                    qtyFurniture = data1[0];
                    qtyICT = data1[1];
                    qtyOffice = data1[2];
                    qtyParti = data1[3];
                    qtyVeh = data1[4];
                    qtyLand = data1[5];
                    qtyOther = data1[6];

                    chartShow();
                } else {
                    // $("#noOfAssect").text("50");
                }
            }
        });
    }

    function chartShow() {
        zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
        ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"];
        var myConfig = {
            "type": "pie",
            "title": {
                "text": "Assets in Main Code"
            },
            "series": [
                {
                    "values": [qtyFurniture],
                    "background-color": "#3558b0"
                },
                {
                    "values": [qtyICT],
                    "background-color": "#3a74db"
                },
                {
                    "values": [qtyOffice],
                    "background-color": "#3986e2"
                },
                {
                    "values": [qtyParti],
                    "background-color": "#bbd3fa"
                },
                {
                    "values": [qtyVeh],
                    "background-color": "#c3a0f2"
                },
                {
                    "values": [qtyLand],
                    "background-color": "#a385ce"
                },
                {
                    "values": [qtyOther],
                    "background-color": "#655088"
                }
            ]
        };

        zingchart.render({
            id: 'myChart',
            data: myConfig,
            height: 400,
            width: "100%"
        });

    }

    //line chart

    // var myConfig = {
    //     "type": "line","title": {
    //         "text": "Assets On Months"
    //     },   "scale-x":{
    //         "label":{
    //             "text":"Months"
    //         }
    //     },"scale-y":{
    //         "label":{
    //             "text":"No of Assets"
    //         }
    //     }, "series": [
    //         {"values": [0, 40, 25, 50, 15, 45, 33, 34, 20, 40, 25, 50, 15]},
    //         {"values": [0, 30, 21, 18, 59, 50, 28, 33,5, 30, 21, 18, 59 ]},
    //         {"values": [0, 5, 18, 21, 33, 41, 29, 15, 30, 5, 18, 21, 33]}
    //     ]
    // };
    //
    // zingchart.render({
    //     id: 'myLineChart',
    //     data: myConfig,
    //     height: 350,
    //     width: "100%"
    // });


    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
        // loadNoOFAsset();
    });

    $('#btnSubmit').click(function () {
        $(".se-pre-con").show();
    });

    function loadNoOFAsset() {
        <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
        var branchId = $("#branchId").val();
        $.ajax({
            url: '${root}/data/loadNoDataForDashBord/'+branchId,
            success: function (data) {
                // alert(data);
                if (data != 0) {
                    $("#noOfAssect").text(data[0]);
                    $("#noOfDamage").text(data[1]);
                    $("#noOfDisposal").text(data[2]);
                    $("#noOfTransferd").text(data[3]);
                } else {
                    // $("#noOfAssect").text("50");
                }
            }
        });
    }


    <%--function loadData() {--%>
    <%--var dateFrom=$("2019-04-02").val();--%>
    <%--var today = formatDate("2019/03/01");--%>
    <%--var noDays=$("#noOfDays").val();--%>
    <%--var categoryType=$("#category").find("option:selected").val();--%>
    <%--$("#tblVerification").find("tr:not(:first)").remove();--%>
    <%--if (today != "") {--%>
    <%--$.ajax({--%>
    <%--url: '${root}/report/loadData/' + today +'/'+30+'/'+1,--%>
    <%--success: function (data) {--%>
    <%----%>
    <%--$("#reminderDataId").append("<h2 style='margin-left: 100px'>Insurance Reminders</h2>");--%>
    <%--for (var i = 0; i < data.length; i++) {--%>
    <%--var date1 =formatDate(data[i].dateFrom);--%>
    <%--var date2 =formatDate(data[i].dateTo);--%>

    <%--// var markup = "<tr><td><input type='text' readonly style='width: 137px' value='" + data[i].assetCode + "' name='assetCode'></td> " +--%>
    <%--//     "<td><input type='text' readonly style='width: 100px' value='" + data[i].transactionNo + "' name='transactionNo'></td>" +--%>
    <%--//     "<td><input type='text' style='width: 100px' ' value='" + date2+ " ' name='toDate'></td>" +--%>
    <%--//     "<td><input type='text' style='width: 100px' value='" + data[i].value + "' name='value'></td></tr>";--%>
    <%--var markup = "<div class='row' style='margin-top: 10px; background:#ceceff' ><div>" +--%>
    <%--"<div class='row'><label class='col-md-4'>Asset Code</label><input class='col-md-6' type='text' readonly  value='" + data[i].assetCode + "' name='assetCode'></div> " +--%>
    <%--"<div class='row'><label class='col-md-4'>Date From</label><input class='col-md-6' type='text' readonly  value='" + date1 + "' name='fromDate'></div>" +--%>
    <%--"<div class='row'><label class='col-md-4'>Due Date</label><input class='col-md-6' type='text' value='" + date2+ " ' name='toDate'></div>" +--%>
    <%--"<div class='row'><label class='col-md-4'>Value</label><input class='col-md-6' type='text' value='" + data[i].value + "' name='value'></div>" +--%>
    <%--"</div>";--%>
    <%--// // var markup = "<P> test </P>";--%>
    <%--$("#reminderDataId").append(markup);--%>
    <%--}--%>
    <%--}--%>
    <%--})}else {--%>
    <%--swal({--%>
    <%--title: 'Warning',--%>
    <%--text: 'Please select Date !!!',--%>
    <%--type: 'warning',--%>
    <%--timer: 3000--%>
    <%--})--%>
    <%--}--%>
    <%--}--%>


    // // Load google charts
    // google.charts.load('current', {'packages':['corechart']});
    // google.charts.setOnLoadCallback(drawChart);
    //
    // // Draw the chart and set the chart values
    // function drawChart() {
    //     var data = google.visualization.arrayToDataTable([
    //         ['Task', 'Hours per Day'],
    //         ['Land', 13],
    //         ['Building', 2],
    //         ['Computer', 25],
    //         ['Vehicle', 2],
    //         ['Office Equipment', 8]
    //     ]);
    //
    //     // Optional; add a title and set the width and height of the chart
    //     var options = {'title':'Types of Asset ', 'width':550, 'height':400};
    //
    //     // Display the chart inside the <div> element with id="piechart"
    //     var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    //     chart.draw(data, options);
    // }

</script>
</html>
