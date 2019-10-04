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
    <div class="col-md-7">
        <div class="page">
            <form name="QrForm" id="qr" action="${root}/option/generateQrCode" method="post">
                <div class="row" style="margin: 0">
                    <legend>Qr Generator For All Assets</legend>
                </div>
                    <div class="row" style="padding-top: 10px">
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-primary" style="width: 100%">Generate</button>
                        </div>
                        <div class="col-md-4"></div>
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

    //    $('.btnSubmit').click(function (e) {
    //        $(".se-pre-con").show();
    //    });


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
                    window.location.href = "./qrGenerator";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Main Category Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./qrGenerator";
                })
            }
        }
    });
</script>
