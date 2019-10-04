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
    <div class="col-md-10" style="width: 70%">
        <div class="page">
            <form name="" action="${root}/report/generateMaintenanceMasterListingReport" target="_blank"
                  id="formGenerateMaintenanceMasterListingReportt" method="post">

                <div class="row" style="margin: 0">
                    <legend>Master Listing</legend>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Listing</div>
                            <div class="col-md-8">
                                <select required style="width: 96%" name="listingType" id="listingTypeID"
                                        onchange="loadAssetByType()">
                                    <option value="">--SELECT--</option>
                                    <option value="1">Land</option>
                                    <option value="2">Land & Building</option>
                                    <option value="3">Vehicle</option>
                                    <option value="4">Computer</option>
                                    <option value="5">Plant & Machinery</option>
                                    <option value="6">Furniture</option>
                                    <option value="7">Office Equipment</option>
                                    <option value="8">Lab Equipment</option>
                                    <option value="9">Teaching Equipment</option>
                                    <option value="10">Fixtures & Fitting</option>
                                    <option value="11">Library Book</option>
                                    <option value="12">Sports Equipment</option>
                                    <option value="13">Software</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Data Type</div>
                            <div class="col-md-8">
                                <select required style="width: 95%" name="dataType" id="dataType">
                                    <option value="1">Primary Data</option>
                                    <option value="2">Secondary Data</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="width:95%;padding-top: 10px">Assets From</div>
                            <%--<div class="col-md-8">--%>
                            <%--<select style="width: 95%" required name="assetFrom" id="assetFromId">--%>
                            <%--<option value="">---SELECT---</option>--%>
                            <%--</select>--%>
                            <%--</div>--%>

                            <div class="col-md-8" id="divAsset">
                                <select class="chosen" required
                                        style="width: 97%">
                                    <option value="">---SELECT---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Assets To</div>
                            <%--<div class="col-md-8">--%>
                            <%--<select style="width: 95%" required name="assetTo" id="assetToId">--%>
                            <%--<option value="">---SELECT---</option>--%>
                            <%--</select>--%>
                            <%--</div>--%>

                            <div class="col-md-8" id="divAssetTo">
                                <select class="chosen" required
                                        style="width: 97%">
                                    <option value="">---SELECT---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" style="padding-top: 10px">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4" style="padding-top: 3px">
                                <a type="button" class="btn btn-primary" style="width: 100%; font-size: 14px"
                                   href="${root}/report/maintenanceMasterListingReport">New</a>
                            </div>
                            <div class="col-md-4">
                                <button onclick="$('#dataType').attr('disabled',false);" onblur="$('#dataType').attr('disabled',true);" class="btn btn-primary" style="width: 100%; font-size: 14px">
                                    Print
                                </button>
                            </div>
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
<input type="hidden" id="status" value="${status}"/>

<script type="text/javascript">
    $(".chosen").chosen();
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
        var pageType = $("#pageType").val();
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'success!',
                    type: 'success',
                    timer: 1000
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Main Category Added Fail !!',
                    type: 'error',
                    timer: 1000
                })
            }
        }
    });

    <%--function loadListing(id) {--%>
        <%--var listing = id.value;--%>
        <%--$("#fromId").empty();--%>
        <%--$("#toId").empty();--%>
        <%--$("#listingTypeID").val("");--%>
        <%--$("#listingTypeID").val(listing);--%>
        <%--$.ajax({--%>
            <%--url: '${root}/report/loadListing/' + listing,--%>
            <%--success: function (data) {--%>
                <%--for (var i = 0; i < data.length; i++) {--%>
                    <%--$("#fromId").append(--%>
                        <%--"<option value = '" + data[i].listingId + "'>" + data[i].listingName + " - " + data[i].description--%>
                        <%--+ "</option>");--%>
                    <%--$("#toId").append(--%>
                        <%--"<option value = '" + data[i].listingId + "'>" + data[i].listingName + " - " + data[i].description--%>
                        <%--+ "</option>");--%>

                <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    function loadAssetByType() {
        var type = $('#listingTypeID').val(); // get selected value
        var userBranch = '<%= session.getAttribute("userBranch") %>';

        if (type == 1 || type == 2 || type == 3) {
            $('#dataType').attr('disabled',false);
        }else{
            document.getElementById("dataType").selectedIndex = 0;
            $('#dataType').attr('disabled',true);
        }

        $.ajax({
            url: '${root}/data/loadAssetByTypes/' + userBranch + '/' +type ,
            success: function (data) {

                $("#divAsset").html('');
                $("#divAsset").html(' <select class="chosen"  required id="assetFromId"' +
                    '  name="assetFrom" onchange="getAssetAlocation(),checkDisposalDate()" style="width: 97%;">');
                $("#assetFromId").append('<option value="">---SELECT---</option>');
                for (var i = 0; i < data.length; i++) {
                    if (i == 0) {
                        $("#assetFromId").append("<option selected value = '" + data[i][0] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");
                    } else {
                        $("#assetFromId").append("<option value = '" + data[i][0] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");
                    }
                }
                $("#assetFromId").append('  </select>');


                $("#divAssetTo").html('');
                $("#divAssetTo").html(' <select class="chosen"  required id="assetToId"' +
                    '  name="assetTo" onchange="getAssetAlocation(),checkDisposalDate()" style="width: 97%;">');
                $("#assetToId").append('<option value="">---SELECT---</option>');
                for (var i = 0; i < data.length; i++) {
                    $("#assetToId").append("<option selected value = '" + data[i][0] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");
                }
                $("#assetToId").append('  </select>');
                $(".chosen").chosen();

            }
        });
    }

</script>