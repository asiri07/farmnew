<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 9/28/2017
  Time: 2:25 AM
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
<!-- Ends -->
<div class="col-md-12 page">
    <form name="viewAssetForm" id="viewAssetFormId"
          action="#" method="post">

        <div class="row" style="margin: 0">
            <div class="col-md-12"><legend>View Asset</legend></div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <table id="dataTable" class="display compact" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Transaction No.</th>
                        <th>Asset Code</th>
                        <th>Asset Code Old</th>
                        <th>Description</th>
                        <th>Purchase Date</th>
                        <th>Unit Price</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="asset" items="${assets}">
                        <tr>
                            <td>${asset.transactionNo}</td>
                            <td><a href="${root}/data/viewAssetData/${asset.asId}">${asset.asCode}</a></td>
                            <td>${asset.asCodeOld}</td>
                            <td>${asset.asDes}</td>
                            <td><fmt:formatDate pattern="yyyy/MM/dd" value="${asset.purDate}"/></td>
                            <td align="right"> ${asset.currencyCode}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </form>
</div>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>



<script type="text/javascript">

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
    });

    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });


    $(document).ready(function () {
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": true,

        });

    });



    function checkAssetCode() {
        var fromDepId = $("#departmentID").val();
        var fromSecId = $("#sectionID").val();
        var fromLocId = $("#locationID").val();
        var formAssetCode = fromDepId + fromSecId + fromLocId;

        var toDepId = $("#depID").find("option:selected").text();
        var toSecId = $("#secID").find("option:selected").text();
        var toLocId = $("#locID").find("option:selected").text();
        var toAssetCode = toDepId + toSecId + toLocId;

        if (formAssetCode === toAssetCode) {
            swal({
                title: 'Warning',
                text: 'Can not Transfer Asset with in same Location !!',
                type: 'warning',
                timer: 2000
            })
            $("#btnSubmit").prop('disabled', true);
        } else {
            $("#btnSubmit").prop('disabled', false);
        }
    }

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Transfer success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./transfers";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./transfers";
                })
            }
        }

    });

    function getAssetAlocation() {
        var assetId = $("#assetCodeId").find("option:selected").val();
        $("#departmentID").val("");
        $("#sectionID").val("");
        $("#locationID").val("");
        if (assetId > 0) {
            $.ajax({
                url: '${root}/data/getAssetAlocation/' + assetId,
                success: function (data) {
                    if (data != "") {
                        var subCat = data.split("-");
                        $("#departmentID").val(subCat[1].toUpperCase());
                        $("#sectionID").val(subCat[2].toUpperCase());
                        $("#locationID").val(subCat[3].toUpperCase());
                        $("#depID")[0].selectedIndex = 0;
                        $("#secID").empty();
                        $("#locID").empty();

                    }
                }
            });
        }

    }

    function loadLocation() {
        $("#locID").empty();
        var sectionId = $("#secID").find("option:selected").val();
        var sectionCode = $("#secID").find("option:selected").text();
        $("#secCode").val(sectionCode);
        $.ajax({
            url: '${root}/allocation/getLocations/' + sectionId,
            success: function (data) {
                $("#locID").append(
                    "<option value = ''> ---SELECT--- </option>");
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#locID").append(
                        "<option value = '" + subCat[1] + "'>" + subCat[1]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select section !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }


    $("#btnDepartmentTransfer").click(function (event) {
        var assetId = $("#assetCodeId").find("option:selected").val();
        if (assetId > 0) {
            event.preventDefault();
            $(".se-pre-con").show();
            $.blockUI({
                message: "<h3>Processing, Please Wait...</h3>",
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .4,
                    color: '#fff'
                }
            });
            $("#isDepartmentTrasfer").val(1);
            $("#transferFormID").submit();
        } else {
            $('#divAsset').addClass("txtError").focus();
            swal({
                title: 'Warning',
                text: "Please Select the Asset Code",
                type: 'warning',
                timer: 1000
            })
        }
    });


    function checkDisposalDate() {
        var dat = $("input[name=date]").val();
        var assetId = $("#assetCodeId").find("option:selected").val();
        if (dat != "" && assetId > 0) {
            var date = formatDate(dat);
            $.ajax({
                url: '${root}/data/checkDisposalDate',
                data: {date: date, assetId: assetId},
                success: function (data) {
                    if (data > 0) {
                        var msg;
                        switch (data) {
                            case 1:
                                msg = "Please change Transfers date";
                                break;
                            case 2:
                                msg = "Please select date, after the Asset purchase or register date";
                                break;
                            case 3 :
                                msg = "Please select date, after the Asset transfer date";
                                break;
                            case 4 :
                                msg = "Please select date, after the Asset Damage date";
                                break;
                            case 5 :
                                msg = "Please select date, after the Asset Improvement date";
                        }
                        swal({
                            title: 'Warning',
                            text: msg,
                            type: 'warning',
                            timer: 1000
                        })
                        $("input[name=date]").val("");
                    }

                }

            });
        }
    }
</script>