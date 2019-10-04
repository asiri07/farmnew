<%--
  Created by IntelliJ IDEA.
  User: Hashane
  Date: 2019-01-07
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<div class="col-md-6 page">
    <div class="row" style="margin: 0">
        <legend>Warranty</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="WarrantyForm" action="${root}/maintenance/saveWarranty" method="post">
                <div class="row">
                    <div class="col-md-4">Asset Type</div>
                    <div class="col-md-8">
                        <select style="width: 100%" name="category" id="category" onchange="loadAssetByType()">
                            <option value="0">---SELECT---</option>
                            <option value="Vehicle">Vehicle</option>
                            <option value="Computer">Computer</option>
                            <option value="Plant">Plant</option>
                            <option value="Furniture">Furniture</option>
                            <option value="Office">Office Equipment</option>
                            <option value="Lab">Lab Equipment</option>
                            <option value="Teaching">Teaching Equipment</option>
                            <option value="Fixtures">Fixtures And Fitting</option>
                            <option value="Sports">Sports Equipment</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                    <div class="col-md-8">
                        <select required id="assetId" class="chosen" style="width: 100%"
                                onchange="clearFields(),loadWarrantyNoList()" name="assetId">
                            <option value="">---SELECT---</option>
                        </select>
                    </div>
                </div>

                <div class="row">

                    <div class="col-md-4" style="padding-top: 10px">Transaction No.</div>
                    <div class="col-md-8">
                        <select id="transactionSelectId" class="chosen" style="width: 100%"
                                onchange="loadWarrantyDetails()"
                                name="transactionSelect">
                            <option value="">---SELECT---</option>
                        </select>
                    </div>
                </div>
                <div class="row">

                    <div class="col-md-4" style="padding-top: 10px">Warranty Period (Month)</div>
                    <div class="col-md-8">
                        <input type="text" required style="width: 100%" maxlength="10" id="warrantyPeriodId"
                               onchange="loadToDate()"
                               name="warrantyPeriod"/>
                    </div>
                </div>

                <div class="row">

                    <div class="col-md-4" style="padding-top: 10px">Period From</div>
                    <div class="col-md-8">
                        <div class='input-group date' style="width: 100%">
                            <input type='text' required class="form-control" style="height: 93%"
                                   id="warrantyPeriodFromId" onchange="checkFromDate(),loadToDate()"
                                   name="warrantyPeriodFrom"/>
                            <span class="input-group-addon">
                                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Period To</div>
                    <div class="col-md-8">
                        <div class='input-group date' style="width: 100%">
                            <input type='text' required class="form-control" id="warrantyPeriodToId"
                                   onchange="checkToDate()"
                                   name="warrantyPeriodTo" style="height: 93%"/>
                            <span class="input-group-addon">
                                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                        </div>
                    </div>
                </div>
                <div>&nbsp</div>
                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-6" style="padding-top: 3px">
                                <a class="btn btn-primary" style="width: 100%; font-size: 14px"
                                   href="${root}/maintenance/warranty">New</a>
                            </div>
                            <div class="col-md-6">
                                <button type="submit" class="btn btn-primary" id="btnSave"
                                        style="width: 100%; font-size: 14px">Save
                                </button>
                            </div>
                        </div>
                    </div>

                </div>

                <input type="hidden" name="warrantyId" value="${warranty.warrantyId}"/>
                <input type="hidden" name="transactionNo" id="transactionNo" value="${transactionNo}"/>

            </form>
        </div>
    </div>
</div>


<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>


