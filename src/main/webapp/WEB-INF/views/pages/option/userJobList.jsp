<%--
Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
*  PROPRIETARY AND COPYRIGHT NOTICE.

This software product contains information which is proprietary to
and considered a trade secret MsSoftIT Solution .
It is expressly agreed that it shall not be reproduced in whole or part,
disclosed, divulged or otherwise made available to any third party directly
or indirectly.  Reproduction of this product for any purpose is prohibited
without written authorisation from the The MsSoftIT Solution
All Rights Reserved.

E-Mail mssoftit@gmail.com
URL : mssoftit.lk
Created By : Mahendra Sri Dayarathna

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
    <div class="col-md-11 page">
        <div style="float: right"><input type="checkbox" id="checkAll">Check All</div>
        <form name="jobForm"
              action="${root}/user/saveJob" method="post">
            <div class="row" style="margin: 0">
                <legend>Job Define</legend>

            </div>

            <div class="row" id="accordion">
                <c:set var="count" value="0" scope="page"/>
                <c:forEach var="job" items="${jobList}">
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h5 class="mb-0" data-toggle="collapse" data-target="#collapseOne_${job.mainTabId}"
                                aria-expanded="true" aria-controls="collapseOne_${job.mainTabId}">
                                    ${job.mainTabName}
                                <span class="glyphicon glyphicon-plus pull-right"></span>
                            </h5>
                        </div>

                        <div id="collapseOne_${job.mainTabId}" class="collapse collapseMain"
                             aria-labelledby="headingOne"
                             data-parent="#accordion">
                            <c:forEach items="${job.subList}" var="subJob">
                                <input type="hidden" name="mainTabId" value="${subJob.sub_Tab_No}">
                                <c:choose>
                                    <c:when test="${subJob.subtabSubList.size() > 0}">

                                        <div class="card subCard">
                                            <div class="card-header" id="subHeadingOne">
                                                <h5 class="mb-0 m-0 p-0" data-toggle="collapse"
                                                    data-target="#collapseOne1_${subJob.sub_Tab_No}"
                                                    aria-expanded="true"
                                                    aria-controls="collapseOne1_${subJob.sub_Tab_No}">
                                                        ${subJob.sub_Tab_Name}
                                                    <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                                                </h5>
                                            </div>
                                            <div id="collapseOne1_${subJob.sub_Tab_No}"
                                                 class="collapse collapseSub"
                                                 aria-labelledby="headingOne"
                                                 data-parent="#accordion">

                                                <c:forEach var="subSubJob"
                                                           items="${subJob.subtabSubList}">

                                                    <c:forEach items="${jobDefine}" var="defineJob">
                                                        <%--<c:if test="${defineJob.mainTabId == job.mainTabId}">--%>
                                                        <c:forEach items="${defineJob.subList}"
                                                                   var="defineSub">
                                                            <%--<c:if test="${defineSub.sub_Tab_No == subJob.sub_Tab_No}">--%>
                                                            <c:forEach
                                                                    items="${defineSub.subtabSubList}"
                                                                    var="defineSubSub">
                                                                <c:if test="${defineSubSub.sub_Tab_Sub_ID == subSubJob.sub_Tab_Sub_ID}>">
                                                                    <c:set var="isSubSubTabChecked"
                                                                           value="true"></c:set>
                                                                </c:if>
                                                            </c:forEach>
                                                            <%--</c:if>--%>
                                                        </c:forEach>
                                                        <%--</c:if>--%>
                                                    </c:forEach>
                                                    <c:choose>
                                                        <c:when test="${isSubSubTabChecked}">
                                                            <div class="card-body">
                                                                <div class="checkbox">
                                                                    <div class="col-md-3">
                                                                        <input name="type" type="checkbox"
                                                                               checked="checked"
                                                                               value="${job.mainTabId}_${subJob.sub_Tab_No}_${subSubJob.sub_Tab_Sub_ID}"><label>${subSubJob.sub_Tab_Sub_Name}</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <c:set var="isSubSubTabChecked" value="false"></c:set>
                                                            <c:set var="count" value="${count + 1}" scope="page"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="card-body">
                                                                <div class="checkbox">
                                                                    <div class="col-md-3">
                                                                        <input name="type" type="checkbox"
                                                                               value="${job.mainTabId}_${subJob.sub_Tab_No}_${subSubJob.sub_Tab_Sub_ID}"><label>${subSubJob.sub_Tab_Sub_Name}</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <c:set var="isSubSubTabChecked" value="false"></c:set>
                                                            <c:set var="count" value="${count + 1}" scope="page"/>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${jobDefine}" var="defineJob">
                                            <c:if test="${defineJob.mainTabId == job.mainTabId}">
                                                <c:forEach items="${defineJob.subList}"
                                                           var="defineSub">
                                                    <c:if test="${defineSub.main_Tab_ID == subJob.main_Tab_ID && defineSub.sub_Tab_No == subJob.sub_Tab_No}">
                                                        <c:set var="isSubTabChecked"
                                                               value="true"></c:set>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${isSubTabChecked}">
                                                <div class="card-body">
                                                    <div class="checkbox">
                                                        <div class="col-md-4">
                                                            <input name="type" type="checkbox" checked="checked"
                                                             value="${job.mainTabId}_${subJob.sub_Tab_No}_0"><label>${subJob.sub_Tab_Name}</label>

                                                        </div>
                                                    </div>
                                                </div>
                                                <c:set var="isSubTabChecked"
                                                       value="false"></c:set>
                                                <c:set var="count" value="${count + 1}" scope="page"/>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="card-body">
                                                    <div class="checkbox">
                                                        <div class="col-md-4"><input name="type" type="checkbox"
                                                                                     value="${job.mainTabId}_${subJob.sub_Tab_No}_0"><label>${subJob.sub_Tab_Name}</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <c:set var="isSubTabChecked"
                                                       value="false"></c:set>
                                                <c:set var="count" value="${count + 1}" scope="page"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
                <input type="hidden" id="countLimit" value="${count}"/>
            </div>
            <div class="row" style="margin-top: 5px">
                <div class="col-md-3"><a class="btn btn-primary" style="width: 100%"
                                         href="${root}/">Refresh</a></div>
                <div class="col-md-3" style="margin-top: 3px">
                    <a class="btn btn-primary" style="width: 100%"
                       href="${root}/user/userJobs">Close</a>
                </div>
                <div class="col-md-3">
                    <button type="button" id="btnSavePages" class="btn btn-primary" style="width: 100%">Save
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>


