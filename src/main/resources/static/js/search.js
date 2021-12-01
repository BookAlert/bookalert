$(() => {

    ///=================  FETCH DATA FOR AUTHOR
    $('body').on('click', '#authorSearch', function(){
        const text = $('#authorSearchInput').val();
        var url = new URL('author-suggestions', window.location.origin )

        url.search = new URLSearchParams({search: text}).toString();

        fetch(url)
            .then(response => response.json())
            .then(buildSearchResults)
    })
//================  FUNCTION TO MAP AUTHOR RESULTS TO HTML
    function buildSearchResults(results) {
        const html = results.map(result => `
            
            <div class="author-search-result" data-name="${result.artistName}" id="search"> <i class="fas fa-plus mr-2"></i>${result.artistName} </div>
            
          `).join("")
        $('#authorResults').html(html)
    }

    //==================  POST RESULTS OF AUTHOR SEARCH W/ EVENT HANDLER
    $('body').on('click', '.author-search-result', function(){
        const authorName = $(this).data("name");

        fetch("add-author", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({name: authorName})
        })
        // $.post("/search", {'search': $('#search').val()}, function () {
        //     iziToast.show({
        //         title: 'Success',
        //         message: 'Successfully added author!',
        //         position: 'topRight',
        //         timeout: 5000,
        //         color: 'green'
        //     });
        //
        // }).fail(function () {
        //     iziToast.show({
        //         title: 'Fail',
        //         message: 'Failed to add author',
        //         position: 'topLeft',
        //         color: 'red'
        //     });
        // });

    })





    //=================  FETCH DATA FOR TITLES/BOOKS
    $('#titleSearch').on('click',  function(){
        const text = $('#titleSearchInput').val();
        var url = new URL('book-suggestions', window.location.origin )

        url.search = new URLSearchParams({search: text}).toString();

        fetch(url)
            .then(response => response.json())
            .then(buildTitleResults)
    })





    //================ FUNCTION TO MAP TITLE RESULTS TO HTML
    function buildTitleResults(results) {

        let html = results.map(result =>

        `
        <div>
            <img alt="image" data-src="${result.artworkUrl100} hidden" src="${result.artworkUrl100}">
           <div class="card">
                <div class="card-body">
                    <h2 class="card-title" data-title="${result.trackName}" >${result.trackName}</h2>
                        <p class="card-text lead" data-description="${result.description}">
                            <small>${result.description}</small>
                        </p>
                    <span  data-date="${result.releaseDate}">${result.releaseDate}</span>
                    <a data-href="${result.trackViewUrl}">${result.trackViewUrl}</a>
                </div>
                <button class="btn btn-outline-info title-search-result" type="submit" id="${result.id}">Add Title</button>
            </div>
        </div>
            
            
          `).join("")
        $('#titleResults').html(html)
    }


    //==================  POST RESULTS OF TITLE SEARCH W/ EVENT HANDLER
    $('body').on('click', '.title-search-result', function(){
        let id = $(this).attr('id')
        console.log(id)
        let newTitle = {
            title : $("h2").data("title"),
            description : $("p").data("description"),
            release_date : $("span").data("date"),
            itunes_url : $("a").data("href"),
            artwork_url : $("img").data("src")
        }

        console.log(newTitle);

        fetch(`add-book/${id}`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(newTitle)

        })

    })
})
