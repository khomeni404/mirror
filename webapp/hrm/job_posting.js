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
    $("#addResponsibility").bind("click", function(){

        if(counter > 5){
            alert("Only 10 Responsibility you are allowed to add");
            return false;
        }

        var newTextBoxDiv = $(document.createElement('input'))
        .attr({
                id: 'responsibility' + counter,
                type: 'text',
                placeholder: 'Responsibility ' + counter,
                name: 'responsibility' + counter
            });

        newTextBoxDiv.appendTo("#responsibilityGroup");
        counter++;

    });

//for remove textfield in requirement
    $("#removeRequirement").click(function () {
        if(counter == 2){
        alert("No more textbox to remove");
        return false;
        }

        counter--;
        $("#responsibility" + counter).remove();

    });


});

