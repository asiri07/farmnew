<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 10/26/2017
  Time: 6:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

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
</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="row">
    <div class="col-md-12" style="width: 70%">
        <div class="page">
            <form name="" action="${root}/report/genarateBranchSummary" target="_blank" method="post">

                <div class="row" style="margin: 0">
                    <legend>Branch Summary</legend>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Branch</div>
                            <div class="col-md-8">
                                <select style="width: 95%" required name="fromcord">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="department" items="${departments}">
                                        <option value="${department.depId}">${department.depCode}
                                            - ${department.depDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-8">
                                <select style="width: 95%" required name="tocord">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="department" items="${departments}">
                                        <option value="${department.depId}">${department.depCode}
                                            - ${department.depDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Process Range</div>
                            <div class="col-md-8" >
                                <input class="form-control" name="Fromdate" readonly style="width: 95%;height:105%" value="${fromDate}"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-8">
                                <input class="form-control" name="Todate" readonly style="width: 95%;height:105%" value="${toDate}"/>
                            </div>
                            <div class="col-md-4"></div>
                        </div>

                    </div>
                </div>
<div class="row">&nbsp</div>
                <div class="row" style="padding-top: 10px">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <a type="button" class="btn btn-primary" style="width: 100%; font-size: 14px;"
                                        href="${root}/report/departmentSummary">New</a>
                            </div>

                            <div class="col-md-4">
                                <c:choose>
                                    <c:when test="${doPreparate}">
                                        <button type="submit" disabled class="btn btn-primary" style="width: 100%; font-size: 14px;">Print
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-primary" style="width: 100%; font-size: 14px;">Print</button>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="col-md-2"></div>
                        </div>
                    </div>
                    <div class="col-md-6"></div>
                </div>

            </form>
        </div>
    </div>
</div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="doPreparate" value="${doPreparate}"/>

<script type="text/javascript">
    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('#btnSubmit').click(function () {
        $(".se-pre-con").show();
    });

    $(document).ready(function () {
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": false
        });
    });

    $(function () {
        var status = $("#status").val();
        if (status == 'true') {
            swal({
                title: 'Warning',
                text: 'Please do the Report Preparation!',
                type: 'warning',
                timer: 2000
            })
        }

    });

</script>