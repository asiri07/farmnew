<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 9/17/2017
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
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
</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="row">
    <div class="col-md-7">
        <div class="page">
            <form name="locationForm" id="locationFormID"
                  action="${root}/allocation/saveLocation" method="post">

                <div class="row" style="margin: 0">
                    <legend>Location Details</legend>
                </div>
                <input type="hidden" name="locId" value="${location.locId}"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Company Code</div>
                            <div class="col-md-6">
                                <select name="" class="chosen" required id="companyID" onblur="loadDepartments()"
                                        style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="companyList" items="${companyList}">
                                        <c:choose>
                                            <c:when test="${companyList.comId == location.sectionMaster.departmentMaster.companyMaster.comId}">
                                                <option selected="selected"
                                                        value="${location.sectionMaster.departmentMaster.companyMaster.comId}">${location.sectionMaster.departmentMaster.companyMaster.comCode}
                                                    - ${location.sectionMaster.departmentMaster.companyMaster.comDes}</option>
                                                <option value="${companyList.comId}">${companyList.comCode}
                                                    - ${companyList.comDes}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${companyList.comId}">${companyList.comCode}
                                                    - ${companyList.comDes}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Department Code (Branch)</div>
                            <div class="col-md-6">
                                <select name="" required id="departmentId" onblur="loadSections()" style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:if test="${location.sectionMaster.departmentMaster.depId > 0}">
                                        <option value="${location.sectionMaster.departmentMaster.depId}"
                                                selected="selected">${location.sectionMaster.departmentMaster.depCode}
                                            - ${location.sectionMaster.departmentMaster.depDes}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Section Code</div>
                            <div class="col-md-6">
                                <select required name="sectionMaster.secId" id="sectionId" style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:if test="${location.sectionMaster.secId > 0}">
                                        <option value="${location.sectionMaster.secId}"
                                                selected="selected">${location.sectionMaster.secCode}
                                            - ${location.sectionMaster.secDes}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">City Code</div>
                            <div class="col-md-6">
                                <select id="cityID" onblur="loadBuilding()" required style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="citys" items="${citys}">
                                        <c:choose>
                                            <c:when test="${citys.cityId == location.room.floor.building.city.cityId}">
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
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Building Code</div>
                            <div class="col-md-6">
                                <select id="buildingId" onblur="loadFlow()" required style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:if test="${location.room.floor.building.id > 0}">
                                        <option selected="selected"
                                                value="${location.room.floor.building.id}">${location.room.floor.building.buildingCode}
                                            - ${location.room.floor.building.description}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Floor Code</div>
                            <div class="col-md-6">
                                <select id="flowId" onblur="loadRoom()" required style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:if test="${location.room.floor.id > 0}">
                                        <option selected="selected"
                                                value="${location.room.floor.id}">${location.room.floor.floorCode}
                                            - ${location.room.floor.description}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="col-md-2"></div>
                        </div>


                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Room Code</div>
                            <div class="col-md-6">
                                <select id="roomId" onblur="generateLocationCode()" required name="room.id"
                                        style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:if test="${location.room.id > 0}">
                                        <option selected="selected"
                                                value="${location.room.id}">${location.room.roomCode}
                                            - ${location.room.description}</option>
                                    </c:if>
                                </select>
                            </div>

                            <div class="col-md-2"></div>
                        </div>


                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Location Code</div>
                            <div class="col-md-6">
                                <input type="text" id="locCode" readonly class="uppercase"
                                       onblur="checkLocationCode(this)" value="${location.locCode}" required="required"
                                       width="100" name="locCode"
                                       style="width: 100%"/>
                            </div>

                            <div class="col-md-2"></div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 30px">Description</div>
                            <div class="col-md-6">
                                <textarea name="locDes" required="required" maxlength="50"
                                          style="width: 100%">${location.locDes}</textarea>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-6">

                                <div class="row" style="margin-top: 15px">

                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; margin-top:3px;height: 30px; font-size: 14px"
                                           href="${root}/allocation/location">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <c:choose>
                                            <c:when test="${location.locId > 0 }">
                                                <button onclick="enabledElements()" class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px">
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
                <th>Location Code</th>
                <th>Description</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="locations" items="${locations}">
                <tr id="${locations.locId}">
                    <td>${locations.locCode}</td>
                    <td>${locations.locDes}</td>
                    <td><a href="${root}/allocation/editLocation/${locations.locId}"><i
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
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": false
        });
    });

    function enabledElements(){
        $('#companyID').attr('disabled', false);
        $('#departmentId').attr('disabled', false);
        $('#sectionId').attr('disabled', false);
        $('#cityID').attr('disabled', false);
        $('#buildingId').attr('disabled', false);
        $('#flowId').attr('disabled', false);
        $('#roomId').attr('disabled', false);
    }

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var isEdit = $("#isEdit").val();
        if (isEdit == 1) {
            $('#companyID').attr('disabled', true);
            $('#departmentId').attr('disabled', true);
            $('#sectionId').attr('disabled', true);
            $('#cityID').attr('disabled', true);
            $('#buildingId').attr('disabled', true);
            $('#flowId').attr('disabled', true);
            $('#roomId').attr('disabled', true);

        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./location";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./location";
                })
            }
        }

    });

    function loadDepartments() {
        $("#departmentId").empty();
        var comId = $("#companyID").find("option:selected").val();
        $.ajax({
            url: '${root}/allocation/getDepartments/' + comId,
            success: function (data) {
                $("#departmentId").append(
                    "<option value = ''> ---SELECT--- </option>");
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#departmentId").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1] + " - " + subCat[2]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select company !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }


    function loadSections() {
        $("#sectionId").empty();
        var departmentId = $("#departmentId").find("option:selected").val();
        $.ajax({
            url: '${root}/allocation/getSections/' + departmentId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#sectionId").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1] + " - " + subCat[2]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select department !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }

    //
    //    $( "#locationFormID" ).submit(function( event ) {
    //        var comId = $("#companyID").find("option:selected").val();
    //        var depaId = $("#departmentId").find("option:selected").val();
    //        var secId = $("#sectionId").find("option:selected").val();
    //        if(comId > 0 && depaId > 0) {
    //            if(secId > 0) {
    //                $("#sectionId").removeClass("txtError");
    //                $("#locationFormID").submit();
    //            } else {
    //                $("#sectionId").addClass("txtError");
    //                $("#sectionId").focus();
    //            }
    //            $("#companyID").removeClass("txtError");
    //            $("#departmentId").removeClass("txtError");
    //        }else {
    //            if(comId == 0) {
    //                $("#companyID").addClass("txtError");
    //                $("#companyID").focus();
    //
    //            } else if (depaId == 0) {
    //                $("#departmentId").addClass("txtError");
    //                $("#departmentId").focus();
    //            } else {
    //                $("#departmentId").removeClass("txtError");
    //            }
    //        }
    //        event.preventDefault();
    //    });

    function checkLocationCode(code) {
        var locCode = code.value;
        var secId = $("#sectionId").find("option:selected").val();
        var isEdit = $("#isEdit").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/allocation/checkLocationCode/' + locCode + "/" + secId,
                success: function (data) {
                    if (!data) {
                        // $("#locCode").focus();
                        $("#locCode").addClass("txtError");
                        $("#locCode").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate Location code !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#locCode").removeClass("txtError");
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
                url: '${root}/allocation/deleteLocation/' + trId,
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
                        window.location.href = "./location";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Location Code already used !!',
                            type: 'warning',
                            timer: 1000
                    })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Location Code !!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }

    function loadBuilding() {
        $("#buildingId").empty();
        var cityID = $("#cityID").find("option:selected").val();
        $.ajax({
            url: '${root}/location/getBuildingByCity/' + cityID,
            success: function (data) {
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

    function loadFlow() {
        $("#flowId").empty();
        var buildingId = $("#buildingId").find("option:selected").val();
        $.ajax({
            url: '${root}/location/getFlowByBuilding/' + buildingId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#flowId").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1] + " - " + subCat[2]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select Building !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }

    function loadRoom() {
        $("#roomId").empty();
        var flowId = $("#flowId").find("option:selected").val();
        $.ajax({
            url: '${root}/location/getRoomByFlow/' + flowId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#roomId").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1] + " - " + subCat[2]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select Floor !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }

    function generateLocationCode() {
        $("#locCode").val();
        var dep = $("#departmentId").find("option:selected").text();
        var sec = $("#sectionId").find("option:selected").text();
        var city = $("#cityID").find("option:selected").text();
        var building = $("#buildingId").find("option:selected").text();
        var flow = $("#flowId").find("option:selected").text();
        var room = $("#roomId").find("option:selected").text();

        var depCode = dep.split(" - ")[0];
        var secCode = sec.split(" - ")[0];
        var cityCode = city.split(" - ")[0];
        var buildingCode = building.split(" - ")[0];
        var flowCode = flow.split(" - ")[0];
        var roomCode = room.split(" - ")[0];
        var roomId = $("#roomId").find("option:selected").val();

        if (roomId != "") {
            var code = cityCode.trim() + buildingCode.trim() + flowCode.trim() + roomCode.trim() + depCode.trim() + secCode.trim();
            $("#locCode").val(code.toUpperCase());
            $('#locCode').prop('readonly', true);
        }
    }


</script>