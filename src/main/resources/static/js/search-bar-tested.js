

const foodList = document.getElementById('foodList');
const searchBar = document.getElementById('searchInput');
const allFoods = [];

fetch("http://localhost:8080/food/api-tested").
then(response => response.json()).
then(data => {
    for (let food of data) {
        allFoods.push(food);
    }
})


searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    console.log(allFoods);
    let filteredFoods = allFoods.filter(food => {
        return food.name.toLowerCase().includes(searchingCharacters)
            || food.brand.toLowerCase().includes(searchingCharacters)
            || food.store.toLowerCase().includes(searchingCharacters);
    });
    console.log(filteredFoods);
    displayFoods(filteredFoods);
})



const displayFoods = (foods) => {
    foodList.innerHTML = foods
        .map((a) => {
            return ` <div class="col" >
                <div class="card mb-4 box-shadow bg-transparent align-items-center">
                <a href="/food/details/${a.id}">
                <img src="${a.urlToPic}" class="card-img-top" alt="pic"
                     data-holder-rendered="true"
                     style="height: 225px; width: 225px;">
                     </a>
                <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom ">Име: ${a.name}</p>
                        <p class="card-text border-bottom ">Марка: ${a.brand}</p>
                         <p class="card-text border-bottom ">Магазин :
                            <a class="text-lightgreen" href= "${a.store}">
                                <img class="p-1" src= "${a.storelogoUrl}" alt="pic"
                                     style="height: 50px; width: 50px;">
                            </a>
                        </p>
                          <p class="card-text border-bottom ">

                  ${a.nimaTested ?  '<img  src="../img/nima-logo.png" alt="pic" style="height: 50px; width: 50px;">' : ''}
                  ${a.markedAsGF ? '<img src="../img/GlutenFree-s-logo.png" alt="pic"  style="height: 50px; width: 50px; padding-left: 5px">' : ''}
                   ${a.withoutLactose  ? '<img  src="../img/LactoseFree-s-logo.png" alt="pic" style="height: 50px; width: 50px;">' : ''}
                            </p>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">

                        <div class="btn-group pt-2">
                            <a href="/food/details/${a.id}"  type="button"  class="btn btn-primary btn-block w-30">Детайли</a>
                        </div>


                    </div>
                </div>
            </div>
            </div>`
        })
        .join('');

}


// <div sec:authorize="hasRole('ROLE_ADMIN')" class="btn-group pt-2">
//     <a href="/food/delete/${a.id}"  type="button" class="btn btn-primary btn-block w-30">Delete</a>
// </div>

