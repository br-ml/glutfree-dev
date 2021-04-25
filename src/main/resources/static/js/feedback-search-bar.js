

const feedbackList = document.getElementById('feedbackList');
const searchBar = document.getElementById('searchInput');
const allFeedbacks = [];

fetch("http://localhost:8080/feedback/api").
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


                    <div sec:authorize="hasRole('ROLE_ADMIN')" class="p-2 d-flex justify-content-center">
                            <a th:href="@{/feedback/delete/{id}(id= *{id})}" type="button" class="btn btn-primary btn-block w-50">Delete</a>
                    </div>
                </div>
<!--            </div>-->
<!--            </div>-->


    </div>`
        })
        .join('');

}


