<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="javax.xml.bind.DatatypeConverter" %>
<%@ page import="java.io.IOException" %>


<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2019-04-09
  Time: 12:00 PM
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

<div class="se-pre-con"></div>
<!-- Ends -->
<div class="row">
    <div class="col-md-12">
        <div class="page">
            <form name="QrForm" id="qr"  method="post">
                <div class="row" style="margin: 0">
                    <legend>Data of the Asset : ${assetCode}</legend>
                </div>
                <div>
                    <div class="row">
                        <label class="col-md-1"></label>
                        <label class="col-md-3">Asset Code</label>
                        <label>${assetCode}</label>
                    </div>
                    <div class="row">
                        <label class="col-md-1"></label>
                        <label class="col-md-3">Old Asset Code</label>
                        <label>${oldAssetCode}</label>
                    </div>
                    <div class="row">
                        <label class="col-md-1"></label>
                        <label class="col-md-3">Description</label>
                        <label>${assetDescription}</label>
                    </div>
                    <div class="row">
                        <label class="col-md-1"></label>
                        <label class="col-md-3">Amount</label>
                        <label>${assetPrice}</label>
                    </div>
                    <div class="row">
                        <label class="col-md-1"></label>
                        <label class="col-md-3">Registered Date</label>
                        <label>${assetRegisterdDate}</label>
                    </div>
                    <div class="row">
                        <label class="col-md-1"></label>
                        <label class="col-md-3">QR Image </label>
                        <img  class="img-responsive" style="width: 100px; height: 100px" src="data:image/jpg;base64, ${qrPath}"/>
                    </div>
                    <div class="row">
                        <label class="col-md-1"></label>
                        <label class="col-md-3">Image </label>
                        <img class="img-responsive" style="width: 200px; height: 200px; border:4px solid #9a9a9a; border-raduis:4px; " src="data:image/jpg;base64, ${DocPath}"/>
                    </div>
                    <div class="row">&nbsp;</div>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="co-md-3">
                        <button  id="back"
                                class="btn btn-primary"
                                style="width: 95px;height: 30px; font-size: 14px">BACK
                        </button></div>
                    </div>

                    <%--<div class="row">--%>
                        <%--<label class="col-md-2"></label>--%>
                        <%--<label class="col-md-3">Document</label>--%>
                        <%--<file name="images-content" path="C:\backup\C01S01D0100001.png" />--%>
                        <%--<a href="${qrPath}">link</a>--%>
                        <%--&lt;%&ndash;<label>${assetId}</label>&ndash;%&gt;--%>
                    <%--</div>--%>
                    <%--<img src="http://somro.lk/sapare.jpg">--%>
                    <%--<img src="E:\\t5.jpeg">--%>
                    <%--<label>${qrPath}</label>--%>





                </div>
            </form>
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

       $('.btnSubmit').click(function (e) {
           $(".se-pre-con").show();
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
                    window.location.href = "./viewAssetData";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Main Category Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./viewAssetData";
                })
            }
        }
    });

    $("#back").click(function (){
        parent.history.back();
        return false;
    });
</script>
