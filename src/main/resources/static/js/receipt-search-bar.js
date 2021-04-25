

const receiptList = document.getElementById('receiptList');
const searchBar = document.getElementById('searchInput');
const allReceipt = [];

fetch("http://localhost:8080/receipt/api").
then(response => response.json()).
then(data => {
    for (let receipt of data) {
        allReceipt.push(receipt);
    }
})


searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    console.log(allReceipt);
    let filteredReceipt = allReceipt.filter(receipt => {
        return receipt.name.toLowerCase().includes(searchingCharacters)
            || receipt.typeOfMeal.toLowerCase().includes(searchingCharacters);
    });
    console.log(filteredReceipt);
    displayReceipt(filteredReceipt);
})



const displayReceipt = (receipt) => {
    receiptList.innerHTML = receipt
        .map((a) => {
            return `     
<!--     <div class="col">-->
<!--        <div class="col-md-12 card-columns">-->
            <div class="card mb-4 box-shadow bg-transparent align-items-center">
                <img src="${a.urlToPic}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 225px; width: 200px;">
                <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom" >Име: ${a.name}</p>
                       
                          <p class="card-text border-bottom ">Описание: "${a.description}"</p>
                     
                        <p class="card-text border-bottom " >Списък с продукти: "${a.productsList}"</p>
<!--                        <p class="card-text border-bottom "th:text="|Точки: *{score}|" ></p>-->
                        <p class="card-text border-bottom"  >Време за приготване: "${a.duration}"</p>
                        <p class="card-text border-bottom"  >Тип храна: "${a.typeOfMeal}"</p>
                    </div>


                    <div sec:authorize="hasRole('ROLE_ADMIN')" class="p-2 d-flex justify-content-center">
                            <a th:href="@{/receipt/delete/{id}(id= *{id})}" type="button" class="btn btn-primary btn-block w-50">Delete</a>
                    </div>
                </div>
<!--            </div>-->
<!--            </div>-->


    </div>`
        })
        .join('');

}


