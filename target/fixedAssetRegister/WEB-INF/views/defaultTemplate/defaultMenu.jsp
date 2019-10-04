<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <style>
        .nav-sidebar {
            width: 100%;
            padding: 23px 2px;
        }

        .nav-sidebar a {
            color: #333;
            -webkit-transition: all 0.08s linear;
            -moz-transition: all 0.08s linear;
            -o-transition: all 0.08s linear;
            transition: all 0.08s linear;
        }

        .nav-sidebar .active a {
            cursor: default;
            background-color: #0b56a8;
            color: #fff;
        }

        .nav-sidebar .active a:hover {
            background-color: #E50000;
        }

        .nav-sidebar .text-overflow a, .nav-sidebar .text-overflow .media-body {
            white-space: nowrap;
            overflow: hidden;
            -o-text-overflow: ellipsis;
            text-overflow: ellipsis;
        }
    </style>

</head>
<body>


<div class="gw-sidebar">
    <div id="gw-sidebar" class="gw-sidebar">
        <div class="nano-content">
            <ul class="gw-nav gw-nav-list">
                <!-- <li class="init-un-active"> <a href="javascript:void(0)"> <span class="gw-menu-text">Navigation Menu</span> </a> </li> -->

                <%--Dynamic manu--%>
                <c:forEach var="job" items="${jobList}">
                    <li class="init-arrow-down "><a href="#"><i class="${job.mainCode}"></i>
                            ${job.mainTabName} <b class="gw-arrow"></b></a>
                        <ul class="gw-submenu">
                            <c:forEach items="${job.subList}" var="jobSub">
                                <c:choose>
                                    <c:when test="${jobSub.subtabSubList.size() > 0}">
                                        <li class="init-arrow-down"><a href="#">${jobSub.sub_Tab_Name}</a>
                                            <c:forEach items="${jobSub.subtabSubList}" var="jobSubSub">
                                                <ul class="gw-submenu2">
                                                    <li>
                                                        <a href="${root}/${jobSubSub.refPage}">${jobSubSub.sub_Tab_Sub_Name}</a>
                                                    </li>
                                                </ul>

                                            </c:forEach>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${root}/${jobSub.refPage}"><i class=""></i> ${jobSub.sub_Tab_Name}
                                        </a></li>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>

                <%-- END Manu--%>


                <%--Static Manu--%>
                <%--<li class="init-arrow-down"><a href="#"><i class="fa fa-fw fa-dashboard"></i>--%>
                <%--Asset Category (Class) <b class="gw-arrow"></b></a>--%>
                <%--<ul class="gw-submenu">--%>

                <%--<li><a href="${root}/category/mainCategory"><i class=""></i> Main--%>
                <%--Category</a></li>--%>

                <%--<li><a href="${root}/category/subCategory"><i class=""></i> Sub--%>
                <%--Category</a></li>--%>

                <%--<li><a href="${root}/category/detailCategory"><i class=""></i> Detail--%>
                <%--Category</a></li>--%>

                <%--</ul>--%>
                <%--</li>--%>

                <%--<li class="init-arrow-down"><a href="#"><i class="fa fa-fw fa-table"></i>--%>
                <%--Asset Location<b class="gw-arrow"></b></a>--%>
                <%--<ul class="gw-submenu">--%>

                <%--<li><a href="${root}/location/city"><i class=""></i>--%>
                <%--City</a></li>--%>

                <%--<li><a href="${root}/location/building"><i class=""></i>--%>
                <%--Building</a></li>--%>

                <%--<li><a href="${root}/location/flow"><i class=""></i>--%>
                <%--Floor</a></li>--%>

                <%--<li><a href="${root}/location/room"><i class=""></i>--%>
                <%--Room</a></li>--%>

                <%--</ul>--%>
                <%--</li>--%>


                <%--<li class="init-arrow-down"><a href="#"><i class="fa fa-fw fa-table"></i>--%>
                <%--Asset Allocation<b class="gw-arrow"></b></a>--%>
                <%--<ul class="gw-submenu">--%>

                <%--<li><a href="${root}/allocation/company"><i class=""></i>--%>
                <%--Company</a></li>--%>

                <%--<li><a href="${root}/allocation/department"><i class=""></i>--%>
                <%--Department (Branch)</a></li>--%>

                <%--<li><a href="${root}/allocation/section"><i class=""></i>--%>
                <%--Section</a></li>--%>

                <%--<li><a href="${root}/allocation/location"><i class=""></i>--%>
                <%--Location</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>


                <%--<li class="init-arrow-down"><a href="#"><i class="fa fa-sitemap fa-fw"></i>--%>
                <%--Data<b class="gw-arrow"></b></a>--%>
                <%--<ul class="gw-submenu">--%>

                <%--<li><a href="${root}/data/asset"><i class=""></i> Asset--%>
                <%--Registering</a></li>--%>

                <%--<li><a href="${root}/data/disposal"><i class=""></i> Assets--%>
                <%--Disposal</a></li>--%>

                <%--<li><a href="${root}/data/transfers"><i class=""></i> Assets--%>
                <%--Transfers</a></li>--%>

                <%--<li><a href="${root}/data/damage"><i class=""></i> Assets--%>
                <%--Damaged</a></li>--%>

                <%--&lt;%&ndash;<li><a href="${root}/data/incomeTax" ><i class=""></i> Income&ndash;%&gt;--%>
                <%--&lt;%&ndash;Tax</a></li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li><a href="${root}/data/priorRegister" ><i class=""></i> Assets&ndash;%&gt;--%>
                <%--&lt;%&ndash;Prior Registration</a></li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li><a href="${root}/data/registerExistingAsset"><i class=""></i> Register Existing&ndash;%&gt;--%>
                <%--&lt;%&ndash;Asset</a></li>&ndash;%&gt;--%>

                <%--<li><a href="${root}/data/improvements"><i class=""></i> Assets--%>
                <%--Improvements</a></li>--%>

                <%--<li><a href="${root}/data/disposalResaon"><i class=""></i> Assets--%>
                <%--Disposal Reasons</a></li>--%>

                <%--<li><a href="${root}/data/physicalVerification"><i class=""></i> Assets--%>
                <%--Physical Verification</a></li>--%>

                <%--</ul>--%>
                <%--</li>--%>

                <%--<li class="init-arrow-down"><a href="#"><i class="fa fa-fw fa-book"></i>--%>
                <%--Library <b class="gw-arrow"></b></a>--%>
                <%--<ul class="gw-submenu">--%>

                <%--<li><a href="${root}/library/bookRegistration"><i class=""></i> Asset--%>
                <%--Book Registration</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>

                <%--<li class="init-arrow-down"><a href="#"><i class="fa fa-fw fa-bar-chart-o"></i>--%>
                <%--Reports <b class="gw-arrow"></b></a>--%>
                <%--<ul class="gw-submenu">--%>

                <%--<li><a href="${root}/report/masterListing"><i class=""></i> Master Listing </a></li>--%>

                <%--<li class="init-arrow-down"><a href="#"> Assets--%>
                <%--</a>--%>
                <%--<ul class="gw-submenu2">--%>
                <%--<li><a href="${root}/report/assetListing">Details</a></li>--%>
                <%--</ul>--%>
                <%--<ul class="gw-submenu2">--%>
                <%--<li><a href="${root}/report/withDepreciation	">With Depreciation</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>

                <%--<li class="init-arrow-down"><a href="#"> Class of Assets--%>
                <%--</a>--%>
                <%--<ul class="gw-submenu2">--%>
                <%--<li><a href="${root}/report/finalSummary">Final Summary</a></li>--%>
                <%--</ul>--%>
                <%--<ul class="gw-submenu2">--%>
                <%--<li><a href="${root}/report/locationSummary">Location</a></li>--%>
                <%--</ul>--%>
                <%--<ul class="gw-submenu2">--%>
                <%--<li><a href="${root}/report/departmentSummary">Branch</a></li>--%>
                <%--</ul>--%>
                <%--<ul class="gw-submenu2">--%>
                <%--<li><a href="${root}/report/sectionSummary">Section</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>
                <%--<li><a href="${root}/report/transferListing"><i class=""></i> Transfers </a></li>--%>

                <%--<li><a href="${root}/report/disposalListing"><i class=""></i>--%>
                <%--Disposals </a></li>--%>

                <%--<li><a href="${root}/report/damagedListing"><i class=""></i>--%>
                <%--Damaged </a></li>--%>

                <%--<li><a href="${root}/report/labelPrint"><i class=""></i>--%>
                <%--Label Print </a></li>--%>

                <%--<li><a href="${root}/report/individualAsset"><i class=""></i>--%>
                <%--Detail Of Individual Asset </a></li>--%>

                <%--</ul>--%>
                <%--</li>--%>


                <%--<li class="init-arrow-down"><a href="#"><i class="fa fa-fw fa-wrench"></i>--%>
                <%--Asset Config<b class="gw-arrow"></b></a>--%>
                <%--<ul class="gw-submenu">--%>

                <%--<li><a href="${root}/data/analysisData"><i class=""></i>Analysis Master</a></li>--%>


                <%--</ul>--%>
                <%--</li>--%>

                <%--<li class="init-arrow-down"><a href="#"><i class="fa fa-fw fa-desktop"></i>--%>
                <%--Option <b class="gw-arrow"></b></a>--%>
                <%--<ul class="gw-submenu">--%>

                <%--<li><a href="${root}/preparation/reportPreparation"><i class=""></i>--%>
                <%--Report Preparation </a></li>--%>

                <%--<li><a href="${root}/user/userType"><i class=""></i>--%>
                <%--Add User Type</a></li>--%>

                <%--<li><a href="${root}/user/newUser"><i class=""></i>--%>
                <%--Add User</a></li>--%>

                <%--<li><a href="${root}/user/userJobs"><i class=""></i>--%>
                <%--User Job Define</a></li>--%>

                <%--<li><a href="${root}/option/dataBackup"><i class=""></i>--%>
                <%--Data Backup</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>

                <%--<li class="init-arrow-down"><a href="#"><i class="fa fa-fw fa-desktop"></i>--%>
                <%--Upload <b class="gw-arrow"></b></a>--%>
                <%--<ul class="gw-submenu">--%>

                <%--<li><a href="${root}/upload/uploadFile"><i class=""></i>--%>
                <%--Upload XML Data </a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>







            </ul>
        </div>
    </div>
</div>

</body>
</html>