<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="isEdit" value="${isEdit}"/>
<input type="hidden" id="userId" value="${userId}"/>
<input type="hidden" id="branchId" value="${branchId}"/>
<script type="text/javascript">

    $("#checkAll").click(function () {
        $('input:checkbox').not(this).prop('checked', this.checked);
    });

    $('.collapseMain').on('shown.bs.collapse', function () {
        $(this).parent().find(".glyphicon-plus").removeClass("glyphicon-plus").addClass("glyphicon-minus");
    }).on('hidden.bs.collapse', function () {
        $(this).parent().find(".glyphicon-minus").removeClass("glyphicon-minus").addClass("glyphicon-plus");
    });

    $('.collapseSub').on('shown.bs.collapse', function () {
        $(this).parent().find(".glyphicon-chevron-down").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
    }).on('hidden.bs.collapse', function () {
        $(this).parent().find(".glyphicon-chevron-up").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
    });

    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('.btnSubmit').click(function () {
        $(".se-pre-con").show();
    });

    $(document).ready(function () {
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            searching: false,
            paging: false,
            info: false,
            "paging": false
        });
    });

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./userJobs";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Company Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./userJobs";
                })
            }
        }

    });

    $("#btnSavePages").click(function () {
        var userId = $("#userId").val();
        var branchId = $("#branchId").val();
        var AJobDefine = [];
        $("input:checkbox[name=type]:checked").each(function () {
            var id = $(this).val();
            var str = id.split("_");
            var mainId = str[0];
            var subId = str[1];
            var subsubId = str[2];
            item = {}
            item ["main_Tab_Id"] = mainId;
            item ["sub_Tab_Id"] = subId;
            item ["ref_User_Type"] = userId;
            item ["rer_Branch_Id"] = branchId;
            item ["sub_Tab_Sub_Id"] = subsubId;
            AJobDefine.push(item);
        });

        $.ajax({
            url: "${root}/user/saveJob",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(AJobDefine),
            dataType: "json",
            success: function (data) {
                if (data == true) {
                    swal({
                        title: 'success!',
                        type: 'success',
                        timer: 1000
                    })
                } else {
                    swal({
                        title: 'Error !',
                        text: 'User Type Job Define Fail !!',
                        type: 'error',
                        timer: 1000
                    })
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                swal({
                    title: 'Error !',
                    text: 'User Type Job Define Fail !!',
                    type: 'error',
                    timer: 1000
                })
            }
        });

    });

    function onDelete(id) {
        var trId = $(id).closest('tr').attr('id');
        swal({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then(function () {
            $.ajax({
                url: '${root}/location/deleteCity/' + trId,
                success: function (data) {
                    if (data == 1) {
                        swal({
                            title: 'Deleted!',
                            text: 'Your file has been deleted.',
                            type: 'success',
                            timer: 1000
                        })
                        var row = id.parentNode.parentNode;
                        row.parentNode.removeChild(row);
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'City Code already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete City Code !!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }

</script>