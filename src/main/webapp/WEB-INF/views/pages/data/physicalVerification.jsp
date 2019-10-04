<%--
    Created by IntelliJ IDEA.
    User: MsD
     Date: 9/28/2017
    Time: 4:17 AM
    To change this template use File | Settings | File Templates.

    Modification Date     : 6/25/2019
    User                  : Dilusha Kumari
    Purpose               : Modified Asset Code loading method
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

    .ui-autocomplete {
        max-height: 200px;
        max-width: 350px;
        overflow-y: auto;
        /* prevent horizontal scrollbar */
        overflow-x: hidden;
        /* add padding to account for vertical scrollbar */
        padding-right: 20px;
    }
    .chosen-container .chosen-results {
    max_selected_options: 1;
        max-height:200px;
    mso-element: 5;
}
</style>


<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="col-md-12 page">
    <form name="damageForm"
          action="${root}/data/saveVerification" method="post">
        <div class="row" style="margin: 0">
            <legend>Asset Physical Verification</legend>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4"  style="padding-top: 10px">Location Code</div>
                    <div class="col-md-8">
                        <input id="locCodeId" name="" style="height:35px;width:100%;" type="text" autocomplete="off" onchange="loadAsset(),loadExistingVerifications()"
                               class="form-control"
                               placeholder="Enter 1st character">
                        <input id="getId" name="locationId" hidden>

                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-2"  style="padding-top: 10px">Date</div>
                    <div class="col-md-7">
                        <div class='input-group date' style="width: 100%">
                            <input type='text' onchange="loadExistingVerifications()" required class="form-control"
                                   name="date" style="height:35px"/>
                            <span class="input-group-addon">
                                <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-md-2"  style="padding-top: 10px">Asset Code</div>
            <div class="col-md-4" id="divAsset" style="padding-left: 17px">
                <select style="width: 101%;"   class="chosen-select"  id="temp">
                    <option value="">---SELECT---</option>
                </select>
            </div>
            <div class="col-md-6"></div>
        </div>


        <div class="row" style="padding-left: 10px;padding-right: 10px">
            <table id="tblVerification" style="overflow-y: auto" class="responstable" width="100%"
                   cellspacing="0">
                <thead>
                <tr>
                    <th style="width: 170px">Asset Code</th>
                    <th>Description</th>
                    <th style="width: 150px">Asset Physical Balance</th>
                    <th style="width: 100px">Remove</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>


        <div class="row" style="margin-top: 20px">
            <div class="col-md-2"></div>
            <div class="col-md-7">
                <div class="row" style="padding-top: 10px">
                    <div class="col-md-3">
                        <button type="button" id="btnbeforePrint" onclick="runBeforePhysicalVerificationReport()"
                                class="btn btn-primary"
                                style="width: 120px; font-size: 14px;">Before Print
                        </button>
                    </div>
                    <div class="col-md-3">
                        <button type="button" id="btnPrint" onclick="runPhysicalVerificationReport()"
                                class="btn btn-primary"
                                style="width: 120px ; font-size: 14px;">Print
                        </button>
                    </div>
                    <div class="col-md-3" style="margin-top: 2px">
                        <a class="btn btn-primary" style="width: 120px;  margin-top: 1px ; font-size: 14px;"
                           href="${root}/data/physicalVerification">New</a>
                    </div>
                    <div class="col-md-3" ; style="margin-top: 0px">
                        <button type="submit" class="btn btn-primary" id="btnSave"
                                style="width: 120px;   margin-top: 1px ; font-size: 14px;">Save
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
    </form>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<form action="${root}/report/genaratePhysicalVerification" id="formPhysicalVerification" target="_blank" method="post">
    <input type="hidden" id="locationId" name="locationId"/>
    <input type="hidden" id="locationName" name="locationName"/>
</form>

<form action="${root}/report/genarateBeforePhysicalVerification" id="formBeforePhysicalVerification" target="_blank"
      method="post">
    <input type="hidden" id="locatiId" name="locationId"/>
    <input type="hidden" id="locatiName" name="locationName"/>
