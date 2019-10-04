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
    <div class="col-md-7 ">
        <div class="page">
            <form name="mainCatergoryForm" id="mainCatergory" action="${root}/category/saveMainCatergory" method="post">

                <div class="row" style="margin: 0">
                    <legend>Asset Category-Main</legend>
                </div>
                <input type="hidden" name="mcatId" value="${mainCat.mcatId}"/>
                <input type="hidden" name="id" value="${mainCat.mcatId}"/>
                <input type="hidden" name="typeid" value="${typeid}"/>
                <div class="row">

                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Main Code</div>
                            <div class="col-md-6">
                                <input type="text" class="uppercase" id="mainCode" onkeyup="validateCharacter(this)"
                                       onblur="checkMainCode(this),validateMasterCode(this,'MAIN_CODE')"
                                       name="mcatCode"  required="required" value="${mainCat.mcatCode}"
                                       style="width: 100%"/>
                            </div>
                            <div class="col-md-2"></div>

                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 20px">Description</div>
                            <div class="col-md-6">
                                <textarea name="mcatDes" maxlength="50" required="required"
                                          style="width: 100%">${mainCat.mcatDes}</textarea>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                        <hr/>
                        <div class="row">
                            <div class="col-md-3">Asset Types</div>
                            <div class="col-md-9">
                                <div>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <input type="checkbox" name="typeLand" ${typeLand}><label>Land</label>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="checkbox" name="typeLandBuilding" ${typeLandBuilding}><label>Land
                                            &
                                            Building</label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-5">
                                            <input type="checkbox"
                                                   name="typeVehicle" ${typeVehicle}><label>Vehicle</label>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="checkbox"
                                                   name="typeComputer" ${typeComputer}><label>Computer</label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-5">
                                            <input type="checkbox"
                                                   name="typeFurniture" ${typeFurniture}><label>Furniture</label>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="checkbox"
                                                   name="typePlantMachinary" ${typePlantMachinary}><label>Plant
                                            &
                                            Machinary</label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-5">
                                            <input type="checkbox" name="typeLabEquipment" ${typeLabEquipment}><label>LAB
                                            Equipment</label>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="checkbox"
                                                   name="typeOfficeEquipment" ${typeOfficeEquipment}><label>Office
                                            Equipment</label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-5">
                                            <input type="checkbox"
                                                   name="typeTeachingEquipment" ${typeTeachingEquipment}><label>Teaching
                                            Equipment</label>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="checkbox" name="typeFixtures" ${typeFixtures}><label>Fixtures &
                                            Fittings</label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-5">
                                            <input type="checkbox" name="typeLibraryBooks" ${typeLibraryBooks}><label>Library
                                            Books</label>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="checkbox"
                                                   name="typeSportEquipment" ${typeSportEquipment}><label>Sport
                                            Equipment</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <input type="checkbox"
                                                   name="typeSoftware" ${typeSoftware}><label>Software</label>
                                        </div>
                                    </div>
                                </div>
                                <%--<select class="chosen" id="assetTypeId"--%>
                                <%--name="assetTypeId" style="width: 100%">--%>
                                <%--<option value="0">---SELECT---</option>--%>
                                <%--&lt;%&ndash;<c:forEach var="asset" items="${assetsType}">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<option value="${asset.id}">${asset.type}</option>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                                <%--</select>--%>
                            </div>
                            <div class="col-md-2"></div>
                        </div>


                        <%--<div class="row"><label>Select Assets Types</label></div>--%>
                        <%--<div class="row" id="divAssets" style="background: #0b56a8">--%>
                        <%--<div class="col-md-12">--%>
                        <%--<select name="multiAssets" multiple="multiple" id="bulkAsset"  class="active">--%>
                        <%--<c:forEach var="assets" items="${assets}">--%>
                        <%--<input type="checkbox" value="${assets.id}"><label>${assets.type}</label>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--</div>--%>
                        <%--</div>--%>


                        <%--<div id="assetTypesId" name="multiAssets" multiple="multiple" var="assets" class="active ">--%>

                        <%--</div>--%>

                        <div class="row" style="margin-top: 15px">
                            <div class="col-md-4"></div>
                            <div class="col-md-6 col-sm-12">
                                <div class="row">
                                    <div class="col-md-6 col-sm-6">
                                        <a class="btn btn-primary"
                                           style="width: 100%; margin-top:3px;height: 30px; font-size: 14px"
                                           href="${root}/category/mainCategory">New</a>
                                    </div>
                                    <div class="col-md-6 col-sm-6">
                                        <c:choose>
                                            <c:when test="${mainCat.mcatId > 0 }">
                                                <button type="submit" class="btn btn-primary btnSubmit"
                                                        style="width: 100%; height: 30px; font-size: 14px">
                                                    Update
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-primary btnSubmit"
                                                        style="width: 100%; height: 30px; font-size: 14px">
                                                    Save
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
    <div class="col-md-5 col-sm-12">
        <table id="dataTable" class="display compact" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>Main Category Code</th>
                <th>Description</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="mainCategory" items="${mainCategory}">
                <tr id="${mainCategory.mcatId}">
                    <td>${mainCategory.mcatCode}</td>
                    <td>${mainCategory.mcatDes}</td>
                    <td><a href="${root}/category/editMainCategory/${mainCategory.mcatId}"><i
                            class="fa fa-pencil-square-o"></i></a></td>
                    <td><a onclick="onDelete(this)"><i class="fa fa-trash-o"></i></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--href="${root}/category/editMainCategory/${mainCategory.mcatId}"--%>
    </div>
