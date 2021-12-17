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

    // function renderCorrectIcon(author, userSavedAuthorNames) {
    //     // in condition, needs to be true only if resultId is in userSavedIds; if this is true, return a string for a checkmark icon, otherwirse return string for plus icon
    //     let icon = '<i class="fas fa-check mr-2 add-author" id="search"></i>';
    //     // for(let i = 0; i < resultId.length; i++) {
    //
    //         if(userSavedAuthorNames.includes(author.artistName)) {
    //             icon = '<i class="fas fa-check mr-2 add-author-button-checked" id="search"></i>';
    //         } else {
    //             icon = '<i class="fas fa-plus mr-2 add-author add-author-button" id="search"></i>';
    //         }
    //     return icon;
    //
    // }


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
                            <button class="btn btn-outline-light btn-sm title-search-result" type="submit" data-name="${result.artistName}" ${ isAdded ? 'disabled': '' }>${isAdded ? 'Author Added' : 'Add Author'}</button>
                        </div>
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
        }).then( () => {
            iziToast.success({
                title: 'Success',
                message: 'Successfully added author!',
                position: 'bottomRight',
                timeout: 1500
            })
            this.textContent = 'Author Added'
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

})


