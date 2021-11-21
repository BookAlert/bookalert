$(() => {
    $('body').on('click', '#authorSearch', function(){
        console.log('added')
        const text = $('#authorSearchInput').val();
        var url = new URL('author-suggestions', window.location.origin )

        url.search = new URLSearchParams({search: text}).toString();

        fetch(url)
            .then(response => response.json())
            .then(buildSearchResults)
    })

    function buildSearchResults(results) {
        const html = results.map(result => `
            
            <div class="author-search-result" data-name="${result.artistName}"> <i class="fas fa-plus mr-2"></i>${result.artistName} </div>
            
          `).join("")
        $('#authorResults').html(html)
    }

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

})
