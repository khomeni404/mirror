/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 7/19/12
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */




$(document).ready(function(){
    var counter = 2;


//for add textfield requirement
    $("#addOP").bind("click", function(){

        if(counter > 2){
            alert("Only 1 payment box you are allowed to add");
            return false;
        }

        var newTextBoxDiv1 = $(document.createElement('input'))
            .attr({
                type: 'text', name: 'paymentType'+counter, id: 'paymentType'+counter, placeholder: 'Payment type '+counter
            });
        var newTextBoxDiv2 = $(document.createElement('input'))
            .attr({
                type: 'text', name: 'paymentName'+counter, id: 'paymentName'+counter, placeholder: 'Payment name '+counter
            });



        newTextBoxDiv1.appendTo("#g1");
        newTextBoxDiv2.appendTo("#g2");


        counter++;

    });

//for remove textfield in requirement
    $("#removeOP").click(function () {
        if(counter == 2){
            alert("No more box to remove");
            return false;
        }

        counter--;
        $("#paymentType" + counter).remove();
        $("#paymentName" + counter).remove();

    });


});