</form>
<script type="text/javascript">

    $(".chosen-select").chosen();


    document.getElementById('locCodeId').setAttribute('maxlength', LOCATION_CODE_LENGTH);

    $("#btnbeforePrint").attr("disabled", true);
    $("#btnPrint").attr("disabled", true);

    var assetId = [];


    // $(function () {
    //     $("#datePicker").datepicker(({
    //         dateFormat: 'yy/mm/dd',
    //         changeMonth: true,
    //         changeYear: true,
    //         showOtherMonths: true,
    //         yearRange:'c-200:c+200',
    //     }));
    //
    // });
    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
            endDate: '+0d'
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

    function runPhysicalVerificationReport() {

        var locCodeId = $("#getId").val();
        var locCode = $("#locCodeId").val();
        if (locCodeId != "") {
            $("#locationId").val(locCodeId);
            $("#locationName").val(locCode);
            $("#formPhysicalVerification").submit();
        } else {
            swal({
                title: 'Warning',
                text: 'Please select Location Code !!',
                type: 'warning',
                timer: 1000
            })
        }
    }

    function runBeforePhysicalVerificationReport() {

        var locCodeId = $("#getId").val();
        var locCode = $("#locCodeId").val();

        if (locCodeId != "") {
            $("#locatiId").val(locCodeId);
            $("#locatiName").val(locCode);
            $("#formBeforePhysicalVerification").submit();
        } else {
            swal({
                title: 'Warning',
                text: 'Please select Location Code !!',
                type: 'warning',
                timer: 1000
            })
        }
    }

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./physicalVerification";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./physicalVerification";
                })
            }
        }
    });

    function loadAsset() {
        $("#tblVerification").find("tr:not(:first)").remove();
        $("#assetId").find("option:not(:first)").remove();

        var locCodeId =$("#getId").val();

        if (locCodeId > 0) {
            $.ajax({
                url: '${root}/data/getAssetByLocation/' + locCodeId,
                success: function (data) {

                    $("#divAsset").html('');
                    $("#divAsset").html(' <select class="chosen" select id="assetId" style="width: 100%" onchange="loadAssetTable()" name="asset" ">');
                    $("#assetId").append('<option value="">---SELECT---</option>');
                    for (var i = 0; i < data.length; i++) {
                        $("#assetId").append("<option value = '" + data[i][0] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");
                    }
                    $("#assetId").append('  </select>');
                    $(".chosen").chosen();
                }
            });
        }



    }

    function checkAssetCodeValid() {
        var assetCode = $('input[name=assetCodeUser]').val();
        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {
                    // $('#assetCodeUserId').removeClass('is-invalid');
                    // $('#assetCodeId').val(data);
                    $('#btnSave').attr("disabled", false);
                    //  loadAssetTable();
                } else {
                    // $('#assetCodeId').val(0);
                    $('#btnSave').attr("disabled", true);
                    // $('#assetCodeUserId').addClass('is-invalid');
                }
            }
        });
    }

    jQuery.fn.putCursorAtEnd = function () {
        return this.each(function () {
            var $el = $(this),
                el = this;
            if (!$el.is(":focus")) {
                $el.focus();
            }
            if (el.setSelectionRange) {
                var len = $el.val().length * 2;
                setTimeout(function () {
                    el.setSelectionRange(len, len);
                }, 1);
            } else {
                $el.val($el.val());
            }
            this.scrollTop = 999999;
        });
    };

    function loadAssetTable() {

        var detaiCatId = $("#assetId").find("option:selected").val();
        var text = $("#assetId").find("option:selected").text();
        var detaiCat = text.substring(0, ASSET_CODE_LENGTH).toUpperCase();

        var date = $("input[name=date]").val();

        if (date != "") {
            if (detaiCatId > 0) {
                $.ajax({
                    url: '${root}/data/getAssetDetailCat/' + detaiCatId,
                    success: function (data) {

                        var markup = "<tr><td><input type='hidden' name='detailId' value='" + detaiCatId + "'/> <input type='text' readonly style='width: 95%' value='" + detaiCat + "' name='detailCode'></td><td><input type='text' readonly style='width: 97%' value='" + data + "' name='description'></td><td><input type='text' style='width: 98%' name='detailBalance'></td><td style='padding-left: 20px'><a onclick=deleteRow('tblVerification',this)><i  class=\"fa fa-trash-o fa-3x\"></i></a></td></tr>";

                        $("table tbody").append(markup);
                        // $("#assetId option[value='" + detaiCatId + "']").remove()
                        // $("#assetId").find("option:selected").remove();
                    }
                });

            }
        } else {
            $("input[name=date]").focus();

            swal({
                title: 'Warning',
                text: 'Please select Date !!',
                type: 'warning',
                timer: 1000
            })
        }
    }

    function deleteRow(tblName, link) {
        var tbl = document.getElementById(tblName);
        var tableRow = link.parentElement.parentElement;
        tbl.deleteRow(tableRow.rowIndex);
    }

    function loadExistingVerifications() {
        $("#btnbeforePrint").attr("disabled", false);
        $("#btnPrint").attr("disabled", false);
        $("#tblVerification").find("tr:not(:first)").remove();
        var date = $("input[name=date]").val();
        var today = formatDate(date);
        var locCodeId = $("#getId").val();
        if (locCodeId != null) {
            if (date != "") {
                $.ajax({
                    url: '${root}/data/loadExitsVerifications/' + today + '/' + locCodeId,
                    success: function (data) {
                        assetId = [];
                        if (data != "") {
                            for (var i = 0; i < data.length; i++) {
                                var markup = "<tr><td><input type='hidden' name='detailId' value='" + data[i].detailId + "'/> <input type='text' readonly style='width: 95%' value='" + data[i].detailCode + "' name='detailCode'></td><td><input type='text' readonly style='width: 97%' value='" + data[i].description + "' name='description'></td><td><input type='text' style='width: 98%' value='" + data[i].balance + "' name='detailBalance'></td><td style='padding-left: 20px'><a onclick=deleteRow('tblVerification',this)><i  class=\"fa fa-trash-o fa-3x\"></i></a></td></tr>";
                                $("table tbody").append(markup);
                                assetId.push(data[i].detailId);
                                // $("#assetId option[value='" + data[i].detailId + "']").remove();
                            }
                            $("#btnSave").html('Update');
                        } else {
                            $.ajax({
                                url: '${root}/data/loadAssetSearchData/' + today + '/' + locCodeId,
                                success: function (data) {
                                    if (data != "") {
                                        for (var i = 0; i < data.length; i++) {
                                            var markup = "<tr><td><input type='hidden' name='detailId' value='" + data[i][2] + "'/> <input type='text' readonly style='width: 95%' value='" + data[i][0] + "' name='detailCode'></td><td><input type='text' readonly style='width: 97%' value='" + data[i][1] + "' name='description'></td><td><input type='text' style='width: 98%' value='" + data[i][3] + "' name='detailBalance'></td><td style='padding-left: 20px'><a onclick=deleteRow('tblVerification',this)><i  class=\"fa fa-trash-o fa-3x\"></i></a></td></tr>";
                                            $("table tbody").append(markup);
                                            $("#assetId option[value='" + data[i].detailId + "']").remove();
                                        }
                                        $("#btnSave").html('Confirm');
                                    } else {
                                        $("#btnSave").html('Save');
                                    }
                                }
                            });
                        }
                    }
                });
            } else {

            }
        } else {
            $("input[name=location]").focus();
            swal({
                title: 'Warning',
                text: 'Please select location !!',
                type: 'warning',
                timer: 1000
            })
        }
    }

    createJsonObject();
    var jsonArray = [];

    function createJsonObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';

        $.ajax({
            url: '${root}/data/loadLocationCode/' + userBranch,
            success: function (data) {

                var ten = data.length;

                for (var i = 0; i < ten; i++) {
                    var arr2 = {};
                    var id = data[i][0];
                    var code = data[i][1] + " - " + data[i][2];
                    arr2 = {id: id, value: code};

                    jsonArray.push(arr2);

                }
            }
        });

    }

    $("#locCodeId").autocomplete({

        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("getId").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,

    });

</script>