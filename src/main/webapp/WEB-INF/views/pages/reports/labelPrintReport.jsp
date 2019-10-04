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

    .ui-autocomplete {
        max-height: 200px;
        max-width: 350px;
        overflow-y: auto;
        /* prevent horizontal scrollbar */
        overflow-x: hidden;
        /* add padding to account for vertical scrollbar */
        padding-right: 20px;
    }
</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="row">
    <div class="col-md-12" style="width: 70%">
        <div class="page">
            <form id="lebelPrintForm" action="${root}/report/genarateLabelPrint" target="_blank" method="post">

                <div class="row" style="margin: 0">
                    <legend>Label Print</legend>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Department</div>
                            <div class="col-md-8">
                                <select style="width: 99%" id="departmentId" onchange="loadAssets()"
                                        name="depId">
                                    <option value="0">---SELECT---</option>
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
                            <div class="col-md-4" style="padding-top: 10px">Detail Code</div>
                            <div class="col-md-8">
                                <select style="width: 99%" id="detailCode" onchange="loadAssets()"
                                        name="detailId">
                                    <option value="0">---SELECT---</option>
                                    <c:forEach var="detail" items="${catergoryDetails}">
                                        <option value="${detail.dcatId}">${detail.dcatCode}
                                            - ${detail.dcatDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">From Date</div>
                            <div class="col-md-8">
                            <div class='input-group date'>
                            <input type='text' class="form-control" value="" onchange="loadAssets()" style="height:35px"
                            name="fromDate"/>
                            <span class="input-group-addon">
                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                            <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                            </span>
                            </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">To Date</div>
                            <div class="col-md-8">
                            <div class='input-group date'>
                            <input type='text' class="form-control" onchange="loadAssets()" value="" style="height:35px"
                            name="toDate"/>
                            <span class="input-group-addon">
                            <%--<span class="glyphicon glyphicon-calendar"></span>--%>

                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span> </span>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Assets From</div>
                            <div class="col-md-8">
                                <%--<select style="width: 99%" required id="fromCodeId" name="fromCode">--%>
                                    <%--<option value="">---SELECT---</option>--%>
                                <%--</select>--%>
                                    <input id="assetCodeFrom" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                                           class="form-control" data-maxitems="100"
                                           placeholder="Enter 1st character" >
                                    <input id="fromCodeId" name="fromCode" hidden>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Assets To</div>
                            <div class="col-md-8">
                                <%--<select style="width: 99%" required id="toCodeId" name="toCode">--%>
                                    <%--<option value="">---SELECT---</option>--%>
                                <%--</select>--%>
                                    <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                                           class="form-control" data-maxitems="100"
                                           placeholder="Enter 1st character" >
                                    <input id="toCodeId" name="toCode" hidden>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="row" style="padding-top: 10px">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4" style="padding-top: 3px">
                                <a type="button" class="btn btn-primary" style="width: 100%; font-size: 14px;"
                                   href="${root}/report/labelPrint">New</a>
                            </div>

                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary"
                                        style="width: 100%; font-size: 14px;">
                                    Print
                                </button>
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

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
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

    function submitForm() {
        $("#lebelPrintForm").submit();
        document.getElementById("departmentId").selectedIndex = 0;
        document.getElementById("detailCode").selectedIndex = 0;
        $("input[name=fromDate]").val("");
        $("input[name=toDate]").val("");
    }


    var jsonArray = [];

    function loadAssets() {
        var departmentId = 0;
        var detailId = 0;
        var fromDate = "0";
        var toDate = "0";
        // $("#fromCodeId").empty();
        // $("#toCodeId").empty();
        $("#fromCodeId").find("option:not(:first)").remove();
        $("#toCodeId").find("option:not(:first)").remove();

        if (document.getElementById("departmentId").selectedIndex > 0) {
            departmentId = $("#departmentId").find("option:selected").val();
//            document.getElementById("detailCode").selectedIndex = 0;
//            $("input[name=fromDate]").val("");
//            $("input[name=toDate]").val("");
        }
        if (document.getElementById("detailCode").selectedIndex > 0) {
            detailId = $("#detailCode").find("option:selected").val();
//            document.getElementById("departmentId").selectedIndex = 0;
//            $("input[name=fromDate]").val("");
//            $("input[name=toDate]").val("");
        }
        if ($("input[name=fromDate]").val() != '') {
            fromDate = $("input[name=fromDate]").val();
            fromDate = formatDate(fromDate);
//            document.getElementById("departmentId").selectedIndex = 0;
//            document.getElementById("detailCode").selectedIndex = 0;
        } else {
            fromDate = null;
        }
        if ($("input[name=toDate]").val() != '') {
            toDate = $("input[name=toDate]").val();
            toDate = formatDate(toDate);
//            document.getElementById("departmentId").selectedIndex = 0;
//            document.getElementById("detailCode").selectedIndex = 0;
        } else {
            toDate = null;
        }
        if (fromDate == null && toDate == null) {
            $.ajax({
                url: '${root}/data/loadAssetByDepartmentId/' + departmentId + '/' + detailId,
                success: function (data) {
                    if (data.length > 0) {
                        // for (var i = 0; i < data.length; i++) {
                        //     var subCat = data[i];
                        //     var sub = subCat.split("-");
                        //     if (i == 0) {
                        //         $("#fromCodeId").append(
                        //             "<option selected value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                        //     } else {
                        //         $("#fromCodeId").append(
                        //             "<option value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                        //     }
                        //     if ((data.length - 1) == i) {
                        //         $("#toCodeId").append(
                        //             "<option selected value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                        //     } else {
                        //         $("#toCodeId").append(
                        //             "<option value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                        //     }
                        // }
                        jsonArray = [];
                        for (var i = 0; i < data.length; i++) {
                            var arr2 = {};
                            var subCat = data[i];
                            var sub = subCat.split("-");
                            var id = sub[0];
                            var code = sub[1] + " - " + sub[2];
                            arr2 = {id: id, value: code};

                            jsonArray.push(arr2);

                        }
                    } else {
                        swal({
                            title: 'Warning',
                            text: 'No Assets ! Please Select Other option!',
                            type: 'warning',
                            timer: 2000
                        })
                    }
                },
                error: function (data) {
                }
            });
        } else {
            var userBranch = '<%= session.getAttribute("userBranch") %>';
            $.ajax({
                url: '${root}/data/loadAssetByDepartmentId/' + departmentId + '/' + detailId + '/' + fromDate + '/' + toDate + '/' + userBranch,
                success: function (data) {
                    if (data.length > 0) {
                        // for (var i = 0; i < data.length; i++) {
                        //     var subCat = data[i];
                        //     var sub = subCat.split("-");
                        //     if (i == 0) {
                        //         $("#fromCodeId").append(
                        //             "<option selected value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                        //     } else {
                        //         $("#fromCodeId").append(
                        //             "<option value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                        //     }
                        //     if ((data.length - 1) == i) {
                        //         $("#toCodeId").append(
                        //             "<option selected value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                        //     } else {
                        //         $("#toCodeId").append(
                        //             "<option value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                        //     }
                        // }
                        jsonArray = [];
                        for (var i = 0; i < data.length; i++) {
                            var arr2 = {};
                            var subCat = data[i];
                            var sub = subCat.split("-");
                            var id = sub[0];
                            var code = sub[1] + " - " + sub[2];
                            arr2 = {id: id, value: code};

                            jsonArray.push(arr2);

                        }
                    } else {
                        if (toDate != null) {
                            swal({
                                title: 'Warning',
                                text: 'No Assets ! Please Select Other option!',
                                type: 'warning',
                                timer: 2000
                            })
                        }
                    }
                },
                error: function (data) {
                }
            });
        }
    }

    function loadAssets2() {
        var departmentId = 0;
        var detailId = 0;
        var fromDate = "0";
        var toDate = "0";

        $("#fromCodeId").find("option:not(:first)").remove();
        $("#toCodeId").find("option:not(:first)").remove();

        if (document.getElementById("departmentId").selectedIndex > 0) {
            departmentId = $("#departmentId").find("option:selected").val();
        }

        if (document.getElementById("detailCode").selectedIndex > 0) {
            detailId = $("#detailCode").find("option:selected").val();
        }

        if ($("input[name=fromDate]").val() != '') {
            fromDate = $("input[name=fromDate]").val();
            fromDate = formatDate(fromDate);
        } else {
            fromDate = null;
        }
        if ($("input[name=toDate]").val() != '') {
            toDate = $("input[name=toDate]").val();
            toDate = formatDate(toDate);
        } else {
            toDate = null;
        }
        if (fromDate == null && toDate == null) {
            $.ajax({
                url: '${root}/data/loadAssetByDepartmentId/' + departmentId + '/' + detailId,
                success: function (data) {
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            var subCat = data[i];
                            var sub = subCat.split("-");
                            if (i == 0) {
                                $("#fromCodeId").append(
                                    "<option selected value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                            } else {
                                $("#fromCodeId").append(
                                    "<option value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                            }
                            if ((data.length - 1) == i) {
                                $("#toCodeId").append(
                                    "<option selected value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                            } else {
                                $("#toCodeId").append(
                                    "<option value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                            }
                        }
                    } else {
                        swal({
                            title: 'Warning',
                            text: 'No Assets ! Please Select Other option!',
                            type: 'warning',
                            timer: 2000
                        })
                    }
                },
                error: function (data) {
                }
            });
        } else {
            var userBranch = '<%= session.getAttribute("userBranch") %>';
            $.ajax({
                url: '${root}/data/loadAssetByDepartmentId/' + departmentId + '/' + detailId + '/' + fromDate + '/' + toDate + '/' + userBranch,
                success: function (data) {
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            var subCat = data[i];
                            var sub = subCat.split("-");
                            if (i == 0) {
                                $("#fromCodeId").append(
                                    "<option selected value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                            } else {
                                $("#fromCodeId").append(
                                    "<option value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                            }
                            if ((data.length - 1) == i) {
                                $("#toCodeId").append(
                                    "<option selected value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                            } else {
                                $("#toCodeId").append(
                                    "<option value = '" + sub[0] + "'>" + sub[1] + "-" + sub[2] + "</option>");
                            }
                        }
                    } else {
                        if (toDate != null) {
                            swal({
                                title: 'Warning',
                                text: 'No Assets ! Please Select Other option!',
                                type: 'warning',
                                timer: 2000
                            })
                        }
                    }
                },
                error: function (data) {
                }
            });
        }
    }

    $("#assetCodeTo").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("toCodeId").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,
        max: 100,

    });

    $("#assetCodeFrom").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("fromCodeId").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,
        max: 100,

    });

</script>