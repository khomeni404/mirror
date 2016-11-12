/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/20/14
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */

function smartDateToNormal(smartDate, pattern) {
    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    var monthSl = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];
    var year, month, day;

    if (smartDate.toString().length == 12) {
        //var smartDate = "Feb 12, 2014"
        for (var i = 0; i < months.length; i++) {
            if (smartDate.substring(0, 3) == months[i]) {
                year = smartDate.substring(8, 12);
                month = monthSl[i];
                day = smartDate.substring(4, 6);
                break;
            }
        }
    } else if (smartDate.toString().length == 11) {
        //var smartDate = "Feb 2, 2014"
        for (var i = 0; i < months.length; i++) {
            if (smartDate.substring(0, 3) == months[i]) {
                year = smartDate.substring(7, 11);
                month = monthSl[i];
                day = "0" + smartDate.substring(4, 5);
                break;
            }
        }
    }

    //var smartDate = "Feb 2, 2014"

    return year + pattern + month + pattern + day;
}

function reverseDate(date, pattern) {
    //alert(date);
    var yyyy = "";
    var mm = "";
    var dd ="";
    var separator = "-";
    var dateArr = date.split(separator);
    if (dateArr.length < 3) {
        separator = "/";
        dateArr = date.split(separator);
    }
    //alert(dateArr[2].length);
    if (dateArr[0].length == 4) {
        dd = dateArr[0];
        mm = dateArr[1];
        yyyy = dateArr[2];
        return dd+pattern+mm+pattern+yyyy;
    } else if(dateArr[2].length == 4){
        yyyy = dateArr[0];
        mm = dateArr[1];
        dd = dateArr[2];
        return dd+pattern+mm+pattern+yyyy;
    } else {
        return "";
    }
}


function validateYYYYMMDD(date) {
    //var date = "2015-10-10"

    //This validator will check month & year range also
    //var patternYYYYMMDD = /^(10|99)\d{2}\-(0[1-9]|1[0-2])\-(0[1-9]|1\d|2\d|3[01])$/;
    var patternYYYYMMDD = /^\d{4}\-(\d{2})\-\d{2}$/;
    if (!patternYYYYMMDD.test(date)) {
        alert("Date pattern should be like YYYY-MM-DD");
        return false;
    }
    var parts = date.split("-");
    var month = parseInt(parts[1], 10);
    var year = parseInt(parts[0], 10);
    var day = parseInt(parts[2], 10);

    if (year < 1000 || year > 9999) {
        alert("Sorry you must put year between 1000 - 9999 instead of " + year);
        return false;
    }
    if (month == 0 || month > 12) {
        alert("Sorry you must put month '0-12', instead of " + month);
        return false;
    }
    var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
        monthLength[1] = 29;
    if (day > 0) {
        if (day <= monthLength[month - 1]) {
            //alert ("ok");
            return true;
        } else {
            alert("This is the month of " + monthLength[month - 1] + " days");
            return false;
        }
    } else {
        alert("Please check u'r date. It may not less or equal '0'");
        return false;
    }

}

