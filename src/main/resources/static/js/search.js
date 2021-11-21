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
        const html = results.map(result => `<div class="author-search-result">${result.artistName}</div>`)
        $('#authorResults').html(html)
    }
    console.log('attached')

})
