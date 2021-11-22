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

    //==================  DISPLAY RESULTS OF AUTHOR SEARCH W/ EVENT HANDLER
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
            
            <div class="title-search-result" data-name="${result.trackName}"> <i class="fas fa-plus mr-2"></i>${result.trackName} </div>
            
          `).join("")
        $('#titleResults').html(html)
    }


    //==================  DISPLAY RESULTS OF TITLE SEARCH W/ EVENT HANDLER
    $('body').on('click', '.title-search-result', function(){
        const bookName = $(this).data("name");
        const description = $(this).data("description");
        const releaseDate = $(this).data("date");
        const trackViewUrl = $(this).data("link");
        const artWorkUrl100 = $(this).data("coverArt");

        fetch("add-book", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({title: bookName, description: description, release_date: releaseDate, itunes_url: trackViewUrl, artwork_url: artWorkUrl100
            })
        })

    })
})
