<table class="disburse">
    <tr>
        <th class="particular">Particular</th>
        <th class="total">Total</th>
        <th class="payable">Payable</th>
        <th class="paid">Paid</th>
        <th class="due">Due
        <th class="adjust">Adjustment</th>
    </tr>

    <tr>
        <td class="particular">Instalment Amount</td>
        <td class="total"><input type="text" value="${}"/></td>
        <td class="payable"><input type="text" value="${}"/></td>
        <td class="paid"><input type="text" value="${}" id="instOld" name="instOld"/></td>
        <td class="due"><input type="text" value="${}"/>
        <td class="adjust"><input type="text" name="instNew" id="instNew"/></td>
    </tr>
    <tr>
        <td class="particular">Down-payment</td>
        <td class="total"><input type="text" value="${}"/></td>
        <td class="payable"><input type="text" value="${}"/></td>
        <td class="paid"><input type="text" value="${}" name="dpOld" id="dpOld"/></td>
        <td class="due"><input type="text" value="${}"/>
        <td class="adjust"><input type="text" name="dpNew" id="dpNew"/></td>
    </tr>
    <tr>
        <td class="particular">Special Payment</td>
        <td class="total"><input type="text" value="${}"/></td>
        <td class="payable"><input type="text" value="${}"/></td>
        <td class="paid"><input type="text" value="${}" id="spOld" name="spOld"/></td>
        <td class="due"><input type="text" value="${}"/>
        <td class="adjust"><input type="text" name="spNew" id="spNew"/></td>
    </tr>
    <tr>
        <td class="particular">Car Parking</td>
        <td class="total"><input type="text" value="${}"/></td>
        <td class="payable"><input type="text" value="${}"/></td>
        <td class="paid"><input type="text" value="${}" id="cpOld" name="cpOld"/></td>
        <td class="due"><input type="text" value="${}"/>
        <td class="adjust"><input type="text" name="cpNew" id="cpNew"/></td>
    </tr>
    <tr>
        <td class="particular">Other</td>
        <td class="total"><input type="text" value="${}"/></td>
        <td class="payable"><input type="text" value="${}"/></td>
        <td class="paid"><input type="text" value="${}" id="otherOld" name="otherOld"/></td>
        <td class="due"><input type="text" value="${}"/>
        <td class="adjust"><input type="text" name="otherNew" id="otherNew"/></td>
    </tr>


</table>
<hr/>
<table class="disburse">
    <tr>
        <td class="particular">Paid Total</td>
        <td><input type="text" value="500" id="paid" name="paid"/></td>
    </tr>
    <tr>
        <td class="particular">Disburse Total</td>
        <td><input type="text" value="400" id="disburse" name="disburse"/></td>
    </tr>
    <tr>
        <td class="particular">Rest Total</td>
        <td><input type="text" value="" name="rest" id="rest"/></td>
    </tr>
    <script>
        function abc() {
            var rest = Integer.parseInt(document.getElementById("disburse").value);
            document.getElementById('rest').innerHTML = rest;
        }
    </script>
    <input type="button" value="abc" onclick="abc();"/>
</table>