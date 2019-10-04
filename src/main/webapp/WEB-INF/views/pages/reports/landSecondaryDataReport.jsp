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
            <form name="" action="${root}/report/generateLandSecondaryData" target="_blank" id="" method="post">

                <div class="row" style="margin: 0">
                    <legend>Land Secondary Data</legend>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Assets From</div>
                            <div class="col-md-8">
                                <select style="width: 95%" required name="assetFrom">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="asset" items="${assets}">
                                        <option value="${asset.asId}">${asset.asCode}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Assets To</div>
                            <div class="col-md-8">
                                <select style="width: 95%" required name="assetTo">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="asset" items="${assets}">
                                        <option value="${asset.asId}">${asset.asCode}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="row" style="padding-top: 10px">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <button type="button" class="btn btn-primary" style="width: 100%">Help</button>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary" style="width: 100%">Ok</button>
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
           url : '${root}/report/loadListing/' + listing,
           success:function (data) {
               for (var i=0;i<data.length;i++) {
                   $("#fromId").append(
                       "<option value = '" + data[i].listingId + "'>" + data[i].listingName + " - "+ data[i].description
                       + "</option>");
                   $("#toId").append(
                       "<option value = '" + data[i].listingId + "'>" + data[i].listingName + " - "+ data[i].description
                       + "</option>");

               }
           }
       });
   }
</script>