<%--
  Created by IntelliJ IDEA.
  User: Dilusha Kumari
  Date: 1/30/2019
  Time: 1:20 PM
  To change this template use File | Settings | File Templates.

  Modification: Modification done for asset code loading function by Dilusha on 2019/06/18
--%>
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
</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="col-md-6 page">
    <div class="row" style="margin: 0">
        <legend>Library Books</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="libraryBooksForm" action="${root}/maintenance/saveLibraryBooks" method="post">

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                    <div class="col-md-8">
                        <%--<select class="chosen" id="assetCode" required--%>
                                <%--name="assetId" style="width: 100%" onchange="loadLibraryBooksDetails()">--%>
                            <%--<option value="">---SELECT---</option>--%>
                            <%--<c:forEach var="asset" items="${assets}">--%>
                                <%--<option value="${asset.asId}">${asset.asCode} - ${asset.asDes}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>

                            <%--<datalist id="divAssetList">--%>
                            <%--</datalist>--%>

                            <%--<div id="listasset">--%>
                                <%--<input autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"--%>
                                       <%--onkeyup="loadAssetByType()" onblur="checkAssetCodeValid()"--%>
                                       <%--id="assetCodeUserId" name="assetCodeUser" placeholder="Search.."--%>
                                       <%--class="form-control"/>--%>
                            <%--</div>--%>
                            <%--<input id="assetCodeId" name="assetId" hidden>--%>

                            <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                                   class="form-control"
                                   placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()">
                            <input id="getId" name="assetId" hidden>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Author</div>
                    <div class="col-md-8">
                        <input type="text" required style="width: 100%" id="authorId" name="author" maxlength="100"
                        />
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Editors</div>
                    <div class="col-md-8">
                        <input type="text" style="width: 100%" id="editorsId" name="editors" maxlength="100"
                               required/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Place Of Publication</div>
                    <div class="col-md-8">
                        <input type="text" style="width: 100%" id="placeOfPublicationId"
                               name="placeOfPublication" maxlength="100" required/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Publisher</div>
                    <div class="col-md-8">
                        <input type="text" style="width: 100%" id="publisherId" name="publisher"
                               maxlength="100" required/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Date of Publication</div>
                    <div class="col-md-8">
                        <div class='input-group date' style="width: 102%">
                            <input type='text' required class="form-control"
                                   name="dateOfPublication" style="height:100%"/>
                            <span class="input-group-addon">
                                <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                        </div>
                    </div>


                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">No. Of Series</div>
                    <div class="col-md-8">
                        <input type="text" style="width: 100%" id="seriesNoId" name="seriesNo" maxlength="10"
                               onkeypress="return isNumberOnly(event)"
                               required/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4"style="padding-top: 10px">Edition</div>
                    <div class="col-md-8">
                        <input type="text" required style="width: 100%" id="editionId" name="edition" maxlength="10"
                               required/>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">ISBN No.</div>
                    <div class="col-md-8">
                        <input type="text" required style="width: 100%" id="isbmNoId" name="isbmNo" maxlength="10"
                               required/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Contents</div>
                    <div class="col-md-8">
                        <input type="text" required style="width: 100%" id="contentsId" name="contents" maxlength="100"
                               required/>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Comments</div>
                    <div class="col-md-8">
                        <input type="text" required style="width: 100%" id="commentsId" name="comments" maxlength="200"
                               required/>
                    </div>
                </div>
                <div class="row">&nbsp</div>
                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-6" style="margin-top: 3px">
                                <a class="btn btn-primary" style="width: 100%; font-size: 14px"
                                   href="${root}/maintenance/libraryBooks">New</a>
                            </div>
                            <div class="col-md-6">
                                <button type="submit" id="btnSave" class="btn btn-primary" style="width: 100%; font-size: 14px">
                                    Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <input type="hidden" name="libraryBookId" value="0">
            </form>
        </div>
    </div>

</div>


<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="update" value="${update}"/>


