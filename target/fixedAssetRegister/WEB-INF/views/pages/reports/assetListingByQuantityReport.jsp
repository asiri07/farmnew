<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 12/2/2017
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.

  Modification Date     : 7/1/2019:
  User                  : Dilusha Kumari
  Purpose               : Modified Date Picker
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
                  action="${root}/report/genarateAssetListingByQuantity" target="_blank" method="post">

                <div class="row" style="margin: 0">
                    <legend>Assets Listing By Quantity</legend>
                </div>


                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Department From</div>
                            <div class="col-md-7">
                                <select style="width: 85%" name="branchFrom">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${departments}" var="department">
                                        <option value="${department.depId}">${department.depCode}
                                            - ${department.depDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-1" style="padding-top: 10px">To</div>
                            <div class="col-md-8">
                                <select style="width: 85%" name="branchTo">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${departments}" var="department">
                                        <option value="${department.depId}">${department.depCode}
                                            - ${department.depDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Section From</div>
                            <div class="col-md-7">
                                <select style="width: 85%" name="secFrom">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${sections}" var="section">
                                        <option value="${section.secId}">${section.secCode} - ${section.secDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">

                        <div class="row">
                            <div class="col-md-1" style="padding-top: 10px">To</div>
                            <div class="col-md-8">
                                <select style="width: 85%" name="secTo">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${sections}" var="section">
                                        <option value="${section.secId}">${section.secCode} - ${section.secDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Location From</div>
                            <div class="col-md-7">
                                <select style="width: 85%" name="locFrom">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${locations}" var="location">
                                        <option value="${location.locId}">${location.locCode}
                                            - ${location.locDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">

                        <div class="row">
                            <div class="col-md-1" style="padding-top: 10px">To</div>
                            <div class="col-md-8">
                                <select style="width: 85%" name="locTo">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${locations}" var="location">
                                        <option value="${location.locId}">${location.locCode}
                                            - ${location.locDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                    </div>
                </div>

                <div class="row" style="padding-top: 10px">
                    <div class="col-md-2">Date Wise</div>
                    <div class="col-md-1" style="margin-left: 4%"><input type="checkbox" style="float: left"
                                                                         name="dateWise"/></div>
                    <div class="col-md-10"></div>
                </div>


                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Date From</div>
                            <div class="col-md-7">
                                <div class='input-group date'>
                                    <input type='text' class="form-control" id="" style="height:35px"
                                           value="" name="fromDate"/>
                                    <span class="input-group-addon">
                                        <span style="font-size:24px;color:#3e71fb; width: 60px"
                                              class="fa fa-calendar"></span>
                                                </span>
                                </div>
                            </div>


                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-1" style="padding-top: 10px">To</div>
                            <div class="col-md-8">
                                <div class='input-group date'>
                                    <input type='text' class="form-control" style="height:35px"
                                           value="" name="toDate"/>
                                    <span class="input-group-addon">
                                        <span style="font-size:24px;color:#3e71fb; width: 60px"
                                              class="fa fa-calendar"></span>
                                                </span>
                                </div>
                            </div>

                            <div class="col-md-3"></div>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                        <div class="col-md-5" style="padding-top: 10px">Group By</div>
                        <div class="col-md-7">
                            <select required style="width: 85%" name="groupType">
                                <option value="">--SELECT--</option>
                                <option value="1">Main Category Wise</option>
                                <option value="2">Sub Category Wise</option>
                                <option value="3">Detail Category Wise</option>
                            </select>
                        </div></div>
                    </div>
                    <div class="col-md-6"></div>
                </div>
                <div class="row" style="padding-top: 10px">
                    <div class="col-md-4"></div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-3">
                            </div>
                            <div class="col-md-3">
                                <a type="button" class="btn btn-primary"
                                   style="width: 100%;height: 30px; font-size: 14px; margin-top: 2px"
                                   href="${root}/report/assetListingByQuantity">New</a>
                            </div>
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-primary"
                                        style="width: 100%;height: 30px; font-size: 14px;">Print
                                </button>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                    </div>

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
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
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