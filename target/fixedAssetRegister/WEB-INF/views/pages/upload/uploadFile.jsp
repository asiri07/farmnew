<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 12/19/2017
  Time: 11:42 PM
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
    <div class="col-md-7">
        <div class="page">
            <form action="${root}/upload/uploadXML" method="post" enctype="multipart/form-data">
                <div class="row" style="margin: 0">
                    <legend>Select Excel File To Upload:</legend>
                </div>
                <h4 style="color: #E50000"><label>Important !!</label> Please clear the Database before upload file.
                </h4>
                <c:if test="${pageType == 1}">
                    <h4 style="color: #0f69a8"><label>Uploaded Data :</label> ${count} </h4>
                </c:if>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Select Type</div>
                            <div class="col-md-6">
                                <select name="type" required style="width: 100%">
                                    <option value="">--SELECT--</option>
                                    <option value="1">Main Category</option>
                                    <option value="2">Sub Category</option>
                                    <option value="3">Detail Category</option>
                                    <option value="4">Asset</option>
                                    <option value="5">Asset Tranfer</option>
                                </select>
                            </div>
                            <div class="col-md-2"></div>

                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4">Select a file to upload:</div>
                            <div class="col-md-6">
                                <input type="file" name="file" size="5000"/>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-8">

                                <div class="row">
                                    <div class="col-md-4">
                                        <button type="submit" class="btn btn-primary" id="btnUpload"
                                                style="width: 100%; font-size: 14px">Upload File
                                        </button>
                                    </div>
                                    <div class="col-md-8">
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-5"></div>

</div>


<form id="editForm"></form>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<script type="text/javascript">
    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('#btnUpload').click(function () {
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
                window.location.href = "./uploadFile";
                swal({
                    title: 'success!',
                    type: 'success',
                    timer: 2000
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Upload unsuccessfully !!',
                    type: 'error',
                    timer: 2000
                })
            }
        }
    });


</script>