<script type="text/javascript">

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var transactionNo = $("#transactionNo").val();
        var message = "";

        if (transactionNo != "") {
            message = "Saved Successfully!!! Transaction No : ";
        } else {
            message = "Updated Successfully!!! ";
        }

        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: message + transactionNo,
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./warranty";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./warranty";
                })
            }
        }
    });


    function loadWarrantyNoList() {

        var assetId = $("#assetId").find("option:selected").val();

        $("#transactionSelectId").find("option:not(:first)").remove();
        $.ajax({
            url: '${root}/maintenance/loadWarrantyNoList/' + assetId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#transactionSelectId").append(
                        "<option value = '" + data[i].warrantyId + "'>" + data[i].transactionNo
                        + "</option>");
                }
            }
        });
    }

    function loadWarrantyDetails() {
        var warrantyId = $("#transactionSelectId").find("option:selected").val();

        $.ajax({
            url: '${root}/maintenance/loadWarrantyDetailsByWarrantyId/' + warrantyId,
            success: function (data) {
                if (data != "") {
                    $("input[name=warrantyId]").val(data.warrantyId);
                    $("input[name=transactionNo]").val(data.transactionNo);
                    $("input[name=warrantyPeriod]").val(data.warrantyPeriod);
                    $("input[name=warrantyPeriodFrom]").val(dateFormator(data.warrantyPeriodFrom));
                    $("input[name=warrantyPeriodTo]").val(dateFormator(data.warrantyPeriodTo));
                    document.getElementById('category').value = data.category;
                    $("#btnSave").html('Update');
                }
            }
        });
    }


    function clearFields() {
        $("input[name=warrantyId]").val("0");
        $("input[name=transactionNo]").val("");
        $("input[name=warrantyPeriod]").val("");
        $("input[name=warrantyPeriodFrom]").val("");
        $("input[name=insurancePeriodFrom]").val("");
        $("input[name=warrantyPeriodTo]").val("");
        $("#btnSave").html('Save');
    }


    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('.tabgroup > div').hide();
    $('.tabgroup > div:first-of-type').show();
    $('.tabs a').click(function (e) {
        e.preventDefault();
        var $this = $(this),
            tabgroup = '#' + $this.parents('.tabs').data('tabgroup'),
            others = $this.closest('li').siblings().children('a'),
            target = $this.attr('href');
        others.removeClass('active');
        $this.addClass('active');
        $(tabgroup).children('div').hide();
        $(target).show();

    });

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
    });

    function loadAssetByType() {
        var type = $('#category').val(); // get selected value
        var userBranch = '<%= session.getAttribute("userBranch") %>';
        $.ajax({
            url: '${root}/maintenance/loadAssetByTypeWarranty/' + type + '/'+ userBranch ,
            success: function (data) {
                if (data != null) {
                    $("#assetId").empty();
                    $("#assetId").append('<option value="">---SELECT---</option>');
                    for (var i = 0; i < data.length; i++) {
                        $("#assetId").append(
                            "<option value = '" + data[i][0] + "'>" + data[i][1] + " - " + data[i][2]
                            + "</option>");
                    }
                }
            }
        });
    }


    function loadToDate() {
        var periodFrom = $("input[name=warrantyPeriodFrom]").val();
        var period = $("input[name=warrantyPeriod]").val();

        var date = formatDate(periodFrom);


        if (period != "") {
            if (periodFrom != "") {
                $.ajax({
                    url: '${root}/maintenance/loadToDate',
                    data: {date: date, period: period},

                    success: function (data) {
                        var date2 = data;
                        $("input[name=warrantyPeriodTo]").val(dateFormators(date2));
                    }
                });
            }
        } else {
            swal({
                title: 'Warning',
                text: "Please Enter Warranty Agree Period",
                type: 'warning',
                timer: 1000
            });
            $("input[name=warrantyPeriodTo]").val("");
        }
    }

    function checkFromDate() {

        var periodFrom = $("input[name=warrantyPeriodFrom]").val();
        var periodTo = $("input[name=warrantyPeriodTo]").val();
        var assetId = $("#assetId").find("option:selected").val();

        if (assetId > 0) {
            if (periodFrom != "") {
                var date = formatDate(periodFrom);
                $.ajax({
                    url: '${root}/maintenance/checkDeedSignedDate',
                    data: {date: date, assetId: assetId},
                    success: function (data) {
                        if (data > 0) {
                            var msg;
                            switch (data) {
                                case 1:
                                    msg = "";
                                    break;
                                case 2:
                                    msg = "Please select date, after Asset registered date";
                                    break;

                            }
                            swal({
                                title: 'Warning',
                                text: msg,
                                type: 'warning',
                                timer: 1000
                            })
                            $("input[name=warrantyPeriodFrom]").val("");
                        }

                    }

                });
            }
        } else {
            swal({
                title: 'Warning',
                text: "Please Select Asset Id",
                type: 'warning',
                timer: 1000
            });
            $("input[name=warrantyPeriodFrom]").val("");
        }
    }

    function checkToDate() {

        var periodFrom = $("input[name=warrantyPeriodFrom]").val();
        var periodTo = $("input[name=warrantyPeriodTo]").val();
        var assetId = $("#assetId").find("option:selected").val();

        if (assetId > 0) {
            if (periodFrom == "") {
                swal({
                    title: 'Warning',
                    text: "Select From Date",
                    type: 'warning',
                    timer: 1000
                })
                $("input[name=warrantyPeriodTo]").val("");
            }

        }

        else {
            swal({
                title: 'Warning',
                text: "Please Select Asset Id",
                type: 'warning',
                timer: 1000
            });
            $("input[name=warrantyPeriodTo]").val("");
        }
    }


</script>
