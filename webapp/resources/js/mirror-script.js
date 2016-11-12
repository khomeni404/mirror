/**
 * Created by khomeni404 on 10/27/2016.
 */

/**
 * Author: Khomeni
 * Implemented on : 02/10/2016
 * To: Create HTML DOM input, to append with the DOM form object
 *
 * Example:
 *          var myField = createHiddenField('custId', 21325454541);
 *
 *          var myForm = createForm('post', 'ctx/doSomething.do');
 *          myForm.appendChild(myField);
 *          myForm.submit();
 *
 * @param name. Name of the field. Like- <input name='???'/>
 * @param value. Value of the field. Like- <input value='???'/>
 * @returns {Element|*} HTML DOM input object
 */
function createHiddenField(name, value) {
    var myInput = document.createElement("input");
    myInput.type = "hidden";
    myInput.name = name;
    myInput.value = value;
    return myInput;
}


/**
 * Author: Khomeni
 * Implemented on : 02/10/2016
 * To: Create form to 'post' or 'get' request.
 * Example:
 *          var myForm = createForm('post', 'ctx/doSomething.do');
 *          myForm.submit();
 *          N.B. You can add element within the form to contain values.
 *          See 'createHiddenField(name, value) function'
 * @param method. The method name i.e. 'post'/'get'
 * @param url. The URL where to submit. e.g. something.do
 * @returns HTML DOM Form Object
 * Last Edited: 18/10/2016
 */
function createForm(method, url) {
    var submitBtn = document.createElement("input");
    submitBtn.type = 'submit';

    var myForm = document.createElement("form");
    myForm.method = method;
    myForm.action = url;
    // myForm.enctype = "multipart/form-data";
    //if (navigator.userAgent.search("Firefox") > -1) {
    myForm.appendChild(submitBtn);
    document.getElementsByTagName("body")[0].appendChild(myForm);
    // }

    return myForm;
}


/**
 * Author: Khomeni
 * To:  Create and post a Dynamic form
 * Example:
 *      onclick="postMe('ctx/hitMe.do', 'id=23 & type=special & cat=3 & status=')"
 *      N.B. In the second param, spaces (before and after '&' sign) won't raise any effect.
 * @param url
 * @param paramValuePairs
 */
function postMe(url, paramValuePairs) {
    var myForm = createForm('post', url);
    var pairArray = paramValuePairs.split("&");
    for (var i = 0; i < pairArray.length; i++) {
        var pv = pairArray[i].split("=");
        myForm.appendChild(createHiddenField(pv[0].trim(), pv.length < 2 ? '' : pv[1].trim()));
    }
    //console.log(myForm);
    myForm.submit();
}
/**
 * Author: Khomeni
 * To:  Create and post a Dynamic form and open in a new window
 * Example:
 *      onclick="postMe('ctx/hitMe.do', 'id=23&type=special&cat=3')"
 * @param url
 * @param paramValuePairs
 */
function postMe_blank(url, paramValuePairs) {
    var myForm = createForm('post', url);
    var pairArray = paramValuePairs.split("&");
    for (var i = 0; i < pairArray.length; i++) {
        var pv = pairArray[i].split("=");
        myForm.appendChild(createHiddenField(pv[0].trim(), pv[1].trim()));
    }
    myForm.target = "_blank";
    //console.log(myForm);
    myForm.submit();
}