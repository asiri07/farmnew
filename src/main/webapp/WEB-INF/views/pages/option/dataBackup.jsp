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
    <div class="col-md-8">
        <div class="page">
            <div class="row" style="margin: 0">
                <legend>Create Database Backup</legend>
            </div>
            <div style="margin-left: 10%">
                <div>
                    <button class="btn btn-primary btn-lg" type="button" id="btnBackup" onclick="generateBackup()">
                        Generate Backup
                    </button>

                </div>
                <div>
                    <label id="backupLabel" style="color: #2a62bc"></label>
                </div>

            </div>
        </div>
    </div>
    <div class="col-md-4">
    </div>
</div>
<script type="text/javascript" src="${root}/resources/js/download.js"></script>
<script type="text/javascript">
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
            "paging": false
        });
    });

    //    $(document).on('ready', function () {
    //        $("#input-folder-1").fileinput({
    //            browseLabel: 'Select Folder...'
    //        });
    //    });

    function generateBackup() {

        $(".se-pre-con").show();
        $.ajax({
            url: '${root}/option/generateBackup',
            success: function (data) {
                if (data != "") {
                    swal({
                        title: 'success!',
                        type: 'success',
                        timer: 1000
                    })
                    $("#backupLabel").html("Backup Location is : " + data);
                    $(".se-pre-con").fadeOut("slow");
                    //$("#btnDownload").removeAttr('disabled');
//
//                    var arr= new Uint8Array(data.length);
//                    data.split("").forEach(function(a,b){
//                        arr[b]=a.charCodeAt();
//                    });
//                    download( arr, "textUInt8Array.txt", "text/plain" );

                } else {
                    swal({
                        title: 'Error !',
                        text: 'Database Backup Unsuccessful !!',
                        type: 'error',
                        timer: 1000
                    })
                    //$("#btnDownload").removeAttr('disabled');
                }
            }
        });
    }

    function getfolder(e) {
        var files = e.target.files;
        var path = files[0].webki;
        var Folder = path.split("/");
        alert(Folder);
    }

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
                    window.location.href = "./dataBackup";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Company Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./dataBackup";
                })
            }
        }

    });


</script>