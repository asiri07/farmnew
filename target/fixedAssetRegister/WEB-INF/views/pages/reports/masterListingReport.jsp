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
            <form name="" action="${root}/report/genarateMasterListing" target="_blank" id="formMasterListingReport"
                  method="post">

                <div class="row" style="margin: 0">
                    <legend>Master Listing</legend>
                </div>

                <div class="row">
                    <div class="col-md-1"  style="padding-top: 10px">Listing</div>
                    <div class="col-md-4">
                        <select id="" onchange="loadListing(this)" required style="width: 87%" name="">
                            <option value="">--SELECT--</option>
                            <option value="1">Main Category</option>
                            <option value="2">Sub Category</option>
                            <option value="3">Detail Category</option>
                            <option value="4">Company</option>
                            <option value="5">Department</option>
                            <option value="6">Section</option>
                            <option value="7">Location</option>
                            <option value="9">City</option>
                            <option value="10">Building</option>
                            <option value="11">Floor</option>
                            <option value="12">Room</option>
                            <option value="13">Disposal Category</option>
                            <option value="14">Analysis</option>
                        </select>
                    </div>
                    <div class="col-md-7"></div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-2"  style="padding-top: 10px">From</div>

                            <div class="col-md-8" id="divAsset">
                                <select class="chosen" required
                                        style="width: 88%">
                                    <option value="" style="padding-top: 10px">---SELECT---</option>
                                </select>
                            </div>

                            <%--<div class="col-md-7">--%>
                                <%--<select id="fromId" style="width: 100%" name="fromCode">--%>
                                    <%--<option value="">---SELECT---</option>--%>

                                <%--</select>--%>
                            <%--</div>--%>
                            <div class="col-md-3"></div>
                        </div>
                    </div>
                    <div class="col-md-6">

                        <div class="row">
                            <div class="col-md-2"  style="padding-top: 10px">To</div>
                            <div class="col-md-8" id="divAssetTo">
                                <select class="chosen" required
                                        style="width: 87%;padding-top: 10px">
                                    <option value="" style="padding-top: 10px">---SELECT---</option>
                                </select>
                            </div>

                            <%--<div class="col-md-7">--%>
                                <%--<select id="toId" style="width: 100%" name="toCode">--%>
                                    <%--<option value="">---SELECT---</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                            <div class="col-md-3"></div>
                        </div>

                    </div>
                </div>

                <div class="row" style="padding-top: 10px">

                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-4">
                                <a type="button" class="btn btn-primary"
                                   style="width: 100%;height: 30px; font-size: 14px;margin-top: 2px"
                                   href="${root}/report/masterListing">New</a>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary"
                                        style="width: 100%;height: 30px; font-size: 14px; ">Print
                                </button>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                    </div>
                    <div class="col-md-6"></div>
                </div>
                <input type="hidden" name="listingType" id="listingTypeID"/>
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

    function loadListing(id) {
        var listing = id.value;
        $("#fromId").empty();
        $("#toId").empty();
        $("#listingTypeID").val("");
        $("#listingTypeID").val(listing);
        $.ajax({
            url: '${root}/report/loadListing/' + listing,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    // $("#fromId").append(
                    //     "<option value = '" + data[i].listingId + "'>" + data[i].listingName + " - " + data[i].description
                    //     + "</option>");
                    // $("#toId").append(
                    //     "<option value = '" + data[i].listingId + "'>" + data[i].listingName + " - " + data[i].description
                    //     + "</option>");



                    $("#divAsset").html('');
                    $("#divAsset").html(' <select class="chosen"  required id="assetFromId"' +
                        '  name="fromCode" onchange="getAssetAlocation(),checkDisposalDate()" style="width: 88%;">');
                    $("#assetFromId").append('<option value="">---SELECT---</option>');
                    for (var i = 0; i < data.length; i++) {
                        if (i == 0) {
                            $("#assetFromId").append("<option selected value = '" + data[i].listingId + "'>" + data[i].listingName + " - " + data[i].description + "</option>");
                        } else {
                            $("#assetFromId").append("<option value = '" + data[i].listingId + "'>" + data[i].listingName + " - " + data[i].description + "</option>");
                        }
                    }
                    $("#assetFromId").append('  </select>');

                    $("#divAssetTo").html('');
                    $("#divAssetTo").html(' <select class="chosen"  required id="assetToId"' +
                        '  name="toCode" onchange="getAssetAlocation(),checkDisposalDate()" style="width: 88%;">');
                    $("#assetToId").append('<option value="">---SELECT---</option>');
                    for (var i = 0; i < data.length; i++) {
                        $("#assetToId").append("<option selected value = '" + data[i].listingId + "'>" + data[i].listingName + " - " + data[i].description + "</option>");
                    }
                    $("#assetToId").append('  </select>');
                    $(".chosen").chosen();

                }
        }
        });
    }
</script>