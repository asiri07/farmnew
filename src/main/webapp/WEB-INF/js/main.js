// Master Code Length
var masterCodeLength = 3;
var MAIN_CODE = 1;
var SUB_CODE = 2;
var DETAIL_CODE = 2;
var CITY_CODE = 3;
var BUILDING_CODE = 3;
var FLOOR_CODE = 3;
var ROOM_CODE = 2;
var COMPANY_CODE = 3;
var DEPARTMENT_CODE = 2;
var SECTION_CODE = 2;
var ASSET_CODE_LENGTH=10
var TR_NO_LENGTH=8;
var LOCATION_CODE_LENGTH=15;

function isNumber(evt) {
    var val = $(evt).val();

    if (isNaN(val)) {
        val = val.replace(/[^0-9\.]/g, '');
        if (val.split('.').length > 2)
            val = val.replace(/\.+$/, "");
    }
    $(evt).val(val);
}

function isNumberOnly(evt) {
    evt = (evt) ? evt : window.event;

    var charCode = (evt.which) ? evt.which : evt.keyCode;

    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function isNumberAndDecimalOnly(evt) {
    var keyCode = (evt.which) ? evt.which : evt.keyCode;
    if ((keyCode >= 48 && keyCode <= 57) || (keyCode == 8))
        return true;
    else if (keyCode == 46) {
        var curVal = document.activeElement.value;
        if (curVal != null && curVal.trim().indexOf('.') == -1)
            return true;
        else
            return false;
    }
    else
        return false;
}

function validateCharacter(code) {
    if (/[^a-zA-Z0-9\-\/]/.test(code.value)) {
        // $("#" + ($(code).attr('id'))).val("");
        swal({
            title: 'Sorry!',
            text: 'Can not use special characters',
            type: 'warning',
            timer: 1000
        })
        return false;
    }
    return true;
}


function validatePercentageField(code) {
    var taxValuvePercentage = parseInt(code.value);
    if (taxValuvePercentage < 0 || taxValuvePercentage >= 100) {
        $("#" + $(code).attr('id')).val("");
        $("#" + $(code).attr('id')).focus();
        swal({
            title: 'Sorry !',
            text: 'Please the correct percentage. ',
            type: 'error',
            timer: 1000
        })
        return false;
    }
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

function dateFormator(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [month, day, year].join('/');
}

function dateFormators(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('/');
}

function validateMasterCode(field, category) {
    var value = field.value;
    var message = "";
    switch (category) {
        case 'MAIN_CODE':
            masterCodeLength = MAIN_CODE;
            message = " Main Code Length is  ";
            break;
        case 'SUB_CODE':
            masterCodeLength = SUB_CODE;
            message = " SUB Code Length is  ";
            break;

        case 'DETAIL_CODE':
            masterCodeLength = DETAIL_CODE;
            message = " Detail Code Length is  ";
            break;
        case 'CITY_CODE':
            masterCodeLength = CITY_CODE;
            message = " City Code Length is  ";
            break;
        case 'BUILDING_CODE':
            masterCodeLength = BUILDING_CODE;
            message = " Building Code Length is  ";
            break;
        case 'FLOOR_CODE':
            masterCodeLength = FLOOR_CODE;
            message = " Floor Code Length is  ";
            break;
        case 'ROOM_CODE':
            masterCodeLength = ROOM_CODE;
            message = " Room Code Length is  ";
            break;
        case 'COMPANY_CODE':
            masterCodeLength = COMPANY_CODE;
            message = " Company Code Length is  ";
            break;
        case 'DEPARTMENT_CODE':
            masterCodeLength = DEPARTMENT_CODE;
            message = " Deprtment Code Length is  ";
            break;
        case 'SECTION_CODE':
            masterCodeLength = SECTION_CODE;
            message = " Section Code Length is  ";
            break;
    }
    if (/[^a-zA-Z0-9\-\/]/.test(value)) {
        $("#" + ($(field).attr('id'))).val("");
        swal({
            title: 'Sorry!',
            text: 'Can Not Use Special Characters',
            type: 'warning',
            timer: 1000
        })
    } else {
        if (value.length != masterCodeLength) {
            $("#" + ($(field).attr('id'))).val("");
            swal({
                title: 'Sorry !',
                text: message + masterCodeLength,
                type: 'warning',
                timer: 1000
            })
            $("#" + $(field).attr('id')).focus();
        }
    }
}


function addComma(id) {
    var idName = id.valueOf();
    var concatIdName = "#" + idName;
    var enteredValue = $(concatIdName).val().replace(/,/g, '');


    var num = Number(enteredValue).toLocaleString();

    if (enteredValue.indexOf(",") < 0) {
        if (num.indexOf(".") < 0) {
            num += ".00";
        } else if (num.indexOf(".") == -1) {
            num += "0";
        }else {
            var n = parseFloat(enteredValue).toFixed(2).toString();
            num = Number(n).toLocaleString();
        }

        $(concatIdName).val(num);
    } else {
        num = enteredValue;
    }
    return num;
}

function addCommaForNumber(number) {
    var num = Number(number).toLocaleString();
    if (num.indexOf(",") < 0) {
        if (num.indexOf(".") < 0) {
            num += ".00";
        } else {
            var n = parseFloat(enteredValue).toFixed(2).toString();
            num = Number(n).toLocaleString();
        }

        $(concatIdName).val(num);
    } else {
        if (num.indexOf(".") < 0) {
            num += ".00";
        } else {
            var n = parseFloat(enteredValue).toFixed(2).toString();
            num = Number(n).toLocaleString();
        }
    }
    return num;
}


function setToTwoDecimals(id) {
    var idName = id.valueOf();
    var concatIdName = "#" + idName;
    var enteredValue = $(concatIdName).val();

    var num = Number(enteredValue).toLocaleString();

    if (enteredValue.indexOf(",") < 0) {
        if (num.indexOf(".") < 0) {
            num;
        } else {
            var n = parseFloat(enteredValue).toFixed(2).toString();
            num = Number(n).toLocaleString();
        }
        $(concatIdName).val(num);
    } else {
        num = enteredValue;
    }
    return num;
}

function CommaRemove(id) {
    var idName = id.valueOf();
    var concatIdName = "#" + idName;
    var max = $(concatIdName).val();
    $(concatIdName).val(max.replace(/,/g, ''));
}