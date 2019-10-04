<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 9/28/2017
  Time: 2:25 AM
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
<div class="col-md-12 ">
    <form name="existingAssetForm" id="existingAssetFormID"
          action="${root}/data/saveExistingAssets" method="post">
        <div class="row" style="margin: 0">
            <legend>Register Existing Assets</legend>
        </div>
        <div class="row page">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-7"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Reference No.</div>
                            <div class="col-md-8"><input type="text" width="100" id="" name=""
                                                         style="width: 100%"/></div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-7"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Register Date</div>
                            <%--<div class="col-md-8">--%>
                                <%--<div class='input-group date'>--%>
                                    <%--<input type='text' required="required" onchange="checkDisposalDate()"--%>
                                           <%--class="form-control" name="date"/>--%>
                                    <%--<span class="input-group-addon">--%>
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--&lt;%&ndash;<span class="glyphicon glyphicon-calendar"></span>&ndash;%&gt;--%>
                                    <%--</span>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="col-md-7">

                                <input type="text" onchange="checkDisposalDate()" autocomplete="off" required class="form-control"
                                       id="datePicker"
                                       style=" height: 35px"
                                       name="date"/>

                            </div>
                            <div class="col-md-1">
                                <span style="text-align:center;font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-7"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Old Asset Code</div>
                            <div class="col-md-8"><input type="text" width="100" name=""
                                                         style="width: 100%"/>
                            </div>
                        </div>
                    </div>


                    <div class="row">

                        <div class="col-md-5" style="border: 1px solid;padding-bottom: 10px">
                            <label>From</label>
                            <div class="row">
                                <div class="col-md-4"  style="padding-top: 10px">Department</div>
                                <div class="col-md-8">
                                    <input type="text" required width="100" readonly id="departmentID" name="fromDep"
                                           style="width: 100%"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4"  style="padding-top: 10px">Section</div>
                                <div class="col-md-8">
                                    <input type="text" required width="100" readonly id="sectionID" name="fromSec"
                                           style="width: 100%"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4"  style="padding-top: 10px">Location</div>
                                <div class="col-md-8">
                                    <input type="text" required width="100" readonly id="locationID" name="fromLoc"
                                           style="width: 100%"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4"  style="padding-top: 10px">Employee No.</div>
                                <div class="col-md-8">
                                    <input type="text" required width="100" name="fromEmpNo"
                                           style="width: 100%"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2"></div>

                        <div class="col-md-5" style="border: 1px solid;padding-bottom: 10px">
                            <label>To</label>
                            <div class="row">
                                <div class="col-md-4"  style="padding-top: 10px">Department</div>
                                <div class="col-md-8">
                                    <select required name="" id="depID" onchange="loadSections()" style="width: 100%">
                                        <option value="">---SELECT---</option>
                                        <c:forEach var="departments" items="${departments}">
                                            <option value="${departments.depId}">${departments.depCode}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="toDep" id="depCode">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4"  style="padding-top: 10px">Section</div>
                                <div class="col-md-8">
                                    <select required name="" id="secID" onclick="loadLocation()" style="width: 100%">
                                        <option value="">---SELECT---</option>
                                    </select>
                                    <input type="hidden" name="toSec" id="secCode">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4"  style="padding-top: 10px">Location</div>
                                <div class="col-md-8">
                                    <select required onchange="checkAssetCode()" name="toLoc" id="locID"
                                            style="width: 100%">
                                        <option value="">---SELECT---</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4"  style="padding-top: 10px">Employee No.</div>
                                <div class="col-md-8">
                                    <input type="text" onkeyup="checkAssetCode()" required width="100" name="toEmpNo"
                                           style="width: 100%"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">

                        </div>
                        <div class="col-md-7">
                            <div class="row" style="padding-top: 10px">
                                <div class="col-md-3"></div>
                                <div class="col-md-3">
                                    <button type="button" onclick="loadBuklTransfer()" class="btn btn-primary"
                                            style="width: 100%">Bulk Transfer
                                    </button>
                                </div>
                                <div class="col-md-3">
                                    <a class="btn btn-primary" style="width: 100%; margin-top:3px;" href="${root}/data/transfers">New</a>
                                </div>
                                <div class="col-md-3">
                                    <button type="submit" id="btnSubmit" class="btn btn-primary" style="width: 100%">
                                        Transfer
                                    </button>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<form action="${root}/data/loadBulkTransfer" id="bulkTransferForm" method="get"></form>
