function handleClick(e) {
  // Getting left hand operand 
  const leftOperand = $('#left__input').val();

  // Getting right hand operand 
  const rightOperand = $('#right__input').val();

  // Getting operation selected
  const data = new FormData(document.querySelector("form"));
  let operation = "";
  for (const i of data) {
    operation = i[1];
  };

  // Validation before sending over to backend
  if (!isNaN(leftOperand) && !isNaN(rightOperand) && operation.length === 1 &&
    leftOperand != '' && rightOperand != '') {

    let res = '';
    $.ajax('http://localhost:8080/', {
      type: 'POST',
      data: {
        leftOperand: leftOperand,
        rightOperand: rightOperand,
        operation: operation
      },
      async: false,
      success: function (txt) {
        res = txt;
      }
    })

    const returnSplit = res.split('"');

    let expression = returnSplit[3]
    let result = returnSplit[returnSplit.length - 1].replace(':', '').replace('}', '')

    document.querySelector('.expression').textContent = expression
    document.querySelector('.result').textContent = result
  }
}

$(document).ready(function () {
  $('.calculate').click(handleClick);
})
