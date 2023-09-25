type rating = {
    rate: number;
    count: number
};

export type Product={
    category:string;
    description: string;
    id:number;
    image:string;
    price:number;
    rating:rating;
    title:string;
};

fetch ('https://fakestoreapi.com/products')
.then(res=>res.json())
.then( (products : Product[]) => {
    console.log('products', products);
    //estructura de la tabla
    let tableHTML:string='<thead><tr> <th> ID </th> <th>TITLE</th> <th>DESCRIPTION</th> <th>PRICE</th> </tr></thead><tbody>';
   //se arman las filas de la tabla
    products.forEach((p:Product)=> {
        
        tableHTML += `<tr><td>${p.id}</td><td>${p.title}</td><td>${p.description}</td><td>${p.price}</td></tr>`;
    });
    //cierre de tabla
    tableHTML+='</tbody>';
    //setear valores del elemento tabla
    document.querySelector('#tableElement')!.innerHTML = tableHTML;
    //ocultar el spinner
    const spinnerElement: HTMLElement = document.querySelector("#spinnerContainer")!;
    spinnerElement.style.display='none';
});