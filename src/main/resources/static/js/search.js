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
    $('body').on('click', '#titleSearch', function(){
        const text = $('#titleSearchInput').val();
        var url = new URL('book-suggestions', window.location.origin )

        url.search = new URLSearchParams({search: text}).toString();

        fetch(url)
            .then(response => response.json())
            .then(buildTitleResults)
    })


    //================ FUNCTION TO MAP TITLE RESULTS TO HTML
    function buildTitleResults(results) {
        const html = results.map(result => `
        <div class="container mb-3 ">
            <img alt="image" src="${result.artworkUrl100}">
               <div class="card">
                    <div class="card-body">
                        <h2 class="card-title" data-name="${result.trackName}" >${result.trackName}</h2>
                            <p class="card-text lead" data-description="${result.description}">
                                <small>${result.description}</small>
                            </p>
                        <div  data-date="${result.releaseDate}">${result.releaseDate}</div>
                        <a href="${result.trackViewUrl}">${result.trackViewUrl}</a>
                    </div>
                    <button class="btn btn-outline-info title-search-result" type="submit">Add Title</button>
                </div>
        </div>
            
            
          `).join("")
        $('#titleResults').html(html)
    }


    //==================  POST RESULTS OF TITLE SEARCH W/ EVENT HANDLER
    $('body').on('click', '.title-search-result', function(){
        const title = $(this).data("name");
        const description = $(this).data("description");
        const release_date = $(this).data("date");
        const itunes_url = $(this).data("href");
        const artwork_url = $(this).data("src");
        console.log($(this));

        fetch("add-book", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(
                {title,
                    description,
                    release_date,
                    itunes_url,
                    artwork_url
            })
        })

    })
})
