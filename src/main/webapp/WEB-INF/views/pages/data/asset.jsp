<%--
Created by IntelliJ IDEA.
User: MsD
Date: 10/7/2017
Time: 7:46 AM
To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>

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

    .redMsg {
        color: #E50000;
    }

    [src=''] {
        visibility: hidden;
    }

    .ui-autocomplete {
        max-height: 450px;
        max-width: 350px;
        overflow-y: auto;
        /* prevent horizontal scrollbar */
        overflow-x: hidden;
        /* add padding to account for vertical scrollbar */
        padding-right: 20px;
    }


</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="row">
    <div class="col-md-8">
        <div class="page">
            <form id="assetFormID"
                  action="${root}/data/saveAsset" method="post" enctype="multipart/form-data">
                <div class="row" style="margin: 0">
                    <legend>Asset Registration</legend>
                </div>

                <div class="row">
                    <div class="col-md-12">

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Transaction No.</div>
                            <div class="col-md-5">
                                <input id="searchTransactionNo" name="searchTransactionNo"
                                       style="height:35px;width:100%" type="text" autocomplete="off"
                                       class="form-control" data-maxitems="100"
                                       placeholder="Enter Transaction No. " onblur="checkTrNoValid(),isTrEmpty()">
                                <input id="TrId" name="TrId" hidden>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Main Category Code <label
                                    class="redMsg">*</label></div>
                            <div class="col-md-5">
                                <select id="mainCategoryId" required class="select_style" onblur="loadSubCategory()"
                                        name=""
                                        style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach var="mainCats" items="${mainCategory}">
                                        <%--<c:if test="${asset.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatId == mainCats.mcatId}">--%>
                                        <%--<option selected value="${mainCats.mcatId}">${mainCats.mcatCode}--%>
                                        <%--- ${mainCats.mcatDes}</option>--%>
                                        <%--</c:if>--%>
                                        <option value="${mainCats.mcatId}">${mainCats.mcatCode}
                                            - ${mainCats.mcatDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Sub Category Code<label
                                    class="redMsg">*</label></div>
                            <div class="col-md-5">
                                <select id="subCategoryId" required class="select_style" onblur="loadDetailCategory()"
                                        name=""
                                        style="width: 100%">
                                    <c:if test="${asset.assetCatergoryDetail.assetCatergorySub.scatId > 0}">
                                        <option selected
                                                value="${asset.assetCatergoryDetail.assetCatergorySub.scatId}">${asset.assetCatergoryDetail.assetCatergorySub.scatCode}
                                            - ${asset.assetCatergoryDetail.assetCatergorySub.mcatDes}</option>
                                    </c:if>

                                    <option value="">---SELECT---</option>
                                </select>
                            </div>
                            <div class="col-md-3"></div>

                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Detail Category Code <label
                                    class="redMsg">*</label></div>
                            <div class="col-md-5">
                                <select id="detailCategoryListId" required class="select_style"
                                        onblur="generateAssetCode()"
                                        name="assetCatergoryDetail.dcatId"
                                        style="width: 100%">
                                    <c:if test="${asset.assetCatergoryDetail.dcatId > 0}">
                                        <option selected
                                                value="${asset.assetCatergoryDetail.dcatId}">${asset.assetCatergoryDetail.dcatId}
                                            - ${asset.assetCatergoryDetail.dcatDes}</option>
                                    </c:if>
                                    <option value="">---SELECT---</option>

                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Asset Code <label class="redMsg">*</label>
                            </div>
                            <div class="col-md-5">
                                <input type="text" required readonly id="assetCode" style="width: 100%"
                                       value="${asset.asCode}"
                                       name="asCode"/>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4" style="padding-top: 30px">Asset Description</div>
                            <div class="col-md-5">
                                <textarea name="asDes" maxlength="75" style="width: 100%">${asset.asDes}</textarea>
                            </div>
                            <div class="col-md-3">

                                <img id='barcode'
                                     src=""
                                     alt=""
                                     width="50" height="50"/></div>
                        </div>


                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Previous Asset Code</div>
                            <div class="col-md-5">
                                <input type="text" id="assetCodeOld " maxlength="45" style="width: 100%"
                                       name="asCodeOld"/>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row" style="padding-bottom: 10px">
                            <div class="col-md-4" style="padding-top: 10px">Analysis Code</div>
                            <div class="col-md-5">
                                <select id="analysisCode" class="select_style" name="analysisCode" style="width: 100%">
                                    <option value="0">---SELECT---</option>
                                    <c:forEach items="${analysiCodes}" var="analysiCodes">
                                        <option value="${analysiCodes.anaCode}">${analysiCodes.anaCode}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                            <%--https://codepen.io/oknoblich/pen/EAzBc--%>
                        </div>
                        <div class="row" style="padding-bottom: 10px" id="IMGDEV">
                            <div class="col-md-4" style="padding-top: 5px">Related Image</div>
                            <div class="col-md-5">
                                <input type="file" name="file"/>
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                        <div class="wrapper tab">
                            <div class='breadcrumbs'>
                                <div class='inner'>
                                    <ul class="tabs clearfix cf" data-tabgroup="first-tab-group">
                                        <li>
                                            <a href="#tab1" class="active">
                                                <span>1</span>
                                                <span>Purchase</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#tab2">
                                                <span>2</span>
                                                <span>Primary</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#tab3">
                                                <span>3</span>
                                                <span>Allocation</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#tab4">
                                                <span>4</span>
                                                <span>Accounting</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#tab5">
                                                <span>5</span>
                                                <span>Depreciation</span>
                                            </a>
                                        </li>

                                    </ul>
                                </div>
                            </div>

                            <section id="first-tab-group" class="tabgroup">
                                <div id="tab1">
                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Purchase Order No.</div>
                                        <div class="col-md-4">
                                            <input type="text" maxlength="25" style="width: 100%" value="${asset.poNo}"
                                                   id="poNoId"
                                                   name="poNo"/>
                                        </div>
                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Purchase Order Date</div>
                                        <div class="col-md-4">
                                            <div class='input-group date'>
                                                <input type='text' class="form-control" value="${asset.poDate}"
                                                       id="poDateId" name="poDate" style="height:35px"/>
                                                <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        </span>
                                            </div>
                                        </div>
                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">GRN No.</div>
                                        <div class="col-md-4">
                                            <input type="text" maxlength="25" style="width: 100%" value="${asset.grnNo}"
                                                   id="grnNoId"
                                                   name="grnNo"/>
                                        </div>
                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">GRN Date</div>
                                        <div class="col-md-4">
                                            <div class='input-group date'>
                                                <input type='text' class="form-control" onchange="DateValidation()"
                                                       style="height:35px"
                                                       id="grnDateId" name="grnDate" value="${asset.grnDate}"/>
                                                <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        </span>
                                            </div>
                                        </div>


                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Issue Note No.</div>
                                        <div class="col-md-4">
                                            <input type="text" maxlength="25" style="width: 100%" id="issueNoteNoId"
                                                   value="${asset.issueNoteNo}" name="issueNoteNo"/>
                                        </div>
                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Issue Note Date</div>
                                        <div class="col-md-4">
                                            <div class='input-group date'>
                                                <input type='text' class="form-control" onchange="DateValidation()"
                                                       style="height:35px"
                                                       id="issueNoteDateId"
                                                       name="issueNoteDate" value="${asset.issueNoteDate}"/>
                                                <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        </span>
                                            </div>
                                        </div>


                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Supplier Invoice No.</div>
                                        <div class="col-md-4">
                                            <input type="text" maxlength="25" style="width: 100%"
                                                   value="${asset.suppliersInvoiceNo}"
                                                   id="suppliersInvoiceNoId" name="suppliersInvoiceNo"/>
                                        </div>
                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Invoice Date</div>
                                        <div class="col-md-4">
                                            <div class='input-group date'>
                                                <input type='text' class="form-control" id="invoiceDateId"
                                                       onchange="DateValidation()" style="height:35px"
                                                       name="invoiceDate" value="${asset.invoiceDate}"/>
                                                <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        </span>
                                            </div>
                                        </div>


                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Supplier Name</div>
                                        <div class="col-md-4">
                                            <input type="text" style="width: 100%" maxlength="45" id="supplierNameId"
                                                   value="${asset.supplierName}"
                                                   name="supplierName"/>
                                        </div>
                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Address</div>
                                        <div class="col-md-4">
                                            <input type="text" style="width: 100%" maxlength="45" id="addressId"
                                                   name="address" value="${asset.address}"/>
                                        </div>
                                        <div class="col-md-5"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Telephone No.</div>
                                        <div class="col-md-4">
                                            <input type="text" maxlength="15" style="width: 100%"
                                                   value="${asset.telephoneNo}" id="telephoneNoId" name="telephoneNo"/>
                                        </div>
                                        <div class="col-md-2"></div>
                                    </div>
                                </div>
                                <div id="tab2">
                                    <div class="row">
                                        <div class="col-md-4" style="padding-top: 10px">Purchased Date <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <div class='input-group date'>
                                                <input type='text' class="form-control" id="purchaseDate"
                                                       style="height:35px"
                                                       required="required" value="${asset.purDate}" name="purDate"/>
                                                <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        </span>
                                            </div>
                                        </div>


                                        <div class="col-md-3"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4" style="padding-top: 10px">Registered Date <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <div class='input-group date'>
                                                <input type='text' class="form-control" onchange="checkRegDate(this)"
                                                       style="height:35px"
                                                       id="regDate" required="required" value="${asset.regDate}"
                                                       name="regDate"/>
                                                <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        </span>
                                            </div>
                                        </div>

                                        <div class="col-md-3"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4" style="padding-top: 10px">No of Units <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <input type="text" required id="noOfUnitId" style="width: 100%"
                                                   class="numeric" maxlength="11"
                                                   onkeypress="return isNumberOnly(event)" onchange="callTotal()"
                                                   value="${asset.noOfUnit}" name="noOfUnit"/>
                                        </div>
                                        <div class="col-md-3"></div>
                                    </div>

                                    <div class="row" style="">
                                        <div class="col-md-4" style="padding-top: 10px">Currency Type <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <select id="currencyId" required class="select_style" name="currencyType"
                                                    style="width: 100%">
                                                <option value="0">---SELECT---</option>
                                                <c:forEach items="${currencyTypes}" var="currencyType">
                                                    <%--<c:if test="${asset.currencyType == currencyType.currencyId}">--%>
                                                    <%--<option selected value="${currencyType.currencyId}">${currencyType.currencyCode}</option>--%>
                                                    <%--</c:if>--%>
                                                    <option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-3"></div>

                                    </div>

                                    <div class="row">
                                        <div class="col-md-4" style="padding-top: 10px">Unit Price <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <input type="text" required class="numeric" style="width: 100%"
                                                   maxlength="16" onkeyup="isNumber(this)" onchange="callTotal()"
                                                   onblur="addComma(id)"
                                                   value="${asset.unitPrice}" name="unitPrice" id="unitPrice"/>
                                        </div>
                                        <div class="col-md-3"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4" style="padding-top: 10px">Amount <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <input type="text" readonly maxlength="16" style="width: 100%"
                                                   value="${asset.amount}" name="amount" id="amount"/>
                                        </div>
                                        <div class="col-md-3"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4" style="padding-top: 10px">Asset Manufacturer’s Reg.No.
                                        </div>
                                        <div class="col-md-5">
                                            <input type="text" maxlength="35" style="width: 100%"
                                                   value="${asset.manRegNo}" name="manRegNo"/>
                                        </div>
                                        <div class="col-md-3"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4" style="padding-top: 10px">Manufacturer</div>
                                        <div class="col-md-5">
                                            <input type="text" maxlength="50" style="width: 100%"
                                                   value="${asset.manufacturer}" name="manufacturer"/>
                                        </div>
                                        <div class="col-md-3"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4" style="padding-top: 10px">Original Cost</div>
                                        <div class="col-md-5">
                                            <input type="text" style="width: 100%" value="${asset.originalCost}"
                                                   name="originalCost" maxlength="24"/>
                                        </div>
                                        <div class="col-md-3"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4" style="padding-top: 30px">Funding Sources</div>
                                        <div class="col-md-5">
                                            <textarea name="fundingSource" maxlength="100"
                                                      style="width: 100%">${asset.fundingSource}</textarea>
                                        </div>
                                        <div class="col-md-3"></div>
                                    </div>
                                </div>
                                <div id="tab3">
                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Company <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <select id="comId" required onblur="loadDepartments()" class="select_style"
                                                    style="width: 100%">
                                                <option value="">---SELECT---</option>
                                                <%--<c:if test="${assetLocation.sectionMaster.departmentMaster.companyMaster.comId > 0}">--%>
                                                <%--<option selected value="${assetLocation.sectionMaster.departmentMaster.companyMaster.comId}">${assetLocation.sectionMaster.departmentMaster.companyMaster.comCode}--%>
                                                <%--- ${assetLocation.sectionMaster.departmentMaster.companyMaster.comDes}</option>--%>
                                                <%--</c:if>--%>
                                                <c:forEach var="companyList" items="${companyList}">
                                                    <option value="${companyList.comId}">${companyList.comCode}
                                                        - ${companyList.comDes}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-4"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Department/Branch <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <select name="" required id="departmentId" class="select_style"
                                                    onblur="loadSections()"
                                                    style="width: 100%">
                                                <option value="">---SELECT---</option>
                                                <%--<c:if test="${assetLocation.sectionMaster.departmentMaster.depId > 0}">--%>
                                                <%--<option selected value="${assetLocation.sectionMaster.departmentMaster.depId}">${assetLocation.sectionMaster.departmentMaster.depCode}--%>
                                                <%--- ${assetLocation.sectionMaster.departmentMaster.depDes}</option>--%>
                                                <%--</c:if>--%>
                                            </select>

                                        </div>
                                        <div class="col-md-4"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Section <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <select id="sectionId" required onblur="loadLocation()" class="select_style"
                                                    style="width: 100%">
                                                <option value="">---SELECT---</option>
                                            </select>
                                        </div>
                                        <div class="col-md-4"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Location <label
                                                class="redMsg">*</label></div>
                                        <div class="col-md-5">
                                            <select name="locationMaster.locId" required id="locationId"
                                                    class="select_style"
                                                    style="width: 100%">
                                                <option value="">---SELECT---</option>
                                            </select>

                                        </div>
                                        <div class="col-md-4"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Authorized Person</div>
                                        <div class="col-md-5">
                                            <input type="text" maxlength="45" style="width: 100%" name="authPerson"
                                                   value="${asset.authPerson}"/>
                                        </div>
                                        <div class="col-md-4"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Leasing</div>
                                        <div class="col-md-5">
                                            <select name="isLeasing" id="isLeasing" class="select_style"
                                                    style="width: 100%">
                                                <option selected value="0">No</option>
                                                <option value="1">Yes</option>
                                            </select>
                                        </div>
                                        <div class="col-md-4"></div>
                                    </div>
                                </div>
                                <div id="tab4">
                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Ledger Code</div>
                                        <div class="col-md-4">
                                            <input type="text" maxlength="25" id="ledgerCodeId"
                                                   value="${asset.ledgerCode}" name="ledgerCode"/>
                                        </div>
                                        <div class="col-md-5"></div>
                                    </div>
                                </div>
                                <div id="tab5">
                                    <div class="row">
                                        <div class="col-md-3" style="padding-top: 10px">Life Time <label class="redMsg">*</label>
                                        </div>
                                        <div class="col-md-5">
                                            <input required type="text" maxlength="11" id="lifeTimeId"
                                                   value="${asset.lifeTime}"
                                                   onkeypress="return isNumberOnly(event)"
                                                   name="lifeTime"/>
                                            <label>Month(s)</label>

                                        </div>
                                        <div class="col-md-4"></div>
                                    </div>

                                </div>

                            </section>
                        </div>

                        <div class="row" style="margin-top: 15px">
                            <div class="col-md-4"></div>
                            <div class="col-md-6">

                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-primary"
                                           style="margin-top:3px;width: 140px;height: 30px; font-size: 14px"
                                           href="${root}/data/asset">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <button id="btnSave" onclick="CommaRemove('unitPrice'), CommaRemove('amount');"
                                                class="btn btn-primary"
                                                style="width: 140px;height: 30px; font-size: 14px">Save
                                        </button>
                                    </div>
                                    <div class="col-md-3"></div>

                                </div>

                            </div>
                            <div class="col-md-2"></div>

                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>

    <div class="col-md-4">

        <div class="row">
            &nbsp;
        </div>

        <div class="row">
            <div class="col-md-9">
                <input id="assetCodeNew" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                       class="form-control" data-maxitems="100"
                       placeholder="Enter New Asset Code " onblur="checkAssetCodeValid(),isEmpty()">
                <input id="getId" name="asset" hidden>
            </div>
            <div class="col-md-3">

            </div>
        </div>

        <div class="row">
            <div class="col-md-9" style="text-align: center ;margin-top:7px">
                <b>OR</b>
            </div>
            <div class="col-md-3">
            </div>

        </div>
        <div class="row">
            <div class="col-md-9">
                <input id="assetCodeOld" name="getOldName" style="height:35px;width:100%" type="text" autocomplete="off"
                       class="form-control" data-maxitems="100"
                       placeholder="Enter Previous Asset Code " onblur="checkAssetCodeValid(),isEmpty()">
                <input id="getOldId" hidden>
            </div>
            <div class="col-md-3">
            </div>

        </div>
        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col-md-9" style="text-align: center ">
                <div class="row">
                    <div class="col-md-7"></div>
                    <div class="col-md-5">
                        <button onclick="viewData();"
                                class="btn btn-primary"
                                style="width: 88px;height: 30px; font-size: 14px">Get Details
                        </button>
                    </div>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>

    </div>
</div>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="transactionNo" value="${transactionNo}"/>

<script type="text/javascript">

    createJsonObject();
    createTrJsonObject();
    createOldAssetJsonObject();
    document.getElementById('assetCodeNew').setAttribute('maxlength', ASSET_CODE_LENGTH);
    document.getElementById('searchTransactionNo').setAttribute('maxlength', ASSET_CODE_LENGTH);

    // loadAssetByType();

    $('.tabgroup > div').hide();
    $('.tabgroup > div:first-of-type').show();
    $('.tabs a').click(function (e) {
        e.preventDefault();
        var $this = $(this),
            tabgroup = '#' + $this.parents('.tabs').data('tabgroup'),
            others = $this.closest('li').siblings().children('a'),
            target = $this.attr('href');
        others.removeClass('active');
        $this.addClass('active');
        $(tabgroup).children('div').hide();
        $(target).show();

    });

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
    });

    function checkRegDate(date2) {
        var date1 = $("#purchaseDate").val();

        var d1 = dateFormator(date1);
        var d2 = dateFormator((date2.value))

        if (d1 > d2) {
            swal({
                title: 'Warning',
                text: 'Please select Registered Date after the Purchased Date!',
                type: 'warning',
                timer: 1000
            })
            $("#regDate").val("");
        }
    }

    function DateValidation() {
        checkGRNDate();
        checkIssueDate();
        checkINVDate();
    }

    function checkGRNDate() {
        var startDt = document.getElementById("poDateId").value;
        var endDt = document.getElementById("grnDateId").value;

        if ((new Date(startDt).getTime() > new Date(endDt).getTime())) {
            swal({
                title: 'Warning',
                text: 'Please select Date after the Purchase Order Date!',
                type: 'warning',
                timer: 1000
            })
            $("#grnDateId").val("");
        }
    }

    function checkIssueDate() {
        var poDt = document.getElementById("poDateId").value;
        var grnDt = document.getElementById("grnDateId").value;
        var inDt = document.getElementById("issueNoteDateId").value;

        if ((new Date(poDt).getTime() > new Date(inDt).getTime())) {
            swal({
                title: 'Warning',
                text: 'Please select Date after the Purchase Order Date!',
                type: 'warning',
                timer: 1000
            })
            $("#issueNoteDateId").val("");
        } else if ((new Date(grnDt).getTime() > new Date(inDt).getTime())) {
            swal({
                title: 'Warning',
                text: 'Please select Date after the GRN Date !',
                type: 'warning',
                timer: 1000
            })
            $("#issueNoteDateId").val("");
        }
    }

    function checkINVDate() {
        var poDt = document.getElementById("poDateId").value;
        var grnDt = document.getElementById("grnDateId").value;
        var inDt = document.getElementById("issueNoteDateId").value;
        var invDt = document.getElementById("invoiceDateId").value;

        if ((new Date(poDt).getTime() > new Date(invDt).getTime())) {
            swal({
                title: 'Warning',
                text: 'Please select Date after the Purchase Order Date!',
                type: 'warning',
                timer: 1000
            })
            $("#invoiceDateId").val("");
        } else if ((new Date(grnDt).getTime() > new Date(invDt).getTime())) {
            swal({
                title: 'Warning',
                text: 'Please select Date after the GRN Date !',
                type: 'warning',
                timer: 1000
            })
            $("#invoiceDateId").val("");
        } else if ((new Date(inDt).getTime() > new Date(invDt).getTime())) {
            swal({
                title: 'Warning',
                text: 'Please select Date after the Issue Note Date !',
                type: 'warning',
                timer: 1000
            })
            $("#invoiceDateId").val("");
        }
    }

    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('#btnSubmit').click(function () {
        $(".se-pre-con").show();
    });

    $(document).ready(function () {
        $('#dataTable').DataTable({
            "scrollY": "800px",
            "scrollCollapse": true,
            "paging": false
        });
    });

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var transactionNo = $("#transactionNo").val();
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Success! Transaction No. : ' + transactionNo,
                    type: 'success'
                }).then(function () {
                    window.location.href = "./asset";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./asset";
                })
            }
        }
    });

    function loadSubCategory() {
        $("#subCategoryId").empty();
        var mainCat = $("#mainCategoryId").find("option:selected").val();

        $.ajax({
            url: '${root}/category/getSubCategoryList/' + mainCat,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#subCategoryId").append(
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

    function generateAssetCode() {
        $("#assetCode").val();
        var main = $("#mainCategoryId").find("option:selected").text();
        var sub = $("#subCategoryId").find("option:selected").text();
        var detail = $("#detailCategoryListId").find("option:selected").text();
        var detailCatId = $("#detailCategoryListId").find("option:selected").val();

        var manCat = main.split(" - ")[0].trim();
        var subCat = sub.split(" - ")[0].trim();
        var detailCat = detail.split(" - ")[0].trim();

        if (detailCat != "") {
            $.ajax({
                url: '${root}/category/getDetailsCatSerial/' + detailCatId,
                success: function (data) {
                    if (data != "No") {
                        var code = manCat + subCat + detailCat + data;
                        $("#assetCode").val(code.toUpperCase());
                        $('#assetCode').prop('readonly', true);
                        // var nric = $('#assetCode').val();
//                        if(nric!=""){
//                            var url = 'https://api.qrserver.com/v1/create-qr-code/?data=' + nric + '&amp;size=50x50';
//                            $('#barcode').attr('src', url);
//                        }
                    } else {
                        swal({
                            title: 'Warning',
                            text: 'Can\'t be create Asset Code !! Please try again',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        }
    }

    function loadDetailCategory() {
        $("#detailCategoryListId").empty();
        var subCat = $("#subCategoryId").find("option:selected").val();
        $.ajax({
            url: '${root}/category/getDetailCategoryList/' + subCat,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var det = data[i];
                    var detailCat = det.split("-");
                    $("#detailCategoryListId").append(
                        "<option value = '" + detailCat[0] + "'>" + detailCat[1] + " - " + detailCat[2]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select subcategory !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }

    function loadDepartments() {
        $("#departmentId").empty();
        var comId = $("#comId").find("option:selected").val();
        $.ajax({
            url: '${root}/allocation/getDepartments/' + comId,
            success: function (data) {
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

    function loadLocation() {
        $("#locationId").empty();
        var sectionId = $("#sectionId").find("option:selected").val();
        $.ajax({
            url: '${root}/allocation/getLocations/' + sectionId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#locationId").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1] + " - " + subCat[2]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select section !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }


    function callTotal() {
        var noOfUnit = $("input[name=noOfUnit]").val();
        var unitPrice = $("input[name=unitPrice]").val().replace(/,/g, '');

        if (noOfUnit != "" && unitPrice != "") {
            $("input[name=amount]").val(noOfUnit * unitPrice);
            addComma("amount");
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
                url: '${root}/data/deleteAsset/' + trId,
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
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Assets already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Asset !!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }

    function viewData() {

        var assetIdNew = $('#getId').val();
        var assetIdOld = $('#getOldId').val();
        var assetId = "";
        if (assetIdOld == "" && assetIdNew != "") {
            assetId = assetIdNew;
            window.location = '${root}/data/viewAssetData/' + assetId;
        } else if (assetIdOld != "" && assetIdNew == "") {
            assetId = assetIdOld;
            window.location = '${root}/data/viewAssetData/' + assetId;
        } else if (assetIdOld != "" && assetIdNew != "") {
            $('#getId').val("");
            $('#getOldId').val("");
            $('#assetCodeOld').val("");
            $('#assetCodeNew').val("");
            swal({
                title: 'Warning',
                text: "Please Select One Asset Code",
                type: 'warning',
                timer: 5000
            });
        }
        else {
            swal({
                title: 'Warning',
                text: "Please Select New Asset Code Or Old Asset Code",
                type: 'warning',
                timer: 5000
            });
        }


    }


    function checkAssetCodeValid() {
        var des = $('input[name=getName]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {
                    // loadTransactionNoList();
                    // $('#btnSave').attr("disabled", false);
                    $('#assetCodeNew').css('border-color', '#aaaaaa');

                } else {
                    // $('#btnSave').attr("disabled", true);
                    $('#assetCodeNew').css('border-color', 'red');
                }
            }
        });
    }

    function checkTrNoValid() {
        var des = $('input[name=searchTransactionNo]').val();
        var assetCode = des.substring(0, TR_NO_LENGTH);
        $.ajax({
            url: '${root}/data/checkAssetTrNoValid/' + assetCode,
            success: function (data) {
                if (data != 0) {
                    // loadDataByTrNo();
                    loadAssetDetails();
                    $('#btnSave').attr("disabled", true);
                    $('#searchTransactionNo').css('border-color', '#aaaaaa');

                } else {
                    clearForm();
                    // $('#btnSave').attr("disabled", true);
                    $('#searchTransactionNo').css('border-color', 'red');
                }
            }
        });
    }

    // function loadDataByTrNo() {
    <%--var assetId = $("#TrId").val();--%>
    <%--$.ajax({--%>
    <%--url: '${root}/maintenance/loadAssetById/' + assetId,--%>
    <%--success: function (data) {--%>
    <%--if (data != "") {--%>
    <%--$("input[name=teachingId]").val(data.teachingId);--%>
    <%--$("input[name=model]").val(data.model);--%>
    <%--$("input[name=width]").val(data.width);--%>
    <%--$("input[name=length]").val(data.length);--%>
    <%--$("input[name=weight]").val(data.weight);--%>
    <%--$("input[name=height]").val(data.height);--%>
    <%--$("input[name=comments]").val(data.comments);--%>
    <%--document.getElementById("isInsuranceId").selectedIndex = data.isInsurance;--%>
    <%--document.getElementById("isServiceAgreId").selectedIndex = data.isServiceAgre;--%>
    <%--$("#btnSave").html('Update');--%>
    <%--} else {--%>
    <%--$("input[name=teachingId]").val("0");--%>
    <%--$("input[name=model]").val("");--%>
    <%--$("input[name=width]").val("");--%>
    <%--$("input[name=length]").val("");--%>
    <%--$("input[name=weight]").val("");--%>
    <%--$("input[name=height]").val("");--%>
    <%--$("input[name=comments]").val("");--%>
    <%--document.getElementById("isInsuranceId").selectedIndex = 0;--%>
    <%--document.getElementById("isServiceAgreId").selectedIndex = 0;--%>
    <%--$("#btnSave").html('Save');--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    // }

    function isTrEmpty() {
        if (document.getElementById("searchTransactionNo").value == '') {
            $('#assetCodeNew').css('border-color', '#aaaaaa');
        }
    }

    function isEmpty() {

        if (document.getElementById("assetCodeNew").value == '') {
            $('#assetCodeNew').css('border-color', '#aaaaaa');

        }
    }

    //to search new asset codes
    var jsonArray = [];

    function createJsonObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';

        $.ajax({
            url: '${root}/data/loadAssetByAssetCode/' + userBranch,
            success: function (data) {

                var ten = data.length;

                for (var i = 0; i < ten; i++) {
                    var arrNew = {};
                    var id = data[i][0];
                    var code = data[i][1] + " - " + data[i][2];
                    arrNew = {id: id, value: code};

                    jsonArray.push(arrNew);

                }
            }
        });

    }

    $("#assetCodeNew").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("getId").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,
        max: 100,

    });

    //to search Tr numbers
    var jsonTrArray = [];

    function createTrJsonObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';

        $.ajax({
            url: '${root}/data/loadAssetByTrNo/' + userBranch,
            success: function (data) {

                var ten = data.length;

                for (var i = 0; i < ten; i++) {
                    var arrNew = {};
                    var id = data[i][0];
                    var code = data[i][3] + " - " + data[i][2];
                    arrNew = {id: id, value: code};

                    jsonTrArray.push(arrNew);

                }
            }
        });

    }

    $("#searchTransactionNo").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonTrArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {
            document.getElementById("TrId").value = ui.item.id;
        },
        delay: 0,
        minLength: 1,
        max: 100,

    });

    //to search old asset codes
    var jsonOldAssetArray = [];

    function createOldAssetJsonObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';

        $.ajax({
            url: '${root}/data/loadAssetByOldAssetCode/' + userBranch,
            success: function (data) {

                var ten = data.length;

                for (var i = 0; i < ten; i++) {
                    var arrOld = {};
                    var id = data[i][0];
                    var code = data[i][1] + " - " + data[i][2];
                    arrOld = {id: id, value: code};

                    jsonOldAssetArray.push(arrOld);

                }
            }
        });

    }

    $("#assetCodeOld").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonOldAssetArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("getOldId").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,
        max: 100,

    });


    function loadAssetDetails() {
        clearForm();
        var assetId = $("#TrId").val();
        $.ajax({
            url: '${root}/data/loadAssetDetail/' + assetId,
            success: function (data) {
                if (data != "") {
                    // alert("mainCategoryId : " + data[4] + data[5] + data[6] );

                    document.getElementById("mainCategoryId").value = data[4];//Main Category Code

                    $("#subCategoryId").append(
                        "<option selected value = '" + data[5] + "'>" + data[6] + " - " + data[7]
                        + " </option> ");//Sub Category Code

                    $("#detailCategoryListId").append(
                        "<option selected value = '" + data[8] + "'>" + data[9] + " - " + data[10]
                        + " </option> ");//Detail Category Code

                    $("input[name=asCode]").val(data[0] == null ? "" : data[0]);//Asset Code
                    $("input[name=asCodeOld]").val(data[1] == "NULL" ? "" : data[1]);//Asset Code Old
                    $("textarea[name=asDes]").val(data[2] == "NULL" ? "" : data[2]);//Asset Description

                    $("input[name=poNo]").val(data[11] == "NULL" ? "" : data[11]);//PO Number
                    $("input[name=poDate]").val(data[12] == "NULL" ? "" : dateFormators(data[12]));//PO Date

                    $("input[name=grnNo]").val(data[13] == "NULL" ? "" : data[13]);//GRN Number
                    $("input[name=grnDate]").val(data[14] == "NULL" ? "" : dateFormators(data[14]));//GRN Date

                    $("input[name=issueNoteNo]").val(data[15] == "NULL" ? "" : data[15]);//Issue Number
                    $("input[name=issueNoteDate]").val(data[16] == "NULL" ? "" : dateFormators(data[16]));//Issue Date

                    $("input[name=suppliersInvoiceNo]").val(data[17] == "NULL" ? "" : data[17]);//Invoice Number
                    $("input[name=invoiceDate]").val(data[18] == "NULL" ? "" : dateFormators(data[18]));//Invoice Date

                    $("input[name=supplierName]").val(data[19] == "NULL" ? "" : data[19]);//Supplier Name
                    $("input[name=address]").val(data[20] == "NULL" ? "" : data[20]);//Address
                    $("input[name=telephoneNo]").val(data[21] == "NULL" ? "" : data[21]);//Telephone No.

                    $("input[name=purDate]").val(data[22] == "NULL" ? "" : dateFormators(data[22]));//Purchas Date
                    $("input[name=regDate]").val(data[23] == "NULL" ? "" : dateFormators(data[23]));//Registerd Date.
                    $("input[name=noOfUnit]").val(data[24] == "NULL" ? "" : data[24]);//No of unit

                    // $("input[name=currencyType]").val(data[25]);//Currency Type.
                    document.getElementById("currencyId").selectedIndex = data[25] == 1 ? 0 : data[25];//Currency Type.

                    $("input[name=unitPrice]").val(data[26] == "NULL" ? "0.00" : data[26]);//unit price
                    $("input[name=amount]").val(data[27] == "NULL" ? "0.00" : data[27]);//amount
                    $("input[name=manRegNo]").val(data[28] == "NULL" ? "" : data[28]);// manRegNo
                    $("input[name=manufacturer]").val(data[29] == "NULL" ? "" : data[29]);// Manufacturer
                    $("input[name=originalCost]").val(data[30] == "NULL" ? "0.00" : data[30]);// Original cost
                    $("textarea[name=fundingSource]").val(data[31] == "NULL" ? "" : data[31]);// Funding Source

                    $("input[name=ledgerCode]").val(data[32] == "NULL" ? "" : data[32]);// Ledger Code
                    $("input[name=lifeTime]").val(data[33] == "NULL" ? "0" : data[33]);// Life time

                    document.getElementById("comId").value = data[34] == "NULL" ? 0 : data[34];//Company Code

                    $("#departmentId").append(
                        "<option selected value = '" + data[35] + "'>" + data[36] + " - " + data[37]
                        + " </option> ");//Department Code

                    $("#sectionId").append(
                        "<option selected value = '" + data[38] + "'>" + data[39] + " - " + data[40]
                        + " </option> ");//section Code

                    $("#locationId").append(
                        "<option selected value = '" + data[41] + "'>" + data[42] + " - " + data[43]
                        + " </option> ");//Location Code

                    $("input[name=authPerson]").val(data[44] == "NULL" ? "" : data[44]);// Auth Person

                    document.getElementById("isLeasing").selectedIndex = data[45] == "NULL" ? 0 : data[45]; // Leasing


                    $("#IMGDEV").html('<label style="padding-top: 5px"></label>\n' +
                        '                        <label class="col-md-4">Image </label>\n' +
                        '                        <img class="img-responsive" style="margin-left:20px; width: 200px; height: 200px; border:4px solid #9a9a9a; border-raduis:4px; " src="data:image/jpg;base64,'+data[46]+'"/>\n' +
                        '               ');


                }
            }
        });
    }

    function clearForm() {
        document.getElementById("mainCategoryId").selectedIndex = 0;//Main Category Code
        document.getElementById("subCategoryId").selectedIndex = 0;//Sub Category Code
        document.getElementById("detailCategoryListId").selectedIndex = 0;//Detail Category Code

        // $("#subCategoryId").append(
        //     "<option selected value = '" + data[5] + "'>" + data[6] + " - " + data[7]
        //     + " </option> ");//Sub Category Code

        // $("#detailCategoryListId").append(
        //     "<option selected value = '" + data[8] + "'>" + data[9] + " - " + data[10]
        //     + " </option> ");//Detail Category Code

        $("input[name=asCode]").val("");//Asset Code
        $("input[name=asCodeOld]").val("");//Asset Code Old
        $("textarea[name=asDes]").val("");//Asset Description

        $("input[name=poNo]").val("");//PO Number
        $("input[name=poDate]").val("");//PO Date

        $("input[name=grnNo]").val("");//GRN Number
        $("input[name=grnDate]").val("");//GRN Date

        $("input[name=issueNoteNo]").val("");//Issue Number
        $("input[name=issueNoteDate]").val("");//Issue Date

        $("input[name=suppliersInvoiceNo]").val("");//Invoice Number
        $("input[name=invoiceDate]").val("");//Invoice Date

        $("input[name=supplierName]").val("");//Supplier Name
        $("input[name=address]").val("");//Address
        $("input[name=telephoneNo]").val("");//Telephone No.

        $("input[name=purDate]").val("");//Purchas Date
        $("input[name=regDate]").val("");//Registerd Date.
        $("input[name=noOfUnit]").val("");//No of unit

        // $("input[name=currencyType]").val(data[25]);//Currency Type.
        document.getElementById("currencyId").selectedIndex = 0;//Currency Type.

        $("input[name=unitPrice]").val("0.00");//unit price
        $("input[name=amount]").val("0.00");//amount
        $("input[name=manRegNo]").val("");// manRegNo
        $("input[name=manufacturer]").val("");// Manufacturer
        $("input[name=originalCost]").val("0.00");// Original cost
        $("textarea[name=fundingSource]").val("");// Funding Source

        $("input[name=ledgerCode]").val("");// Ledger Code
        $("input[name=lifeTime]").val("");// Life time

        document.getElementById("comId").selectedIndex = 0;//Department Code
        document.getElementById("departmentId").selectedIndex = 0;//Company Code
        document.getElementById("sectionId").selectedIndex = 0;//Section Code
        document.getElementById("locationId").selectedIndex = 0;//Location Code

        // document.getElementById("comId").value = data[34]=="NULL"?0:data[34];//Company Code

        // $("#departmentId").append(
        //     "<option selected value = '" + data[35] + "'>" + data[36] + " - " + data[37]
        //     + " </option> ");//Department Code
        //
        // $("#sectionId").append(
        //     "<option selected value = '" + data[38] + "'>" + data[39] + " - " + data[40]
        //     + " </option> ");//section Code
        //
        // $("#locationId").append(
        //     "<option selected value = '" + data[41] + "'>" + data[42] + " - " + data[43]
        //     + " </option> ");//Location Code


        $("input[name=authPerson]").val("");// Auth Person

        document.getElementById("isLeasing").selectedIndex = 0; // Leasing

    }

    function getSubCode(id) {
        var des = "";
        var id = id;
        $.ajax({
            url: '${root}/data/getSubCode/' + id,
            success: function (data) {
                if (data != "") {

                    des = data;
                    $("#subCategoryId").attr("text", "scaksbbckas")
                }
            }
        });
    }
</script>