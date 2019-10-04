<%--
Created by IntelliJ IDEA.
User: MsD
Date: 10/7/2017
Time: 7:46 AM
To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

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
        background: url(${root}/resources/images/Preloader_3.gif) center
        no-repeat #fff;
        opacity: .8;
    }
</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="row">
    <div class="col-md-6">
        <div class="page">
            <form name="priorRegForm"
                  action="${root}/data/savePriorReg" method="post">

                <div class="row" style="margin: 0">
                    <legend>Asset Prior Registering</legend>
                </div>

                <div class="row">

                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Asset Prior Code</div>
                            <div class="col-md-5">
                                <input type="text" name="asPriorCode" style="width: 100%" />
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Main Category</div>
                            <div class="col-md-5">
                                <select id="mainCategoryID" onblur="loadSubCategory()" name=""
                                        style="width: 100%">
                                    <option selected="selected">---SELECT---</option>
                                    <c:forEach var="mainCats" items="${mainCategory}">
                                        <option value="${mainCats.mcatId}">${mainCats.mcatCode}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>

                        </div>

                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Sub Category</div>
                            <div class="col-md-5">
                                <select id="subCategoryListID" onblur="loadDetailCategory()" name=""
                                        style="width: 100%">
                                    <option>---SELECT---</option>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                            <%--https://codepen.io/oknoblich/pen/EAzBc--%>
                        </div>

                        <div class="row" >
                            <div class="col-md-4"  style="padding-top: 10px">Detail Category Code</div>
                            <div class="col-md-5">
                                <select id="detailCategoryListID"  name="assetCatergoryDetail.dcatId"
                                        style="width: 100%">
                                    <option>---SELECT---</option>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Registered Date</div>
                            <%--<div class="col-md-5">--%>
                                <%--<div class='input-group date'>--%>
                                    <%--<input type='text' class="form-control" name="regDate"/>--%>
                                    <%--<span class="input-group-addon">--%>
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                                <%--&lt;%&ndash;<span class="glyphicon glyphicon-calendar"></span>&ndash;%&gt;--%>
                                                <%--</span>--%>
                                <%--</div>--%>
                            <%--</div>--%>


                            <div class="col-md-4">

                                <input type="text" onchange="checkDisposalDate()" autocomplete="off" required class="form-control"
                                       id="datePicker"
                                       style=" height: 35px"
                                       name="regDate"/>

                            </div>
                            <div class="col-md-1">
                                <span style="text-align:center;font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">No of Units</div>
                            <div class="col-md-5">
                                <input type="text" name="noOfUnits" style="width: 100%"></input>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Amount</div>
                            <div class="col-md-5">
                                <input type="text" name="amount" style="width: 100%"></input>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Voucher No.</div>
                            <div class="col-md-5">
                                <input type="text" name="voucherNo" style="width: 100%"></input>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">GRN No.</div>
                            <div class="col-md-5">
                                <input type="text" name="grnNo" style="width: 100%"></input>
                            </div>
                            <div class="col-md-3"></div>
                        </div>


                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-8">

                                <div class="row">
                                    <%--<div class="col-md-3">--%>
                                        <%--<button type="button" class="btn btn-primary" style="width: 100%">Help</button>--%>
                                    <%--</div>--%>
                                    <div class="col-md-4">
                                        <a class="btn btn-primary" style="width: 100%; margin-top:3px;" href="${root}/data/priorRegister">New</a>
                                    </div>
                                    <div class="col-md-4">

                                        <button type="submit" class="btn btn-primary" style="width: 100%">Save</button>
                                    </div>
                                    <div class="col-md-4"></div>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-6">
        <table id="dataTable" class="display compact" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>Prior Code</th>
                <th>Registered Date</th>
                <th>No of Units</th>
                <th>Amount</th>
                <th>Voucher No</th>
                <th>GRN No</th>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="priorReg" items="${priorReg}">
                <tr>
                    <td>${priorReg.asPriorCode}</td>
                    <td>${priorReg.regDate}</td>
                    <td>${priorReg.noOfUnits}</td>
                    <td>${priorReg.amount}</td>
                    <td>${priorReg.voucherNo}</td>
                    <td>${priorReg.grnNo}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>

    </div>
</div>



<input type="hidden" id="pageType" value="${pageType}" />
<input type="hidden" id="status" value="${status}" />

<script type="text/javascript">
    $(window).load(function() {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('.btnSubmit').click(function() {
        $(".se-pre-con").show();
    });

    $(function () {
        $("#datePicker").datepicker(({
            dateFormat: 'yy/mm/dd',
            changeMonth: true,
            changeYear: true,
            showOtherMonths: true,
        }));

    });

    $(document).ready(function() {
        $('#dataTable').DataTable({
            "scrollY" : "300px",
            "scrollCollapse" : true,
            "paging" : false
        });
    });

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
            endDate: '+0d'
        });
    });


    $(function() {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        if (pageType == 1) {

            if (status == 'true') {
                swal({
                    title : 'Success!',
                    type : 'success',
                    timer : 1000
                }).then(function () {
                    window.location.href = "./asset";
                })
            } else {
                swal({
                    title : 'Error !',
                    text : 'Main Category Added Fail !!',
                    type : 'error',
                    timer : 1000
                }).then(function () {
                    window.location.href = "./asset";
                })
            }
        }

    });


    function loadSubCategory() {
        var mainCat = $("#mainCategoryID").find("option:selected").val();
        $.ajax({
            url: '${root}/category/getSubCategoryList/' + mainCat,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#subCategoryListID").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Select Main Category...!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }


    function loadDetailCategory() {
        var subCat = $("#subCategoryListID").find("option:selected").val();
        $.ajax({
            url: '${root}/category/getDetailCategoryList/' + subCat,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var det = data[i];
                    var detailCat = det.split("-");
                    $("#detailCategoryListID").append(
                        "<option value = '" + detailCat[0] + "'>" + detailCat[1]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select subcategory !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }
</script>
