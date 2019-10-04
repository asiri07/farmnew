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
            <form name="" action="${root}/report/genarateFinalSummary" target="_blank" id="formFinalSummary" method="GET">

                <div class="row" style="margin: 0">
                    <legend>Final Summary</legend>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Class of Asset </div>
                            <div class="col-md-8">
                                <select id="fromMainCat" style="width: 95%" name="fromMainCode">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="mainCats" items="${mainCats}">
                                        <option value="${mainCats.mcatId}">${mainCats.mcatCode} - ${mainCats.mcatDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-8">
                                <select id="toMainCat" style="width: 95%" name="toMainCode">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="mainCats" items="${mainCats}">
                                        <option value="${mainCats.mcatId}">${mainCats.mcatCode} - ${mainCats.mcatDes}</option>
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
                            <div class="col-md-5" style="padding-top: 10px">Process Range</div>
                            <div class="col-md-7" style="margin-left: -46px">
                                <input type="text" name="fromDate" readonly style="width: 110%" value="${fromDate}"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-7">
                                <input type="text" name="toDate" readonly style="width: 110%" value="${toDate}"/>
                            </div>
                            <div class="col-md-5"></div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-2" style="padding-top: 10px">Group By</div>
                    <div class="col-md-5">
                        <select id="" required style="width: 75%" name="groupType">
                            <option value="">--SELECT--</option>
                            <option value="1">Main Category Wise</option>
                            <option value="2">Sub Category Wise</option>
                            <option value="3">Detail Category Wise</option>
                        </select>
                    </div>
                    <div class="col-md-5"></div>
                </div>

                <div class="row" style="padding-top: 10px">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <a type="button" class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px; margin-top: 2px" href="${root}/report/finalSummary">New</a>
                            </div>

                            <div class="col-md-4">
                                <c:choose>
                                    <c:when test="${doPreparate}">
                                        <button type="submit" disabled class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px">Print</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px">Print</button>
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