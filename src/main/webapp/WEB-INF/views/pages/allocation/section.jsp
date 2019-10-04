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
            <form id="sectionFormID"
                  action="${root}/allocation/saveSection" method="post">

                <div class="row" style="margin: 0">
                    <legend>Section Details</legend>
                </div>
                <input type="hidden" name="secId" value="${section.secId}"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Company</div>
                            <div class="col-md-6">
                                <select id="companyId" class="chosen" required onchange="loadDepartments()" name=""
                                        style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="companyList" items="${companyList}">
                                        <c:choose>
                                            <c:when test="${companyList.comId == section.departmentMaster.companyMaster.comId}">
                                                <option selected="selected"
                                                        value="${section.departmentMaster.companyMaster.comId}">${section.departmentMaster.companyMaster.comCode}
                                                    - ${section.departmentMaster.companyMaster.comDes}</option>
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
                            <div class="col-md-4" style="padding-top: 10px">Department</div>
                            <div class="col-md-6">
                                <select required id="departmentId" name="departmentMaster.depId" style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:if test="${section.departmentMaster.depId > 0}">
                                        <option selected="selected"
                                                value="${section.departmentMaster.depId}">${section.departmentMaster.depCode}
                                            - ${section.departmentMaster.depDes}</option>
                                    </c:if>
                                </select>
                            </div>

                            <div class="col-md-2"></div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Section</div>
                            <div class="col-md-6">
                                <input type="text" class="uppercase" id="secCode" onkeyup="validateCharacter(this)"
                                       onblur="checkSectionCode(this),validateMasterCode(this,'SECTION_CODE')"
                                       value="${section.secCode}" maxlength="3" required="required" style="width: 100%"
                                       name="secCode"/>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 30px">Description</div>
                            <div class="col-md-6">
                                <textarea name="secDes" required="required" maxlength="50"
                                          style="width: 100%">${section.secDes}</textarea>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row" style="margin-top: 15px">
                            <div class="col-md-4"></div>
                            <div class="col-md-6">
                                <div class="row">

                                    <div class="col-md-6">
                                        <a class="btn btn-primary"
                                           style="width: 100%; margin-top:3px;height: 30px; font-size: 14px"
                                           href="${root}/allocation/section">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <c:choose>
                                            <c:when test="${section.secId > 0 }">
                                                <button onclick=" $('#departmentId').attr('disabled', false);" class="btn btn-primary"
                                                        style="width: 100%;height: 30px; font-size: 14px">
                                                    Update
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-primary"
                                                        style="width: 100%;height: 30px; font-size: 14px">Save
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-3"></div>

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
                <%--<th>Company Code</th>--%>
                <th>Department Code</th>
                <th>Section Code</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="sections" items="${sections}">
                <tr id="${sections.secId}">
                        <%--<c:forEach var="companyList" items="${companyList}">--%>
                        <%--<c:if test="${companyList.comId == sections.departmentMaster.companyMaster.comId}">--%>
                        <%--<td>${companyList.comCode}</td>--%>
                        <%--</c:if>--%>
                        <%--</c:forEach>--%>
                    <c:forEach var="departments" items="${departments}">
                        <c:if test="${departments.depId == sections.departmentMaster.depId}">
                            <td>${departments.depCode}</td>
                        </c:if>
                    </c:forEach>
                    <td>${sections.secCode}</td>
                    <td><a href="${root}/allocation/editSection/${sections.secId}"><i class="fa fa-pencil-square-o"></i></a>
                    </td>
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
        $('#secCode').attr('maxlength',SECTION_CODE);
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
            $('#secCode').prop('readonly', true);
            $("#companyId").attr('disabled', true);
            $('#departmentId').attr('disabled', true);
            // $('#departmentId').find('option[value="<required-value>"]').attr('selected','selected');
        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./section";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./section";
                })
            }
        }

    });

    function loadDepartments() {
        $("#departmentId").empty();
        var comId = $("#companyId").find("option:selected").val();
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

    //
    //    $( "#sectionFormID" ).submit(function( event ) {
    //        var comId = $("#companyId").find("option:selected").val();
    //        var depaId = $("#departmentId").find("option:selected").val();
    //        if(comId > 0 && depaId > 0) {
    //            $("#sectionFormID").submit();
    //        }else {
    //            if(comId == 0) {
    //                $("#companyId").addClass("txtError");
    //                $("#d").focus();
    //
    //            } else if (depaId == 0) {
    //                $("#departmentId").addClass("txtError");
    //                $("#departmentId").focus();
    //            }
    //        }
    //        event.preventDefault();
    //    });

    function checkSectionCode(code) {
        var secCode = code.value;
        var depaId = $("#departmentId").find("option:selected").val();
        var isEdit = $("#isEdit").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/allocation/checkSectionCode/' + secCode + '/' + depaId,
                success: function (data) {
                    if (!data) {
                        // $("#secCode").focus();
                        $("#secCode").addClass("txtError");
                        $("#secCode").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate Section code !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#secCode").removeClass("txtError");
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
                url: '${root}/allocation/deleteSection/' + trId,
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
                        window.location.href = "./section";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Section Code already used !!',
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