<script type="text/javascript">
    $('select[multiple]').multiselect({
        columns: 5,
        placeholder: 'Select options'
    });

    $(".chosen").chosen();
    $('.tabgroup > div').hide();
    $('.tabgroup > div:first-of-type').show();
    $('.tabs a').click(function (e) {
        e.preventDefault();
        var $this = $(this),
            tabgroup = '#' + $this.parents('.tabs').data('tabgroup'),
            others = $this.closest('li').siblings().children('a'),
            target = $this.attr('href');
        others.removeClass('active');
        $this.addClass('active');
        $(tabgroup).children('div').hide();
        $(target).show();

    });


//     $(function () {
//         $('.date').datepicker({
//             format: 'yyyy/mm/dd',
//             autoclose: true,
// //            endDate: '+0d'
//         });
//
//     });


    $(function () {
        $("#datePicker").datepicker(({
            dateFormat: 'yy/mm/dd',
            changeMonth: true,
            changeYear: true,
            showOtherMonths: true,
        }));

    });




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

    function checkAssetCode() {
        var fromDepId = $("#departmentID").val();
        var fromSecId = $("#sectionID").val();
        var fromLocId = $("#locationID").val();
        var formAssetCode = fromDepId + fromSecId + fromLocId;

        var toDepId = $("#depID").find("option:selected").text();
        var toSecId = $("#secID").find("option:selected").text();
        var toLocId = $("#locID").find("option:selected").text();
        var toAssetCode = toDepId + toSecId + toLocId;

        if (formAssetCode === toAssetCode) {
            swal({
                title: 'Warning',
                text: 'Can not Transfer Asset with in same Location !!',
                type: 'warning',
                timer: 1000
            })
            $("#btnSubmit").prop('disabled', true);
        } else {
            $("#btnSubmit").prop('disabled', false);
        }
    }

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Transfer success!',
                    type: 'success',
                    timer: 1000
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                })
            }
        }

    });

    function getAssetAlocation() {
        var assetId = $("#assetCodeId").find("option:selected").val();
        if (assetId > 0) {
            $.ajax({
                url: '${root}/data/getAssetAlocation/' + assetId,
                success: function (data) {
                    if (data != "") {
                        var subCat = data.split("-");
                        $("#companyID").val("");
                        $("#departmentID").val("");
                        $("#sectionID").val("");
                        $("#locationID").val("");
                        $("#companyID").val(subCat[0].toUpperCase());
                        $("#departmentID").val(subCat[1].toUpperCase());
                        $("#sectionID").val(subCat[2].toUpperCase());
                        $("#locationID").val(subCat[3].toUpperCase());
                        $("select#depID")[0].selectedIndex = 0;
                        $("#secID").empty();
                        $("#locID").empty();

                    }
                }
            });
        }

    }

    //    function loadBulkAsset(asserId) {
    //
    //        var MYdata=[{"Value":"1","ValueText":"name1"}
    //            ,{"Value":"2","ValueText":"name2"}
    //            ,{"Value":"3","ValueText":"name3"}];
    //
    //
    //        $('#bulkAsset').html(function(){
    //            return $.map(MYdata, function(v) {
    //                return "<option id='"+ v.Value +"'>" + v.Value + "-" + v.ValueText +"</option>";
    //            }).join('');
    //        });
    //
    //    }

    function loadBuklTransfer() {
        var assetId = $("#assetCodeId").find("option:selected").val();
        if (assetId > 0) {
            $('<input />').attr('type', 'hidden')
                .attr('name', "assetId")
                .attr('value', assetId)
                .appendTo('#bulkTransferForm');
            $("#bulkTransferForm").submit();
        } else {
            $('#divAsset').addClass("txtError").focus();
            swal({
                title: 'Warning',
                text: 'Please select for main asset !!',
                type: 'warning',
                timer: 1000
            })

        }

    }

    function loadSections() {
        $("#secID").empty();
        var departmentId = $("#depID").find("option:selected").val();
        var departmentCode = $("#depID").find("option:selected").text();

        $("#depCode").val(departmentCode);
        $.ajax({
            url: '${root}/allocation/getSections/' + departmentId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#secID").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select department !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }

    function loadLocation() {
        $("#locID").empty();
        var sectionId = $("#secID").find("option:selected").val();
        var sectionCode = $("#secID").find("option:selected").text();
        $("#secCode").val(sectionCode);
        $.ajax({
            url: '${root}/allocation/getLocations/' + sectionId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#locID").append(
                        "<option value = '" + subCat[1] + "'>" + subCat[1]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select section !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }


</script>