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

        if(counter > 20){
            alert("Only 20 payment box you are allowed to add");
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
        var newTextBoxDiv3 = $(document.createElement('input'))
            .attr({
                type: 'text', name: 'paymentOf'+counter, id: 'paymentOf'+counter, placeholder: 'Payment of '+counter
            });
        var newTextBoxDiv4 = $(document.createElement('input'))
            .attr({
                type: 'text', name: 'amount'+counter, id: 'amount'+counter, placeholder: 'Amount '+counter
            });
        var newTextBoxDiv5 = $(document.createElement('input'))
            .attr({
                type: 'text', name: 'deadLine'+counter, id: 'deadLine'+counter, placeholder: 'Dead Line '+counter
            });
        var newTextBoxDiv6 = $(document.createElement('input'))
            .attr({
                type: 'text', name: 'note'+counter, id: 'note'+counter, placeholder: 'Note '+counter
            });


        newTextBoxDiv1.appendTo("#g1");
        newTextBoxDiv2.appendTo("#g2");
        newTextBoxDiv3.appendTo("#g3");
        newTextBoxDiv4.appendTo("#g4");
        newTextBoxDiv5.appendTo("#g5");
        newTextBoxDiv6.appendTo("#g6");

        counter++;

    });

//for remove textfield in requirement
    $("#removeOP").click(function () {
        if(counter == 2){
            alert("No more payment box to remove");
            return false;
        }

        counter--;
        $("#paymentType" + counter).remove();
        $("#paymentName" + counter).remove();
        $("#paymentOf" + counter).remove();
        $("#amount" + counter).remove();
        $("#deadLine" + counter).remove();
        $("#note" + counter).remove();

    });


});

