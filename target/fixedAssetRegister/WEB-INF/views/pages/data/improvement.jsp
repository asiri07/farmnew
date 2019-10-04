<%--Modification Date     : 3/8/2019:--%>
<%--User                  : Dilusha Kumari--%>
<%--Purpose               : Add a method for generating Transaction Number--%>

<%--Modification Date     : 6/25/2019:--%>
<%--User                  : Dilusha Kumari--%>
<%--Purpose               : Modified Asset Code loading method--%>

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
        max-width: 350px;
        overflow-y: auto;
        /* prevent horizontal scrollbar */
        overflow-x: hidden;
        /* add padding to account for vertical scrollbar */
        padding-right: 20px;
    }

    /*.modal {*/
    /*background: green;*/
    /*position: absolute;*/
    /*float: left;*/
    /*left: 50%;*/
    /*top: 50%;*/
    /*transform: translate(-50%, -50%);*/
    /*}*/
    /*body.modal-open div.modal-backdrop {*/
    /*z-index: 0;*/
    /*}*/


</style>


<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="col-md-12 ">
    <div class="row">
        <div class="col-md-6 page">
            <%--<div class="page">--%>
            <form name="improvementForm" id="improvementForm" action="${root}/data/saveImprovement" method="post">

                <div class="row" style="margin: 0 ; ">
                    <legend>Asset Improvement</legend>
                </div>


                <input type="hidden" name="assetImpId" value="${imp.assetImpId}"/>
                <div class="row">

                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-8">


                                <%--<select class="chosen" id="assetCodeId" required name="asset.asId"--%>
                                <%--style=" width:101%; text-align:center; " onchange="loadTransactionNoList()">--%>
                                <%--<option value="">---SELECT---</option>--%>
                                <%--<c:forEach var="asset" items="${assets}">--%>
                                <%--<c:if test="${asset.asId == imp.asset.asId}">--%>
                                <%--<option selected value="${asset.asId}">${asset.asCode}</option>--%>
                                <%--</c:if>--%>
                                <%--<option value="${asset.asId}">${asset.asCode} - ${asset.asDes}</option>--%>
                                <%--</c:forEach>--%>
                                <%--</select>--%>


                                <%--<datalist id="divAssetList">--%>
                                <%--</datalist>--%>

                                <%--<div id="listasset">--%>
                                <%--<input autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"--%>
                                <%--onkeypress="loadAssetByType()" onblur="checkAssetCodeValid()"--%>
                                <%--id="assetCodeUserId" name="assetCodeUser" placeholder="Search.." class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<input id="assetCodeId" name="asset" hidden>--%>
                                <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text"
                                       autocomplete="off"
                                       class="form-control"
                                       placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()">
                                <input id="getId" name="asset" hidden>

                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Transaction No.</div>
                            <div class="col-md-8">
                                <select id="transactionSelectId" style="width: 100%"
                                        onchange="loadImprovementsDetails()"
                                        name="transactionSelect">
                                    <option value="">---SELECT---</option>
                                </select>
                            </div>
                            <div class="col-md-2"></div>

                        </div>
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Date</div>
                            <div class="col-md-8">
                                <div class='input-group date'>
                                    <input type='text' onchange="checkImprovementDate()" required class="form-control"
                                           style="height:35px"
                                           value="${imp.impDate}" name="impDate"/>
                                    <span class="input-group-addon">
                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                            </span>
                                </div>

                            </div>
                            <div class="col-md-2"></div>

                            <%--<div class="col-md-7">--%>

                            <%--<input type="text" onchange="checkDisposalDate()" autocomplete="off" required--%>
                            <%--class="form-control"--%>
                            <%--id="datePicker"--%>
                            <%--style=" height: 35px;width:106%"--%>
                            <%--name="impDate"/>--%>

                            <%--</div>--%>
                            <%--<div class="col-md-1">--%>
                            <%--<span style="text-align:center;font-size:24px;color:#3e71fb"--%>
                            <%--class="fa fa-calendar"></span>--%>
                            <%--</div>--%>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 30px">Description</div>
                            <div class="col-md-8">
                                <textarea type="text" required maxlength="100" name="description"
                                          style="width: 100%">${imp.description}</textarea>

                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Currency Type</div>
                            <div class="col-md-8">
                                <select id="currencyId" required class="select_style" name="currencyType"
                                        style="width: 100%">
                                    <option value="0">---SELECT---</option>
                                    <c:forEach items="${currencyTypes}" var="currencyType">
                                        <option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>
                                    </c:forEach>
                                    <%--<c:forEach items="${currencyTypes}" var="currencyType">--%>


                                    <%--&lt;%&ndash;<c:choose>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<c:when test="${mainCats.mcatId == subCat.assetCatergoryMain.mcatId}">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<option value="${subCat.assetCatergoryMain.mcatId}"&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;selected="selected">${subCat.assetCatergoryMain.mcatCode}&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;- ${subCat.assetCatergoryMain.mcatDes}</option>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<c:otherwise>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<option value="${mainCats.mcatId}">${mainCats.mcatCode} - ${mainCats.mcatDes}</option>&ndash;%&gt;--%>
                                    <%--<option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>--%>
                                    <%--&lt;%&ndash;</c:otherwise>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</c:choose>&ndash;%&gt;--%>


                                    <%--</c:forEach>--%>
                                    <%--<c:forEach items="${currencyTypes}" var="currencyType">--%>
                                    <%--<option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>--%>
                                    <%--</c:forEach>--%>
                                    <%--<c:forEach items="${currencyTypes}" var="currencyTypeFrom">--%>
                                    <%--<c:if test="${currencyTypeFrom.currencyId == imp.currencyType}">--%>
                                    <%--<option value="${currencyTypeFrom.currencyCode}</option>--%>
                                    <%----%>
                                    <%--</c:forEach>--%>
                                </select>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Value</div>
                            <div class="col-md-8">
                                <input type="text" onkeypress="return isNumberAndDecimalOnly(event)"
                                       onblur="addComma(id)"
                                       maxlength="16" value="${imp.currencyValue}"
                                       required name="currencyValue" id="currencyValue"
                                       style="width: 100%"/>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row">&nbsp;</div>

                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-8">
                                <div class="row">
                                    <%--style="margin-left: 37px"--%>
                                    <div class="col-md-5">
                                        <a class="btn btn-primary"
                                           style="width: 120%; margin-top: 3px ;font-size: 14px;"
                                           href="${root}/data/improvements">New</a>
                                    </div>
                                    <div class="col-md-5">
                                        <%--<c:choose>--%>
                                        <%--<c:when test="${imp.assetImpId > 0 }">--%>
                                        <a class="btn btn-primary"
                                           style="width: 120%; margin-top: 3px ;font-size: 14px;color:white"
                                           onclick="CommaRemove('currencyValue');save(this);" id="btnSave">
                                            Save
                                        </a>
                                        <%--</c:when>--%>
                                        <%--<c:otherwise>--%>
                                        <%--<a onclick="save();CommaRemove('currencyValue');" class="btn btn-primary"--%>
                                        <%--id="btnSave"--%>
                                        <%--style="width: 120% ; font-size: 14px;">--%>
                                        <%--Save--%>
                                        <%--</a>--%>
                                        <%--</c:otherwise>--%>
                                        <%--</c:choose>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <input type="hidden" name="assetImpId" id="assetImpId" value="0">
                <input type="hidden" name="transactionNo" id="transactionNo" value="${transactionNo}"/>

            </form>

            <%--<%@ include file= 'HigherUserCredential.jsp' %>--%>
            <jsp:include page="../../pages/HigherUserCredential.jsp"/>

        </div>
        <div class="col-md-5"></div>
    </div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="isRate" value="${isRate}"/>

