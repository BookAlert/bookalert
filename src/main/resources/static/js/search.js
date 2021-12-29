$(() => {

    ///=================  FETCH DATA FOR AUTHOR
    $('body').on('click', '#authorSearch', function () {
        console.log("testing");
        let text = $('#authorSearchInput').val();
        text = text.charAt(0).toUpperCase() + text.substring(1).toLowerCase()
        var url = new URL('author-suggestions', window.location.origin)

        url.search = new URLSearchParams({search: text}).toString();
        Promise.all([
            fetch(url)
                .then(response => response.json()),

            fetch("user/authors")
                .then(response => response.json())
        ]).then(buildSearchResults);

    })



//================  FUNCTION TO MAP AUTHOR RESULTS TO HTML

    function buildSearchResults([ results, authorsList ]) {
        const html = results.map(result => {
            const isAdded = authorsList.includes(result.artistName)
            return `
                <div class="d-flex" >
                    <span class="text-light align-self-center mr-2">${result.artistName}</span>
                    <button class="btn btn-xs btn-outline-light align-self-center author-search-result"
                            data-name="${result.artistName}"
                            data-external-id="${result.artistId}"
                            ${ isAdded ? 'disabled': '' }>
                        ${isAdded ? 'ADDED' : 'ADD'}
                    </button>
                </div>
          `
        }).join("")
        console.log(results)
        console.log(authorsList)
        $('#authorResults').html(html)
    }

    //==================  POST RESULTS OF AUTHOR SEARCH W/ EVENT HANDLER
    $('body').on('click', '.author-search-result', function () {
        const name = $(this).data("name")
        const externalId = $(this).data("external-id")
        this.textContent = 'ADDED'
        this.setAttribute('disabled', 'true')
        fetch("add-author", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({name, externalId})
        }).then( () => {
            iziToast.success({
                title: 'Success',
                message: 'Successfully added author!',
                position: 'bottomRight',
                timeout: 1500
            })
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

        Promise.all([
            fetch(url)
                .then(response => response.json()),
            fetch("user/authors")
                .then(response => response.json())
        ]).then(buildTitleResults);
    })





    //================ FUNCTION TO MAP TITLE RESULTS TO HTML
    function buildTitleResults([ results, authorsList ]) {
        console.log(results)

        let html = results.map(result => {
            const isAdded = authorsList.includes(result.artistName)
            result.artworkUrl100 = result.artworkUrl100.replace('100x100bb', '200x200bb')
            return `
                <div class="card bg-transparent border-light mb-3">
                    <div class="card-body p-2">
                        <h4 class="text-light mb-0" data-title="${result.trackName}" >${result.trackName}</h4>
                        <div class="font-italic text-light mb-1">by ${result.artistName}</div>
                        <div class="d-flex title-card-contents mb-2">
                            <img class="rounded border border-light" alt="image" data-src="${result.artworkUrl100} hidden" src="${result.artworkUrl100}">
                            <small class="overflow-y-auto flex-grow-1 px-2 text-justify text-light tighten-line-height">${result.description}</small>
                        </div>
                        <div class="text-right">
                            <a class="btn btn-outline-light btn-sm text-uppercase" target="_blank" href="${result.trackViewUrl}">Buy from iTunes</a>
                            <button class="btn btn-outline-light btn-sm title-search-result"
                                    type="submit" 
                                    data-name="${result.artistName}"
                                    data-external-id="${result.artistId}"
                                    ${ isAdded ? 'disabled': '' }>
                                        ${isAdded ? 'Author Added' : 'Add Author'}
                            </button>
                        </div>
                    </div>
                </div>
            `
        }).join("")
        $('#titleResults').html(html)
    }


    //==================  POST RESULTS OF TITLE SEARCH W/ EVENT HANDLER
    $('body').on('click', '.title-search-result', async function(){
        const name = $(this).data("name");
        const externalId = $(this).data("external-id")
        this.textContent = 'Author Added'
        this.setAttribute('disabled', 'true')
        fetch(`add-author`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({name, externalId})
        }).then( () => {
            iziToast.success({
                title: 'Success',
                message: 'Successfully added author!',
                position: 'bottomRight',
                timeout: 1500
            })
        }).catch(() => {
            iziToast.fail({
                title: 'Failure',
                message: 'Author exists!',
                position: 'center',
                timeout: 1500
            })
        })
    })
})


