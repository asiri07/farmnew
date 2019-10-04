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
            <form name="departmentForm" id="departmentFormID"
                  action="${root}/allocation/saveDepartment" method="post">

                <div class="row" style="margin: 0">
                    <legend>Department / Branch Details</legend>
                </div>
                <input type="hidden" name="depId" value="${department.depId}"/>
                <div class="row">

                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Company</div>
                            <div class="col-md-6">
                                <select id="companyID" required name="companyMaster.comId" style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="companyList" items="${companyList}">
                                        <c:choose>
                                            <c:when test="${companyList.comId == department.companyMaster.comId}">
                                                <option selected="selected"
                                                        value="${department.companyMaster.comId}">${department.companyMaster.comCode}
                                                    - ${department.companyMaster.comDes}</option>
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
                            <div class="col-md-4" style="padding-top: 10px">Department / Branch</div>
                            <div class="col-md-6">
                                <input type="text" class="uppercase" id="depCode" onkeyup="validateCharacter(this)"
                                       onblur="checkDepartmentCode(this),validateMasterCode(this,'DEPARTMENT_CODE')"
                                       value="${department.depCode}" required="required" maxlength="3" width="100"
                                       name="depCode"
                                       style="width: 100%"/>
                            </div>

                            <div class="col-md-2"></div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 20px">Description</div>
                            <div class="col-md-6">
                                <textarea name="depDes" required="required" maxlength="50"
                                          style="width: 100%">${department.depDes}</textarea>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-6">

                                <div class="row" style="margin-top: 15px">

                                    <div class="col-md-6">
                                        <a class="btn btn-primary"
                                           style="width: 100%; margin-top:3px;height: 30px; font-size: 14px"
                                           href="${root}/allocation/department">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <c:choose>
                                            <c:when test="${department.depId > 0 }">
                                                <button onclick="$('#companyID').attr('disabled',false);" class="btn btn-primary"
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
                <th>Company Code</th>
                <th>Department Code</th>
                <th>Description</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="departments" items="${departments}">
                <tr id="${departments.depId}">
                    <c:forEach var="companyList" items="${companyList}">
                        <c:if
                                test="${companyList.comId == departments.companyMaster.comId}">
                            <td>${companyList.comCode}</td>
                        </c:if>
                    </c:forEach>
                    <td>${departments.depCode}</td>
                    <td>${departments.depDes}</td>
                    <td><a href="${root}/allocation/editDepartment/${departments.depId}"><i
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
        $('#depCode').attr('maxlength',DEPARTMENT_CODE);
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
            $('#depCode').prop('readonly', true);
            $("#companyID").attr('disabled',true);
        }
        if (pageType == 1) {

            if (status == 'true') {
                swal({
                    title: 'Success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./department";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Department Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./department";
                })
            }
        }

    });
    //
    //    $( "#departmentFormID" ).submit(function( event ) {
    //        var comId = $("#companyID").find("option:selected").val();
    //        if(comId > 0) {
    //            $("#departmentFormID").submit();
    //        }else {
    //            $("#companyID").addClass("txtError");
    //            $("#companyID").focus();
    //        }
    //        event.preventDefault();
    //    });

    function checkDepartmentCode(code) {
        var depCode = code.value;
        var comId = $("#companyID").find("option:selected").val();
        var isEdit = $("#isEdit").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/allocation/checkDepartmentCode/' + depCode + '/' + comId,
                success: function (data) {
                    if (!data) {
                        // $("#depCode").focus();
                        $("#depCode").addClass("txtError");
                        $("#depCode").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate Department code !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#depCode").removeClass("txtError");
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
                url: '${root}/allocation/deleteDepartment/' + trId,
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
                        window.location.href = "./department";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Department Code already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Department Code !!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }


</script>