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
            <form name="detailsCatergoryForm" id="detailsCatergoryFormID"
                  action="${root}/category/saveDeatailCatergory" method="post">

                <div class="row" style="margin: 0">
                    <legend>Detail Asset Category</legend>
                </div>
                <input type="hidden" name="dcatId" value="${detailCat.dcatId}"/>
                <div class="row">

                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-5"  style="padding-top: 10px">Main Code</div>
                            <div class="col-md-6">
                                <select required id="mainCategoryID" onblur="loadSubCategory()" name=""
                                        style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="mainCats" items="${mainCategory}">
                                        <c:choose>
                                            <c:when test="${mainCats.mcatId == detailCat.assetCatergorySub.assetCatergoryMain.mcatId}">
                                                <option value="${detailCat.assetCatergorySub.assetCatergoryMain.mcatId}"
                                                        selected="selected">${detailCat.assetCatergorySub.assetCatergoryMain.mcatCode}
                                                    - ${detailCat.assetCatergorySub.assetCatergoryMain.mcatDes}</option>
                                                <option value="${mainCats.mcatId}">${mainCats.mcatCode}
                                                    - ${mainCats.mcatDes}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${mainCats.mcatId}">${mainCats.mcatCode}
                                                    - ${mainCats.mcatDes}</option>
                                            </c:otherwise>
                                        </c:choose>

                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-1"></div>

                        </div>

                        <div class="row">
                            <div class="col-md-5"  style="padding-top: 10px">Sub Code</div>
                            <div class="col-md-6">
                                <select required id="subCategoryListID" onchange="genarateDetailCategory()"
                                        name="assetCatergorySub.scatId" style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:if test="${detailCat.assetCatergorySub.scatId != null}">
                                        <option value="${detailCat.assetCatergorySub.scatId}"
                                                selected="selected">${detailCat.assetCatergorySub.scatCode}
                                            - ${detailCat.assetCatergorySub.scatDes}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="col-md-1"></div>

                        </div>

                        <div class="row">
                            <div class="col-md-5"  style="padding-top: 10px">Detail Code</div>
                            <div class="col-md-6">
                                <input type="text" width="100" id="detailCode" onkeyup="validateCharacter(this)"
                                       onblur="checkDetailCode(this)"  onchange="validateMasterCode(this,'DETAIL_CODE')"
                                       class="uppercase" required="required"  value="${detailCat.dcatCode}"
                                       name="dcatCode"
                                       style="width: 100%"/>
                            </div>
                            <div class="col-md-1"></div>

                        </div>
                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-5"  style="padding-top: 10px">Depreciation Rate - Income Tax</div>
                            <div class="col-md-6">
                                <input type="text" width="100" id="depRateIncomeTaxId"
                                       onblur="validatePercentageField(this); return setToTwoDecimals(id)"
                                       onkeyup="isNumber(this);"
                                       value="${detailCat.depRateIncomeTax}" name="depRateIncomeTax" maxlength="5"
                                       style="width: 100%"/>
                            </div>
                            <div class="col-md-1"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-5"  style="padding-top: 10px">Depreciation Rate - Accounting</div>
                            <div class="col-md-6">
                                <input type="text" width="100" id="depRateAccountId"
                                       onblur="validatePercentageField(this); return setToTwoDecimals(id)"
                                       onkeyup="isNumber(this)"
                                       value="${detailCat.depRateAccount}" name="depRateAccount" maxlength="5"
                                       style="width: 100%"/>
                            </div>
                            <div class="col-md-1"></div>

                        </div>
                        <div class="row">
                            <div class="col-md-5"  style="padding-top: 30px">Description</div>
                            <div class="col-md-6">
                                <textarea name="dcatDes"  maxlength="50"
                                          style="width: 100%">${detailCat.dcatDes}</textarea>
                            </div>
                            <div class="col-md-1"></div>
                        </div>

                        <div class="row" style="margin-top: 15px">
                            <div class="col-md-5"></div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; margin-top:3px;height: 30px; font-size: 14px"
                                           href="${root}/category/detailCategory">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <c:choose>
                                            <c:when test="${detailCat.dcatId > 0 }">
                                                <button type="submit" class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px">
                                                    Update
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px">Save
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-5">
        <table id="dataTable" class="display compact" width="100%"
               cellspacing="0">
            <thead>
            <tr>
                <th>Sub Category Code</th>
                <th>Detail Category Code</th>
                <th>Description</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="detail" items="${detailCategory}">
                <tr id="${detail.dcatId}">
                    <c:forEach var="subCategory" items="${subCategory}">
                        <c:if
                                test="${detail.assetCatergorySub.scatId == subCategory.scatId}">
                            <td>${subCategory.scatCode}</td>
                        </c:if>
                    </c:forEach>
                    <td>${detail.dcatCode}</td>
                    <td>${detail.dcatDes}</td>
                    <td><a href="${root}/category/editDetailCategory/${detail.dcatId}"><i
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

    addComma("depRateIncomeTaxId");
    addComma("depRateAccountId");

    function setTwoNumberDecimal(event) {
        this.value = parseFloat(this.value).toFixed(2);
    }

    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('.btnSubmit').click(function () {
        $(".se-pre-con").show();
    });

    $(document).ready(function () {
        $('#detailCode').attr('maxlength',DETAIL_CODE);
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": false
        });
    });

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var isEdit = $("#isEdit").val();
        if (isEdit == 1) {
            $('#detailCode').prop('readonly', true);
            $("#mainCategoryID").attr('disabled',true);
            $("#subCategoryListID").attr('disabled',true);
        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./detailCategory";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Sub Category Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./detailCategory";
                })
            }
        }
    });

    $( "#detailsCatergoryFormID" ).submit(function( event ) {
        $("#mainCategoryID").attr('disabled',false);
        $("#subCategoryListID").attr('disabled',false);

    });

    function genarateDetailCategory() {
        var subCat = $("#subCategoryListID").find("option:selected").val();
        $.ajax({
            url: '${root}/category/getDetailCatbySub/' + subCat,
            success: function (data) {
                // $("#detailCode").val(data); // not working correctly. because they can enter characters
            }
        });

    }

    function loadSubCategory() {
        $("#subCategoryListID").empty();
        var mainCat = $("#mainCategoryID").find("option:selected").val();
        $.ajax({
            url: '${root}/category/getSubCategoryList/' + mainCat,
            success: function (data) {
                $("#subCategoryListID").append(
                    "<option value = ''> ---SELECT--- </option>");
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#subCategoryListID").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1] + " - " + subCat[2]
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

    $("#detailsCatergoryFormID").submit(function (event) {
        var mainCat = $("#mainCategoryID").find("option:selected").val();
        var subCat = $("#subCategoryListID").find("option:selected").val();
        if (subCat > 0 && mainCat > 0) {
            $("#detailsCatergoryFormID").submit();
        } else {
            if (mainCat == 0) {
                $("#mainCategoryID").addClass("txtError");
                $("#mainCategoryID").focus();
            } else if (subCat == 0) {
                $("#subCategoryListID").addClass("txtError");
                $("#subCategoryListID").focus();
            }
        }
        event.preventDefault();
    });

    function checkDetailCode(code) {
        var detailCode = code.value;
        var isEdit = $("#isEdit").val();
        var subCat = $("#subCategoryListID").find("option:selected").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/category/checkDetailCode/' + detailCode + '/' + subCat,
                success: function (data) {
                    if (!data) {
                        // $("#detailCode").focus();
                        $("#detailCode").addClass("txtError");
                        $("#detailCode").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate Detail Category code !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#detailCode").removeClass("txtError");
                    }
                },
                error: function () {
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
                url: '${root}/category/deleteDetailCategory/' + trId,
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
                        window.location.href = "./detailCategory";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Detail Category already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Detail Category!!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }

</script>