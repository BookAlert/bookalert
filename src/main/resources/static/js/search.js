$(() => {

    ///=================  FETCH DATA FOR AUTHOR
    $('body').on('click', '#authorSearch', function () {
        console.log("testing");
        const text = $('#authorSearchInput').val();
        var url = new URL('author-suggestions', window.location.origin)

        url.search = new URLSearchParams({search: text}).toString();
        Promise.all([
            fetch(url)
                .then(response => response.json()),
            fetch("user/authors")
                .then(response => response.json())
        ]).then(buildSearchResults);

    })

    function renderCorrectIcon(author, userSavedAuthorNames) {
        // in condition, needs to be true only if resultId is in userSavedIds; if this is true, return a string for a checkmark icon, otherwirse return string for plus icon
        let icon = '<i class="fas fa-check mr-2 add-author" id="search"></i>';
        // for(let i = 0; i < resultId.length; i++) {

            if(userSavedAuthorNames.includes(author.artistName)) {
                icon = '<i class="fas fa-check mr-2 add-author-button-checked" id="search"></i>';
            } else {
                icon = '<i class="fas fa-plus mr-2 add-author add-author-button" id="search"></i>';
            }
        return icon;

    }


//================  FUNCTION TO MAP AUTHOR RESULTS TO HTML
    //call function instead of i tag
    // <span>${renderCorrectIcon(result, authorsList)}</span>
    function buildSearchResults([ results, authorsList ]) {
        const html = results.map(result => {
            const isAdded = authorsList.includes(result.artistName)
            return `
                <div class="d-flex" >
                    <span class="text-light align-self-center mr-2">${result.artistName}</span>
                    <button class="btn btn-xs btn-outline-light align-self-center author-search-result"
                            data-name="${result.artistName}"
                            ${ isAdded ? 'disabled': '' }>
                        ${isAdded ? 'ADDED' : 'ADD'}
                    </button>
                </div>
          `
        }).join("")
        $('#authorResults').html(html)
    }

    //==================  POST RESULTS OF AUTHOR SEARCH W/ EVENT HANDLER
    $('body').on('click', '.author-search-result', function () {
        const authorName = $(this).data("name");
        fetch("add-author", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({name: authorName})
        }).then( () => {
            iziToast.success({
                title: 'Success',
                message: 'Successfully added author!',
                position: 'bottomRight',
                timeout: 1500
            })
            // button.classList.remove('fa-plus')
            // button.classList.remove('add-author')
            // button.classList.add('fa-check')
            // button.classList.remove('add-author-button')
            // button.classList.add('add-author-button-checked')
            this.textContent = 'ADDED'
            this.setAttribute('disabled', 'true')

        }).catch(() => {
            iziToast.fail({
                title: 'Failure',
                message: 'Author exists!',
                position: 'center',
                timeout: 1500

            })
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

        let html = results.map(result => {
            result.artworkUrl100 = result.artworkUrl100.replace('100x100bb', '300x300bb')
            return `
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
                        <button class="btn btn-outline-info title-search-result" type="submit" data-name="${result.artistName}">Add Author</button>
        
                    </div>
                </div>
            `
        }).join("")


        $('#titleResults').html(html)
    }


    //==================  POST RESULTS OF TITLE SEARCH W/ EVENT HANDLER
    $('body').on('click', '.title-search-result', async function(){
        let authorName = $(this).data("name");





        fetch(`add-author`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                name:authorName
            })

        })

    })

    function getTitle(id) {
        return fetch(`https://itunes.apple.com/lookup?id=${id}`).then(response => response.json().then(result => {
            let book = result.results[0];

            return {
                externalId: book.trackId,
                title: book.trackCensoredName,
                description: book.description,
                release_date: book.releaseDate,
                itunes_url: book.trackViewUrl,
                artwork_url: book.artworkUrl100.replace('100x100bb', '300x300bb')
            }
        }))

    }
})


