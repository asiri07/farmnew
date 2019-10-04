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
            <form id="subCatergoryFormID" name="subCatergoryForm" action="${root}/category/saveSubCategory"
                  method="post">

                <div class="row" style="margin: 0">
                    <legend>Asset Category-Sub</legend>
                </div>
                <input type="hidden" name="scatId" value="${subCat.scatId}"/>
                <div class="row">

                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Main Code</div>
                            <div class="col-md-6">
                                <select required id="mainCategoryId" name="assetCatergoryMain.mcatId" required
                                        style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="mainCats" items="${mainCategory}">
                                        <c:choose>
                                            <c:when test="${mainCats.mcatId == subCat.assetCatergoryMain.mcatId}">
                                                <option value="${subCat.assetCatergoryMain.mcatId}"
                                                        selected="selected">${subCat.assetCatergoryMain.mcatCode}
                                                    - ${subCat.assetCatergoryMain.mcatDes}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${mainCats.mcatId}">${mainCats.mcatCode}
                                                    - ${mainCats.mcatDes}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>

                            </div>

                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Sub Code</div>
                            <div class="col-md-6">
                                <input type="text" width="100" id="subCode" onkeyup="validateCharacter(this)"
                                       onblur="checkSubCode(this)" onchange="validateMasterCode(this,'SUB_CODE')"
                                       class="uppercase" required="required" value="${subCat.scatCode}"
                                       name="scatCode"
                                       <%--maxlength="masterCodeLength"--%>
                                       style="width: 100%"/>
                            </div>

                            <div class="col-md-2"></div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 30px">Description</div>
                            <div class="col-md-6">
                                <textarea name="scatDes" required="required" maxlength="50"
                                          style="width: 100%">${subCat.scatDes}</textarea>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row" style="margin-top: 15px">
                            <div class="col-md-4"></div>
                            <div class="col-md-6">

                                <div class="row">

                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; margin-top:3px;height: 30px; font-size: 14px"
                                           href="${root}/category/subCategory">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <c:choose>
                                            <c:when test="${subCat.scatId > 0}">
                                                <button type="submit" class="btn btn-primary" style="width: 100%; height: 30px; font-size: 14px">
                                                    Update
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-primary" style="width: 100%; height: 30px; font-size: 14px">Save
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-2"></div>

                                </div>

                            </div>

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
                <th>Main Category Code</th>
                <th>Sub Category Code</th>
                <th>Description</th>
                <th>Edit</th>
                <th>Delete</th>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="subCategory" items="${subCategory}">
                <tr id="${subCategory.scatId}">
                    <c:forEach var="mainCategory" items="${mainCategory}">
                        <c:if test="${subCategory.assetCatergoryMain.mcatId == mainCategory.mcatId}">
                            <td>${mainCategory.mcatCode}</td>
                        </c:if>
                    </c:forEach>
                    <td>${subCategory.scatCode}</td>
                    <td>${subCategory.scatDes}</td>
                    <td><a href="${root}/category/editSubCategory/${subCategory.scatId}"><i
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

    $('.btnSubmit').click(function () {
        $(".se-pre-con").show();
    });

    $(document).ready(function () {
        $('#subCode').attr('maxlength',SUB_CODE);
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
            $('#subCode').prop('readonly', true);
            $("#mainCategoryId").attr('disabled',true);

        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./subCategory";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Sub Category Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./subCategory";
                })
            }
        }

    });

      $( "#subCatergoryFormID" ).submit(function( event ) {
          $("#mainCategoryId").attr('disabled',false);
        });

    function checkSubCode(code) {
        var mainCat = $("#mainCategoryId").find("option:selected").val();
        var subCode = code.value;
        var isEdit = $("#isEdit").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/category/checkSubCode/' + subCode + '/' + mainCat,
                success: function (data) {
                    if (!data) {
                        // $("#subCode").focus();
                        $("#subCode").addClass("txtError");
                        $("#subCode").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate Sub Category code !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#subCode").removeClass("txtError");
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
                url: '${root}/category/deleteSubCategory/' + trId,
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
                        window.location.href = "./subCategory";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Sub Category already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Sub Category!!',
                            type: 'warning',
                            timer: 1000
                    })
                    }
                }
            });
        })
    }


</script>