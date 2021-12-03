$(() => {

    ///=================  FETCH DATA FOR AUTHOR
    $('body').on('click', '#authorSearch', function(){
        console.log("testing");
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
            
            <div class="author-search-result" data-name="${result.artistName}"> <i class="fas fa-plus mr-2"></i>${result.artistName} </div>
            
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
        console.log(results)

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
                <button class="btn btn-outline-info title-search-result" type="submit" id="${result.trackId}">Add Title</button>
            </div>
        </div>
            
            
          `).join("")

        $('#titleResults').html(html)
    }


    //==================  POST RESULTS OF TITLE SEARCH W/ EVENT HANDLER
    $('body').on('click', '.title-search-result', async function(){
        let id = $(this).attr('id')
        console.log(id)
         let newTitle = await getTitle(id);

        console.log(newTitle);

        fetch(`add-book`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(newTitle)

        })

    })

    function getTitle(id) {
        return fetch(`https://itunes.apple.com/lookup?id=${id}`).then(response => response.json().then(result => {
            let book = result.results[0];

            return {
                title: book.trackCensoredName,
                description: book.description,
                release_date: book.releaseDate,
                itunes_url: book.trackViewUrl,
                artwork_url: book.artworkUrl100
            }
        }))

    }
})
