
fetch('https://fakestoreapi.com/products')
    .then(function (res) { return res.json(); })
    .then(function (products) {
    console.log('products', products);
    //estructura de la tabla
    var tableHTML = '<thead><tr> <th> ID </th> <th>TITLE</th> <th>DESCRIPTION</th> <th>PRICE</th> </tr></thead><tbody>';
    //se arman las filas de la tabla
    products.forEach(function (p) {
        tableHTML += "<tr><td>".concat(p.id, "</td><td>").concat(p.title, "</td><td>").concat(p.description, "</td><td>").concat(p.price, "</td></tr>");
    });
    //cierre de tabla
    tableHTML += '</tbody>';
    //setear valores del elemento tabla
    document.querySelector('#tableElement').innerHTML = tableHTML;
    //ocultar el spinner
    var spinnerElement = document.querySelector("#spinnerContainer");
    spinnerElement.style.display = 'none';
});
