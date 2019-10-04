<%--Modification: Modification done for asset code loading function by Dilusha on 2019/06/18--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        max-width: 307px;
        overflow-y: auto;
        /* prevent horizontal scrollbar */
        overflow-x: hidden;
        /* add padding to account for vertical scrollbar */
        padding-right: 20px;
    }
</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>

<div class="col-md-12 page">
    <div class="row" style="margin: 0">
        <legend>Software</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="softwareForm" action="${root}/maintenance/saveSoftware" method="post">

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-8">

                                    <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                                           class="form-control"
                                           placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()">
                                    <input id="getId" name="assetId" hidden>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Description</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="100" id="descriptionId"
                                       name="description"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Seller-Address</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="200" id="sellerAddId"
                                       name="sellerAdd"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Seller/Dealer</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="100" id="sellerDealerId"
                                       name="sellerDealer"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Product Key</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="100" id="productKeyId"
                                       name="productKey"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Owner</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="100" id="softOwnerNameId"
                                       name="softOwnerName"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Service Agreement</div>
                            <div class="col-md-8">
                                <select id="isServiceAgreId" required
                                        name="isServiceAgre"
                                        style="width: 100%">
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select></div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">No. of Users</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="12" id="noUsersId"
                                       name="noUsers"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6"></div>
                    <div class="col-md-5">
                        <div class="row" style="padding-top: 10px">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <a class="btn btn-primary" style="width: 100%; font-size: 14px"
                                   href="${root}/maintenance/software">New</a>
                            </div>
                            <div class="col-md-4" style="margin-top:-3px">
                                <button type="submit" class="btn btn-primary" style="width: 100%; font-size: 14px"
                                        id="btnSave">
                                    Save
                                </button>

                            </div>

                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <input type="hidden" value="0" name="softwareId">
                <input type="hidden" id="pageType" value="${pageType}"/>
                <input type="hidden" id="status" value="${status}"/>
                <input type="hidden" id="update" value="${update}"/>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">

    createJsonOfficeEquipmentObject();
    $('#btnSave').attr("disabled", true);
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);

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


    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var update = $("#update").val();

        if (update == 0) {
            message = "Saved Successfully!!! ";
        } else {
            message = "Updated Successfully!!! ";
        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: message,
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./software";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./software";
                })
            }
        }

    });

    function loadSoftwareDetailsByAsset() {
        var assetId = $("#getId").val();
        $.ajax({
            url: '${root}/maintenance/loadSoftwareDetailsByAsset/' + assetId,
            success: function (data) {
                if (data != "") {
                    $("input[name=softwareId]").val(data.softwareId);
                    $("input[name=sellerDealer]").val(data.sellerDealer);
                    $("input[name=sellerAdd]").val(data.sellerAdd);
                    $("input[name=softOwnerName]").val(data.softOwnerName);
                    $("input[name=productKey]").val(data.productKey);
                    $("input[name=noUsers]").val(data.noUsers);
                    // $("input[name=comments]").val(data.comments);
                    $("input[name=description]").val(data.description);
                    document.getElementById("isServiceAgreId").selectedIndex = data.isServiceAgre;
                    $("#btnSave").html('Update');
                } else {
                    $("input[name=softwareId]").val("0");
                    $("input[name=sellerDealer]").val("");
                    $("input[name=sellerAdd]").val("");
                    $("input[name=softOwnerName]").val("");
                    $("input[name=productKey]").val("");
                    $("input[name=noUsers]").val("");
                    $("input[name=comments]").val("");
                    $("input[name=description]").val("");
                    document.getElementById("isServiceAgreId").selectedIndex = 0;
                    $("#btnSave").html('Save');
                }
            }
        });
    }

    var jsonArray = [];

    function createJsonOfficeEquipmentObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';
        var type = 1;

        $.ajax({
            url: '${root}/data/loadAssetByTypes/' + userBranch + '/' + type,
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

    function checkAssetCodeValid() {
        var des = $('input[name=getName]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {

                    $('#btnSave').attr("disabled", false);
                    $('#assetCodeTo').css('border-color', '#aaaaaa');
                    loadSoftwareDetailsByAsset();
                } else {
                    $('#btnSave').attr("disabled", true);
                    $('#assetCodeTo').css('border-color', 'red');
                }
            }
        });
    }


    function isEmpty() {

        if (document.getElementById("assetCodeTo").value == '') {
            $('#assetCodeTo').css('border-color', '#aaaaaa');
            $('#form input[type="text"]').val('');


        }
    }

    $("#assetCodeTo").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("getId").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,
        max: 100,

    });


</script>