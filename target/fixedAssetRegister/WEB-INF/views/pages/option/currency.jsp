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
    <div class="col-md-7">
        <div class="page">
            <form name="currencyForm" action="${root}/option/saveCurrency" method="post">

                <div class="row" style="margin: 0">
                    <legend>Add Currency</legend>
                </div>
                <input type="hidden" name="currencyId" value="${currency.currencyId}"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-3" style="padding-top: 10px">Currency Code</div>
                            <div class="col-md-6">
                                <input type="text" id="currencyCodeId" required="required" name="currencyCode" value="${currency.currencyCode}" maxlength="3" onkeyup="validateCharacter(this)"
                                       style="width: 100%" onblur="checkCurrencyCode(this)"/>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-3" style="padding-top: 10px">Description</div>
                            <div class="col-md-6">
                                <input type="text" id="descriptionId" required="required" value="${currency.description}" name="description" maxlength="50"
                                       style="width: 100%"/>
                            </div>
                            <div class="col-md-3"></div>
                        </div>


                        <div class="row" style="margin-top: 15px">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">

                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; font-size: 14px; margin-top: 3px"
                                           href="${root}/option/currency">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <c:choose>
                                            <c:when test="${currency.currencyId > 0 }">
                                                <button type="submit" class="btn btn-primary"
                                                        style="width: 100%; font-size: 14px">
                                                    Update
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-primary"
                                                        style="width: 100%; font-size: 14px">Save
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3"></div>

                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-5">
        <table id="dataTable" class="display compact" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>Currency Code</th>
                <th>Description</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="currencyType" items="${currencyTypes}">
                <tr id="${currencyType.currencyId}">
                    <td>${currencyType.currencyCode}</td>
                    <td>${currencyType.description}</td>
                    <td><a href="${root}/option/editCurrency/${currencyType.currencyId}"><i
                            class="fa fa-pencil-square-o"></i></a></td>
                    <td><a onclick="onDelete(this)"><i class="fa fa-trash-o"></i></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="isEdit" value="${isEdit}"/>

<script type="text/javascript">
    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var isEdit = $("#isEdit").val();
        if (isEdit == 1) {
            $('#currencyCodeId').prop('readonly', true);
        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'success!',
                    type: 'success',
                    timer: 2000
                }).then(function () {
                    window.location.href = "./currency";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Currency Added Fail !!',
                    type: 'error',
                    timer: 2000
                }).then(function () {
                    window.location.href = "./currency";
                })
            }
        }

    });

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
                url: '${root}/option/deleteCurrency/' + trId,
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
                        window.location.href = "./currency";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Currency already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Currency!!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });

        })
    }

    function checkCurrencyCode(code) {
        var currencyCode = code.value;
        var isEdit = $("#isEdit").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/option/checkCurrencyCode/' + currencyCode,
                success: function (data) {
                    if (!data) {
                        $("#currencyCodeId").focus();
                        $("#currencyCodeId").addClass("txtError");
                        $("#currencyCodeId").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate Currency code !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#currencyCodeId").removeClass("txtError");
                    }
                },
                error: function () {

                }
            });
        }
    }
    $(document).ready(function () {
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": false
        });
    });

</script>