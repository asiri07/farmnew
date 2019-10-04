<%--
  Created by IntelliJ IDEA.
  User: mahendra
  Date: 12/15/17
  Time: 12:37 PM
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
<div class="col-md-12 page">
    <form name="transferForm" id="transferFormID"
          action="${root}/data/saveTransfers" method="post">

        <div class="row" style="margin: 0">
            <legend>Bulk Transfers</legend>
        </div>

        <div class="row">
            <div class="col-md-12">

                <div class="row" style="padding-bottom: 15px">
                    <div class="col-md-2"></div>
                    <div class="col-md-9" style="border: 1px solid">
                        <div class="row">
                            <div class="col-md-1"><input type="radio" checked="checked" style="width: 100%" value="1"
                                                         name="type"></div>
                            <div class="col-md-3"><label>Internally</label></div>

                            <div class="col-md-1"><input type="radio" style="width: 100%" value="2" name="type"></div>
                            <div class="col-md-3"><label>From Company to Outside</label></div>

                            <div class="col-md-1"><input type="radio" style="width: 100%" value="3" name="type"></div>
                            <div class="col-md-3"><label>From Outside to Company</label></div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Date</div>
                            <div class="col-md-7">
                                <div class='input-group date'>
                                    <input type='text' required="required" onchange="checkDisposalDate()"
                                           class="form-control" name="date" style="height:35px"/>
                                    <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                                </div>
                            </div>


                        </div>
                    </div>

                    <div class="col-md-2"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-8">
                                <input type="text" required value="${asset.asCode}" readonly id=""
                                       style="width: 100%"/>
                                <input type="hidden" name="assertId" id="assetCodeId" value="${asset.asId}"/>
                            </div>
                        </div>
                    </div>
                </div>
<div class="row">&nbsp</div>
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
                            <div class="col-md-4" style="padding-top: 10px">Section</div>
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
                                <input type="text" width="100" name="fromEmpNo"
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
                            <div class="col-md-4"  style="padding-top: 10px"> Location</div>
                            <div class="col-md-8">
                                <select required onchange="checkAssetCode()" name="toLoc" id="locID"
                                        style="width: 100%">
                                    <option value="">---SELECT---</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Employee No.</div>
                            <div class="col-md-8">
                                <input type="text" onkeyup="checkAssetCode()" width="100" name="toEmpNo"
                                       style="width: 100%"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">&nbsp</div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 30px">Issued To</div>
                            <div class="col-md-8"><textarea name="issuedTo" style="width: 100%"></textarea></div>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 30px">Comments</div>
                            <div class="col-md-8"><textarea name="comments" style="width: 100%"></textarea></div>
                        </div>
                    </div>
                </div>

                <div class="row"><div class="col-md-5" ><label>Select Bulk Assets</label></div><div class="col-md-7"></div></div>
                <div class="row" id="divAssets">
                    <div class="col-md-11">
                        <select name="multiAssets" multiple="multiple" id="bulkAsset" required class="active ">
                            <c:forEach var="assets" items="${assets}">
                                <option value="${assets.asId}">${assets.asCode}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                    </div>
                    <div class="col-md-7">
                        <div class="row" style="padding-top: 10px">
                            <div class="col-md-4">
                            </div>
                            <div class="col-md-4" style="margin-top: 2px">
                                <a class="btn btn-primary" style="width: 100%" href="${root}/data/transfers">Exit</a>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" id="btnSubmit" class="btn btn-primary" style="width: 100%">
                                    Bulk Transfer
                                </button>
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

<script type="text/javascript">
    $('select[multiple]').multiselect({
        columns: 5,
        placeholder: 'Select options'
    });
    //
    //    $(".chosen").chosen();
    //    $('.tabgroup > div').hide();
    //    $('.tabgroup > div:first-of-type').show();
    //    $('.tabs a').click(function (e) {
    //        e.preventDefault();
    //        var $this = $(this),
    //            tabgroup = '#' + $this.parents('.tabs').data('tabgroup'),
    //            others = $this.closest('li').siblings().children('a'),
    //            target = $this.attr('href');
    //        others.removeClass('active');
    //        $this.addClass('active');
    //        $(tabgroup).children('div').hide();
    //        $(target).show();
    //
    //    });

    // $(function () {
    //     $('.date').datepicker({
    //         format: 'yyyy/mm/dd',
    //         autoclose: true,
    //         endDate: '+0d'
    //     });
    //
    // });
    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
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
                    title: 'Transfer Success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./transfers";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./transfers";
                })
            }
        }

    });

    $("#btnSubmit").click(function () {
        var ids = $("#bulkAsset").val();

        if (ids == null) {
            $('#divAssets').addClass("txtError").focus();
        } else {
            $('#divAssets').removeClass("txtError");
        }
    });


    $(function () {
        var assetId = $("#assetCodeId").val();
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
    });

    function loadSections() {
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

    function checkDisposalDate() {
        var dat = $("input[name=date]").val();
        var assetId = $("#assetCodeId").val();
        if (dat != "" && assetId > 0) {
            var date = formatDate(dat);
            $.ajax({
                url: '${root}/data/checkDisposalDate',
                data: {date: date, assetId: assetId},
                success: function (data) {
                    if (data > 0) {
                        var msg;
                        switch (data) {
                            case 1:
                                msg = "Please change Transfers date";
                                break;
                            case 2:
                                msg = "Please select date, after the Asset purchase or register date";
                                break;
                            case 3 :
                                msg = "Please select date, after the Asset transfer date";
                                break;
                            case 4 :
                                msg = "Please select date, after the Asset Damage date";
                                break;
                            case 5 :
                                msg = "Please select date, after the Asset Improvement date";
                        }
                        swal({
                            title: 'Warning',
                            text: msg,
                            type: 'warning'
                        })
                        $("input[name=date]").val("");
                    }

                }

            });
        }
    }

</script>