<script type="text/javascript">
    // loadAssetByType();

    onload = createJsonObject();
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);
    $('#btnSave').attr("disabled", true);

    // $(".chosen").chosen();
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
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
            endDate: '+0d'
        });
    });


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
        var isRate = $("#isRate").val();
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var transactionNo = $("#transactionNo").val();
        var message = "";

        if (transactionNo != "") {
            message = "Success!!! Transaction No. : ";
        } else {
            message = "Update success!!! ";
        }

        if (pageType == 1) {
            if (isRate == 'false') {
                swal({
                    title: 'Error',
                    text: 'Please Update Currency Rate in improvements Date !!',
                    type: 'error',
                })
            } else {
                if (status == 'true') {
                    swal({
                        title: message + transactionNo,
                        type: 'success',
                    }).then(function () {
                        window.location.href = "./improvements";
                    })
                } else {
                    swal({
                        title: 'Error !',
                        text: 'Improvement Added Fail !!',
                        type: 'error',
                        timer: 1000
                    }).then(function () {
                        window.location.href = "./improvements";
                    })
                }
            }
        }

    });

    function checkImprovementDate() {
        var dat = $("input[name=impDate]").val();
        // var assetId = $("#assetCodeId").find("option:selected").val();
        var assetId = $("#getId").val();
        if (dat != "" && assetId > 0) {
            var date = formatDate(dat);
            $.ajax({
                url: '${root}/data/checkImprovementDate',
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
                        }
                        swal({
                            title: 'Warning',
                            text: msg,
                            type: 'warning',
                            timer: 2000
                        })
                        $("input[name=impDate]").val("");
                    }

                }

            });
        }
    }


    function onDelete(id) {
        var trId = $(id).closest('tr').attr('id');
        swal({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then(function () {
            $.ajax({
                url: '${root}/data/deleteImprovement/' + trId,
                success: function (data) {
                    if (data == 1) {
                        swal({
                            title: 'Deleted!',
                            text: 'Your file has been deleted.',
                            type: 'success',
                            timer: 1000
                        })
                        var row = id.parentNode.parentNode;
                        row.parentNode.removeChild(row);
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Asset Improvement already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            trxt: 'Can\'t Delete Asset !!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }

    function loadTransactionNoList() {

        // var assetId = $("#assetCodeId").find("option:selected").val();

        var assetId = $("#getId").val();

        $("#transactionSelectId").find("option:not(:first)").remove();

        var userBranch = '<%= session.getAttribute("userBranch") %>';

        $.ajax({
            url: '${root}/data/loadImprovementNoList/' + assetId + '/' + userBranch,
            success: function (data) {
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        $("#transactionSelectId").append(
                            "<option value = '" + data[i][0] + "'>" + data[i][1]
                            + "</option>");
                    }
                } else {
                    $("#btnSave").html('Save');
                    $("input[name=assetImpId]").val(0);
                    $("input[name=transactionNo]").val(0);
                    $("input[name=impDate]").val("");
                    $("input[name=currencyValue]").val("");
                    $("textarea[name=description]").val("");
                    document.getElementById("currencyId").selectedIndex = 0;
                }
            }
        });
    }

    function loadImprovementsDetails() {
        var improvementId = $("#transactionSelectId").find("option:selected").val();

        $.ajax({
            url: '${root}/data/loadImprovementDetailsByImprovementId/' + improvementId,
            success: function (data) {
                $("#btnSave").html('Update');
                if (data != "") {
                    $("input[name=assetImpId]").val(data[0][0]);
                    $("input[name=transactionNo]").val(data[0][1]);
                    $("input[name=impDate]").val(dateFormators(data[0][2]));
                    $("input[name=currencyValue]").val(data[0][3]);
                    $("textarea[name=description]").val(data[0][4]);
                    document.getElementById("currencyId").selectedIndex = data[0][5];
                    addComma("currencyValue");
                }
            }
        });
    }

    function save() {
        var userType = '<%= session.getAttribute("userType") %>';
        if ($(btnSave).text() == "Update") {
            if (userType.trim() == 'admin') {
                $('#improvementForm').submit();
            } else {
                $('#myModal').modal('show')
                $('#myModal').appendTo("body")
            }
        } else {
            $('#improvementForm').submit();
        }

    }

    function checkHigherUser() {
        var username = $("#usernameModal").val();
        var password = $("#passwordModal").val();
        $("#username").val(username);
        $("#password").val(password);

        $.ajax({
            url: "${root}/user/checkHigherUserLogin",
            data: {username: username, password: password},
            success: function (result) {
                if (result == true) {
                    $('#myModal').modal('toggle');
                    $('#improvementForm').submit();
                } else {
                    swal({
                        title: 'Login Failed!',
                        text: 'Authectication Fail!',
                        type: 'warning',
                        timer: 1000
                    })
                }
            }
        });


        // $('#checkHigherUserForm').submit();
        <%--$.post('${root}/user/checkHigherUserLogin', { json_string:JSON.stringify({username:username, password:password}) });--%>
    }


    <%--function loadAssetByType() {--%>
    <%--setTimeout(function () {--%>
    <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
    <%--var assetCodeIdval = $('input[name=assetCodeUser]').val().toUpperCase();--%>

    <%--if (assetCodeIdval == '') {--%>
    <%--assetCodeIdval = 0;--%>
    <%--}--%>

    <%--$.ajax({--%>
    <%--url: '${root}/data/loadAssetByAssetCode/' + userBranch + '/' + assetCodeIdval,--%>
    <%--success: function (data) {--%>
    <%--assetCodeIdval = assetCodeIdval == "0" ? "" : assetCodeIdval;--%>
    <%--$("#listasset").html('<input  autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"\n' +--%>
    <%--'               onkeydown="loadAssetByType()"' +--%>
    <%--'               onblur="checkAssetCodeValid()" value="' + assetCodeIdval + '" ' +--%>
    <%--'               id="assetCodeUserId" name="assetCodeUser" placeholder="Search.." class="form-control "/>');--%>
    <%--$("#divAssetList").html('');--%>
    <%--var ten = 100;--%>
    <%--if (data.length < 100) {--%>
    <%--ten = data.length;--%>
    <%--}--%>
    <%--for (var i = 0; i < ten; i++) {--%>
    <%--$("#divAssetList").append("<option selected value = '" + data[i][1] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");--%>
    <%--}--%>
    <%--$('#assetCodeUserId').putCursorAtEnd();--%>
    <%--}--%>
    <%--});--%>
    <%--$(".chosen").chosen();--%>
    <%--}, 10);--%>
    <%--}--%>


    function checkAssetCodeValid() {
        // var assetCode = $('input[name=assetCodeUser]').val();

        var des = $('input[name=getName]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        var assetId = $("#getId").val();
        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {
                    // $('#assetCodeUserId').removeClass('is-invalid');
                    // $('#assetCodeId').val(data);
                    $('#btnSave').attr("disabled", false);
                    $('#assetCodeTo').css('border-color', '#aaaaaa');
                    ;
                    loadTransactionNoList();
                } else {
                    // $('#assetCodeId').val(0);
                    $('#btnSave').attr("disabled", true);
                    $('#assetCodeTo').css('border-color', 'red');
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

    function isEmpty() {

        if (document.getElementById("assetCodeTo").value == '') {
            $('#assetCodeTo').css('border-color', '#aaaaaa');

        }
    }

    var jsonArray = [];

    function createJsonObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';

        $.ajax({
            url: '${root}/data/loadAssetByAssetCode/' + userBranch,
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

    })

</script>