<script type="text/javascript">



    createLibraryBooksObject();
    $('#btnSave').attr("disabled", true);
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);

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
                    window.location.href = "./libraryBooks";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./libraryBooks";
                })
            }
        }

    });

    function loadLibraryBooksDetails() {

        var assetId = $("#getId").val();

        $.ajax({
            url: '${root}/maintenance/loadLibraryBooksDetailsByAsset/' + assetId,
            success: function (data) {
                if (data != "") {

                    $("input[name=libraryBookId]").val(data.libraryBookId);
                    $("input[name=author]").val(data.author);
                    $("input[name=editors]").val(data.editors);
                    $("input[name=placeOfPublication]").val(data.placeOfPublication);
                    $("input[name=dateOfPublication]").val((data.dateOfPublication));
                    $("input[name=seriesNo]").val(data.seriesNo)
                    $("input[name=edition]").val(data.edition)
                    $("input[name=isbmNo]").val(data.isbmNo);
                    $("input[name=contents]").val(data.contents);
                    $("input[name=comments]").val(data.comments);
                    $("input[name=publisher]").val(data.publisher);
                    $("#btnSave").html('Update');
                } else {

                    $("input[name=libraryBookId]").val("0");
                    $("input[name=author]").val("");
                    $("input[name=editors]").val("");
                    $("input[name=placeOfPublication]").val("");
                    $("input[name=dateOfPublication]").val("");
                    $("input[name=seriesNo]").val("")
                    $("input[name=edition]").val("")
                    $("input[name=isbmNo]").val("");
                    $("input[name=contents]").val("");
                    $("input[name=comments]").val("");
                    $("input[name=publisher]").val("");
                    $("#btnSave").html('Save');
                }
            }
        });

    }

    <%--function loadAssetByType() {--%>
        <%--setTimeout(function () {--%>
            <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
            <%--var assetCodeIdval = $('input[name=assetCodeUser]').val().toUpperCase();--%>
            <%--var type = 11;--%>

            <%--if (assetCodeIdval == '') {--%>
                <%--assetCodeIdval = 0;--%>
            <%--}--%>

            <%--$.ajax({--%>
                <%--url: '${root}/data/loadAssetByAssetCode/' + userBranch + '/' + assetCodeIdval + '/' + type,--%>
                <%--success: function (data) {--%>
                    <%--assetCodeIdval = assetCodeIdval == "0" ? "" : assetCodeIdval;--%>
                    <%--$("#listasset").html('<input  autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"\n' +--%>
                        <%--'               onkeypress="loadAssetByType()"' +--%>
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



    <%--function checkAssetCodeValid() {--%>
        <%--var assetCode = $('input[name=assetCodeUser]').val();--%>
        <%--$.ajax({--%>
            <%--url: '${root}/data/checkAssetCodeValid/' + assetCode,--%>
            <%--success: function (data) {--%>
                <%--if (data != 0) {--%>
                    <%--$('#assetCodeUserId').removeClass('is-invalid');--%>
                    <%--$('#assetCodeId').val(data);--%>
                    <%--$('#btnSave').attr("disabled", false);--%>
                    <%--loadLibraryBooksDetails();--%>
                <%--} else {--%>
                    <%--$('#assetCodeId').val(0);--%>
                    <%--$('#btnSave').attr("disabled", true);--%>
                    <%--$('#assetCodeUserId').addClass('is-invalid'); // Giving a red border when invalid asset Code--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
    //
    // jQuery.fn.putCursorAtEnd = function () {
    //     return this.each(function () {
    //         var $el = $(this),
    //             el = this;
    //         if (!$el.is(":focus")) {
    //             $el.focus();
    //         }
    //         if (el.setSelectionRange) {
    //             var len = $el.val().length * 2;
    //             setTimeout(function () {
    //                 el.setSelectionRange(len, len);
    //             }, 1);
    //         } else {
    //             $el.val($el.val());
    //         }
    //         this.scrollTop = 999999;
    //     });
    // };

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
        });
    });

    var jsonArray = [];

    function createLibraryBooksObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';
        var type = 11;

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
                    loadLibraryBooksDetails();
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