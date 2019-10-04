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
            <form name="buildingForm" id="buildingFormID"
                  action="${root}/location/saveBuilding" method="post">

                <div class="row" style="margin: 0">
                    <legend>Building Details</legend>
                </div>
                <input type="hidden" name="id" value="${building.id}"/>
                <div class="row">

                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">City</div>
                            <div class="col-md-6">
                                <select id="cityID" required name="city.cityId" style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="city" items="${citys}">
                                        <c:choose>
                                            <c:when test="${city.cityId == building.city.cityId}">
                                                <option selected="selected"
                                                        value="${building.city.cityId}">${building.city.cityCode}
                                                    - ${building.city.description}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${city.cityId}">${city.cityCode}
                                                    - ${city.description}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>

                            </div>

                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Building</div>
                            <div class="col-md-6">
                                <input type="text" class="uppercase" id="buildingCode" onkeyup="validateCharacter(this)"
                                       onblur="checkBuildingCode(this),validateMasterCode(this,'BUILDING_CODE')"
                                       value="${building.buildingCode}" required="required" maxlength="3" width="100"
                                       name="buildingCode"
                                       style="width: 100%"/>
                            </div>

                            <div class="col-md-2"></div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 30px">Description</div>
                            <div class="col-md-6">
                                <textarea name="description" required="required" maxlength="50"
                                          style="width: 100%">${building.description}</textarea>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row" style="margin-top: 15px">
                            <div class="col-md-4"></div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; margin-top:3px;height: 30px; font-size: 14px" href="${root}/location/building">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <c:choose>
                                            <c:when test="${building.id > 0 }">
                                                <button onclick="$('#cityID').attr('disabled',false);" class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px">
                                                    Update
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px">Save
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
        <table id="dataTable" class="display compact" width="100%"
               cellspacing="0">
            <thead>
            <tr>
                <th>City Code</th>
                <th>Building Code</th>
                <th>Description</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="buildings" items="${buildings}">
                <tr id="${buildings.id}">
                    <c:forEach var="citys" items="${citys}">
                        <c:if
                                test="${citys.cityId == buildings.city.cityId}">
                            <td>${citys.cityCode}</td>
                        </c:if>
                    </c:forEach>
                    <td>${buildings.buildingCode}</td>
                    <td>${buildings.description}</td>
                    <td><a href="${root}/location/editBuilding/${buildings.id}"><i
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
        $('#buildingCode').attr('maxlength',BUILDING_CODE);
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
            $('#buildingCode').prop('readonly', true);
            $("#cityID").attr('disabled',true);
        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Success!',
                    type: 'success',
                    timer: 1000
                });
                window.location.href = "./building";
            } else {
                swal({
                    title: 'Error !',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                });
                window.location.href = "./building";
            }
        }
    });


    function checkBuildingCode(code) {
        var buildingCode = code.value;
        var cityId = $("#cityID").find("option:selected").val();
        var isEdit = $("#isEdit").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/location/checkBuildingCode/' + buildingCode + '/' + cityId,
                success: function (data) {
                    if (!data) {
                        // $("#buildingCode").focus();
                        $("#buildingCode").addClass("txtError");
                        $("#buildingCode").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate Buiding code !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#buildingCode").removeClass("txtError");
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
                url: '${root}/location/deleteBuilding/' + trId,
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
                        window.location.href = "./building";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Building Code already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Building Code !!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }
</script>