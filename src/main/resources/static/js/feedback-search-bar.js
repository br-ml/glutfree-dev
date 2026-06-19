

const feedbackList = document.getElementById('feedbackList');
const searchBar = document.getElementById('searchInput');
const allFeedbacks = [];

fetch("https://www.glutfree.eu/feedback/api").
then(response => response.json()).
then(data => {
    for (let feedback of data) {
        allFeedbacks.push(feedback);
    }
})


searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    console.log(allFeedbacks);
    let filteredFeedbacks = allFeedbacks.filter(feedback => {
        return feedback.name.toLowerCase().includes(searchingCharacters)
            || feedback.typeOfPlace.toLowerCase().includes(searchingCharacters);
    });
    console.log(filteredFeedbacks);
    displayFeedbacks(filteredFeedbacks);
})



const displayFeedbacks = (feedbacks) => {
    feedbackList.innerHTML = feedbacks
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
                        <a class="text-lightgreen" target="_blank" href= "${a.webSiteUrl}">
                          <p class="card-text border-bottom ">Марка: "${a.webSiteUrl}"</p>
                        </a>
                        <p class="card-text border-bottom " >Обратна Връзка: "${a.feedbackText}"</p>
<!--                        <p class="card-text border-bottom "th:text="|Точки: *{score}|" ></p>-->
                        <p class="card-text border-bottom"  >Тип обект: "${a.typeOfPlace}"</p>
                    </div>


                 <div class="d-flex justify-content-between align-items-center">
                        
                        <div class="btn-group pt-2">
                            <a href="/feedback/details/${a.id}"  type="button"  class="btn btn-primary btn-block w-30">Детайли</a>
                        </div>
                        
                        // <div sec:authorize="hasRole('ROLE_ADMIN')" class="btn-group pt-2">
                        //     <a href="/food/delete/${a.id}"  type="button" class="btn btn-primary btn-block w-30">Delete</a>
                        // </div>
                        
   
                    </div>
                </div>
<!--            </div>-->
<!--            </div>-->


    </div>`
        })
        .join('');

}


