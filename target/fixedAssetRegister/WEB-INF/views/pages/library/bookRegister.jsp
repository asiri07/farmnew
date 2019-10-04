<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 10/22/2017
  Time: 7:46 AM
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
    <div class="col-md-12" style="width: 98%">
        <div class="page">
            <form name="bookForm"
                  action="${root}/library/saveBook" method="POST">
                <div class="row" style="margin: 0">
                    <legend>Additional Detail(Books)</legend>
                </div>
                <%--<input type="hidden" name="comId" value="${company.comId}"/>--%>
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4">Accession No.</div>
                            <div class="col-md-6"><input name="accessionNo" style="width: 100%" value=""/></div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">MFN No.</div>
                            <div class="col-md-6"><input name="mfnNo" style="width: 100%" value=""/></div>
                            <div class="col-md-2"></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4">ID</div>
                            <div class="col-md-6"><input name="pgimId" style="width: 100%" value=""/></div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">Date</div>
                            <div class="col-md-6">
                                <div class='input-group date' id='datepicker1'>
                                    <input type='text' class="form-control" style="width: 70%" required="required" name="date"/>
                                    <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                                <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                                </span>
                                </div>
                                <div class="col-md-2"></div>
                            </div>
                            <script type="text/javascript">
                                $(function () {
                                    $('#datepicker1').datepicker({
                                        format: 'dd/mm/yyyy',
                                        autoclose: true
                                        // locale: 'ru'
                                    });
                                });
                            </script>
                        </div>

                    </div>
                </div>

                <div class="row">
                    <div class="col-md-2">Title</div>
                    <div class="col-md-9"><input name="title" style="width: 100%" value=""/></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-2">Author 1</div>
                    <div class="col-md-9"><input name="author1" style="width: 100%" value=""/></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-2">Auther 2</div>
                    <div class="col-md-9"><input name="author2" style="width: 100%" value=""/></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-2">Auther 3</div>
                    <div class="col-md-9"><input name="author3" style="width: 100%" value=""/></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-2">Auther 4</div>
                    <div class="col-md-9"><input name="author4" style="width: 100%" value=""/></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-2">Editors</div>
                    <div class="col-md-9"><input name="editors" style="width: 100%" value=""/></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-2">Place of Publication</div>
                    <div class="col-md-9"><input name="placeOfPublication" style="width: 100%" value=""/></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-2">Publisher</div>
                    <div class="col-md-9"><input name="publisher" style="width: 100%" value=""/></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4">Date of Publication</div>
                            <div class="col-md-8">
                                <div class='input-group date' id='datepicker2'>
                                    <input type='text' class="form-control"  style="width: 70%" required="required" name="dateOfPublication"/>
                                    <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                                <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                                </span>
                                </div>
                            </div>
                            <script type="text/javascript">
                                $(function () {
                                    $('#datepicker2').datepicker({
                                        format: 'dd/mm/yyyy',
                                        autoclose: true
                                        // locale: 'ru'
                                    });
                                });
                            </script>
                        </div>
                        <div class="row">
                            <div class="col-md-4">Series</div>
                            <div class="col-md-8"><input name="series" style="width: 100%" value=""/></div>

                        </div>

                        <div class="row">
                            <div class="col-md-4">Price</div>
                            <div class="col-md-8"><input name="price" style="width: 100%" value=""/></div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">Currency and Rate</div>
                            <div class="col-md-8"><input name="currencyRate" style="width: 100%" value=""/></div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">Price Sri Lankan Rupees</div>
                            <div class="col-md-8"><input name="priceRs" style="width: 100%" value=""/></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4">Source</div>
                            <div class="col-md-8">
                                <select name="source" id="" style="width: 100%">
                                    <option>--SELECT--</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4">Purchase Order No.</div>
                            <div class="col-md-8">
                                <input name="purchaseOrderNo" style="width: 100%"/>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-2">Location</div>
                            <div class="col-md-8"><input name="location" style="width: 100%" value=""/></div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">Edition</div>
                            <div class="col-md-8"><input name="edition" style="width: 100%" value=""/></div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">ISBN</div>
                            <div class="col-md-8"><input name="isbn" style="width: 100%" value=""/></div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">Class No.</div>
                            <div class="col-md-8"><input name="classNo" style="width: 100%" value=""/></div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-2">Supplier's Name</div>
                            <div class="col-md-8"><input name="supplierName" style="width: 100%"/></div>
                            <div class="col-md-2"></div>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">Contents</div>
                    <div class="col-md-9"><textarea name="contents" style="width: 100%"></textarea></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <%--<div class="col-md-4">--%>
                                <%--<button type="button" class="btn btn-primary" style="width: 100%">Help</button>--%>
                            <%--</div>--%>
                            <div class="col-md-6">
                                <a class="btn btn-primary" style="width: 80%; font-size: 14px; margin-top:3px; margin-left: 50px" href="${root}/library/bookRegistration">New</a>
                            </div>
                            <div class="col-md-6">
                                <button type="submit" class="btn btn-primary" style="width: 80%; font-size: 14px; ">Save</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </form>
        </div>
    </div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<script type="text/javascript">
    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('#btnSubmit').click(function () {
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
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'success!',
                    type: 'success',
                    timer: 2000
                }).then(function () {
                    window.location.href = "./bookRegistration";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Company Added Fail !!',
                    type: 'error',
                    timer: 2000
                }).then(function () {
                    window.location.href = "./bookRegistration";
                })
            }
        }

    });

    $(function () {
        $('.date').datepicker({
            format: 'mm/dd/yyyy',
            startDate: '-3d'
        });
    });



</script>