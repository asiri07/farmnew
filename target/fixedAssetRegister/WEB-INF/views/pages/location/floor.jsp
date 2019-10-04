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
            <form id="floorForm"
                  action="${root}/location/saveFloor" method="post">

                <div class="row" style="margin: 0">
                    <legend>Floor Details</legend>
                </div>
                <input type="hidden" name="id" value="${floor.id}"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">City</div>
                            <div class="col-md-6">
                                <select id="cityID" class="chosen" onchange="loadBuilding()" required
                                        style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="citys" items="${citys}">
                                        <c:choose>
                                            <c:when test="${citys.cityId == floor.building.city.cityId}">
                                                <option selected="selected" value="${citys.cityId}">${citys.cityCode}
                                                    - ${citys.description}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${citys.cityId}">${citys.cityCode}
                                                    - ${citys.description}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Building</div>
                            <div class="col-md-6">
                                <select id="buildingId" required name="building.id" style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:if test="${floor.building.id > 0}">
                                        <option selected="selected"
                                                value="${floor.building.id}">${floor.building.buildingCode}
                                            - ${floor.building.description}</option>
                                    </c:if>
                                </select>
                            </div>

                            <div class="col-md-3"></div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Floor</div>
                            <div class="col-md-6">
                                <input type="text" class="uppercase" onkeyup="validateCharacter(this)"
                                       onchange="checkFlowCode(this),validateMasterCode(this,'FLOOR_CODE')"
                                       value="${floor.floorCode}"  required="required" id="floorCode"
                                       style="width: 100%" maxlength="3"
                                       name="floorCode"/>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Description</div>
                            <div class="col-md-6">
                                <input type="text" value="${floor.description}" maxlength="50" required="required"
                                       style="width: 100%" name="description"/>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row" style="margin-top: 15px">
                            <div class="col-md-4"></div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; margin-top:3px; height: 30px; font-size: 14px" href="${root}/location/floor">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <c:choose>
                                            <c:when test="${floor.id > 0 }">
                                                <button onclick="$('#cityID').attr('disabled',false); $('#buildingId').attr('disabled',false)"
                                                        class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px">
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
                                <div class="col-md-2"></div>

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
                <th>Floor Code</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="floor" items="${floors}">
                <tr id="${floor.id}">
                    <c:forEach var="city" items="${citys}">
                        <c:if test="${city.cityId == floor.building.city.cityId}">
                            <td>${city.cityCode}</td>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="building" items="${buildings}">
                        <c:if test="${building.id == floor.building.id}">
                            <td>${building.buildingCode}</td>
                        </c:if>
                    </c:forEach>
                    <td>${floor.floorCode}</td>
                    <td><a href="${root}/location/editFloor/${floor.id}"><i class="fa fa-pencil-square-o"></i></a></td>
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
        $('#floorCode').attr('maxlength',FLOOR_CODE);
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
            $('#floorCode').prop('readonly', true);
            $("#cityID").attr('disabled',true);
            $("#buildingId").attr('disabled',true);
        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Success!',
                    type: 'success',
                    timer: 1000
                });
                window.location.href = "./floor";
            } else {
                swal({
                    title: 'Error !',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                });window.location.href = "./floor";
            }
        }

    });

    function loadBuilding() {
        $("#buildingId").empty();
        var cityID = $("#cityID").find("option:selected").val();

        $.ajax({
            url: '${root}/location/getBuildingByCity/' + cityID,
            success: function (data) {
                $("#buildingId").append(
                    "<option value = ''> ---SELECT--- </option>");
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#buildingId").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1] + " - " + subCat[2]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select City !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }


    function checkFlowCode(code) {
        var floorCode = code.value;
        var cityID = $("#cityID").find("option:selected").val();
        var buildID = $("#buildingId").find("option:selected").val();
        var isEdit = $("#isEdit").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/location/checkFloorCode/' + floorCode + '/' + cityID + '/' + buildID,
                success: function (data) {
                    if (!data) {
                        // $("#floorCode").focus();
                        $("#floorCode").addClass("txtError");
                        $("#floorCode").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate Floor code !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#floorCode").removeClass("txtError");
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
                url: '${root}/location/deleteFloor/' + trId,
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
                        window.location.href = "./floor";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Floor Code already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Section Code !!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }


</script>