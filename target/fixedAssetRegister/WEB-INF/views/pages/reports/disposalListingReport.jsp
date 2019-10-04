<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 12/2/2017
  Time: 5:37 PM
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
            <form name=""
                  action="${root}/report/genarateDisposalListing" target="_blank" type="post">

                <div class="row" style="margin: 0">
                    <legend>Disposal Listing</legend>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Process Range</div>
                            <div class="col-md-7" style="margin-left: -30px">
                                <div class='input-group date'>
                                    <input type='text' class="form-control" required="required" value="" style="height:35px"
                                           name="fromcord"/>
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
                            <div class="col-md-7">
                                <div class='input-group date'>
                                    <input type='text' style="height:35px" class="form-control" required="required" value="" name="tocord"/>
                                    <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                                <%--<span style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                                </span>
                                </div>
                            </div>

                            <div class="col-md-5"></div>
                        </div>

                    </div>
                </div>
                <div class="row" style="padding-top: 10px">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4" style="padding-top: 3px">
                                <a type="button" class="btn btn-primary" style="width: 100%; font-size: 14px;"
                                        href="${root}/report/disposalListing">New</a>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary" style="width: 100%; font-size: 14px;">Print</button>
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

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
    });
    //
    // $(function () {
    //     $(".datePicker").datepicker(({
    //         dateFormat: 'yy/mm/dd',
    //         changeMonth: true,
    //         changeYear: true,
    //         showOtherMonths: true,
    //     }));
    //
    // });

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


</script>