</div>


<form id="editForm"></form>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="isEdit" value="${isEdit}"/>

<script type="text/javascript">

    // loadAssetTypes();
    $(window).load(function () {
// Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    //    $('.btnSubmit').click(function (e) {
    //        $(".se-pre-con").show();
    //    });

    $(document).ready(function () {
        $('#mainCode').attr('maxlength',MAIN_CODE);
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
            $('#mainCode').prop('readonly', true);
        }
        if (pageType == 1) {

            if (status == 'true') {
                swal({
                    title: 'success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./mainCategory";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Main Category Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./mainCategory";
                })
            }
        }
    });

    function checkMainCode(code) {
        var mainCode = code.value;
        var isEdit = $("#isEdit").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/category/checkMainCode/' + mainCode,
                success: function (data) {
                    if (!data) {
                        // $("#mainCode").focus();
                        $("#mainCode").addClass("txtError");
                        $("#mainCode").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate Main Category code !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#mainCode").removeClass("txtError");
                    }
                },
                error: function () {

                }
            });
        }

    }


    function clickToEdit(id) {
        $.ajax({
            url: '${root}/category/editableToMainCode/' + id,
            success: function (data) {
                if (data) {
                    $('#editForm').attr('action', '${root}/category/editMainCategory/' + id);
                    $('#editForm').submit();
                } else {
                    swal({
                        title: 'Warning',
                        text: 'Can\'t Change Main Category code !!',
                        type: 'warning',
                        timer: 1000
                    })
                }
            }
        });
    }

    <%--function loadAssetTypes() {--%>
    <%--$.ajax({--%>
    <%--url: '${root}/category/loadAssetType',--%>
    <%--success: function (data) {--%>
    <%--for (var i = 0; i < data.length; i=i+2) {--%>
    <%--$("#assetTypesId").append(--%>
    <%--"<div class=\"row\">"+--%>
    <%--"<div class=\"col-md-5\">" +--%>
    <%--"<input type='checkbox'>" + "<label value = '" + data[i].id + "'>" + data[i].type + "</label>"+--%>
    <%--"</div>"--%>
    <%--);--%>

    <%--if(i+1 < data.length) {--%>
    <%--$("#assetTypesId").append(--%>
    <%--"<div class=\"col-md-5\">" +--%>
    <%--"<input type='checkbox'>" + "<label value = '" + data[i + 1].id + "'>" + data[i + 1].type +"</label>"+--%>
    <%--"</div> "--%>
    <%--);--%>
    <%--}--%>

    <%--$("#assetTypesId").append(--%>
    <%--"</div>"--%>
    <%--);--%>


    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

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
                url: '${root}/category/deleteMainCategory/' + trId,
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
                        window.location.href = "./mainCategory";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Main Category already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Main Category!!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }


</script>