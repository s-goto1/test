/**
 *
 */

function actionA(){
    document.getElementById('form').action = './ExcelOut';
}

function actionB(){
    document.getElementById('form').action = './register.jsp';
}

function setAction(url) {
    $('form').attr('action', url);

    $('form').submit();
}



